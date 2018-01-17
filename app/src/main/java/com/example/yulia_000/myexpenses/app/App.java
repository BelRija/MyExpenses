package com.example.yulia_000.myexpenses.app;

/**
 * Created by Yulia_000 on 15.01.2018.
 */

import android.app.Application;
import android.content.Context;

import com.example.yulia_000.myexpenses.data.DatabaseManager;
import com.example.yulia_000.myexpenses.data.DBHelper;

/**
 * Created by Tan on 1/26/2016.
 */
public class  App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }

    public static Context getContext(){
        return context;
    }

}
