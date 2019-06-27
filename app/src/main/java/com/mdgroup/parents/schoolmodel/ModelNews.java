package com.mdgroup.parents.schoolmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ModelNews implements Serializable {

    @SerializedName("NEWS_ID")
    @Expose
    private String NEWS_ID;

    @SerializedName("NEWS_HEADING")
    @Expose
    private String NEWS_HEADING;


    @SerializedName("NEWS_DESCRIPTION")
    @Expose
    private String NEWS_DESCRIPTION;


    @SerializedName("NEWS_UPLOAD_DATE")
    @Expose
    private String NEWS_UPLOAD_DATE;


    @SerializedName("NEWS_MAIN_IMG")
    @Expose
    private String NEWS_MAIN_IMG;

    @SerializedName("NEWS_SUB_IMG1")
    @Expose
    private String NEWS_SUB_IMG1;


    @SerializedName("NEWS_SUB_IMG2")
    @Expose
    private String NEWS_SUB_IMG2;

    @SerializedName("NEWS_SUB_IMG3")
    @Expose
    private String NEWS_SUB_IMG3;


    public String getNEWS_ID() {
        return NEWS_ID;
    }

    public void setNEWS_ID(String NEWS_ID) {
        this.NEWS_ID = NEWS_ID;
    }

    public String getNEWS_HEADING() {
        return NEWS_HEADING;
    }

    public void setNEWS_HEADING(String NEWS_HEADING) {
        this.NEWS_HEADING = NEWS_HEADING;
    }

    public String getNEWS_DESCRIPTION() {
        return NEWS_DESCRIPTION;
    }

    public void setNEWS_DESCRIPTION(String NEWS_DESCRIPTION) {
        this.NEWS_DESCRIPTION = NEWS_DESCRIPTION;
    }

    public String getNEWS_UPLOAD_DATE() {
        return NEWS_UPLOAD_DATE;
    }

    public void setNEWS_UPLOAD_DATE(String NEWS_UPLOAD_DATE) {
        this.NEWS_UPLOAD_DATE = NEWS_UPLOAD_DATE;
    }

    public String getNEWS_MAIN_IMG() {
        return NEWS_MAIN_IMG;
    }

    public void setNEWS_MAIN_IMG(String NEWS_MAIN_IMG) {
        this.NEWS_MAIN_IMG = NEWS_MAIN_IMG;
    }

    public String getNEWS_SUB_IMG1() {
        return NEWS_SUB_IMG1;
    }

    public void setNEWS_SUB_IMG1(String NEWS_SUB_IMG1) {
        this.NEWS_SUB_IMG1 = NEWS_SUB_IMG1;
    }

    public String getNEWS_SUB_IMG2() {
        return NEWS_SUB_IMG2;
    }

    public void setNEWS_SUB_IMG2(String NEWS_SUB_IMG2) {
        this.NEWS_SUB_IMG2 = NEWS_SUB_IMG2;
    }

    public String getNEWS_SUB_IMG3() {
        return NEWS_SUB_IMG3;
    }

    public void setNEWS_SUB_IMG3(String NEWS_SUB_IMG3) {
        this.NEWS_SUB_IMG3 = NEWS_SUB_IMG3;
    }

    public ModelNews() {
    }
}
