package com.swacorp.crew.pages.trim.login;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
//import com.hp.lft.sdk.internal.winforms.WinFormsWindow;
import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import com.swacorp.crew.genericwrappers.editor.Editor;

import java.io.IOException;

public class LoginPage_am  extends TestManager {

    //ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(LoginPage_am.class);
    private final String TRiM_TITLE = TRiMHomePage.TRiM_WINDOW;

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
/*
    public static void print(String s){
        System.out.println("Test message:>>"+s);
    }


    public static void isObjectExist(TestObject obj){
        try {
            if (obj.exists()) {
                print("Object found: "+obj.getDisplayName());
            }
        }
        catch (Exception e){
            print("Object Not found: "+obj.getDisplayName());
        }
    }


    public static void SetEditBox(EditField obj, String txt){
        try {
            if (obj.exists()) {
                print("Object found: "+obj.getDisplayName());
                obj.setText(txt);
            }
        }
        catch (Exception e){
            print("Object Not found: "+obj.getDisplayName());
        }
    }

*/
}

