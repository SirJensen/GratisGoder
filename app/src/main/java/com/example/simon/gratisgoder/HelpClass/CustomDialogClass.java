package com.example.simon.gratisgoder.HelpClass;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
import com.example.simon.gratisgoder.ListFragment;
import com.example.simon.gratisgoder.R;

import java.util.ArrayList;
import java.util.List;

public class CustomDialogClass extends Dialog {

    public Activity c;
    public Dialog d;
    public CheckBox nordjyl, sydSj, born, midtSj, fyn, ostJyl, vestJyl, storKbh, midtJyl,sydJyl,nordSj,vestSj;
    Button sog,clearAll;

    List<Oplevelser> filterList = new ArrayList<>();

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

        SharedPreferences prefs;

        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        nordjyl = (CheckBox) findViewById(R.id.nordjyl);
        sydSj  = (CheckBox) findViewById(R.id.sydsj);
        born = (CheckBox) findViewById(R.id.born);
        midtSj = (CheckBox) findViewById(R.id.midtsj);
        fyn = (CheckBox) findViewById(R.id.fyn);
        ostJyl = (CheckBox) findViewById(R.id.ostjyl);
        vestJyl = (CheckBox) findViewById(R.id.vestjyl);
        storKbh = (CheckBox) findViewById(R.id.kbh);
        midtJyl = (CheckBox) findViewById(R.id.midtjyl);
        sydJyl = (CheckBox) findViewById(R.id.sydjyl);
        nordSj = (CheckBox) findViewById(R.id.nordsj);
        vestSj = (CheckBox) findViewById(R.id.vestsj);

        final CheckBox [] checkButtons = {nordjyl, sydSj, born, midtSj, fyn, ostJyl, vestJyl, storKbh, midtJyl,sydJyl,nordSj,vestSj};
        String [] nameOfCheckBox = {"nordjyl", "sydSj", "born","midtSj", "fyn", "ostJyl", "vestJyl", "storKbh", "midtJyl","sydJyl","nordSj","vestSj"};
        int setAllTrue = 0;
        for(int i = 0 ; i< checkButtons.length;i++){

        if(prefs.getInt(nameOfCheckBox[i], 0) == 1){
            setAllTrue = 1;
        checkButtons[i].setChecked(true);
    }

}
if(setAllTrue == 0){
    setCheckBox(true);
}
        sog = (Button) findViewById(R.id.sog);
        sog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0 ; i < checkButtons.length; i++){
                    if(checkButtons[i].isChecked()){
                        prefs.edit().putInt(nameOfCheckBox[i],1).commit();
                        List<Oplevelser> addToAdapter = ListFragment.getListe(checkButtons[i].getText().toString());
                        if(addToAdapter!= null)
                      filterList.addAll (addToAdapter);

                    }
                    else{
                        prefs.edit().putInt(nameOfCheckBox[i],0).commit();
                    }

                }
                    ListFragment.myAdapter.clear();
                        ListFragment.myAdapter.addAll(filterList);
                   ListFragment.myAdapter.notifyDataSetChanged();
                dismiss();

                }

        });

        clearAll = (Button) findViewById(R.id.clearAll);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheckBox(false);

            }
        });



    }


public void setCheckBox(boolean value){
    nordjyl.setChecked(value);
    sydSj.setChecked(value);
    born.setChecked(value);
    midtSj.setChecked(value);
    fyn.setChecked(value);
    ostJyl.setChecked(value);
    vestJyl.setChecked(value);
    storKbh.setChecked(value);
    midtJyl.setChecked(value);
    sydJyl.setChecked(value);
    nordSj.setChecked(value);
    vestSj.setChecked(value);
}


}
