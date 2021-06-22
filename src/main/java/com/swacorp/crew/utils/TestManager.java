package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVWriter;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.constants.AsapConstants;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by x219949 on 8/14/2018.
 */

// TaskManager class implements following TestNg Methods
// @AfterMethod, @AfterClass, @afterSuite
public class TestManager extends DriverSource {

    ExtentAppend ext = new ExtentAppend();
    ExtentReports extent = ExtentAppend.getExtentInstance();
    public static final Logger loggerTestManager = Logger.getLogger(TestManager.class);
    BasePage basePage = new BasePage();
    ReportUtil report = new ReportUtil();
    String jbehavePath;
    public static final String JSON_FILE = "xref.json";
    public static final String FAILED =     "Failed";
    public static final String PASSED =     "Passed";
    public static final List<String[]> testResults = new CopyOnWriteArrayList();
    static {
        testResults.add(new String[]{"TC ID", "TEST NAME", "STATUS", "TEST TYPE", "E2E TCID", "FAILURE REASON"});
    }


    public ExtentTest getExtentTest() {
        Long key = Thread.currentThread().getId();
        return TestUtil.extentTestMap.get(key);
    }

    public Long getParentTcKey(Long threadID) {
        return threadID + 1234567890;
    }

    public void setScenarioName(String scenarioName) {
        Long key = Thread.currentThread().getId();
        Long parentTcKey = getParentTcKey(key);
        do {
            if (TestUtil.extentTestMap.containsKey(parentTcKey)) {
                setSubScenarioName(scenarioName);
            } else {
                ExtentTest extentTest = extent.createTest(scenarioName);
                TestUtil.extentTestMap.put(parentTcKey, extentTest);
                TestUtil.extentTestMap.put(key, extentTest);
                loggerTestManager.info("----------- Execution for scenario " + scenarioName + " started ------------------");
                if (!basePage.validateStringStartsWithPattern(scenarioName, "TC[0-9]+_")) {
                    report.report("error", "Scenario name '" + scenarioName + "' not started with pattern 'TC[0-9]+_'");
                }
            }
        } while (TestUtil.extentTestMap.get(key) == null);
    }

    public void setSubScenarioName(String subScenarioName) {
        if (basePage.validateStringStartsWithPattern(subScenarioName, "TC[0-9]+_") || basePage.validateStringStartsWithPattern(subScenarioName, "Precondition_")|| basePage.validateStringStartsWithPattern(subScenarioName, "Postcondition_")) {
            Long key = Thread.currentThread().getId();
            Long parentTcKey = getParentTcKey(key);
            ExtentTest extentTest = TestUtil.extentTestMap.get(parentTcKey);
            ExtentTest subScenarioTest = extentTest.createNode(subScenarioName);
            TestUtil.extentTestMap.put(key, subScenarioTest);
        } else {
            report.report("error", "Sub scenario name '" + subScenarioName + "' not started with pattern 'TC[0-9]+_' OR 'Precondition_' OR 'Postcondition_'");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String screenshotPath;
        loggerTestManager.info("TEAR DOWN ::");
        ExtentTest test = getExtentTest();
        String failureReason = "";
        String status = null;
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                status =FAILED;
                failureReason = result.getThrowable().getMessage();
                if(result.getThrowable().getStackTrace()[1].toString().contains("LeanFT")){
                    screenshotPath =ReportUtil.getFailedTestPath();
                }else {
                    screenshotPath = ext.takeScreenshot(getDriver(), result.getName());
                }
                test.fail(basePage.setFontColor(result.getName() + " Test case FAILED due to issue : " + failureReason, "red"));

                loggerTestManager.info(result.getThrowable());
                test.log(Status.FAIL, basePage.setFontColor("Snapshot when exception occur: ", "black"),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                test.fail(FAILED);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                status = PASSED;
                test.pass(PASSED);
                loggerTestManager.info("Test case passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                status = "Skipped";
                failureReason = result.getThrowable().getMessage();
                test.skip(result.getThrowable());
            } else {
                status = String.valueOf(result.getStatus());
                failureReason = result.getThrowable().getMessage();
                test.error(result.getThrowable());
            }
        } catch (Exception e) {
            status = FAILED;
            failureReason = e.getMessage();
            test.log(Status.FAIL, "Exception in Tear down : " + e.getMessage());
            loggerTestManager.error(e);
        } finally {
            captureResult(test, status, failureReason);
            quitDriver();
            Long key = Thread.currentThread().getId();
            Long parentTcKey = getParentTcKey(key);
            TestUtil.extentTestMap.keySet().removeIf(k -> k.equals(key));
            TestUtil.extentTestMap.keySet().removeIf(k -> k.equals(parentTcKey));
            TestUtil.driverMap.keySet().removeIf(k -> k.equals(key));
        }
        loggerTestManager.info("-----------Test Execution Completed & Status is "+status+"------------------");
    }

    @BeforeSuite(alwaysRun = true)
    public void initializeDataProperty() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
            File file = new File(AsapConstants.DOWNLOAD_PATH);
            if(file.isDirectory())
                FileUtils.cleanDirectory(file);
            file.mkdirs();
        } catch (IOException e) {
            loggerTestManager.error(e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void clearStaticData() {
        Long id = Thread.currentThread().getId();
        String key = "-" + id;
        TestUtil.dynamicDataArrayMap.keySet().removeIf(k -> k.contains(key));
        TestUtil.dynamicDataMap.keySet().removeIf(k -> k.contains(key));
        TestUtil.dynamicBooleanDataMap.keySet().removeIf(k -> k.contains(key));
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        clearExtentTest();
    }

    public void clearExtentTest() {
        try {
            extent.flush();
            generateCsvResult();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            loggerTestManager.error(e);
        } finally {
            try {
                String almIntegration = System.getProperty("uploadResultsToALM");
                createXREFJson();
            }catch(Exception e){
                loggerTestManager.error(e);
            }
        }
    }

    public void captureResult(ExtentTest test, String testStatus, String failureReason) {
        Test testModel = test.getModel();
        if (testModel.isChildNode()) {
            Test parentTest = testModel.getParent();
            List<Test> childTestList = parentTest.getNodeContext().getAll();
            String parentTestName = parentTest.getName();
            String parentTestIDName = parentTestName.split("_")[0];
            String parentTestID = parentTestIDName.substring(2);
            testResults.add(new String[]{parentTestID, parentTestName, testStatus, "E2E", parentTestID, failureReason});
            for (Test childTest : childTestList) {
                String childTestName = childTest.getName();
                String childTestStatus = childTest.getStatus().toString();
                String childTestCaseID = null;
                String childTestIDName = childTestName.split("_")[0];
                if (childTestIDName.toUpperCase().startsWith("TC")) {
                    childTestCaseID = childTestIDName.substring(2).trim();
                    if (childTestStatus.equalsIgnoreCase("pass")) {
                        testResults.add(new String[]{childTestCaseID, childTestName, PASSED, "Individual", parentTestID, ""});
                    } else {
                        testResults.add(new String[]{childTestCaseID, childTestName, FAILED, "Individual", parentTestID, failureReason});
                    }
                }
            }
        } else {
            String testName = testModel.getName();
            String testIDName = testName.split("_")[0];
            String parentTestID = testIDName.substring(2);
            testResults.add(new String[]{parentTestID, testName, testStatus, "E2E", parentTestID, failureReason});
        }
    }

    public void generateCsvResult() throws IOException {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter("build\\extent-reports\\result.csv"));
            csvWriter.writeAll(testResults);
            csvWriter.close();
        } catch (IOException e) {
            loggerTestManager.error(e);
            throw new IOException("Unable to create a csv file : "+e);
        }
    }

    void createXREFJson() {
        JsonArray testCasesArray = new JsonArray();
        for (String[] testcase : testResults) {
            if (testcase[2].equalsIgnoreCase(PASSED)) {
                JsonObject testCaseObject = new JsonObject();
                testCaseObject.addProperty("name", "CRW_" + testcase[0] + "_" + testcase[1].split("_")[1]);
                testCaseObject.addProperty("pending", false);
                testCaseObject.addProperty("passed", true);
                testCasesArray.add(testCaseObject);
            } else if(testcase[2].equalsIgnoreCase(FAILED)){
                JsonObject testCaseObject = new JsonObject();
                testCaseObject.addProperty("name", "CRW_" + testcase[0] + "_" + testcase[1].split("_")[1]);
                testCaseObject.addProperty("pending", false);
                testCaseObject.addProperty("passed", false);
                testCasesArray.add(testCaseObject);
            }
        }
        if (testCasesArray.size() != 0) {
            JsonObject storiesMap = new JsonObject();
            storiesMap.add("stories", testCasesArray);
            JsonObject xrefJsonObject = new JsonObject();
            xrefJsonObject.add("xref", storiesMap);
            Gson gsonXref = new GsonBuilder().setPrettyPrinting().create();
            jbehavePath = System.getProperty("xrefDirectory");
            File dir = new File(jbehavePath);
            dir.mkdirs();
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(jbehavePath + JSON_FILE);
                gsonXref.toJson(xrefJsonObject, fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                Assert.fail("Unable to create XREF JSON file");
                loggerTestManager.error(e);
            }
        }
    }

}