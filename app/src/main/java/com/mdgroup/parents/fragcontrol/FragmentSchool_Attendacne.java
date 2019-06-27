package com.mdgroup.parents.fragcontrol;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.mdgroup.parents.R;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ResponseAttendances;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Gaurav Mangal
 */
public class FragmentSchool_Attendacne extends Fragment implements View.OnClickListener {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    Date green_presentDate = null;
    PrefManager prefManager;
    TextView no_attendance;

    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();
        String str_p = "L";

        //getDrawableCustom_GrikInfotechAttendance(str_p, cal, 5);
/*
        cal.set(Calendar.DATE, 02);
        cal.set(Calendar.MONTH, 02);
        cal.set(Calendar.YEAR, 2017);
        green_presentDate = cal.getTime();
        ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.green_present));
        caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);*/


    }

    public FragmentSchool_Attendacne() {
        // Required empty public constructor
    }



    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getDrawableCustom_GrikInfotechAttendance(String attendance_grik, Calendar cal, int date, int month, int year) {

        if (attendance_grik.equalsIgnoreCase("P")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.green_present));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
        }
        if (attendance_grik.equalsIgnoreCase("A")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.absent));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
        }
        if (attendance_grik.equalsIgnoreCase("L")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable leave = new ColorDrawable(getResources().getColor(R.color.leavet));
            caldroidFragment.setBackgroundDrawableForDate(leave, green_presentDate);
        }
        if (attendance_grik.equalsIgnoreCase("H")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable leave_H = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
            caldroidFragment.setBackgroundDrawableForDate(leave_H, green_presentDate);
        }

        caldroidFragment.refreshView();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        caldroidFragment = new CaldroidFragment();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.school_st_attend, container, false);
        prefManager = new PrefManager(getActivity());
        no_attendance = (TextView) view.findViewById(R.id.no_attendance);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
            caldroidFragment.setArguments(args);

            /*cal.set(Calendar.DAY_OF_MONTH, 1); // 1st day of current month
            caldroidFragment.setMinDate(cal.getTime());

            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, maxDay);
            caldroidFragment.setMaxDate(cal.getTime()); //last day of current month*/
        }
        //setCustomResourceForDates();
        getAttendancefromserverMonthData("March", prefManager.getCheckUser_id());
        Log.d("iser_id",prefManager.getCheckUser_id());
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

            }
            @Override
            public void onChangeMonth(int month, int year) {
                // String text = "month: " + month + " year: " + year;
                String month_name = getMonthName(month);
                getAttendancefromserverMonthData(month_name, prefManager.getCheckUser_id());
                Log.d("iser_id",prefManager.getCheckUser_id());
            }
            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {
                /*if (caldroidFragment.getLeftArrowButton() != null) {

                }*/

            }
        };

        caldroidFragment.setCaldroidListener(listener);


        return view;
    }

    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "January";

            case 2:
                return "February";

            case 3:
                return "March";

            case 4:
                return "April";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "July";

            case 8:
                return "August";

            case 9:
                return "September";

            case 10:
                return "October";

            case 11:
                return "November";

            case 12:
                return "December";
        }
        return "";
    }
    @Override
    public void onClick(View view) {

    }
    public void getAttendancefromserverMonthData(String date_month, String st_id) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);
        System.out.println("Size data:date_month" + date_month);
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);

        Call<ResponseAttendances> call = apiService.getAttendanceData(st_id, date_month);
        call.enqueue(new Callback<ResponseAttendances>() {
            @Override
            public void onResponse(Call<ResponseAttendances> call, retrofit2.Response<ResponseAttendances> response) {
                try {
                    Thread.sleep(2000);
                    pDialog.dismiss();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (response.code() == 200) {
                    ResponseAttendances data = response.body();
                    if (data.getResult() != null) {
                        if (data.getResult().size() > 0) {
                            no_attendance.setVisibility(View.VISIBLE);
                            System.out.println("Size data:" + data.getResult().size());
                            for (int i = 0; i < data.getResult().size(); i++) {
                                //String at_id = data.getResult().get(i).getATTENDANCE_ID();
                                String at_type = data.getResult().get(i).getATTENDANCE_TYPE();
                                Calendar cal = Calendar.getInstance();
                                String at_date = data.getResult().get(i).getATTENDANCE_DATE();
                                Log.d("at_date",at_date);
                                String[] separated_at_date = at_date.split("-");
                                Log.d("separated_at_date", String.valueOf(separated_at_date));
                                String year = separated_at_date[0];
                                String month = separated_at_date[1];
                                String date_ = separated_at_date[2];
                                Log.d("year",year);
                                Log.d("date",date_);
                                /*char c=date_.charAt(0);
                                if(c=='0')
                                {
                                    date_=date_.replace("0","");
                                    date_=date_.trim();
                                }*/
                                TimeZone timezone = TimeZone.getDefault();
                                Calendar calendar = new GregorianCalendar(timezone);

                                switch (Integer.parseInt(month)) {
                                    case 1:
                                        calendar.set(Integer.parseInt(year), Calendar.JANUARY, Integer.parseInt(date_));
                                        break;
                                    case 2:
                                        calendar.set(Integer.parseInt(year), Calendar.FEBRUARY, Integer.parseInt(date_));
                                        break;
                                    case 3:
                                        calendar.set(Integer.parseInt(year), Calendar.MARCH, Integer.parseInt(date_));
                                        break;
                                    case 4:
                                        calendar.set(Integer.parseInt(year), Calendar.APRIL, Integer.parseInt(date_));
                                        break;
                                    case 5:
                                        calendar.set(Integer.parseInt(year), Calendar.MAY, Integer.parseInt(date_));
                                        break;
                                    case 6:
                                        calendar.set(Integer.parseInt(year), Calendar.JUNE, Integer.parseInt(date_));
                                        break;
                                    case 7:
                                        calendar.set(Integer.parseInt(year), Calendar.JULY, Integer.parseInt(date_));
                                        break;
                                    case 8:
                                        calendar.set(Integer.parseInt(year), Calendar.AUGUST, Integer.parseInt(date_));
                                        break;
                                    case 9:
                                        calendar.set(Integer.parseInt(year), Calendar.SEPTEMBER, Integer.parseInt(date_));
                                        break;
                                    case 10:
                                        calendar.set(Integer.parseInt(year), Calendar.OCTOBER, Integer.parseInt(date_));
                                        break;
                                    case 11:
                                        calendar.set(Integer.parseInt(year), Calendar.NOVEMBER, Integer.parseInt(date_));
                                        break;
                                    case 12:
                                        calendar.set(Integer.parseInt(year), Calendar.DECEMBER, Integer.parseInt(date_));
                                        break;
                                }

                                int reslut = calendar.get(Calendar.DAY_OF_WEEK);
                                System.out.println("CAll DAta calendar" + reslut);
                                switch (reslut) {
                                    case Calendar.SUNDAY:
                                        at_type = "H";
                                        break;
                                }
                                String datadd="CAll DAta" + at_type + Integer.parseInt(date_) + Integer.parseInt(month) + Integer.parseInt(year);
                                System.out.println(datadd);
                                getDrawableCustom_GrikInfotechAttendance(at_type, cal, Integer.parseInt(date_), Integer.parseInt(month), Integer.parseInt(year));
                            }
                        }
                        else
                        {
                            no_attendance.setVisibility(View.VISIBLE);
                            //no_attendance.setText(data.getMessage());
                        }
                    } else {
                        UtilityFunction.showCenteredToast(getActivity(), data.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAttendances> call, Throwable t) {
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Attendance");

    }


    @Override
    public boolean getAllowReturnTransitionOverlap() {
        return super.getAllowReturnTransitionOverlap();
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        super.setAllowReturnTransitionOverlap(allow);
    }

    @Override
    public boolean getAllowEnterTransitionOverlap() {
        return super.getAllowEnterTransitionOverlap();
    }

    @Override
    public void setAllowEnterTransitionOverlap(boolean allow) {
        super.setAllowEnterTransitionOverlap(allow);
    }

    @Override
    public Object getSharedElementReturnTransition() {
        return super.getSharedElementReturnTransition();
    }

    @Override
    public void setSharedElementReturnTransition(Object transition) {
        super.setSharedElementReturnTransition(transition);
    }

    @Override
    public Object getSharedElementEnterTransition() {
        return super.getSharedElementEnterTransition();
    }

    @Override
    public void setSharedElementEnterTransition(Object transition) {
        super.setSharedElementEnterTransition(transition);
    }

    @Override
    public Object getReenterTransition() {
        return super.getReenterTransition();
    }
}
