package com.bankingtransaction.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date) {
        try {
             return dateFormat.parse(date);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return null;
    }
}
