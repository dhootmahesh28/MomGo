package com.swacorp.crew.pages.oqs.login;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OQSLoginPage extends BasePage {

    private final Logger LOGGER = Logger.getLogger(OQSLoginPage.class);
    ReportUtil report = new ReportUtil();
    //private final By USERID_LINK = By.xpath("//*[@class='yui-dt0-col-employeeId yui-dt-col-employeeId yui-dt-sortable yui-dt-first']//*[@class='yui-dt-liner' and text()='" + EnvironmentConstants.OQSLOGINUSER + "']");
    private final By USERID_LINK = By.xpath("//*[text()='ROSA']");
    //private final By USERID_LINK = By.ByCssSelector("#yui-rec87 > td.yui-dt0-col-employeeId.yui-dt-col-employeeId.yui-dt-sortable.yui-dt-first > div");
    private final By SEARCH_BUTTON = By.xpath("//*[@id='crewmemberSearchButtonId']");

    public String oqsUrl;


    public void setCompatibilityMode() throws InterruptedException, AWTException {

        Thread.sleep(5000);

        Robot rob = new Robot();

        rob.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(1000);
        rob.keyPress(KeyEvent.VK_T);
        Thread.sleep(1000);
        rob.keyRelease(KeyEvent.VK_T);
        Thread.sleep(1000);
        rob.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(1000);
        rob.keyPress(KeyEvent.VK_R);
        Thread.sleep(1000);
        rob.keyRelease(KeyEvent.VK_R);
        /*for (int i = 1; i <= 9; i++) {
            rob.keyPress(KeyEvent.VK_DOWN);
            rob.keyRelease(KeyEvent.VK_DOWN);
        }*/

        rob.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        rob.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);

    }

    public void loginOQS() {
        try {
            getDriver().manage().window().maximize();
            System.out.println("Waiting for enterprise mode...");

            oqsUrl = EnvironmentConstants.OQSURL;
            getDriver().get(oqsUrl);
            setCompatibilityMode();
            //waitUntilDomLoad();
            //waitUntilElementClickable(USERID_LINK);
            waitForElement(USERID_LINK);

            if (isElementVisible(USERID_LINK)) {
                report.reportSelenium("INFO", "Navigation to OQS application is successful. URL: "+oqsUrl);
            } else {
                report.report("ERROR", "Failed to navigate to OQS application URL: "+oqsUrl);
            }

            buttonClick(USERID_LINK);
            waitForElement(SEARCH_BUTTON);
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
