package com.base;

import com.enums.Waits;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static com.driver.DriverManager.getDriver;
import static com.factories.WaitFactory.waitForElement;
import static com.factories.WaitFactory.waitForElements;
import static com.reports.ReportsLogger.info;

public class BasePage {
    protected static WebElement getElement(By by, Waits waits) {
        return waitForElement(by, waits);
    }

    protected static List<WebElement> getElements(By by, Waits waits) {
        return waitForElements(by, waits);
    }

    protected static void click(By by, Waits waits, String elementName) {
        WebElement element = waitForElement(by, waits);
        element.click();
        info("<b>" + elementName + "</b> is clicked");
    }

    protected static void click(WebElement element, Waits waits, String elementName) {
        waitForElement(element, waits).click();
        info("<b>" + elementName + "</b> is clicked");
    }

    protected static void sendKeys(By by, Waits waits, String value) {
        WebElement element = waitForElement(by, waits);
        element.sendKeys(value);
        info("Filling <b>" + value + "</b> in <b>" + element.getText() + "</b>");
    }

    protected static void pressKeys(By by, Waits waits, Keys key) {
        WebElement element = waitForElement(by, waits);
        element.sendKeys(key);
    }

    @SneakyThrows
    protected static void selectDesiredOption(By by, Waits waits, String value) {
        List<WebElement> elements = getElements(by, waits);
        elements.stream().filter(e -> e.getText().startsWith(value))
                .findAny().orElseThrow(() -> new Exception("Element not found")).click();
        info("<b>" + value + "</b> has been selected.");
    }

    @SneakyThrows
    protected static void selectOption(By by, Waits waits) {
        List<WebElement> elements = getElements(by, waits);
        int optionsIndex = new Faker().random().nextInt(0, elements.size());
        WebElement ele = elements.stream().filter(e -> elements.indexOf(e) == optionsIndex)
                .findFirst().orElseThrow(() -> new Exception("Element not found"));
        ele.click();
        info("<b>" + ele.getText() + "</b> has been selected.");
    }

    protected static void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,500)");
    }

    protected static WebElement generateDynamicElement(String str, String value) {
        String xpath = String.format(str, value);
        return getDriver().findElement(By.xpath(xpath));
    }

    protected static By generateDynamicByLocator(String str, String text) {
        return By.xpath(String.format(str, text));
    }

    protected static void synchronizeElements(long waitFor) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(waitFor));
    }

}
