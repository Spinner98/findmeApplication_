package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class ThemeContentData {
    @SerializedName("num")
    private int num;
    ThemeContentData (int num){
        this.num = num;
    }
}
