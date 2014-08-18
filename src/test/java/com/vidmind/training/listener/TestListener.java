package com.vidmind.training.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by barcelona on 8/18/14.
 */
public class TestListener extends TestListenerAdapter{

    Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        LOGGER.info("Test started -> " + testContext.getName());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        LOGGER.info("Test finished -> " + testContext.getName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        LOGGER.info("Test passed -> " + tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        LOGGER.error("Test failed -> " + tr.getName());
    }
}
