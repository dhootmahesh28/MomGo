package com.swacorp.crew.pages.trim.login;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.common.LeanFTBasePage;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LoginPage extends LeanFTBasePage {

    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(LoginPage.class);
    private final String LOGIN_TO_SOUTHWEST = "Login to Southwest";
    private final String USERNAME_TXT = "txtUserID";
    private final String PASSWORD_TXT = "txtPassword";
    private final String LOGIN_BTN = "btnOK";
    private final String TRiM_TITLE = TRiMHomePage.TRiM_WINDOW;

    public void loginTRiM() throws GeneralLeanFtException, IOException {
        new ProcessBuilder(EnvironmentConstants.TRiMAPPPATH).start();
        setParentWindow(LOGIN_TO_SOUTHWEST);
        enterText(USERNAME_TXT, EnvironmentConstants.TRiMLOGINUSER);
        enterSecureText(PASSWORD_TXT, EnvironmentConstants.TRiMLOGINPASSWORD);
        report.reportLeanFT(windowObject, "INFO", "Entered credentials");
        buttonClick(LOGIN_BTN);
        setParentWindow(TRiM_TITLE);
    }
}
