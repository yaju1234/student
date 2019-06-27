package com.mdgroup.parents.adptercstm.adpter.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgroup.parents.R;
import com.squareup.picasso.Picasso;
import com.mdgroup.parents.fragcontrol.FragmentSchool_Teacherdetail;
import com.mdgroup.parents.schoolmodel.ModelTeacher;

import java.util.List;

/**
 * Created by Pratibha on 3/1/2017.
 */

public class Adapterknowteacher extends RecyclerView.Adapter<Adapterknowteacher.YourRecyclerViewHolder> {

    private LayoutInflater inflater;

    static AppCompatActivity activity;

    private List<ModelTeacher> albumList;
    public static Context context;
    public static Fragment fragment;


    @Override
    public void onViewRecycled(YourRecyclerViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(YourRecyclerViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    public Adapterknowteacher(Context context, List<ModelTeacher> albumList) {

        inflater = LayoutInflater.from(context);
        activity = (AppCompatActivity) context;
        this.albumList = albumList;
        this.context = context;

    }

    @Override
    public YourRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_knowteacher, viewGroup, false);
        YourRecyclerViewHolder holder = new YourRecyclerViewHolder(root);
        return holder;
    }


    @Override
    public void onBindViewHolder(final YourRecyclerViewHolder yourRecyclerViewHolder, final int i) {
        final ModelTeacher album = albumList.get(i);
        yourRecyclerViewHolder.name.setText(album.getTEACHERS_NAME());
        yourRecyclerViewHolder.subject.setText(album.getSUBJECTS_NAME());
        if(album.getTEACHERS_FILE_PATH()==null)
        {
            if(album.getTEACHERS_FILE_PATH()=="")
            {
                          Picasso.with(context)
                        .load(R.drawable.no_image_available)
                        // .error(R.drawable.no_image_available)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.no_image_available)
                        .into(yourRecyclerViewHolder.image);
            }
        }
        else
        {
            if(!album.getTEACHERS_FILE_PATH().equalsIgnoreCase(""))
            {
                Picasso.with(context)
                        .load(album.getTEACHERS_FILE_PATH())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.no_image_available)
                        .into(yourRecyclerViewHolder.image);
            }

        }
        yourRecyclerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSchool_Teacherdetail fragInfo = new FragmentSchool_Teacherdetail();
                Bundle bundle = new Bundle();
                bundle.putString("name", album.getTEACHERS_NAME());
                bundle.putString("subject", album.getSUBJECTS_NAME());
                bundle.putString("qualification", album.getTEACHERS_QUALIFICATION());
                bundle.putString("experience", album.getTEACHERS_EXPERIENCE());
                bundle.putString("image",album.getTEACHERS_FILE_PATH());
                bundle.putString("email",album.getTEACHERS_EMAIL());
                fragInfo.setArguments(bundle);
                activity.getSupportFragmentManager().popBackStackImmediate();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragInfo).addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    static class YourRecyclerViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView image;
        TextView name, subject;

        public YourRecyclerViewHolder(View itemView) {

            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.teacher_detail);
            name = (TextView) itemView.findViewById(R.id.teacher_name);
            subject = (TextView) itemView.findViewById(R.id.teacher_subject);
            image=(ImageView)itemView.findViewById(R.id.teacher_image);

        }
    }


    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
}