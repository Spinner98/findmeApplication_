package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionContentResponse {
    @SerializedName("questioncontent")
    private ArrayList questioncontent;

    @SerializedName("questiondate")
    private ArrayList questiondate;

    @SerializedName("question")
    private ArrayList question;

    @SerializedName("result")
    private ArrayList result;

    public ArrayList getQuestioncontent(){return questioncontent;}
    public ArrayList getQuestiondate(){return questiondate;}
    public ArrayList getQuestion(){return question;}

    public ArrayList getResult(){return result;}
}
