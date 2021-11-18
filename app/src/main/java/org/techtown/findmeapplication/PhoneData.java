package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class PhoneData {
    @SerializedName("phone_number")
    String userphone;

    public PhoneData(String userphone) {
        this.userphone = userphone;
    }
}
