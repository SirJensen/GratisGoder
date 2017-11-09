package com.example.simon.gratisgoder.API;

import com.example.simon.gratisgoder.DataFromDB.Articles;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tobias on 30-10-2017.
 */

    public interface MInterface {


    @GET("/images/GetInfoFromDB.php")
    Call<Articles> getOplevelser();

    }

