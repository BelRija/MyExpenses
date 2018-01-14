package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marija on 13.01.2018.
 */

public class PieChartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.piechart_activity );
        PieChart chart = (PieChart) findViewById(R.id.chart);

        Resources res = getResources();
        String[] kategories = res.getStringArray(R.array.kategorieliste);


        List<PieEntry> entries = new ArrayList<>();
        float x=5f;
        for(String k: kategories){
            x+=5;
            entries.add(new PieEntry(x, k));
        }

        PieDataSet set = new PieDataSet(entries, "");
        PieData data = new PieData(set);
        set.setColors(new int[] {
                R.color.green, R.color.yellow, R.color.red, R.color.blue,
                R.color.violet, R.color.orange, R.color.pink, R.color.turkis,
                R.color.lightblue, R.color.green1, R.color.pink1, R.color.darkblue,
                R.color.pink2, R.color.orange1, R.color.choco, R.color.kreme}, this);
        chart.setData(data);
        chart.invalidate();

    }

}
