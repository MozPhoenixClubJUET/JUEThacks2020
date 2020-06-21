package com.example.android.hackathonapp.NetworkUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class store_networktask {

    public static JSONObject getJsonResponse(String url) throws IOException {

        URL urlc = createURL(url);
        Log.e("this", String.valueOf(urlc));
        JSONObject jsonString = null;
        try {
            jsonString = makehttprequest(urlc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private static URL createURL(String urlc) {
        URL url = null;
        try {
            url = new URL(urlc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private static JSONObject makehttprequest(URL url) throws IOException, JSONException {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setDoInput(true);
        conn.setRequestProperty("User-Agent", "android");
        conn.setRequestProperty("Accept", "application/json");
        conn.addRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        if (!url.getHost().equals(conn.getURL().getHost())) {
            conn.disconnect();
            return new JSONObject();
        }

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return new JSONObject(response.toString());
    }

}
