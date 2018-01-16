package com.example.yulia_000.myexpenses.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yulia_000.myexpenses.data.DatabaseManager;
import com.example.yulia_000.myexpenses.data.model.Entry;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yulia_000 on 15.01.2018.
 */

public class EntryRepo {
    private Entry entry;

    public EntryRepo(){
        entry = new Entry();
    }
    public static String createTable(){
        return "CREATE TABLE " + Entry.TABLE  + "("
                + Entry.KEY_ID  + " INT PRIMARY KEY AUTOINCREMENT ,"
                + Entry.KEY_UserID  + " TEXT,"
                + Entry.KEY_Date + " TEXT, "
                + Entry.KEY_Description + " TEXT, "
                + Entry.KEY_Kategory + " TEXT, "
                + Entry.KEY_Amount  + " TEXT )";
    }


    public void insert(Entry entry) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        // values.put(Entry.KEY_ID, entry.getID());
//        values.put(Entry.KEY_UserID, entry.getUserID());
        values.put(Entry.KEY_Date, entry.getDate());
        values.put(Entry.KEY_Description, entry.getDescription());
        values.put(Entry.KEY_Kategory, entry.getKategory());
        values.put(Entry.KEY_Amount, entry.getAmount());

        // Inserting Row
        db.insert(Entry.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Entry> getList(){
        Entry entry = new Entry();
        List<Entry> entrys = new ArrayList<Entry>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String Query = "Select * from Entry";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            do {
                entry= new Entry();
                entry.setUserID(cursor.getString(cursor.getColumnIndex(entry.KEY_UserID)));
                entry.setKategory(cursor.getString(cursor.getColumnIndex(entry.KEY_Kategory)));
                entry.setDescription(cursor.getString(cursor.getColumnIndex(entry.KEY_Description)));
                entry.setAmount(cursor.getString(cursor.getColumnIndex(entry.KEY_Amount)));
                entry.setDate(cursor.getString(cursor.getColumnIndex(entry.KEY_Date)));

                entrys.add(entry);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return entrys;
    }
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Entry.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }


}
