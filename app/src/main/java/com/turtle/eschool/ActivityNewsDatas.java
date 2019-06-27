package com.turtle.eschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turtle.eschool.schoolmodel.ModelNews;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityNewsDatas extends AppCompatActivity {

    ImageView image_sub;
    static int i = 0;
    private Timer myTimer;
    ModelNews news_data_receive;
    Context mcontext;
    TextView eventname;
    @Override
    public AppCompatDelegate getDelegate() {
        return super.getDelegate();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    @Nullable
    @Override
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return super.getDrawerToggleDelegate();
    }

    @Override
    public void onSupportContentChanged() {
        super.onSupportContentChanged();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void supportNavigateUpTo(Intent upIntent) {
        super.supportNavigateUpTo(upIntent);
    }

    @Override
    public boolean supportShouldUpRecreateTask(Intent targetIntent) {
        return super.supportShouldUpRecreateTask(targetIntent);
    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return super.getSupportParentActivityIntent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_datas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mcontext = getApplicationContext();
        eventname=(TextView)findViewById(R.id.eventname);
       final CollapsingToolbarLayout toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout.setTitle("News");
                    isShow = true;
                } else if(isShow) {
                    toolbar_layout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        TextView news_details = (TextView) findViewById(R.id.news_details);
        //receive
        news_data_receive = (ModelNews) getIntent().getSerializableExtra("news_data");
        image_sub = (ImageView) findViewById(R.id.image_sub);

        if (news_data_receive != null) {
            setTitle("");
            eventname.setText(news_data_receive.getNEWS_HEADING());
            news_details.setText(news_data_receive.getNEWS_DESCRIPTION());
            Glide.with(ActivityNewsDatas.this).load(news_data_receive.getNEWS_SUB_IMG1()).into(image_sub);
        }
        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        }, 500, 5000);


    }

    @Override
    public void supportStartPostponedEnterTransition() {
        super.supportStartPostponedEnterTransition();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            System.out.println("the value id call:" + i);
            if (i < 3) {
                System.out.println("the value id:" + i);
                if (i == 0) {
                    try {
                        if (news_data_receive != null) {
                            Glide.with(mcontext).load(news_data_receive.getNEWS_SUB_IMG1()).into(image_sub);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (i == 1) {
                    if (news_data_receive != null) {
                        Glide.with(mcontext).load(news_data_receive.getNEWS_SUB_IMG2()).into(image_sub);
                    }
                }
                if (i == 2) {
                    try {
                        if (news_data_receive != null) {
                            Glide.with(mcontext).load(news_data_receive.getNEWS_SUB_IMG3()).into(image_sub);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                i = 0;
                try {
                    if (news_data_receive != null) {
                        Glide.with(mcontext).load(news_data_receive.getNEWS_SUB_IMG1()).into(image_sub);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
