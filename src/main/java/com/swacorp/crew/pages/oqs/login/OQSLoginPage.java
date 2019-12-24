package com.swacorp.crew.pages.oqs.login;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class OQSLoginPage extends BasePage {

    private final Logger LOGGER = Logger.getLogger(OQSLoginPage.class);
    ReportUtil report = new ReportUtil();
    private final By USERID_LINK = By.xpath("//*[@class='yui-dt0-col-employeeId yui-dt-col-employeeId yui-dt-sortable yui-dt-first']//*[@class='yui-dt-liner' and text()='" + EnvironmentConstants.OQSLOGINUSER + "']");
    private final By SEARCH_BUTTON = By.xpath("//*[@id='crewmemberSearchButtonId']");
    public String oqsUrl;

    public void loginOQS() {
        oqsUrl = EnvironmentConstants.OQSURL;
        getDriver().get(oqsUrl);
        if (isElementVisible(USERID_LINK)) {
            report.reportSelenium("INFO", "Navigation to OQS application is successful");
        } else {
            report.report("ERROR", "Failed to navigate to OQS application");
            }
        buttonClick(USERID_LINK);
        if (isElementVisible(SEARCH_BUTTON)) {
            LOGGER.info("Search Button exists on Loginpage..");
            report.reportSelenium("INFO", "User: " + EnvironmentConstants.OQSLOGINUSER + " logged in to OQS application");
        } else {
            report.report("ERROR", "User: " + EnvironmentConstants.OQSLOGINUSER + " failed to login to OQS application");
            LOGGER.info("Search Button NOT exists on Loginpage..");
        }
    }
}
