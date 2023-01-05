package com.digicorp.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.digicorp.constants.FrameworkConstants;
import com.digicorp.driver.DriverManager;
import com.digicorp.enums.Groups;
import com.digicorp.enums.SDET;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public final class ExtentReport {
    static ExtentReports extentReports;

    private ExtentReport() {
    }

    public static void initReports() throws Exception {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
            extentReports.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Reports");
            spark.config().setReportName("Pay-Fils Test Reports");
        }
    }

    public static void flushReports() throws Exception {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ExtentReportManager.unLoad();
        Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
    }

    public static void createTests(String testcasename) {
        ExtentReportManager.setReport(extentReports.createTest(testcasename));
    }

    public static void addAuthors(SDET[] authors) {
        for (SDET author : authors) {
            ExtentReportManager.getReport().assignAuthor(String.valueOf(author));
        }
    }

    public static void addCategory(Groups[] categories) {
        for (Groups category : categories) {
            ExtentReportManager.getReport().assignCategory(String.valueOf(category));
        }
    }

    public static void setBrowser() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String browser = capabilities.getBrowserName().substring(0, 1).toUpperCase() + capabilities.getBrowserName().substring(1);
        String version = capabilities.getVersion();
        System.out.println(browser + " " + version);
        ExtentReportManager.getReport().assignDevice(browser + " " + version);
    }
}
