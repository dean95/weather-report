
package com.example.dean.weatherreport.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Coord {

    public abstract @NonNull Double lon();
    public abstract @NonNull Double lat();

    public static TypeAdapter<Coord> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_Coord.GsonTypeAdapter(gson);
    }
}
