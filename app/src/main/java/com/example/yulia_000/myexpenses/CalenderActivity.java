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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalenderView = (CalendarView)findViewById(R.id.calendarView);

        mCalenderView.setOnDateChangeListener((new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                Log.d(TAG, date);

                Intent intent = new Intent(CalenderActivity.this, MainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        }));
    }
}
