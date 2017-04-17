
package com.example.dean.weatherreport.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Temp {

    public abstract @NonNull Double day();
    public abstract @NonNull Double min();
    public abstract @NonNull Double max();
    public abstract @NonNull Double night();
    public abstract @NonNull Double eve();
    public abstract @NonNull Double morn();

    public static TypeAdapter<Temp> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_Temp.GsonTypeAdapter(gson);
    }
}
