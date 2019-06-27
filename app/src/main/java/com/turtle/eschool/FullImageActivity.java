package com.turtle.eschool;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        // Selected image id
        String data = getIntent().getExtras().getString("category_id","category_id");

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        Picasso.with(getApplicationContext()).load(data).into(imageView);

    }

}
