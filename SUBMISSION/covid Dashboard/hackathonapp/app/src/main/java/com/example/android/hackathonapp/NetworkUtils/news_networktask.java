package com.example.android.hackathonapp.NetworkUtils;

import android.security.NetworkSecurityPolicy;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class news_networktask {

    public static String getJsonResponse(String url) throws IOException {

        URL urlc = createURL(url);
        Log.e("this", String.valueOf(urlc));
        String jsonString = makehttprequest(urlc);

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

    private static String makehttprequest(URL url) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        try {
            InputStream in = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }catch (Exception e){
            Log.e("this", String.valueOf(e));
            return null;
        }finally {
            httpsURLConnection.disconnect();
        }
    }

}
