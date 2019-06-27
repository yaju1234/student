package com.turtle.eschool.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ResHolidays {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("results")
    @Expose
    ArrayList<ModelHolidays> results;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ModelHolidays> getResults() {
        return results;
    }

    public void setResults(ArrayList<ModelHolidays> results) {
        this.results = results;
    }
}
