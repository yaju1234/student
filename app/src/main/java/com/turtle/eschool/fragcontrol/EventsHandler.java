package com.turtle.eschool.fragcontrol;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.turtle.eschool.R;
import com.turtle.eschool.adptercstm.adpter.chat.EventsAdapter;
import com.turtle.eschool.api.urlsApimanage.ApiClient;
import com.turtle.eschool.interfaces.custom.ApiIHandler;
import com.turtle.eschool.schoolmodel.ModelEvents;
import com.turtle.eschool.schoolmodel.ResponseEvent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Gaurav Mangal
 */

public class EventsHandler extends Fragment {
    private RecyclerView recyclerView;
    private EventsAdapter adapter;
    private List<ModelEvents> albumList;
    RecyclerView.LayoutManager mLayoutManager;
    TextView newsdata_not_availabe;

    public EventsHandler()
    {
        // Required empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Events");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
    }

    @Override
    public void setTargetFragment(Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(hasMenu);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events_rv, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_newsdatarv);
        newsdata_not_availabe = (TextView) view.findViewById(R.id.newsdata_not_availabe);
        albumList = new ArrayList<>();


        mLayoutManager = new LinearLayoutManager(getActivity());


        geteventDataFROMSERVER();


       /* try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return view;
    }
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public LoaderManager getLoaderManager() {
        return super.getLoaderManager();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void geteventDataFROMSERVER() {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);

        Call<ResponseEvent> call = apiService.FetchEventData();
        call.enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, retrofit2.Response<ResponseEvent> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseEvent data = response.body();
                    if (data.getMessage().equalsIgnoreCase("No records found.")) {
                        pDialog.hide();
                        newsdata_not_availabe.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        if (data.getResult().size() > 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            newsdata_not_availabe.setVisibility(View.GONE);
                            albumList = data.getResult();
                            adapter = new EventsAdapter(getActivity(), albumList);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);
                        } else {
                            pDialog.hide();
                            newsdata_not_availabe.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
                newsdata_not_availabe.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
