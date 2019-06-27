package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mdgroup.parents.adptercstm.adpter.chat.ChatMessage;

import java.util.ArrayList;

/**
 * Created by Gaurav on
 */
public class ResponseAllChat {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    ArrayList<ChatMessage> result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ChatMessage> getResult() {
        return result;
    }

    public void setResult(ArrayList<ChatMessage> result) {
        this.result = result;
    }
}
