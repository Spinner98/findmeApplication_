package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class insert_Diary_user_Response {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
