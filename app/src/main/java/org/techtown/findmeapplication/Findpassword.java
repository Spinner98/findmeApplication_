package org.techtown.findmeapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class Findpassword extends AppCompatActivity {
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);
    public String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);
        Button check = (Button) findViewById(R.id.email_conti);
        EditText mail = (EditText) findViewById(R.id.email);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                checkEmail(new org.techtown.findmeapplication.EmailData(email));

            }
        });
    }
    public void checkEmail(org.techtown.findmeapplication.EmailData data){
        service.userEmail(data).enqueue(new Callback<org.techtown.findmeapplication.EmailResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.EmailResponse> call, Response<org.techtown.findmeapplication.EmailResponse> response) {
                org.techtown.findmeapplication.EmailResponse result = response.body();

                if(result.getCode()==204){
                    Intent intent = new Intent(getApplicationContext(),changePasswordActivity.class);
                    mail = result.getMail();
                    intent.putExtra("mail",mail);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(Findpassword.this, "회원이 아닙니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.EmailResponse> call, Throwable t) {
                Toast.makeText(Findpassword.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }
}