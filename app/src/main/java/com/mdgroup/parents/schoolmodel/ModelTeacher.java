package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pratibha on 3/2/2017.
 */

public class ModelTeacher implements Serializable {
    @SerializedName("TEACHERS_ID")
    @Expose
    private String TEACHERS_ID;

    @SerializedName("TEACHERS_NAME")
    @Expose
    private String TEACHERS_NAME;

    @SerializedName("TEACHERS_EMAIL")
    @Expose
    private String TEACHERS_EMAIL;

    @SerializedName("TEACHERS_FATHER_NAME")
    @Expose
    private String TEACHERS_FATHER_NAME;

    @SerializedName("TEACHERS_QUALIFICATION")
    @Expose
    private String TEACHERS_QUALIFICATION;

    @SerializedName("TEACHERS_EXPERIENCE")
    @Expose
    private String TEACHERS_EXPERIENCE;

    @SerializedName("TEACHERS_CLASS_ID")
    @Expose
    private String TEACHERS_CLASS_ID;

    @SerializedName("TEACHERS_SECTION_ID")
    @Expose
    private String TEACHERS_SECTION_ID;

    @SerializedName("CLASSES_NAME")
    @Expose
    private String CLASSES_NAME;
    @SerializedName("SECTIONS_NAME")
    @Expose
    private String SECTIONS_NAME;

    @SerializedName("SUBJECTS_NAME")
    @Expose
    private String SUBJECTS_NAME;

    @SerializedName("TEACHERS_FILE_PATH")
    @Expose
    private String TEACHERS_FILE_PATH;

    public String getTEACHERS_EMAIL() {
        return TEACHERS_EMAIL;
    }

    public void setTEACHERS_EMAIL(String TEACHERS_EMAIL) {
        this.TEACHERS_EMAIL = TEACHERS_EMAIL;
    }

    public String getTEACHERS_ID() {
        return TEACHERS_ID;
    }

    public void setTEACHERS_ID(String EXAMS_ID) {
        this.TEACHERS_ID = TEACHERS_ID;
    }

    public String getTEACHERS_NAME() {
        return TEACHERS_NAME;
    }

    public void setTEACHERS_NAME(String TEACHERS_NAME) {
        this.TEACHERS_NAME = TEACHERS_NAME;
    }
    public String getTEACHERS_FATHER_NAME() {
        return TEACHERS_FATHER_NAME;
    }

    public void setTEACHERS_FATHER_NAME(String TEACHERS_FATHER_NAME) {
        this.TEACHERS_FATHER_NAME = TEACHERS_FATHER_NAME;
    }
    public String getTEACHERS_QUALIFICATION() {
        return TEACHERS_QUALIFICATION;
    }

    public void setTEACHERS_QUALIFICATION(String TEACHERS_QUALIFICATION) {
        this.TEACHERS_QUALIFICATION = TEACHERS_QUALIFICATION;
    }
    public String getTEACHERS_EXPERIENCE() {
        return TEACHERS_EXPERIENCE;
    }

    public void setTEACHERS_EXPERIENCE(String TEACHERS_EXPERIENCE) {
        this.TEACHERS_EXPERIENCE = TEACHERS_EXPERIENCE;
    }
    public String getTEACHERS_CLASS_ID() {
        return TEACHERS_CLASS_ID;
    }

    public void setTEACHERS_CLASS_ID(String TEACHERS_CLASS_ID) {
        this.TEACHERS_CLASS_ID = TEACHERS_CLASS_ID;
    }
    public String getTEACHERS_SECTION_ID() {
        return TEACHERS_SECTION_ID;
    }

    public void setTEACHERS_SECTION_ID(String EXAMS_ID) {
        this.TEACHERS_SECTION_ID = TEACHERS_SECTION_ID;
    }
    public String getCLASSES_NAME() {
        return CLASSES_NAME;
    }

    public void setCLASSES_NAME(String EXAMS_ID) {
        this.CLASSES_NAME = CLASSES_NAME;
    }
    public String getSECTIONS_NAME() {
        return SECTIONS_NAME;
    }

    public void setSECTIONS_NAME(String SECTIONS_NAME) {
        this.SECTIONS_NAME = SECTIONS_NAME;
    }
    public String getSUBJECTS_NAME() {
        return SUBJECTS_NAME;
    }

    public void setSUBJECTS_NAME(String SUBJECTS_NAME) {
        this.SUBJECTS_NAME = SUBJECTS_NAME;
    }
    public String getTEACHERS_FILE_PATH() {
        return TEACHERS_FILE_PATH;
    }

    public void setTEACHERS_FILE_PATH(String SUBJECTS_NAME) {
        this.TEACHERS_FILE_PATH = SUBJECTS_NAME;
    }
}
