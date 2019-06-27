package com.mdgroup.parents.fragcontrol;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;
import com.roomorama.caldroid.CaldroidFragment;
import com.mdgroup.parents.R;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ResponseAttendances;
import com.mdgroup.parents.schoolmodel.SchoolAttendacnce;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Gaurav Mangal
 */
public class ManageAttendance extends Fragment implements View.OnClickListener {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    Date green_presentDate = null;
    PrefManager prefManager;
    TextView no_attendance;
  ArrayList<SchoolAttendacnce> arr_scholl;


    CustomCalendarView calendarView;
    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();
        String str_p = "L";

        //getDrawableCustom_GrikInfotechAttendance(str_p, cal, 5);
    }

    public ManageAttendance() {
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



    @Override
    public void onCreate(Bundle savedInstanceState) {
        caldroidFragment = new CaldroidFragment();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.school_st_attend1, container, false);
        prefManager = new PrefManager(getActivity());
        no_attendance = (TextView) view.findViewById(R.id.no_attendance);
        arr_scholl=new ArrayList<>();
       // String month_name = getMonthName(month);
        //
        //Initialize CustomCalendarView from layout
        calendarView = (CustomCalendarView)view.findViewById(R.id.calendar_view);
        getAttendancefromserverMonthData("March", prefManager.getCheckUser_id());
        //Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
                String month_name = getMonthName(Integer.parseInt(df.format(date)));
                getAttendancefromserverMonthData(month_name, prefManager.getCheckUser_id());
            }
        });

        //Setting custom font
       /* final Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Arch_Rival_Bold.ttf");
        if (null != typeface) {
            calendarView.setCustomTypeface(typeface);

        }*/

        //adding calendar day decorators
        List<DayDecorator> decorators = new ArrayList<>();
        decorators.add(new DisabledColorDecorator());
        calendarView.setDecorators(decorators);

        calendarView.refreshCalendar(currentCalendar);
        return view;
    }
    class DisabledColorDecorator implements DayDecorator {
        @Override
        public void decorate(DayView dayView) {
           /* if (CalendarUtils.isPastDay(dayView.getDate())) {

            }*/
           // int color = Color.parseColor("#a9afb9");
            //dayView.setBackgroundColor(color);

            if(arr_scholl.size()>0 && arr_scholl !=null)
            {
                for (int i = 0; i < arr_scholl.size(); i++) {
                    //String at_id = data.getResult().get(i).getATTENDANCE_ID();
                    String at_type = arr_scholl.get(i).getATTENDANCE_TYPE();
                    Calendar cal = Calendar.getInstance();
                    String at_date = arr_scholl.get(i).getATTENDANCE_DATE();
                    String[] separated_at_date = at_date.split("-");
                    String year = separated_at_date[0];
                    String month = separated_at_date[1];
                    String date_ = separated_at_date[2];
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
                    getDrawableCustom_GrikInfotechAttendance(dayView,at_type, cal, Integer.parseInt(date_), Integer.parseInt(month), Integer.parseInt(year));
                }
            }





        }
    }
    public void getDrawableCustom_GrikInfotechAttendance(DayView dayView,String attendance_grik, Calendar cal, int date, int month, int year) {

        if (attendance_grik.equalsIgnoreCase("P")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.green_present));
            // caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
            int color = Color.parseColor("#6aca30");
            dayView.setBackgroundColor(color);


        }
        if (attendance_grik.equalsIgnoreCase("A")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.absent));
            //  caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
            int color = Color.parseColor("#E2595C");
            dayView.setBackgroundColor(color);
        }
        if (attendance_grik.equalsIgnoreCase("L")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable leave = new ColorDrawable(getResources().getColor(R.color.leavet));
            //caldroidFragment.setBackgroundDrawableForDate(leave, green_presentDate);
            int color = Color.parseColor("#FBCC33");
            dayView.setBackgroundColor(color);
        }
        if (attendance_grik.equalsIgnoreCase("H")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable leave_H = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
            //caldroidFragment.setBackgroundDrawableForDate(leave_H, green_presentDate);
            int color = Color.parseColor("#1a458c");
            dayView.setBackgroundColor(color);
        }
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
                            arr_scholl=data.getResult();
                            no_attendance.setVisibility(View.VISIBLE);
                            System.out.println("Size data:" + data.getResult().size());

                        }
                        else
                        {
                            arr_scholl=data.getResult();
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
