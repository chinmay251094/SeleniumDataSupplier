package com.utils;

/**
 * A utility class for obtaining information about the operating system.
 */
public final class OSInfoUtils {

    /**
     * Private constructor to prevent external instantiation of this utility class.
     */
    private OSInfoUtils() {
    }

    /**
     * Gets the name of the current operating system.
     *
     * @return The name of the current operating system.
     */
    public static String getOSInfo() {
        return System.getProperty("os.name").replace(" ", "_");
    }

}
