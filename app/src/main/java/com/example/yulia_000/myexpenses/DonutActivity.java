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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.DonutProgress;
import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.model.Entry;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.repo.SaveupRepo;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;

import java.util.List;

public class DonutActivity extends AppCompatActivity {

    private ImageButton btnHinzu;
    private ImageButton btnSparen;
    private DonutProgress donutProgress;
    private EditText text;
    private String message;
    double betrag = 0;
    double bb;
    float value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_donut );



      //  text=(EditText) findViewById( R.id.EditText );
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        btnHinzu=(ImageButton)this.findViewById(R.id.hinzuImageButton);
        btnSparen=(ImageButton)this.findViewById(R.id.sparenImageButton);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        message = intent.getStringExtra(Guthaben.MSG);

        UserRepo userRepo = new UserRepo();
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        User tmpuser = userRepo.getUserByName(sharedpreferences.getString("name",""));

        try {
            betrag = Double.parseDouble(tmpuser.getCredit());
            Log.i("PROVERKAbETRAG",betrag+"");

        } catch(NumberFormatException nfe) {
        }

        EntryRepo entryRepo = new EntryRepo();

//        entryRepo.delete();
       // SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        List<Entry> entrys =  entryRepo.getList(sharedpreferences.getInt("userId",0));
        Log.d("MyApp","I am here");
        Log.d("MyApp",entrys.toString());

        double entryBetrag = 0;
        for (Entry entry : entrys){
            try {
                entryBetrag = Double.parseDouble(entry.getAmount());
            } catch(NumberFormatException nfe) {
            }
            Log.d("getID",entry.getID()+"");
            Log.d("getUserID",entry.getUserID()+"");
            Log.d("getAmount",entry.getAmount()+"");
            Log.d("getDate",entry.getDate()+"");
            Log.d("getKategory",entry.getKategory()+"");
            Log.d("getDescription",entry.getDescription()+"");

            betrag =  betrag - entryBetrag;
            //setBetrag(betrag);
            betragBerechnenErsparnisse(betrag);
           // setValue(betrag+"");
        }

        Toast.makeText(this,"MSG: "+message, Toast.LENGTH_LONG).show();

        //setMax(betrag+"");
        setMax(tmpuser.getCredit()); Log.i("getBetragAsFloat",getBetrag()+"");
        setValue(betrag+"",tmpuser);

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
                intent.putExtra("betrag", DonutActivity.this.max-DonutActivity.this.betrag+"");
                Log.i("HAHAHA1",DonutActivity.this.betrag+"");
                startActivity(intent);
            }
        });

    }

    public void betragBerechnenErsparnisse(double gesamtbetrag){
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SaveupRepo saveupRepo = new SaveupRepo();
        List<Saveup> saveups =  saveupRepo.getList(sharedpreferences.getInt("userId",0));
        float tmpbetrag=0;
        for (Saveup saveup : saveups){
            char flag=saveup.getSaveupAmount().charAt( 0 ); // + or -
            if(flag=='+'){
                tmpbetrag = Float.valueOf(saveup.getSaveupAmount().substring( 1 ));
                gesamtbetrag-=tmpbetrag;
            }else{
                tmpbetrag = Float.valueOf(saveup.getSaveupAmount().substring( 1 ));
                gesamtbetrag+=tmpbetrag;
            }
            this.betrag=gesamtbetrag;
            Log.i("getBetraisse",gesamtbetrag+"");
        }
    }

    public void setBetrag(double b){
        this.bb=b;
    }

    public double getBetrag(){
        return this.bb;
    }

    float max;
    public void setMax(String msg){
        float value = Float.valueOf(msg);
        // Toast.makeText(this,text.toString(), Toast.LENGTH_LONG).show();
        this.donutProgress.setMax( value );
         max=this.donutProgress.getMax();
        Log.i("PROVERMAX",max+"");
     /*   Toast.makeText(this,"max: "+Float.toString( max ), Toast.LENGTH_LONG).show();
        this.donutProgress.setText( Float.toString( value )+ "€" );
        donutProgress.setTextColor( Color.rgb( 2, 204, 2 ));*/

    }

    public float getMax(){
        float value = Float.valueOf(max);
        return value;
    }

    public void setValue(String msg,User user){
        value = Float.valueOf(msg);
        float max=Float.valueOf(user.getCredit());
        float val = value;
        float valueP = (val*100)/max;
        this.donutProgress.setProgress( val );
        Log.i("PROVERPROGRESS",this.donutProgress.getProgress()+"");
        if(value>=0){
            this.donutProgress.setText( Float.toString( value )+ "€" );
            Toast.makeText(this,Float.toString( valueP )+"%", Toast.LENGTH_LONG).show();

            if(valueP >= 75.0){//green
               // Log.i("LALA75",valueP+"");
                donutProgress.setFinishedStrokeColor( Color.rgb( 2, 204, 2 ));
                donutProgress.setTextColor( Color.rgb( 2, 204, 2 ));
            }else  if(valueP >= 25.0 && valueP < 50.0){
                //Log.i("LALA25",valueP+"");
                donutProgress.setFinishedStrokeColor( Color.rgb(255, 102, 0 ));
                donutProgress.setTextColor( Color.rgb(255, 102, 0 ));
            }else  if(valueP >= 50.0 && valueP < 75.0){
                //Log.i("LALA50",valueP+"");
                donutProgress.setFinishedStrokeColor( Color.rgb(255, 247, 0));
                donutProgress.setTextColor( Color.rgb(255, 247, 0));

            }else {
              //  Log.i("LALA",valueP+"");
                donutProgress.setFinishedStrokeColor( Color.rgb(214, 17, 17));
               donutProgress.setTextColor( Color.rgb(214, 17, 17));
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
        }else if(id == R.id.action_logout){
            SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            finish();

            Intent intent = new Intent(DonutActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public float getValue(){
        return value;
    }


}
