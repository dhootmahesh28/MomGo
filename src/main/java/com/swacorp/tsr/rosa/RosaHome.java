package com.swacorp.tsr.rosa;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class RosaHome extends BasePage {

    private final Logger LOGGER = Logger.getLogger(RosaHome.class);
    ReportUtil report = new ReportUtil();

    private final By LOGIN_PAGE_TXT = By.xpath("//h2[text()='Request Optimizer Solution Application']");

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
}
