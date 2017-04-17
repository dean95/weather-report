
package com.example.dean.weatherreport.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class City {

    public abstract @NonNull Integer id();
    public abstract @NonNull String name();
    public abstract @NonNull Coord coord();
    public abstract @NonNull String country();
    public abstract @NonNull Integer population();

    public static TypeAdapter<City> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_City.GsonTypeAdapter(gson);
    }
}
