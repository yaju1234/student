package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pratibha on 3/3/2017.
 */

public class ModelHomework implements Serializable{
    @SerializedName("HOMEWORK_ID")
    @Expose
    private String HOMEWORK_ID;

    @SerializedName("SUBJECTS_NAME")
    @Expose
    private String SUBJECTS_NAME;


    @SerializedName("HOMEWORK_TOPIC")
    @Expose
    private String HOMEWORK_TOPIC;


    @SerializedName("HOMEWORK_DESCRIPTION")
    @Expose
    private String HOMEWORK_DESCRIPTION;

    public String getHOMEWORK_ID() {
        return HOMEWORK_ID;
    }

    public void setHOMEWORK_ID(String HOMEWORK_ID) {
        this.HOMEWORK_ID = HOMEWORK_ID;
    }

    public String getSUBJECTS_NAME() {
        return SUBJECTS_NAME;
    }

    public void setSUBJECTS_NAME(String SUBJECTS_NAME) {
        this.SUBJECTS_NAME = SUBJECTS_NAME;
    }

    public String getHOMEWORK_TOPIC() {
        return HOMEWORK_TOPIC;
    }

    public void setHOMEWORK_TOPIC(String HOMEWORK_TOPIC) {
        this.HOMEWORK_TOPIC = HOMEWORK_TOPIC;
    }

    public String getHOMEWORK_DESCRIPTION() {
        return HOMEWORK_DESCRIPTION;
    }

    public void setHOMEWORK_DESCRIPTION(String HOMEWORK_DESCRIPTION) {
        this.HOMEWORK_DESCRIPTION = HOMEWORK_DESCRIPTION;
    }
}
