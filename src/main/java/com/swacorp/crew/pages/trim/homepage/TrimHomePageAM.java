package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.swacorp.crew.utils.ReportStatus;

import java.util.HashMap;

public class TrimHomePageAM extends WinBasePage{
    ReportUtil report = new ReportUtil();
    //ReportUtil report = null;
    private final Logger LOGGER = Logger.getLogger(TrimHomePageAM.class);
    MainObjectRepoTrim or =null;
    public HashMap<String, String> pgMap = new HashMap<>();

    public TrimHomePageAM()  {
        or = super.lftObjectRepo;
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
        ReportUtil report = new ReportUtil();
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

    public int SearchEmployeesDetails(String empNumber) throws  GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        String empNumberFromApp;
        String fldFirstNamefromApp;
        Window winFindEmployee = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField fldEmpSearch = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button btnShowEmpDetails = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeDetailsButton();
        Window winFindEmployeeSearch = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow();
        EditField fldEmpNumber = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtEmployeeNumberEditField();
        EditField fldFirstName = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtFirstNameEditField();

        try {
            //if (winFindEmployee.exists(5)){
            Highlight(winFindEmployee);
            //fldEmpSearch.setText(empNumber);
            setTextInEditBox(fldEmpSearch, empNumber);
            //btnShowEmpDetails.click();
            try {
                btnClick(btnShowEmpDetails);
            }catch(Exception e){
                report.report("Fail","Error occured while clicking on button. - btnShowEmpDetails");
            }

            if (Highlight(winFindEmployeeSearch)) {
                empNumberFromApp = fldEmpNumber.getText();
                fldFirstNamefromApp = fldFirstName.getText();

                pgMap.put("TRIM_EMP_NO", empNumberFromApp);
                pgMap.put("TRIM_EMP_FIRSTNAME", fldFirstNamefromApp);
                retVal = 0;
            }else{
                retVal = 1;
            }
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "info", "Search result after clicking on Search button.");
        }catch( Exception  e){
            e.printStackTrace();
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occured while searching the employee number on Find Employee window of Trim pplication.");
        }finally {
            {
                //winFindEmployeeSearch.close();
                //winFindEmployee.close();
            }
        }
        return retVal;
    }

    public void CloseApplication() throws  GeneralLeanFtException {

        FlushObjects();
    }

    public void ValidateSearchResults(int intStatus, String status, String Msg){
        if (intStatus == 0 && status.equalsIgnoreCase("Pass") ){
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", Msg);
        }else if(intStatus == 1 && status.equalsIgnoreCase("Pass")){
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", Msg);
        }else{
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "FAILED: "+Msg);
        }

    }
}


