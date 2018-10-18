package com.example.suzuki.datadetailview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;
import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

public class DownloadFile extends AsyncTask<String, String, String> {
    private ProgressDialog progressDialog;
    private String fileName;
    private String folder;
    private boolean isDownloaded;
    private Context mainActivity;
    private DataManager dataManager;
    private CountDownLatch cdLatch;

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */

    DownloadFile(Context mainActivity, DataManager dataManager,CountDownLatch latch){
        super();
        this.dataManager = dataManager;
        this.mainActivity = mainActivity;
        this.cdLatch = latch;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog = new ProgressDialog(mainActivity);
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

   @Override
    public String doInBackground(String... f_url) {
        int count;
        try{
            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            int lengthOfFile = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            //Extract file name from URL
            fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

            //External directory path to save file
            folder = this.mainActivity.getFilesDir().getPath() + File.separator + "androiddeft/";

            //Create androiddeft folder if it does not exist
            File directory = new File(folder);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Output stream to write file
            OutputStream output = new  FileOutputStream(folder + fileName);
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output, "UTF-8")
            this.dataManager.addFileName(fileName);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;

                publishProgress("" + (int) ((total * 100) / lengthOfFile));
                Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                output.write(data, 0, count);
            }

            output.flush();

            // closing streams
            output.close();
            input.close();

            this.cdLatch.countDown();

            return "Downloaded at: " + folder + fileName;

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());

        }
        return "Something went wrong";
    }

    /**
     * Updating progress bar
     */
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        progressDialog.setProgress(Integer.parseInt(progress[0]));
    }

    @Override
    protected void onPostExecute(String message) {
        // dismiss the dialog after the file was downloaded
        this.progressDialog.dismiss();

        // Display File path after downloading
        Toast.makeText(mainActivity.getApplicationContext(),
                message, Toast.LENGTH_LONG).show();
    }
}
