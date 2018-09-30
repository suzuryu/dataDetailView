package com.example.suzuki.datadetailview;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DataManager extends Activity{
    private String textData;
    private String PACKAGE_NAME = "com.example.suzuki.datadetailview/";

    DataManager(){
        this.textData = "TEST";
    }

    public String convertFileName(String fileNameOrigin){
        return "/data/data/" + PACKAGE_NAME  + fileNameOrigin;
    }

    public FileInputStream getLocalInputFileStream(String fileName){
        try{
            return openFileInput(fileName);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public void writeToFile(String fileName, String text) {
        fileName = convertFileName(fileName);
        try (FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE)){
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFromFile(String fileName) {
        try {
            fileName = convertFileName(fileName);
            FileInputStream inputStream = getLocalInputFileStream(fileName);
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String data;
            ArrayList<String> fileData = new ArrayList<String>();
            while((data = bfReader.readLine()) != null){
                fileData.add(data);
            }

            return fileData;
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    public String getTextData(){
        return this.textData;
    }
}
