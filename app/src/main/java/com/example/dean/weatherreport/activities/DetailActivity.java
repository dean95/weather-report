package com.example.dean.weatherreport.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dean.weatherreport.R;
import com.example.dean.weatherreport.model.List;

public class DetailActivity extends AppCompatActivity {

    public static final String WEATHER_FOR_DAY_EXTRA = "weather_for_day_extra";

    private TextView textView;
    private List weatherForDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView) findViewById(R.id.tv_test);

        Intent intentThatStartedActivity = getIntent();

        if (intentThatStartedActivity != null) {
            if (intentThatStartedActivity.hasExtra(WEATHER_FOR_DAY_EXTRA)) {
                weatherForDay = intentThatStartedActivity.getParcelableExtra(WEATHER_FOR_DAY_EXTRA);
                textView.setText(weatherForDay.weather().get(0).description());
            }
        }
    }
}
