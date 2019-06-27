package com.mdgroup.parents.adptercstm.adpter.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mdgroup.parents.R;

public class StudentResuleManager extends AppCompatActivity {
    private static final String TAG = "ChatActivity";

    LinearLayout rl_main;
    LinearLayout rl_main_ct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.school_result_details);

        Intent mIntent = getIntent();
        String EXTRA_VIEWID = String.valueOf(mIntent.getIntExtra("EXTRA_VIEWID", 0));

        setTitle("MarkSheet");

        rl_main_ct=(LinearLayout)findViewById(R.id.rl_main_ct);
        rl_main=(LinearLayout) findViewById(R.id.rl_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(EXTRA_VIEWID.equalsIgnoreCase("2"))
        {
            rl_main_ct.setVisibility(View.GONE);
            rl_main.setVisibility(View.VISIBLE);
        }
        else
        {
            rl_main_ct.setVisibility(View.VISIBLE);
            rl_main.setVisibility(View.GONE);
        }
    }

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