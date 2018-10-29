package com.example.suzuki.datadetailview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SubOpenHelper extends SQLiteOpenHelper {
    private final String SQL_CREATE_ENTRIES =
            "CREATE TABLE cityTable(_id INTEGER PRIMARY KEY, cityName TEXT, prefName TEXT, peopleCount INTEGER,"
            +"schoolCount INTEGER, stationCount INTEGER, crimePer DOUBLE);";

    public SubOpenHelper(Context c, String dbName, int version){
        super(c, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
