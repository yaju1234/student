package com.turtle.eschool.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 30/04/2017.
 */
public class ResponseExamsMain {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("results")
    @Expose
    ArrayList<ExamType> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ExamType> getResults() {
        return results;
    }

    public void setResults(ArrayList<ExamType> results) {
        this.results = results;
    }
}
