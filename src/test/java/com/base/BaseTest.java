package com.base;

import com.supplier.TestDataSupplier;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.driver.Driver.initDriver;
import static com.driver.Driver.quitDriver;

/**
 * This is a base test class containing setup and teardown methods for test classes to inherit from.
 */
public class BaseTest {
    /**
     * This method is run before each test method and sets up the test data and initializes the web driver.
     *
     * @param data An array of objects containing test data.
     */
    @BeforeMethod
    public void setUp(Object[] data) {
        // Create a map to store the test data
        Map<String, TestDataSupplier> testDataMap = new HashMap<>();

        // Loop through the test data and add it to the map
        for (Object obj : data) {
            TestDataSupplier dataSupplier = (TestDataSupplier) obj;
            testDataMap.put(dataSupplier.getTestcase(), dataSupplier);
        }

        // Get the test data supplier for the first test case in the data array
        TestDataSupplier testDataSupplier = testDataMap.get(((TestDataSupplier) data[0]).getTestcase());
        System.out.println(testDataSupplier.getURL());
        // Initialize the web driver with the test data
        initDriver(testDataSupplier.getBrowser(), testDataSupplier.getURL(), testDataSupplier.getMode(), testDataSupplier.getDescription(), testDataSupplier.getVersion(), testDataSupplier.getHeadless());
    }

    /**
     * This method is run after each test method and quits the web driver.
     */
    @AfterMethod
    public void tearDown() {
        // Quit the web driver
        quitDriver();
    }
}