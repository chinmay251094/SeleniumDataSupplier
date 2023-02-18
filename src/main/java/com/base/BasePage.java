package com.base;

import com.enums.Waits;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.constants.FrameworkConstants.*;
import static com.driver.DriverManager.getDriver;
import static com.factories.WaitFactory.waitForElement;
import static com.factories.WaitFactory.waitForElements;
import static com.reports.ReportsLogger.info;
import static com.utils.UsefulFunctionsUtils.waitForPageLoaded;

public class BasePage {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH'h'mm'm'ss's'");

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
        element.clear();
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

    public static void takeScreenshot(String testcase) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots/";
        File screenshotsDir = new File(screenshotsDirPath);
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        String screenshotFilePath = screenshotsDirPath + testcase + "_" + dateFormat.format(new Date()) + ".png";
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

    public void performDoubleClick(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(element).build().perform();
    }

    public void doubleClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("var evt = document.createEvent('MouseEvents');" +
                "evt.initMouseEvent('dblclick',true,true,window,0,0,0,0,0,false,false,false,false,0,null);" +
                "arguments[0].dispatchEvent(evt);", element);
    }

    protected static boolean validateElementPresent(By by) {
        return !getDriver().findElements(by).isEmpty();
    }

    protected static void performRightClick(By by, Waits waits) {
        Actions actions = new Actions(getDriver());
        actions.contextClick(getElement(by, waits)).perform();
    }

    protected static void performRightClickUsingKeyboard(By by, Waits waits) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(by, waits)).sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN)).perform();
    }

    public static void switchToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        if (tabs.size() < tabIndex) {
            throw new NoSuchElementException("No tab found with index: " + tabIndex);
        }
        getDriver().switchTo().window(tabs.get(tabIndex - 1));
    }

    public static void switchToLastTab() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static void openLinkInNewTab(String link) {
        Actions newTab = new Actions(getDriver());
        newTab.keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().get(link);
    }

    public boolean isSpecialCharPresent(char specialChar) {
        String specialCharAsString = String.valueOf(specialChar);
        WebElement body = getDriver().findElement(By.tagName("body"));
        return body.getText().contains(specialCharAsString);
    }

    public boolean isElementPresent(By locator) {
        try {
            WebElement element = getDriver().findElement(locator);
            return element != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isImagePresent(String imageSrc) {
        List<WebElement> images = getDriver().findElements(By.tagName("img"));
        for (WebElement image : images) {
            if (image.getAttribute("src").equals(imageSrc) && image.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    public boolean isLinkPresent(String linkText) {
        try {
            getDriver().findElement(By.linkText(linkText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void selectValueFromCustomDropdown(By dropdown, String xpath, String option) {
        WebElement dropdownElement = getDriver().findElement(dropdown);
        dropdownElement.click();
        List<WebElement> dropdownOptions = dropdownElement.findElements(By.xpath(xpath));
        for (WebElement dropdownOption : dropdownOptions) {
            if (dropdownOption.getText().equals(option)) {
                dropdownOption.click();
                return;
            }
        }
        throw new NoSuchElementException("Option not found in dropdown: " + option);
    }

    public static void selectRandomValueFromCustomDropdown(By dropdown, String xpath) {
        // Find the dropdown element
        WebElement dropdownElement = getDriver().findElement(dropdown);
        // Click on the dropdown to open the options
        dropdownElement.click();

        // Find all the options in the dropdown
        List<WebElement> options = getDriver().findElements(By.xpath(xpath));

        // Generate a random number between 0 and the number of options
        int index = (int) (Math.random() * options.size());

        // Click on the random option
        options.get(index).click();
    }

    public static String getTooltipText(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).build().perform();
        return element.getAttribute("title");
    }

    public List<WebElement> getULandLITagElements(By by, Waits waits) {
        return getElements(by, waits);
    }

    public static void clickElementUnderListByText(By list, Waits waits, String tagName, String text) {
        WebElement ulElement = getElement(list, waits);
        List<WebElement> liElements = ulElement.findElements(By.tagName(tagName));
        for (WebElement liElement : liElements) {
            if (liElement.getText().equals(text)) {
                liElement.click();
                break;
            }
        }
    }
}
