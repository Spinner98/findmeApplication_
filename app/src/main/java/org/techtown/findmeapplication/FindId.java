package org.techtown.findmeapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindId extends AppCompatActivity {
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
        Button check = (Button) findViewById(R.id.send);
        EditText phone =(EditText) findViewById(R.id.editTextTextPhone);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone_number = phone.getText().toString();
                findId(new org.techtown.findmeapplication.FindIdData(phone_number));
// 메시지
            }
        });
    }
    void findId(org.techtown.findmeapplication.FindIdData data){
        service.findId(data).enqueue(new Callback<org.techtown.findmeapplication.FindIdResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.FindIdResponse> call, Response<org.techtown.findmeapplication.FindIdResponse> response) {

                org.techtown.findmeapplication.FindIdResponse result = response.body();
                int Code = result.getCode();
                if(Code==204){
                    Toast.makeText(FindId.this,"해당 번호를 가진 회원이 없습니다.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else{
                    String email = result.getUserEmail();
                    AlertDialog.Builder dlg = new AlertDialog.Builder(FindId.this);

                    dlg.setTitle("아이디찾기").setMessage(email);
//                //버튼클릭시 동작
                    dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    dlg.show();
                }

            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.FindIdResponse> call, Throwable t) {
                System.out.println("err");
            }
        });
    }
}