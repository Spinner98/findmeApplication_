package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class userQuestionData {
    @SerializedName("userid")
    int userid;

    public userQuestionData(int userid) {
        this.userid = userid;
    }
}
