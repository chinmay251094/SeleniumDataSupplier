/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Selenium - Java with Docker, Git and Jenkins (https://www.testingminibytes.com/courses/selenium-java-with-docker-git-and-jenkins/)
 * Tutor: Amuthan Sakthivel (https://www.testingminibytes.com/)
 */

package com.utils;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.driver.DriverManager.getDriver;

//final -> We do not want any class to extend this class
public final class BrowserInfoUtils {

    //private -> We do not want anyone to create the object of this class
    //Private constructor to avoid external instantiation
    private BrowserInfoUtils() {
    }

    public static String getBrowserInfo() {
        Capabilities capabilities = ((RemoteWebDriver) getDriver()).getCapabilities();
        return capabilities.getBrowserName().toUpperCase();

    }

    public static String getBrowserVersionInfo() {
        Capabilities capabilities = ((RemoteWebDriver) getDriver()).getCapabilities();
        return capabilities.getBrowserVersion();

    }

}
