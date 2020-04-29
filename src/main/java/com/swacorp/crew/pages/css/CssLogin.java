package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.constants.EnumWaitConstants;
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

    public CssLogin(Map<String, Map<String, ArrayList<String[]>>> masterHMrosa) {
        this();
        masterHM = masterHMrosa;
        loggerCssLogin.info("CssLogin called with MasterHM constructor..");
    }

    public CssLogin()  {
        lftObjects = super.cssObjectRepo;
    }

    public void loginCss(String userName, String password, String cssPath) throws GeneralLeanFtException, IOException {
        int cssLaunched = 0;
        try{
            new ProcessBuilder("cmd", "/c", cssPath).start();
            cssLaunched = 1;
        }catch(Exception e){
               reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(), "Fail","Path not found: "+cssPath);
            return;
        }

        if (cssLaunched == 1){
            try{
                if (!isCssAlradyLogedIn()) {
                    WaitEditorTillVisible(lftObjects.loginDialog().userNameEditor(), EnumWaitConstants.WaitDuration.TEN.status());
                    lftObjects.loginDialog().userNameEditor().setText(userName);
                    lftObjects.loginDialog().passwordEditor().setText(password);
                    lftObjects.loginDialog().loginButton().click();
                    WaitForJavaWindowTillVisible(lftObjects.CssMainWindow(), EnumWaitConstants.WaitDuration.TEN.status());
                    if (lftObjects.CssMainWindow().exists()){
                        reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Login to CSS is successful.");
                    }else{
                        reportCssLogin.report("FAIL", "CSS login is failed");
                    }
                }else{
                    if (lftObjects.CssMainWindow().exists()){
                        String cssMainWindowTitleInitial = lftObjects.CssMainWindow().getTitle();
                        loggerCssLogin.info("CSS main window title: "+cssMainWindowTitleInitial);
                        reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "CSS is already logged in for userId: "+userName);
                    }else{
                        reportCssLogin.report("FAIL", "CSS is open but failed to identify the main window.");
                    }
                }
                readMasterHM();
            }catch(Exception e){
                reportCssLogin.report("FAIL", "Failed to login to CSS. UserID: " +userName+", Error: "+e.getMessage());
            }
        }
    }

    public void readMasterHM(){

        for (Map.Entry<String, Map<String, ArrayList<String[]>>> allEmpId : masterHM.entrySet()){
            rosaempID = allEmpId.getKey();
            Map<String, ArrayList<String[]>> eachEmpMap = allEmpId.getValue();

            for (Map.Entry<String, ArrayList<String[]>> entry : eachEmpMap.entrySet()){
                loggerCssLogin.info("Keys of masterHM:"+entry.getKey());
                Map<String, ArrayList<String[]>> empDetailsMap = allEmpId.getValue();
                training = empDetailsMap.get("trng");
                triptopull = empDetailsMap.get("triptopull");
            }
            if(true) {
                break;
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




