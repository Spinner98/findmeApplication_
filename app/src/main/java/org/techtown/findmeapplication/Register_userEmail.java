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

public class Register_userEmail extends AppCompatActivity {
    private EditText mEmailView;
    private Button emailConti;
    public boolean check =false;
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_email);
        mEmailView =(EditText) findViewById(R.id.email);
        emailConti = (Button) findViewById(R.id.email_conti);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name_string"); // 이름
        String phone = extras.getString("phone_string");//핸드폰 번호
        emailConti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkEmail();
                if(check ==true){
                    String email = mEmailView.getText().toString();

                    Intent intent = new Intent(getApplicationContext(),Register_userpassword.class);
                    intent.putExtra("name_string",name);
                    intent.putExtra("phone_string",phone);
                    intent.putExtra("email_string",email);
                    startActivity(intent);
                }


            }
        });
    }
    private void checkEmail(){
        mEmailView.setError(null);
        String email = mEmailView.getText().toString();
        View focusView = null;

        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;

        } else if (!isEmailValid(email)) {
            mEmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mEmailView;

        }else{
            checkEmail(new org.techtown.findmeapplication.EmailData(email));
        }

    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public void checkEmail(org.techtown.findmeapplication.EmailData data){
        service.userEmail(data).enqueue(new Callback<org.techtown.findmeapplication.EmailResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.EmailResponse> call, Response<org.techtown.findmeapplication.EmailResponse> response) {
                org.techtown.findmeapplication.EmailResponse result = response.body();

                if(result.getCode()==200){
                    check =true;
                    Toast.makeText(Register_userEmail.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Register_userEmail.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.EmailResponse> call, Throwable t) {
                Toast.makeText(Register_userEmail.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }
}