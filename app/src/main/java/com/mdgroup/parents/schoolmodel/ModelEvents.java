package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ModelEvents implements Serializable {

    @SerializedName("EVENTS_ID")
    @Expose
    private String EVENTS_ID;

    @SerializedName("EVENTS_NAME")
    @Expose
    private String EVENTS_NAME;


    @SerializedName("EVENTS_DATE")
    @Expose
    private String EVENTS_DATE;


    @SerializedName("EVENTS_TIME")
    @Expose
    private String EVENTS_TIME;


    @SerializedName("EVENTS_LOCATION")
    @Expose
    private String EVENTS_LOCATION;

    @SerializedName("EVENTS_MAIN_IMG")
    @Expose
    private String EVENTS_MAIN_IMG;


    @SerializedName("EVENTS_SUB_IMG1")
    @Expose
    private String EVENTS_SUB_IMG1;

    @SerializedName("EVENTS_SUB_IMG2")
    @Expose
    private String EVENTS_SUB_IMG2;
    @SerializedName("EVENTS_SUB_IMG3")
    @Expose
    private String EVENTS_SUB_IMG3;


    public String getEVENTS_ID() {
        return EVENTS_ID;
    }

    public void setEVENTS_ID(String EVENTS_ID) {
        this.EVENTS_ID = EVENTS_ID;
    }

    public String getEVENTS_NAME() {
        return EVENTS_NAME;
    }

    public void setEVENTS_NAME(String EVENTS_NAME) {
        this.EVENTS_NAME = EVENTS_NAME;
    }

    public String getEVENTS_DATE() {
        return EVENTS_DATE;
    }

    public void setEVENTS_DATE(String EVENTS_DATE) {
        this.EVENTS_DATE = EVENTS_DATE;
    }

    public String getEVENTS_TIME() {
        return EVENTS_TIME;
    }

    public void setEVENTS_TIME(String EVENTS_TIME) {
        this.EVENTS_TIME = EVENTS_TIME;
    }

    public String getEVENTS_LOCATION() {
        return EVENTS_LOCATION;
    }

    public void setEVENTS_LOCATION(String EVENTS_LOCATION) {
        this.EVENTS_LOCATION = EVENTS_LOCATION;
    }

    public String getEVENTS_MAIN_IMG() {
        return EVENTS_MAIN_IMG;
    }

    public void setEVENTS_MAIN_IMG(String EVENTS_MAIN_IMG) {
        this.EVENTS_MAIN_IMG = EVENTS_MAIN_IMG;
    }

    public String getEVENTS_SUB_IMG1() {
        return EVENTS_SUB_IMG1;
    }

    public void setEVENTS_SUB_IMG1(String EVENTS_SUB_IMG1) {
        this.EVENTS_SUB_IMG1 = EVENTS_SUB_IMG1;
    }

    public String getEVENTS_SUB_IMG2() {
        return EVENTS_SUB_IMG2;
    }

    public void setEVENTS_SUB_IMG2(String EVENTS_SUB_IMG2) {
        this.EVENTS_SUB_IMG2 = EVENTS_SUB_IMG2;
    }

    public String getEVENTS_SUB_IMG3() {
        return EVENTS_SUB_IMG3;
    }

    public void setEVENTS_SUB_IMG3(String EVENTS_SUB_IMG3) {
        this.EVENTS_SUB_IMG3 = EVENTS_SUB_IMG3;
    }

    public ModelEvents() {
    }
}
