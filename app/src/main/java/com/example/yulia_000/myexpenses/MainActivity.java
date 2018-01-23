package com.example.yulia_000.myexpenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.data.model.Entry;

import com.example.yulia_000.myexpenses.data.model.User;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView theDate;
    private Spinner spinner;
    private String kategoryText;
    private TextView txtBezeichung;
    private TextView txtBetrag;
    private TextView txtDate;
    private Button btnOkKategorie,btnAbbrechenKategorie;
    private RadioButton einnahme,ausgabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("Ausgabeactivity","IN");

        theDate = (TextView)findViewById(R.id.txtDate);
        // txtKategorien = ((Spinner)findViewById(R.id.SpinnerFeedbackType));
        spinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
        txtBezeichung = (TextView)findViewById(R.id.txtBezeichungKategorien);
        txtBetrag = (TextView)findViewById(R.id.txtEuroKategorien);
        txtDate = (TextView)findViewById(R.id.txtDate);
        btnOkKategorie = (Button)findViewById(R.id.btnKategorienOk);
        btnAbbrechenKategorie = (Button)findViewById(R.id.btnAbbrechen);
        einnahme = (RadioButton)findViewById(R.id.einnahmeButton);
        ausgabe = (RadioButton)findViewById(R.id.ausgabeButton);


        this.btnAbbrechenKategorie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kategorieliste, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(MainActivity.this, item.toString(),
                            Toast.LENGTH_SHORT).show();
                    kategoryText = item.toString();
                }
                Toast.makeText(MainActivity.this, "Selected",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);
        String betrag = incomingIntent.getStringExtra("betrag");
        String bez = incomingIntent.getStringExtra("bez");
        String kategorie = incomingIntent.getStringExtra("kategorie");
        //Log.i("PROVERKA",""+betrag);
        if(bez!=null)
            txtBezeichung.setText( bez );
        if(betrag!=null)
            txtBetrag.setText( betrag );
        if(kategorie!= null && spinner!=null){
            for (int position = 0; position < spinner.getAdapter().getCount(); position++) {
                if(adapter.getItem(position) !=null) {
                    //Log.i("PROVERKA",""+kategorie);
                    if(adapter.getItem(position).equals(kategorie)){
                    //Log.i("PROVERKA",""+kategorie);
                        spinner.setSelection(position);
                        return;
                    }
                }
            }
        }
        String stringDate = txtDate.getText().toString();

        theDate.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                String stringBezeichung = txtBezeichung.getText().toString();
                String stringBetrag = txtBetrag.getText().toString();
                String stringDate = txtDate.getText().toString();
                Intent intent = new Intent(MainActivity.this, CalenderActivity.class);

                intent.putExtra("class", "ausgaben");
                intent.putExtra("betrag", stringBetrag);
                intent.putExtra("bez",stringBezeichung);
                intent.putExtra("kategorie",kategoryText);
                //Log.i("PROVERKA1",""+kategoryText);
                startActivity(intent);
            }
        });

    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        einnahme = (RadioButton)findViewById(R.id.einnahmeButton);
        ausgabe = (RadioButton)findViewById(R.id.ausgabeButton);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ausgabeButton:
                if (checked)
                    ausgabe.setTypeface(null, Typeface.NORMAL);
                    ausgabe.setTypeface(null, Typeface.BOLD);
                Log.i("this.ausgabe.isChecked","-----------------------------------");

                Log.i("this.einnahme.isChecked","Ich bin in minus, wieso auch immer");
                break;
            case R.id.einnahmeButton:
                if (checked)
                    einnahme.setTypeface(null, Typeface.NORMAL);
                    einnahme.setTypeface(null, Typeface.BOLD);
                    Log.i("this.einnahme.isChecked","++++++++++++++++++++++++++++++++++");

                    Log.i("this.einnahme.isChecked","ich bin da");
                    Intent intent = new Intent(MainActivity.this, EinnahmeActivity.class);
                    startActivity(intent);
                break;
        }
    }

    public void btnOK(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String stringDate = txtDate.getText().toString();
        if(txtBezeichung.getText().length()!=0 && txtBetrag.getText().length()!=0 && stringDate.length()!=0){
           // Log.i("PROVERKA",""+txtBezeichung.getText().length()+" | "+txtBetrag.getText().length()+" | "+stringDate.length());


           // if(einnahme.isChecked()==true){
                //EA-Marker!!- Datenbank
                //Summe vergleichen

                UserRepo userRepo = new UserRepo();
                User tmpuser = userRepo.getUserByName(sharedpreferences.getString("name",""));

                    Log.i("PROVERKAbETRAGokB",tmpuser.getCredit()+"");

                EntryRepo entryRepo = new EntryRepo();
                Entry entry = new Entry();
                entry.setID(null);
                entry.setUserID(sharedpreferences.getInt("userId",0));
                entry.setKategory(kategoryText);
                entry.setDescription(txtBezeichung.getText().toString());
                entry.setAmount("-"+txtBetrag.getText().toString());
                entry.setDate(stringDate);
                entryRepo.insert(entry);

                Toast.makeText(MainActivity.this, "Ausgabe erfolgreich eingetragen!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Bitte alle Felder ausfüllen!",Toast.LENGTH_LONG).show();
        }

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}