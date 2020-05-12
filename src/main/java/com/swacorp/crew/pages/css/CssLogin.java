package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.constants.CommonConstants;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.*;

public class CssLogin extends WinBasePage{

    ReportUtil reportCssLogin = new ReportUtil();
    private final Logger loggerCssLogin = Logger.getLogger(CssLogin.class);
    ObjectRepoCSS lftObjects =null;
    String rosaempID;
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();

    public CssLogin()  {
        lftObjects = super.cssObjectRepo;
    }

    public void loginCss() throws GeneralLeanFtException, IOException {

        if (!isCssAlradyLogedIn()) {
            new ProcessBuilder("cmd", "/c", EnvironmentConstants.CSSPATH).start();

            waitEditorTillVisible(lftObjects.loginDialog().userNameEditor(), CommonConstants.WAIT_LFT_TEN_SECS);

            lftObjects.loginDialog().userNameEditor().setText(EnvironmentConstants.CSSUSERID);

            lftObjects.loginDialog().passwordEditor().setText(EnvironmentConstants.CSSPASSWORD);
            lftObjects.loginDialog().loginButton().click();

            waitForJavaWindowTillVisible(lftObjects.CssMainWindow(), CommonConstants.WAIT_LFT_TEN_SECS);

            if (lftObjects.CssMainWindow().exists()){
                reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Login to CSS is successful.");
            }else{
                reportCssLogin.reportLeanFT(lftObjects.loginDialog(), "Fail", "CSS login is failed");
            }
        }else{
            if (lftObjects.CssMainWindow().exists()){
                String cssMAinWindowTitleInitial = lftObjects.CssMainWindow().getTitle();
                reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(),"Info", "CSS is already logged in. Application title: "+cssMAinWindowTitleInitial);
            }else{
                reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "CSS is open but failed to identify the main window.");
            }
        }
    }
    private boolean isCssAlradyLogedIn() throws GeneralLeanFtException {

        lftObjects.CssMainWindow().describe(Window.class, new WindowDescription.Builder().title(EnvironmentConstants.CSSWINDOWTITLE).build());
        try {
            if (lftObjects.CssMainWindow().exists()) {
                lftObjects.CssMainWindow().highlight();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            loggerCssLogin.error(e);
            return false;
        }
    }
}



