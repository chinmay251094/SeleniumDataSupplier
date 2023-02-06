package com.factories;

import com.enums.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.driver.DriverManager.getDriver;

public class WaitFactory {
    private WaitFactory() {}

    private static void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    private static void highlightElements(List<WebElement> elements) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        for (WebElement element : elements) {
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }

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

    public static List<WebElement> waitForElements(By by, Waits waitFor) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        List<WebElement> elements = null;

        switch (waitFor) {
            case VISIBLITY -> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            case PRESENCE -> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        }
        if(Objects.nonNull(elements)) {
            highlightElements(elements);
        }
        return elements;
    }

    public static WebElement waitForElement(WebElement element, Waits waitFor) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        switch (waitFor) {
            case VISIBLITY -> element = wait.until(ExpectedConditions.visibilityOf(element));
            case CLICKABLE -> element = wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        highlightElement(element);
        return element;
    }
}
