package com.turtle.eschool.adptercstm.adpter.chat;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.turtle.eschool.R;
import com.turtle.eschool.schoolmodel.ModelExams;
import com.turtle.eschool.schoolmodel.ResultExams;

import java.util.List;

/**
 * @Gaurav Mangal
 */
public class REsultsAdapters extends RecyclerView.Adapter<REsultsAdapters.MyViewHolder> {

    private Context mContext;
    private List<ResultExams> albumList;
    private int idview;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView HOLIDAYS_NAME, Exam_SUBJECT, HOLIDAYS_DESCRIPTION, HOLIDAYS_DATE, result_status;
        public ImageView image_request;
        public ProgressBar pb_image_request;
        RelativeLayout row_news, rl_color;

        public MyViewHolder(View view) {
            super(view);
            HOLIDAYS_NAME = (TextView) view.findViewById(R.id.HOLIDAYS_NAME);
           // HOLIDAYS_DESCRIPTION = (TextView) view.findViewById(R.id.HOLIDAYS_DESCRIPTION);
            HOLIDAYS_DATE = (TextView) view.findViewById(R.id.HOLIDAYS_DATE);
            Exam_SUBJECT = (TextView) view.findViewById(R.id.Exam_SUBJECT);
            row_news = (RelativeLayout) view.findViewById(R.id.row_news);
            rl_color = (RelativeLayout) view.findViewById(R.id.rl_color);
            result_status = (TextView) view.findViewById(R.id.result_status);
        }
    }

    public REsultsAdapters(Context mContext, List<ResultExams> albumList,int idview) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.idview=idview;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_resultdata, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ResultExams album = albumList.get(position);
        holder.HOLIDAYS_NAME.setText("Exam Name : " + album.getEXAMS_NAME());

        holder.HOLIDAYS_DATE.setText("Exam Date : " + album.getEXAMS_DATE());
        holder.Exam_SUBJECT.setText("Subject : " + album.getEXAMS_NAME());

        if(album.getRESULTS_STATUS().equalsIgnoreCase("DECLARED"))
        {
            holder.result_status.setText("See Result...");
        }
        else
        {
            holder.result_status.setText("Will Declare Soon");
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
        holder.row_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(album.getRESULTS_STATUS().equalsIgnoreCase("DECLARED"))
                {
                    Intent intent = new Intent(mContext, StudentResuleManager.class);
                    intent.putExtra("EXTRA_VIEWID", idview);
                    mContext.startActivity(intent);
                }
                else
                {
                    Toast.makeText(mContext,"Results will be declared shortly.",Toast.LENGTH_SHORT).show();
                }

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