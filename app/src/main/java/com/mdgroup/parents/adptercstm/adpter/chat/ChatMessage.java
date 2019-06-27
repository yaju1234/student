package com.mdgroup.parents.adptercstm.adpter.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatMessage {

    @SerializedName("CHATS_ID")
    @Expose
    private String CHATS_ID;

    @SerializedName("CHATS_MESSAGE")
    @Expose
    private String CHATS_MESSAGE;

    @SerializedName("TEACHERS_NAME")
    @Expose
    private String TEACHERS_NAME;

    @SerializedName("STUDENT_FIRST_NAME")
    @Expose
    private String STUDENT_FIRST_NAME;

    @SerializedName("STUDENT_FATHER_NAME")
    @Expose
    private String STUDENT_FATHER_NAME;

    @SerializedName("CHATS_SIDE")
    @Expose
    private String CHATS_SIDE;

    public String getCHATS_ID() {
        return CHATS_ID;
    }

    public void setCHATS_ID(String CHATS_ID) {
        this.CHATS_ID = CHATS_ID;
    }

    public String getCHATS_MESSAGE() {
        return CHATS_MESSAGE;
    }

    public void setCHATS_MESSAGE(String CHATS_MESSAGE) {
        this.CHATS_MESSAGE = CHATS_MESSAGE;
    }

    public String getTEACHERS_NAME() {
        return TEACHERS_NAME;
    }

    public void setTEACHERS_NAME(String TEACHERS_NAME) {
        this.TEACHERS_NAME = TEACHERS_NAME;
    }

    public String getSTUDENT_FIRST_NAME() {
        return STUDENT_FIRST_NAME;
    }

    public void setSTUDENT_FIRST_NAME(String STUDENT_FIRST_NAME) {
        this.STUDENT_FIRST_NAME = STUDENT_FIRST_NAME;
    }

    public String getSTUDENT_FATHER_NAME() {
        return STUDENT_FATHER_NAME;
    }

    public void setSTUDENT_FATHER_NAME(String STUDENT_FATHER_NAME) {
        this.STUDENT_FATHER_NAME = STUDENT_FATHER_NAME;
    }

    public String getCHATS_SIDE() {
        return CHATS_SIDE;
    }

    public void setCHATS_SIDE(String CHATS_SIDE) {
        this.CHATS_SIDE = CHATS_SIDE;
    }

    /*
    public boolean side;
	public String message;*/

    public ChatMessage(String CHATS_ID, String CHATS_MESSAGE, String TEACHERS_NAME, String STUDENT_FIRST_NAME, String STUDENT_FATHER_NAME, String CHATS_SIDE) {
        this.CHATS_ID = CHATS_ID;
        this.CHATS_MESSAGE = CHATS_MESSAGE;
        this.TEACHERS_NAME = TEACHERS_NAME;
        this.STUDENT_FIRST_NAME = STUDENT_FIRST_NAME;
        this.STUDENT_FATHER_NAME = STUDENT_FATHER_NAME;
        this.CHATS_SIDE = CHATS_SIDE;
    }
}