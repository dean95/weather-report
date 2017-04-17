
package com.example.dean.weatherreport.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class List {

    public abstract @NonNull Long dt();
    public abstract @NonNull Temp temp();
    public abstract @NonNull Double pressure();
    public abstract @NonNull Integer humidity();
    public abstract @NonNull java.util.List<Weather> weather();
    public abstract @Nullable Double speed();
    public abstract @Nullable Integer deg();
    public abstract @Nullable Integer clouds();
    public abstract @Nullable Double rain();

    public static TypeAdapter<List> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_List.GsonTypeAdapter(gson);
    }
}