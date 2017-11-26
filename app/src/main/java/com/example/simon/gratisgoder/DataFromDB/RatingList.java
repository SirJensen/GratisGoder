package com.example.simon.gratisgoder.DataFromDB;

/**
 * Created by tof on 25/11/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingList {

    @SerializedName("rating")
    @Expose
    private List<Rating> rating = null;

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

}