package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class Insert_Diary_user_Data {
    @SerializedName("userid")
    private int userid;

    @SerializedName("content")
    private String content;

    @SerializedName("Create")
    private String Create;


    public Insert_Diary_user_Data(int userid,String content, String Create) {
        this.userid =userid;
        this.content = content;
        this.Create = Create;
    }
}
