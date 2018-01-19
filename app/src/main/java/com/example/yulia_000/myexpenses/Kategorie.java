package com.example.yulia_000.myexpenses;

import java.util.ArrayList;

/**
 * Created by Marija on 18.01.2018.
 */

public class Kategorie {

    private String name;
    private float betrag;
    private ArrayList<Kategorie> kategories=new ArrayList<Kategorie>();

    public Kategorie(String name, float betrag){
        setName(name);
        setBetrag(betrag);

    }

    public void setName(String n){
        this.name=n;
    }
    public String getName(){
        return this.name;
    }

    public void setBetrag(float b){
        this.betrag=b;
    }
    public float getBetrag(){
        return this.betrag;
    }

    public void addIntoList(Kategorie kat){
        if(kategories.size()>0){
            for(Kategorie k:kategories){
                if(k.getName().equals( kat.getName() )){
                    kat.setBetrag( kat.getBetrag()+k.getBetrag() );
                }else{kategories.add(kat); return;}
            }
        }else{kategories.add(kat);}
    }

    public ArrayList<Kategorie> getList(){
        return kategories;
    }
}
