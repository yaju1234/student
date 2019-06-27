package com.turtle.eschool.adptercstm.adpter.chat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turtle.eschool.R;
import com.turtle.eschool.schoolmodel.ModelHomework;

import java.util.List;

/**
 * Created by Pratibha on 2/28/2017.
 */

public class AdapterHomeworkSubject extends RecyclerView.Adapter<AdapterHomeworkSubject.YourRecyclerViewHolder> {

    private LayoutInflater inflater;

    static AppCompatActivity activity;

    private List<ModelHomework> albumList;
    public static Context context;
    public static Fragment fragment;
    String e = "E", m = "M", s = "S", h = "H";


    public AdapterHomeworkSubject(Context context, List<ModelHomework> albumList) {

        inflater = LayoutInflater.from(context);
        activity = (AppCompatActivity) context;
        this.albumList = albumList;
        this.context = context;

    }

    @Override
    public YourRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_homeworksubject, viewGroup, false);
        YourRecyclerViewHolder holder = new YourRecyclerViewHolder(root);
        return holder;
    }
    @Override
    public void onBindViewHolder(final YourRecyclerViewHolder yourRecyclerViewHolder, final int i) {
        final ModelHomework album = albumList.get(i);
        String firstchar = album.getSUBJECTS_NAME();
        char first = firstchar.charAt(0);
        yourRecyclerViewHolder.subject_firstletter.setText("" + first);
        if (String.valueOf(first).equals(e)) {
            yourRecyclerViewHolder.subject_firstletter.setBackgroundResource(R.drawable.circle3);
        } else if (String.valueOf(first).equals(m)) {
            yourRecyclerViewHolder.subject_firstletter.setBackgroundResource(R.drawable.circle2);
        } else if (String.valueOf(first).equals(h)) {
            yourRecyclerViewHolder.subject_firstletter.setBackgroundResource(R.drawable.circle1);
        } else {
            yourRecyclerViewHolder.subject_firstletter.setBackgroundResource(R.drawable.circle);

        }
        yourRecyclerViewHolder.subjectname.setText(album.getSUBJECTS_NAME());
        yourRecyclerViewHolder.heading.setText(album.getHOMEWORK_TOPIC());
        yourRecyclerViewHolder.detail.setText(album.getHOMEWORK_DESCRIPTION());

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    static class YourRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView subjectname, heading, detail, subject_firstletter;


        public YourRecyclerViewHolder(View itemView) {

            super(itemView);
            subjectname = (TextView) itemView.findViewById(R.id.subject_name);
            heading = (TextView) itemView.findViewById(R.id.homework_heading);
            detail = (TextView) itemView.findViewById(R.id.homework_detail);
            subject_firstletter = (TextView) itemView.findViewById(R.id.subject_firstletter);
//            String s=subjectname.getText().toString();
//            char first = s.charAt(0);

        }
    }
}