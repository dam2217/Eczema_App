package com.example.eczema_app.ui;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

// adapted from https://prodevsblog.com/view/android-httpurlconnection-post-and-get-request-tutorial/

public class HttpCommunicate {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    // method to send data to server
    public static String sendPost(String r_url , byte[] body) throws Exception {

        URL url = new URL(r_url);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        Log.i("works?", "yes");

        // Set up the header
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoInput(true);
        conn.setDoOutput(true);

        try (OutputStream outputStream = conn.getOutputStream()) {

            outputStream.write(body, 0, body.length);

        }

        //Send the message to the server
//        OutputStream os = conn.getOutputStream();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//        writer.write(encodeParams(postDataParams));
//        writer.flush();
//        writer.close();
//        os.close();

        int responseCode = conn.getResponseCode(); // To Check for 200
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        }
        return null;
    }

    // method to receive data from server
    public static String sendGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Response Code :: " + responseCode);
        if (responseCode == HttpsURLConnection.HTTP_OK) { // connection ok
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            return response.toString();
        } else {
            System.out.println("Not connected");
            return "";
        }
    }

//    private static String encodeParams(JSONObject params) throws Exception {
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//        Iterator<String> itr = params.keys();
//        while(itr.hasNext()){
//            String key= itr.next();
//            Object value = params.get(key);
//            if (first) {
//                first = false;
//            } else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//        }
//        return result.toString();
//    }

}


