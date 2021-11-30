package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class DiaryContentData {
    @SerializedName("userid")
    private int userid;
    DiaryContentData(int userid){
        this.userid = userid;
    }
}
