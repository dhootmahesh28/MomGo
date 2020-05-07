package com.swacorp.crew.pages.oqs;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OqsLogin extends BasePage {

    private final Logger loggerOqsLogin = Logger.getLogger(OqsLogin.class);
    ReportUtil report = new ReportUtil();
    private final By xpathUserIdLink = By.xpath("//*[text()='ROSA']");
    private final By xpathSearchButton = By.xpath("//*[@id='crewmemberSearchButtonId']");
    private String oqsUrl;


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
            oqsUrl = EnvironmentConstants.OQSURL;
            getDriver().get(oqsUrl);


            waitUntilElementClickable(xpathUserIdLink);
            waitForElement(xpathUserIdLink);

            if (isElementVisible(xpathUserIdLink)) {
                report.reportSelenium("INFO", "Navigation to OQS application is successful. URL: " + oqsUrl);
            } else {
                report.report("ERROR", "Failed to navigate to OQS application URL: " + oqsUrl);
            }

            buttonClick(xpathUserIdLink);
            if (applyEnterpriseMode) {
                report.reportSelenium("INFO", "Enterprise mode: " + applyEnterpriseMode);
                setCompatibilityMode();

            } else {
                report.reportSelenium("INFO", "Enterprise mode: " + applyEnterpriseMode);
            }

            if (isElementVisible(xpathSearchButton)) {
                loggerOqsLogin.info("Search Button exists on Loginpage..");
                report.reportSelenium("INFO", "User: " + EnvironmentConstants.OQSLOGINUSER + " logged in to OQS application");
            } else {
                report.report("ERROR", "User: " + EnvironmentConstants.OQSLOGINUSER + " failed to login to OQS application");
                loggerOqsLogin.info("Search Button NOT exists on Loginpage..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
