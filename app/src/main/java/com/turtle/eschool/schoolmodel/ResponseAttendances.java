package com.turtle.eschool.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 13/02/2017.
 */
public class ResponseAttendances {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<SchoolAttendacnce> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<SchoolAttendacnce> getResult() {
        return result;
    }

    public void setResult(ArrayList<SchoolAttendacnce> result) {
        this.result = result;
    }
}
