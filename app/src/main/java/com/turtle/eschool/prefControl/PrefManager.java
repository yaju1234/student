package com.turtle.eschool.prefControl;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "school_manage";
    private String checkUser_id = "Check_User_Id";
    private String checklogindata = "Login_data";
    private String Class_id = "class_id";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private String checkloginusername = "User_Name";
    private String checkloginpassword= "User_Pass";
    private String Image= "Image";


    public String getClass_id() {
        return pref.getString(Class_id, "");
    }

    public void setClass_id(String cls) {
        editor.putString(Class_id, cls);
        editor.commit();
    }

    public String getImage() {
        return pref.getString(Image, "");
    }

    public void setImage(String unn) {
        editor.putString(Image, unn);
        editor.commit();
    }

    public String getCheckloginusername() {
        return pref.getString(checkloginusername, "");
    }

    public void setCheckloginusername(String un) {
        editor.putString(checkloginusername, un);
        editor.commit();
    }

    public String getCheckloginpassword() {
        return pref.getString(checkloginpassword, "");
    }

    public void setCheckloginpassword(String pass) {
        editor.putString(checkloginpassword, pass);
        editor.commit();
    }

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getCheckUser_id() {
        return pref.getString(checkUser_id, "");
    }

    public String getChecklogindata() {
        return pref.getString(checklogindata, "");
    }

    public void setChecklogindata(String checklogidata) {
        editor.putString(checklogindata, checklogidata);
        editor.commit();
    }

    public void setCheckUser_id(String checkUserid) {
        editor.putString(checkUser_id, checkUserid);
        editor.commit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


}