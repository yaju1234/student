package com.mdgroup.parents.fragcontrol;


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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mdgroup.parents.R;
import com.mdgroup.parents.adptercstm.adpter.chat.AdapterTimeTable;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelTimeTable;
import com.mdgroup.parents.schoolmodel.ModelUser;
import com.mdgroup.parents.schoolmodel.ResponseTimetable;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Pratibha on 2/28/2017.
 */

public class FragmentSchool_Timetable extends Fragment {
    Button timetable, timetable1;
    String week[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String select;
    TextView weekday,timetabledata_not_availabe;
    int i = 0;
    int c;
    PrefManager prefManager;
    int length;
    RecyclerView timetable_list;
    AdapterTimeTable adapterTimeTable;
    private List<ModelTimeTable> albumList;
    String class_id="1",section_id="1",day="Monday";

    public FragmentSchool_Timetable() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Time Table");

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
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        prefManager = new PrefManager(getActivity());
        String data = prefManager.getChecklogindata();

        String login_data = prefManager.getChecklogindata();
        System.out.println("login_data :" + login_data);
        Gson gson = new Gson();
        final ModelUser modelstudent = gson.fromJson(data, ModelUser.class);
        weekday = (TextView) view.findViewById(R.id.weekday);
        timetabledata_not_availabe=(TextView)view.findViewById(R.id.timetabledata_not_availabe);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        weekday.setText(dayOfTheWeek);
        timetable = (Button) view.findViewById(R.id.btn_timetabledateselection);
        timetable1 = (Button) view.findViewById(R.id.btn_timetabledateselection1);
        timetable_list = (RecyclerView) view.findViewById(R.id.timetable_list);
        gettimetableshow(modelstudent.getClass_id(),section_id,day);


        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = c;
                if (i <= 5) {
                    String a = week[i];
                    weekday.setText(a);

                    gettimetableshow(modelstudent.getClass_id(),section_id,a);
                    i = i + 1;
                    c = i;
                } else {
                    return;

                }
            }
        });
        timetable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = c;
                i = i - 1;
                if (i == -1) {
                    return;
                } else {
                    String a = week[i];
                    weekday.setText(a);
                    gettimetableshow(modelstudent.getClass_id(),section_id,a);

                    c = i;
                }
            }
        });
        return view;
    }

    private void gettimetableshow(String class_id, String section_id, String homework_date1)
    {
            final ApiIHandler apiService = ApiClient.getClient().create(ApiIHandler.class);
            final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
            Call<ResponseTimetable> call = apiService.TimetableData(class_id, section_id, homework_date1);
            call.enqueue(new Callback<ResponseTimetable>() {
                @Override
                public void onResponse(Call<ResponseTimetable> call, retrofit2.Response<ResponseTimetable> response) {
                    pDialog.dismiss();
                    if (response.code() == 200) {
                        ResponseTimetable data = response.body();
                        pDialog.hide();
                        timetabledata_not_availabe.setVisibility(View.VISIBLE);
                        timetable_list.setVisibility(View.GONE);
                        if(data.getMessage().equalsIgnoreCase("No records found."))
                        {
                            Toast.makeText(getActivity(),"No records found.",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            if (data.getResults().size() > 0) {
                                timetable_list.setVisibility(View.VISIBLE);
                                timetabledata_not_availabe.setVisibility(View.GONE);
                                albumList = data.getResults();
                                adapterTimeTable = new AdapterTimeTable(getContext(),albumList);
                                adapterTimeTable.notifyDataSetChanged();
                                timetable_list.setAdapter(adapterTimeTable);
                                timetable_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));                        } else {
                                pDialog.hide();
                                timetabledata_not_availabe.setVisibility(View.VISIBLE);
                                timetable_list.setVisibility(View.GONE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseTimetable> call, Throwable t)
                {
                    // Log error here since request failed
                    Log.e("", t.toString());
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

    @Override
    public void setReenterTransition(Object transition) {
        super.setReenterTransition(transition);
    }

    @Override
    public Object getExitTransition() {
        return super.getExitTransition();
    }
}



