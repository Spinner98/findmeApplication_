package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class prequestionUpdateDate {
    @SerializedName("userid")
    private int userid;
    prequestionUpdateDate(int userid){
        this.userid = userid;
    }
}
