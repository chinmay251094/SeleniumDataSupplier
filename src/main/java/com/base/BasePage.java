package com.base;

import com.constants.FrameworkConstants;
import com.enums.Waits;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static com.constants.FrameworkConstants.*;
import static com.driver.DriverManager.getDriver;
import static com.factories.WaitFactory.waitForElement;
import static com.factories.WaitFactory.waitForElements;
import static com.reports.ReportsLogger.info;
import static com.utils.UsefulFunctionsUtils.waitForPageLoaded;

public class BasePage {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void smartWait() {
        if (ACTIVE_PAGE_LOADED.trim().equals("true")) {
            waitForPageLoaded();
        }
        sleep(WAIT_SLEEP_STEP);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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

    protected static void sendKeys(By by, Waits waits, String value, String field) {
        WebElement element = waitForElement(by, waits);
        element.sendKeys(value);
        String text = element.getText();
        if (text.isEmpty()) {
            info("Filling <b>" + value + "</b> in <b>" + field + "</b>");
        } else {
            info("Filling <b>" + value + "</b> in <b>" + element.getText() + "</b>");
        }
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

    protected static void screenshotElement(By by, String elementName, Waits waits) {
        File scrFile = getElement(by, waits).getScreenshotAs(OutputType.FILE);
        try {
            File dir = new File(getScreenshotFilePathFilePath());
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileUtils.copyFile(scrFile, new File(dir + "/" + elementName + "-" + dateFormat.format(new Date()) + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void takeScreenshot(String screenName) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String screenshotFilePath = "./screenshots/" + screenName + "_" + dateFormat.format(new Date()) + ".png";
        File destination = new File(screenshotFilePath);
        try {
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    public static void hoverOverElement(By by, Waits waits) {
        WebElement menu = getElement(by, waits);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].hover();", menu);

    }

    public static void scrollToBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Boolean areMoreElements = true;
        while (areMoreElements) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            waitForElement(by, Waits.PRESENCE);
            areMoreElements = (Boolean) js.executeScript("return arguments[0]", "window.scrollY < document.body.scrollHeight");
        }
    }

    public void scrollToBottomOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script = "window.scrollTo(0, document.body.scrollHeight)";
        while (true) {
            js.executeScript(script);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int previousHeight = (int) (long) js.executeScript("return document.body.scrollHeight");
            js.executeScript(script);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int newHeight = (int) (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == previousHeight) {
                break;
            }
        }
    }
}
