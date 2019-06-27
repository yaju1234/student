package com.mdgroup.parents.fragcontrol;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.mdgroup.parents.DateDisplayUtils;
import com.mdgroup.parents.PickerDialog;
import com.mdgroup.parents.PickerDialogFragment;
import com.mdgroup.parents.R;
import com.mdgroup.parents.adptercstm.adpter.chat.AdapterHolidays;
import com.mdgroup.parents.adptercstm.adpter.chat.AdapterHomeworkSubject;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelHolidays;
import com.mdgroup.parents.schoolmodel.ModelHomework;
import com.mdgroup.parents.schoolmodel.ModelUser;
import com.mdgroup.parents.schoolmodel.ResHolidays;
import com.mdgroup.parents.schoolmodel.ResponseHomework;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
/**
 * @Gaurav Mangal
 */

public class HomeFragmentContoller extends Fragment implements PickerDialog.OnDateSetListener, View.OnClickListener {
    DatePickerDialog datePickerDialog;
    Calendar c = Calendar.getInstance();
    RecyclerView subject_list;
    TextView homework_date,homework_st_name,homework_st_class;
    AdapterHomeworkSubject adapterHomeworkSubject;
    private List<ModelHomework> albumList;
    ModelUser modelstudent;
    String class_id="1",section_id="1",homework_date1=" 2017-03-03";
    //String class_id="1",section_id="1",homework_date1=" 2017-03-03";
    TextView homeworkdata_not_availabe,homework_date2;
    Date dt;
    ImageView profile_image_url;
    StringBuilder stringbuilder;
    Activity activity;
    String date_self;
    Button increment,decrement;
    String str1,dt1;
    SimpleDateFormat dateFormat;
    PrefManager prefManager;
    private RecyclerView rv_holidaysdatarv;
    private AdapterHolidays adapterHolidays;
    private List<ModelHolidays> albumListholiday;
    RecyclerView.LayoutManager mLayoutManager;
    TextView holidaydata_not_availabe;
    Button btn_holidaydateselection;

    public HomeFragmentContoller() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Dashboard");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash, container, false);;

        homework_date=(TextView)view.findViewById(R.id.homework_date);
        homework_date2=(TextView)view.findViewById(R.id.homework_date1);
        homework_st_name=(TextView)view.findViewById(R.id.homework_st_name);
        homework_st_class=(TextView)view.findViewById(R.id.homework_st_class);
        profile_image_url=(ImageView)view.findViewById(R.id.profile_image_url);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Are you sure you want to exit?");
                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    getActivity().finishAffinity();
                                }else{
                                    getActivity().finish();
                                }
                            }
                        });
                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();

                        return true;
                    }
                }
                return false;
            }
        });
        prefManager = new PrefManager(getActivity());
        String data = prefManager.getChecklogindata();

        String login_data = prefManager.getChecklogindata();
        System.out.println("login_data :" + login_data);

        Gson gson = new Gson();
        final ModelUser modelstudent = gson.fromJson(data, ModelUser.class);
        homework_st_name.setText(modelstudent.getSTUDENT_FULL_NAME());
        Picasso.with(getContext()).load(modelstudent.getIMAGE()).fit().into(profile_image_url);
        String str_data="Class : "+modelstudent.getCLASSES_NAME().concat(" Section : "+modelstudent.getSECTIONS_NAME());
        homework_st_class.setText(str_data);

        homeworkdata_not_availabe=(TextView)view.findViewById(R.id.homeworkdata_not_availabe);
        increment=(Button)view.findViewById(R.id.increment_date);
        decrement=(Button)view.findViewById(R.id.decrement_date);
        homework_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        getActivity().INPUT_METHOD_SERVICE);
                if (imm != null) {
                    if (imm.isActive()) {
                        if (getActivity().getCurrentFocus() != null)
                            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    }
                }
            }

        });
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int year = c.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String formattedDay = (String.valueOf(dayOfMonth));
                String formattedMonth = (String.valueOf(monthOfYear));
                int month=monthOfYear+1;
                if (dayOfMonth < 10) {
                    formattedDay = "0" + dayOfMonth;
                }
                if (month < 10) {
                    formattedMonth = "0" + month;
                }
                stringbuilder=new StringBuilder();
                stringbuilder.append(year+"-");
                stringbuilder.append(formattedMonth+"-");
                stringbuilder.append(formattedDay);
                str1=stringbuilder.toString();
                date_self = UtilityFunction.dateConvert(str1);
                homework_date.setText(date_self);
                homework_date2.setText(date_self);
            }
        }, year, month, date);

        datePickerDialog.setTitle("Choose Date");
        dt=new Date();
        dt1=String.valueOf(dt);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String check = dateFormat.format(dt);
        System.out.println("Call now :"+check);
        date_self = UtilityFunction.dateConvert(check);
        homework_date.setText(date_self);
        homework_date2.setText(date_self);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(dateFormat.parse(dt1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, 1);  // number of days to add
                dt1 = dateFormat.format(c.getTime());
                date_self = UtilityFunction.dateConvert(dt1);
                homework_date.setText(date_self);
                homework_date2.setText(date_self);
                getExaminationdateshown(modelstudent.getClass_id(),section_id,UtilityFunction.dateConvertUpdates(dt1));

            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(dateFormat.parse(dt1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, -1);  // number of days to add
                dt1 = dateFormat.format(c.getTime());
                date_self = UtilityFunction.dateConvert(dt1);
                homework_date2.setText(date_self);
                homework_date.setText(date_self);
                System.out.println("Call now  decre:"+ UtilityFunction.dateConvertUpdates(dt1));
                getExaminationdateshown(modelstudent.getClass_id(),section_id,UtilityFunction.dateConvertUpdates(dt1));

            }
        });
        subject_list=(RecyclerView)view.findViewById(R.id.homework_subject_list);
        albumList = new ArrayList<>();
        getExaminationdateshown(modelstudent.getClass_id(),section_id,check);



        /******************** HOLIDAY******************/

      /*  rv_holidaysdatarv = (RecyclerView) view.findViewById(R.id.rv_holidaysdatarv);
        holidaydata_not_availabe = (TextView) view.findViewById(R.id.holidaydata_not_availabe);
        albumList = new ArrayList<>();
        btn_holidaydateselection = (Button) view.findViewById(R.id.btn_holidaydateselection);
        btn_holidaydateselection.setOnClickListener(this);
        *//*btn_holidaydateselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showDialog(DATE_DIALOG_ID);
            }
        });*//*

        mLayoutManager = new LinearLayoutManager(getActivity());
        getHolidayDataMonth("April");*/
        return view;

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
    private void getExaminationdateshown(String class_id, String section_id, String homework_date1) {
        final ApiIHandler apiService = ApiClient.getClient().create(ApiIHandler.class);
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        Call<ResponseHomework> call = apiService.HomeworkData(class_id,section_id,homework_date1);
        call.enqueue(new Callback<ResponseHomework>() {
            @Override
            public void onResponse(Call<ResponseHomework> call, retrofit2.Response<ResponseHomework> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseHomework data = response.body();

                    if(data.getMessage().equalsIgnoreCase("No records found."))
                    {
                        pDialog.hide();
                        homeworkdata_not_availabe.setVisibility(View.VISIBLE);
                        subject_list.setVisibility(View.GONE);
                    }
                    else
                    {
                        if (data.getResults().size() > 0) {
                            subject_list.setVisibility(View.VISIBLE);
                            homeworkdata_not_availabe.setVisibility(View.GONE);
                            albumList = data.getResults();
                            adapterHomeworkSubject=new AdapterHomeworkSubject(getContext(),albumList);
                            adapterHomeworkSubject.notifyDataSetChanged();
                            subject_list.setAdapter(adapterHomeworkSubject);
                            subject_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        }
                        else
                        {
                            pDialog.hide();
                            homeworkdata_not_availabe.setVisibility(View.VISIBLE);
                            subject_list.setVisibility(View.GONE);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseHomework> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
            }
        });
    }

    public void getHolidayDataMonth(String monthselection) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        Call<ResHolidays> call = apiService.HolidaysData(monthselection);
        call.enqueue(new Callback<ResHolidays>() {
            @Override
            public void onResponse(Call<ResHolidays> call, retrofit2.Response<ResHolidays> response) {
                pDialog.dismiss();
                if (response.code() == 200)
                {
                    ResHolidays data = response.body();
                    pDialog.hide();
                    if (data.getResults().size() > 0) {
                        rv_holidaysdatarv.setVisibility(View.VISIBLE);
                        holidaydata_not_availabe.setVisibility(View.GONE);
                        albumListholiday = data.getResults();
                        adapterHolidays = new AdapterHolidays(getActivity(), albumListholiday);
                        rv_holidaysdatarv.setLayoutManager(mLayoutManager);
                        rv_holidaysdatarv.setItemAnimator(new DefaultItemAnimator());
                        rv_holidaysdatarv.setAdapter(adapterHolidays);
                    } else {
                        pDialog.hide();
                        holidaydata_not_availabe.setVisibility(View.VISIBLE);
                        rv_holidaysdatarv.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResHolidays> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                holidaydata_not_availabe.setVisibility(View.VISIBLE);
                holidaydata_not_availabe.setText("Please connect to internet connection.");
                rv_holidaysdatarv.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_holidaydateselection) {
            //displaySimpleDatePickerDialogFragment();
        }
    }

    private void displaySimpleDatePickerDialogFragment() {
        PickerDialogFragment datePickerDialogFragment;
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        datePickerDialogFragment = PickerDialogFragment.getInstance(
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        datePickerDialogFragment.setOnDateSetListener(this);
        datePickerDialogFragment.show(getChildFragmentManager(), null);
    }

    @Override
    public void onDateSet(int year, int monthOfYear) {


        String CurrentSelectionMONYEAR = DateDisplayUtils.formatMonthYear(year, monthOfYear);
        btn_holidaydateselection.setText(CurrentSelectionMONYEAR);
        String[] separatedMonthName = CurrentSelectionMONYEAR.split(" ");
        getHolidayDataMonth(separatedMonthName[0].trim());
    }

}
