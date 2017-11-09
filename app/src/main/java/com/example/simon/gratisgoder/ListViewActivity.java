package com.example.simon.gratisgoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simon.gratisgoder.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Tobias on 09-11-2017.
 */

public class ListViewActivity extends AppCompatActivity {

    ImageView image ;
    TextView titelTxt,stedTxt,adresseTxt,beskivTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oplevelse_activity);

        Bundle extras = getIntent().getExtras();
        String titel = extras.getString("Titel");
        String sted = extras.getString("Sted");
        String adresse = extras.getString("Adresse");
        String img = extras.getString("Image");
        String beskrivelse = extras.getString("Beskrivelse");


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



    }
}

