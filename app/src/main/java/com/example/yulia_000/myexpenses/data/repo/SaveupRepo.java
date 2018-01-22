package com.example.yulia_000.myexpenses.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yulia_000.myexpenses.data.DatabaseManager;
import com.example.yulia_000.myexpenses.data.model.Entry;
import com.example.yulia_000.myexpenses.data.model.Saveup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulia_000 on 22.01.2018.
 */

public class SaveupRepo {
    private Saveup saveup;

    public SaveupRepo(){
        saveup = new Saveup();
    }
    public static String createTable(){
        return "CREATE TABLE " + Saveup.TABLE  + "("
                + Saveup.KEY_ID  + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Saveup.KEY_UserID  + " INTEGER,"
                + Saveup.KEY_Date + " TEXT, "
                + Saveup.KEY_Description + " TEXT, "
                + Saveup.KEY_Amount  + " TEXT )";
    }


    public void insert(Saveup saveup) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
//        values.put(Entry.KEY_ID, entry.getID());
        values.put(Saveup.KEY_UserID, saveup.getUserID());
        values.put(Saveup.KEY_Date, saveup.getSaveupDate());
        values.put(Saveup.KEY_Description, saveup.getSaveupDescription());
        values.put(Saveup.KEY_Amount, saveup.getSaveupAmount());

        // Inserting Row
        db.insert(Saveup.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Saveup> getList(int id){
        Saveup save = new Saveup();
        List<Saveup> saves = new ArrayList<Saveup>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String Query = "Select * from " + Saveup.TABLE  + " WHERE "+Saveup.KEY_UserID+" = "+id ;
        Cursor cursor = db.rawQuery(Query, null);
        Log.d("SaveupRepo","beim auslesen");
        if (cursor.moveToFirst()) {
            do {
                save= new Saveup();
                save.setID(cursor.getInt(cursor.getColumnIndex(save.KEY_ID)));
                save.setUserID(cursor.getInt(cursor.getColumnIndex(save.KEY_UserID)));
                save.setSaveupDescription(cursor.getString(cursor.getColumnIndex(save.KEY_Description)));
                save.setSaveupAmount(cursor.getString(cursor.getColumnIndex(save.KEY_Amount)));
                save.setSaveupDate(cursor.getString(cursor.getColumnIndex(save.KEY_Date)));

                saves.add(save);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return saves;
    }
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Entry.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
