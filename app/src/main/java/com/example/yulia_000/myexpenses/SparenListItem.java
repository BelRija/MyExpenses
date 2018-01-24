package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 20.01.2018.
 */

public class SparenListItem {

    private String bezeichnung;
    private float betrag;

    public SparenListItem(String bezeichnung, float betrag){

        if(bezeichnung!=null && betrag!=0){
            setBetrag( betrag );
            setBezeichnung( bezeichnung );
        }
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public float getBetrag() {
        return betrag;
    }

    public void setBetrag(float betrag) {
        this.betrag = betrag;
    }
}
