package com.utils;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.constants.FrameworkConstants.ASSERTION_FOR;
import static com.reports.ReportsLogger.fail;
import static com.reports.ReportsLogger.pass;

/**
 * Utility class for performing validation checks and logging results.
 */
public class VerificationUtils {

    /**
     * Validates if two objects are equal and logs the result.
     *
     * @param actual   the actual object
     * @param expected the expected object
     * @param message  the message to be logged
     */
    public static void validate(Object actual, Object expected, String message) {
        try {
            logFile(actual, expected);
            Assert.assertEquals(actual, expected, message);
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b> | <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + " | <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
            Assertions.fail(message);
        }
    }

    /**
     * Validates if a list of web elements is not empty and logs the result.
     *
     * @param listOfWebElements the list of web elements
     * @param message           the message to be logged
     */
    public static void validate(List<WebElement> listOfWebElements, String message) {
        try {
            Assertions.assertThat(listOfWebElements).isNotEmpty();
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b> | <b><i>Actual: </i> </b>" + "<u>" + listOfWebElements.get(0).getText() + "</u>" + " and <b><i> Expected: </i> </b>" + "<u>Present</u>",
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + " | <b><i>Actual: </i> </b>" + "<u>" + listOfWebElements.get(0).getText() + "</u>" + " and <b><i> Expected: </i> </b>" + "<u>Not Present</u>");
            Assertions.fail(message);
        }
    }

    /**
     * Validates if two URLs are not equal and logs the result.
     *
     * @param actual   the actual URL
     * @param expected the expected URL
     * @param message  the message to be logged
     */
    public static void validateURL(Object actual, Object expected, String message) {
        try {
            logFile(actual, expected);
            Assert.assertNotEquals(actual, expected, message);
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b> | <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + " | <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
            Assert.fail(message);
        }
    }

    /**
     * Asserts that the initial URL and final URL are not equal and logs the actual and expected URLs.
     *
     * @param initialURL the initial URL
     * @param finalURL   the final URL
     * @param message    the message to display if the assertion fails
     */
    public static void assertURL(Object initialURL, Object finalURL, String message) {
        try {
            logFile(initialURL, finalURL);
            Assertions.assertThat(initialURL).isNotEqualTo(finalURL);
            pass(ASSERTION_FOR + " - <b> <u>" + message
                            + "</u> </b> | <b><i>Actual: </i> </b><u>" + initialURL + "</u> and <b><i> Expected: </i> </b><u>" + finalURL + "</u>",
                    true);
        } catch (AssertionError assertionError) {
            fail(ASSERTION_FOR + " - <b> <u>" + message
                    + " | <b><i>Actual: </i> </b>" + initialURL + " and <b><i> Expected: </i> </b>" + finalURL);
            Assert.fail(message);
        }
    }

    /**
     * Logs the actual and expected objects to the test log.
     *
     * @param actual   the actual object
     * @param expected the expected object
     */
    private static void logFile(Object actual, Object expected) {
        TestUtils.log().info("Actual: " + actual);
        TestUtils.log().info("Expected: " + expected);
    }

    /**
     * Validates that the given result is true and logs the given message.
     *
     * @param result  the result to validate
     * @param message the message to log if the result is false
     */
    public static void validateResponse(boolean result, String message) {
        try {
            Assert.assertTrue(result);
            pass("<b><i>" + message + "</b></i>", true);
        } catch (AssertionError assertionError) {
            fail("<b><i>" + message + "</b></i>");
            Assert.fail(message);
        }
    }
}