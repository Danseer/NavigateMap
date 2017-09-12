package com.example.navigatemap;

import android.net.Uri;
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
                .url(URLSpec)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();

        return result;
    }


    public String fetch(Double lat,Double lon) {
        String url = Uri.parse(API_URL)
                .buildUpon()
                .appendQueryParameter("latlng", lat.toString()+','+lon.toString())
                .appendQueryParameter("key", API_KEY)
                .build().toString();

/*
        try {
            String jsonString = getJSONString(url);
            return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";*/
        return url;
    }


}
