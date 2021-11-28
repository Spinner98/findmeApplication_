package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class questionCheckResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("questionDate")
    private String questionDate;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    public String getQuestionDate(){return questionDate;}
}
