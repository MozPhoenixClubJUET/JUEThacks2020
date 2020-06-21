package com.example.android.hackathonapp.NetworkUtils;

import android.util.Log;

import com.example.android.hackathonapp.modals.news;
import com.example.android.hackathonapp.modals.store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news_JsonParse {

    public static List<news> parse(String jsonString) throws JSONException {

        JSONObject root = new JSONObject(jsonString);
        List<news> NewsList = new ArrayList<>();

        JSONArray articles = root.getJSONArray("articles");

        for(int i = 0; i < articles.length(); i++) {
            JSONObject currentArticle = articles.getJSONObject(i);
            String headline = "", image = "", date = "", url = "";

            headline = currentArticle.getString("title");
            image = currentArticle.getString("urlToImage");
            url = currentArticle.getString("url");
            date = currentArticle.getString("publishedAt");
            date = date.replaceFirst("T", " ");
            date = date.replaceFirst("Z", "");

            news newsObject = new news(headline, image, date, url);
            NewsList.add(newsObject);

        }

        return NewsList;
    }

}
