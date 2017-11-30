package com.example.simon.gratisgoder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.simon.gratisgoder.API.MInterface;
import com.example.simon.gratisgoder.API.Service;
import com.example.simon.gratisgoder.DataFromDB.Articles;
import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
import com.example.simon.gratisgoder.HelpClass.CustomDialogClass;
import com.example.simon.gratisgoder.HelpClass.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tobias on 09-11-2017.
 */

public class ListFragment extends Fragment {

    public static ListView listView;
    MInterface api;
    Call<Articles> call;
    Articles oplevelser = new Articles();
    public static CustomListAdapter myAdapter;
    public static List<Oplevelser> alle, nordjyl, sydSj, born, midtSj, fyn, ostJyl, vestJyl, storKbh, midtJyl,sydJyl,nordSj,vestSj;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_list, container, false);


        listView = rootView.findViewById(R.id.list);

        nordjyl = new ArrayList<>();
        sydSj = new ArrayList<>();
        born = new ArrayList<>();
        midtSj = new ArrayList<>();
        fyn = new ArrayList<>();
        ostJyl = new ArrayList<>();
        vestJyl = new ArrayList<>();
        storKbh = new ArrayList<>();
        midtJyl = new ArrayList<>();
        vestSj = new ArrayList<>();
        nordSj = new ArrayList<>();
        sydJyl = new ArrayList<>();



        api = Service.createService(MInterface.class);

        call = api.getOplevelser();

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    oplevelser = response.body();
                    alle = oplevelser.getOplevelser();
                    //myAdapter =  new CustomListAdapter(getActivity(),oplevelser.getOplevelser());
                    //listView.setAdapter(myAdapter);
                    for (Oplevelser alle : alle) {
                        if (alle.getSted().contains("Nordjylland")) {
                            nordjyl.add(alle);
                        }
                        if (alle.getSted().contains("Sydsjælland")) {
                            sydSj.add(alle);
                        }

                        if (alle.getSted().contains("Bornholm")) {
                            born.add(alle);
                        }

                        if (alle.getSted().contains("Midtsjælland")) {
                            midtSj.add(alle);
                        }

                        if (alle.getSted().contains("Fyn")) {
                            fyn.add(alle);
                        }

                        if (alle.getSted().contains("Østjylland")) {
                            ostJyl.add(alle);
                        }

                        if (alle.getSted().contains("Vestjyllad")) {
                            vestJyl.add(alle);
                        }

                        if (alle.getSted().contains("Storkøbenhavn")) {
                            storKbh.add(alle);
                        }

                        if (alle.getSted().contains("Midtjylland")) {
                            midtJyl.add(alle);
                        }
                        if (alle.getSted().contains("Nordsjælland")) {
                            nordSj.add(alle);
                        }
                        if (alle.getSted().contains("Vestsjælland")) {
                            vestSj.add(alle);
                        }
                        if (alle.getSted().contains("Sydjylland")) {
                            sydJyl.add(alle);
                        }


                    }

                    myAdapter = new CustomListAdapter(getActivity(), alle);
                    listView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

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

                //String titel = oplevelser.getOplevelser().get(i).getTitel();
                //String sted = oplevelser.getOplevelser().get(i).getSted();
                //String adresse = oplevelser.getOplevelser().get(i).getAdresse();
                //String img = oplevelser.getOplevelser().get(i).getImage();
                //img = img.replace("\\", "/");
                //String beskrivelse = oplevelser.getOplevelser().get(i).getBeskrivelse();

                Bundle bundle = new Bundle();

                bundle.putString("Titel", titel);
                bundle.putString("Sted", sted);
                bundle.putString("Adresse", adresse);
                bundle.putString("Image", img);
                bundle.putString("Beskrivelse", beskrivelse);
                Intent appInfo = new Intent(getActivity(), ListViewActivity.class);
                appInfo.putExtras(bundle);
                startActivity(appInfo);
            }
        });

        return rootView;
    }

    public static List<Oplevelser> getListe(String sted) {
List<Oplevelser> filterList = null;
        switch (sted) {
            case "NordJylland":
                if(nordjyl != null)
                filterList = nordjyl;
            break;
            case "Sydsjælland":
                if(sydSj != null)
                filterList = sydSj;
            break;
            case "Bornholm":
                if(born != null)
                filterList = born;
                break;
            case "Midtsjælland":
                if(midtSj != null)
                filterList = midtSj;
                break;
            case "Fyn":
                if(fyn != null)
                filterList = fyn;
                break;
            case "Østjylland":
                if(ostJyl != null)
                filterList = ostJyl;
                break;
            case "Vestjyllad":
                if(vestJyl != null)
                filterList = vestJyl;
                break;
            case "København":
                if(storKbh != null)
                filterList = storKbh;
                break;
            case "Midtjylland":
                if(midtJyl != null)
                filterList = midtJyl;
                break;
            case "Nordsjælland":
                if(nordSj != null)
                    filterList = nordSj;
                break;
            case "Vestsjælland":
                if(vestSj != null)
                    filterList = vestSj;
                break;
            case "Sydjylland":
                if(sydJyl != null)
                    filterList = sydJyl;
                break;
            default:
                break;
        }
    return filterList ;
    }


}

