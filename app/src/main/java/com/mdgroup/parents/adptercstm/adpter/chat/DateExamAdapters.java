package com.mdgroup.parents.adptercstm.adpter.chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdgroup.parents.R;
import com.mdgroup.parents.schoolmodel.ModelExams;

import java.util.List;

/**
 * @Gaurav Mangal
 */
public class DateExamAdapters extends RecyclerView.Adapter<DateExamAdapters.MyViewHolder> {

    private Context mContext;
    private List<ModelExams> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView HOLIDAYS_NAME, Exam_SUBJECT, HOLIDAYS_DESCRIPTION, HOLIDAYS_DATE;
        public ImageView image_request;
        public ProgressBar pb_image_request;
        RelativeLayout row_news, rl_color;

        public MyViewHolder(View view) {
            super(view);
            HOLIDAYS_NAME = (TextView) view.findViewById(R.id.HOLIDAYS_NAME);
            HOLIDAYS_DESCRIPTION = (TextView) view.findViewById(R.id.HOLIDAYS_DESCRIPTION);
            HOLIDAYS_DATE = (TextView) view.findViewById(R.id.HOLIDAYS_DATE);
            Exam_SUBJECT = (TextView) view.findViewById(R.id.Exam_SUBJECT);
            row_news = (RelativeLayout) view.findViewById(R.id.row_news);
            rl_color = (RelativeLayout) view.findViewById(R.id.rl_color);
        }
    }
    public DateExamAdapters(Context mContext, List<ModelExams> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_examdatesdata, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelExams album = albumList.get(position);
        holder.HOLIDAYS_NAME.setText("Exam Name : " + album.getEXAMS_NAME());
        holder.HOLIDAYS_DESCRIPTION.setText("Time Duration : " + album.getEXAMS_START_TIME().concat(" - ").concat(album.getEXAMS_END_TIME()));
        holder.HOLIDAYS_DATE.setText("Exam Date : " + album.getEXAMS_DATE());
        if( album.getEXAM_SUBJECT_NAME() !=null)
        {
            holder.Exam_SUBJECT.setText("Subject : " + album.getEXAM_SUBJECT_NAME());
        }

       else
        {
            holder.Exam_SUBJECT.setText("Subject : " + "");
        }
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