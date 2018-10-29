package com.example.suzuki.datadetailview;

import android.content.ContentValues;
import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class JsonHelper {
    private SQLiteManager dbManager;

    JsonHelper(SQLiteManager dbManager){
        this.dbManager = dbManager;
    }

    public void readJson(InputStream jsonIn) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new InputStreamReader(jsonIn,"SHIFT-JIS"));
            for(String pref: Prefecture.PREFUCTURE_LIST) {
                for (JsonNode node : root.get(pref)) {
                    Iterator<Map.Entry<String, JsonNode>> city = node.fields();

                    while(city.hasNext()){
                        String name = city.next().getKey();

                        ContentValues content = new ContentValues();
                        content.put("cityName", name);
                        content.put("prefName", pref);
                        content.put("population", Integer.parseInt(node.get(name).get("population").asText()));
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
