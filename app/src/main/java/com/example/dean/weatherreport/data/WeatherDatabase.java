package com.example.dean.weatherreport.data;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider;

@ContentProvider(authority = WeatherDatabase.AUTHORITY,
        database = WeatherDatabase.class,
        baseContentUri = WeatherDatabase.BASE_CONTENT_URI)
@Database(name = WeatherDatabase.DB_NAME,
        version = WeatherDatabase.DB_VERSION)
public class WeatherDatabase {

    public static final String DB_NAME = "weather";
    public static final int DB_VERSION = 1;

    public static final String BASE_CONTENT_URI = "content://";
    public static final String AUTHORITY = "com.example.dean.weatherreport.data";
}
