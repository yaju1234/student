package com.mdgroup.parents.fragcontrol;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mdgroup.parents.R;
import com.mdgroup.parents.adptercstm.adpter.chat.AdapterHomeworkSubject;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelHomework;
import com.mdgroup.parents.schoolmodel.ModelUser;
import com.mdgroup.parents.schoolmodel.ResponseHomework;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Pratibha on 2/28/2017.
 */

public class FragmentSchool_Homework extends Fragment {
    DatePickerDialog datePickerDialog;
    Calendar c = Calendar.getInstance();
    RecyclerView subject_list;
    TextView homework_date,homework_st_name,homework_st_class;
    AdapterHomeworkSubject adapterHomeworkSubject;
    private List<ModelHomework> albumList;
    String class_id="1",section_id="1",homework_date1=" 2017-03-03";
    //String class_id="1",section_id="1",homework_date1=" 2017-03-03";
    TextView homeworkdata_not_availabe,homework_date2;
    Date dt;
    StringBuilder stringbuilder;
    Activity activity;
    String date_self;
    Button increment,decrement;
    String str1,dt1;
    SimpleDateFormat dateFormat;
    PrefManager prefManager;

    LinearLayout ll_stinfo;
    public FragmentSchool_Homework()
    {

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Homework");

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_homework,container,false);
        homework_date=(TextView)view.findViewById(R.id.homework_date);
        homework_date2=(TextView)view.findViewById(R.id.homework_date1);
        homework_st_name=(TextView)view.findViewById(R.id.homework_st_name);
        homework_st_class=(TextView)view.findViewById(R.id.homework_st_class);
        ll_stinfo=(LinearLayout)view.findViewById(R.id.ll_stinfo);
        ll_stinfo.setVisibility(View.GONE);
        prefManager = new PrefManager(getActivity());
        String data = prefManager.getChecklogindata();

        String login_data = prefManager.getChecklogindata();

        System.out.println("login_data :" + login_data);

        Gson gson = new Gson();
        final ModelUser modelstudent = gson.fromJson(data, ModelUser.class);
        homework_st_name.setText(modelstudent.getSTUDENT_FULL_NAME());
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
                homework_date.setText(date_self);
                homework_date2.setText(date_self);
                System.out.println("Call now  decre:"+ UtilityFunction.dateConvertUpdates(dt1));
                getExaminationdateshown(modelstudent.getClass_id(),section_id,UtilityFunction.dateConvertUpdates(dt1));

            }
        });
        subject_list=(RecyclerView)view.findViewById(R.id.homework_subject_list);
        albumList = new ArrayList<>();
        getExaminationdateshown(modelstudent.getClass_id(),section_id,check);

        return view;
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
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
