package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 11.01.2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListFragment extends Fragment{

    private ImageButton btnHinzu;
    private ArrayList<String> list=new ArrayList <String>( );

    public ListFragment() {   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Menü bekannt geben, dadurch kann unser Fragment Menü-Events verarbeiten
        setHasOptionsMenu(true);
        list.add( " " );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_listfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Wir prüfen, ob Menü-Element mit der ID "action_daten_aktualisieren"
        // ausgewählt wurde und geben eine Meldung aus
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    //die Callback-Methode onCreateView(), in der wir das Layout des Fragments laden.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String LOG_TAG = ListFragment.class.getSimpleName();

        Log.v(LOG_TAG, "verbose     - Meldung");
        Log.d(LOG_TAG, "debug       - Meldung");
        Log.i(LOG_TAG, "information - Meldung");
        Log.w(LOG_TAG, "warning     - Meldung");
        Log.e(LOG_TAG, "error       - Meldung");

        String strtext="";
        Log.i("MAMAMA1222", ""+getArguments());

    if(getArguments() !=null)
    {
        strtext=getArguments().getString("name");
        Log.i("MAMAMA1222", strtext+"  " + getArguments().getString("bez"));
      //  setList(strtext,getArguments().getString("bez"));
    }

        Log.i("MAMAMA22", list.toString());

        ArrayAdapter <String> aktienlisteAdapter; // Beispieldaten in einer ArrayList
        aktienlisteAdapter = new ArrayAdapter<String>(
                getActivity(), // Die aktuelle Umgebung (diese Activity)
                R.layout.list_item, // ID der XML-Layout Datei
                R.id.list_item, // ID des TextViews
                list);

        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu( menu,v,menuInfo );
        menu.setHeaderTitle( "Aktion auswählen" );
        menu.add( 0,v.getId(),0,"Bearbeiten" );
        menu.add( 0,v.getId(),0,"Löschen" );

    }


}