package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.constants.MessageConstants;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ReportUtil {
    BasePage basePage = new BasePage();
    public static final Logger LOGGER = Logger.getLogger(ReportUtil.class);
    ExtentTest extentTest;
    ExtentAppend extentAppend = new ExtentAppend();
    private static String failedTestPath;
    private static final String PASS = "PASS_";
    private static final String FAIL = "FAIL_";
    private static final String INFO = "INFO_";
    String msgScreenshotException = "Exception while taking screenshot : ";

    public static String getFailedTestPath() {
        return failedTestPath;
    }

    public static void setFailedTestPath(String failedTestPath) {
        ReportUtil.failedTestPath = failedTestPath;
    }

    public void reportSelenium(String status, String message) {
        SoftAssert softAssert = new SoftAssert();
        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 3;
        extentTest = getExtentTest();
        do {
            try {
                if (status.equalsIgnoreCase(MessageConstants.INFO)) {
                    message = basePage.setFontColor(message, "black");
                    String screenshotPath = extentAppend.takeScreenshot(basePage.getDriver(), INFO + basePage.randomString(5) + "_");
                    extentTest.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else if (status.equalsIgnoreCase(MessageConstants.ERROR)) {
                    message = basePage.setFontColor(message, "red");
                    extentTest.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.AMBER));
                    softAssert.fail(message, new Throwable());
                    softAssert.assertAll();
                }else if (status.equalsIgnoreCase(MessageConstants.PASSED)) {
                    message = basePage.setFontColor(message, "green");
                    String screenshotPath = extentAppend.takeScreenshot(basePage.getDriver(), PASS + basePage.randomString(5) + "_");
                    extentTest.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
                else {
                    message = basePage.setFontColor(message, "red");
                    String screenshotPath = extentAppend.takeScreenshot(basePage.getDriver(), FAIL + basePage.randomString(5) + "_");
                    extentTest.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    softAssert.fail(message, new Throwable());
                    softAssert.assertAll();
                }
                retryStatus = false;
            } catch (Exception e) {
                retryStatus = true;
                LOGGER.error("Report method exception : " + e);
                if (++retryCounter > maxRetryCount) {
                    Assert.assertTrue(false, msgScreenshotException + e.getMessage());
                    break;
                }
            }
        } while (retryStatus);
    }

    public void report(String status, String message){

        ExtentTest extTest = basePage.getExtentTest();
        if (status.equalsIgnoreCase("info")) {
            extTest.log(Status.INFO, message);
        } else if (status.equalsIgnoreCase("error")) {
            extTest.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.AMBER));
        }else if (status.equalsIgnoreCase("pass")) {
            extTest.log(Status.PASS, message);
        }
        else {
            extTest.log(Status.FAIL, message);
            Assert.fail(message, new Throwable());
        }

    }

    public ExtentTest getExtentTest() {
        Long threadId = Thread.currentThread().getId();
        return TestUtil.extentTestMap.get(threadId);
    }

    public void reportNonWeb(String status, String message) {
        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 3;
        extentTest = getExtentTest();
        do {
            String screenshotPath = extentAppend.nonWebAppScreenshot(INFO + basePage.randomString(5) + "_");
            try {
                if (status.equalsIgnoreCase("info")) {
                    extentTest.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else if (status.equalsIgnoreCase("error")) {
                    extentTest.log(Status.ERROR, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    Assert.assertTrue(false, message);
                } else {
                    extentTest.log(Status.DEBUG, message);
                }
                retryStatus = false;
            } catch (Exception e) {
                retryStatus = true;
                LOGGER.error("Report non web method exception : " + e);
                if (++retryCounter > maxRetryCount) {
                    Assert.assertTrue(false, msgScreenshotException + e.getMessage());
                    break;
                }
            }
        } while (retryStatus);
    }

}
