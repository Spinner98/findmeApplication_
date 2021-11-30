package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changePasswordActivity extends AppCompatActivity {

RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        EditText pwd = (EditText) findViewById(R.id.editTextTextPassword1);
        Button conform = (Button) findViewById(R.id.changepwd);

        Intent intent = getIntent();
        String email = intent.getStringExtra("mail");
        System.out.println(email);

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = pwd.getText().toString();
                change(new changePwdData(email,password));

            }
        });
    }

    void change(org.techtown.findmeapplication.changePwdData data){
        service.changepwd(data).enqueue(new Callback<org.techtown.findmeapplication.changePwdResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.changePwdResponse> call, Response<org.techtown.findmeapplication.changePwdResponse> response) {
                org.techtown.findmeapplication.changePwdResponse result = response.body();
                int Code = result.getCode();
                if(Code ==200){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    Toast.makeText(changePasswordActivity.this, "변경 성공", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    Toast.makeText(changePasswordActivity.this, "변경 실패", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<changePwdResponse> call, Throwable t) {
                System.out.println("err");
            }
        });
    }
}