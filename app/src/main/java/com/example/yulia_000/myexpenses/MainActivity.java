package com.example.yulia_000.myexpenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Calender
        theDate = (TextView)findViewById(R.id.txtDate);



        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

       // Calendar c = Calendar.getInstance();
       // int mYear = c.get(Calendar.YEAR);
       // int mMonth = c.get(Calendar.MONTH);
       // int mDay = c.get(Calendar.DAY_OF_MONTH);
       // theDate.setText(mDay+"/"+(mMonth+1)+"/"+mYear);

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
