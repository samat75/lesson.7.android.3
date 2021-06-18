package com.example.lesson7android3.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Prefs {

    private static SharedPreferences sharedPreferences;
    private static final String MAPS_KEY = "Latlng";
    public static final String PREFS_KEY = "prefs";

    public Prefs(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    public void saveLocation(List<LatLng> lng) {
        Gson gson = new Gson();
        String list = gson.toJson(lng);
        sharedPreferences.edit().putString(MAPS_KEY, list).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public List<LatLng> getLocation() {
        List<LatLng> list;
        String gsonStr = sharedPreferences.getString(MAPS_KEY, null);
        Type type = new TypeToken<List<LatLng>>() {
        }.getType();
        Gson gson = new Gson();
        list = gson.fromJson(gsonStr, type);
        if (list == null)
            list = new ArrayList<>();

        return list;
    }
}