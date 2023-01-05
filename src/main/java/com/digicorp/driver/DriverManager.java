package com.digicorp.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager extends ThreadLocal {
    private DriverManager() {}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverref) {
        driver.set(driverref);
    }

    public static void unload() {
        driver.remove();
    }
}
