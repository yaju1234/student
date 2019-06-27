package com.turtle.eschool.adptercstm.adpter.chat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turtle.eschool.R;
import com.turtle.eschool.schoolmodel.ModelTimeTable;

import java.util.List;

/**
 * Created by Pratibha on 3/1/2017.
 */

public class AdapterTimeTable extends RecyclerView.Adapter<AdapterTimeTable.YourRecyclerViewHolder> {

    private LayoutInflater inflater;

    static AppCompatActivity activity;


    public static Context context;
    public static Fragment fragment;
    private List<ModelTimeTable> albumList;

    public  AdapterTimeTable(Context context,List<ModelTimeTable> albumList) {

        inflater= LayoutInflater.from(context);
        activity = (AppCompatActivity)context;
        this.albumList=albumList;
        this.context=context;

    }

    @Override
    public boolean onFailedToRecycleView(YourRecyclerViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(YourRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public YourRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_timetable, viewGroup, false);
        YourRecyclerViewHolder holder = new YourRecyclerViewHolder(root);
        return holder;
    }
    @Override
    public void onBindViewHolder(final YourRecyclerViewHolder yourRecyclerViewHolder, final int i)
    {
        final ModelTimeTable album=albumList.get(i);
        yourRecyclerViewHolder.period.setText(album.getTIME_TABLE_PERIOD_NO());
        yourRecyclerViewHolder.time.setText(album.getTIME_TABLE_PERIOD_START_TIME()+"-"+album.getTIME_TABLE_PERIOD_END_TIME());
        yourRecyclerViewHolder.subject.setText(album.getSUBJECTS_NAME());
        yourRecyclerViewHolder.teacher_name.setText(album.getTEACH_BY());
        yourRecyclerViewHolder.room_no.setText(album.getROOM_NO());





    }

    @Override
    public int getItemCount()
    {
        return albumList.size();
    }


    static class YourRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView time,period,subject,teacher_name,room_no;

        public YourRecyclerViewHolder(View itemView)
        {

            super(itemView);
            time=(TextView)itemView.findViewById(R.id.timetable_time);
            period=(TextView)itemView.findViewById(R.id.timetable_period);
            subject=(TextView)itemView.findViewById(R.id.timetable_subject);
            teacher_name=(TextView)itemView.findViewById(R.id.teacher_name);
            room_no=(TextView)itemView.findViewById(R.id.room_no);

        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }
}