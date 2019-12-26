package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.swacorp.crew.wrappers.GenericMethods;

import java.util.HashMap;

public class TrimHomePageAM extends WinBasePage{
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TrimHomePageAM.class);
    MainObjectRepoTrim or =null;
    public HashMap<String, String> pgMap = new HashMap<>();

    public TrimHomePageAM()  {
        or = super.or;
    }

    private int VerifyWindowExist(Window o) throws  GeneralLeanFtException{
        int retVal = 1;
        if (o.exists()){
            retVal =0;
            return retVal;
        }
        return retVal;
    }


    public int NavigateMenu(String NavigationString) throws  GeneralLeanFtException{
        int returnInt = 1;
        System.out.println("NavigateMenu.........");
        Window mainWin = or.tRiMTrainingResourceManagerSouthwestWindow();
        UiObject topMenuStrip = or.tRiMTrainingResourceManagerSouthwestWindow().mainMenuUiObject();
        String[] arrMenuItems = NavigationString.split("-->");

        try {
            topMenuStrip.highlight();
            for (String menuItem : arrMenuItems) {
                topMenuStrip.sendKeys(menuItem);
            }
            returnInt = 0;
            return returnInt;
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnInt;
    }

    public int SearchEmployeesDetails(String empNumber) throws  GeneralLeanFtException{
        int retVal = 1;
        String empNumberFromApp;
        String fldFirstNamefromApp;

        Window winFindEmployee = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField fldEmpSearch = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button btnShowEmpDetails = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeDetailsButton();

        Window winFindEmployeeSearch = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow();
        EditField fldEmpNumber = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtEmployeeNumberEditField();
        EditField fldFirstName = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtFirstNameEditField();
        //EditField empNumber = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().


            try {
                if (winFindEmployee.exists(5)){
                    fldEmpSearch.setText(empNumber);
                    btnShowEmpDetails.click();
                }else{
                    throw new GeneralLeanFtException("winFindEmployee does not exist.");
                }

                if (winFindEmployeeSearch.exists()){
                 empNumberFromApp = fldEmpNumber.getText();
                    fldFirstNamefromApp = fldFirstName.getText();
                    }else{
                    throw  new GeneralLeanFtException("winFindEmployeeSearch does not exist.");
                }try{
                    pgMap.put("TRIM_EMP_NO", empNumberFromApp);
                    pgMap.put("TRIM_EMP_FIRSTNAME", fldFirstNamefromApp);

                }catch(Exception e){
                    e.printStackTrace();
                }

                winFindEmployeeSearch.close();
                winFindEmployee.close();

            }catch(Exception e){
                e.printStackTrace();
            }
            return 0;
        }


    public void CloseApplication() throws  GeneralLeanFtException {
        FlushObjects();
    }
}


