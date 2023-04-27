package com.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.utils.FrameworkConfig;
import com.utils.ScreenshotUtils;
import org.aeonbits.owner.ConfigFactory;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;
import static com.reports.ReportsManager.getReports;
import static com.utils.ScreenshotUtils.getBase64;

/**
 * A class that provides various logging methods to generate reports.
 */
public class ReportsLogger {
    // Initializing a FrameworkConfig object to read configuration values
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    /**
     * Logs a passing message to the report.
     *
     * @param message The message to log as passing message.
     */
    public static void pass(String message) {
        getReports().pass(message);
    }

    /**
     * Logs a passing message with markup to the report.
     *
     * @param message The markup message to log as passing message.
     */
    public static void pass(Markup message) {
        getReports().pass(message);
    }

    /**
     * Logs a failing message to the report.
     *
     * @param message The message to log as failing message.
     */
    public static void fail(String message) {
        getReports().fail(message);
    }

    /**
     * Logs a failing message with markup to the report.
     *
     * @param message The markup message to log as failing message.
     */
    public static void fail(Markup message) {
        getReports().fail(message);
    }

    /**
     * Logs a skipped message to the report.
     *
     * @param message The message to log as skipped message.
     */
    public static void skip(String message) {
        getReports().skip(message);
    }

    /**
     * Logs a skipped message with markup to the report.
     *
     * @param message The markup message to log as skipped message.
     */
    public static void skip(Markup message) {
        getReports().skip(message);
    }

    /**
     * Logs an informational message with markup to the report.
     *
     * @param message The markup message to log as informational message.
     */
    public static void info(Markup message) {
        getReports().info(message);
    }

    /**
     * Logs an informational message with markup to the report.
     *
     * @param message The markup message to log as informational message.
     */
    public static void info(String message) {
        getReports().info(message);
    }

    /**
     * Logs a passing message to the report along with a screenshot if required.
     *
     * @param message              The message to log as passing message.
     * @param isScreenshotRequired A boolean value that specifies if a screenshot is required.
     */
    public static void pass(String message, boolean isScreenshotRequired) {
        if (frameworkConfig.passedstepscreenshot().equalsIgnoreCase("yes") && isScreenshotRequired) {
            getReports().pass(message, createScreenCaptureFromBase64String(getBase64()).build());
        } else {
            pass(message);
        }
    }

    /**
     * Logs a passing message with markup to the report along with a screenshot if required.
     *
     * @param message           The markup message to log as passing message.
     * @param isScreeshotNeeded A boolean value that specifies if a screenshot is required.
     */
    public static void pass(Markup message, boolean isScreeshotNeeded) {
        if (frameworkConfig.passedstepscreenshot().equalsIgnoreCase("yes") && isScreeshotNeeded) {
            getReports().pass(createScreenCaptureFromBase64String(getBase64()).build());
            getReports().pass(message);
        } else {
            pass(message);
        }
    }

    /**
     * This method is used to fail a test step and take a screenshot if required.
     *
     * @param message              The message to be displayed along with the failed step.
     * @param isScreenshotRequired boolean flag indicating whether to take a screenshot or not.
     */
    public static void fail(String message, boolean isScreenshotRequired) {
        if (frameworkConfig.failedstepscreenshot().equalsIgnoreCase("yes") && isScreenshotRequired) {
            getReports().pass(message, createScreenCaptureFromBase64String(getBase64()).build());
        } else {
            fail(message);
        }
    }

    /**
     * This method is used to fail a test step and add a Markup message if required.
     *
     * @param message           the Markup message to be displayed along with the failed step.
     * @param isScreeshotNeeded boolean flag indicating whether to take a screenshot or not.
     */
    public static void fail(Markup message, boolean isScreeshotNeeded) {
        if (frameworkConfig.failedstepscreenshot().equalsIgnoreCase("yes") && isScreeshotNeeded) {
            getReports().fail(createScreenCaptureFromBase64String(getBase64()).build());
            getReports().fail(message);
        } else {
            fail(message);
        }
    }

    /**
     * This method is used to skip a test step and take a screenshot if required.
     *
     * @param message              the message to be displayed along with the skipped step.
     * @param isScreenshotRequired boolean flag indicating whether to take a screenshot or not.
     */
    public static void skip(String message, boolean isScreenshotRequired) {
        if (frameworkConfig.skippedstepscreenshot().equalsIgnoreCase("yes") && isScreenshotRequired) {
            getReports().pass(message, createScreenCaptureFromBase64String(getBase64()).build());
        } else {
            skip(message);
        }
    }

    /**
     * This method is used to skip a test step and add a Markup message if required.
     *
     * @param message           the Markup message to be displayed along with the skipped step.
     * @param isScreeshotNeeded boolean flag indicating whether to take a screenshot or not.
     */
    public static void skip(Markup message, boolean isScreeshotNeeded) {
        if (frameworkConfig.skippedstepscreenshot().equalsIgnoreCase("yes") && isScreeshotNeeded) {
            getReports().skip(createScreenCaptureFromBase64String(getBase64()).build());
            getReports().skip(message);
        } else {
            skip(message);
        }
    }
}