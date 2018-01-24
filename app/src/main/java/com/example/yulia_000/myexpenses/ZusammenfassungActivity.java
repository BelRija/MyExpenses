package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yulia_000.myexpenses.data.model.*;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;
import com.example.yulia_000.myexpenses.data.model.Entry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulia_000 on 16.01.2018.
 */

public class ZusammenfassungActivity extends Activity {

    private ListView lv;
    private List<String> entry_listing=new ArrayList<String>();
    private TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zusammenfassung_layout);

        txtLabel=(TextView)this.findViewById(R.id.label);

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        EntryRepo entryRepo = new EntryRepo();
        List<Entry> entrys =  entryRepo.getList(sharedpreferences.getInt("userId",0));

        Intent incomingIntent = getIntent();
        String kategorie = incomingIntent.getStringExtra("kategorie");

        float summe=0;
        DecimalFormat df = new DecimalFormat("0.00");
        float tmpbetrag=0;
        if(kategorie!=null){
            filtertList(entrys, kategorie);
        }else{
            for (Entry entry : entrys){
                entry_listing.add(
                        entry.getDate()+": "+entry.getAmount()+ " €  | "+entry.getKategory()
                );
                String amount=entry.getAmount()+"";
                char flag=amount.charAt( 0 ); // + or -
                if(flag=='-'){
                    tmpbetrag = Float.valueOf(entry.getAmount().substring( 1 ));
                    summe+=tmpbetrag;
                }else{
                    tmpbetrag = Float.valueOf(entry.getAmount().substring( 1 ));
                    summe-=tmpbetrag;
                }

            }
            txtLabel.setText( txtLabel.getText()+":\n -"+ df.format(summe)+" €" );
            setList(entry_listing);
        }

        ArrayList<String> entry_list = new ArrayList<String>();
        for(int i=getList().size()-1;i>=0;i--){
            entry_list.add(getList().get(i));
        }
        lv = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                entry_list );

        lv.setAdapter(arrayAdapter);

    }

    public void filtertList(List<Entry> entrys,String kategorie){
        float summe=0;
        DecimalFormat df = new DecimalFormat("0.00");
        float tmpbetrag=0;
        for (Entry entry : entrys){
            if(entry.getKategory().equals( kategorie )){
            entry_listing.add(
                    entry.getDate()+": "+entry.getAmount()+ " € | "+entry.getKategory()
            );

            String amount=entry.getAmount()+"";
            char flag=amount.charAt( 0 ); // + or -
            if(flag=='-'){
                tmpbetrag = Float.valueOf(entry.getAmount().substring( 1 ));
                summe+=tmpbetrag;
            }else{
                tmpbetrag = Float.valueOf(entry.getAmount().substring( 1 ));
                summe-=tmpbetrag;
            }}

        } txtLabel.setText( txtLabel.getText()+":\n -"+ df.format(summe)+" €" );
        setList(entry_listing);

    }
    public void setList(List<String> list){
        this.entry_listing=list;
    }
    public List<String> getList(){
        return entry_listing;
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
            Intent intent = new Intent(ZusammenfassungActivity.this, ZusammenfassungActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
