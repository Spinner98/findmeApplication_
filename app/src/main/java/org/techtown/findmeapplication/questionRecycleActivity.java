package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class questionRecycleActivity extends AppCompatActivity {
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    QuestionContentResponse userQuestion;
    public ArrayList dateInfo = new ArrayList<>();
    public ArrayList contentInfo = new ArrayList<>();
    public ArrayList questionInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_recycle);
        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        int number = Integer.parseInt(id); //id 받아오기
        setQuestion(new QuestionContentData(number));
    }

    void setQuestion(QuestionContentData data){
        service.userQuestion(data).enqueue(new Callback<QuestionContentResponse>() {
            @Override
            public void onResponse(Call<QuestionContentResponse> call, Response<QuestionContentResponse> response) {
                userQuestion = response.body();
                dateInfo = userQuestion.getQuestiondate();
                contentInfo = userQuestion.getQuestioncontent();
                questionInfo = userQuestion.getQuestion();
                // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
                questionReclycleAdapter adapter = new questionReclycleAdapter(questionInfo,contentInfo,dateInfo) ;
                RecyclerView recyclerView = findViewById(R.id.recyle_question) ;
                recyclerView.setLayoutManager(new LinearLayoutManager(questionRecycleActivity.this)) ;
                recyclerView.setAdapter(adapter) ;
            }

            @Override
            public void onFailure(Call<QuestionContentResponse> call, Throwable t) {

            }
        });

    }
}