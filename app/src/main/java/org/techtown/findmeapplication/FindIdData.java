package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class FindIdData {
    @SerializedName("phone_number")
    String phone_number;

    public FindIdData(String phone_number) {
        this.phone_number = phone_number;
    }
}
