package com.utils;

/**
 * The RandomUtils class provides utility methods for generating random data using the Faker library.
 */
public final class RandomUtils {

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private RandomUtils() {
    }

    /**
     * Generates a random integer between the specified start and end values.
     *
     * @return a random integer
     */
    public static int getId() {
        return FakerUtils.getNumber(100, 2000);
    }

    /**
     * Generates a random name.
     *
     * @return a random name
     */
    public static String getName() {
        return FakerUtils.getString();
    }

    /**
     * Generates a random email address based on the name.
     *
     * @return a random email address
     */
    public static String getEmail() {
        return getName().replaceAll("\s", "").toLowerCase().concat("@gmail.com");
    }

    /**
     * Generates a random city name.
     *
     * @return a random city name
     */
    public static String getCity() {
        return FakerUtils.getCity();
    }
}
