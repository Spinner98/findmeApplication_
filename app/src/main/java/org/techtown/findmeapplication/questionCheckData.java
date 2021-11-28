package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class questionCheckData {
    @SerializedName("id")
    int id;
    @SerializedName("question")
    String question;

    public questionCheckData(int id,String question) {
        this.id = id;
        this.question =question;
    }
}
