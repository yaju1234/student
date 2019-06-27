package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ResultExams implements Serializable {

    @SerializedName("EXAMS_ID")
    @Expose
    private String EXAMS_ID;

    @SerializedName("EXAMS_NAME")
    @Expose
    private String EXAMS_NAME;


    @SerializedName("RESULTS_STATUS")
    @Expose
    private String RESULTS_STATUS;


    @SerializedName("EXAMS_DATE")
    @Expose
    private String EXAMS_DATE;

    public String getRESULTS_STATUS() {
        return RESULTS_STATUS;
    }

    public void setRESULTS_STATUS(String RESULTS_STATUS) {
        this.RESULTS_STATUS = RESULTS_STATUS;
    }

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


    public String getEXAMS_DATE() {
        return EXAMS_DATE;
    }

    public void setEXAMS_DATE(String EXAMS_DATE) {
        this.EXAMS_DATE = EXAMS_DATE;
    }


    public ResultExams() {
    }
}
