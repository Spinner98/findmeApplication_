package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ThemeContentResponse {
    @SerializedName("detail")
    private ArrayList detail;

    @SerializedName("question")
    private ArrayList question;

    @SerializedName("url")
    private ArrayList url;

    @SerializedName("result")
    private ArrayList result;

    public ArrayList getUrl(){return url;}
    public ArrayList getDetail(){return detail;}
    public ArrayList getQuestion(){return question;}

    public ArrayList getResult(){return result;}
}
