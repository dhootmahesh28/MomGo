package com.swacorp.crew.pages.rosa;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.constants.CommonFormats;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeRegExp.test;

public class RosaHome extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(RosaHome.class);
    ReportUtil report = new ReportUtil();
    String currentSystemDate;

    private static final By LOGIN_PAGE_TXT = By.xpath("//h2[text()='Request Optimizer Solution Application']");
    private static final By PILOT_TRAINING_BTN = By.xpath("//button[@id='button-1']");
    private static final By PILOT_TRAINING_OPTIMIZER_TXT = By.xpath("//h2[text()='Pilot Training Optimizer']");
    private static final By CONDITIONAL_RADIO_BTN = By.xpath("//*[text()='Conditional']");
    private static final By CONDITIONAL_CORE_RADIO_BTN = By.xpath("//*[text()='Core']");
    private static final By START_BTN = By.xpath("//*[@id='startButton']");
    private static final By RESET_BTN = By.xpath("//*[text()='Reset']");
    private static final By EVENT_DROP = By.xpath("/html/body/app-root/div[2]/div/div/app-pilot-training-request/div[2]/div[1]/div[2]/cl-dropdown/div/div/div[2]");
    private static final By BID_LINE_DR0P = By.xpath("//*[@class='cl-icon--dark-midnight cl-icon cl-icon-chevron-down']");
    private static final By HARD_LINE_DROP_DOWN_VALUE = By.xpath("//*[text()=' Hard Line ']");
    private static final By START_SUBMIT_BTN = By.xpath("//*[text()='Start']");
    private static final By PTO_QUEUE_BTN = By.xpath("(//*[text()='Queue'])[1]");

    private static final String REPLACE_TXT = "PLACEHOLDER";
    private static final String DROP_VALUE = "//*[contains(text(),'"+ REPLACE_TXT + "')]";

    public void VerifyHomePageAppear() {
        waitForElement(LOGIN_PAGE_TXT);
        if (isElementVisible(LOGIN_PAGE_TXT)){
            report.reportSelenium("Pass", "Home page visible successfully.");
            printConsole("VerifyHomePageAppear passed");

        }else {
            report.reportSelenium("fail", "Home page NOT visible.");
            printConsole("VerifyHomePageAppear failed");
        }
    }

    public void VerifyElementAppear(String locator) {

        waitForElement(getWebElement(locator));
        if (isElementVisible(getWebElement(locator))){
            report.reportSelenium("Pass", "Element Exists");
            printConsole("Element Exists");

        }else {
            report.reportSelenium("fail", "Element NOT visible.");
            printConsole("element failed");
        }
    }

    public  By getWebElement(String eleName){
        By ele = null;

        if (eleName.equalsIgnoreCase("CONDITIONAL_RADIO_BTN")){
            ele = CONDITIONAL_RADIO_BTN;
        }
        return ele;
    }


    public void clickAndVerifyPilotTrainingOptimizer(){
        List<WebElement> btn = getDriver().findElements(PILOT_TRAINING_BTN);
        btn.get(1).click();
        if (isElementVisible(PILOT_TRAINING_OPTIMIZER_TXT)){
            report.reportSelenium("Pass", "Training Optimizer page appeared.");
        }else{
            report.reportSelenium("Fail", "Training Optimizer page NOT appeared.");
        }
    }

    public void verifyConditionalRadioButtonExist(){
        if (isElementVisible(CONDITIONAL_RADIO_BTN)) {
            report.reportSelenium("Pass", "Core radio button exist.");
        }else{
        report.reportSelenium("Fail", "Core radio button does not exist.");
        }
    }

    public void verifyStartAndResetButtonExist(){
        if (isElementVisible(CONDITIONAL_CORE_RADIO_BTN)) {
            report.reportSelenium("Pass", "Training Optimizer page appeared.");
        }else{
            report.reportSelenium("Fail", "Training Optimizer page NOT appeared.");
        }
    }

    public void verifyCoreRadioButtonExist(){
        if (isElementVisible(START_BTN)) {
            report.reportSelenium("Pass", "Start button exists.");
        }else{
            report.reportSelenium("Fail", "Start button does not exists.");
        }

        if (isElementVisible(RESET_BTN)) {
            report.reportSelenium("Pass", "Reset button exists.");
        }else{
            report.reportSelenium("Fail", "Reset button does not exists.");
        }
    }

    public void selectFromDropDown(String dropHeader, String dropValue){
        String xpathForDropdown = DROP_VALUE.replace(REPLACE_TXT, dropHeader);
        String xpathForDropdownValue = DROP_VALUE.replace(REPLACE_TXT, dropValue);
        Boolean valueSelected = false;
        try {
            for (int i = 0; i < 3; i++) {   // Different drop down value is selected some times
                getDriver().findElement(By.xpath(xpathForDropdown)).click();
                Thread.sleep(1000);
                waitUntilDomLoad();
                getDriver().findElement(By.xpath(xpathForDropdown)).findElement(By.xpath(xpathForDropdownValue)).click();

                if (getDriver().findElement(By.xpath(xpathForDropdown)).findElement(By.xpath(xpathForDropdownValue)).getText().equalsIgnoreCase(dropValue)) {
                    valueSelected = true;
                    break;
                }
            }
            if (valueSelected) {
                report.reportSelenium("Pass", "Selected: " + dropHeader + " - " + dropValue);
            }else{
                report.reportSelenium("Fail", "Failed to select the value : "+ dropValue + " from the drop down : "+ dropHeader);
            }
        }catch(Exception e){
            report.reportSelenium("Fail", "Failed to select: "+dropHeader+" - "+dropValue);
            LOGGER.error(e);
        }
    }

    public void selectMonthYear(String dropHeader, String monthYear) throws Exception{
        Boolean blnDropValueFound = false;
        DateUtil dateUtil = new DateUtil();
        String month = monthYear.split(" ")[0];
        String year = monthYear.split(" ")[1];
        if (month.length() == 3) {
            monthYear = TestUtil.short2LongMonthMap.get(month) +" "+ year;
        }
        String dateFrom = dateUtil.getCurrentDate(CommonFormats.DAY_MONTHNAME_YEAR);
        String dateTo = dateUtil.getCurrentDay() +"-"+ month.substring(0, 3) +"-"+ year;
        long monthsDiff = dateUtil.getMonthDiff(dateFrom, dateTo, CommonFormats.DAY_MONTHNAME_YEAR);
        if (monthsDiff <= 0) {
            if (dateUtil.changeLocalDate((int) monthsDiff-1)) {
                report.reportSelenium("Pass", "System date changed to : " + dateTo);
                for (int i = 0; i <= 60; ++i) {  //Dropdown value not reflecting with one time browser refresh.
                    Thread.sleep(2000);
                    getDriver().navigate().refresh();
                    if (isElementPresent(By.xpath("//*[contains(text(),'"+ monthYear +"')]"))) {
                        blnDropValueFound = true;
                        break;
                    }
                }
                if (!blnDropValueFound) {
                    report.reportSelenium("error", "Drop value: "+ monthYear +" not listed with system date change");
                }
            }else{
                report.reportSelenium("fail", "Fail to change System date to : "+ dateTo);
            }
        }
        selectFromDropDown(dropHeader, monthYear);
    }

    public RosaSolutionQueue createPTOSolutionRequest(String category, String cycle, String aircraft, String event, String month, String bidLine, String coreConditional) throws Exception {
        DateUtil du = new DateUtil();

        String xpathCoreConditional = "//*[contains(text(),'PLACEHOLDER')]/preceding-sibling::*[position()=1]";
        String xpathCategory;

        selectMonthYear("MONTH / YEAR", month);
        selectFromDropDown("CATEGORY",category);
        selectFromDropDown("CYCLE",cycle);
        selectFromDropDown("AIRCRAFT",aircraft);
        selectFromDropDown("EVENT",event);
        selectFromDropDown("BID LINE",bidLine);

        try {
            if(!coreConditional.isEmpty()) {
                xpathCategory = xpathCoreConditional.replace(REPLACE_TXT, coreConditional);
                getDriver().findElement(By.xpath(xpathCategory)).click();
                report.reportSelenium("Pass", "Selected: "+coreConditional);
            }else{
                report.reportSelenium("Info", "Not Selected");
            }

        }catch(Exception e){
            report.reportSelenium("Fail", "Failed to select: "+coreConditional);
            LOGGER.error(e);
        }

        currentSystemDate = du.getCurrentDate(CommonFormats.ROSA_FORMAT);
        buttonClick(START_BTN);

        return new RosaSolutionQueue(currentSystemDate);
    }


    public RosaSolutionQueue selectFromDropDown(String event) throws Exception{

        try {
            buttonClick(EVENT_DROP);
            String newXpathLocator = DROP_VALUE.replace(REPLACE_TXT,event );
            printConsole("newXpathLocator: "+newXpathLocator);
            //*[contains(text(),Event)]
            WebElement element = getDriver().findElement(By.xpath(newXpathLocator));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            getDriver().findElements(BID_LINE_DR0P).get(2).click();
            buttonClick(HARD_LINE_DROP_DOWN_VALUE);
            buttonClick(START_SUBMIT_BTN);
            return new RosaSolutionQueue();
        }catch(Exception e){
            LOGGER.error(e);
        }
        return null;
    }

    public RosaSolutionQueue gotoPTOQueus(){
        try {
            buttonClick(PTO_QUEUE_BTN);
            report.reportSelenium("Pass", "Navigation to PTO queus is successful.");
            return new RosaSolutionQueue();
        }
        catch(Exception e){
            report.reportSelenium("Fail", "Failed to navigate to PTO queus.");
            LOGGER.error(e);
            return null;
            }
    }
}
