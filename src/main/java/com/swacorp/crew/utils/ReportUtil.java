package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.constants.MessageConstants;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtil {
    BasePage basePage = new BasePage();
    public final static Logger LOGGER = Logger.getLogger(ReportUtil.class);
    public ExtentTest extentTest;
    public ExtentAppend extentAppend = new ExtentAppend();
    public static String failedTestPath;
    private static final String PASS = "PASS_";
    private static final String FAIL = "FAIL_";
    private static final String INFO = "INFO_";
    String msgScreenshotException = "Exception while taking screenshot : ";

    public void reportLeanFT(com.hp.lft.sdk.java.Window window, String status, String message){
        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 3;
        ExtentTest extTest = basePage.getExtentTest();
        do {
            try {
                if (status.equalsIgnoreCase(MessageConstants.INFO)) {
                    String screenshotPath = captureLeanFTScreenshot(window, FAIL + basePage.randomString(5) + "_");
                    extTest.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else if (status.equalsIgnoreCase(MessageConstants.ERROR)) {
                    extTest.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.AMBER));
                }else if (status.equalsIgnoreCase(MessageConstants.PASSED)) {
                    String screenshotPath = captureLeanFTScreenshot(window, PASS + basePage.randomString(5) + "_");
                    extTest.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
                else {
                    String screenshotPath = captureLeanFTScreenshot(window, FAIL + basePage.randomString(5) + "_");
                    failedTestPath = screenshotPath;
                    extTest.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    Assert.fail(message, new Throwable());
                }
                retryStatus = false;
            } catch (Exception e) {
                e.printStackTrace();
                retryStatus = true;
                LOGGER.error("Exception in report method: " + e);
                if (++retryCounter > maxRetryCount) {
                    Assert.assertTrue(false, msgScreenshotException + e.getMessage());
                    break;
                }
            }
        } while (retryStatus);
    }

    //com.hp.lft.sdk.winforms.ComboBox


    public void reportLeanFT(Window window, String status, String message){

        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 3;
        ExtentTest extTest = basePage.getExtentTest();
        do {
            try {
                if (status.equalsIgnoreCase(MessageConstants.INFO)) {
                    String screenshotPath = captureLeanFTScreenshot(window, INFO + basePage.randomString(5) + "_");
                    extTest.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else if (status.equalsIgnoreCase(MessageConstants.ERROR)) {
                    extTest.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.AMBER));
                }else if (status.equalsIgnoreCase(MessageConstants.PASSED)) {
                    String screenshotPath = captureLeanFTScreenshot(window, PASS+ basePage.randomString(5) + "_");
                    extTest.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
                else {
                    String screenshotPath = captureLeanFTScreenshot(window, FAIL+ basePage.randomString(5) + "_");
                    failedTestPath = screenshotPath;
                    extTest.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    Assert.fail(message, new Throwable());
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

    public void reportSelenium(String status, String message) {
        SoftAssert softAssert = new SoftAssert();
        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 3;
        extentTest = getExtentTest();
        do {
            try {
                if (status.equalsIgnoreCase(MessageConstants.INFO)) {
                    String screenshotPath = extentAppend.takeScreenshot(basePage.getDriver(), INFO + basePage.randomString(5) + "_");
                    extentTest.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else if (status.equalsIgnoreCase(MessageConstants.ERROR)) {
                    extentTest.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.AMBER));
                    Assert.fail(message, new Throwable());
                }else if (status.equalsIgnoreCase(MessageConstants.PASSED)) {
                    String screenshotPath = extentAppend.takeScreenshot(basePage.getDriver(), PASS + basePage.randomString(5) + "_");
                    extentTest.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
                else {
                    String screenshotPath = extentAppend.takeScreenshot(basePage.getDriver(), FAIL + basePage.randomString(5) + "_");
                    extentTest.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    softAssert.fail(message, new Throwable());
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


    public String captureLeanFTScreenshot(com.hp.lft.sdk.java.Window window, String screenshotName) {
        String destination = null;
        String imgPath = null;
        int maxRetryCount = 5;
        int retryCounter = 0;

        String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
        while (retryCounter < maxRetryCount){
            try {
                RenderedImage img = window.getSnapshot();
                imgPath = "LeanFTScreenshots\\" + screenshotName + dateName + ".png";
                destination = System.getProperty("user.dir") + "\\build\\extent-reports\\" + imgPath;
                File finalDestination = new File(destination);
                finalDestination.getParentFile().mkdir();
                ImageIO.write(img, "png", finalDestination);
                LOGGER.info("Screenshot destination : " + destination);
                return imgPath;
            } catch (Exception e) {
                LOGGER.error("takeScreenshot Exception : " + e);
                if (++retryCounter > maxRetryCount) {
                    Assert.fail(msgScreenshotException + e.getMessage());
                    break;
                }
            }
        }

        LOGGER.info("Destination after exception: " + destination);
        return imgPath;
    }


    public String captureLeanFTScreenshot(Window window, String screenshotName) {
        String destination = null;
        String imgPath = null;
        int maxRetryCount = 5;
        int retryCounter = 0;

        String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
        while (retryCounter < maxRetryCount){
            try {
                RenderedImage img = window.getSnapshot();
                imgPath = "LeanFTScreenshots\\" + screenshotName + dateName + ".png";
                destination = System.getProperty("user.dir") + "\\build\\extent-reports\\" + imgPath;
                File finalDestination = new File(destination);
                finalDestination.getParentFile().mkdir();
                ImageIO.write(img, "png", finalDestination);
                LOGGER.info("Screenshot destination : " + destination);
                return imgPath;
            } catch (Exception e) {
                LOGGER.error("takeScreenshot Exception : " + e);
                if (++retryCounter > maxRetryCount) {
                    Assert.fail(msgScreenshotException + e.getMessage());
                    break;
                }
            }
        }

        LOGGER.info("Destination after exception: " + destination);
        return imgPath;
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
