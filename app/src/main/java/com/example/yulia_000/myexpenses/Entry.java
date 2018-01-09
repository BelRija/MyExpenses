package com.example.yulia_000.myexpenses;

import java.util.Date;

/**
 * Created by Yulia_000 on 09.01.2018.
 */

public class Entry {
    private long id;
    private String login_name;
    private double kontostand;
    private String kategorie;
    private double betrag;
    private Date datum;
    //password


    public long getId() {
        return id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public double getBetrag() {
        return betrag;
    }

    public Date getDatum() {
        return datum;
    }

    private String bezeichnung;

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

}
