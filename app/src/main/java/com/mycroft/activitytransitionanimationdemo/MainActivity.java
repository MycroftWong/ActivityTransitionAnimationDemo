package com.mycroft.activitytransitionanimationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image_view);
    }

    public void other(View view) {
//        startActivity(new Intent(this, OtherActivity.class));

        ActivityCompat.startActivity(this,
                new Intent(this, OtherActivity.class),
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, (Pair<View, String>[]) null).toBundle());
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void transitionOther(View view) {
        ActivityCompat.startActivity(this,
                new Intent(this, OtherActivity.class),
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, mImageView, "image").toBundle());
    }
}
