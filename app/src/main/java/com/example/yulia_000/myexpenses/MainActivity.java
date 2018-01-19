package com.example.yulia_000.myexpenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        theDate = (TextView)findViewById(R.id.txtDate);
        // txtKategorien = ((Spinner)findViewById(R.id.SpinnerFeedbackType));
        spinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
        txtBezeichung = (TextView)findViewById(R.id.txtBezeichungKategorien);
        txtBetrag = (TextView)findViewById(R.id.txtEuroKategorien);
        txtDate = (TextView)findViewById(R.id.txtDate);
        btnOkKategorie = (Button)findViewById(R.id.btnKategorienOk);
        btnAbbrechenKategorie = (Button)findViewById(R.id.btnAbbrechen);

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

       // Calendar c = Calendar.getInstance();
       // int mYear = c.get(Calendar.YEAR);
       // int mMonth = c.get(Calendar.MONTH);
       // int mDay = c.get(Calendar.DAY_OF_MONTH);
       // theDate.setText(mDay+"/"+(mMonth+1)+"/"+mYear);




        btnOkKategorie.setOnClickListener(new OnClickListener(){
           // String stringKategorie =
            String stringBezeichung = txtBezeichung.getText().toString();
            String stringBetrag = txtBetrag.getText().toString();
            String stringDate = txtDate.getText().toString();


            @Override
            public void onClick(View view){

                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

                EntryRepo entryRepo = new EntryRepo();
                Entry entry = new Entry();
                entry.setID(null);
                entry.setUserID(sharedpreferences.getInt("userId",0));
                entry.setKategory(kategoryText);
                entry.setDescription(txtBezeichung.getText().toString());
                entry.setAmount(txtBetrag.getText().toString());
                entry.setDate(stringDate);
                entryRepo.insert(entry);

                Toast.makeText(MainActivity.this, "Ausgabe erfolgreich eingetragen!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(intent);
            }
        });

       theDate.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

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
