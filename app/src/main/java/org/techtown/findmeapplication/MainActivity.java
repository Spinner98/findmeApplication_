package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    public String question;
    public String url;
    String loginId, loginPwd;
    long waitTime = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.pwd);
        Button Login = (Button) findViewById(R.id.btn_login);
        Button Register = (Button) findViewById(R.id.btn_Register);

        TextView findId = (TextView)findViewById(R.id.Find_id);
        TextView findpwd = (TextView)findViewById(R.id.find_pwd);
        CheckBox autoLogin = (CheckBox) findViewById(R.id.checkBox);

        //로그인
        if(loginId !=null && loginPwd !=null){
            startLogin(new LoginData(loginId,loginPwd));
        }//자동로그인 체크 후, 다시 접속 시

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoLogin.isChecked()==true&&loginId==null&&loginPwd==null){
                    String userEmail = email.getText().toString();
                    String userPwd = password.getText().toString();
                    startLogin(new LoginData(userEmail,userPwd),true);
                    System.out.println("auto");
                }else if(autoLogin.isChecked()==false&&loginId==null&&loginPwd==null){
                    String userEmail = email.getText().toString();
                    String userPwd = password.getText().toString();
                    startLogin(new LoginData(userEmail,userPwd));
                    System.out.println("no auto");
                }
            }
        });

//회원가입
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register_userName.class);
                startActivity(intent);
            }
        });
        //아이디찾기
       findId.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent intent = new Intent(getApplicationContext(),FindId.class);
               startActivity(intent);
           }
       });
       //비밀번호찾기
        findpwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Findpassword.class);
                startActivity(intent);
            }
        });
    }
    //로그인 메인기능
    void startLogin(LoginData data){
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                int code = result.getCode();
                if(code ==200){
                    Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                    String id = result.getUserId();
                    question = result.getQuestion();
                    url = result.getUrl();
                    String present = result.getpresent();
                    intent.putExtra("id",id);
                    intent.putExtra("question",question);
                    intent.putExtra("url",url);
                    intent.putExtra("present",present);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌

            }
        });
    }

    void startLogin(LoginData data,boolean check){
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        if(check ==true){
            service.userLogin(data).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse result = response.body();
                    int code = result.getCode();
                    if(code ==200){
                        Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                        String id = result.getUserId();
                        int number = Integer.parseInt(id);
                        question = result.getQuestion();
                        url = result.getUrl();
                        String usermail = result.getUsermail();
                        String password = result.getPassword();

                        SharedPreferences.Editor autoLogin = auto.edit();
                        autoLogin.putString("inputId",usermail);
                        autoLogin.putString("inputPwd",password);
                        autoLogin.commit();

                        intent.putExtra("id",id);
                        intent.putExtra("question",question);
                        intent.putExtra("url",url);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                    Log.e("로그인 에러 발생", t.getMessage());
                    t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌

                }
            });
        }

    }





    //뒤로가기 맊기
    @Override public void onBackPressed() {
        if(System.currentTimeMillis() - waitTime >=1500 ) {
            waitTime = System.currentTimeMillis();
            Toast.makeText(this,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        } else {
            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료
        }
    }

}