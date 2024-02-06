package com.example.financial_app.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    // Get the current date as a LocalDate object
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    // Format a LocalDate to a String using a specific format
    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Date toDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

