package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by Gaurav on 14/02/2017.
 */


public class SchoolAttendacnce implements Serializable {

    @SerializedName("ATTENDANCE_ID")
    @Expose
    private String ATTENDANCE_ID;

    @SerializedName("ATTENDANCE_TYPE")
    @Expose
    private String ATTENDANCE_TYPE;


    @SerializedName("ATTENDANCE_DATE")
    @Expose
    private String ATTENDANCE_DATE;


    @SerializedName("STUDENT_ADMISSION_NO")
    @Expose
    private String STUDENT_ADMISSION_NO;


    @SerializedName("ATTENDANCE_MONTH")
    @Expose
    private String ATTENDANCE_MONTH;


    public String getATTENDANCE_ID() {
        return ATTENDANCE_ID;
    }

    public void setATTENDANCE_ID(String ATTENDANCE_ID) {
        this.ATTENDANCE_ID = ATTENDANCE_ID;
    }

    public String getATTENDANCE_TYPE() {
        return ATTENDANCE_TYPE;
    }

    public void setATTENDANCE_TYPE(String ATTENDANCE_TYPE) {
        this.ATTENDANCE_TYPE = ATTENDANCE_TYPE;
    }

    public String getATTENDANCE_DATE() {
        return ATTENDANCE_DATE;
    }

    public void setATTENDANCE_DATE(String ATTENDANCE_DATE) {
        this.ATTENDANCE_DATE = ATTENDANCE_DATE;
    }

    public String getSTUDENT_ADMISSION_NO() {
        return STUDENT_ADMISSION_NO;
    }

    public void setSTUDENT_ADMISSION_NO(String STUDENT_ADMISSION_NO) {
        this.STUDENT_ADMISSION_NO = STUDENT_ADMISSION_NO;
    }

    public String getATTENDANCE_MONTH() {
        return ATTENDANCE_MONTH;
    }

    public void setATTENDANCE_MONTH(String ATTENDANCE_MONTH) {
        this.ATTENDANCE_MONTH = ATTENDANCE_MONTH;
    }

    public SchoolAttendacnce() {
    }
}
