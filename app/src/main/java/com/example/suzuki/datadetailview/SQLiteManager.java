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
    private static final String dbName = "townData.db";
    private static final String tableName = "cityTable";

    SQLiteManager(Context c) {
        this.context = c;
        this.subHelper = new SubOpenHelper(context,  dbName, 6);
    }

    public  SQLiteDatabase getWRDatabase(){
        return subHelper.getWritableDatabase();
    }

    public SQLiteDatabase getRDatabase(){
        try{
            subHelper.getReadableDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        return subHelper.getReadableDatabase();
    }

    public TownData queryByCityName(String cityName){
        String[] selectionArgs = {cityName};
        Cursor c = this.getRDatabase().query(tableName, null,"cityName = ?", selectionArgs,null,null,null);

        TownData td = new TownData();
        if(c.moveToFirst()){
            td.setCityName(c.getString(c.getColumnIndex("cityName")));
            td.setPrefecture(c.getString(c.getColumnIndex("prefName")));
            td.setPopulation(c.getInt(c.getColumnIndex("population")));
            td.setSchoolCount(c.getInt(c.getColumnIndex("schoolCount")));
            td.setStationCount(c.getInt(c.getColumnIndex("stationCount")));
            td.setCrimePer(c.getDouble(c.getColumnIndex("crimePer")));
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
