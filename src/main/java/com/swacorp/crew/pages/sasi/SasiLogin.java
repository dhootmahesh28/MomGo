package com.swacorp.crew.pages.sasi;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Map;

public class SasiLogin extends BasePage {

    private final Logger loggerSasiLogin = Logger.getLogger(SasiLogin.class);
    ReportUtil report = new ReportUtil();
    private Map<String, Map<String, ArrayList<String[]>>> masterHM;

    public SasiLogin(Map<String, Map<String, ArrayList<String[]>>> hm) {
        masterHM = hm;
    }
    public SasiLogin(){
    }

    public SasiHome loginSasi() {
        String url;
        url = EnvironmentConstants.SASIURL;
        getDriver().get(url);
        readyStateWait(getDriver());
        if (getDriver().getTitle().contains("SASI")) {
            report.reportSelenium("Pass", "Login to SASI is successful.");
            return new SasiHome(masterHM);
        } else {
            report.reportSelenium("Failed", "Login to SASI is failed.");
            return null;
        }
    }
}