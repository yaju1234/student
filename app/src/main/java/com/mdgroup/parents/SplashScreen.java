package com.mdgroup.parents;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mdgroup.parents.R;

/**
 * Gaurav Mangal
 */
public class SplashScreen extends Activity {

    private static final int SPLASH_TIME_OUT = 3000;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                    Intent i = new Intent(SplashScreen.this, WelcomeSlider.class);
                    startActivity(i);
                    finish();
            }
        }, 3000);
    }



}
