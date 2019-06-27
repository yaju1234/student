package com.mdgroup.parents.fragcontrol;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mdgroup.parents.R;
import com.mdgroup.parents.adptercstm.adpter.chat.REsultsAdapters;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelUser;
import com.mdgroup.parents.schoolmodel.ResResults;
import com.mdgroup.parents.schoolmodel.ResultExams;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * @Gaurav
 */

public class SchoolResultsManager extends Fragment implements View.OnClickListener {

    LinearLayout rl_main, rl_main_ct;
    Button btn_ct, btn_fe;
    RelativeLayout rl_active_ct, rl_active_fe;
    TextView login_title, data_not_availabe;
    private List<ResultExams> albumList;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView rv_resultnamedata;
    PrefManager prefManager;
    ModelUser modelstudent;

    public SchoolResultsManager() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Result");

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.school_result, container, false);

        // rl_main=(LinearLayout) view.findViewById(R.id.rl_main);
        //rl_main_ct=(LinearLayout)view.findViewById(R.id.rl_main_ct);
        btn_ct = (Button) view.findViewById(R.id.btn_ct);
        btn_fe = (Button) view.findViewById(R.id.btn_fe);
        rl_active_ct = (RelativeLayout) view.findViewById(R.id.rl_active_ct);
        rl_active_fe = (RelativeLayout) view.findViewById(R.id.rl_active_fe);
        rv_resultnamedata = (RecyclerView) view.findViewById(R.id.rv_resultnamedata);
        data_not_availabe = (TextView) view.findViewById(R.id.data_not_availabe);
        albumList = new ArrayList<>();
        prefManager = new PrefManager(getActivity());
        String data = prefManager.getChecklogindata();
        Gson gson = new Gson();
        modelstudent = gson.fromJson(data, ModelUser.class);
        mLayoutManager = new LinearLayoutManager(getActivity());
        getdataREsultMAtch(modelstudent.getSTUDENT_ADMISSION_NO(),1);
        // login_title=(TextView)view.findViewById(R.id.login_title);
        // login_title.setText("Exam Name : Class Test");
        btn_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_active_ct.setBackgroundColor(Color.rgb(255,215,110));
                rl_active_fe.setBackgroundColor(Color.rgb(153, 153, 153));
                btn_ct.setTextColor(Color.rgb(255,255,255));
                btn_fe.setTextColor(Color.rgb(153,153,153));
                // rl_main.setVisibility(View.VISIBLE);
                // rl_main_ct.setVisibility(View.GONE);
                //login_title.setText("Exam Name : Final Exam");
                getdataREsultMAtch(modelstudent.getSTUDENT_ADMISSION_NO(),1);
            }
        });
        btn_fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_active_ct.setBackgroundColor(Color.rgb(153, 153, 153));
                rl_active_fe.setBackgroundColor(Color.rgb(255,215,110));

                btn_ct.setTextColor(Color.rgb(153,153,153));
                btn_fe.setTextColor(Color.rgb(255,255,255));
                //rl_main.setVisibility(View.GONE);
                //rl_main_ct.setVisibility(View.VISIBLE);
                //login_title.setText("Exam Name : Class Test");
                getdataREsultMAtch(modelstudent.getSTUDENT_ADMISSION_NO(),2);
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public Object getExitTransition() {
        return super.getExitTransition();
    }

    @Override
    public void setExitTransition(Object transition) {
        super.setExitTransition(transition);
    }

    @Override
    public Object getReturnTransition() {
        return super.getReturnTransition();
    }

    @Override
    public void setReturnTransition(Object transition) {
        super.setReturnTransition(transition);
    }

    @Override
    public Object getEnterTransition() {
        return super.getEnterTransition();
    }

    @Override
    public void setEnterTransition(Object transition) {
        super.setEnterTransition(transition);
    }

    @Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        super.setExitSharedElementCallback(callback);
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        super.setEnterSharedElementCallback(callback);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void unregisterForContextMenu(View view) {
        super.unregisterForContextMenu(view);
    }


    public void getdataREsultMAtch(final String positionid, final int viewid) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Result Loading ...", true);
        Call<ResResults> call = apiService.REsultData(prefManager.getCheckUser_id(), positionid);
        call.enqueue(new Callback<ResResults>() {
            @Override
            public void onResponse(Call<ResResults> call, retrofit2.Response<ResResults> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResResults data = response.body();
                    pDialog.hide();
                   try
                   {
                       if(data.getResult() !=null)
                       {
                           if (data.getResult().size() > 0) {
                               rv_resultnamedata.setVisibility(View.VISIBLE);
                               data_not_availabe.setVisibility(View.GONE);
                               albumList = data.getResult();
                               REsultsAdapters adapterHolidays = new REsultsAdapters(getActivity(), albumList,viewid);
                               rv_resultnamedata.setLayoutManager(mLayoutManager);
                               rv_resultnamedata.setItemAnimator(new DefaultItemAnimator());
                               rv_resultnamedata.setAdapter(adapterHolidays);
                           } else
                           {
                               pDialog.hide();
                               data_not_availabe.setVisibility(View.VISIBLE);
                               if(positionid.equalsIgnoreCase("1"))
                               {
                                   data_not_availabe.setText("No class tests available");
                               }
                               if(positionid.equalsIgnoreCase("2"))
                               {
                                   data_not_availabe.setText("No Main Exam available");
                               }
                               rv_resultnamedata.setVisibility(View.GONE);
                           }
                       }
                       else
                       {
                           pDialog.hide();
                           data_not_availabe.setVisibility(View.VISIBLE);
                           if(positionid.equalsIgnoreCase("1"))
                           {
                               data_not_availabe.setText("No class tests available");
                           }
                           if(positionid.equalsIgnoreCase("2"))
                           {
                               data_not_availabe.setText("No main exams available");
                           }
                           rv_resultnamedata.setVisibility(View.GONE);
                       }

                   }catch (Exception e)
                   {
                       e.printStackTrace();
                   }

                }
            }

            @Override
            public void onFailure(Call<ResResults> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
