package com.example.navigatemap;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by Константин on 11.09.2017.
 */

public class DataFetcher {
    private static final String TAG = "DataFetcher";
    private static final String API_KEY = "AIzaSyDaafgvECggiEKFwvGUUqL8VIzgMqerLSI";
    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json";


    public DataFetcher() throws IOException {
    }

    public String getJSONString(String URLSpec) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=AIzaSyDaafgvECggiEKFwvGUUqL8VIzgMqerLSI")
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();

        return result;

    }


    public String fetch(Double lat, Double lng) {
        String item = "err";
        String url = Uri.parse(API_URL)
                .buildUpon()
                .appendQueryParameter("latlng", lat.toString() + ',' + lng.toString())
                .appendQueryParameter("key", API_KEY)
                .build().toString();


        try {
           String jsonString = getJSONString(url);
            //JSONObject jsonBody = new JSONObject(jsonString);
            //return Parsing(item, jsonBody);
            return url;
            //return jsonString;
        } catch (IOException e) {
           e.printStackTrace();
           Log.e(TAG, "Ощибка загрузки данных", e);
        }
            // catch (JSONException e) {
           // Log.e(TAG, "Ошибка парсинга JSON", e);
       // }

        return item;
    }

    private String Parsing(String s, JSONObject jsonBody) throws IOException, JSONException {
        JSONObject resultsJSONObject = jsonBody.getJSONObject("results");
        s = String.valueOf(resultsJSONObject.get("formatted_address"));

        //JSONObject formatted_addressJSONObject=resultsJSONObject.getJSONObject("formatted_address");

        //JSONArray resultsJSONArray=jsonBody.getJSONArray("results");
        // JSONObject resJSONObject=resultsJSONArray.getJSONObject(1);
        //resultsJSONArray.
        return s;


    }


}
