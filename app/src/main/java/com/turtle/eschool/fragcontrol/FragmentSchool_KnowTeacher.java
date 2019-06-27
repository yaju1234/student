package com.turtle.eschool.fragcontrol;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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

import com.turtle.eschool.R;
import com.turtle.eschool.adptercstm.adpter.chat.AdapterTimeTable;
import com.turtle.eschool.adptercstm.adpter.chat.Adapterknowteacher;
import com.turtle.eschool.adptercstm.adpter.chat.DateExamAdapters;
import com.turtle.eschool.api.urlsApimanage.ApiClient;
import com.turtle.eschool.interfaces.custom.ApiIHandler;
import com.turtle.eschool.prefControl.PrefManager;
import com.turtle.eschool.schoolmodel.ModelExams;
import com.turtle.eschool.schoolmodel.ModelTeacher;
import com.turtle.eschool.schoolmodel.ResponseExams;
import com.turtle.eschool.schoolmodel.ResponseTeacher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Pratibha on 3/1/2017.
 */

public class FragmentSchool_KnowTeacher extends Fragment {
    RecyclerView knowteacher_list;
    Adapterknowteacher adapterknowteacher;
    private GridLayoutManager lLayout;
    private List<ModelTeacher> albumList;
    PrefManager prefManager;
    TextView knowteacher_not_availabe;
    RecyclerView.LayoutManager mLayoutManager;
    String id="1";
    String id1="1";
    public FragmentSchool_KnowTeacher() {
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Know Your Teacher");

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
        View view = inflater.inflate(R.layout.fragment_knowteacher, container, false);
        knowteacher_not_availabe=(TextView)view.findViewById(R.id.knowteacher_not_availabe);
        lLayout = new GridLayoutManager(getContext(), 2);
        knowteacher_list=(RecyclerView)view.findViewById(R.id.knowteacher_list);
        albumList = new ArrayList<>();
         mLayoutManager = new LinearLayoutManager(getActivity());
        getExaminationdateshown(id,id1);


        return view;
    }
    public void getExaminationdateshown(String st_id,String i) {
        final ApiIHandler apiService = ApiClient.getClient().create(ApiIHandler.class);
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        Call<ResponseTeacher> call = apiService.TeacherData(st_id,i);
        call.enqueue(new Callback<ResponseTeacher>() {
            @Override
            public void onResponse(Call<ResponseTeacher> call, retrofit2.Response<ResponseTeacher> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseTeacher data = response.body();
                    pDialog.hide();
                    if(data.getMessage().equalsIgnoreCase("No records found."))
                    {
                        knowteacher_not_availabe.setVisibility(View.VISIBLE);
                        knowteacher_list.setVisibility(View.GONE);
                    }
                    else
                    {
                        if (data.getResults().size() > 0) {
                            knowteacher_list.setVisibility(View.VISIBLE);
                            knowteacher_not_availabe.setVisibility(View.GONE);
                            albumList = data.getResults();
                            adapterknowteacher = new Adapterknowteacher(getActivity(), albumList);
                            knowteacher_list.setLayoutManager(mLayoutManager);
                            knowteacher_list.setItemAnimator(new DefaultItemAnimator());
                            knowteacher_list.setAdapter(adapterknowteacher);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseTeacher> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }

}