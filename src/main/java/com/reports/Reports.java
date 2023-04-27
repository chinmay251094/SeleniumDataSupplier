package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.enums.Author;
import com.enums.Category;
import com.utils.IconUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.util.Objects;

import static com.constants.FrameworkConstants.*;
import static com.reports.ReportsManager.getReports;
import static com.reports.ReportsManager.setReports;

/**
 * This class handles the creation and management of Extent Reports,
 * which provides a visual representation of test execution results.
 */
public class Reports {
    /**
     * Static instance of ExtentReports that is created only once.
     */
    static ExtentReports extentReports;

    /**
     * Initializes the ExtentReports instance, creates a spark reporter, and attaches it to the ExtentReports instance.
     * <p>
     * Sets the theme, report name, document title, and system information for the organization, department, manager, and team members.
     *
     * @throws Exception if the report file path is not valid
     */
    public static void initReports() throws Exception {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(getExtentReportFilePath());
            extentReports.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setReportName("Test Automation Reports");
            sparkReporter.config().setDocumentTitle("Digicorp Information System");
            extentReports.setSystemInfo("Organization", "<img src='" + System.getProperty("user.dir") + "/src/test/resources/Digicorp_Logo.png' style='float:left; width:50px; height:50px; vertical-align:middle;'>&nbsp;&nbsp;" + "<b style='vertical-align:middle;'> Digicorp Information Systems </b>" + "<a href='" + ICON_ORGANIZATION_SITE + "'>" + ICON_ORGANIZATION_SITE + "</a>");
            extentReports.setSystemInfo("Department", "<i><b>Engineering (Automation Testing)</i></b>" + "  " + ICON_BUG);
            extentReports.setSystemInfo("Manager", "<i><b>" + MANAGER + "</i></b>" + " " + ICON_MANAGER);
            extentReports.setSystemInfo("Team Members", "<i><b>" + MEMBER1 + "</i></b> " + ICON_TEAM + "<br>" +
                    "<i><b>" + MEMBER2 + "</i></b> " + ICON_TEAM + "<br>" +
                    "<i><b>" + MEMBER3 + "</i></b> " + ICON_TEAM + "<br>");
        }
    }

    /**
     * Flushes the ExtentReports instance and unloads the ReportsManager.
     * Opens the report file path in the system's default web browser.
     *
     * @throws Exception if the report file path is not valid
     */
    public static void flushReports() throws Exception {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ReportsManager.unLoad();
        Desktop.getDesktop().browse(new File(getExtentReportFilePath()).toURI());
    }

    /**
     * Creates a new test under the ExtentReports instance with the specified test name.
     *
     * @param testName the name of the test to be created
     */
    public static void createTests(String testName) {
        setReports(extentReports.createTest("<span style='font-size:14px; font-weight:bold;'>" + IconUtils.getBrowserIcon() + ": " + testName + "</span>"));
    }

    /**
     * This method gets the name and version of the browser in use and returns it with the corresponding icon as a string.
     *
     * @param driver WebDriver instance for the current test.
     * @return A string with the icon and name of the browser in use.
     */
    public static String getBrowserNameAndVersion(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = cap.getBrowserVersion();
        String browserIcon = "";

        switch (browserName) {
            case "chrome":
                browserIcon = "<i class='fa fa-chrome' style='font-size:18px'></i>";
                break;
            case "firefox":
                browserIcon = "<i class='fa fa-firefox' style='font-size:18px'></i>";
                break;
            case "safari":
                browserIcon = "<i class='fa fa-safari' style='font-size:18px'></i>";
                break;
            case "internet explorer":
                browserIcon = "<i class='fa fa-internet-explorer' style='font-size:18px'></i>";
                break;
            default:
                browserIcon = "<i class='fa fa-browser' style='font-size:18px'></i>";
                break;
        }

        return browserIcon + " " + browserName + " " + browserVersion;
    }

    /**
     * This method assigns the provided description, authors, and categories to the ExtentReports object.
     *
     * @param description The description of the test.
     * @param authors     An array of Author objects representing the authors of the test.
     * @param categories  An array of Category objects representing the categories of the test.
     * @throws IllegalArgumentException If the provided description is null or empty.
     */
    public static void assignTestAttributes(String description, Author[] authors, Category[] categories) {
        if (description != null && !description.isEmpty()) {
            createTests(description);
            for (Author author : authors) {
                getReports().assignAuthor(author.name());
            }
            for (Category category : categories) {
                getReports().assignCategory(category.name());
            }
        } else {
            throw new IllegalArgumentException("Test Description must not be null or empty");
        }
    }

    /**
     * Returns a comma-separated list of author names from the given array of Author objects.
     *
     * @param authors An array of Author objects representing the authors of a test.
     * @return A String representing the comma-separated list of author names.
     */
    public static String getAuthorList(Author[] authors) {
        StringBuilder authorList = new StringBuilder();
        for (Author author : authors) {
            authorList.append(author.name()).append(", ");
        }
        return authorList.toString().substring(0, authorList.length() - 2);
    }

    /**
     * Returns a comma-separated list of category names from the given array of Category objects.
     *
     * @param categories An array of Category objects representing the categories of a test.
     * @return A String representing the comma-separated list of category names.
     */
    public static String getCategoryList(Category[] categories) {
        StringBuilder categoryList = new StringBuilder();
        for (Category category : categories) {
            categoryList.append(category.name()).append(", ");
        }
        return categoryList.toString().substring(0, categoryList.length() - 2);
    }
}