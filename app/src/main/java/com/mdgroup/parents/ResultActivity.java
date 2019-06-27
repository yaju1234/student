package com.mdgroup.parents;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mdgroup.parents.R;
import com.squareup.picasso.Picasso;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelUser;

public class ResultActivity extends AppCompatActivity {

    PrefManager prefManager;
    TextView tv,point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#E6E6E6"));
        }
        prefManager = new PrefManager(ResultActivity.this);
        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        tv=(TextView)findViewById(R.id.na);
        point=(TextView)findViewById(R.id.point);
      //  RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        ImageView img = (ImageView)findViewById(R.id.img1);

        String data = prefManager.getChecklogindata();

        Gson gson = new Gson();
        ModelUser modelstudent = gson.fromJson(data, ModelUser.class);
        tv.setText(modelstudent.getSTUDENT_FULL_NAME());
        //String str_data="Class : "+modelstudent.getCLASSES_NAME().concat(" Section : "+modelstudent.getSECTIONS_NAME());
        //tv_headerScreendetails.setText(str_data);
        Picasso.with(getApplicationContext()).load(modelstudent.getIMAGE()).fit().into(img);

        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        int vv = b.getInt("vv");
       // ratingBar.setRating(score);
        scoreTxtView.setText(String.valueOf(score));
        point.setText(String.valueOf(vv));

       /* if(score == 0){
            img.setImageResource(R.drawable.score_0);
        }else if(score == 1){
            img.setImageResource(R.drawable.score_1);
        }else if(score == 2){
            img.setImageResource(R.drawable.score_2);
        }else if(score == 3){
            img.setImageResource(R.drawable.score_3);
        }else if(score == 4){
            img.setImageResource(R.drawable.score_4);
        }else if(score == 5){
            img.setImageResource(R.drawable.score_5);
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),DrawerActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}
