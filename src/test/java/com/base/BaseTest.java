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

public class BaseTest {
    @BeforeMethod
    public void setUp(Object[] data) {
        Map<String, TestDataSupplier> testDataMap = new HashMap<>();

        for (Object obj : data) {
            TestDataSupplier dataSupplier = (TestDataSupplier) obj;
            testDataMap.put(dataSupplier.getTestcase(), dataSupplier);
        }
        TestDataSupplier testDataSupplier = testDataMap.get(((TestDataSupplier) data[0]).getTestcase());

        initDriver(testDataSupplier.getBrowser(), testDataSupplier.getURL(), testDataSupplier.getMode(), testDataSupplier.getDescription(), testDataSupplier.getVersion());
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}