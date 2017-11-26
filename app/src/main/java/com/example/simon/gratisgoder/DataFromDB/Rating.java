package com.example.simon.gratisgoder.DataFromDB;

/**
 * Created by tof on 25/11/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("Titel")
    @Expose
    private String titel;
    @SerializedName("Rating")
    @Expose
    private String rating;

    public Rating(String titel, String rating){
        this.titel = titel;
        this.rating = rating;

    }



    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
