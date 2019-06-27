package com.mdgroup.parents.interfaces.custom;


import com.mdgroup.parents.schoolmodel.ForgotPasswordModal;
import com.mdgroup.parents.schoolmodel.ModelUser;
import com.mdgroup.parents.schoolmodel.NewsResponse;
import com.mdgroup.parents.schoolmodel.ResHolidays;
import com.mdgroup.parents.schoolmodel.ResResults;
import com.mdgroup.parents.schoolmodel.ResSingle;
import com.mdgroup.parents.schoolmodel.ResponseAllChat;
import com.mdgroup.parents.schoolmodel.ResponseAttendances;
import com.mdgroup.parents.schoolmodel.ResponseEvent;
import com.mdgroup.parents.schoolmodel.ResponseExams;
import com.mdgroup.parents.schoolmodel.ResponseExamsMain;
import com.mdgroup.parents.schoolmodel.ResponseHomework;
import com.mdgroup.parents.schoolmodel.ResponseTeacher;
import com.mdgroup.parents.schoolmodel.ResponseTecherChat;
import com.mdgroup.parents.schoolmodel.ResponseTimetable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Gaurav Mangal
 */
public interface ApiIHandler {

    @FormUrlEncoded
    @POST("login.php")
    Call<ModelUser> getLoginAuthenticationStudent(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("forgot-password-otp.php")
    Call<ForgotPasswordModal> getFORGOTSTUDENT(@Field("STUDENT_FATHER_MOBILE_NO") String STUDENT_FATHER_MOBILE_NO);

    @FormUrlEncoded
    @POST("forgot-password-otp-match.php")
    Call<ForgotPasswordModal> getOTPMATCHAPI(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO, @Field("OTP") String OTP);

    @FormUrlEncoded
    @POST("forgot-password-reset.php")
    Call<ForgotPasswordModal> get_forgotpassword_reset(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO, @Field("password") String password, @Field("cpassword") String cpassword);

    @FormUrlEncoded
    @POST("change-password.php")
    Call<ForgotPasswordModal> get_change_password(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO, @Field("oldpassword") String oldpassword, @Field("password") String password, @Field("cpassword") String cpassword);

    @FormUrlEncoded
    @POST("update-profile.php")
    Call<ForgotPasswordModal> get_updates_profiledata(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO,
                                                      @Field("STUDENT_FATHER_MOBILE_NO") String STUDENT_FATHER_MOBILE_NO,
                                                      @Field("STUDENT_FATHER_EMAIL") String STUDENT_FATHER_EMAIL,
                                                      @Field("STUDENT_FATHER_ADDRESS1") String STUDENT_FATHER_ADDRESS1,
                                                      @Field("STUDENT_FATHER_ADDRESS2") String STUDENT_FATHER_ADDRESS2,
                                                      @Field("STUDENT_FIRST_NAME") String STUDENT_FIRST_NAME,
                                                      @Field("action") String action
    );


    @POST("getnewslist.php")
    Call<NewsResponse> getNewsDataFROMSERVER();

    @POST("geteventslist.php")
    Call<ResponseEvent> FetchEventData();

    @FormUrlEncoded
    @POST("getholidays.php")
    Call<ResHolidays> HolidaysData(@Field("HOLIDAYS_MONTH") String HOLIDAYS_MONTH);  //Janauary


    @FormUrlEncoded
    @POST("getexams.php")
    Call<ResponseExams> ExamsShownShoolsData(@Field("EXAMS_CLASS_NO") String EXAMS_CLASS_NO,@Field("EXAMS_SECTION_NO") String EXAMS_SECTION_NO,@Field("EXAMS_CATEGORY") String EXAMS_CATEGORY,@Field("EXAMS_TYPE") String EXAMS_TYPE);  //1

    @POST("getexamtype.php")
    Call<ResponseExamsMain> getMainExamType();

    @FormUrlEncoded
    @POST("addleaves.php")
    Call<ForgotPasswordModal> addleavesData(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO, @Field("LEAVES_RECEIVER") String LEAVES_RECEIVER,
                                            @Field("LEAVES_SUBJECT") String LEAVES_SUBJECT, @Field("LEAVES_FROMDATE") String LEAVES_FROMDATE ,
                                            @Field("LEAVES_TODATE") String LEAVES_TODATE, @Field("LEAVES_BODY") String LEAVES_BODY, @Field("LEAVES_SINGLEDAY") String LEAVES_SINGLEDAY, @Field("LEAVES_CURRENT_DATE") String LEAVES_CURRENT_DATE);




    @FormUrlEncoded
    @POST("addcomplains.php")
    Call<ResSingle> addComplainData(@Field("COMPLAINS_STUDENT_ID") String COMPLAINS_STUDENT_ID, @Field("COMPLAINS_TITLE") String COMPLAINS_TITLE,
                                    @Field("COMPLAINS_DESCRIPTION") String COMPLAINS_DESCRIPTION);


    @FormUrlEncoded
    @POST("getattendance.php")
    Call<ResponseAttendances> getAttendanceData(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO, @Field("ATTENDANCE_MONTH") String ATTENDANCE_MONTH);


    @FormUrlEncoded
    @POST("getteachers.php")
    Call<ResponseTecherChat> chatTeacherListdata(@Field("TEACHERS_CLASS_ID") String TEACHERS_CLASS_ID, @Field("TEACHERS_SECTION_ID") String TEACHERS_SECTION_ID);  //Janauary

    @FormUrlEncoded
    @POST("sendchat.php")
    Call<ForgotPasswordModal> sendmessagedataobject(@Field("CHATS_MESSAGE") String CHATS_MESSAGE, @Field("CHATS_STUDENT_ID") String CHATS_STUDENT_ID,@Field("CHATS_TEACHER_ID") String CHATS_TEACHER_ID,@Field("CHATS_SIDE") String CHATS_SIDE);

    @FormUrlEncoded
    @POST("getresultslist.php")
    Call<ResResults> REsultData(@Field("STUDENT_ADMISSION_NO") String STUDENT_ADMISSION_NO,@Field("EXAMS_TYPE") String EXAMS_TYPE);

    @FormUrlEncoded
    @POST("getchat.php")
        Call<ResponseAllChat> getChatdata(@Field("CHATS_STUDENT_ID") String CHATS_STUDENT_ID, @Field("CHATS_TEACHER_ID") String CHATS_TEACHER_ID);

    @FormUrlEncoded
    @POST("gethomework.php")
    Call<ResponseHomework>HomeworkData(@Field("HOMEWORK_CLASS_ID") String HOMEWORK_CLASS_ID,
                                       @Field("HOMEWORK_SECTION_ID") String HOMEWORK_SECTION_ID,
                                       @Field("HOMEWORK_DATE") String HOMEWORK_DATE);
    @FormUrlEncoded
    @POST("gettimetable.php")
    Call<ResponseTimetable>TimetableData(@Field("TIME_TABLE_CLASS_ID") String TIME_TABLE_CLASS_ID,
                                         @Field("TIME_TABLE_SECTION_ID") String TIME_TABLE_SECTION_ID,
                                         @Field("TIME_TABLE_DAY") String TIME_TABLE_DAY);
    @FormUrlEncoded
    @POST("getteachers.php")
    Call<ResponseTeacher>TeacherData(@Field("TEACHERS_CLASS_ID") String TEACHERS_CLASS_ID,
                                     @Field("TEACHERS_SECTION_ID") String TEACHERS_SECTION_ID);



}
