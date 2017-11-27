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

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


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

    Button createButton;
    String adresse = "";
    String sted = "";
    int i = -1;

    MInterface api;
    Call<String> call;

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
                insertValues(headline, place.getName().toString());

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


        createButton = (Button) findViewById(R.id.button2);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean b4 = checkRadioButtons();
                Boolean b1 = checkValues((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input), "Adresse*");
                Boolean b2 = checkValues(headline, "Overskrift*");
                Boolean b3 = checkValues(description, "Beskrivelse*");

                if (b1 && b2 && b3 && b4) {
                    Context context = getApplicationContext();
                    CharSequence text = "Hello toast!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    RadioButton radioButton = (RadioButton) findViewById(i);
                    sted = radioButton.getText().toString();

                    api = Service.createService(MInterface.class);
                    call = api.setNewOP(
                            description.getText().toString(),
                            sted,
                            adresse,
                            "test",
                            headline.getText().toString());
                    /*call = api.setNewOP(
                            "test",
                            "test",
                            "test",
                            "test",
                            "test");*/

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Succes!",
                                        Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "No Response",
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            toast();
                        }

                    });
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


            private boolean checkValues(EditText editText, String s) {
                if (editText.getText().toString().equals("") || editText.getText().toString().equals(s)) {

                    editText.setBackgroundResource(R.drawable.edittext_outline);
                    return false;
                } else {
                    editText.setBackgroundResource(0);
                    return true;
                }
            }

            private boolean checkRadioButtons() {
                if (i == -1) {
                    linearLayout.setBackgroundResource(R.drawable.edittext_outline);
                    return false;
                } else {
                    linearLayout.setBackgroundResource(0);
                    return true;
                }
            }

            private void insertValues(EditText editText, String s) {
                if (editText.getText().toString().equals("") || editText.getText().toString().equals(s)) {
                    editText.setText(s);
                }
            }

            private void toast() {
                Context context = getApplicationContext();
                CharSequence text = "Udfyld Alle Felter Med *";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


}
