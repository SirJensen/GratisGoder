package com.example.simon.gratisgoder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
import com.example.simon.gratisgoder.HelpClass.CustomListAdapter;
import com.example.simon.gratisgoder.HelpClass.DBHandler;
import com.example.simon.gratisgoder.HelpClass.Result;
import com.example.simon.gratisgoder.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tobias on 04-12-2017.
 */

public class VistedActivity extends AppCompatActivity {
    TextView titelTxt,stedTxt,adresseTxt,beskivTxt ;
    DBHandler db;
    AppBarLayout appBar;
     CustomListAdapter myAdapter;
    ListView listView;
    ArrayList<Oplevelser> oplevelser = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DBHandler(this);
        
        listView = findViewById(R.id.list);


    oplevelser = getListAdapter();

        Log.i("je",""+oplevelser);
        myAdapter = new CustomListAdapter(this, oplevelser);
        listView.setAdapter(myAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int i, long l) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(new ContextThemeWrapper(VistedActivity.this, R.style.myDialog));
                builder1.setMessage("Vil du slette "+oplevelser.get(i).getTitel());
                final String delete  = oplevelser.get(i).getTitel();
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ja",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DBHandler database = new DBHandler(getApplicationContext());
                                database.removeSingleContact(delete);
                                myAdapter.clear();
                                myAdapter.addAll(getListAdapter());
                                myAdapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Nej",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String titel = myAdapter.getItem(i).getTitel();
                String sted = myAdapter.getItem(i).getSted();
                String adresse = myAdapter.getItem(i).getAdresse();
                String img = myAdapter.getItem(i).getImage();
                img = img.replace("\\", "/");
                String beskrivelse = myAdapter.getItem(i).getBeskrivelse();


                Bundle bundle = new Bundle();

                bundle.putString("Titel", titel);
                bundle.putString("Sted", sted);
                bundle.putString("Adresse", adresse);
                bundle.putString("Image", img);
                bundle.putString("Beskrivelse", beskrivelse);
                Intent appInfo = new Intent(getApplicationContext(), ListViewActivity.class);
                appInfo.putExtras(bundle);
                startActivity(appInfo);
            }
        });



    }


public ArrayList<Oplevelser> getListAdapter(){
    ArrayList<Result> data = db.getContent();
    ArrayList<Oplevelser> oplevelser = new ArrayList<>() ;

    for(int i = 0 ; i< data.size(); i++){
        Oplevelser op = new Oplevelser();
        op.setTitel(data.get(i).getTitel());
        op.setSted(data.get(i).getSted());
        op.setBeskrivelse(data.get(i).getBeskrivelse());
        op.setAdresse(data.get(i).getAdresse());
        op.setImage(data.get(i).getImg());
        oplevelser.add(op);
    }
    return oplevelser;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}

