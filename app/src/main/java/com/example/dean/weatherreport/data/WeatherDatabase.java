package com.example.dean.weatherreport.data;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = WeatherDatabase.DB_NAME, version = WeatherDatabase.DB_VERSION)
public class WeatherDatabase {

    public static final String DB_NAME = "weather";

    public static final int DB_VERSION = 1;
}
