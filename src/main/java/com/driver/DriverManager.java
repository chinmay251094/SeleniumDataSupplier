package com.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    /**
     * A class representing a ThreadLocal WebDriver instance to handle concurrent browser sessions.
     * <p>
     * The class provides methods to get, set and unload the WebDriver instance.
     */
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return the WebDriver instance for the current thread
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param driver the WebDriver instance to set for the current thread
     */
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    /**
     * Unloads the WebDriver instance for the current thread.
     */
    public static void unLoad() {
        driverThreadLocal.remove();
    }
}
