package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.GeneralLeanFtException;
//import com.sun.deploy.ref.AppModel;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
//import com.lftrepo.repo.SharedRepository.trim
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;


public class TRiMHomePage extends WinBasePage {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TRiMHomePage.class);
    public static final String TRiM_WINDOW = "TRiM";
    private final String MAIN_MENU_NAME = "barMainMenu";
    private final String FIND_EMPLOYEE_WINDOW = FindEmployeePage.FIND_EMPLOYEE_WINDOW;

    ObjectRepoTRiM or =null;
    public TRiMHomePage()  {
        or = super.trimObjectRepo;
    }
    Window mainWin = or.loginToSouthwestWindow();

    public int NavigateMenu(String NavigationString) throws  GeneralLeanFtException{
        int returnInt = 1;

        try {

            Highlight(mainWin);
            System.out.println(" Highlight(mainWin); ");
/*            setTextInEditBox(lftObjectRepo.loginToSouthwestWindow().txtUserIDEditField(),dataProperties.getProperty("trimUserName"));
            setTextInEditBox(lftObjectRepo.loginToSouthwestWindow().txtPasswordEditField(),dataProperties.getProperty("trimUserPassword"));
            btnClick(lftObjectRepo.loginToSouthwestWindow().btnLoginButton());
            // lftObjectRepo.loginToSouthwestWindow().btnLoginButton().click();
            WaitProperty(lftObjectRepo.loginToSouthwestWindow().btnLoginButton(),"visible", "false", 5);*/
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
