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
    MainObjectRepoTrim lftObjects =null;
    public HashMap<String, String> pgMap = new HashMap<>();
    private String empNumberFromApp = "";
    private String fldFirstNameFromApp = "";
    private boolean searchFound;

    public String getDynamicDataFromPage(String key){
        return pgMap.get(key);
    }


    //Window winFindEmployee = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
    public TrimHomePageAM()  {
        lftObjects = super.lftObjectRepo;
       }


    public void validateTreeNode() throws GeneralLeanFtException {
        try {
            TreeView x = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().tvTreeView();
            x.highlight();
            x.activateNode("737;MDW");
            List<TreeViewNode> n = x.getVisibleNodes();

            for (int i=0; i<= n.size()-1;i++){
                System.out.println(n.get(i).getClass());
            }
            x.activateNode("737;MDW;First Officer");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int NavigateMenu(String NavigationString) throws  GeneralLeanFtException{
        int returnInt = 1;
        ReportUtil report = new ReportUtil();
        Window mainWin = lftObjects.tRiMTrainingResourceManagerSouthwestWindow();
        UiObject topMenuStrip = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().mainMenuUiObject();
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

    public int SearchEmployeesDetails(String empNumber, boolean verifyActive) throws  GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        Window winFindEmployee = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField fldEmpSearch = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button btnShowEmpDetails = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeDetailsButton();
        Window winFindEmployeeSearch = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow();
        EditField fldEmpNumber = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtEmployeeNumberEditField();
        EditField fldFirstName = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().txtFirstNameEditField();

        try {
            //getDynamicData("EmployeeNumber");
            //System.out.println("getDynamicData EmployeeNumber" + getDynamicData("EmployeeNumber"));
            Highlight(winFindEmployee);
            setTextInEditBox(fldEmpSearch, empNumber);
            VerifyNoDuplicate();
            try {
                btnClick(btnShowEmpDetails);
                if(verifyActive) {
                    VerifyActive();
                }
            }catch(Exception e){
                report.report("Fail","Error occured while clicking on button. - btnShowEmpDetails");
            }

            if (Highlight(winFindEmployeeSearch)) {
                empNumberFromApp = fldEmpNumber.getText();
                fldFirstNameFromApp = fldFirstName.getText();

                pgMap.put("TRIM_EMP_NO", empNumberFromApp);
                pgMap.put("TRIM_EMP_FIRSTNAME", fldFirstNameFromApp);
                retVal = 0;
                winFindEmployeeSearch.close();
                searchFound = true;
            }else{
                searchFound = false;
                retVal = 1;
            }
            winFindEmployee.close();
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "info", "Search result after clicking on Search button.");
        }catch( Exception  e){
            e.printStackTrace();
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occured while searching the employee number on Find Employee window of Trim pplication.");
        }
        return retVal;
    }

    public void CloseApplication() throws  GeneralLeanFtException {
        flushObjects();
    }

   public void ValidateSearchResults(Map<String, String> runtimeData , String[] testData){
        if (empNumberFromApp.equalsIgnoreCase(runtimeData.get("CrewEmployeeNumber"))){
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Employee number matched.");
        }else{
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Employee number not matched.");
        }

        if (fldFirstNameFromApp.equalsIgnoreCase(testData[4])){
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Employee first name matched.");
        }else{
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Employee first name  not matched.");
        }
    }

    public void ValidateSearchResults(boolean status) throws GeneralLeanFtException{
        String visible = status ? "visible": "not visible";
        if ((status && searchFound) || (!status && !searchFound)){
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "CREW employee details "+visible+" on Trim.");
        }else{
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "CREW employee details not "+visible+" on Trim.");
        }
        flushAllChileWindowsExceptMain();
    }



    public void VerifyNoDuplicate()  throws GeneralLeanFtException{

        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        List<ListItem> lst = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().lstSearchListBox1().getItems();
        if (lst.size() < 2 ){ // less than 2 means no duplicate
                report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "No duplicate record is found");
        }else{
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Duplicate record found.");
        }
    }

    public void VerifyActive() throws GeneralLeanFtException{
        CheckedState state = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().activeCheckBox().getState();
        if (state.toString().equalsIgnoreCase("checked")){
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Checkbox enabled");
        }else{
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "fail", "Checkbox disabled");
        }
    }


    public void flushAllChileWindowsExceptMain() throws  GeneralLeanFtException{
        CloseWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow(),  5);
        CloseWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow(),  5);
    }

    public void MinimizeMainWindow() throws  GeneralLeanFtException{
        MinimiseWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow());
    }

    public void MaximizeMainWindow() throws  GeneralLeanFtException{
        MaximizeWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow());
    }


    public void closeEmployeeDetailsWindow() {

    }

    public void closeEmployeeSearchWindow() {
    }
}


