package com.example.suzuki.datadetailview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class jsonHelper {
//    public static ArrayList<schoolData> schoolJson(String strJson){
//        ArrayList<schoolData> schoolAry = new ArrayList<schoolData>();
//
//        try{
//            String schoolName = "hogeschool";
//            JSONArray schools = new JSONObject(strJson).getJSONObject("school").getJSONArray(schoolName);
//
//            for(int i = 0; i < schools.length(); i++){
//                JSONObject entry = schools.getJSONObject(i);
//                schoolAry.add(parserToSchool(entry));
//            }
//
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//
//        System.out.println(schoolAry);
//        return schoolAry;
//    }

//    public static schoolData parserToSchool(JSONObject json) throws JSONException{
//        schoolData tmpData = new schoolData();
//
//        tmpData.setName(json.getString("name"));
//        tmpData.setCity_name(json.getString("city"));
//        tmpData.setState_name(json.getString("state"));
//
//        return tmpData;
//    }
}
