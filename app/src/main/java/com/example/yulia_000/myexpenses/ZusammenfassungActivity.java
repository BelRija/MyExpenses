package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulia_000 on 16.01.2018.
 */

public class ZusammenfassungActivity extends Activity {

    private ListView lv;
    private List<String> entry_listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zusammenfassung_layout);

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);


        EntryRepo entryRepo = new EntryRepo();
        List<Entry> entrys =  entryRepo.getList(sharedpreferences.getInt("userId",0));

        entry_listing = new ArrayList<String>();

        for (Entry entry : entrys){

            entry_listing.add(
                    "ID: "+entry.getID()+
                    "\nUser ID: "+entry.getUserID()+
                    "\nBeschreibung: "+entry.getDescription()+
                    "\nKategorie: "+entry.getKategory()+
                    "\nBetrag: "+entry.getAmount()+
                    "\nDatum: "+entry.getDate()
            );


        }

        lv = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                entry_listing );

        lv.setAdapter(arrayAdapter);

    }

    public void filtertList(ArrayList<String>list, String kategorie){

    }
    public List<String> getList(){
        return entry_listing;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ZusammenfassungActivity.this, ZusammenfassungActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
