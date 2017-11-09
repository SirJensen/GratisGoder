package com.example.simon.gratisgoder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.simon.gratisgoder.API.MInterface;
import com.example.simon.gratisgoder.API.Service;
import com.example.simon.gratisgoder.DataFromDB.Articles;
import com.example.simon.gratisgoder.HelpClass.CustomListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tobias on 09-11-2017.
 */

public class ListFragment extends Fragment {

    ListView listView ;
    MInterface api;
    Call<Articles> call;
    Articles oplevelser = new Articles();
    CustomListAdapter myAdapter;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_list, container, false);

        listView = (ListView)rootView.findViewById(R.id.list);

        api = Service.createService(MInterface.class);

        call = api.getOplevelser();

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if(response.isSuccessful()) {
                    oplevelser = response.body();
                    myAdapter =  new CustomListAdapter(getActivity(),oplevelser.getOplevelser());
                    listView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

            }
        });


        return rootView;
    }
}