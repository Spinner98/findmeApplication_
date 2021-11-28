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

public class question_write extends AppCompatActivity {
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_write);
        TextView Date = (TextView) findViewById(R.id.diary_date);
        Button Conform = (Button) findViewById(R.id.conform);


        Intent intent = getIntent();
        String question =intent.getStringExtra("question");
        String id = intent.getStringExtra("id");


        EditText Content_text = (EditText) findViewById(R.id.editTextTextMultiLine3);
        TextView questionText_write = (TextView) findViewById(R.id.question);
        questionText_write.setText(question);

        java.util.Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);

        Date.setText(year+"년"+month +"월"+day+"일");
        String DateText = Date.getText().toString();


        Conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Content = Content_text.getText().toString();
                if(Content.length() ==0){
                    Toast.makeText(question_write.this, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                }else{
                    int number = Integer.parseInt(id); //숫자로 변형된 id
                    RegisterContent_question(new Register_question_Data(number,question,Content,DateText)); //서버에 내용 보내기

                }


            }
        });


    }
    void RegisterContent_question(Register_question_Data data){
        service.userRegisterQuestion(data).enqueue(new Callback<Register_question_Response>() {
            @Override
            public void onResponse(Call<Register_question_Response> call, Response<Register_question_Response> response) {
                Register_question_Response result = response.body();
                int questioncheckcode = result.getCode();
                if(questioncheckcode == 500){
                    System.out.println("성공");
                    Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Register_question_Response> call, Throwable t) {
                System.out.println("실패");
            }
        });
    }


}