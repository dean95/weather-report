package com.example.dean.weatherreport.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dean.weatherreport.R;
import com.example.dean.weatherreport.adapters.AutoValueGsonTypeAdapterFactory;
import com.example.dean.weatherreport.adapters.ForecastAdapter;
import com.example.dean.weatherreport.api.OpenWeatherMapClient;
import com.example.dean.weatherreport.data.WeatherTable;
import com.example.dean.weatherreport.model.WeatherData;
import com.example.dean.weatherreport.preferences.ForecastPreferences;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements ForecastAdapter.ListItemClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String API_KEY = "00ece9005440c91a0a4df58ea28f8349";
    private static final String OPEN_WEATHER_MAP_BASE_URL = "http://api.openweathermap.org/";

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        String location = ForecastPreferences.loadLocationFromPreferences(this, sharedPreferences);
        String units = ForecastPreferences.loadUnitsFromPreferences(this, sharedPreferences);
        String numberOfDays = ForecastPreferences.loadNumberOfDaysFromPreferences(this, sharedPreferences);

        makeNetworkRequest(location, units, numberOfDays);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.WEATHER_FOR_DAY_EXTRA,
                mWeatherData.list().get(clickedItemIndex));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_map:
                openLocationInMap();
                return true;
            case R.id.action_settings:
                Intent settingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(settingsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        makeNetworkRequest(
                ForecastPreferences.loadLocationFromPreferences(this, sharedPreferences),
                ForecastPreferences.loadUnitsFromPreferences(this, sharedPreferences),
                ForecastPreferences.loadNumberOfDaysFromPreferences(this, sharedPreferences)
        );
    }

    private void openLocationInMap() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String location = ForecastPreferences.loadLocationFromPreferences(this, sharedPreferences);

        Uri addressUri = Uri.parse("geo:0,0?q=" + location);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void makeNetworkRequest(String location, String units, String days) {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                new GsonBuilder()
                        .registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
                        .create()
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPEN_WEATHER_MAP_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();

        OpenWeatherMapClient client = retrofit.create(OpenWeatherMapClient.class);
        Call<WeatherData> call = client.getWeatherData(location, units, days, API_KEY);

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
}
