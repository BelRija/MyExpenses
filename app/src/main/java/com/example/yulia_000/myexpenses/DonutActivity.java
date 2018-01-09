package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 09.01.2018.
 */

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.DonutProgress;

public class DonutActivity extends AppCompatActivity {

    private DonutProgress donutProgress;
    private EditText text;
    String message;
    float value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_donut );

        text=(EditText) findViewById( R.id.EditText );
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        message = intent.getStringExtra(Guthaben.MSG);

        Toast.makeText(this,"MSG: "+message, Toast.LENGTH_LONG).show();
        setMax(message);
    }

    public void setDonutProgress(DonutProgress donutProgress) {
        this.donutProgress = donutProgress;
    }

    public void setMax(String msg){

        float value = Float.valueOf(msg);
        // Toast.makeText(this,text.toString(), Toast.LENGTH_LONG).show();
        this.donutProgress.setMax( value );
        float max=this.donutProgress.getMax();
        Toast.makeText(this,"max: "+Float.toString( max ), Toast.LENGTH_LONG).show();
        this.donutProgress.setText( Float.toString( value )+ "€" );
        donutProgress.setTextColor( Color.rgb( 2, 204, 2 ));

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


}
