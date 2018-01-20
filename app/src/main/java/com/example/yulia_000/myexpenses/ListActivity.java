package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 11.01.2018.
 */

import android.content.Intent;
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
import android.widget.ImageButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ImageButton btnHinzu;
    private ImageButton btnZurueck;
    private String betrag,bez;
    private ListFragment liste=new ListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

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

        Intent intent = getIntent();
        betrag = intent.getStringExtra( "betrag" );
        bez = intent.getStringExtra( "bez" );
        Log.i("MAMAMAaaa", betrag+"  "+bez);
        if(betrag!=null && bez!=null)
            addToList(betrag,bez);


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
        ArrayList<String>list=new ArrayList <>(  );
        list.add(name+","+bez);

       // liste.setList( list );

        if (name != null && bez != null) {
            Log.i("MAMAMAaaa", "GA");
        Bundle bundle=new Bundle();
        bundle.putString("name", name);
        bundle.putString("bez", bez);
        //set Fragmentclass Arguments
        Fragment fragobj=new ListFragment();
        fragobj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragobj).commit();

        }

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