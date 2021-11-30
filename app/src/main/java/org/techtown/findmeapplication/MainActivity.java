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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    public String question;
    public String url;
    String loginId, loginPwd;
    public SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.pwd);
        Button Login = (Button) findViewById(R.id.btn_login);
        Button Register = (Button) findViewById(R.id.btn_Register);
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);
        TextView findId = (TextView)findViewById(R.id.Find_id);
        TextView findpwd = (TextView)findViewById(R.id.find_pwd);
        questionFragment q = new questionFragment();
        CheckBox autoLogin = (CheckBox) findViewById(R.id.checkBox);

//회원가입
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register_userName.class);
                startActivity(intent);
            }
        });
        //로그인
        if(loginId ==null && loginPwd==null && autoLogin.isChecked()){
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userEmail = email.getText().toString();
                    String userPwd = password.getText().toString();
                    startLogin(new LoginData(userEmail,userPwd),true);


                }
            });
        }//자동로그인 체크 후, 로그인 했을 경우(첫 로그인)
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userEmail = email.getText().toString();
                    String userPwd = password.getText().toString();
                    startLogin(new LoginData(userEmail,userPwd),true);
                }
            });//자동로그인 체크 없이 로그인 했을 경우

        if(loginId !=null && loginPwd !=null){
            startLogin(new org.techtown.findmeapplication.LoginData(loginId,loginPwd));
        }//자동로그인 체크 후, 다시 접속 시



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPwd = password.getText().toString();
                startLogin(new LoginData(userEmail,userPwd),true);
            }
        });//자동로그인을 체크안하고 로그인 하는 경우.

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
    void startLogin(org.techtown.findmeapplication.LoginData data,boolean check){
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

    void startLogin(org.techtown.findmeapplication.LoginData data){
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




    //뒤로가기 맊기
    @Override public void onBackPressed() {  }

}