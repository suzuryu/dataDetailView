package com.example.suzuki.datadetailview;

import android.content.ContentValues;
import android.util.JsonReader;
import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class JsonHelper {
    private DataManager dataManager;
    private SQLiteManager dbManager;

    JsonHelper(DataManager dataManager, SQLiteManager dbManager){
        this.dataManager = dataManager;
        this.dbManager = dbManager;
    }

    public void readJson(InputStream jsonIn) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new InputStreamReader(jsonIn,"SHIFT-JIS"));
            ArrayList<TownData> townData = new ArrayList<>();
            for(String pref: Prefecture.PREFUCTURE_LIST) {
                for (JsonNode node : root.get(pref)) {
                    Iterator<Map.Entry<String, JsonNode>> city = node.fields();

                    while(city.hasNext()){
                        String name = city.next().getKey();
//                        TownData td = new TownData();
//                        td.setData_name_(name);
//                        td.setPrefecture(pref);
//                        td.setStation_num(Integer.parseInt(node.get(name).get("station").asText()));
//                        td.setSchool_num(Integer.parseInt(node.get(name).get("school").asText()));
//                        td.setCrime_per(node.get(name).get("hanzai").asDouble());
//
//                        this.dataManager.getTownData().put(name, td);

                        ContentValues content = new ContentValues();
                        content.put("cityName", name);
                        content.put("prefName", pref);
                        content.put("peopleCount", 0);
                        content.put("schoolCount",Integer.parseInt(node.get(name).get("school").asText()));
                        content.put("stationCount",Integer.parseInt(node.get(name).get("station").asText()));
                        content.put("crimePer", node.get(name).get("hanzai").asDouble());

                        dbManager.insertData(content);
                    }
                }
            }
            Log.d("DATA","setting ok");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
