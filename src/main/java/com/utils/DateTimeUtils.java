package com.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateTimeUtils class provides utility methods for retrieving the current date and time in a specific format.
 */
public class DateTimeUtils {

    /**
     * Private constructor to prevent external instantiation of this class.
     */
    private DateTimeUtils() {
    }

    /**
     * Retrieves the current date and time in the format of "ddMMyyyy HH'h'mm'm'ss's'" and returns it as a string.
     *
     * @return the current date and time in the specified format.
     */
    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy HH'h'mm'm'ss's'");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
