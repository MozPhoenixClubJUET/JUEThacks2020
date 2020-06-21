package com.example.android.hackathonapp.NetworkUtils;

import android.util.Log;

import com.example.android.hackathonapp.modals.history;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class state_jsonParse {

    public static List<history> parse(JSONObject jsonString) throws JSONException {

        JSONArray root = null;
        List<history> HistoryList = new ArrayList<>();

        try{
            root = jsonString.getJSONArray("statewise");
        }catch (Exception e){
            Log.e("this", String.valueOf(e));
        }

        Log.e("this", String.valueOf(root.length()));

        for(int i = 1; i < root.length(); i++){
            JSONObject current = root.getJSONObject(i);
            String state, totalconfirmed, totalrecovered, totaldeceased;

            state = current.getString("state");
            totalconfirmed = current.getString("confirmed");
            totalrecovered = current.getString("recovered");
            totaldeceased = current.getString("deaths");

            history currentHistory = new history(state, totalconfirmed, totalrecovered, totaldeceased);
            HistoryList.add(currentHistory);

        }

        Log.e("this", String.valueOf(HistoryList.size()));

        return HistoryList;
    }

}
