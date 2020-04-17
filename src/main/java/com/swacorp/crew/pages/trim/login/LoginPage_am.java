package com.swacorp.crew.pages.trim.login;

import com.hp.lft.sdk.GeneralLeanFtException;
//import com.hp.lft.sdk.internal.winforms.WinFormsWindow;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.winforms.Button;

import static com.swacorp.crew.utils.TestManager.dataProperties;

public class LoginPage_am  extends WinBasePage  {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(LoginPage_am.class);
    private final String TRiM_TITLE = TRiMHomePage.TRiM_WINDOW;
    ObjectRepoTRiM lftObjects =null;
    Window mainWindow = null;
    public LoginPage_am()  {
        lftObjects = super.trimObjectRepo;
        //this.report = report;
    }
    private boolean loginSuccessful;
    public TrimHomePageAM loginTRiM(String user, String pass) throws  GeneralLeanFtException {
        mainWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow();
        try {
                mainWindow.maximize();
                mainWindow.activate();
            loginSuccessful = true;
        }catch(GeneralLeanFtException e){
            try {
                new LoginPage_am().loginToTrim(user, pass);
                loginSuccessful = true;
            }catch(Exception e1){
                loginSuccessful = false;
                e1.printStackTrace();
                return  null;
            }
        }
        return new TrimHomePageAM();
    }

    private int loginToTrim (String user, String pass)throws  GeneralLeanFtException {
        int returnInt = 1;
        try {
            EditField UserField = lftObjects.loginToSouthwestWindow().txtUserIDEditField();
            EditField PaswordField = lftObjects.loginToSouthwestWindow().txtPasswordEditField();
            Button loginButton = lftObjects.loginToSouthwestWindow().btnLoginButton();

            new ProcessBuilder(EnvironmentConstants.TRiMAPPPATH).start();
            Highlight(lftObjects.loginToSouthwestWindow());
            setTextInEditBox(UserField, dataProperties.getProperty("trimUserName"));
            setTextInEditBox(PaswordField, dataProperties.getProperty("trimUserPassword"));

           report.reportLeanFT(lftObjects.loginToSouthwestWindow(),"pass", "Login to Trim is successful" );
            loginButton.click();
//            mainWindow.wait(5);
            Thread.sleep(5000);
            mainWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow();
            if (mainWindow.exists()){
                returnInt = 0;
                loginSuccessful = true;
            }else {
                returnInt = 1;
                loginSuccessful = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnInt;
    }

    public void VerifyLoginSuccessful(boolean status) {
        if (loginSuccessful && status){
            report.reportLeanFT(mainWindow,"pass", "Login to Trim is successful.." );
        }else{
            report.reportLeanFT(lftObjects.loginToSouthwestWindow(),"Fail", "Login to Trim is NOT successful." );
        }
    }
}



