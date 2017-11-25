package com.example.simon.gratisgoder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simon.gratisgoder.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Tobias on 09-11-2017.
 */

public class ListViewActivity extends AppCompatActivity {

    ImageView image ;
    AppBarLayout appBar;
    TextView titelTxt,stedTxt,adresseTxt,beskivTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_scrolling);

        Bundle extras = getIntent().getExtras();
        String titel = extras.getString("Titel");
        String sted = extras.getString("Sted");
        String adresse = extras.getString("Adresse");
        String img = extras.getString("Image");
        String beskrivelse = extras.getString("Beskrivelse");

        Toolbar toolbar = findViewById(R.id.toolbar);

        appBar = findViewById(R.id.app_bar);
      //  image = (ImageView) findViewById(R.id.imageView);

     //   Picasso.with(this).load(img).into(image);
        Picasso.with(this).load(img).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                appBar.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });



        toolbar.setTitle(titel);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //   titelTxt = (TextView) findViewById(R.id.titelText);

     //   titelTxt.setText(titel);

        stedTxt = findViewById(R.id.stedTxt);

        stedTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.place, 0, 0, 0);

       stedTxt.setText(sted);

    //    adresseTxt = (TextView) findViewById(R.id.adresseText);

     //   adresseTxt.setText("Adresse : "+adresse);

        beskivTxt = findViewById(R.id.beskrivelseTxt);

        beskivTxt.setText(beskrivelse);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

