package com.utils;

import static com.utils.BrowserInfoUtils.*;

/**
 * A utility class to get information about the operating system, browser, and browser version.
 */
public final class BrowserOSInfoUtils {

    /**
     * Private constructor to avoid external instantiation.
     */
    private BrowserOSInfoUtils() {
    }

    /**
     * Returns a string containing the operating system, browser, and browser version information.
     *
     * @return a string with the OS, browser, and browser version information.
     */
    public static String getOS_Browser_BrowserVersionInfo() {
        return OSInfoUtils.getOSInfo() + " & " + getBrowserInfo() + " - "
                + getBrowserVersionInfo();
    }
}

