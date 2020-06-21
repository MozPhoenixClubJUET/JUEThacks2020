package com.example.android.hackathonapp.NetworkUtils;

import android.util.Log;

import com.example.android.hackathonapp.modals.history;
import com.example.android.hackathonapp.modals.news;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class history_jsonParse {

    public static List<history> parse(JSONObject jsonString) throws JSONException {

        JSONArray root = null;
        List<history> HistoryList = new ArrayList<>();

        try{
            root = jsonString.getJSONArray("cases_time_series");
        }catch (Exception e){
            Log.e("this", String.valueOf(e));
        }

        Log.e("this", String.valueOf(root.length()));

        for(int i = root.length()-1; i >= 0; i--){
            JSONObject current = root.getJSONObject(i);
            String date, totalconfirmed, totalrecovered, totaldeceased;

            date = current.getString("date");
            totalconfirmed = current.getString("totalconfirmed");
            totalrecovered = current.getString("totalrecovered");
            totaldeceased = current.getString("totaldeceased");

            history currentHistory = new history(date, totalconfirmed, totalrecovered, totaldeceased);
            HistoryList.add(currentHistory);

        }

        Log.e("this", String.valueOf(HistoryList.size()));

        return HistoryList;
    }
}
