package com.example.dean.weatherreport.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dean.weatherreport.R;
import com.example.dean.weatherreport.adapters.AutoValueGsonTypeAdapterFactory;
import com.example.dean.weatherreport.adapters.ForecastAdapter;
import com.example.dean.weatherreport.api.OpenWeatherMapClient;
import com.example.dean.weatherreport.model.WeatherData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements ForecastAdapter.ListItemClickListener {

    private static final String API_KEY = "00ece9005440c91a0a4df58ea28f8349";

    private RecyclerView mForecastRecycler;
    private ForecastAdapter mForecastAdapter;
    private WeatherData mWeatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForecastRecycler = (RecyclerView) findViewById(R.id.rv_forecast);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mForecastRecycler.setLayoutManager(layoutManager);
        mForecastRecycler.setHasFixedSize(true);

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
                .create()
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(gsonConverterFactory)
                .build();

        OpenWeatherMapClient client = retrofit.create(OpenWeatherMapClient.class);
        Call<WeatherData> call = client.getWeatherData("zagreb", "metric", "7", API_KEY);

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                mWeatherData = response.body();
                mForecastAdapter = new ForecastAdapter(MainActivity.this,
                        mWeatherData, MainActivity.this);
                mForecastRecycler.setAdapter(mForecastAdapter);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.WEATHER_FOR_DAY_EXTRA,
                mWeatherData.list().get(clickedItemIndex));
        startActivity(intent);
    }
}
