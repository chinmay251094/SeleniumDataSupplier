package com.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.driver.DriverManager.getDriver;

public class ScreenshotUtils {
    private ScreenshotUtils() {
    }

    public static String getBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
