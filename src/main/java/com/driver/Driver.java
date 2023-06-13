package com.driver;

import com.enums.Browsers;
import com.enums.RunModes;

import java.time.Duration;
import java.util.Objects;

import static com.driver.DriverManager.*;
import static com.factories.DriverFactory.*;

public class Driver {
    /**
     * The Driver class provides methods to initialize and quit a WebDriver instance for test automation.
     *
     * @see Browsers
     * @see RunModes
     * @since 2023-04-25
     * /
     * public class Driver {
     * /*
     * <p>
     * The URL used for the test reports.
     */
    private static String urlForReports = null;

    /**
     * Returns the URL used for the test reports.
     *
     * @return the URL used for the test reports
     */
    public static String getURLforReports() {
        return urlForReports;
    }

    /**
     * Initializes the WebDriver instance based on the specified browser, mode, description, and version, and navigates to the specified URL.
     *
     * @param browser     the browser to use for the test
     * @param url         the URL to navigate to for the test
     * @param mode        the mode to run the test in
     * @param description the description of the test
     * @param version     the version of the browser to use for the test
     */
    public static void initDriver(Browsers browser, String url, RunModes mode, String description, String version, Boolean headless) {
        if (Objects.isNull(getDriver())) {
            urlForReports = url;
            setDriver(getDriver(browser, mode, description, version, headless));
            getDriver().manage().window().maximize();
            getDriver().get(url);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    /**
     * Quits the WebDriver instance and unloads any related resources.
     */
    public static void quitDriver() {
        if (Objects.nonNull(getDriver())) {
            getDriver().quit();
            unLoad();
        }
    }
}
