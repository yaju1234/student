package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Pratibha on 3/3/2017.
 */

public class ResponseHomework {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ModelHomework> results;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public ArrayList<ModelHomework> getResults() {
        return results;
    }

    public void setResults(ArrayList<ModelHomework> results) {
        this.results = results;
    }
}
