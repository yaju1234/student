package com.turtle.eschool.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 05/02/2017.
 */
public class NewsResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ModelNews> result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ModelNews> getResult() {
        return result;
    }

    public void setResult(ArrayList<ModelNews> result) {
        this.result = result;
    }
}
