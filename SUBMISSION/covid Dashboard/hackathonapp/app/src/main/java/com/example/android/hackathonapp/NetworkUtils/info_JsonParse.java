package com.example.android.hackathonapp.NetworkUtils;

import android.util.Log;

import com.example.android.hackathonapp.modals.news;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class info_JsonParse {

    public static String[] parse(JSONObject jsonString) throws JSONException {

        JSONArray root = null;

        try{
            root = jsonString.getJSONArray("statewise");
        }catch (Exception e){
            Log.e("this", String.valueOf(e));
        }

        String[] info = new String[8];

        JSONObject total = root.getJSONObject(0);

        info[0] = total.getString("deltaconfirmed");
        info[1] = total.getString("confirmed");
        info[2] = "";
        info[3] = total.getString("active");
        info[4] = total.getString("deltarecovered");
        info[5] = total.getString("recovered");
        info[6] = total.getString("deltadeaths");
        info[7] = total.getString("deaths");

        return info;
    }

}
