package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryRecycleActivity extends AppCompatActivity {
    org.techtown.findmeapplication.RetrofitApi service = org.techtown.findmeapplication.RetrofitClient.getClient().create(org.techtown.findmeapplication.RetrofitApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_recycle);

        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        System.out.println(id);
        int number = Integer.parseInt(id);
        setDiary(new org.techtown.findmeapplication.DiaryContentData(number));

    }
    void setDiary(org.techtown.findmeapplication.DiaryContentData data){
        service.userdiary(data).enqueue(new Callback<org.techtown.findmeapplication.DiaryContentResponse>() {
            @Override
            public void onResponse(Call<org.techtown.findmeapplication.DiaryContentResponse> call, Response<org.techtown.findmeapplication.DiaryContentResponse> response) {
                org.techtown.findmeapplication.DiaryContentResponse result = response.body();


            }

            @Override
            public void onFailure(Call<org.techtown.findmeapplication.DiaryContentResponse> call, Throwable t) {

                Log.d("DiaryRecycleActiviy",t.toString());
            }
        });
    }
}