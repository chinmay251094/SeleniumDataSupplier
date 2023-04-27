package com.base;

import com.enums.Waits;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Month;
import java.util.*;

import static com.constants.FrameworkConstants.*;
import static com.driver.DriverManager.getDriver;
import static com.factories.WaitFactory.*;
import static com.reports.ReportsLogger.info;
import static com.utils.UsefulFunctionsUtils.waitForPageLoaded;

/**
 * The BasePage class provides methods to interact with web page elements and utilities for waiting and sleeping.
 */
public class BasePage {

    /**
     * The date format used to generate timestamps.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH'h'mm'm'ss's'");

    /**
     * Waits for the active page to finish loading and sleeps for a specified amount of time.
     */
    public static void smartWait() {
        if (ACTIVE_PAGE_LOADED.trim().equals("true")) {
            waitForPageLoaded();
        }
        sleep(WAIT_SLEEP_STEP);
    }

    /**
     * Sleeps for the specified number of seconds.
     *
     * @param seconds the number of seconds to sleep for.
     */
    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for an element to be present and returns it.
     *
     * @param by    the locator used to find the element.
     * @param waits the wait configuration to use.
     * @return the first WebElement matching the specified locator.
     */
    protected static WebElement getElement(By by, Waits waits) {
        return waitForElement(by, waits);
    }

    /**
     * Waits for multiple elements to be present and returns them.
     *
     * @param by    the locator used to find the elements.
     * @param waits the wait configuration to use.
     * @return a list of WebElements matching the specified locator.
     */
    protected static List<WebElement> getElements(By by, Waits waits) {
        return waitForElements(by, waits);
    }

    /**
     * Waits for an element to be present, then clicks on it.
     *
     * @param by          the locator used to find the element.
     * @param waits       the wait configuration to use.
     * @param elementName the name of the element being clicked.
     */
    protected static void click(By by, Waits waits, String elementName) {
        WebElement element = waitForElement(by, waits);
        element.click();
        info("<b>" + elementName + "</b> is clicked");
    }

    /**
     * Waits for an element to be present, then clicks on it.
     *
     * @param element     the element to click on.
     * @param waits       the wait configuration to use.
     * @param elementName the name of the element being clicked.
     */
    protected static void click(WebElement element, Waits waits, String elementName) {
        waitForElement(element, waits).click();
        info("<b>" + elementName + "</b> is clicked");
    }

    /**
     * Enters the provided value into a WebElement that is located using the provided By object.
     *
     * @param by    the By object used to locate the WebElement
     * @param waits the Waits object used to wait for the WebElement to become available
     * @param value the value to enter into the WebElement
     * @param field the name of the field that the value is being entered into
     */
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

    /**
     * Sends a keyboard key to a WebElement that is located using the provided By object.
     *
     * @param by    the By object used to locate the WebElement
     * @param waits the Waits object used to wait for the WebElement to become available
     * @param key   the keyboard key to send to the WebElement
     */
    protected static void pressKeys(By by, Waits waits, Keys key) {
        WebElement element = waitForElement(by, waits);
        element.sendKeys(key);
    }

    /**
     * Selects a WebElement that contains the provided value as its text from a list of WebElements located using the provided By object.
     *
     * @param by    the By object used to locate the WebElements
     * @param waits the Waits object used to wait for the WebElements to become available
     * @param value the text to search for in the WebElements
     * @throws Exception if the desired WebElement cannot be found
     */
    @SneakyThrows
    protected static void selectDesiredOption(By by, Waits waits, String value) {
        List<WebElement> elements = getElements(by, waits);
        elements.stream().filter(e -> e.getText().startsWith(value))
                .findAny().orElseThrow(() -> new Exception("Element not found")).click();
        info("<b>" + value + "</b> has been selected.");
    }

    /**
     * Selects a random WebElement from a list of WebElements located using the provided By object.
     *
     * @param by    the By object used to locate the WebElements
     * @param waits the Waits object used to wait for the WebElements to become available
     * @throws Exception if no WebElements are found
     */
    @SneakyThrows
    protected static void selectOption(By by, Waits waits) {
        List<WebElement> elements = getElements(by, waits);
        int optionsIndex = new Faker().random().nextInt(0, elements.size());
        WebElement ele = elements.stream().filter(e -> elements.indexOf(e) == optionsIndex)
                .findFirst().orElseThrow(() -> new Exception("Element not found"));
        ele.click();
        info("<b>" + ele.getText() + "</b> has been selected.");
    }

    /**
     * Scrolls down the web page by 500 pixels using JavascriptExecutor.
     */
    protected static void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,500)");
    }

    /**
     * Generates a WebElement using the provided XPath format string and value.
     *
     * @param str   the format string for the XPath
     * @param value the value to be formatted into the XPath
     * @return the WebElement located by the generated XPath
     */
    protected static WebElement generateDynamicElement(String str, String value) {
        String xpath = String.format(str, value);
        return getDriver().findElement(By.xpath(xpath));
    }

    /**
     * Generates a By object using the provided XPath format string and text.
     *
     * @param str  the format string for the XPath
     * @param text the text to be formatted into the XPath
     * @return the By object located by the generated XPath
     */
    protected static By generateDynamicByLocator(String str, String text) {
        return By.xpath(String.format(str, text));
    }

    /**
     * Pauses the thread for the specified amount of time, without being interrupted.
     *
     * @param waitFor the time to pause for in seconds
     */
    protected static void synchronizeElements(long waitFor) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(waitFor));
    }

    /**
     * Takes a screenshot of the WebElement located by the provided By object, and saves it to a file with the provided name and timestamp.
     *
     * @param by          the By object used to locate the WebElement to be screenshot
     * @param elementName the name to give the screenshot file
     * @param waits       the Waits object used to wait for the WebElement to become available
     */
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

    /**
     * Takes a screenshot of the current web page, and saves it to a file with the provided name and timestamp.
     *
     * @param testcase the name to give the screenshot file
     */
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
            System.out.println("Screenshot saved to file: " + screenshotFilePath);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    /**
     * Performs a hover action over the specified WebElement.
     *
     * @param by    the By object used to locate the WebElement to hover over
     * @param waits the Waits object used to wait for the WebElement to become available
     */
    public static void hoverOverElement(By by, Waits waits) {
        WebElement menu = getElement(by, waits);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].hover();", menu);
    }

    /**
     * Scrolls to the bottom of the page until the specified WebElement becomes available.
     *
     * @param by the By object used to locate the WebElement
     */
    public static void scrollToBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Boolean areMoreElements = true;
        while (areMoreElements) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            waitForElement(by, Waits.PRESENCE);
            areMoreElements = (Boolean) js.executeScript("return arguments[0]", "window.scrollY < document.body.scrollHeight");
        }
    }

    /**
     * Scrolls to the bottom of the page using a JavaScriptExecutor until the bottom of the page is reached.
     */
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

    /**
     * Performs a double-click action on the specified WebElement using the Actions class.
     *
     * @param element the WebElement to double-click
     */
    public void performDoubleClick(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(element).build().perform();
    }

    /**
     * Performs a double-click action on the specified WebElement using a JavaScriptExecutor.
     *
     * @param element the WebElement to double-click
     */
    public void doubleClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("var evt = document.createEvent('MouseEvents');" +
                "evt.initMouseEvent('dblclick',true,true,window,0,0,0,0,0,false,false,false,false,0,null);" +
                "arguments[0].dispatchEvent(evt);", element);
    }

    /**
     * Checks if an element is present on the page.
     *
     * @param by the By object used to locate the element
     * @return true if the element is present, false otherwise
     */
    protected static boolean validateElementPresent(By by) {
        return !getDriver().findElements(by).isEmpty();
    }

    /**
     * Performs a right-click action on the specified WebElement using the Actions class.
     *
     * @param by    the By object used to locate the WebElement to right-click
     * @param waits the Waits object used to wait for the WebElement to become available
     */
    protected static void performRightClick(By by, Waits waits) {
        Actions actions = new Actions(getDriver());
        actions.contextClick(getElement(by, waits)).perform();
    }

    /**
     * Performs a right-click action on the specified WebElement using the keyboard shortcut.
     *
     * @param by    the By object used to locate the WebElement to right-click
     * @param waits the Waits object used to wait for the WebElement to become available
     */
    protected static void performRightClickUsingKeyboard(By by, Waits waits) {
        Actions actions = new Actions(getDriver());
// Moves the mouse to the WebElement, holds down the Control key, clicks the mouse, and releases the Control key
        actions.moveToElement(getElement(by, waits)).sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN)).perform();
    }

    /**
     * Switches to the browser tab with the specified index.
     *
     * @param tabIndex the index of the tab to switch to
     * @throws NoSuchElementException if no tab is found with the specified index
     */
    public static void switchToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        if (tabs.size() < tabIndex) {
            throw new NoSuchElementException("No tab found with index: " + tabIndex);
        }
// Switches to the tab with the specified index
        getDriver().switchTo().window(tabs.get(tabIndex - 1));
    }

    /**
     * Switches to the last opened browser tab.
     */
    public static void switchToLastTab() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
// Switches to the last opened tab
        getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }

    /**
     * Opens a new tab and navigates to the specified URL.
     *
     * @param link the URL to navigate to in the new tab
     */
    public static void openLinkInNewTab(String link) {
        Actions newTab = new Actions(getDriver());
// Presses the Control key, clicks the mouse, and releases the Control key to open a new tab
        newTab.keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
// Switches to the second tab in the list, which is the new tab
        getDriver().switchTo().window(tabs.get(1));
// Navigates to the specified URL in the new tab
        getDriver().get(link);
    }

    /**
     * Determines if the specified special character is present on the current web page.
     *
     * @param specialChar the special character to search for on the web page
     * @return true if the special character is found, false otherwise
     */
    public boolean isSpecialCharPresent(char specialChar) {
        String specialCharAsString = String.valueOf(specialChar);
        WebElement body = getDriver().findElement(By.tagName("body"));
        return body.getText().contains(specialCharAsString);
    }

    /**
     * Determines if an element is present on the current web page.
     *
     * @param locator the By object used to locate the element
     * @return true if the element is found, false otherwise
     */
    public boolean isElementPresent(By locator) {
        try {
            WebElement element = getDriver().findElement(locator);
            return element != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if an image with the specified source is present and visible on the page.
     *
     * @param imageSrc the source of the image to check
     * @return true if the image is present and visible, false otherwise
     */
    public boolean isImagePresent(String imageSrc) {
        List<WebElement> images = getDriver().findElements(By.tagName("img"));
        for (WebElement image : images) {
            if (image.getAttribute("src").equals(imageSrc) && image.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a link with the specified link text is present on the page.
     *
     * @param linkText the link text to check
     * @return true if the link is present, false otherwise
     */
    public boolean isLinkPresent(String linkText) {
        try {
            getDriver().findElement(By.linkText(linkText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Selects a value from a custom dropdown by clicking on the dropdown and then clicking on the option with the
     * specified text.
     *
     * @param dropdown the By object used to locate the dropdown WebElement
     * @param xpath    the xpath expression used to locate the options in the dropdown
     * @param option   the text of the option to select
     * @throws NoSuchElementException if the specified option is not found in the dropdown
     */
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

    /**
     * Selects a random value from a custom dropdown using the specified XPath expression.
     *
     * @param dropdown the By object used to locate the dropdown WebElement
     * @param xpath    the XPath expression used to locate the dropdown options
     */
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

    /**
     * Retrieves the tooltip text for the specified WebElement using the Actions class.
     *
     * @param element the WebElement to retrieve the tooltip text for
     * @return the tooltip text for the WebElement
     */
    public static String getTooltipText(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).build().perform();
        return element.getAttribute("title");
    }

    /**
     * Returns a list of WebElement objects that match the specified By object and tag name.
     *
     * @param by    the By object used to locate the WebElement objects
     * @param waits the Waits object used to wait for the WebElement objects to become available
     * @return a list of WebElement objects that match the specified By object and tag name
     */
    public List<WebElement> getULandLITagElements(By by, Waits waits) {
        return getElements(by, waits);
    }

    /**
     * Clicks on the WebElement that matches the specified text and tag name under a list specified by the By object.
     *
     * @param list    the By object used to locate the list WebElement
     * @param waits   the Waits object used to wait for the list WebElement to become available
     * @param tagName the tag name of the child elements of the list
     * @param text    the text to match against the child elements of the list
     */
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

    /**
     * Switches to an alert and handles it based on the given boolean value.
     *
     * @param accept Whether to accept or dismiss the alert.
     */
    protected static void handleAlert(boolean accept) {
        Alert alert = getDriver().switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert text is " + alertText);
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    /**
     * Performs a click-and-hold action on the web element located by the given locator.
     *
     * @param locator The locator of the web element.
     * @param waits   The object containing wait-related parameters.
     */
    protected static void clickAndHold(By locator, Waits waits) {
        Actions actions = new Actions(getDriver());
        WebElement element = getElement(locator, waits);
        actions.clickAndHold(element).perform();
    }

    /**
     * Waits for the web element located by the given locator to have a non-empty attribute value,
     * and returns that value. Returns null if the attribute value is empty or null.
     *
     * @param by    The locator of the web element.
     * @param waits The object containing wait-related parameters.
     * @return The value of the attribute, or null if the attribute value is empty or null.
     */
    protected static String getAttributeValue(By by, Waits waits) {
        return waitForElementAttribute(getElement(by, waits));
    }

    /**
     * Moves the slider of the web element located by the given locator by a fixed offset.
     *
     * @param by    The locator of the web element.
     * @param waits The object containing wait-related parameters.
     */
    protected static void moveSlider(By by, Waits waits) {
        WebElement element = getElement(by, waits);
        Actions actions = new Actions(getDriver());
        actions.clickAndHold(element).moveByOffset(550, 0).release().build().perform();
    }

    /**
     * Sorts the given list of objects in ascending order and returns a new sorted list.
     *
     * @param list the list of objects to be sorted.
     * @param <T>  the type of the objects in the list.
     * @return a new list containing the same elements as the input list, but in ascending order.
     */
    protected static <T extends Comparable<T>> List<T> getSortedList(List<T> list) {
        // Create a Comparator object that can compare objects of type T using the compareTo method.
        // The lambda expression Comparable::compareTo is a shorthand way of creating the Comparator object.
        Comparator<T> comparator = Comparable::compareTo;

        // Sort the input list in place using the Comparator object.
        // The sort method rearranges the elements of the list in ascending order.
        list.sort(comparator);

        // Return the sorted list.
        return list;
    }
}