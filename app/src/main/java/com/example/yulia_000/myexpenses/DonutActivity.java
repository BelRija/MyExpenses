package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 09.01.2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.DonutProgress;
import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.model.Entry;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;

import java.util.List;

public class DonutActivity extends AppCompatActivity {

    private ImageButton btnHinzu;
    private ImageButton btnSparen;
    private DonutProgress donutProgress;
    private EditText text;
    private String message;
    int betrag = 0;
    int bb;
    float value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_donut );

        text=(EditText) findViewById( R.id.EditText );
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        btnHinzu=(ImageButton)this.findViewById(R.id.hinzuImageButton);
        btnSparen=(ImageButton)this.findViewById(R.id.sparenImageButton);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        message = intent.getStringExtra(Guthaben.MSG);
        if(message == null){
            message = "2000";
        }

        try {
            betrag = Integer.parseInt(message);
        } catch(NumberFormatException nfe) {
        }

        EntryRepo entryRepo = new EntryRepo();

//        entryRepo.delete();
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        List<Entry> entrys =  entryRepo.getList(sharedpreferences.getInt("userId",0));
        Log.d("MyApp","I am here");
        Log.d("MyApp",entrys.toString());

        int entryBetrag = 0;
        for (Entry entry : entrys){
            try {
                entryBetrag = Integer.parseInt(entry.getAmount());
            } catch(NumberFormatException nfe) {
            }
            Log.d("getID",entry.getID()+"");
            Log.d("getUserID",entry.getUserID()+"");
            Log.d("getAmount",entry.getAmount()+"");
            Log.d("getDate",entry.getDate()+"");
            Log.d("getKategory",entry.getKategory()+"");
            Log.d("getDescription",entry.getDescription()+"");

            betrag =  betrag - entryBetrag;
            setBetrag(betrag);
        }

        Toast.makeText(this,"MSG: "+message, Toast.LENGTH_LONG).show();



        setMax(betrag+"");

        btnHinzu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSparen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonutActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        this.donutProgress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonutActivity.this, PieChartActivity.class);
                String message = Float.toString(value);
                intent.putExtra("betrag", DonutActivity.this.getBetrag()+"");
                Log.i("HAHAHA1",DonutActivity.this.getBetrag()+"");
                startActivity(intent);
            }
        });

    }

    public void setBetrag(int b){
        this.bb=b;
    }

    public int getBetrag(){
        return this.bb;
    }

    public void setDonutProgress(DonutProgress donutProgress) {
        this.donutProgress = donutProgress;
    }
    public DonutProgress getDonutProgress() {
        return this.donutProgress;
    }
    float max;
    public void setMax(String msg){

        float value = Float.valueOf(msg);
        // Toast.makeText(this,text.toString(), Toast.LENGTH_LONG).show();
        this.donutProgress.setMax( value );
         max=this.donutProgress.getMax();
        Toast.makeText(this,"max: "+Float.toString( max ), Toast.LENGTH_LONG).show();
        this.donutProgress.setText( Float.toString( value )+ "€" );
        donutProgress.setTextColor( Color.rgb( 2, 204, 2 ));

    }
    public float getMax(){
        float value = Float.valueOf(max);
        return value;
    }

    public void setValue(View view){
        value = Float.valueOf(message);
        // Toast.makeText(this,text.toString(), Toast.LENGTH_LONG).show();
        float max=this.donutProgress.getMax();
        text=(EditText) findViewById( R.id.EditText1 );
        float val = Float.valueOf(text.getText().toString());

        float valueP = (val*100)/max;
        this.donutProgress.setProgress( val );
        value-=val;
        if(value>=0){
            this.donutProgress.setText( Float.toString( value )+ "€" );
            Toast.makeText(this,Float.toString( valueP )+"%", Toast.LENGTH_LONG).show();

            if(valueP >= 75.0){
                donutProgress.setFinishedStrokeColor( Color.rgb(214, 17, 17));
                donutProgress.setTextColor( Color.rgb(214, 17, 17));
            }else  if(valueP >= 25.0 && valueP < 50.0){
                donutProgress.setFinishedStrokeColor( Color.rgb(255, 247, 0));
                donutProgress.setTextColor( Color.rgb(255, 247, 0));
            }else  if(valueP >= 50.0 && valueP < 75.0){
                donutProgress.setFinishedStrokeColor( Color.rgb(255, 102, 0 ));
                donutProgress.setTextColor( Color.rgb(255, 102, 0 ));
            }else{
                donutProgress.setFinishedStrokeColor( Color.rgb( 2, 204, 2 ));
                donutProgress.setTextColor( Color.rgb( 2, 204, 2 ));
            }
        }else{
            this.donutProgress.setProgress( max );
            this.donutProgress.setText( Float.toString( value )+ "€" );
            donutProgress.setFinishedStrokeColor( Color.rgb(214, 17, 17));
            donutProgress.setTextColor( Color.rgb(214, 17, 17));
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
            Intent intent = new Intent(DonutActivity.this, ZusammenfassungActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public float getValue(){
        return value;
    }


}
