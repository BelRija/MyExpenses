package com.example.yulia_000.myexpenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

import com.example.yulia_000.myexpenses.R;

public class CalenderActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CalendarView mCalenderView;
    private String  message;
    private String msg,bez,betrag,kategorie;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalenderView = (CalendarView)findViewById(R.id.calendarView);

        Intent intent = getIntent();
        message = intent.getStringExtra( "class" );
        betrag = intent.getStringExtra( "betrag" );
        bez = intent.getStringExtra( "bez" );
        kategorie = intent.getStringExtra( "kategorie" );

        mCalenderView.setOnDateChangeListener((new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                Log.d(TAG, date);
                Intent intent;

                if(message != null && message.equals("sparen")){
                    intent = new Intent(CalenderActivity.this, SparenActivity.class);
                    if(betrag!=null)
                        intent.putExtra("betrag", betrag);
                    if(bez!=null)
                        intent.putExtra("bez", bez);

                }else if(message != null && message.equals("zurueck")){
                    intent = new Intent(CalenderActivity.this, GeldZurueckActivity.class);
                    if(betrag!=null)
                        intent.putExtra("betrag", betrag);

                }else if(message != null && message.equals("ausgaben")){
                    intent = new Intent(CalenderActivity.this, MainActivity.class);
                    Log.i("PROVERKA",""+betrag);
                    if(betrag!=null)
                        intent.putExtra("betrag", betrag);
                    if(bez!=null)
                        intent.putExtra("bez", bez);
                    if(kategorie!=null)
                        intent.putExtra("kategorie", kategorie);

                }else{
                    intent = new Intent(CalenderActivity.this, EinnahmeActivity.class);
                    Log.i("PROVERKA",""+betrag);
                    if(betrag!=null)
                        intent.putExtra("betrag", betrag);
                    if(bez!=null)
                        intent.putExtra("bez", bez);
                    if(kategorie!=null)
                        intent.putExtra("kategorie", kategorie);
                }
                intent.putExtra("date", date);
                startActivity(intent);
            }
        }));
        
    }
}
