package com.turtle.eschool.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Pratibha on 3/2/2017.
 */

public class ResponseTeacher {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ModelTeacher> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ModelTeacher> getResults() {
        return results;
    }

    public void setResults(ArrayList<ModelTeacher> results) {
        this.results = results;
    }
}

