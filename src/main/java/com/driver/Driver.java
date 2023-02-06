package com.driver;

import com.enums.Browsers;
import com.enums.RunModes;

import java.time.Duration;
import java.util.Objects;
import static com.driver.DriverManager.*;
import static com.factories.DriverFactory.*;

public class Driver {
    private static String urlForReports = null;

    public static String getURLforReports() {
        return urlForReports;
    }

    public static void initDriver(Browsers browser, String url, RunModes mode, String description, String version) {
        if(Objects.isNull(getDriver())) {
            urlForReports = url;
            setDriver(getDriver(browser, mode, description, version));
            getDriver().manage().window().maximize();
            getDriver().get(url);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public static void quitDriver() {
        if(Objects.nonNull(getDriver())) {
            getDriver().quit();
            unLoad();
        }
    }
}
