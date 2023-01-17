package com.digicorp.pagelayers;

import com.digicorp.driver.DriverManager;
import com.digicorp.enums.WaitTill;
import com.digicorp.reports.ExtentReportLogger;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.digicorp.factories.WaitFactory.*;

public class BasePage {
    protected static void click(By by, WaitTill waitTill, String element) throws Exception {
        performExplicitWait(waitTill, by).click();
        ExtentReportLogger.pass(element + " has been clicked", false);
    }

    protected static void click(WebElement element, WaitTill waitTill, String value) throws Exception {
        performExplicitWaitForElement(waitTill, element).click();
        ExtentReportLogger.pass(element.getText() + " has been clicked", false);
    }

    protected static void sendKeys(By by, String value, WaitTill waitTill, String element) throws Exception {
        performExplicitWait(waitTill, by).sendKeys(value);
        ExtentReportLogger.pass(value + " has been entered in " + element, false);
    }

    protected static void selectDesiredOption(By by, String value) throws Exception {
        for (WebElement e : performExplicitWaitForElements(WaitTill.VISIBLE, by)) {
            if(e.getAttribute("innerText").equals(value)) {
                e.click();
                ExtentReportLogger.pass(value + " has been selected", false);
                break;
            }
        }
    }

    protected static WebElement fetchElement(By by, WaitTill waitTill) {
        return performExplicitWait(waitTill, by);
    }

    protected static List<WebElement> fetchElements(By by, WaitTill waitTill) {
        return performExplicitWaitForElements(waitTill, by);
    }

    protected static WebDriver fetchDriver() {
        return DriverManager.getDriver();
    }

    protected static void selectDesiredOption(By by, WaitTill waitTill, String element) throws Exception {
        int optionsIndex = new Faker().random().nextInt(0, fetchElements(by, waitTill).size());
        click(DriverManager.getDriver().findElements(by).get(optionsIndex), waitTill, element);
    }

    protected static WebElement generateElementUsingDynamicXpath(WaitTill waitTill, String xpath) {
        return performExplicitWaitForElement(waitTill, DriverManager.getDriver().findElement(By.xpath(xpath)));
    }
}
