package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

        btnOK=(Button)this.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(GeldZurueckActivity.this, Guthaben.class);
                    startActivity(intent);


            }
        });
    }




}

