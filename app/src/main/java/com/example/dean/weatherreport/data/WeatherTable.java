package com.example.dean.weatherreport.data;

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.annotation.provider.ContentUri;
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint;
import com.raizlabs.android.dbflow.structure.provider.BaseProviderModel;
import com.raizlabs.android.dbflow.structure.provider.ContentUtils;

@TableEndpoint(name = WeatherTable.NAME,
        contentProvider = WeatherDatabase.class)
@Table(database = WeatherDatabase.class,
        name = WeatherTable.NAME)
public class WeatherTable extends BaseProviderModel {

    public static final String NAME = "WeatherTable";

    @ContentUri(path = NAME, type = ContentUri.ContentType.VND_MULTIPLE + NAME)
    public static final Uri CONTENT_URI =
            ContentUtils.buildUriWithAuthority(WeatherDatabase.AUTHORITY);

    @Column
    @PrimaryKey(autoincrement = true)
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

    @Override
    public Uri getDeleteUri() {
        return WeatherTable.CONTENT_URI;
    }

    @Override
    public Uri getInsertUri() {
        return WeatherTable.CONTENT_URI;
    }

    @Override
    public Uri getUpdateUri() {
        return WeatherTable.CONTENT_URI;
    }

    @Override
    public Uri getQueryUri() {
        return WeatherTable.CONTENT_URI;
    }
}
