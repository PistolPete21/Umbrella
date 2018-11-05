package com.nerdery.umbrella.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTime {
    private static final SimpleDateFormat defaultFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
    public static final SimpleDateFormat militaryTimeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat timeFormatter = new SimpleDateFormat("h:00 a", Locale.ENGLISH);

    public static Date convertStringToDate(String dateTimeString) {
        return convertStringToDate(dateTimeString, defaultFormatter);
    }

    public static Date convertStringToDate(String dateTimeString, SimpleDateFormat formatter, boolean isUtcTime) {
        if (isUtcTime) {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        return convertStringToDate(dateTimeString, formatter);
    }

    public static Date convertStringToDate(String dateTimeString, SimpleDateFormat formatter) {
        try {
            return formatter.parse(dateTimeString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(Date dateTime, SimpleDateFormat formatter) {
        try {
            return formatter.format(dateTime);
        } catch (Exception ex) {
            return "";
        }
    }

    public static int toHours(String s) {
        String[] hourMin = s.split(":");
        return Integer.parseInt(hourMin[0]);
    }
}
