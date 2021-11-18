package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class PhoneResponse {
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
