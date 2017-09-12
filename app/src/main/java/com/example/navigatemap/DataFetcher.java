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
    private static final String API_KEY = "AIzaSyCDyevc9QSATeOsRGEAE6vX3TJfXv4eydQ";
    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String LANG="ru";
    private static final String RESULT_TYPE="country";

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


    public String fetch(Double lat, Double lng) {

        String url = Uri.parse(API_URL)
                .buildUpon()
                .appendQueryParameter("latlng", lat.toString() + ',' + lng.toString())
                .appendQueryParameter("result_type", RESULT_TYPE)
                .appendQueryParameter("language", LANG)
                .appendQueryParameter("key", API_KEY)
                .build().toString();


        try {
            String jsonString = getJSONString(url);
            Log.e("jsonString", jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            JSONArray resultsJSONArray = jsonBody.getJSONArray("results");

            JSONObject formatted_address = resultsJSONArray.getJSONObject(0);
            String country = String.valueOf(formatted_address.getString("formatted_address"));

            Log.e("country", country);
            return country;


        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Ощибка загрузки данных", e);
            Log.e("url for getJSONString", url);

        } catch (JSONException e) {
            Log.e(TAG, "Ошибка парсинга JSON", e);
        }

        return "ERROR";
    }


}
