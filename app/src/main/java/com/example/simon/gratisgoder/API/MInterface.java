package com.example.simon.gratisgoder.API;

import com.example.simon.gratisgoder.DataFromDB.Articles;
import com.example.simon.gratisgoder.DataFromDB.Rating;
import com.example.simon.gratisgoder.DataFromDB.RatingList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Tobias on 30-10-2017.
 */

    public interface MInterface {


    @GET("/images/GetInfoFromDB.php")
    Call<Articles> getOplevelser();

    @GET("/images/getAVG.php")
    Call<RatingList> getRating();

    @FormUrlEncoded
    @POST("/images/insert.php")
    Call<String> insertToAVG(@Field("titel") String first, @Field("rating") String last);
    }




