package com.swacorp.crew.tests.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.constants.ApplicationConstantsTrim;
import com.swacorp.crew.pages.trim.TrimHome;
import com.swacorp.crew.pages.trim.TrimLogin;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.TestUtil;

/**
 * Created by x257093 on 03-Jan-2020.
 */

public class TrimWrapper extends TestUtil {
    TrimLogin trimLoginPage ;
    TrimHome trimHomePageAM;
    String empNr;

    public TrimWrapper(){
        trimLoginPage = new TrimLogin();
    }

    public void selectFromTrimDueEmployeeSchdPlannerDropdown(String dropdownValue) throws GeneralLeanFtException{
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        trimHomePageAM.selectMenuFromBottomMenu("^D",dropdownValue);
    }

    public void expandTreeNodeAndValidate(String node, String subNode, String firstName, boolean visibility) throws GeneralLeanFtException {
        empNr = getDynamicData("EmpNumber");
        //empNr = "9717";
        trimHomePageAM.validateTreeNode(empNr,firstName, node, subNode, visibility);
    }

    //wrapperMethodToAddDuplicateEmployeeNrOQSTRIM
    public void employeeSearch(boolean visibility, boolean verifyActivechkbox) throws GeneralLeanFtException, InterruptedException {
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        trimLoginPage.verifyLoginSuccessful(true);
        trimHomePageAM.navigateMenu(ApplicationConstantsTrim.SELECT_EMPLOYEE_MENU);
        empNr = getDynamicData("EmpNumber");
        trimHomePageAM.searchEmployeesDetails(empNr, verifyActivechkbox);
        trimHomePageAM.validateSearchResults(visibility );
        trimHomePageAM.minimizeMainWindow();
    }

    /**Method description: This method is used to select the equipments from Equipments dropdown and select the primaryStatus from the primary dropdown from Employee details page in Trim.
     * method call: selectEquipmentAndPrimaryStatus("737", "First Officer")
     * method return: void*/
    public void selectEquipmentAndPrimaryStatus(String equipments, String primaryStatus) throws GeneralLeanFtException, InterruptedException {
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        trimHomePageAM.navigateMenu(ApplicationConstantsTrim.SELECT_EMPLOYEE_MENU);
        trimHomePageAM.selectEquipmentAndPrimaryStatus(empNr, equipments, primaryStatus);
    }
}
