package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ForgotPasswordModal {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("STUDENT_ADMISSION_NO")
    @Expose
    private String STUDENT_ADMISSION_NO;


    public String getSTUDENT_ADMISSION_NO() {
        return STUDENT_ADMISSION_NO;
    }

    public void setSTUDENT_ADMISSION_NO(String STUDENT_ADMISSION_NO) {
        this.STUDENT_ADMISSION_NO = STUDENT_ADMISSION_NO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
