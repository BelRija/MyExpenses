package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.model.Entry;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.components.Legend.LegendPosition.BELOW_CHART_LEFT;
import static com.github.mikephil.charting.components.Legend.LegendPosition.LEFT_OF_CHART;
import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART;
import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART_CENTER;

/**
 * Created by Marija on 13.01.2018.
 */

public class PieChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

 private DonutActivity donut=new DonutActivity();
 private  List<PieEntry> entries = new ArrayList<>();

 private PieChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.piechart_activity );
        chart = (PieChart) findViewById(R.id.chart);
        Intent intent = getIntent();
        String message = intent.getStringExtra("betrag");
        float guthaben=0;
        if(message!=null) {
         guthaben = Float.valueOf(message);}

        float guthabenP;
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        EntryRepo entryRepo = new EntryRepo();
        List<Entry> entrys =  entryRepo.getList(sharedpreferences.getInt("userId",0));

        Resources res = getResources();
        String[] kategories = res.getStringArray(R.array.kategorieliste);
        Kategorie kat;
        ArrayList<Kategorie>kats=new ArrayList <Kategorie>(  );

       for (com.example.yulia_000.myexpenses.data.model.Entry entry : entrys){
           for(String k:kategories){
               if(entry.getKategory()!=null && k!=null)
               if(entry.getKategory().equals(k)){
                   float betrag = Float.valueOf(entry.getAmount());
                   if(kats.size()>0){

                       int tmp=1;
                       for(Kategorie kk:kats){

                           if(k.equals( kk.getName() )){

                               kk.setBetrag( kk.getBetrag()+betrag );tmp=0;

                           }else{}
                       }if(tmp==1) {
                           kats.add(new Kategorie(k,betrag));

                       }
                   }
                   else{kats.add(new Kategorie(k,betrag));}

               }
           }
        }


        for(Kategorie k:kats){
            float betrag = Float.valueOf(k.getBetrag());
            String tmp=k.getBetrag()+"";
            Log.i("getTMP",tmp.charAt( 0 )+"");
            guthabenP = (betrag*100)/guthaben;
            if(tmp.charAt( 0 )=='-') guthabenP*=(-1);
            Log.i("LALALA",""+guthabenP);
            Log.i("LALALA11",""+betrag);
            Log.i("LALALA22",""+guthaben);
            entries.add(new PieEntry(guthabenP, k.getName()));

            Log.i("HAHAHA11",""+k.getName() +"B: "+k.getBetrag());

        }


        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(new int[] {
                R.color.green, R.color.yellow, R.color.red, R.color.blue,
                R.color.violet, R.color.orange, R.color.pink, R.color.turkis,
                R.color.lightblue, R.color.green1, R.color.pink1, R.color.darkblue,
                R.color.pink2, R.color.orange1, R.color.choco, R.color.kreme}, this);

        set.setHighlightEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        set.setDrawValues(true);
        set.setValueFormatter(new MyValueFormatter());
        set.setValueTextColor( Color.WHITE);
        set.setValueTextSize( 10 );
        set.setValueTypeface( Typeface.DEFAULT_BOLD );
        set.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        set.setValueLinePart1OffsetPercentage(60.f);
        set.setValueLinePart1Length(0.2f);
        set.setValueLinePart2Length(0.2f);
        chart.setEntryLabelColor(Color.BLACK);
        //set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data = new PieData(set);
        data.setValueTextColor( Color.WHITE );
        chart.getDescription().setEnabled(false);
        chart.setData(data);
        chart.setOnChartValueSelectedListener(this);
        Legend l=chart.getLegend();
        l.setPosition(   BELOW_CHART_LEFT );
        l.setWordWrapEnabled(true);
        chart.invalidate();

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
            Intent intent = new Intent(PieChartActivity.this, ZusammenfassungActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onValueSelected(com.github.mikephil.charting.data.Entry e, Highlight h) {

        PieEntry pe = (PieEntry) e;
        Log.i("PROVERKA3",""+ pe.getLabel());
        value(pe.getLabel());

    }

    public void value(String label){
        Intent intent = new Intent(this, ZusammenfassungActivity.class);
        intent.putExtra("kategorie", label);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected() {

    }
}
