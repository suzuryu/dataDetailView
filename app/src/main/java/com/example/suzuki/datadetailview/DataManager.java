package com.example.suzuki.datadetailview;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
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
import java.util.Dictionary;
import java.util.HashMap;

public class DataManager{
    private String textData;
    //private String PACKAGE_NAME = "com.example.suzuki.datadetailview/";
    private Context context;
    private ArrayList<String> fileNames;
    private HashMap<String, TownData> townData;

    DataManager(Context context){
        this.textData = "TEST";
        this.context = context;
        this.fileNames = new ArrayList<>();
        this.townData = new HashMap<>();
    }

    public void addFileName(String fileName){
        if (!this.fileNames.contains(fileName)) {
            this.fileNames.add(fileName);
        }
    }

    public ArrayList<String> getFileNames(){
        return this.fileNames;
    }

    public String getFilePath(String fileName){
        return this.context.getFilesDir().getPath() + File.separator + "androiddeft/"  + fileName;
    }

    public FileInputStream getLocalInputFileStream(String fileName){
        try{
            return new FileInputStream(this.getFilePath(fileName));
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public Context getContext() {
        return context;
    }

    public void writeToFile(String fileName, String text) {
        try (FileOutputStream outputStream = new FileOutputStream(new File(getFilePath(fileName)))){
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFromFile(String fileName) {
        try {

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

    public HashMap<String, TownData> getTownData() {
        return townData;
    }

}
