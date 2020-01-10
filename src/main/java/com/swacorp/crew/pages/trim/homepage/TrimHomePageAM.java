package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.swacorp.crew.utils.ReportStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrimHomePageAM extends WinBasePage{
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TrimHomePageAM.class);
    MainObjectRepoTrim or =null;
    public HashMap<String, String> pgMap = new HashMap<>();
    private String empNumberFromApp = "";
    private String fldFirstNameFromApp = "";
    private boolean searchFound;


    //Window winFindEmployee = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
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
        Window winFindEmployee = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField fldEmpSearch = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button btnShowEmpDetails = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeDetailsButton();
        Window winFindEmployeeSearch = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow();
        EditField fldEmpNumber = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtEmployeeNumberEditField();
        EditField fldFirstName = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtFirstNameEditField();

        try {
            //getDynamicData("EmployeeNumber");
            System.out.println("getDynamicData EmployeeNumber" + getDynamicData("EmployeeNumber"));
            Highlight(winFindEmployee);
            setTextInEditBox(fldEmpSearch, empNumber);
            VerifyNoDuplicate();
            try {
                btnClick(btnShowEmpDetails);
                VerifyActive();
            }catch(Exception e){
                report.report("Fail","Error occured while clicking on button. - btnShowEmpDetails");
            }

            if (Highlight(winFindEmployeeSearch)) {
                empNumberFromApp = fldEmpNumber.getText();
                fldFirstNameFromApp = fldFirstName.getText();

                pgMap.put("TRIM_EMP_NO", empNumberFromApp);
                pgMap.put("TRIM_EMP_FIRSTNAME", fldFirstNameFromApp);
                retVal = 0;
                searchFound = true;
            }else{
                searchFound = false;
                retVal = 1;
            }
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "info", "Search result after clicking on Search button.");
        }catch( Exception  e){
            e.printStackTrace();
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occured while searching the employee number on Find Employee window of Trim pplication.");
        }
        return retVal;
    }

    public void CloseApplication() throws  GeneralLeanFtException {
        FlushObjects();
    }

   public void ValidateSearchResults(Map<String, String> runtimeData , String[] testData){
        if (empNumberFromApp.equalsIgnoreCase(runtimeData.get("CrewEmployeeNumber"))){
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Employee number matched.");
        }else{
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Employee number not matched.");
        }

        if (fldFirstNameFromApp.equalsIgnoreCase(testData[4])){
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Employee first name matched.");
        }else{
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Employee first name  not matched.");
        }
    }

    public void ValidateSearchResults(boolean status) throws GeneralLeanFtException{
        String visible = status ? "visible": "not visible";
        if ((status && searchFound) || (!status && !searchFound)){
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "CREW employee details "+visible+" on Trim.");
        }else{
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "CREW employee details "+visible+" on Trim.\"");
        }
        flushAllChileWindowsExceptMain();
    }


    public void VerifyNoDuplicate()  throws GeneralLeanFtException{

        or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        List<ListItem> lst = or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().lstSearchListBox1().getItems();
        if (lst.size() ==1 ){
                report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "No duplicate record");
        }else{
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Duplicate record");
        }
    }

    public void VerifyActive() throws GeneralLeanFtException{
        CheckedState state = or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().activeCheckBox().getState();
        if (state.toString().equalsIgnoreCase("checked")){
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Checkbox enabled");
        }else{
            report.reportLeanFT(or.tRiMTrainingResourceManagerSouthwestWindow(), "fail", "Checkbox disabled");
        }
    }


    public void flushAllChileWindowsExceptMain() throws  GeneralLeanFtException{
        CloseWindowIfExist(or.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow(),  5);
        CloseWindowIfExist(or.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow(),  5);
    }

    public void MinimizeMainWindow() throws  GeneralLeanFtException{
        MinimiseWindowIfExist(or.tRiMTrainingResourceManagerSouthwestWindow());
    }

    public void MaximizeMainWindow() throws  GeneralLeanFtException{
        MaximizeWindowIfExist(or.tRiMTrainingResourceManagerSouthwestWindow());
    }


}


