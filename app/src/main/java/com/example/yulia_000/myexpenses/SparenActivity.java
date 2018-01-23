package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.repo.SaveupRepo;
import com.example.yulia_000.myexpenses.data.model.Saveup;

/**
 * Created by Marija on 13.01.2018.
 */


public class SparenActivity extends Activity {

    EditText txtBetrag;
    EditText txtDatum;
    EditText txtBezeichnung;
    Button btnOK;
    String list;

/*  Button btnCancel;
    Button btnReg;*/

    public static final String MSG = "MSG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sparen_hinzu_activity);
        txtBetrag=(EditText)this.findViewById(R.id.txtBetrag);
        txtDatum=(EditText)this.findViewById(R.id.txtDatum);
        txtBezeichnung=(EditText)this.findViewById(R.id.txtBez);
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        String betrag = incomingIntent.getStringExtra("betrag");
        String bez = incomingIntent.getStringExtra("bez");
         list = incomingIntent.getStringExtra("list");
        if(bez!=null)
            txtBezeichnung.setText( bez );
        if(betrag!=null)
            txtBetrag.setText( betrag );
        if(date!= null)
            txtDatum.setText(date);


        txtDatum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SparenActivity.this, CalenderActivity.class);
                intent.putExtra("class", "sparen");
                intent.putExtra("betrag", txtBetrag.getText().toString());
                intent.putExtra("bez", txtBezeichnung.getText().toString());
                startActivity(intent);
            }

        });

        btnOK=(Button)this.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
                String stringBetrag=txtBetrag.getText().toString();
                String stringBez=txtBezeichnung.getText().toString();
                String stringDate = txtDatum.getText().toString();

                @Override
                public void onClick(View view){

                    Log.i("stringBetrag", stringBetrag);
                    Log.i("stringBez", stringBez);
                    Log.i("stringDate", stringDate);


                        if( TextUtils.isEmpty(txtBetrag.getText()) ||
                                TextUtils.isEmpty(txtBezeichnung.getText()) ||
                                TextUtils.isEmpty(txtDatum.getText())) {
                            txtBezeichnung.setError( "Bitte füllen Sie dieses Feld aus!" );
                            txtBetrag.setError( "Bitte füllen Sie dieses Feld aus!" );
                            txtDatum.setError( "Bitte füllen Sie dieses Feld aus!" );
                        }else{

                            SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

                            SaveupRepo saveupRepo = new SaveupRepo();
                            Saveup saveup = new Saveup();
                            saveup.setID(null);
                            saveup.setUserID(sharedpreferences.getInt("userId", 0));
                            saveup.setSaveupDescription(stringBez);
                            saveup.setSaveupAmount("+" + stringBetrag);
                            saveup.setSaveupDate(stringDate);
                            saveupRepo.insert(saveup);

                            Toast.makeText(SparenActivity.this, "Ausgabe erfolgreich eingetragen!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SparenActivity.this, ListActivity.class);
                            startActivity(intent);
                        }

//                        else{
//                            if(stringBetrag == ""){
//                                txtBetrag.setError( "Bitte füllen Sie dieses Feld aus!" );
//                            }else if(stringBez ==""){
//                                txtBezeichnung.setError( "Bitte füllen Sie dieses Feld aus!" );
//                            }else if(stringDate != ""){
//                                txtDatum.setError( "Bitte füllen Sie dieses Feld aus!" );
//                            }
//
//                        }
                    }


               /* float betrag=Float.valueOf(stringBetrag);
                //   if((txtUserName.getText().toString()).equals(txtPassword.getText().toString())){
                if(stringBez!=null && betrag>0 ){
                    Toast.makeText(SparenActivity.this, "Gespeichert",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SparenActivity.this, ListActivity.class);
                    intent.putExtra("betrag", stringBetrag);
                    intent.putExtra("bez", stringBez);
                    if(list!=null)
                        intent.putExtra("list", list.toString());
                    startActivity(intent);
                } else{
                    Toast.makeText(SparenActivity.this, "Error",Toast.LENGTH_LONG).show();
                }*/
        });
    }




}

