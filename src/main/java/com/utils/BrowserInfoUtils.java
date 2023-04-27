package com.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.driver.DriverManager.getDriver;

/**
 * The BrowserInfoUtils class provides utility methods for getting browser information.
 */
public final class BrowserInfoUtils {

    /**
     * Private constructor to prevent external instantiation.
     */
    private BrowserInfoUtils() {
    }

    /**
     * Gets the browser name.
     *
     * @return a String value representing the name of the current browser
     */
    public static String getBrowserInfo() {
        Capabilities capabilities = ((RemoteWebDriver) getDriver()).getCapabilities();
        return capabilities.getBrowserName().toUpperCase();
    }

    /**
     * Gets the browser version.
     *
     * @return a String value representing the version of the current browser
     */
    public static String getBrowserVersionInfo() {
        Capabilities capabilities = ((RemoteWebDriver) getDriver()).getCapabilities();
        return capabilities.getBrowserVersion();
    }
}
