package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.GeneralLeanFtException;
//import com.sun.deploy.ref.AppModel;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
//import com.lftrepo.repo.SharedRepository.trim
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;

import static com.swacorp.crew.utils.TestManager.dataProperties;


public class TRiMHomePage extends WinBasePage {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TRiMHomePage.class);
    public static final String TRiM_WINDOW = "TRiM";
    private final String MAIN_MENU_NAME = "barMainMenu";
    private final String FIND_EMPLOYEE_WINDOW = FindEmployeePage.FIND_EMPLOYEE_WINDOW;

    MainObjectRepoTrim or =null;
    public TRiMHomePage()  {
        or = super.or;
    }
    Window mainWin = or.loginToSouthwestWindow();

    public int NavigateMenu(String NavigationString) throws  GeneralLeanFtException{
        int returnInt = 1;

        try {

            Highlight(mainWin);
            System.out.println(" Highlight(mainWin); ");
/*            setTextInEditBox(or.loginToSouthwestWindow().txtUserIDEditField(),dataProperties.getProperty("trimUserName"));
            setTextInEditBox(or.loginToSouthwestWindow().txtPasswordEditField(),dataProperties.getProperty("trimUserPassword"));
            btnClick(or.loginToSouthwestWindow().btnLoginButton());
            // or.loginToSouthwestWindow().btnLoginButton().click();
            WaitProperty(or.loginToSouthwestWindow().btnLoginButton(),"visible", "false", 5);*/
            return 0;

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnInt;
    }
    /*
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TRiMHomePage.class);
    public static final String TRiM_WINDOW = "TRiM";
    private final String MAIN_MENU_NAME = "barMainMenu";
    private final String FIND_EMPLOYEE_WINDOW = FindEmployeePage.FIND_EMPLOYEE_WINDOW;



     public void selectMenu(String menuItems) throws GeneralLeanFtException {
        windowClick();
        String[] arrMenuItems = menuItems.split("-->");
        for (String menuItem : arrMenuItems)
           toolBarSendKeys(MAIN_MENU_NAME, menuItem);
        setWindow(FIND_EMPLOYEE_WINDOW);
    }

    public void closeTRiMWindow() throws GeneralLeanFtException {
        closeHomeWindow();
    }
    */
    public void selectMenu(String menuItems) throws GeneralLeanFtException {
        windowClick();
        String[] arrMenuItems = menuItems.split("-->");
        for (String menuItem : arrMenuItems)
            toolBarSendKeys(MAIN_MENU_NAME, menuItem);
        setWindow(FIND_EMPLOYEE_WINDOW);
    }
}
