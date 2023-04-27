package com.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.driver.DriverManager.getDriver;

/**
 * The ScreenshotUtils class provides utility methods for capturing screenshots in Selenium tests.
 */
public class ScreenshotUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ScreenshotUtils() {
    }

    /**
     * Captures a screenshot and returns the Base64 encoded string representation of the image.
     *
     * @return a Base64 encoded string representation of the screenshot image.
     */
    public static String getBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}

