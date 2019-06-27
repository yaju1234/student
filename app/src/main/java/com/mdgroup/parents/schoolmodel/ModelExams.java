package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ModelExams implements Serializable {

    @SerializedName("EXAMS_ID")
    @Expose
    private String EXAMS_ID;

    @SerializedName("EXAMS_NAME")
    @Expose
    private String EXAMS_NAME;


    @SerializedName("EXAM_SUBJECT_NAME")
    @Expose
    private String EXAM_SUBJECT_NAME;


    @SerializedName("EXAMS_DATE")
    @Expose
    private String EXAMS_DATE;


    @SerializedName("EXAMS_START_TIME")
    @Expose
    private String EXAMS_START_TIME;

    @SerializedName("EXAMS_END_TIME")
    @Expose
    private String EXAMS_END_TIME;

    public String getEXAMS_ID() {
        return EXAMS_ID;
    }

    public void setEXAMS_ID(String EXAMS_ID) {
        this.EXAMS_ID = EXAMS_ID;
    }

    public String getEXAMS_NAME() {
        return EXAMS_NAME;
    }

    public void setEXAMS_NAME(String EXAMS_NAME) {
        this.EXAMS_NAME = EXAMS_NAME;
    }

    public String getEXAM_SUBJECT_NAME() {
        return EXAM_SUBJECT_NAME;
    }

    public void setEXAM_SUBJECT_NAME(String EXAM_SUBJECT_NAME) {
        this.EXAM_SUBJECT_NAME = EXAM_SUBJECT_NAME;
    }

    public String getEXAMS_DATE() {
        return EXAMS_DATE;
    }

    public void setEXAMS_DATE(String EXAMS_DATE) {
        this.EXAMS_DATE = EXAMS_DATE;
    }

    public String getEXAMS_START_TIME() {
        return EXAMS_START_TIME;
    }

    public void setEXAMS_START_TIME(String EXAMS_START_TIME) {
        this.EXAMS_START_TIME = EXAMS_START_TIME;
    }

    public String getEXAMS_END_TIME() {
        return EXAMS_END_TIME;
    }

    public void setEXAMS_END_TIME(String EXAMS_END_TIME) {
        this.EXAMS_END_TIME = EXAMS_END_TIME;
    }

    public ModelExams() {
    }
}
