package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
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
 private DonutActivity donut=new DonutActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.piechart_activity );
        PieChart chart = (PieChart) findViewById(R.id.chart);
        Intent intent = getIntent();
        String message = intent.getStringExtra("betrag");
        Log.i("HAHAHA",message);
        float guthaben=0;
        if(message!=null) {
         guthaben = Float.valueOf(message);}

        float guthabenP;
        EntryRepo entryRepo = new EntryRepo();
        List<com.example.yulia_000.myexpenses.data.model.Entry> entrys =  entryRepo.getList();

        Resources res = getResources();
        String[] kategories = res.getStringArray(R.array.kategorieliste);
        Kategorie kat;
        ArrayList<Kategorie>kats=new ArrayList <Kategorie>(  );

       for (com.example.yulia_000.myexpenses.data.model.Entry entry : entrys){
           for(String k:kategories){
               if(entry.getKategory()!=null && k!=null)
               if(entry.getKategory().equals(k)){
                   float betrag = Float.valueOf(entry.getAmount());
                   if(kats.size()>0){ Log.i("HAHAHA33",""+kats.size());int tmp=1;
                       for(Kategorie kk:kats){
                           Log.i("HAHAHA55","** "+kk.getName());  tmp=1;
                           if(k.equals( kk.getName() )){
                               Log.i("HAHAHA44","++"+kk.getBetrag());
                               kk.setBetrag( kk.getBetrag()+betrag );tmp=0;
                               Log.i("HAHAHA44","--"+kk.getBetrag());
                           }else{}
                       }if(tmp==1) {kats.add(new Kategorie(k,betrag)); Log.i("HAHAHA77","Dobavil: "+k);}
                   }
                   else{kats.add(new Kategorie(k,betrag));}
                   Log.i("HAHAHA22",""+k);
               }
           }
        }

        List<PieEntry> entries = new ArrayList<>();
        for(Kategorie k:kats){
            float betrag = Float.valueOf(k.getBetrag());
//            if(entry.getKategory().equals(entires[1]))
            guthabenP = (betrag*100)/guthaben;
            entries.add(new PieEntry(guthabenP, k.getName()));

            Log.i("HAHAHA11",""+k.getName() +"B: "+k.getBetrag());

        }


        PieDataSet set = new PieDataSet(entries, "");
        PieData data = new PieData(set);
        set.setColors(new int[] {
                R.color.green, R.color.yellow, R.color.red, R.color.blue,
                R.color.violet, R.color.orange, R.color.pink, R.color.turkis,
                R.color.lightblue, R.color.green1, R.color.pink1, R.color.darkblue,
                R.color.pink2, R.color.orange1, R.color.choco, R.color.kreme}, this);
        chart.setData(data);
        set.setValueFormatter(new MyValueFormatter());
        chart.invalidate();

    }

}
