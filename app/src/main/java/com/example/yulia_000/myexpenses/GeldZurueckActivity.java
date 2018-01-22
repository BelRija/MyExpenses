package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.data.model.Saveup;
import com.example.yulia_000.myexpenses.data.repo.SaveupRepo;

/**
 * Created by Marija on 13.01.2018.
 */


public class GeldZurueckActivity extends Activity {

    EditText txtBetrag;
    EditText txtDatum;

    Button btnOK;

/*  Button btnCancel;
    Button btnReg;*/

    public static final String MSG = "MSG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sparen_zurueck_activity);
        txtBetrag=(EditText)this.findViewById(R.id.txtBetrag);
        txtDatum=(EditText)this.findViewById(R.id.txtDatum);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        String betrag = incomingIntent.getStringExtra("betrag");
        if(betrag!=null)
            txtBetrag.setText( betrag );

        if(date!= null)
            txtDatum.setText(date);

        txtDatum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(GeldZurueckActivity.this, CalenderActivity.class);
                intent.putExtra("class", "zurueck");
                intent.putExtra("betrag", txtBetrag.getText().toString());
                startActivity(intent);
            }

        });

        btnOK=(Button)this.findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {

            String stringBetrag=txtBetrag.getText().toString();
            String stringDate = txtDatum.getText().toString();

            @Override
            public void onClick(View view) {

                if( TextUtils.isEmpty(txtBetrag.getText()) ||
                        TextUtils.isEmpty(txtDatum.getText())) {
                    txtBetrag.setError( "Bitte füllen Sie dieses Feld aus!" );
                    txtDatum.setError( "Bitte füllen Sie dieses Feld aus!" );
                }else {

                    SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

                    SaveupRepo saveupRepo = new SaveupRepo();
                    Saveup saveup = new Saveup();
                    saveup.setID(null);
                    saveup.setUserID(sharedpreferences.getInt("userId", 0));
                    saveup.setSaveupDescription("zurückgebucht");
                    saveup.setSaveupAmount(stringBetrag);
                    saveup.setSaveupDate(stringDate);
                    saveupRepo.insert(saveup);

                    Toast.makeText(GeldZurueckActivity.this, "Geld erfolgreich zurückgebucht!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GeldZurueckActivity.this, ListActivity.class);
                    startActivity(intent);
                }
//                    Intent intent = new Intent(GeldZurueckActivity.this, Guthaben.class);
//                    startActivity(intent);

            }
        });
    }




}

