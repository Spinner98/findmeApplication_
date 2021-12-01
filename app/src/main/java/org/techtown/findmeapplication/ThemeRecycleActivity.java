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

public class ThemeRecycleActivity extends AppCompatActivity {
    RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
    ThemeContentResponse userTheme;
    public ArrayList detailInfo= new ArrayList<>();
    public ArrayList questionInfo = new ArrayList<>();
    public ArrayList urlInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_recycle);
        Intent intent = getIntent();
        String prequestion = intent.getStringExtra("present");
        int number = Integer.parseInt(prequestion);
        setTheme(new ThemeContentData(number));

    }
    public void setTheme(ThemeContentData data){
        service.userTheme(data).enqueue(new Callback<ThemeContentResponse>() {
            @Override
            public void onResponse(Call<ThemeContentResponse> call, Response<ThemeContentResponse> response) {
                userTheme = response.body();
                detailInfo = userTheme.getDetail();
                questionInfo = userTheme.getQuestion();
                System.out.println(questionInfo);
                ThemeRecycleAdapter adapter = new ThemeRecycleAdapter(questionInfo,detailInfo,urlInfo) ;
                RecyclerView recyclerView = findViewById(R.id.recyle_Theme) ;
                recyclerView.setLayoutManager(new LinearLayoutManager(ThemeRecycleActivity.this)) ;
                recyclerView.setAdapter(adapter) ;
            }

            @Override
            public void onFailure(Call<ThemeContentResponse> call, Throwable t) {

            }
        });
    }
}