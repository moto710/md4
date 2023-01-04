package com.bankingtransaction.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InstantUtils {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern ("yyyy-MM-dd hh:mm:ss").withZone(ZoneId.systemDefault());
    public static String instantToString(Instant instant) {
        return dateFormat.format(instant);
    }

    public static Instant stringToInstant(String instant) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.US);
        LocalDateTime ldt = LocalDateTime.parse(instant, f);
        ZoneId z = ZoneId.systemDefault();
        ZonedDateTime zdt = ldt.atZone(z);
        return zdt.toInstant();
    }
}
