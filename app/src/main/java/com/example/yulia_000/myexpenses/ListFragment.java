package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 11.01.2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListFragment extends Fragment{

    public ListFragment() {   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Menü bekannt geben, dadurch kann unser Fragment Menü-Events verarbeiten
        setHasOptionsMenu(true);
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
        if (id == R.id.action_daten_aktualisieren) {
            Toast.makeText(getActivity(), "Hallo!!!", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.action_msg) {
            Toast.makeText(getActivity(), "I am here!!!", Toast.LENGTH_LONG).show();
            return true;
        }
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



        String [] listeArray = {
                "",
                "Laptop",
                "Urlaub",
                "Tasche",
                "Handy"
        };

        List<String> aktienListe = new ArrayList<>(Arrays.asList(listeArray));

        ArrayAdapter <String> aktienlisteAdapter; // Beispieldaten in einer ArrayList
        aktienlisteAdapter = new ArrayAdapter<String>(
                getActivity(), // Die aktuelle Umgebung (diese Activity)
                R.layout.list_item, // ID der XML-Layout Datei
                R.id.list_item, // ID des TextViews
                aktienListe);

        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        ListView aktienlisteListView = (ListView) rootView.findViewById(R.id.listview_liste);
        aktienlisteListView.setAdapter(aktienlisteAdapter);

        return rootView;

        //return inflater.inflate(R.layout.fragment_main, container, false);

    }

}