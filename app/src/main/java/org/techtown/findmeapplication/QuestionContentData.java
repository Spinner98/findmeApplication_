package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class QuestionContentData {
    @SerializedName("userid")
    private int userid;
    QuestionContentData (int userid){
        this.userid = userid;
    }
}
