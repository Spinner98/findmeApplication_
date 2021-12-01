package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryRecycleActivity extends AppCompatActivity {
   RetrofitApi service = RetrofitClient.getClient().create(RetrofitApi.class);
   DiaryContentResponse userDiary;
   public ArrayList dateInfo = new ArrayList<>();
    public ArrayList contentInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_recycle);
        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        int number = Integer.parseInt(id);
        setDiary(new DiaryContentData(number));

    }
    void setDiary(DiaryContentData data){
        service.userdiary(data).enqueue(new Callback<DiaryContentResponse>() {
            @Override
            public void onResponse(Call<DiaryContentResponse> call, Response<DiaryContentResponse> response) {
               userDiary = response.body();
               dateInfo = userDiary.getDiarydate();
               contentInfo = userDiary.getDiarycontent();

                // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
              diaryReclyeAdapter adapter = new diaryReclyeAdapter(dateInfo,contentInfo) ;
              RecyclerView recyclerView = findViewById(R.id.recyle_diary) ;
              recyclerView.setLayoutManager(new LinearLayoutManager(DiaryRecycleActivity.this)) ;
              recyclerView.setAdapter(adapter) ;
            }

            @Override
            public void onFailure(Call<DiaryContentResponse> call, Throwable t) {
            }


        });
    }
}