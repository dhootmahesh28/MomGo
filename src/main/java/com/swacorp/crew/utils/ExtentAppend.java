package com.swacorp.crew.utils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by x219949 on 8/14/2018.
 */
public class ExtentAppend {

    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReports;
    private static String extentReportPropertyFile = "src\\test\\resources\\";
    public static final Logger LOGGER = Logger.getLogger(ExtentAppend.class);
    public static final String USER_DIR = System.getProperty("user.dir");

    public static ExtentReports getExtentInstance() {
        if (extent == null) {
            try {
                extent = createInstance("extentreport");
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return extent;

    }

    public static ExtentReports createInstance(String propName) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(extentReportPropertyFile + propName + ".properties")) {
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            String reportpath = prop.getProperty("reportpath");
            String reporttitle = prop.getProperty("reporttitle");
            String environment = System.getProperty("test.env");
            String reportName = prop.getProperty("reportname");
            copyLogoDirectory();

            String htmlReportName = "<img src='logos/SwaLogo.png' alt='swaLogo' style='height:100%;width=auto;float:left;padding-right:40px;'/> <span class='label blue darken-3' hspace='35' style='font-size: 130%'>"+reportName+"</span> <span class='label blue darken-3' style='font-size: 100%'>ENV : "+environment+"</span> <img src='logos/MosaicLogo.png' alt='mosaicLogo' style='height:100%;width=auto;float:right;' />";
            String cssToHideText = "a.brand-logo {display : none;}\n" +
                    "#nav-mobile > li:nth-child(2) > a > span {display : none;}";

            htmlReports = new ExtentHtmlReporter(reportpath);
            extent = new ExtentReports();
            extent.attachReporter(htmlReports);
            extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
            htmlReports.config().setDocumentTitle(reporttitle);
            htmlReports.config().setReportName(htmlReportName);
            htmlReports.config().setTheme(Theme.STANDARD);
            htmlReports.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReports.config().setChartVisibilityOnOpen(true);
            htmlReports.config().setEncoding("UTF-8");
            htmlReports.config().setProtocol(Protocol.HTTPS);
            htmlReports.config().setCSS(cssToHideText);
            extent.setSystemInfo("Environment", environment);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        return extent;
    }

    public String takeScreenshot(WebDriver driver, String screenshotName) {
        String destination = null;
        String imgPath = null;
        int maxRetryCount = 5;
        int retryCounter = 0;
        while (driver instanceof TakesScreenshot) {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                imgPath = "TestsScreenshots\\" + screenshotName + dateName + ".png";
                destination = USER_DIR + "\\build\\extent-reports\\" + imgPath;
                File finalDestination = new File(destination);
                FileUtils.copyFile(source, finalDestination);
                LOGGER.info("Screenshot destination : " + destination);
                return imgPath;
            } catch (IOException e) {
                LOGGER.error("takeScreenshot Exception : " + e);
                if (++retryCounter > maxRetryCount) {
                    Assert.assertTrue(false, "Exception while taking screenshot : " + e.getMessage());
                    break;
                }
            }
        }
        LOGGER.info("Destination after exception: " + destination);
        return imgPath;
    }

    public String nonWebAppScreenshot(String screenshotName) {
        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 5;
        String imageFileName;
        String imageFolderName = "non-web-screenshot";

        do {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
            imageFileName = screenshotName + dateName + ".png";
            String directory = USER_DIR + "\\build\\extent-reports\\" + imageFolderName;
            File file = new File(directory);
            if (!file.isDirectory())
                file.mkdirs();
            String destFileName = directory + "\\" + imageFileName;
            File destFile = new File(destFileName);
            try {
                Robot robot = new Robot();
                BufferedImage bi = robot.createScreenCapture(new Rectangle(1600, 900));
                ImageIO.write(bi, "png", destFile);
                return imageFolderName + "\\" + imageFileName;
            } catch (Exception e) {
                LOGGER.error(e);
                retryStatus = true;
                if (++retryCounter > maxRetryCount) {
                    Assert.assertTrue(false, "Exception while taking screenshot for non web application : " + e.getMessage());
                    break;
                }
            }
        } while (retryStatus);
        return imageFolderName + "\\" + imageFileName;
    }


    public static void copyLogoDirectory(){
        String destinationDir = "";
        try {
            String sourceDir = USER_DIR + "/config/logos";
            destinationDir = USER_DIR + "/build/extent-reports/logos";
            File destDir = new File(destinationDir);
            File srcDir = new File(sourceDir);
            FileUtils.copyDirectory(srcDir, destDir);
        }catch(Exception e){
            LOGGER.error(e);
            Assert.assertTrue(false, "Exception occurred while moving logos to drive : "+destinationDir);
        }
    }

}
