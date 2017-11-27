package com.example.simon.gratisgoder.DataFromDB;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oplevelser {

    @SerializedName("Beskrivelse")
    @Expose
    private String beskrivelse;
    @SerializedName("Sted")
    @Expose
    private String sted;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("titel")
    @Expose
    private String titel;

    public Oplevelser(String beskrivelse_, String sted_, String adresse_, String image_, String titel_){
        beskrivelse = beskrivelse_;
        sted = sted_;
        adresse = adresse_;
        image = image_;
        titel = titel_;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getSted() {
        return sted;
    }

    public void setSted(String sted) { this.sted = sted; }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

}