package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 05/02/2017.
 */
public class ResponseTecherChat {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ModelTeacher> result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ModelTeacher> getResult() {
        return result;
    }

    public void setResult(ArrayList<ModelTeacher> result) {
        this.result = result;
    }
}
