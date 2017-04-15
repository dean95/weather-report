package com.example.dean.weatherreport.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dean.weatherreport.R;
import com.example.dean.weatherreport.api.OpenWeatherMapClient;
import com.example.dean.weatherreport.model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "00ece9005440c91a0a4df58ea28f8349";

    public TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = (TextView) findViewById(R.id.tv_json_reponse_test);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapClient client = retrofit.create(OpenWeatherMapClient.class);
        Call<WeatherData> call = client.getWeatherData("zagreb", "metric", "1", API_KEY);

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData weatherData = response.body();
                tvTest.setText(weatherData.getCod());

                for (com.example.dean.weatherreport.model.List list : weatherData.getList()) {
                    tvTest.append("\n" + list.getSpeed());
                    tvTest.append("\n" + list.getTemp().getMax());
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
