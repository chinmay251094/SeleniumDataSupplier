package com.factories;

import com.enums.Browsers;
import com.enums.RunModes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.lang.reflect.Executable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains a static factory method for creating and returning a WebDriver instance based on the specified browser
 * and run mode. The method also takes additional parameters like the test description and version to set browser capabilities
 * and options as required.
 */
public class DriverFactory {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    /**
     * A private constructor to prevent creating instances of the class.
     */
    private DriverFactory() {
    }

    /**
     * Returns a new WebDriver instance based on the provided parameters.
     *
     * @param browser     The browser to use (CHROME, FIREFOX, etc.)
     * @param mode        The run mode (LOCAL or REMOTE)
     * @param description The description to use for the test
     * @param version     The browser version to use (if using remote mode)
     * @return The WebDriver instance
     */
    public static WebDriver getDriver(Browsers browser, RunModes mode, String description, String version, Boolean headless) {
        WebDriver driver = null;

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (mode == RunModes.REMOTE) {
                    Map<String, Object> prefs = new HashMap<>();
                    // Disable notifications
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    // Disable saving passwords
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    // Disable extensions
                    chromeOptions.addArguments("--disable-extensions");
                    // Disable infobars
                    chromeOptions.addArguments("--disable-infobars");
                    // Disable notifications
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.setCapability("browserVersion", version);
                    // Add capabilities for video recording and session management in remote mode
                    chromeOptions.setCapability("videoName", description + "-" + dateFormat.format(new Date()) + ".mp4");
                    chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                        /* How to add test badge */
                        put("name", "Test badge...");

                        /* How to set session timeout */
                        put("sessionTimeout", "15m");

                        /* How to set timezone */
                        put("env", new ArrayList<String>() {{
                            add("TZ=UTC");
                        }});

                        /* How to add "trash" button */
                        put("labels", new HashMap<String, Object>() {{
                            put("manual", "true");
                        }});

                        /* How to enable video recording */
                        put("enableVideo", true);
                    }});
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                    // Create a RemoteWebDriver instance with the provided URL and options
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (Boolean.TRUE.equals(headless)) {
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--window-size=1800,900");
                        chromeOptions.addArguments("--disable-gpu");
                        // Create a ChromeDriver instance with the chromeOptions
                        driver = new ChromeDriver(chromeOptions);
                    } else {
                        // Create a regular ChromeDriver instance
                        driver = new ChromeDriver(chromeOptions);
                    }
                }
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (mode == RunModes.REMOTE) {
                    FirefoxProfile profile = new FirefoxProfile();
                    // Disable notifications
                    profile.setPreference("dom.webnotifications.enabled", false);
                    // Disable saving passwords
                    profile.setPreference("signon.rememberSignons", false);
                    profile.setPreference("signon.autofillForms", false);
                    // Disable saving browsing history
                    profile.setPreference("browser.privatebrowsing.autostart", true);
                    firefoxOptions.setProfile(profile);
                    // Disable extensions
                    firefoxOptions.addArguments("--disable-extensions");
                    // Disable infobars
                    firefoxOptions.addArguments("--disable-infobars");
                    firefoxOptions.setCapability("browserVersion", version);
                    firefoxOptions.setCapability("videoName", description + "-" + dateFormat.format(new Date()) + ".mp4");
                    firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                        /* How to add test badge */
                        put("name", "Test badge...");

                        /* How to set session timeout */
                        put("sessionTimeout", "15m");

                        /* How to set timezone */
                        put("env", new ArrayList<String>() {{
                            add("TZ=UTC");
                        }});

                        /* How to add "trash" button */
                        put("labels", new HashMap<String, Object>() {{
                            put("manual", "true");
                        }});

                        /* How to enable video recording */
                        put("enableVideo", true);
                    }});
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("firefox");
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    try {
                        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub/"), firefoxOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (Boolean.TRUE.equals(headless)) {
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--window-size=1800,900");
                        // Create a FirefoxDriver instance with the firefoxOptions
                        driver = new FirefoxDriver(firefoxOptions);
                    } else {
                        // Create a regular FirefoxDriver instance
                        driver = new FirefoxDriver(firefoxOptions);
                    }
                }
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                Map<String, Object> prefs = new HashMap<>();
                // Disable notifications
                prefs.put("profile.default_content_setting_values.notifications", 2);
                // Disable saving passwords
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                // Disable autofill profile
                prefs.put("autofill.profile_enabled", false);
                edgeOptions.setExperimentalOption("prefs", prefs);
                // Disable extensions
                edgeOptions.addArguments("--disable-extensions");
                // Disable infobars
                edgeOptions.addArguments("--disable-infobars");
                // Disable notifications
                edgeOptions.addArguments("--disable-notifications");

                if (Boolean.TRUE.equals(headless)) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--window-size=1800,900");
                    // Create an EdgeDriver instance with the edgeOptions
                    driver = new EdgeDriver(edgeOptions);
                } else {
                    // Create a regular EdgeDriver instance
                    driver = new EdgeDriver(edgeOptions);
                }
        }
        return driver;
    }
}
