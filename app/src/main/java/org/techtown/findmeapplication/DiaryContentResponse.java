package org.techtown.findmeapplication;

import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiaryContentResponse {

    @SerializedName("diarycontent")
    private List diarycontent;

    @SerializedName("diarydate")
    private List diarydate;



    public List getDiarycontent(){return diarycontent;}
    public List getDiarydate(){return diarydate;}


    }


