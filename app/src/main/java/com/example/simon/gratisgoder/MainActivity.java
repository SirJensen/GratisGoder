package com.example.simon.gratisgoder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Tid for splashscreen on start up
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mapsActivitiy = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapsActivitiy);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

}