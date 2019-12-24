package com.swacorp.crew.pages.trim.login;

import com.hp.lft.sdk.GeneralLeanFtException;
//import com.hp.lft.sdk.internal.winforms.WinFormsWindow;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.utils.EnvironmentConstants;
import org.apache.log4j.Logger;
import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.winforms.Button;

import java.lang.reflect.Method;

import static com.swacorp.crew.utils.TestManager.dataProperties;

public class LoginPage_am  extends WinBasePage  {

    private final Logger LOGGER = Logger.getLogger(LoginPage_am.class);
    private final String TRiM_TITLE = TRiMHomePage.TRiM_WINDOW;
    MainObjectRepoTrim or =null;
    public LoginPage_am()  {
        or = super.or;
    }

    public int loginTRiM(String user, String pass) throws  GeneralLeanFtException{
        int returnInt = 1;
        try {
            MainObjectRepoTrim tsr = new MainObjectRepoTrim();
            System.out.println("Test..............");
            new ProcessBuilder(EnvironmentConstants.TRiMAPPPATH).start();
            Highlight(or.loginToSouthwestWindow());
            setTextInEditBox(or.loginToSouthwestWindow().txtUserIDEditField(),dataProperties.getProperty("trimUserName"));
            setTextInEditBox(or.loginToSouthwestWindow().txtPasswordEditField(),dataProperties.getProperty("trimUserPassword"));
            btnClick(or.loginToSouthwestWindow().btnLoginButton());
           // or.loginToSouthwestWindow().btnLoginButton().click();
            WaitProperty(or.loginToSouthwestWindow().btnLoginButton(),"visible", "false", 5);
            return 0;

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnInt;
    }

    public int navigateMainMenu(String user, String pass) throws  GeneralLeanFtException{
        int returnInt = 1;
        try {
            MainObjectRepoTrim tsr = new MainObjectRepoTrim();
            System.out.println("Test..............");
            new ProcessBuilder(EnvironmentConstants.TRiMAPPPATH).start();
            Highlight(or.loginToSouthwestWindow());
            setTextInEditBox(or.loginToSouthwestWindow().txtUserIDEditField(),dataProperties.getProperty("trimUserName"));
            setTextInEditBox(or.loginToSouthwestWindow().txtPasswordEditField(),dataProperties.getProperty("trimUserPassword"));
            btnClick(or.loginToSouthwestWindow().btnLoginButton());
            // or.loginToSouthwestWindow().btnLoginButton().click();
            WaitProperty(or.loginToSouthwestWindow().btnLoginButton(),"visible", "false", 5);
            return 0;

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnInt;
    }
}



