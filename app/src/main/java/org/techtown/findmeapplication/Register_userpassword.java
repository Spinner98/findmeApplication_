package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_userpassword extends AppCompatActivity {
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    public boolean check =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_userpassword);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name_string"); // 이름
        String phone = extras.getString("phone_string");//핸드폰 번호
        String email = extras.getString("email_string");//이메일

        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        EditText passwordcheck = (EditText) findViewById(R.id.editTextTextPassword_conform);
        Button register = (Button) findViewById(R.id.button);
        TextView find_id = (TextView) findViewById(R.id.Find_id);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String pwd = password.getText().toString();
               String pwdcheck = passwordcheck.getText().toString();
               check(pwd,pwdcheck);
               if(check ==true){
                   Register(new RegisterData(name,phone,email,pwd,1));
               }
            }
        });
    }

    void check(String pwd,String pwdcheck){
        if(pwd.equals(pwdcheck)){
            check =true;
        }else{
            Toast.makeText(Register_userpassword.this, "비밀번호가 같지않습니다", Toast.LENGTH_SHORT).show();
        }
    }
    void Register(RegisterData data){
        service.userRegister(data).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse result = response.body();
                Toast.makeText(Register_userpassword.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Register_userpassword.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }
}