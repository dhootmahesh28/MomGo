package com.swacorp.crew.pages.trim.login;

import com.hp.lft.sdk.GeneralLeanFtException;
//import com.hp.lft.sdk.internal.winforms.WinFormsWindow;
import com.hp.lft.sdk.winforms.UiObject;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.oqs.homepage.HomePageAM;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
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

    public TrimHomePageAM loginTRiM(String user, String pass) throws  GeneralLeanFtException {
        Window mainWindow = or.tRiMTrainingResourceManagerSouthwestWindow();
        try {
                mainWindow.maximize();
                mainWindow.activate();
        }catch(GeneralLeanFtException e){
            int x =3;
            try {

                new LoginPage_am().loginToTrim(user, pass);
            }catch(Exception e1){
                e1.printStackTrace();
                return  null;
            }
        }
        return new TrimHomePageAM();
    }

    private int loginToTrim (String user, String pass)throws  GeneralLeanFtException {
        int returnInt = 1;
        try {
            //MainObjectRepoTrim tsr = new MainObjectRepoTrim();

            EditField UserField = or.loginToSouthwestWindow().txtUserIDEditField();
            EditField PaswordField = or.loginToSouthwestWindow().txtPasswordEditField();
            Button loginButton = or.loginToSouthwestWindow().btnLoginButton();

            new ProcessBuilder(EnvironmentConstants.TRiMAPPPATH).start();
            Highlight(or.loginToSouthwestWindow());
            setTextInEditBox(UserField, dataProperties.getProperty("trimUserName"));
            setTextInEditBox(PaswordField, dataProperties.getProperty("trimUserPassword"));
            loginButton.click();
            returnInt = 0;
            return returnInt;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnInt;
    }
}



