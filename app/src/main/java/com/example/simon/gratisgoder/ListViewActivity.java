package com.example.simon.gratisgoder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.simon.gratisgoder.API.MInterface;
import com.example.simon.gratisgoder.API.Service;
import com.example.simon.gratisgoder.DataFromDB.Rating;
import com.example.simon.gratisgoder.DataFromDB.RatingList;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tobias on 09-11-2017.
 */

public class ListViewActivity extends AppCompatActivity {

    ImageView image ;
    AppBarLayout appBar;
    TextView titelTxt,stedTxt,adresseTxt,beskivTxt,textAfterBtn;

    String titel;
    RatingBar ratingBar,ratingBarUsers;
    Button rateBtn;
    String adresse;
    ImageView map ;
    MInterface api;
    List<Rating> rating ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_scrolling);
        setupWindowAnimations();

        Bundle extras = getIntent().getExtras();
         titel = extras.getString("Titel");
       String sted = extras.getString("Sted");
        adresse = extras.getString("Adresse");
        String img = extras.getString("Image");
        String beskrivelse = extras.getString("Beskrivelse");

        ratingBar =  findViewById(R.id.ratingBar);


        ratingBarUsers = findViewById(R.id.ratingBarAllUser);


        rateBtn =  findViewById(R.id.rbtn) ;
        textAfterBtn =  findViewById(R.id.textafterbtn) ;
        Toolbar toolbar =  findViewById(R.id.toolbar);

        appBar = findViewById(R.id.app_bar);

        map =  findViewById(R.id.googleMap);


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

        api = Service.createService(MInterface.class);
        Call<RatingList> call ;
        call = api.getRating();

        call.enqueue(new Callback<RatingList>() {
            @Override
            public void onResponse(Call<RatingList> call, Response<RatingList> response) {
                if (response.isSuccessful()) {
                    rating = response.body().getRating();
                    for(int i=0; i< rating.size();i++){
                        Log.i("hvad er titel",rating.get(i).getTitel());
                        if(rating.get(i).getTitel().equals(titel)){
                            ratingBarUsers.setRating(Float.parseFloat(rating.get(i).getRating()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RatingList> call, Throwable t) {

            }
        });


        toolbar.setTitle(titel);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //   titelTxt = (TextView) findViewById(R.id.titelText);

     //   titelTxt.setText(titel);

        stedTxt = (TextView) findViewById(R.id.stedTxt);

        stedTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.place, 0, 0, 0);

       stedTxt.setText(sted);

    //    adresseTxt = (TextView) findViewById(R.id.adresseText);

     //   adresseTxt.setText("Adresse : "+adresse);

        beskivTxt = (TextView) findViewById(R.id.beskrivelseTxt);

        beskivTxt.setText(beskrivelse);

      rateBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              api = Service.createService(MInterface.class);
              Call<String> call ;

              call = api.insertToAVG(titel,""+(int)ratingBar.getRating());

              call.enqueue(new Callback<String>() {
                  @Override
                  public void onResponse(Call<String> call, Response<String> response) {

                  }

                  @Override
                  public void onFailure(Call<String> call, Throwable t) {

                  }
              });
            rateBtn.setVisibility(View.GONE);
              ratingBar.setIsIndicator(true);
              textAfterBtn.setText("Du har givet "+ ratingBar.getRating()+" stjerner");
              textAfterBtn.setVisibility(View.VISIBLE);
          }
      });
    String urlToImage = getImageFromAddress(adresse);

        Picasso.with(this).load(urlToImage).fit().centerCrop().into(map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String map = "http://maps.google.co.in/maps?q=" + adresse;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(i);
            }
        });
    }


    private void setupWindowAnimations() {
        overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public  String getImageFromAddress(String strAddress)
    {
        Geocoder coder= new Geocoder(this);
        double latitude = 0 ;
        double longtitude  = 0;

        Geocoder geoCoder = new Geocoder(this);
        LatLng address = null ;
        try {
            List<Address> addresses =
                    geoCoder.getFromLocationName(strAddress, 1);
            if (addresses.size() >  0) {
                 latitude = addresses.get(0).getLatitude();
                longtitude = addresses.get(0).getLongitude();
                address = new LatLng(latitude,longtitude);
            }

        } catch (IOException e) { // TODO Auto-generated catch block
            e.printStackTrace(); }
        return "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longtitude + "&zoom=15&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C"+ latitude + "," + longtitude +"&key=AIzaSyAki1uAFT0sfDhm4UvexbkEXtsLgKjElFs";

    }

}

