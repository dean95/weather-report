package com.example.dean.weatherreport.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private DateUtils() {

    }

    public static String formatDate(Long unixTimestamp) {
        Date date = new Date(unixTimestamp * 1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH);

        return dateFormat.format(date);
    }
}
