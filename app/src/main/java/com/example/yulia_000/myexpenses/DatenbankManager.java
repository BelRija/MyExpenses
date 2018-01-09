package com.example.yulia_000.myexpenses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yulia_000 on 09.01.2018.
 */

public class DatenbankManager extends SQLiteOpenHelper{
    private static final String DB_NAME = "budge.db";
    private static final int DB_VERSION = 1;

    private static final String BUDGE_TABLE = "budge";
    private static final String BUDGE_ID = "id";
    private static final String BUDGE_TITEL = "titel";
    private static final String BUDGE_CONTENT = "content";


    public DatenbankManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE" + " (" +
                BUDGE_ID + " INTEGER PRIMARY KEY, " +
                BUDGE_TITEL + " TEXT, " +
                BUDGE_CONTENT + " TEXT);";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BUDGE_TABLE);
        onCreate(db);
    }
}
