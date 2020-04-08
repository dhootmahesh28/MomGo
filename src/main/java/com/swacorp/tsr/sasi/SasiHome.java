package com.swacorp.tsr.rosa;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RosaHome extends BasePage {

    private final Logger LOGGER = Logger.getLogger(RosaHome.class);
    ReportUtil report = new ReportUtil();

    private final By LOGIN_PAGE_TXT = By.xpath("//h2[text()='Request Optimizer Solution Application']");
    private final By PILOT_TRAINING_BTN = By.xpath("//button[@id='button-1']");
    private final By PILOT_TRAINING_OPTIMIZER_TXT = By.xpath("//h2[text()='Pilot Training Optimizer']");
    private final By CONDITIONAL_RADIO_BTN = By.xpath("//*[text()='Conditional']");
    private final By CONDITIONAL_CORE_RADIO_BTN = By.xpath("//*[text()='Core']");
    private final By START_BTN = By.xpath("//*[@id='startButton']");
    private final By RESET_BTN = By.xpath("//*[text()='Reset']");
    private final By EVENT_DROP = By.xpath("/html/body/app-root/div[2]/div/div/app-pilot-training-request/div[2]/div[1]/div[2]/cl-dropdown/div/div/div[2]");
    private final By BID_LINE_DR0P = By.xpath("//*[@class='cl-icon--dark-midnight cl-icon cl-icon-chevron-down']");
    private final By HARD_LINE_DROP_DOWN_VALUE = By.xpath("//*[text()=' Hard Line ']");
    private final By CONDITIONAL_RADIO = By.xpath("//input[@class='cl-radio__input']");
    private final By START_SUBMIT_BTN = By.xpath("//*[text()='Start']");
    private final By PTO_QUEUE_BTN = By.xpath("(//*[text()='Queue'])[1]");

    private final String EVENT_DROP_VALUE = "//*[text() = ' PLACEHOLDER ']";

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
            printConsole("Training Optimizer page appeared");
        }else{
            report.reportSelenium("Fail", "Training Optimizer page NOT appeared.");
            printConsole("Training Optimizer page NOT appeared");
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
            report.reportSelenium("Fail", "Training Optimizer page NOT appeared.");
        }else{
            report.reportSelenium("Fail", "Training Optimizer page NOT appeared.");
        }
    }

    public void verifyCoreRadioButtonExist(){
        if (isElementVisible(START_BTN)) {
            report.reportSelenium("Fail", "Start button exists.");
        }else{
            report.reportSelenium("Fail", "Start button does not exists.");
        }

        if (isElementVisible(RESET_BTN)) {
            report.reportSelenium("Fail", "Reset button exists.");
        }else{
            report.reportSelenium("Fail", "Reset button does not exists.");
        }
    }

    public RosaSolutioQueue selectFromDropDown(String Event) throws Exception{

        try {
            buttonClick(EVENT_DROP);
            String newXpathLocator = EVENT_DROP_VALUE.replace("PLACEHOLDER",Event );
            printConsole("newXpathLocator: "+newXpathLocator);

            WebElement element = getDriver().findElement(By.xpath(newXpathLocator));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            buttonClick(getDriver().findElement(By.xpath(newXpathLocator)));
            getDriver().findElements(BID_LINE_DR0P).get(2).click();
            buttonClick(HARD_LINE_DROP_DOWN_VALUE);
            buttonClick(START_SUBMIT_BTN);
            return new RosaSolutioQueue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public RosaSolutioQueue gotoPTOQueus(){
        try {
            buttonClick(PTO_QUEUE_BTN);
            report.reportSelenium("Pass", "Navigation to PTO queus is successful.");
            return new RosaSolutioQueue();
        }
        catch(Exception e){
            report.reportSelenium("Fail", "Failed to navigate to PTO queus.");
            return null;
            }
    }
}
