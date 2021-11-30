package org.techtown.findmeapplication;

import com.google.gson.annotations.SerializedName;

public class changePwdData {
    @SerializedName("useremail")
    String useremail;

    @SerializedName("pwd")
    String pwd;

    public changePwdData(String useremail,String pwd) {
        this.useremail = useremail;
        this.pwd =pwd;

    }
}
