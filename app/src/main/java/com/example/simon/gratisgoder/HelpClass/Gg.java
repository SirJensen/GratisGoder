package com.example.simon.gratisgoder.HelpClass;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simon.gratisgoder.R;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tobias on 09-11-2017.
 */

public class Gg  extends Activity
        implements ObservableScrollViewCallbacks {
    ImageView image ;
    TextView titelTxt,stedTxt,adresseTxt,beskivTxt;
    ImageButton back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll);

        Bundle extras = getIntent().getExtras();
        String titel = extras.getString("Titel");
        String sted = extras.getString("Sted");
        String adresse = extras.getString("Adresse");
        String img = extras.getString("Image");
        String beskrivelse = extras.getString("Beskrivelse");

        back = (ImageButton) findViewById(R.id.backBTN);

        image = (ImageView) findViewById(R.id.imageView);

        Picasso.with(this).load(img).into(image);

        titelTxt = (TextView) findViewById(R.id.titelText);

        titelTxt.setText(titel);

        stedTxt = (TextView) findViewById(R.id.stedText);

        stedTxt.setText("STED : "+sted);

        adresseTxt = (TextView) findViewById(R.id.adresseText);

        adresseTxt.setText("Adresse : "+adresse);

        beskivTxt = (TextView) findViewById(R.id.beskrivelseText);

        beskivTxt.setText(beskrivelse);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll,
                                boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}