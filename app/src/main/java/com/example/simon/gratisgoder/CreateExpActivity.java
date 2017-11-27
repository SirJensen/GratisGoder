package com.example.simon.gratisgoder;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simon.gratisgoder.API.MInterface;
import com.example.simon.gratisgoder.API.Service;
import com.example.simon.gratisgoder.DataFromDB.Articles;
import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateExpActivity extends AppCompatActivity {

    private static final String LOG_TAG = "PlaceSelectionListener";

    RadioGroup radioGroup3;
    RadioGroup radioGroup4;
    PlaceAutocompleteFragment autocompleteFragment;
    LinearLayout linearLayout;
    EditText headline;
    EditText description;

    EditText number;
    EditText website;
    EditText email;
    Button createButton;
    String adresse = "";
    String sted = "";
    int i = -1;

    MInterface api;
    Call<Articles> call;
    Articles oplevelser = new Articles();
    public static List<Oplevelser> alle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createexp);
        final TextView textView = (TextView) findViewById(R.id.textView7);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setText("Adresse*");
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextColor(Color.GRAY);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.i(LOG_TAG, "Place: " + place.getName());
                adresse = place.getAddress().toString();
                insertValues(number, place.getPhoneNumber().toString());
                insertValues(website, place.getWebsiteUri().toString());
                insertValues(headline, place.getName().toString());

                String placeDetailsStr = place.getName() + "\n"
                        + place.getId() + "\n"
                        + place.getLatLng().toString() + "\n"
                        + place.getAddress() + "\n"
                        + place.getAttributions() + "\n"
                        + place.getPhoneNumber() + "\n"
                        + place.getWebsiteUri();
                textView.setText(placeDetailsStr);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(LOG_TAG, "An error occurred: " + status);
            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
        radioGroup3.setOnCheckedChangeListener(listener3);
        radioGroup4.setOnCheckedChangeListener(listener4);
        headline = (EditText) findViewById(R.id.headline);
        description = (EditText) findViewById(R.id.description);

        number = (EditText) findViewById(R.id.number);
        website = (EditText) findViewById(R.id.website);
        email = (EditText) findViewById(R.id.email);



        createButton = (Button) findViewById(R.id.button2);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Boolean b4 = checkRadioButtons();
                    Boolean b1 = checkValues((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input), "Adresse*");
                    Boolean b2 = checkValues(headline, "Overskrift*");
                    Boolean b3 = checkValues(description, "Beskrivelse*");
                    if(b1 && b2 && b3 && b4){
                    Context context = getApplicationContext();
                    CharSequence text = "Hello toast!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    RadioButton radioButton = (RadioButton) findViewById(i);
                    sted = radioButton.getText().toString();


                    final Oplevelser myObject = new Oplevelser(
                            description.getText().toString(),
                            sted,
                            adresse,
                            "",
                            headline.getText().toString());

                        api = Service.createService(MInterface.class);

                        call = api.getOplevelser();

                        call.enqueue(new Callback<Articles>() {
                            @Override
                            public void onResponse(Call<Articles> call, Response<Articles> response) {
                                if (response.isSuccessful()) {
                                    oplevelser = response.body();
                                    alle = oplevelser.getOplevelser();

                                    
                                    number.setText(String.valueOf(alle.size()));

                                    //alle.add(myObject);

                                    //call = api.setOp
                                    //oplevelser.setOplevelser(alle);
                                }
                            }
                            @Override
                            public void onFailure(Call<Articles> call, Throwable t) {
                                toast();
                            }
                        });


                    //List<Oplevelser> oplevelser = articles.getOplevelser();
                    //headline.setText(oplevelser.size());

                    //oplevelser.add(myObject);
                   /*

                    articles.setOplevelser(oplevelser);*/
                }
                else{
                        toast();
                    }
            }
        });

    }

    private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup4.setOnCheckedChangeListener(null);
                radioGroup4.clearCheck();
                radioGroup4.setOnCheckedChangeListener(listener4);
                i = checkedId;
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener4 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup3.setOnCheckedChangeListener(null);
                radioGroup3.clearCheck();
                radioGroup3.setOnCheckedChangeListener(listener3);
                i = checkedId;
            }
        }
    };

    private boolean checkValues(EditText editText, String s){
        if (editText.getText().toString().equals("") || editText.getText().toString().equals(s)){

            editText.setBackgroundResource(R.drawable.edittext_outline);
            return false;
        }
        else {
            editText.setBackgroundResource(0);
            return true;
        }
    }
    private boolean checkRadioButtons(){
        if(i == -1){
            linearLayout.setBackgroundResource(R.drawable.edittext_outline);
            return false;
        }
        else{
            linearLayout.setBackgroundResource(0);
            return true;
        }
    }

    private void insertValues(EditText editText, String s){
        if (editText.getText().toString().equals("") || editText.getText().toString().equals(s)){
            editText.setText(s);
        }
    }

    private void toast(){
        Context context = getApplicationContext();
        CharSequence text = "Udfyld Alle Felter Med *";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
