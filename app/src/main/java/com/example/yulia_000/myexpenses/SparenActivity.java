package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                String stringBetrag=txtBetrag.getText().toString();
                String stringBez=txtBezeichnung.getText().toString();
                float betrag=Float.valueOf(stringBetrag);
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
                }

            }
        });
    }




}

