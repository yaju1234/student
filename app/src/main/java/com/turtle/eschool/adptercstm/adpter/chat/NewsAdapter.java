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
import com.turtle.eschool.ActivityNewsDatas;
import com.turtle.eschool.R;
import com.turtle.eschool.schoolmodel.ModelNews;
import com.turtle.eschool.utilityschool.UtilityFunction;

import java.util.List;

/**
 * @Gaurav Mangal
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<ModelNews> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView NEWS_HEADING, NEWS_DESCRIPTION, NEWS_UPLOAD_DATE;
        public ImageView image_request;
        public ProgressBar pb_image_request;
        RelativeLayout row_news;

        public MyViewHolder(View view) {
            super(view);
            NEWS_HEADING = (TextView) view.findViewById(R.id.NEWS_HEADING);
            NEWS_DESCRIPTION = (TextView) view.findViewById(R.id.NEWS_DESCRIPTION);
            NEWS_UPLOAD_DATE = (TextView) view.findViewById(R.id.NEWS_UPLOAD_DATE);
            image_request = (ImageView) view.findViewById(R.id.image_request);
            pb_image_request = (ProgressBar) view.findViewById(R.id.pb_image_request);
            row_news = (RelativeLayout) view.findViewById(R.id.row_news);
        }
    }
    public NewsAdapter(Context mContext, List<ModelNews> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_newsdata, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelNews album = albumList.get(position);
        holder.NEWS_HEADING.setText(album.getNEWS_HEADING());
        holder.NEWS_DESCRIPTION.setText(album.getNEWS_DESCRIPTION());
        String date_self = UtilityFunction.dateConvert(album.getNEWS_UPLOAD_DATE());
        holder.NEWS_UPLOAD_DATE.setText(date_self);
        String img_mainthumb = album.getNEWS_MAIN_IMG();
        if (img_mainthumb != null) {
            Glide.with(mContext).load(album.getNEWS_MAIN_IMG()).into(holder.image_request);
            holder.pb_image_request.setVisibility(View.GONE);
        } else {
            holder.pb_image_request.setVisibility(View.GONE);
        }

        holder.row_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ActivityNewsDatas.class);
                i.putExtra("news_data", album);
                mContext.startActivity(i);
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