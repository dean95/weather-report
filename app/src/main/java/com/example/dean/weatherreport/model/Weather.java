
package com.example.dean.weatherreport.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Weather {

    public abstract @NonNull Integer id();
    public abstract @NonNull String main();
    public abstract @NonNull String description();
    public abstract @NonNull String icon();

    public static TypeAdapter<Weather> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_Weather.GsonTypeAdapter(gson);
    }
}
