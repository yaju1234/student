package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pratibha on 3/4/2017.
 */
public class ModelTimeTable implements Serializable {
    @SerializedName("TIME_TABLE_PERIOD_NO")
    @Expose
    private String TIME_TABLE_PERIOD_NO;

    @SerializedName("TIME_TABLE_PERIOD_START_TIME")
    @Expose
    private String TIME_TABLE_PERIOD_START_TIME;


    @SerializedName("TIME_TABLE_PERIOD_END_TIME")
    @Expose
    private String TIME_TABLE_PERIOD_END_TIME;


    @SerializedName("SUBJECTS_NAME")
    @Expose
    private String SUBJECTS_NAME;


    @SerializedName("TEACH_BY")
    @Expose
    private String TEACH_BY;

    @SerializedName("ROOM_NO")
    @Expose
    private String ROOM_NO;

    public String getTIME_TABLE_PERIOD_NO() {
        return TIME_TABLE_PERIOD_NO;
    }

    public void setTIME_TABLE_PERIOD_NO(String TIME_TABLE_PERIOD_NO) {
        this.TIME_TABLE_PERIOD_NO = TIME_TABLE_PERIOD_NO;
    }

    public String getTIME_TABLE_PERIOD_START_TIME() {
        return TIME_TABLE_PERIOD_START_TIME;
    }

    public void setTIME_TABLE_PERIOD_START_TIME(String TIME_TABLE_PERIOD_START_TIME) {
        this.TIME_TABLE_PERIOD_START_TIME = TIME_TABLE_PERIOD_START_TIME;
    }

    public String getTIME_TABLE_PERIOD_END_TIME() {
        return TIME_TABLE_PERIOD_END_TIME;
    }

    public void setTIME_TABLE_PERIOD_END_TIME(String TIME_TABLE_PERIOD_END_TIME) {
        this.TIME_TABLE_PERIOD_END_TIME = TIME_TABLE_PERIOD_END_TIME;
    }

    public String getSUBJECTS_NAME() {
        return SUBJECTS_NAME;
    }

    public void setSUBJECTS_NAME(String SUBJECTS_NAME) {
        this.SUBJECTS_NAME = SUBJECTS_NAME;
    }


    public String getTEACH_BY() {
        return TEACH_BY;
    }

    public void setTEACH_BY(String TEACH_BY) {
        this.TEACH_BY = TEACH_BY;
    }

    public String getROOM_NO() {
        return ROOM_NO;
    }

    public void setROOM_NO(String ROOM_NO) {
        this.ROOM_NO = ROOM_NO;
    }
}
