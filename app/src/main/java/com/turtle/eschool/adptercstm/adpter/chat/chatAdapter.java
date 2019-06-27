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

import com.turtle.eschool.R;
import com.turtle.eschool.schoolmodel.ModelTeacher;

import java.util.List;

/**
 * @Gaurav Mangal
 */
public class chatAdapter extends RecyclerView.Adapter<chatAdapter.MyViewHolder> {

    private Context mContext;
    private List<ModelTeacher> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView teacher_name, teacher_sub, NEWS_UPLOAD_DATE;
        public ImageView image_request;
        public ProgressBar pb_image_request;
        RelativeLayout row_news;
        public MyViewHolder(View view) {
            super(view);
            teacher_name = (TextView) view.findViewById(R.id.teacher_name);
            teacher_sub = (TextView) view.findViewById(R.id.teacher_sub);
            //NEWS_UPLOAD_DATE = (TextView) view.findViewById(R.id.NEWS_UPLOAD_DATE);
            //image_request = (ImageView) view.findViewById(R.id.image_request);
            pb_image_request = (ProgressBar) view.findViewById(R.id.pb_image_request);
            row_news=(RelativeLayout)view.findViewById(R.id.row_news);
        }
    }
    public chatAdapter(Context mContext, List<ModelTeacher> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chatviewmanager, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelTeacher chat = albumList.get(position);
        holder.teacher_name.setText(chat.getTEACHERS_NAME());
        holder.teacher_sub.setText(chat.getCLASSES_NAME());


        holder.pb_image_request.setVisibility(View.GONE);

        holder.row_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, StudentChat.class);
                i.putExtra("chatobjectdata", chat);
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