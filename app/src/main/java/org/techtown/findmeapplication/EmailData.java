package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class EmailData {
    @SerializedName("email")
    String useremail;

    public EmailData(String useremail) {
        this.useremail = useremail;
    }
}
