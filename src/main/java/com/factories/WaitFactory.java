package com.factories;

import com.enums.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.driver.DriverManager.getDriver;

/**
 * A utility class that provides methods to wait for web elements to appear, disappear or become clickable
 * <p>
 * based on the provided conditions. It also provides methods to highlight elements on the web page
 * <p>
 * for better visibility.
 */
public class WaitFactory {

    private WaitFactory() {
    }

    /**
     * Highlights the given web element by adding a red border around it.
     *
     * @param element The web element to be highlighted.
     */
    private static void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * Highlights the given list of web elements by adding a red border around them.
     *
     * @param elements The list of web elements to be highlighted.
     */
    private static void highlightElements(List<WebElement> elements) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        for (WebElement element : elements) {
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }

    /**
     * Waits for a web element to appear, disappear or become clickable based on the provided condition.
     *
     * @param by      The By locator for the web element to be located.
     * @param waitFor The condition to wait for.
     * @return The located web element.
     */
    public static WebElement waitForElement(By by, Waits waitFor) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement element = null;

        switch (waitFor) {
            case VISIBLITY -> element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            case PRESENCE -> element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            case CLICKABLE -> element = wait.until(ExpectedConditions.elementToBeClickable(by));
            case NONE -> element = getDriver().findElement(by);
        }
        highlightElement(element);
        return element;
    }

    /**
     * Waits for a list of web elements to appear or disappear based on the provided condition.
     *
     * @param by      The By locator for the web elements to be located.
     * @param waitFor The condition to wait for.
     * @return The located list of web elements.
     */
    public static List<WebElement> waitForElements(By by, Waits waitFor) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        List<WebElement> elements = null;

        switch (waitFor) {
            case VISIBLITY -> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            case PRESENCE -> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        }
        if (Objects.nonNull(elements)) {
            highlightElements(elements);
        }
        return elements;
    }

    /**
     * Waits for a web element to appear or become clickable based on the provided condition.
     *
     * @param element The web element to wait for.
     * @param waitFor The condition to wait for.
     * @return The waited web element.
     */
    public static WebElement waitForElement(WebElement element, Waits waitFor) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        switch (waitFor) {
            case VISIBLITY -> element = wait.until(ExpectedConditions.visibilityOf(element));
            case CLICKABLE -> element = wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        highlightElement(element);
        return element;
    }

    /**
     * Waits for the specified web element's attribute to have a non-empty value
     *
     * @param element the web element to wait for the attribute value
     * @return the attribute value of the web element if it has a non-empty value; otherwise, null
     * @throws TimeoutException       if the attribute value is not found within the specified timeout period
     * @throws NoSuchElementException if the specified element is not found on the page
     */
    public static String waitForElementAttribute(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        boolean value = wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
        return value ? element.getAttribute("value") : null;
    }
}
