package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ResResults {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ResultExams> result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ResultExams> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultExams> result) {
        this.result = result;
    }
}
