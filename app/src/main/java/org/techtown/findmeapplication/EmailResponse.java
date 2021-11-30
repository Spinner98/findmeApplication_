package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class EmailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("mail")
    private String mail;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMail() {return mail;}
}
