package com.swacorp.crew.pages.oqs;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OqsLogin extends BasePage {

    private final Logger LOGGER = Logger.getLogger(OqsLogin.class);
    ReportUtil report = new ReportUtil();
    //private final By USERID_LINK = By.xpath("//*[@class='yui-dt0-col-employeeId yui-dt-col-employeeId yui-dt-sortable yui-dt-first']//*[@class='yui-dt-liner' and text()='" + EnvironmentConstants.OQSLOGINUSER + "']");
    private final By USERID_LINK = By.xpath("//*[text()='ROSA']");
    //private final By USERID_LINK = By.ByCssSelector("#yui-rec87 > td.yui-dt0-col-employeeId.yui-dt-col-employeeId.yui-dt-sortable.yui-dt-first > div");
    private final By SEARCH_BUTTON = By.xpath("//*[@id='crewmemberSearchButtonId']");

    public String oqsUrl;


    public void setCompatibilityMode() throws InterruptedException, AWTException {

        Thread.sleep(500);

        Robot rob = new Robot();

        rob.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(100);
        rob.keyPress(KeyEvent.VK_T);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_T);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(100);
        rob.keyPress(KeyEvent.VK_R);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_R);

        report.reportSelenium("INFO", "Starting enterprise mode.");
        rob.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(100);

    }

    public void loginOQS(boolean applyEnterpriseMode) {
        try {
            getDriver().manage().window().maximize();
            System.out.println("Waiting for enterprise mode...");

            oqsUrl = EnvironmentConstants.OQSURL;
            getDriver().get(oqsUrl);


            waitUntilElementClickable(USERID_LINK);
            waitForElement(USERID_LINK);

            if (isElementVisible(USERID_LINK)) {
                report.reportSelenium("INFO", "Navigation to OQS application is successful. URL: "+oqsUrl);
            } else {
                report.report("ERROR", "Failed to navigate to OQS application URL: "+oqsUrl);
            }

            buttonClick(USERID_LINK);
            if(applyEnterpriseMode) {
                report.reportSelenium("INFO", "Enterprise mode: "+applyEnterpriseMode);
                setCompatibilityMode();

            } else {
                report.reportSelenium("INFO", "Enterprise mode: "+applyEnterpriseMode);
            }

            if (isElementVisible(SEARCH_BUTTON)) {
                LOGGER.info("Search Button exists on Loginpage..");
                report.reportSelenium("INFO", "User: " + EnvironmentConstants.OQSLOGINUSER + " logged in to OQS application");
            } else {
                report.report("ERROR", "User: " + EnvironmentConstants.OQSLOGINUSER + " failed to login to OQS application");
                LOGGER.info("Search Button NOT exists on Loginpage..");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void VerifyLoginSuccessful(boolean status){

    }
}
