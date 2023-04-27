package com.listeners;

import com.annotations.TestDescription;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.enums.Author;
import com.enums.Category;
import com.utils.BrowserOSInfoUtils;
import com.utils.FrameworkConfig;
import com.utils.IconUtils;
import com.utils.ScreenRecoderUtils;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.testng.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.base.BasePage.takeScreenshot;
import static com.constants.FrameworkConstants.*;
import static com.driver.Driver.getURLforReports;
import static com.driver.DriverManager.getDriver;
import static com.reports.Reports.*;
import static com.reports.ReportsLogger.*;
import static com.reports.ReportsManager.getReports;

/**
 * The TestListener class implements the ITestListener and ISuiteListener interfaces and is used to listen to events that occur during test execution.
 * <p>
 * It keeps track of the number of passed, skipped, failed, and total test cases.
 * <p>
 * It also initializes and flushes the reports at the start and end of the suite.
 * <p>
 * It assigns test attributes such as description, author, and category to the test reports.
 * <p>
 * It assigns the device details to the test reports.
 * <p>
 * It logs the OS, browser, and browser version information.
 * <p>
 * It navigates to the test report URL.
 * <p>
 * It starts recording the screen if the configuration allows it.
 */
public class TestListener implements ITestListener, ISuiteListener {
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;
    static int count_totalTCs;

    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    private ScreenRecoderUtils screenRecorder;

    /**
     * The constructor initializes the screen recorder.
     */
    public TestListener() {
        try {
            screenRecorder = new ScreenRecoderUtils();
        } catch (IOException | AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The onStart method initializes the reports at the start of the suite.
     *
     * @param suite the test suite
     */
    @SneakyThrows
    @Override
    public void onStart(ISuite suite) {
        initReports();
    }

    /**
     * The onFinish method flushes the reports at the end of the suite.
     *
     * @param suite the test suite
     */
    @SneakyThrows
    @Override
    public void onFinish(ISuite suite) {
        flushReports();
    }

    /**
     * This method is called when a test starts.
     * <p>
     * It retrieves test information such as the description, authors, and categories from the TestDescription annotation and assigns them to the report.
     * <p>
     * It also assigns the device information to the report and starts video recording if configured.
     *
     * @param result the ITestResult object representing the test result
     */
    @Override
    public void onTestStart(ITestResult result) {
        count_totalTCs = count_totalTCs + 1;
        Author[] authors = new Author[0];
        Category[] categories = new Category[0];
        Method method = result.getMethod().getConstructorOrMethod().getMethod();

        // Get the test description from the TestDescription annotation if present
        String description = method.getName();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            authors = testDescription.author();
            categories = testDescription.category();
            assignTestAttributes(description, authors, categories);
        }

        // Assign device information to the report
        String browserNameAndVersion = getBrowserNameAndVersion(getDriver());
        getReports().assignDevice(browserNameAndVersion);

        // Log test information
        info(BOLD_START + IconUtils.getOSIcon() + " & " + IconUtils.getBrowserIcon() + " --------- "
                + BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + BOLD_END);
        info(ICON_AUTHOR + " Author(s): " + "<b>" + getAuthorList(authors) + "</b>");
        info(ICON_CATEGORY + " Category: " + "<b>" + getCategoryList(categories) + "</b>");
        info(ICON_Navigate_Right + " Navigating to : <a href=" + getURLforReports() + "><b>" + getURLforReports() + "</b></a>");

        // Start video recording if configured
        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            if (description != null && !description.isEmpty()) {
                screenRecorder.startRecording(description);
            }
        }
    }

    /**
     * This method is invoked when a test is passed. It increments the count of passed test cases and gets the description
     * of the test from the @TestDescription annotation. It then logs the success message to the test report and takes a
     * screenshot (if enabled) with the name "PASSED_[description]". It also stops the screen recording (if enabled) for
     * the passed test.
     *
     * @param result the result of the test
     */
    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        String description = null;
        count_passedTCs = count_passedTCs + 1;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                String logText = "<b>" + description + " is passed.</b>" + "  " + ICON_SMILEY_PASS;
                Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
                pass(markup_message, false);
            } else {
                throw new IllegalArgumentException("Test Description must not be null or empty");
            }
        }

        if (frameworkConfig.takescreenshots().toLowerCase().trim().equals("yes")) {
            takeScreenshot("PASSED_" + description);
        }

        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            screenRecorder.stopRecording(true);
        }
    }

    /**
     * This method is called each time a test method fails.
     * It retrieves the test method's description from the TestDescription annotation and
     * logs the failure with the description and a stack trace.
     * If configured to do so, it takes a screenshot of the failure and stops the screen recorder.
     *
     * @param result The result of the failed test method
     * @throws IllegalArgumentException if the test method's TestDescription annotation has a null or empty description
     */
    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        String description = null;
        count_failedTCs = count_failedTCs + 1;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                try {
                    // Log failure message with stack trace
                    fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
                    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
                    String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
                            + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
                            + "</details> \n";

                    fail(message);
                    String logText = BOLD_START + description + " has failed." + BOLD_END + "  " + ICON_SMILEY_FAIL;
                    Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
                    fail(markup_message, true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Test Description must not be null or empty");
            }
        }

        // Take screenshot if configured to do so
        if (frameworkConfig.takescreenshots().toLowerCase().trim().equals("yes")) {
            takeScreenshot("FAILED_" + description);
        }

        // Stop screen recorder if configured to do so
        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            screenRecorder.stopRecording(true);
        }
    }

    /**
     * This method is called when a test is skipped. It increments the count of skipped test cases and
     * <p>
     * retrieves the test description if present. If the description is not null or empty, it logs the
     * <p>
     * test as skipped and creates a label with the test name and a skip icon. If screenshots and/or
     * <p>
     * video recording are enabled, it takes a screenshot and stops the screen recorder respectively.
     *
     * @param result the test result object
     */
    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult result) {
        String description = null;
        count_skippedTCs = count_skippedTCs + 1;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
                skip(description + " has been skipped.", false);
                String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_SKIP;
                Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
                skip(markup_message, true);
            } else {
                throw new IllegalArgumentException("Test Description must not be null or empty");
            }
        }

        if (frameworkConfig.takescreenshots().toLowerCase().trim().equals("yes")) {
            takeScreenshot("SKIPPED_" + description);
        }

        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //not in use for now
    }

    @Override
    public void onStart(ITestContext context) {
        //not in use for now
    }

    @Override
    public void onFinish(ITestContext context) {
        //not in use for now
    }
}