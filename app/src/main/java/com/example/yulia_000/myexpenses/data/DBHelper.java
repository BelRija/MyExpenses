package com.example.yulia_000.myexpenses.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yulia_000.myexpenses.app.App;

import com.example.yulia_000.myexpenses.data.model.User;
import com.example.yulia_000.myexpenses.data.model.Entry;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;
import com.example.yulia_000.myexpenses.data.repo.EntryRepo;

/**
 * Created by Yulia_000 on 15.01.2018.
 */


public class DBHelper  extends SQLiteOpenHelper {
        //version number to upgrade database version
        //each time if you Add, Edit table, you need to change the
        //version number.
        private static final int DATABASE_VERSION =9;
        // Database Name
        private static final String DATABASE_NAME = "sqliteDBMultiTbl.db";
        private static final String TAG = DBHelper.class.getSimpleName().toString();

        public DBHelper( ) {
            super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //All necessary tables you like to create will create here
            db.execSQL(UserRepo.createTable());
            db.execSQL(EntryRepo.createTable());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

            // Drop table if existed, all data will be gone!!!
            db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE);
            onCreate(db);
        }

    }
