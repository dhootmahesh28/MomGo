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

import java.io.IOException;

public class LoginPage_am  extends TestManager {

    //ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(LoginPage_am.class);
    private final String TRiM_TITLE = TRiMHomePage.TRiM_WINDOW;

    public static void main(String args[]) throws GeneralLeanFtException, IOException {
        LoginPage_am loginPage_am = new LoginPage_am();
        loginPage_am.loginTRiM();
    }
    public void loginTRiM() throws GeneralLeanFtException, IOException {
        MainObjectRepoTrim or = new MainObjectRepoTrim(); //Before
        or.loginToSouthwestWindow().highlight();

        Window loginWindow = or.loginToSouthwestWindow();
        loginWindow.activate();
        if (loginWindow.isActive()) {
            print("loginWindow.getDescription()>>" + loginWindow.getDescription());
        }
        print(loginWindow.getText());

       // String newText = "22Login to Southwest777777777";
       // print("invalid desc");
        //loginWindow.setDescription(new WindowDescription.Builder().windowTitleRegExp(newText).build());
        com.swacorp.crew.genericwrappers.editor.Editor edt = new com.swacorp.crew.genericwrappers.editor.Editor();
        edt.setTextInEditBox(or.loginToSouthwestWindow().txtPasswordEditField(),"1478529");
        //appModel.loginDialog().userNameEditor()
        edt.setTextInEditBox(or.loginDialog().userNameEditor(),"2345");
        edt.setTextInEditBox(or.loginDialog().userNameEditor(),"13579");

        /*
        isObjectExist(loginWindow);

        newText = "Login to Southwest";
        loginWindow.setDescription(new WindowDescription.Builder().windowTitleRegExp(newText).build());

        print("loginWindow.getText()"+loginWindow.getText());

        isObjectExist(loginWindow);
        EditField passwordField = or.loginToSouthwestWindow().txtPasswordEditField();
        SetEditBox(passwordField, "");
        SetEditBox(passwordField, "test");

        Editor edt = or.loginDialog().userNameEditor();
        isObjectExist(edt);
        */
    }

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


}

