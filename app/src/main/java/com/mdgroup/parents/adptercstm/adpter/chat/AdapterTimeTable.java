package com.mdgroup.parents.adptercstm.adpter.chat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdgroup.parents.R;
import com.mdgroup.parents.schoolmodel.ModelTimeTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


        if(album.getTIME_TABLE_PERIOD_START_TIME().toLowerCase().contains("am") || album.getTIME_TABLE_PERIOD_START_TIME().toLowerCase().contains("am")){
           String tf =   timeDiff1(album.getTIME_TABLE_PERIOD_START_TIME() , album.getTIME_TABLE_PERIOD_END_TIME());
           yourRecyclerViewHolder.diff.setText(tf+"min");

        }else{
            String tf =   timeDiff(album.getTIME_TABLE_PERIOD_START_TIME() , album.getTIME_TABLE_PERIOD_END_TIME());
            yourRecyclerViewHolder.diff.setText(tf+"min");
        }





    }

    @Override
    public int getItemCount()
    {
        return albumList.size();
    }


    static class YourRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView time,period,subject,teacher_name,room_no,diff;

        public YourRecyclerViewHolder(View itemView)
        {

            super(itemView);
            time=(TextView)itemView.findViewById(R.id.timetable_time);
            period=(TextView)itemView.findViewById(R.id.timetable_period);
            subject=(TextView)itemView.findViewById(R.id.timetable_subject);
            teacher_name=(TextView)itemView.findViewById(R.id.teacher_name);
            room_no=(TextView)itemView.findViewById(R.id.room_no);
            diff=(TextView)itemView.findViewById(R.id.diff);

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




    public String timeDiff(String dateStart,String dateStop){

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            return ""+diffMinutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }



    public String timeDiff1(String dateStart,String dateStop){

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("HH:mm a");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            return ""+diffMinutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }



}