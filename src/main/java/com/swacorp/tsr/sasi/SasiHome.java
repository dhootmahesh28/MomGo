package com.swacorp.tsr.sasi;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SasiHome extends BasePage {

    private final Logger LOGGER = Logger.getLogger(SasiHome.class);
    ReportUtil report = new ReportUtil();

    /*private final By LOGIN_PAGE_TXT = By.xpath("//h2[text()='Request Optimizer Solution Application']");
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
    private final By PTO_QUEUE_BTN = By.xpath("(//*[text()='Queue'])[1]");*/

    private final By WORLD_VIEWER_PARADISE = By.xpath("//*[text()='World Viewer - Paradise']");
    private final By SELECT_DRP = By.xpath("//select[@id='qt']");
    private final By EMPLOYEE = By.xpath("//input[@name='attributeEmployeeID']");
    private final By submit = By.xpath("//*[@id='submitRow']/td/input");
    private final By firstAnchorLinq = By.xpath("//a[@title = 'click for detailed crew view'][1]");


    public void readNonFlyDetails(String sectionToRead){
        String xpathPortion = "//*[text() = '"+"PLACEHOLDER"+"'][1]";
        String allTd   = "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr";

        String airport =   "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[1]/td[3]/a[1]";
        String startDate = "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[4]/td[3]";
        String endDate =   "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[13]/td[3]";

        System.out.println(getDriver().findElement(By.xpath(airport)).getText());
        System.out.println(getDriver().findElement(By.xpath(startDate)).getText());
        System.out.println(getDriver().findElement(By.xpath(endDate)).getText());
        System.out.println(getDriver().findElement(By.xpath("//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[12]/td[3]")).getText());
        System.out.println(getDriver().findElement(By.xpath("//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[11]/td[3]")).getText());
        System.out.println(getDriver().findElement(By.xpath("//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[10]/td[3]")).getText());
        scrollToElement(getDriver().findElement(By.xpath(airport)));
        String expectedBase = "DEN";
        String expectedStartDate = "2020-04-02";
        if (getDriver().findElement(By.xpath(airport)).getText().contains(expectedBase)){
            report.reportSelenium("Pass", "The expected airport-id fould in SASI: "+expectedBase);
        }

        scrollToElement(getDriver().findElement(By.xpath(endDate)));
        if (getDriver().findElement(By.xpath(startDate)).getText().contains(expectedStartDate)){
            report.reportSelenium("Pass", "The expected start date is found in SASI: "+expectedStartDate);
        }

    }

public void clickFirstLink(){
    buttonClick(firstAnchorLinq);
}

    public void selectData() throws Exception{
        //buttonClick(SELECT_DRP);
        //selectOption(SELECT_DRP,"Crew");
        Thread.sleep(5000);
        getDriver().switchTo().frame(0);
        selectOption(SELECT_DRP, "Crew ");
        enterText(EMPLOYEE,"68244");
        buttonClick(submit);
    }

    public void clickWorldViewer(){
        buttonClick(WORLD_VIEWER_PARADISE);


        if (isElementVisible(WORLD_VIEWER_PARADISE)){
            report.reportSelenium("Pass", "Training Optimizer page appeared.");
            printConsole("Training Optimizer page appeared");
        }else{
            report.reportSelenium("Fail", "Training Optimizer page NOT appeared.");
            printConsole("Training Optimizer page NOT appeared");
        }
    }




}
