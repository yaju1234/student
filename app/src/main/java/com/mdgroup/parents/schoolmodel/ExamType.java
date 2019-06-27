package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ExamType implements Serializable {

    @SerializedName("EXAM_TYPE_ID")
    @Expose
    private String EXAM_TYPE_ID;

    @SerializedName("EXAM_TYPE_NAME")
    @Expose
    private String EXAM_TYPE_NAME;

    public String getEXAM_TYPE_ID() {
        return EXAM_TYPE_ID;
    }

    public void setEXAM_TYPE_ID(String EXAM_TYPE_ID) {
        this.EXAM_TYPE_ID = EXAM_TYPE_ID;
    }

    public String getEXAM_TYPE_NAME() {
        return EXAM_TYPE_NAME;
    }

    public void setEXAM_TYPE_NAME(String EXAM_TYPE_NAME) {
        this.EXAM_TYPE_NAME = EXAM_TYPE_NAME;
    }

    public ExamType() {
    }
}
