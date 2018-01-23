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

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.repo.SaveupRepo;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        UserRepo userRepo = new UserRepo();
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        User tmpuser = userRepo.getUserByName(sharedpreferences.getString("name",""));

        SaveupRepo saveupRepo = new SaveupRepo();


//        entryRepo.delete();
        //       SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        List<Saveup> saveups =  saveupRepo.getList(sharedpreferences.getInt("userId",0));

        saveup_listing = new ArrayList<String>();

        for (Saveup saveup : saveups){

          //  addToList(saveup.getSaveupDescription(), saveup.getSaveupAmount());


            Log.d("getID",saveup.getID()+"");
            Log.d("getUserID",saveup.getUserID()+"");
            Log.d("getAmount",saveup.getSaveupAmount()+"");
            Log.d("getDate",saveup.getSaveupDate()+"");
            Log.d("getDescription",saveup.getSaveupDescription()+"");




            saveup_listing.add(
                    saveup.getSaveupDescription()+", "+saveup.getSaveupAmount()+" €"
            );
        }

        lv = (ListView) findViewById(R.id.listview_liste);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                saveup_listing );

        lv.setAdapter(arrayAdapter);
        registerForContextMenu(lv);


        btnHinzu=(ImageButton)this.findViewById(R.id.hinzuImageButton);

   /*     btnHinzu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListActivity.this, SparenActivity.class);
                startActivity(intent);

            }
        });*/

        btnZurueck=(ImageButton)this.findViewById(R.id.sparenImageButton);

/*        btnZurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListActivity.this, GeldZurueckActivity.class);
                startActivity(intent);

            }
        });*/

        /*Intent intent = getIntent();
        betrag = intent.getStringExtra( "betrag" );
        bez = intent.getStringExtra( "bez" );
        Log.i("MAMAMAaaa", betrag+"  "+bez);
        if(betrag!=null && bez!=null)
            addToList(betrag,bez);
*/

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


    public void addToList(String name, String bez){
//        Log.i("addToList", name+", "+bez);
//        list.add(name+","+bez);
//
//       // liste.setList( list );
//
//        if (name != null && bez != null) {
//            Log.i("MAMAMAaaa", "GA");
//        Bundle bundle=new Bundle();
//        bundle.putString("name", name);
//        bundle.putString("bez", bez);
//        //set Fragmentclass Arguments
//
//        fragobj.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment, fragobj).commit();
//
//        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

/*    public void sparenHinzu(View view){
        Intent intent = new Intent(ListActivity.this, SparenActivity.class);
        startActivity(intent);
    }*/
}