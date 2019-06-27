package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 30/04/2017.
 */
public class ResponseExams {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("results")
    @Expose
    ArrayList<ModelExams> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ModelExams> getResults() {
        return results;
    }

    public void setResults(ArrayList<ModelExams> results) {
        this.results = results;
    }
}
