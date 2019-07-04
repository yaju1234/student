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
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgroup.parents.R;
import com.mdgroup.parents.adptercstm.adpter.chat.DateExamAdapters;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ExamType;
import com.mdgroup.parents.schoolmodel.ModelExams;
import com.mdgroup.parents.schoolmodel.ResponseExams;
import com.mdgroup.parents.schoolmodel.ResponseExamsMain;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * @Gaurav
 */

public class FragmentExamDates extends Fragment implements View.OnClickListener {

    LinearLayout rl_main, rl_main_ct;
    Button btn_ct, btn_fe;
    RelativeLayout rl_active_ct, rl_active_fe;
    TextView login_title, data_not_availabe;
    private List<ModelExams> albumList;
    private List<ExamType> albumList_etype;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView rv_resultnamedata;
    PrefManager prefManager;

    public FragmentExamDates() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Exam dates");

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
        View view = inflater.inflate(R.layout.fragment_examdates_rv, container, false);

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
        mLayoutManager = new LinearLayoutManager(getActivity());
        getdataREsultMAtch("1",1);
        // login_title=(TextView)view.findViewById(R.id.login_title);
        // login_title.setText("Exam Name : Class Test");
        btn_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_active_ct.setBackgroundColor(getResources().getColor(R.color.msg_line));
                rl_active_fe.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btn_ct.setTextColor(getResources().getColor(R.color.white));
                btn_fe.setTextColor(getResources().getColor(R.color.coor_white_slat));

                getdataREsultMAtch("1",1);
            }
        });
        btn_fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_active_ct.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rl_active_fe.setBackgroundColor(getResources().getColor(R.color.msg_line));

                btn_ct.setTextColor(getResources().getColor(R.color.coor_white_slat));
                btn_fe.setTextColor(getResources().getColor(R.color.white));

                getdataMainExamgetexamtype();
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

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait...", true);
        Call<ResponseExams> call = apiService.ExamsShownShoolsData("1","1","Other","1");
        call.enqueue(new Callback<ResponseExams>() {
            @Override
            public void onResponse(Call<ResponseExams> call, retrofit2.Response<ResponseExams> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseExams data = response.body();
                    pDialog.hide();
                    try
                    {
                        if(!data.getMessage().equalsIgnoreCase("EXAMS_CLASS_NO is NULL."))
                        {
                            if (data.getResults().size() > 0) {
                                rv_resultnamedata.setVisibility(View.VISIBLE);
                                data_not_availabe.setVisibility(View.GONE);
                                albumList = data.getResults();
                                DateExamAdapters adapterHolidays = new DateExamAdapters(getActivity(), albumList);
                                rv_resultnamedata.setLayoutManager(mLayoutManager);
                                rv_resultnamedata.setItemAnimator(new DefaultItemAnimator());
                                rv_resultnamedata.setAdapter(adapterHolidays);
                            } else
                            {
                                pDialog.hide();
                                data_not_availabe.setVisibility(View.VISIBLE);
                                if(positionid.equalsIgnoreCase("1"))
                                {
                                    data_not_availabe.setText("No class tests available.");
                                }
                                if(positionid.equalsIgnoreCase("2"))
                                {
                                    data_not_availabe.setText("No main exams available.");
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
                                data_not_availabe.setText("No class tests available.");
                            }
                            if(positionid.equalsIgnoreCase("2"))
                            {
                                data_not_availabe.setText("No main exams available.");
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
            public void onFailure(Call<ResponseExams> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getdataREsultMAtchMainExam(final String positionid, String examtype) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait...", true);
        Call<ResponseExams> call = apiService.ExamsShownShoolsData("1","1","Main",examtype);
        call.enqueue(new Callback<ResponseExams>() {
            @Override
            public void onResponse(Call<ResponseExams> call, retrofit2.Response<ResponseExams> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseExams data = response.body();
                    pDialog.hide();
                    try
                    {
                        if(!data.getMessage().equalsIgnoreCase("EXAMS_CLASS_NO is NULL."))
                        {
                            if (data.getResults().size() > 0) {
                                rv_resultnamedata.setVisibility(View.VISIBLE);
                                data_not_availabe.setVisibility(View.GONE);
                                albumList = data.getResults();
                                DateExamAdapters adapterHolidays = new DateExamAdapters(getActivity(), albumList);
                                rv_resultnamedata.setLayoutManager(mLayoutManager);
                                rv_resultnamedata.setItemAnimator(new DefaultItemAnimator());
                                rv_resultnamedata.setAdapter(adapterHolidays);
                            } else
                            {
                                pDialog.hide();
                                data_not_availabe.setVisibility(View.VISIBLE);
                                if(positionid.equalsIgnoreCase("1"))
                                {
                                    data_not_availabe.setText("No class tests available.");
                                }
                                if(positionid.equalsIgnoreCase("2"))
                                {
                                    data_not_availabe.setText("No main exams available.");
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
                                data_not_availabe.setText("No class tests available.");
                            }
                            if(positionid.equalsIgnoreCase("2"))
                            {
                                data_not_availabe.setText("No main exams available.");
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
            public void onFailure(Call<ResponseExams> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getdataMainExamgetexamtype() {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait...", true);
        Call<ResponseExamsMain> call = apiService.getMainExamType();
        call.enqueue(new Callback<ResponseExamsMain>() {
            @Override
            public void onResponse(Call<ResponseExamsMain> call, retrofit2.Response<ResponseExamsMain> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseExamsMain data = response.body();
                    pDialog.hide();
                    try
                    {
                        if(!data.getMessage().equalsIgnoreCase("EXAMS_CLASS_NO is NULL."))
                        {
                            if (data.getResults().size() > 0) {
                                rv_resultnamedata.setVisibility(View.VISIBLE);
                                data_not_availabe.setVisibility(View.GONE);
                                albumList_etype = data.getResults();
                                DateExamMainAdapters adapterHolidays = new DateExamMainAdapters(getActivity(), albumList_etype);
                                rv_resultnamedata.setLayoutManager(mLayoutManager);
                                rv_resultnamedata.setItemAnimator(new DefaultItemAnimator());
                                rv_resultnamedata.setAdapter(adapterHolidays);
                            } else
                            {
                                pDialog.hide();
                                data_not_availabe.setVisibility(View.VISIBLE);
                                data_not_availabe.setText("No main exams available.");
                              /*  if(positionid.equalsIgnoreCase("1"))
                                {
                                    data_not_availabe.setText("No class tests available.");
                                }
                                if(positionid.equalsIgnoreCase("2"))
                                {
                                    data_not_availabe.setText("No main exams available.");
                                }*/
                                rv_resultnamedata.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            pDialog.hide();
                            data_not_availabe.setVisibility(View.VISIBLE);
                            data_not_availabe.setText("No main exams available.");
                            /*if(positionid.equalsIgnoreCase("1"))
                            {
                                data_not_availabe.setText("No class tests available.");
                            }
                            if(positionid.equalsIgnoreCase("2"))
                            {
                                data_not_availabe.setText("No main exams available.");
                            }*/
                            rv_resultnamedata.setVisibility(View.GONE);
                        }

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseExamsMain> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class DateExamMainAdapters extends RecyclerView.Adapter<DateExamMainAdapters.MyViewHolder> {

        private Context mContext;
        private List<ExamType> albumList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView HOLIDAYS_NAME, Exam_SUBJECT, HOLIDAYS_DESCRIPTION, HOLIDAYS_DATE;
            public ImageView image_request;
            public ProgressBar pb_image_request;
            RelativeLayout row_news, rl_color;

            public MyViewHolder(View view) {
                super(view);
                HOLIDAYS_NAME = (TextView) view.findViewById(R.id.HOLIDAYS_NAME);
          /*  HOLIDAYS_DESCRIPTION = (TextView) view.findViewById(R.id.HOLIDAYS_DESCRIPTION);
            HOLIDAYS_DATE = (TextView) view.findViewById(R.id.HOLIDAYS_DATE);
            Exam_SUBJECT = (TextView) view.findViewById(R.id.Exam_SUBJECT);*/
                row_news = (RelativeLayout) view.findViewById(R.id.row_news);
                rl_color = (RelativeLayout) view.findViewById(R.id.rl_color);
            }
        }
        public DateExamMainAdapters(Context mContext, List<ExamType> albumList) {
            this.mContext = mContext;
            this.albumList = albumList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_examtypedata, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final ExamType album = albumList.get(position);
            holder.HOLIDAYS_NAME.setText("Exam Name : " + album.getEXAM_TYPE_NAME());
        /*holder.HOLIDAYS_DESCRIPTION.setText("Time Duration : " + album.getEXAMS_START_TIME().concat(" - ").concat(album.getEXAMS_END_TIME()));
        holder.HOLIDAYS_DATE.setText("Exam Date : " + album.getEXAMS_DATE());
        if( album.getEXAM_SUBJECT_NAME() !=null)
        {
            holder.Exam_SUBJECT.setText("Subject : " + album.getEXAM_SUBJECT_NAME());
        }

       else
        {
            holder.Exam_SUBJECT.setText("Subject : " + "");
        }*/
        /*if(position==0)
        {
            holder.HOLIDAYS_DATE1.setText("Declared Soon");
        }
        else
        {
            holder.HOLIDAYS_DATE1.setText("See Result");
        }*/

            if (position % 2 == 1) {
                holder.rl_color.setBackgroundColor(Color.rgb(255, 137, 98));
            } else {
                holder.rl_color.setBackgroundColor(Color.rgb(74, 182, 172));
            }

            holder.row_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getdataREsultMAtchMainExam("2",album.getEXAM_TYPE_ID());
                }
            });

        }

        /**
         * Showing popup menu when tapping on 3 dots
         */


        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
              /*  case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;*/
                    default:
                }
                return false;
            }
        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }
    }


}
