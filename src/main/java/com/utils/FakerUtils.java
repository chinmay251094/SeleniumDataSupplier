package com.utils;

import com.github.javafaker.Faker;

/**
 * The {@code FakerUtils} class is a utility class that provides methods for generating fake data using the Faker library.
 * <p>
 * This class cannot be instantiated as it contains only static methods.
 */
public final class FakerUtils {

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private FakerUtils() {
    }

    /**
     * The Faker instance used to generate fake data.
     */
    private static final Faker fake = new Faker();

    /**
     * Generates a random integer between the start and end values (inclusive).
     *
     * @param start the start value for the range of integers
     * @param end   the end value for the range of integers
     * @return a random integer between the start and end values (inclusive)
     */
    static int getNumber(int start, int end) {
        return fake.number().numberBetween(start, end);
    }

    /**
     * Generates a random full name.
     *
     * @return a random full name
     */
    static String getString() {
        return fake.name().fullName();
    }

    /**
     * Generates a random city name.
     *
     * @return a random city name
     */
    static String getCity() {
        return fake.address().cityName();
    }
}
