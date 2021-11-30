package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class diary_write extends AppCompatActivity {
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);
        TextView Date = (TextView) findViewById(R.id.diary_date);
        Button conform = (Button) findViewById(R.id.conform);
        EditText Content = (EditText) findViewById(R.id.editTextTextMultiLine3);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        java.util.Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        Date.setText(year+"년"+month +"월"+day+"일");

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ContentText = Content.getText().toString();
                String dateText = Date.getText().toString();
                if(ContentText.length() ==0){
                    Toast.makeText(diary_write.this, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    int number = Integer.parseInt(id); //숫자로 변형된 id
                    Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                    registerDiary(new org.techtown.findmeapplication.Insert_Diary_user_Data(number,ContentText,dateText));
                    startActivity(intent);

                }
            }
        });

    }
    void registerDiary(org.techtown.findmeapplication.Insert_Diary_user_Data data){
        service.userRegisterDairy(data).enqueue(new Callback<org.techtown.findmeapplication.insert_Diary_user_Response>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.insert_Diary_user_Response> call, Response<org.techtown.findmeapplication.insert_Diary_user_Response> response) {
                org.techtown.findmeapplication.insert_Diary_user_Response result = response.body();
                int Code = result.getCode();
                if(Code ==200){
                    System.out.println("성공");
                }
            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.insert_Diary_user_Response> call, Throwable t) {
                System.out.println("실패");
            }
        });
    }
}