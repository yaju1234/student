package com.turtle.eschool.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ModelHolidays implements Serializable {

    @SerializedName("HOLIDAYS_ID")
    @Expose
    private String HOLIDAYS_ID;

    @SerializedName("HOLIDAYS_NAME")
    @Expose
    private String HOLIDAYS_NAME;


    @SerializedName("HOLIDAYS_DESCRIPTION")
    @Expose
    private String HOLIDAYS_DESCRIPTION;


    @SerializedName("HOLIDAYS_MONTH")
    @Expose
    private String HOLIDAYS_MONTH;


    @SerializedName("HOLIDAYS_DATE")
    @Expose
    private String HOLIDAYS_DATE;

    @SerializedName("HOLIDAYS_DISPLAY")
    @Expose
    private String HOLIDAYS_DISPLAY;


    public String getHOLIDAYS_ID() {
        return HOLIDAYS_ID;
    }

    public void setHOLIDAYS_ID(String HOLIDAYS_ID) {
        this.HOLIDAYS_ID = HOLIDAYS_ID;
    }

    public String getHOLIDAYS_NAME() {
        return HOLIDAYS_NAME;
    }

    public void setHOLIDAYS_NAME(String HOLIDAYS_NAME) {
        this.HOLIDAYS_NAME = HOLIDAYS_NAME;
    }

    public String getHOLIDAYS_DESCRIPTION() {
        return HOLIDAYS_DESCRIPTION;
    }

    public void setHOLIDAYS_DESCRIPTION(String HOLIDAYS_DESCRIPTION) {
        this.HOLIDAYS_DESCRIPTION = HOLIDAYS_DESCRIPTION;
    }

    public String getHOLIDAYS_MONTH() {
        return HOLIDAYS_MONTH;
    }

    public void setHOLIDAYS_MONTH(String HOLIDAYS_MONTH) {
        this.HOLIDAYS_MONTH = HOLIDAYS_MONTH;
    }

    public String getHOLIDAYS_DATE() {
        return HOLIDAYS_DATE;
    }

    public void setHOLIDAYS_DATE(String HOLIDAYS_DATE) {
        this.HOLIDAYS_DATE = HOLIDAYS_DATE;
    }

    public String getHOLIDAYS_DISPLAY() {
        return HOLIDAYS_DISPLAY;
    }

    public void setHOLIDAYS_DISPLAY(String HOLIDAYS_DISPLAY) {
        this.HOLIDAYS_DISPLAY = HOLIDAYS_DISPLAY;
    }

    public ModelHolidays() {
    }
}
