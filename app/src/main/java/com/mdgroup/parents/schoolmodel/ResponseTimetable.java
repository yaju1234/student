package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Pratibha on 3/4/2017.
 */

public class ResponseTimetable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ModelTimeTable> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ModelTimeTable> getResults() {
        return results;
    }

    public void setResults(ArrayList<ModelTimeTable> results) {
        this.results = results;
    }
}
