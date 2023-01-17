package com.digicorp.listeners;

import com.digicorp.annotations.TeamDigicorp;
import com.digicorp.reports.ExtentReport;
import com.digicorp.reports.ExtentReportLogger;
import lombok.SneakyThrows;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener, ISuiteListener {
    @SneakyThrows
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @SneakyThrows
    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTests(result.getMethod().getDescription());
        ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TeamDigicorp.class).author());
        ExtentReport.addCategory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TeamDigicorp.class).category());
        ExtentReport.setBrowser();
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportLogger.pass(result.getMethod().getDescription() + " has passed successfully.", false);
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentReportLogger.fail(result.getMethod().getDescription() + " has failed.", true);
            ExtentReportLogger.fail(result.getThrowable().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportLogger.pass(result.getMethod().getDescription() + " has been skipped.", false);
    }
}
