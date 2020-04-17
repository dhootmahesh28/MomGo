package com.swacorp.crew.pages.trim.homepage;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Location;
import com.hp.lft.sdk.Position;
import com.hp.lft.sdk.stdwin.Dialog;
import com.hp.lft.sdk.stdwin.Static;
import com.hp.lft.sdk.winforms.*;
import com.hp.lft.sdk.winforms.Button;
import com.hp.lft.sdk.winforms.Label;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.swacorp.crew.utils.ReportStatus;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrimHomePageAM extends WinBasePage{
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TrimHomePageAM.class);
    ObjectRepoTRiM lftObjects =null;
    public HashMap<String, String> pgMap = new HashMap<>();
    private String empNumberFromApp = "";
    private String fldFirstNameFromApp = "";
    private boolean searchFound;

    public String getDynamicDataFromPage(String key){
        return pgMap.get(key);
    }


    //Window winFindEmployee = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
    public TrimHomePageAM()  {
        lftObjects = super.trimObjectRepo;
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

    public int VerifyEmployeeSchedule(String empNumbers, String eventCode, String eventDate) throws  GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        Window findEmployeeWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField txtSearchEmpNumberEditField = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button showEmployeeScheduleButton = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeScheduleButton();
        Window instructorEmployeeScheduleWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().instructorEmployeeScheduleWindow();
        Table dgTable = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().instructorEmployeeScheduleWindow().dgTable();

        try {
            Highlight(findEmployeeWindow);
            setTextInEditBox(txtSearchEmpNumberEditField, empNumbers);
            btnClick(showEmployeeScheduleButton);
            if (Highlight(instructorEmployeeScheduleWindow)) {
                if (dgTable.getCustomGrid().getXtraGrid().getCell(0, eventDate).getValue().toString().equalsIgnoreCase(eventCode)) {
                    TableCell cell = dgTable.getCustomGrid().getXtraGrid().getCell(0, "5");
                    Location loc = new Location(Position.TOP_LEFT, new Point(cell.getX(), cell.getY()));
                    dgTable.mouseMove(loc);
                    Thread.sleep(5);
                    report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Training Event : "+ eventCode +" is scheduled on the date: "+ eventDate +" for Employee: "+ empNumbers);
                }else{
                    report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Training Event: "+ eventCode +" not found on the date: "+ eventDate +" for Employee: "+ empNumbers);
                }
                retVal = 0;
                instructorEmployeeScheduleWindow.close();
            }
            findEmployeeWindow.close();
        }catch( Exception  e){
            e.printStackTrace();
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while validating Employee Schedule on Find Employee Schedule window of Trim application.");
        }
        return retVal;
    }


    public int AddEmployeeRequirement(String empNumbers, String requirementName) throws  GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        String requirementsCreatedMsg = empNumbers.split(",").length + " Requirement(s) Created. No Errors.";
        Window winAddEmpRequirement = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().addEmployeeRequirementWindow();
        Editor txtEmployeeNumbersEditor = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().addEmployeeRequirementWindow().txtEmployeeNumbersEditor();
        ComboBox cboRequirementIDComboBox = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().addEmployeeRequirementWindow().cboRequirementIDComboBox();
        Button addRequirementToDuePilotListButton = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().addEmployeeRequirementWindow().addRequirementToDuePilotListButton();
        Dialog finishedDialog = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().finishedDialog();
        com.hp.lft.sdk.stdwin.Button oKButton = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().finishedDialog().oKButton();
        Static requirementSCreatedNoErrorsStatic = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().finishedDialog().requirementSCreatedNoErrorsStatic();

        try {
            Highlight(winAddEmpRequirement);
            txtEmployeeNumbersEditor.sendKeys(empNumbers);
            cboRequirementIDComboBox.select(requirementName);
            btnClick(addRequirementToDuePilotListButton);
            if (Highlight(finishedDialog)) {
                if (VerifyObjectExist(requirementSCreatedNoErrorsStatic, true)) {
                    if (requirementSCreatedNoErrorsStatic.getText().replace("\r\n", " ").equalsIgnoreCase(requirementsCreatedMsg)){
                        report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Expected message displayed: " + requirementsCreatedMsg);
                        retVal = 0;
                    }else{
                        report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Failed to verify the requirements created message. Expected: "+ requirementsCreatedMsg +" Actual: "+ requirementSCreatedNoErrorsStatic.getText());
                    }
                }else{
                    report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Expected message not displayed on Finished dialog: "+ requirementsCreatedMsg);
                }
                oKButton.click();
            }
        }catch( Exception  e){
            e.printStackTrace();
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while adding employee(s) to the requirement on Add Employee Requirement window of Trim pplication.");
        }
        return retVal;
    }

    public int AutoPopulate(String equipment, String requirementName) throws  GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        Window autoPopulateWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow();
        ComboBox cboEquipComboBox = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().cboEquipComboBox();
        Table templateDataGridTable = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().templateDataGridTable();
        ComboBox swfComboBox = templateDataGridTable.describe(ComboBox.class, new ComboBoxDescription.Builder()
                .fullType("System.Windows.Forms.DataGridViewComboBoxEditingControl")
                .objectName("").build());
        EditField swfEditEditField = templateDataGridTable.describe(EditField.class, new EditFieldDescription.Builder()
                .fullType("System.Windows.Forms.DataGridViewTextBoxEditingControl")
                .objectName("").build());
        TabControl tabPopulateTabControl = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().tabPopulateTabControl();
        Button startButton = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().startButton();
        Window progressWindow = lftObjects.progressWindow();
        Label populateCompleteLabel = lftObjects.progressWindow().populateCompleteLabel();

        try {
            Highlight(autoPopulateWindow);
            autoPopulateWindow.maximize();
            cboEquipComboBox.select(equipment);
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(0, "Template");
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(0, "Template");
            swfComboBox.select(requirementName);
            templateDataGridTable.getCustomGrid().getXtraGrid().selectCell(0, "# of CAs");
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(0, "# of CAs");
            swfEditEditField.setText("10");
            templateDataGridTable.getCustomGrid().getXtraGrid().selectCell(0, "# of FOs");
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(0, "# of FOs");
            swfEditEditField.setText("10");
            tabPopulateTabControl.select("Launch");
            startButton.click();
            if (populateCompleteLabel.exists(150)){
                report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Auto populate is complete.");
                progressWindow.close();
                retVal = 0;
            }else{
                report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Failed to complete the Auto Populate.");
            }
            autoPopulateWindow.close();
        }catch( Exception  e){
            e.printStackTrace();
            report.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while performing Auto Populate on Auto Populate window of Trim pplication.");
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


