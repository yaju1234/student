package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ModelUser {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("STUDENT_ADMISSION_NO")
    @Expose
    private String STUDENT_ADMISSION_NO;

    @SerializedName("USN Number")
    @Expose
    private String USN_Number;

    @SerializedName("STUDENT_FULL_NAME")
    @Expose
    private String STUDENT_FULL_NAME;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobileno")
    @Expose
    private String mobileno;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("CLASSES_NAME")
    @Expose
    private String CLASSES_NAME;

    @SerializedName("SECTIONS_NAME")
    @Expose
    private String SECTIONS_NAME;

    @SerializedName("STUDENT_FATHER_EMAIL")
    @Expose
    private String STUDENT_FATHER_EMAIL;

    @SerializedName("STUDENT_FATHER_MOBILE_NO")
    @Expose
    private String STUDENT_FATHER_MOBILE_NO;


    @SerializedName("STUDENT_FATHER_ADDRESS1")
    @Expose
    private String STUDENT_FATHER_ADDRESS1;

    @SerializedName("STUDENT_FATHER_ADDRESS2")
    @Expose
    private String STUDENT_FATHER_ADDRESS2;

    @SerializedName("IMAGE")
    @Expose
    private String IMAGE;

    @SerializedName("class_id")
    @Expose
    private String class_id;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }


    public String getSTUDENT_FATHER_EMAIL() {
        return STUDENT_FATHER_EMAIL;
    }

    public void setSTUDENT_FATHER_EMAIL(String STUDENT_FATHER_EMAIL) {
        this.STUDENT_FATHER_EMAIL = STUDENT_FATHER_EMAIL;
    }

    public String getSTUDENT_FATHER_MOBILE_NO() {
        return STUDENT_FATHER_MOBILE_NO;
    }

    public void setSTUDENT_FATHER_MOBILE_NO(String STUDENT_FATHER_MOBILE_NO) {
        this.STUDENT_FATHER_MOBILE_NO = STUDENT_FATHER_MOBILE_NO;
    }

    public String getCLASSES_NAME() {
        return CLASSES_NAME;
    }

    public void setCLASSES_NAME(String CLASSES_NAME) {
        this.CLASSES_NAME = CLASSES_NAME;
    }

    public String getSECTIONS_NAME() {
        return SECTIONS_NAME;
    }

    public void setSECTIONS_NAME(String SECTIONS_NAME) {
        this.SECTIONS_NAME = SECTIONS_NAME;
    }

    public String getSTUDENT_FATHER_ADDRESS1() {
        return STUDENT_FATHER_ADDRESS1;
    }

    public void setSTUDENT_FATHER_ADDRESS1(String STUDENT_FATHER_ADDRESS1) {
        this.STUDENT_FATHER_ADDRESS1 = STUDENT_FATHER_ADDRESS1;
    }

    public String getSTUDENT_FATHER_ADDRESS2() {
        return STUDENT_FATHER_ADDRESS2;
    }

    public void setSTUDENT_FATHER_ADDRESS2(String STUDENT_FATHER_ADDRESS2) {
        this.STUDENT_FATHER_ADDRESS2 = STUDENT_FATHER_ADDRESS2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSTUDENT_ADMISSION_NO() {
        return STUDENT_ADMISSION_NO;
    }

    public void setSTUDENT_ADMISSION_NO(String STUDENT_ADMISSION_NO) {
        this.STUDENT_ADMISSION_NO = STUDENT_ADMISSION_NO;
    }

    public String getUSN_Number() {
        return USN_Number;
    }

    public void setUSN_Number(String USN_Number) {
        this.USN_Number = USN_Number;
    }

    public String getSTUDENT_FULL_NAME() {
        return STUDENT_FULL_NAME;
    }

    public void setSTUDENT_FULL_NAME(String STUDENT_FULL_NAME) {
        this.STUDENT_FULL_NAME = STUDENT_FULL_NAME;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ModelUser() {
    }
}
