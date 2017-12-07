package com.example.simon.gratisgoder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simon.gratisgoder.API.MInterface;
import com.example.simon.gratisgoder.API.Service;
import com.example.simon.gratisgoder.DataFromDB.Articles;
import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
import com.example.simon.gratisgoder.HelpClass.CustomListAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tobias on 17-11-2017.
 */

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {


    private GoogleMap mMap;
    private GoogleMap googleMap;

    MInterface api;
    Call<Articles> call;
    Articles oplevelser = new Articles();
    List<Oplevelser> adresser ;
    private Map<Marker, Oplevelser> markersMap = new HashMap<Marker, Oplevelser>();
    LatLngBounds.Builder builder ;



    SupportMapFragment mapFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;
    }



    @Override
    public boolean onMarkerClick(Marker marker) {

        Oplevelser dataFromMarker = markersMap.get(marker);
        Bundle bundle = new Bundle();

        bundle.putString("Titel",dataFromMarker.getTitel());
        bundle.putString("Sted",dataFromMarker.getSted());
        bundle.putString("Adresse",dataFromMarker.getAdresse());
        bundle.putString("Image",dataFromMarker.getImage());
        bundle.putString("Beskrivelse",dataFromMarker.getBeskrivelse());
        Intent appInfo = new Intent(getActivity(), ListViewActivity.class);
        appInfo.putExtras(bundle);
        startActivity(appInfo);




        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);



        api = Service.createService(MInterface.class);

        call = api.getOplevelser();

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                 builder = new LatLngBounds.Builder();
                if(response.isSuccessful()) {
                    oplevelser = response.body();
                    adresser = oplevelser.getOplevelser();

                    for (int i = 0; i<5;i++){
                        LatLng address = getLocationFromAddress(adresser.get(i).getAdresse()) ;

                        Marker ma = googleMap.addMarker(new MarkerOptions().position(address));


                        if(address!=null) {
                           // mMap.addMarker(new MarkerOptions().position(address).title(adresser.get(i).getTitel()));

                            Marker marker = mMap.addMarker(new MarkerOptions().position(address).title(adresser.get(i).getTitel()));
                            markersMap.put(marker, adresser.get(i));
                            builder.include(marker.getPosition());
                            LatLngBounds bounds = builder.build();
                            int padding = 250; // offset from edges of the map in pixels
                            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                            //mMap.moveCamera(cu);
                            mMap.animateCamera(cu);
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

            }
        });


      ;
        mMap.setOnMarkerClickListener(this);

    }

    public LatLng getLocationFromAddress( String strAddress)
    {


       Geocoder geoCoder = new Geocoder(getContext());

        LatLng address = null ;

        try {
            List<Address> addresses = geoCoder.getFromLocationName(strAddress, 1);
            if (addresses.size() >  0) {
                double latitude = addresses.get(0).getLatitude();
                double longtitude = addresses.get(0).getLongitude();
                address = new LatLng(latitude,longtitude);
            }

        } catch (IOException e) { // TODO Auto-generated catch block
            e.printStackTrace(); }
        return address;

    }


}


