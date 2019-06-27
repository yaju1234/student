package com.turtle.eschool.fragcontrol;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.turtle.eschool.DateDisplayUtils;
import com.turtle.eschool.R;
import com.turtle.eschool.PickerDialog;
import com.turtle.eschool.PickerDialogFragment;
import com.turtle.eschool.adptercstm.adpter.chat.AdapterHolidays;
import com.turtle.eschool.api.urlsApimanage.ApiClient;
import com.turtle.eschool.interfaces.custom.ApiIHandler;
import com.turtle.eschool.schoolmodel.ModelHolidays;
import com.turtle.eschool.schoolmodel.ResHolidays;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;


public class FragmentHolidays extends Fragment implements PickerDialog.OnDateSetListener, View.OnClickListener {
    private RecyclerView rv_holidaysdatarv;
    private AdapterHolidays adapterHolidays;
    private List<ModelHolidays> albumList;
    RecyclerView.LayoutManager mLayoutManager;
    TextView holidaydata_not_availabe;
    Button btn_holidaydateselection;

    static final int DATE_DIALOG_ID = 1;
    private int mYear;
    private int mMonth;
    private int mDay;

    public FragmentHolidays() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Holidays");

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_holiday_rv, container, false);

        rv_holidaysdatarv = (RecyclerView) view.findViewById(R.id.rv_holidaysdatarv);
        holidaydata_not_availabe = (TextView) view.findViewById(R.id.holidaydata_not_availabe);
        albumList = new ArrayList<>();
        btn_holidaydateselection = (Button) view.findViewById(R.id.btn_holidaydateselection);
        btn_holidaydateselection.setOnClickListener(this);
        /*btn_holidaydateselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showDialog(DATE_DIALOG_ID);
            }
        });*/

        mLayoutManager = new LinearLayoutManager(getActivity());
        getHolidayDataMonth("May");
       /* try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
       /* adapterHolidays = new AdapterHolidays(getActivity(), albumList);
        rv_holidaysdatarv.setAdapter(adapterHolidays);*/


        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int mo = calendar.get(Calendar.MONTH);
        int ye = calendar.get(Calendar.YEAR);

        String CurrentSelectionMONYEAR = DateDisplayUtils.formatMonthYear(ye, mo);
        btn_holidaydateselection.setText(CurrentSelectionMONYEAR);
        String[] separatedMonthName = CurrentSelectionMONYEAR.split(" ");

        getHolidayDataMonth(separatedMonthName[0].trim());

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_holidaydateselection) {
            displaySimpleDatePickerDialogFragment();
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

    public void getHolidayDataMonth(String monthselection) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "School Holiday wait ...", true);
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
                        albumList = data.getResults();
                        adapterHolidays = new AdapterHolidays(getActivity(), albumList);
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
}
