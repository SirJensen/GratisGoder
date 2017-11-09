package com.example.simon.gratisgoder.DataFromDB;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {

    @SerializedName("oplevelser")
    @Expose
    private List<Oplevelser> oplevelser = null;

    public List<Oplevelser> getOplevelser() {
        return oplevelser;
    }

    public void setOplevelser(List<Oplevelser> oplevelser) {
        this.oplevelser = oplevelser;
    }

}