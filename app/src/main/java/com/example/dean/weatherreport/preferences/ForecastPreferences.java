package com.example.dean.weatherreport.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.dean.weatherreport.R;

public class ForecastPreferences {

    public static void defaultSetup(Context context) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String loadUnitsFromPreferences(Context context,
                                                  SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(
                context.getString(R.string.pref_units_key),
                context.getString(R.string.pref_units_metric_value)
        );
    }

    public static String loadLocationFromPreferences(Context context,
                                                     SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(
                context.getString(R.string.pref_location_key),
                context.getString(R.string.pref_location_default_value)
        );
    }

    public static String loadNumberOfDaysFromPreferences(Context context,
                                                         SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(
                context.getString(R.string.pref_number_of_days_key),
                context.getString(R.string.pref_number_of_days_default_value)
        );
    }
}
