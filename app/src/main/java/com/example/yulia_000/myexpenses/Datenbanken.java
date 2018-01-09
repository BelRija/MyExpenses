package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Yulia_000 on 09.01.2018.
 */

public class Datenbanken extends Activity {

    private DatenbankManager mHelper;
    private SQLiteDatabase mDatenbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daten_datenbanken);

        mHelper = new DatenbankManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDatenbank = mHelper.getReadableDatabase();
        Toast.makeText(this, "Datenbanken geöffnet", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDatenbank.close();
        Toast.makeText(this, "Datenbanken geschlossen", Toast.LENGTH_SHORT).show();
    }



}
