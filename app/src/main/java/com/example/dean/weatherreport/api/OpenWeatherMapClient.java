package com.example.dean.weatherreport.api;

import com.example.dean.weatherreport.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapClient {

    @GET("data/2.5/forecast/daily?")
    Call<WeatherData> getWeatherData(
            @Query("q") String city,
            @Query("units") String units,
            @Query("cnt") String cnt,
            @Query("appid") String apiKey);
}
