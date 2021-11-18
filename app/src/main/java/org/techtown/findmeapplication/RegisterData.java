package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class RegisterData {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userPhone")
    private String userPhone;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPwd")
    private String userPwd;
    @SerializedName("preQuestion")
    private int preQuestion;

    public RegisterData(String userName,String userPhone, String userEmail, String userPwd,int preQuestion) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.preQuestion = preQuestion;
    }
}
