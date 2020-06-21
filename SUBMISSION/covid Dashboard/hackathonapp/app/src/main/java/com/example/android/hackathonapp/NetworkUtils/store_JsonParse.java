package com.example.android.hackathonapp.NetworkUtils;

import android.media.Image;
import android.util.Log;

import com.example.android.hackathonapp.modals.store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class store_JsonParse {

    public static List<store> parse(String jsonString) throws JSONException {

        JSONObject root = new JSONObject(jsonString);
        List<store> StoreList = new ArrayList<>();

        JSONArray items = root.getJSONArray("items");

        for(int i = 0; i < items.length(); i++){
            JSONObject currentStore = items.getJSONObject(i);
            String name = "", address = "", distance = "", lat = "", lon = "";

            try {
                name = currentStore.getString("title");

                JSONObject add = currentStore.getJSONObject("address");
                String label = add.getString("label");
                String[] arrOfStr = label.split(",", 10);
                for(int j = 1; j < arrOfStr.length; j++){
                    address += arrOfStr[j];
                    if(j < arrOfStr.length-1){
                        address += ",";
                    }
                }
                address = address.replaceFirst(" ", "");

                JSONObject position = currentStore.getJSONObject("position");
                lat = position.getString("lat");
                lon = position.getString("lng");

                distance = currentStore.getString("distance");
            }catch (Exception e){
                Log.e("this", String.valueOf(e));
                continue;
            }

            store storeObject = new store(name, address, distance, lat, lon);
            StoreList.add(storeObject);
        }

        return StoreList;
    }

}
