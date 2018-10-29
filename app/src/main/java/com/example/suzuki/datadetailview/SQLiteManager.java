package com.example.suzuki.datadetailview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class SQLiteManager {
    private Context context;
    private SubOpenHelper subHelper;
    private static String dbName = "townData.db";
    private static String tableName = "cityTable";

    SQLiteManager(Context c) {
        this.context = c;
        this.subHelper = new SubOpenHelper(context,  dbName, 2);
    }

    public  SQLiteDatabase getWRDatabase(){
        return subHelper.getWritableDatabase();
    }

    public SQLiteDatabase getRDatabase(){
        return subHelper.getReadableDatabase();
    }

    public TownData queryByCityName(String cityName){
        String[] selectionArgs = {cityName};
        Cursor c = this.getRDatabase().query(tableName, null,"cityName = ?", selectionArgs,null,null,"2");

        TownData td = new TownData();
        if(c.moveToFirst()){
            td.setData_name_(c.getString(c.getColumnIndex("cityName")));
            td.setPrefecture(c.getString(c.getColumnIndex("prefName")));
            td.setPeople_num(c.getInt(c.getColumnIndex("peopleCount")));
            td.setSchool_num(c.getInt(c.getColumnIndex("schoolCount")));
            td.setStation_num(c.getInt(c.getColumnIndex("stationCount")));
            td.setCrime_per(c.getDouble(c.getColumnIndex("crimePer")));
        }
        c.close();
        return td;
    }

    public void insertData(ContentValues cv){
        long id = this.getWRDatabase().insert(tableName,  null, cv);
        if(id < 0){
            Log.d("SQL","could not insert data");
        }
    }
}
