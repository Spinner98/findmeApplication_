package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_UserPhone extends AppCompatActivity {
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);
    public boolean check =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_phone);
        Button send = (Button) findViewById(R.id.send);
        Button next =(Button) findViewById(R.id.conti_next);
        EditText phone = (EditText) findViewById(R.id.editTextTextPhone);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name_string"); // email 값 받기

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String phoneNumber=phone.getText().toString();
                phonecheck(phoneNumber);

            }
        }); // 핸드폰번호 조회

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check ==true){
                    String phoneNumber=phone.getText().toString();
                    Intent intent = new Intent(getApplicationContext(),Register_userEmail.class);
                    intent.putExtra("name_string",name);
                    intent.putExtra("phone_string",phoneNumber);
                    startActivity(intent);
                }else{

                }

            }
        }); // 다음 액티비티 이동 전환
    }
    void phonecheck(String phone){
        if(phone.length() ==11){
            if(phone.contains("010")){
                phone(new org.techtown.findmeapplication.PhoneData(phone));
            }else{
                Toast.makeText(Register_UserPhone.this, "잘못된 형식을 가진 번호입니다.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Register_UserPhone.this, "잘못된 형식을 가진 번호입니다.", Toast.LENGTH_SHORT).show();
        }
    }
    void phone(org.techtown.findmeapplication.PhoneData data){
        service.userPhone(data).enqueue(new Callback<org.techtown.findmeapplication.PhoneResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.PhoneResponse> call, Response<org.techtown.findmeapplication.PhoneResponse> response) {
                org.techtown.findmeapplication.PhoneResponse result = response.body();
                Toast.makeText(Register_UserPhone.this, result.getMessage(), Toast.LENGTH_SHORT).show();
            int code = result.getCode();
            if(code ==200){
                check =true;
            }
            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.PhoneResponse> call, Throwable t) {
                Toast.makeText(Register_UserPhone.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }
}