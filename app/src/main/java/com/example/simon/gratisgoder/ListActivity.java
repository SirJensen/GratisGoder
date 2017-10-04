package com.example.simon.gratisgoder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Button MapButton = (Button) findViewById(R.id.map_button);

        MapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent getListIntent = new Intent(ListActivity.this, MapsActivity.class);
                ListActivity.this.startActivity(getListIntent);
            }
        });
    }

}
