package com.digicorp.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.digicorp.utils.FrameworkConfig;
import com.digicorp.utils.ScreenshotUtils;
import org.aeonbits.owner.ConfigFactory;

public final class ExtentReportLogger {
    private ExtentReportLogger() {
    }

    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    public static void pass(String message) {
        ExtentReportManager.getReport().pass(message);
    }

    public static void fail(String message) {
        ExtentReportManager.getReport().fail(message);
    }

    public static void skip(String message) {
        ExtentReportManager.getReport().skip(message);
    }

    public static void pass(String message, boolean requireScreenshot) throws Exception {
        if (frameworkConfig.passedstepscreenshot().equalsIgnoreCase("yes")
                && requireScreenshot == true) {
            ExtentReportManager.getReport().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());

        } else {
            pass(message);
        }
    }

    public static void fail(String message, boolean requireScreenshot) throws Exception {
        if (frameworkConfig.failedstepscreenshot().equalsIgnoreCase("yes")
                && requireScreenshot == true) {
            ExtentReportManager.getReport().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());

        } else {
            fail(message);
        }
    }

    public static void skip(String message, boolean requireScreenshot) throws Exception {
        if (frameworkConfig.skippedstepscreenshot().equalsIgnoreCase("yes")
                && requireScreenshot == true) {
            ExtentReportManager.getReport().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64()).build());

        } else {
            skip(message);
        }
    }


}
