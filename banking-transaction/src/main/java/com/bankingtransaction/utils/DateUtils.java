package com.bankingtransaction.utils;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class InstantUtils {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ss:mm:hh");
    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date) {
        return dateFormat.parse(date);
    }
}
