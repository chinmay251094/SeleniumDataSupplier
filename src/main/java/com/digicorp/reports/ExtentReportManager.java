package com.digicorp.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager {
    private ExtentReportManager() {}

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static ExtentTest getReport() {
        return extentTest.get();
    }

    static void setReport(ExtentTest rep) {
        extentTest.set(rep);
    }

    static void unLoad() {
        extentTest.remove();
    }

}
