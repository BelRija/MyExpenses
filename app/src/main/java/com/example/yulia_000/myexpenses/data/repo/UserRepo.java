package com.example.yulia_000.myexpenses.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yulia_000.myexpenses.data.DatabaseManager;
import com.example.yulia_000.myexpenses.data.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by Yulia_000 on 15.01.2018.
 */

public class UserRepo {
    private User user;

    public UserRepo(){
        user = new User();
    }
    public static String createTable(){
        return "CREATE TABLE " + User.TABLE  + "("
                + User.KEY_UserID  + " TEXT PRIMARY KEY AUTOINCREMENT ,"
                + User.KEY_Name + " TEXT, "
                + User.KEY_Password  + " TEXT )";
    }

    public void insert(User user) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_UserID, user.getUserId());
        values.put(User.KEY_Name, user.getName());
        values.put(User.KEY_Password, user.getPassword());

        // Inserting Row
        db.insert(User.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public boolean isLoggedIn(String username, String password){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String Query = "Select * from User where name = '" + username + "' AND password = '"+ password +"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(User.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
