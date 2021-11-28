package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class Register_question_Data{
    @SerializedName("userid")
    private int userid;

    @SerializedName("question")
    private String question;

    @SerializedName("content")
    private String content;

    @SerializedName("Createdat")
    private String Createdat;


    public Register_question_Data(int userid, String question, String content, String Createdat) {
        this.userid =userid;
        this.question = question;
        this.content = content;
        this.Createdat = Createdat;
    }
}
