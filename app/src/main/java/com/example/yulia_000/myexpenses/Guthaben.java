package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Marija on 29.12.2017.
 */

public class Guthaben extends Activity{

    private EditText text;
    public static final String MSG = "MSG";
    private String message;
    private TextView txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.guthaben_activity );
        txtUserName=(TextView)this.findViewById(R.id.TextViewName);
        Intent intent = getIntent();
        message = intent.getStringExtra(Guthaben.MSG);
        txtUserName.setText("Hallo "+message.toString()+"!");
    }

    public void setValue(View view){
        text=(EditText) findViewById( R.id.EditText );
        if(text.length() > 1){
            // float value = Float.valueOf(text.getText().toString());
            Intent intent = new Intent(this, DonutActivity.class);
            String message = text.getText().toString();
            intent.putExtra(MSG, message);
            startActivity(intent);
        }else{
            Toast.makeText(Guthaben.this, "Error!",Toast.LENGTH_LONG).show();
        }


        // Toast.makeText(this,text.toString(), Toast.LENGTH_LONG).show();
    }

}
