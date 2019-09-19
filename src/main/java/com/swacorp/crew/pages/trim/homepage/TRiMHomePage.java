package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.common.LeanFTBasePage;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;

public class TRiMHomePage extends LeanFTBasePage {
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
}
