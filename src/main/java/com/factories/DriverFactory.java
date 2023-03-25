package com.factories;

import com.enums.Browsers;
import com.enums.RunModes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

public class DriverFactory {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    private DriverFactory() {
    }

    public static WebDriver getDriver(Browsers browser, RunModes mode, String description, String version) {
        WebDriver driver = null;

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (mode == RunModes.REMOTE) {
                    chromeOptions.setCapability("browserVersion", version);
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
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    driver = new ChromeDriver(chromeOptions);
                }
                break;
            case FIREFOX:
                if (mode == RunModes.REMOTE) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setCapability("browserVersion", version);
                    options.setCapability("videoName", description + "-" + dateFormat.format(new Date()) + ".mp4");
                    options.setCapability("selenoid:options", new HashMap<String, Object>() {{
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
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
                    try {
                        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub/"), options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    driver = new FirefoxDriver();
                }
                break;
            case EDGE:
                driver = new EdgeDriver();
        }
        return driver;
    }
}
