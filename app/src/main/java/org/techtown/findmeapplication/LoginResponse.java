package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

    @SerializedName("question")
    private String question;

    @SerializedName("url")
    private String url;

    @SerializedName("questionCreate")
    private String questionCreate;

    @SerializedName("usermail")
    private String usermail;

    @SerializedName("password")
    private String password;

    @SerializedName("present")
    private String present;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return id;
    }
    public String getQuestion() {return question;}
    public String getUrl() {return url;}
    public String getQuestionCreate(){return  questionCreate;}
    public String getPassword(){return password;}
    public String getUsermail(){return usermail;}
    public String getpresent(){return present;}
}
