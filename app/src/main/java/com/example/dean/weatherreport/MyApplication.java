package com.example.dean.weatherreport;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).openDatabasesOnInit(true).build());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }
}
