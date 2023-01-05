package com.digicorp.driver;

import com.digicorp.factories.DriverFactory;

import java.net.MalformedURLException;
import java.util.Objects;

public final class Driver {
    private Driver() {
    }

    public static void initDriver(String browser, String url, String mode, String version) {
        if (Objects.isNull(DriverManager.getDriver())) {
            try {
                DriverManager.setDriver(DriverFactory.getDriver(browser, mode, version));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Browser invocation failure");
            }
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().get(url);
        }
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
