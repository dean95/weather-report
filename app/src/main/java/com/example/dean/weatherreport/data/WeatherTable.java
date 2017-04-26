package com.example.dean.weatherreport.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = WeatherDatabase.class)
public class WeatherTable extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    @NotNull
    @Unique(onUniqueConflict = ConflictAction.REPLACE)
    Long date;

    @Column
    @NotNull
    int weatherId;

    @Column
    @NotNull
    Double minTemp;

    @Column
    @NotNull
    Double maxTemp;

    @Column
    @NotNull
    Double humidity;

    @Column
    @NotNull
    Double pressure;

    @Column
    @NotNull
    Double windSpeed;

    @Column
    @NotNull
    Double degrees;
}
