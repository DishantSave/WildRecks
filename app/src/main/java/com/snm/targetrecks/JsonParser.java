package com.snm.targetrecks;

//helper class for map activity
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private HashMap<String , String> parserJsonObject(JSONObject object){
        HashMap<String, String> dataList = new HashMap<>();

        try {
            String name = object.getString("name");

//            get lat from obj
            String latitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lat");

//            get longitude from obj
            String longitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lng");
//            put all value in hash map
            dataList.put("name", name);
            dataList.put("lat", latitude);
            dataList.put("lng",longitude );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
//        return hash map
        return dataList;
    }
    private List<HashMap<String, String>> parserJsonArray(JSONArray jsonArray) {
        // Initialize hash map list
        List<HashMap<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                // Init hashmap
                HashMap<String, String> data = parserJsonObject((JSONObject) jsonArray.get(i));
                // Add data into hash map list
                dataList.add(data);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return dataList;
    }

    public List<HashMap<String ,String>> parseResults(JSONObject object){
        JSONArray jsonArray = null;
        try {
            jsonArray = object.getJSONArray("results");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
//return array
        return parserJsonArray(jsonArray);
    }
}
