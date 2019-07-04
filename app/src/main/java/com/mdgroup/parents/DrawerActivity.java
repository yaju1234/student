package com.mdgroup.parents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mdgroup.parents.R;
import com.squareup.picasso.Picasso;
import com.mdgroup.parents.fragcontrol.ChatHandlerManager;
import com.mdgroup.parents.fragcontrol.EventsHandler;
import com.mdgroup.parents.fragcontrol.FragmentExamDates;
import com.mdgroup.parents.fragcontrol.FragmentHolidays;
import com.mdgroup.parents.fragcontrol.FragmentSchool_Attendacne;
import com.mdgroup.parents.fragcontrol.FragmentSchool_Homework;
import com.mdgroup.parents.fragcontrol.FragmentSchool_KnowTeacher;
import com.mdgroup.parents.fragcontrol.FragmentSchool_Timetable;
import com.mdgroup.parents.fragcontrol.Fragment_ApplicationLeave;
import com.mdgroup.parents.fragcontrol.Gallery;
import com.mdgroup.parents.fragcontrol.HomeFragmentContoller;
import com.mdgroup.parents.fragcontrol.ManagesNews;
import com.mdgroup.parents.fragcontrol.Quiz;
import com.mdgroup.parents.fragcontrol.Videos;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelUser;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    PrefManager prefManager;
    TextView tv_headerScreenname, tv_headerScreendetails;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(0.0f);
       // getSupportActionBar().setElevation(0);
        setSupportActionBar(toolbar);


        prefManager = new PrefManager(DrawerActivity.this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View header = navigationView.getHeaderView(0);
        tv_headerScreenname = (TextView) header.findViewById(R.id.tv_un);
        tv_headerScreendetails = (TextView) header.findViewById(R.id.textView);
        image=(ImageView)header.findViewById(R.id.imageView);

        LinearLayout clickheader = (LinearLayout) header.findViewById(R.id.clickheader);

        clickheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DrawerActivity.this, EditActivity.class);
                startActivity(i);
            }
        });
        String data = prefManager.getChecklogindata();

        String login_data = prefManager.getChecklogindata();

        System.out.println("login_data :" + login_data);

        Gson gson = new Gson();
        ModelUser modelstudent = gson.fromJson(data, ModelUser.class);
        tv_headerScreenname.setText(modelstudent.getSTUDENT_FULL_NAME());
        String str_data="Class : "+modelstudent.getCLASSES_NAME().concat("("+modelstudent.getSECTIONS_NAME()+")");
        tv_headerScreendetails.setText(str_data);
        Picasso.with(getApplicationContext()).load(modelstudent.getIMAGE()).fit().into(image);
        switchFragment(new HomeFragmentContoller());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {

            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }

    @Override
    public boolean supportRequestWindowFeature(int featureId) {
        return super.supportRequestWindowFeature(featureId);
    }

    @Override
    public void supportInvalidateOptionsMenu() {
        super.supportInvalidateOptionsMenu();
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        super.onSupportActionModeStarted(mode);
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        super.onSupportActionModeFinished(mode);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.id_XX101) {
            setTitle("Dashboard");
            switchFragment(new HomeFragmentContoller());
        }else if (id == R.id.id_XX102) {
            setTitle("News");
            switchFragment(new ManagesNews());
        }else if (id == R.id.id_XX103) {
            setTitle("Events");
            switchFragment(new EventsHandler());
        }else if (id == R.id.id_Vedio) {
            setTitle("Video");
            switchFragment(new Videos());
        }
        else if (id == R.id.id_quiz) {
            setTitle("Quiz");
            switchFragment(new Quiz());
        }else if (id == R.id.id_gallery) {
            setTitle("Gallery");
            switchFragment(new Gallery());
        }else if (id == R.id.id_XX104) {
            setTitle("Holidays");
            switchFragment(new FragmentHolidays());
        }else if (id == R.id.id_XX105) {
            setTitle("Exam dates");
            switchFragment(new FragmentExamDates());
        }/*else if (id == R.id.id_XX106) {
            setTitle("Result");
            switchFragment(new SchoolResultsManager());
        }*/else if (id == R.id.id_XX107) {
            setTitle("Leave Application");
            switchFragment(new Fragment_ApplicationLeave());
        }else if (id == R.id.id_XX108) {
            setTitle("Attendance");
            switchFragment(new FragmentSchool_Attendacne());
        }/*else if (id == R.id.id_XX1099) {
            setTitle("Homework");
            switchFragment(new FragmentSchool_Homework());
        }*/else if (id == R.id.id_XX10998) {
            setTitle("Time Table");
            switchFragment(new FragmentSchool_Timetable());
        }else if (id == R.id.id_XXX101) {
            setTitle("Know Your Teacher");
            switchFragment(new FragmentSchool_KnowTeacher());
        }else if (id == R.id.id_XX109) {
            setTitle("Chat");
            switchFragment(new ChatHandlerManager());
        }/*else if (id == R.id.id_XXX102) {
            setTitle("Fees");
            switchFragment(new feeManager());
        }*/else if (id == R.id.id_XXY_101) {
            setTitle("Complain");
            switchFragment(new StudentComplain());
        }else if (id == R.id.id_XX1010) {
            prefManager.setChecklogindata("");
            prefManager.setCheckUser_id("");
            finish();
            Intent i = new Intent(DrawerActivity.this, LoginSlider.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment).addToBackStack(null).commit();
    }


}
