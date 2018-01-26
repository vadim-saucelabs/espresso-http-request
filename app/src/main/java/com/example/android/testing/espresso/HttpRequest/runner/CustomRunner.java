package com.example.android.testing.espresso.HttpRequest.runner;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by umutuzgur on 25/09/15.
 */
public class CustomRunner extends AndroidJUnitRunner {

    Thread log;
    @Override


    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        log = new Thread(new Runnable() {
            @Override
            public void run() {

                BufferedWriter bufferedWriter = null;

                try {
                    final File file = new File("/data/log");
                    Log.i("location",file.getAbsolutePath());
                    bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                    Process process = Runtime.getRuntime().exec("logcat -d");
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(process.getInputStream()));

                    String oneLine;
                    while ((oneLine = bufferedReader.readLine()) != null) {

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://10.0.2.2/log");

                        try {
                            // Add your data
                            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                            nameValuePairs.add(new BasicNameValuePair("log", oneLine));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                            // Execute HTTP Post Request
                            HttpResponse response = httpclient.execute(httppost);

                        } catch (ClientProtocolException e) {
                            // TODO Auto-generated catch block
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                        }
                        bufferedWriter.write(oneLine);
                        bufferedWriter.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        log.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log.interrupted();
    }
}
