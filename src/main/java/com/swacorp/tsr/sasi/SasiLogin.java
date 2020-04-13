package com.swacorp.tsr.sasi;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SasiLogin extends BasePage {

    private final Logger LOGGER = Logger.getLogger(SasiLogin.class);
    ReportUtil report = new ReportUtil();
    private final By USERID_EDT = By.xpath("//input[@name='username']");
    private final By PASSWORD_EDT = By.xpath("//input[@name='password']");
    private final By SUBMIT_BTN = By.xpath("//input[@name='submit']");
    private final By LOGIN_PAGE_TXT = By.xpath("//h2[text()='Request Optimizer Solution Application']");


    public SasiHome loginSasi() {
        String url;
        url = EnvironmentConstants.SASIURL;
        getDriver().get(url);
        readyStateWait(getDriver());
        if (getDriver().getTitle().contains("SASI")) {
            report.reportSelenium("Pass", "Login to SASI is successful.");
            return new SasiHome();
        } else {
            report.reportSelenium("Failed", "Login to SASI is failed.");
            return null;
        }
    }
}