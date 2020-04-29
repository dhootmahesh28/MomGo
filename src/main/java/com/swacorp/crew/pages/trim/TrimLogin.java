package com.swacorp.crew.pages.trim;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.winforms.Button;

import static com.swacorp.crew.utils.TestManager.dataProperties;

public class TrimLogin extends WinBasePage  {
    ReportUtil reportLoginPageAM = new ReportUtil();
    private final Logger loggerLoginPage = Logger.getLogger(TrimLogin.class);
    ObjectRepoTRiM lftObjects =null;
    Window mainWindow = null;
    public TrimLogin()  {
        lftObjects = super.trimObjectRepo;
    }
    private boolean loginSuccessful;
    private boolean alreadyLogin;
    public TrimHome loginTRiM(String user, String pass) throws  GeneralLeanFtException {
        mainWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow();
        try {
                mainWindow.maximize();
                mainWindow.activate();
                mainWindow.highlight();
                reportLoginPageAM.reportLeanFT(mainWindow,"info", "Trim is already logged in for the user name: "+user  );
                alreadyLogin = true;
                loginSuccessful = false;
        }catch(GeneralLeanFtException e){
            try {
                new TrimLogin().loginToTrim(user, pass);
                reportLoginPageAM.reportLeanFT(mainWindow,"pass", "Login to Trim is successful for the user name: "+user );
                loginSuccessful = true;
            }catch(Exception e1){
                reportLoginPageAM.reportLeanFT(mainWindow,"fail", "Login to Trim is NOT successful for the user name: "+user  );
                loggerLoginPage.error("Trim login failed.."+e1.getMessage());
                loginSuccessful = false;
                return  null;
            }
        }
        return new TrimHome();
    }

    private int loginToTrim (String user, String pass)throws  GeneralLeanFtException {
        int returnInt = 1;
        if (user.equalsIgnoreCase("")){
            user = dataProperties.getProperty("trimUserName");
        }

        if (pass.equalsIgnoreCase("")){
            pass = dataProperties.getProperty("trimUserPassword");
        }
        try {
            EditField userField = lftObjects.loginToSouthwestWindow().txtUserIDEditField();
            EditField paswordField = lftObjects.loginToSouthwestWindow().txtPasswordEditField();
            Button loginButton = lftObjects.loginToSouthwestWindow().btnLoginButton();

            new ProcessBuilder(EnvironmentConstants.TRiMAPPPATH).start();
            Highlight(lftObjects.loginToSouthwestWindow());
            setTextInEditBox(userField,user );
            setTextInEditBox(paswordField, pass);

           reportLoginPageAM.reportLeanFT(lftObjects.loginToSouthwestWindow(),"pass", "Login to Trim is successful for the user: "+user );
            loginButton.click();

            mainWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow();
            if (mainWindow.exists()){
                returnInt = 0;
                loginSuccessful = true;
            }else {
                returnInt = 1;
                loginSuccessful = false;
            }

        } catch (Exception e) {
            loggerLoginPage.error(e.getMessage());
        }
        return returnInt;
    }

    public void verifyLoginSuccessful(boolean status) {
        if (loginSuccessful && status){
            reportLoginPageAM.reportLeanFT(mainWindow,"pass", "Login to Trim is successful.." );
        }else if((alreadyLogin && status)){
            reportLoginPageAM.reportLeanFT(mainWindow,"info", "Trim is already logged in for the user: " );
        }else
                reportLoginPageAM.reportLeanFT(lftObjects.loginToSouthwestWindow(),"Fail", "Login to Trim is NOT successful." );
        }
}



