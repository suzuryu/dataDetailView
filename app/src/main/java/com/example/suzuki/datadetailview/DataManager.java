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

public class DataManager extends Activity {

    public FileOutputStream getLocalOutputFileStream(String fileName){
        try {
            return openFileOutput(fileName, Context.MODE_PRIVATE);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
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
        try {
            FileOutputStream outputStream = getLocalOutputFileStream(fileName);

            outputStream.write(text.getBytes());
            outputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFromFile(String fileName, String text) {
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


}
