package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    org.techtown.findmeapplication.RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    public static boolean check=false;
    public String id;
    public String question;
    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_write);
        TextView Date = (TextView) findViewById(R.id.diary_date);
        Button Conform = (Button) findViewById(R.id.conform);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        question = intent.getStringExtra("question");
        url = intent.getStringExtra("url");

        TextView questionText = (TextView)findViewById(R.id.question);
        questionText.setText(question);
        EditText Content_text = (EditText)findViewById(R.id.editTextTextMultiLine3);
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
                    check =true;
                    RegisterContent_question(new Register_question_Data(number,question,Content,DateText)); //서버에 내용 보내기
                    updateprequsetion(new prequestionUpdateDate(number));
                }


            }
        });


    }
    void RegisterContent_question(org.techtown.findmeapplication.Register_question_Data data){
        service.userRegisterQuestion(data).enqueue(new Callback<Register_question_Response>() {
            @Override
            public void onResponse(Call<Register_question_Response> call, Response<Register_question_Response> response) {
                Register_question_Response result = response.body();
                int questioncheckcode = result.getCode();
                if(questioncheckcode == 500){
                    System.out.println("성공");
                    check =true;
                    Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Register_question_Response> call, Throwable t) {
                System.out.println("실패");
            }
        });
    }
    void updateprequsetion(prequestionUpdateDate data){
        service.userQuestionUp(data).enqueue(new Callback<prequestionUpdateResponse>() {
            @Override
            public void onResponse(Call<prequestionUpdateResponse> call, Response<prequestionUpdateResponse> response) {

                prequestionUpdateResponse result = response.body();
                result.getMessage();
            }

            @Override
            public void onFailure(Call<prequestionUpdateResponse> call, Throwable t) {
                System.out.println("실패");
            }
        });
    }


}