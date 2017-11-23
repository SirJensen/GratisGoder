package com.example.simon.gratisgoder.HelpClass;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
import com.example.simon.gratisgoder.ListFragment;
import com.example.simon.gratisgoder.R;
import com.example.simon.gratisgoder.TabbedActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomDialogClass extends Dialog {

    public Activity c;
    public Dialog d;
    public CheckBox nordjyl, sydSj, born, midtSj, fyn, ostJyl, vestJyl, storKbh, midtJyl;

    Button sog;
    ListView test ;
    List<Oplevelser> h = new ArrayList<>();
    private boolean flagCheckedNordjyl = false;
    private boolean flagNoChecked  = false;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        nordjyl = (CheckBox) findViewById(R.id.nordjyl);
        sydSj  = (CheckBox) findViewById(R.id.sydsj);
        born = (CheckBox) findViewById(R.id.born);
        midtSj = (CheckBox) findViewById(R.id.midtsj);
        fyn = (CheckBox) findViewById(R.id.fyn);
        ostJyl = (CheckBox) findViewById(R.id.ostjyl);
        vestJyl = (CheckBox) findViewById(R.id.vestjyl);
        storKbh = (CheckBox) findViewById(R.id.kbh);
        midtJyl = (CheckBox) findViewById(R.id.midtjyl);
        CheckBox [] checkButtons = {nordjyl, sydSj, born, midtSj, fyn, ostJyl, vestJyl, storKbh, midtJyl};
        sog = (Button) findViewById(R.id.sog);
        sog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0 ; i < checkButtons.length; i++){
                    if(checkButtons[i].isChecked()){
                        List<Oplevelser> addToAdapter = ListFragment.getListe(checkButtons[i].getText().toString());
                        if(addToAdapter!= null)
                      h.addAll (addToAdapter);
                    }
                }
                    ListFragment.myAdapter.clear();
                        ListFragment.myAdapter.addAll(h);
                   ListFragment.myAdapter.notifyDataSetChanged();
                dismiss();

                }

        });

    }


}
