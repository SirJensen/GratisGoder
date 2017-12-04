package com.example.simon.gratisgoder.HelpClass;

/**
 * Created by Tobias on 04-12-2017.
 */

public class Result {


    private String titel,sted,adresse,img,beskrivelse;



    public Result(String titel, String sted, String adresse, String img, String beskrivelse){

        this.titel = titel;
        this.sted = sted;
        this.adresse = adresse;
        this.img = img;
        this.beskrivelse = beskrivelse;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getSted() {
        return sted;
    }

    public void setSted(String sted) {
        this.sted = sted;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

}
