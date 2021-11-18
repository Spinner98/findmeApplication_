package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class QuestionResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("question")
    private String question;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getQuestion(){return question;}
}
