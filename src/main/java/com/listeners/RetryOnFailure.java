package com.listeners;

import com.utils.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/*
 RetryOnFailure is an implementation of the IRetryAnalyzer interface.
 It allows retrying a test a certain number of times in case of failure.
 */
public class RetryOnFailure implements IRetryAnalyzer {
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
    private int maxRetryCount = frameworkConfig.failure_retries(); // Maximum number of retries
    private int retryCount = 0; // Current retry count

    public int getRetryCount() {
        return retryCount;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    /**
     * Determines whether to retry a failed test.
     *
     * @param result The test result containing information about the test.
     * @return true if the test should be retried, false otherwise.
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true; // Retry the test
        }
        return false; // Do not retry the test
    }
}

