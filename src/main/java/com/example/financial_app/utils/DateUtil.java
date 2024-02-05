package com.example.financial_app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
        throw new AssertionError("DateUtil class should not be instantiated.");
    }

    // Get the current date as a LocalDate object
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    // Format a LocalDate to a String using a specific format
    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}

