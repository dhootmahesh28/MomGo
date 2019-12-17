package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVWriter;
import com.swacorp.crew.pages.common.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by x219949 on 8/14/2018.
 */
public class TestManager extends DriverSource {

    ExtentAppend ext = new ExtentAppend();
    public ExtentReports extent = ext.getExtentInstance();
    public final static Logger LOGGER = Logger.getLogger(TestManager.class);
    public static Properties dataProperties;
    BasePage basePage = new BasePage();
    ReportUtil report = new ReportUtil();
    public static String jbehavePath;
    public static final String jsonFile = "xref.json";
    public static List<String[]> testResults = new CopyOnWriteArrayList<String[]>() {
        {
            add(new String[]{"TC ID", "TEST NAME", "STATUS", "TEST TYPE", "E2E TCID", "FAILURE REASON"});
        }
    };

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
                LOGGER.info("----------- Execution for scenario " + scenarioName + " started ------------------");
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
    public void tearDown(ITestResult result) throws Exception {
        System.out.println("TEAR DOWN ::");
        ExtentTest test = getExtentTest();
        String failureReason = "";
        String status = null;
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                status = "Failed";
                failureReason = result.getThrowable().getMessage();
                String screenshotPath = ext.takeScreenshot(getDriver(), result.getName());
                test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +
                        " Test case FAILED due to below issues:", ExtentColor.RED));
                test.fail(result.getThrowable());
                test.log(Status.FAIL, "Snapshot when exception occur: ",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                status = "Passed";
                test.pass("Passed");
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
            status = "Failed";
            failureReason = e.getMessage();
            test.log(Status.FAIL, "Exception in Tear down : " + e.getMessage());
        } finally {
            captureResult(test, status, failureReason);
            quitDriver();
            Long key = Thread.currentThread().getId();
            Long parentTcKey = getParentTcKey(key);
            TestUtil.extentTestMap.keySet().removeIf(k -> k.equals(key));
            TestUtil.extentTestMap.keySet().removeIf(k -> k.equals(parentTcKey));
            TestUtil.driverMap.keySet().removeIf(k -> k.equals(key));
        }
        LOGGER.info("-----------Test Execution Completed & Status is "+status+"------------------");
    }

    @BeforeSuite(alwaysRun = true)
    public void initializeDataProperty() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //The belo code is inherited from the LeanFt BaseTest
        try {
            suiteSetup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataProperties = new Properties();
        String dataFileName = "data.properties";
        String dataPath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\" + dataFileName;
        try {
            FileInputStream dataFile = new FileInputStream(dataPath);
            dataProperties.load(dataFile);
        } catch (FileNotFoundException e) {
            Assert.assertTrue(false, e.getMessage());
        } catch (IOException e) {
            Assert.assertTrue(false, e.getMessage());
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
    public void clearExtentTest() {
        try {
            extent.flush();
            dataProperties = null;
            generateCsvResult();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        } finally {
            try {
                String almIntegration = System.getProperty("uploadResultsToALM");
                createXREFJson();
                ResultUploadToALM resultUploadToALM = new ResultUploadToALM();
                resultUploadToALM.uploadResultToALM(almIntegration);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                try {
                    //suiteTearDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            copyResults();
        }
    }

    public static String getTestData(String propertyName) {
        return dataProperties.getProperty(propertyName);
    }

    public void copyResults() {
        String destinationDir = "";
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            destinationDir = "//qsvmnas03/QMOAutomationTestResults/Mosaic/Release18/CertCycle7/TestNG/" + System.getProperty("includedGroups") + "/" + timeStamp + "/extent-reports";
            File logFile = new File(System.getProperty("user.dir") + "/build/log.out");

            String sourceDir = System.getProperty("user.dir") + "/build/extent-reports";
            File destDir = new File(destinationDir);
            File srcDir = new File(sourceDir);

            FileUtils.copyFileToDirectory(logFile,destDir);
            FileUtils.copyDirectory(srcDir, destDir);

            System.out.println("Results are copied from location " + sourceDir.replace('/', '\\'));
            System.out.println("Results are copied to location " + destinationDir.replace('/', '\\'));
        } catch (Exception e) {
            LOGGER.error("Exception occurred while moving results to shared drive : " + destinationDir + " with exception " + e);
            Assert.assertTrue(false, "Exception occurred while moving results to shared drive : " + destinationDir);
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
                String ChildTestCaseID = null;
                String childTestIDName = childTestName.split("_")[0];
                if (childTestIDName.toUpperCase().startsWith("TC")) {
                    ChildTestCaseID = childTestIDName.substring(2).trim();
                    if (childTestStatus.equalsIgnoreCase("pass")) {
                        testResults.add(new String[]{ChildTestCaseID, childTestName, "Passed", "Individual", parentTestID, ""});
                    } else {
                        testResults.add(new String[]{ChildTestCaseID, childTestName, "Failed", "Individual", parentTestID, failureReason});
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

    public void generateCsvResult() throws Exception {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter("build\\extent-reports\\result.csv"));
            csvWriter.writeAll(testResults);
            csvWriter.close();
        } catch (IOException e) {
            LOGGER.error(e.getSuppressed());
            throw new Exception("Unable to create a csv file : "+e.getMessage());
        }
    }

    void createXREFJson() {
        JsonArray testCasesArray = new JsonArray();
        for (String[] testcase : testResults) {
            if (testcase[2] == "Passed") {
                JsonObject testCaseObject = new JsonObject();
                testCaseObject.addProperty("name", "CRW_" + testcase[0] + "_" + testcase[1].split("_")[1]);
                testCaseObject.addProperty("pending", false);
                testCaseObject.addProperty("passed", true);
                testCasesArray.add(testCaseObject);
            } else if(testcase[2] == "Failed"){
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
                fileWriter = new FileWriter(jbehavePath + jsonFile);
                gsonXref.toJson(xrefJsonObject, fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                Assert.fail("Unable to create XREF JSON file");
            }
        }
    }

}
