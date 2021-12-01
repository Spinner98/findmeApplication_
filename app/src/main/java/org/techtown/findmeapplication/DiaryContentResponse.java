package org.techtown.findmeapplication;

import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DiaryContentResponse {

    @SerializedName("diarycontent")
    private ArrayList diarycontent;

    @SerializedName("diarydate")
    private ArrayList diarydate;

    @SerializedName("result")
    private ArrayList result;

    public ArrayList getDiarycontent(){return diarycontent;}

    public ArrayList getDiarydate(){return diarydate;}

    public ArrayList getResult(){return result;}


    }


