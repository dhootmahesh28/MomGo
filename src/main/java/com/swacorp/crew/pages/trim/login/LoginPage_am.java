package com.swacorp.crew.pages.trim.login;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
//import com.hp.lft.sdk.internal.winforms.WinFormsWindow;
import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.genericwrappers.editor.Editor1;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import com.swacorp.crew.genericwrappers.editor.Editor;

import java.io.IOException;

public class LoginPage_am  extends TestManager  {

    public static MainObjectRepoTrim or = null;
    //ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(LoginPage_am.class);
    private final String TRiM_TITLE = TRiMHomePage.TRiM_WINDOW;

    public LoginPage_am()  {
    }


    public void loginTRiM() throws  GeneralLeanFtException{

        try {
            MainObjectRepoTrim tsr = new MainObjectRepoTrim();
            System.out.println("Test..............");
            //or = new MainObjectRepoTrim();
            EditField x = tsr.loginToSouthwestWindow().txtPasswordEditField();
            Editor1<com.hp.lft.sdk.winforms.EditField> edt = new Editor1<com.hp.lft.sdk.winforms.EditField>();
            edt.set(x);
            System.out.println(">>>>>>>>>>>>>>>>>>>>" + edt.get().getClass());
            System.out.println(">>>>>>>>>>>>>>>>>>>>" + edt.get().getClass().toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    public void loginTRiM() {
        try {
            genericMethods.VerifyObjectExist(or.loginToSouthwestWindow().txtUserIDEditField(), true);
            genericMethods.Highlight(or.loginToSouthwestWindow().txtUserIDEditField());
            edt.setTextInEditBox(or.loginToSouthwestWindow().txtUserIDEditField(), "");
            edt.setTextInEditBox(or.loginToSouthwestWindow().txtUserIDEditField(), "admin");
            edt.setTextInEditBox(or.loginToSouthwestWindow().txtPasswordEditField(), "admin123");
            btn.setTextInEditBox(or.loginToSouthwestWindow().btnLoginButton());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    */

    /*public static void main(String args[]) throws  GeneralLeanFtException{
        MainObjectRepoTrim tsr = new MainObjectRepoTrim();
        System.out.println("Test..............");
        //or = new MainObjectRepoTrim();
        EditField x = tsr.loginToSouthwestWindow().txtPasswordEditField();
        Editor1<com.hp.lft.sdk.winforms.EditField> edt = new Editor1<com.hp.lft.sdk.winforms.EditField>();
        edt.set(x);
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+edt.get().getObjectName());
    }*/
}



