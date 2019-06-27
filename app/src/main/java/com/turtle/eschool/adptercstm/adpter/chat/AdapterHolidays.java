package com.turtle.eschool.adptercstm.adpter.chat;

import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.turtle.eschool.ActivityEventsDatas;
import com.turtle.eschool.R;
import com.turtle.eschool.schoolmodel.ModelEvents;
import com.turtle.eschool.schoolmodel.ModelHolidays;
import com.turtle.eschool.utilityschool.UtilityFunction;

import java.util.List;

/**
 * @Gaurav Mangal
 */
public class AdapterHolidays extends RecyclerView.Adapter<AdapterHolidays.MyViewHolder> {

    private Context mContext;
    private List<ModelHolidays> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView HOLIDAYS_NAME, HOLIDAYS_DESCRIPTION, HOLIDAYS_DATE;
        public ImageView image_request;
        public ProgressBar pb_image_request;
        RelativeLayout row_news;
        public MyViewHolder(View view) {
            super(view);
            HOLIDAYS_NAME = (TextView) view.findViewById(R.id.HOLIDAYS_NAME);
            HOLIDAYS_DESCRIPTION = (TextView) view.findViewById(R.id.HOLIDAYS_DESCRIPTION);
            HOLIDAYS_DATE = (TextView) view.findViewById(R.id.HOLIDAYS_DATE);

            row_news=(RelativeLayout)view.findViewById(R.id.row_news);
        }
    }

    public AdapterHolidays(Context mContext, List<ModelHolidays> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_holidaysdata, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelHolidays album = albumList.get(position);
        holder.HOLIDAYS_NAME.setText(album.getHOLIDAYS_NAME());
        holder.HOLIDAYS_DESCRIPTION.setText(album.getHOLIDAYS_DESCRIPTION());

        String date_self = UtilityFunction.dateConvert(album.getHOLIDAYS_DATE());

        holder.HOLIDAYS_DATE.setText(date_self);
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