package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 11.01.2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.repo.SaveupRepo;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ImageButton btnHinzu;
    private ImageButton btnZurueck;
    private String bez,date;
    private double betrag = 0;
    double bb;
    private Fragment liste=new ListFragment();
    Fragment fragobj=new ListFragment();
    private ArrayList<String>list=new ArrayList <>(  );
    private List<String> saveup_listing;
    private ListView lv;
    private TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        btnHinzu=(ImageButton)this.findViewById(R.id.hinzuImageButton);
        btnZurueck=(ImageButton)this.findViewById(R.id.sparenImageButton);
        txtLabel=(TextView)this.findViewById(R.id.textViewLabel);

        UserRepo userRepo = new UserRepo();
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        User tmpuser = userRepo.getUserByName(sharedpreferences.getString("name",""));

        SaveupRepo saveupRepo = new SaveupRepo();

        List<Saveup> saveups =  saveupRepo.getList(sharedpreferences.getInt("userId",0));

        saveup_listing = new ArrayList<String>();

        float summe=0;
        DecimalFormat df = new DecimalFormat("0.00");
        float tmpbetrag=0;

        for (Saveup saveup : saveups){

            Log.d("getID",saveup.getID()+"");
            Log.d("getUserID",saveup.getUserID()+"");
            Log.d("getAmount",saveup.getSaveupAmount()+"");
            Log.d("getDate",saveup.getSaveupDate()+"");
            Log.d("getDescription",saveup.getSaveupDescription()+"");

            saveup_listing.add(
                    saveup.getSaveupDate()+": "+saveup.getSaveupAmount()+" €"+" | "+ saveup.getSaveupDescription()
            );

            char flag=saveup.getSaveupAmount().charAt( 0 ); // + or -
            if(flag=='+'){
                tmpbetrag = Float.valueOf(saveup.getSaveupAmount().substring( 1 ));
                summe+=tmpbetrag;
            }else{

                tmpbetrag = Float.valueOf(saveup.getSaveupAmount().substring( 1 ));
                summe-=tmpbetrag;
            }

        }
        txtLabel.setText( txtLabel.getText()+""+df.format(summe)+"€" );

       ArrayList<String> saveup_list = new ArrayList<String>();
       for(int i=saveup_listing.size()-1;i>=0;i--){
           saveup_list.add(saveup_listing.get(i));
       }

        lv = (ListView) findViewById(R.id.listview_liste);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                saveup_list );

        lv.setAdapter(arrayAdapter);
        registerForContextMenu(lv);
    }

    public void setBetrag(double b){
        this.bb=b;
    }

    public double getBetrag(){
        return this.bb;
    }

    public void btnHinzu(View view){
        Intent intent = new Intent(ListActivity.this, SparenActivity.class);
        startActivity(intent);
    }
    public void btnZurueck(View view){
        Intent intent = new Intent(ListActivity.this, GeldZurueckActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu( menu,v,menuInfo );
        menu.setHeaderTitle( "Aktion auswählen" );
        menu.add( 0,v.getId(),0,"Bearbeiten" );
        menu.add( 0,v.getId(),0,"Löschen" );

    }

}