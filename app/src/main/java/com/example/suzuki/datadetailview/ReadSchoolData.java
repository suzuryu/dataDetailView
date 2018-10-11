package com.example.suzuki.datadetailview;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class ReadSchoolData {
    private DataManager dataManager;

    ReadSchoolData(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public void readFromCsv(String csv_file_name){
        try{

            FileInputStream inputStream = this.dataManager.getLocalInputFileStream(csv_file_name);
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line;
            int limit_time = 10000;
            int count = 0;
            Prefecture prefec = new Prefecture();

            while((line = bfReader.readLine()) != null){
                String[] data = line.split(",", 0);
                if(data.length == 1){
                    if (Prefecture.isPrefucture(data[0])){
                        prefec = new Prefecture(data[0]);
                    }
                    continue;
                }
                data = Arrays.copyOfRange(data, 0, 2);

                if (!Arrays.asList(data).contains(null)){
                    String num = data[1];
                    String[] firstData = data[0].split(" ", 0);
                    if(firstData.length == 2){
                        String name = firstData[1];
                        Log.d("ReadSchoolData",prefec.getHerePrefecture());
                        Log.d("ReadSchoolData",name + ":"  + num);
                    }
                }
                if(count > limit_time){
                    break;
                }
                count++;
            }
            bfReader.close();
        } catch (IOException e){
            System.out.println(e);
        }

    }
}

