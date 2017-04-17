
package com.example.dean.weatherreport.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class WeatherData {

    public abstract @NonNull City city();
    public abstract @NonNull String cod();
    public abstract @NonNull Double message();
    public abstract @NonNull Integer cnt();
    public abstract @NonNull java.util.List<List> list();

    public static TypeAdapter<WeatherData> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_WeatherData.GsonTypeAdapter(gson);
    }
}
