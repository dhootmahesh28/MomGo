package com.swacorp.crew.pages.sasi;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;

public class SasiLogin extends BasePage {

    private final Logger loggerSasiLogin = Logger.getLogger(SasiLogin.class);
    ReportUtil report = new ReportUtil();


    public SasiHome loginSasi() {
        String url;
        try {
            url = EnvironmentConstants.SASIURL;
            getDriver().get(url);
            readyStateWait(getDriver());
            if (getDriver().getTitle().contains("SASI")) {
                report.reportSelenium("Pass", "Login to SASI is successful.");

            } else {
                report.reportSelenium("Failed", "Login to SASI is failed.");
                return null;
            }

        }catch(Exception e){
                loggerSasiLogin.error(e);
        }
        return new SasiHome();
    }
}