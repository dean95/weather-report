package com.example.dean.weatherreport.utilities;

import java.util.Date;

public class DateUtils {

    private DateUtils() {

    }

    public static Date formatDate(Long unixTimestamp) {
        Date date = new Date(unixTimestamp * 1000);

        return date;
    }
}
