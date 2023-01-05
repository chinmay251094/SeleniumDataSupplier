package com.digicorp.pagelayers;

import com.digicorp.driver.DriverManager;
import com.digicorp.enums.WaitTill;
import com.digicorp.factories.WaitFactory;
import com.digicorp.reports.ExtentReportLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    protected static void click(By by, WaitTill waitTill, String element) throws Exception {
        WaitFactory.performExplicitWait(waitTill, by).click();
        ExtentReportLogger.pass(element + " has been clicked", false);
    }

    protected static void sendKeys(By by, String value, WaitTill waitTill, String element) throws Exception {
        WaitFactory.performExplicitWait(waitTill, by).sendKeys(value);
        ExtentReportLogger.pass(value + " has been entered in " + element, false);
    }

    protected static void selectOptions(List<WebElement> ele, String value) throws Exception {
        for (WebElement e : WaitFactory.performExplicitWaitForElements(WaitTill.VISIBLE, ele)) {
            if(e.getAttribute("innerText").equals(value)) {
                e.click();
                ExtentReportLogger.pass(value + " has been selected", false);
                break;
            }
        }
    }

    protected static WebElement fetchElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    protected static List<WebElement> fetchElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    protected static WebDriver fetchDriver() {
        return DriverManager.getDriver();
    }
}
