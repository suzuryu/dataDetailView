package com.example.suzuki.datadetailview;

import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ContentHandler;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

public class DownloadSync {
    private String fileName;
    private Context mainActivity;
    private DataManager dataManager;

    DownloadSync(Context actity, DataManager dataManager){
        this.mainActivity = actity;
        this.dataManager = dataManager;
    }


    public void download(CountDownLatch latch, String... f_url){
        int count;
        try{
            String folder;

            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            //Extract file name from URL
            fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());
            this.dataManager.addFileName(fileName);

            //External directory path to save file
            folder = this.mainActivity.getFilesDir().getPath() + File.separator + "androiddeft/";

            //Create androiddeft folder if it does not exist
            File directory = new File(folder);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Output stream to write file
            OutputStream output = new FileOutputStream(folder + fileName);

            byte data[] = new byte[1024];

            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
        }

        latch.countDown();
    }
}
