package com.reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * The ReportsManager class provides methods for managing ExtentTest reports. It uses a ThreadLocal variable to store and retrieve reports for each thread.
 */
public class ReportsManager {

    // ThreadLocal variable for storing reports for each thread
    private static ThreadLocal<ExtentTest> reports = new ThreadLocal<>();

    /**
     * Returns the ExtentTest report for the current thread.
     *
     * @return ExtentTest report for the current thread
     */
    public static ExtentTest getReports() {
        return reports.get();
    }

    /**
     * Sets the ExtentTest report for the current thread.
     *
     * @param report the ExtentTest report to be set for the current thread
     */
    public static void setReports(ExtentTest report) {
        reports.set(report);
    }

    /**
     * Removes the ExtentTest report for the current thread.
     */
    public static void unLoad() {
        reports.remove();
    }
}
