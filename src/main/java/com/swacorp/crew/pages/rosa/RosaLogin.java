package com.swacorp.crew.pages.rosa;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class RosaLogin extends BasePage {

    private final Logger LOGGER = Logger.getLogger(RosaLogin.class);
    ReportUtil report = new ReportUtil();
    private static final By USERID_EDT = By.xpath("//input[@name='username']");
    private static final By PASSWORD_EDT = By.xpath("//input[@name='password']");
    private static final By SUBMIT_BTN = By.xpath("//input[@name='submit']");


    public RosaHome loginRosa() {
        String url;
        String userid;
        String pass;

        url = EnvironmentConstants.ROSAURL;
        userid = EnvironmentConstants.ROSAUSERID;
        pass = EnvironmentConstants.ROSAPASSWORD;

        getDriver().get(url);
        enterText(USERID_EDT, userid);
        enterText(PASSWORD_EDT, pass);
        buttonClick(SUBMIT_BTN);

        if (getDriver().getTitle().equalsIgnoreCase("ROSA")) {
            report.reportSelenium("Pass", "Login to ROSA is successful.");
            return new RosaHome();
        } else {
            report.reportSelenium("Failed", "Login to ROSA is failed.");
            return null;
        }
    }
}