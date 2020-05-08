package com.swacorp.crew.pages.trim;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.winforms.*;
import com.hp.lft.sdk.winforms.Button;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.ReportStatus;
import com.swacorp.crew.pages.constants.WaitConstants;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.swacorp.crew.pages.constants.ApplicationConstantsTrim;
import org.apache.log4j.Level;

public class TrimHome extends WinBasePage {
    ReportUtil reportTrimHome = new ReportUtil();
    ObjectRepoTRiM lftObjects = null;
    public static final Map<String, String> pgMap = new HashMap<>();
    private String empNumberFromApp = "";
    private String fldFirstNameFromApp = "";
    private boolean searchFound;

    public String getDynamicDataFromPage(String key) {
        return pgMap.get(key);
    }

    public void selectMenuFromBottomMenu(String menu, String drpValue) throws GeneralLeanFtException {
        try {
            navigateMenu(menu);
            selectValue(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), lftObjects.tRiMTrainingResourceManagerSouthwestWindow().cbSubFilterComboBox(), drpValue);
        }catch(GeneralLeanFtException e){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "fail", "Error occured while selecting dropdown value: "+drpValue);
            loggerWinBasePage.error(e);
        }catch(InterruptedException e) {
            loggerWinBasePage.log(Level.WARN, "Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }
    public TrimHome() {
        lftObjects = super.trimObjectRepo;
    }

    public void validateTreeNode(String empNr, String firstName, String node, String partialNode, boolean validation) throws GeneralLeanFtException {
        String is = "' is '";
        String nodeVisibility = "Visibility of the employeeID node '";
        String expectedVisibility = "' in the Tree view.Expected '";
        boolean found = false;
        boolean partialNodeFound = false;
        String tempNode="";
        TreeView schdPlannerTreeView = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().tvTreeView();
        try {
            if(schdPlannerTreeView.exists(WaitConstants.FIVE_SECOND_LFT)){
                schdPlannerTreeView.highlight();
            }
            Thread.sleep(5000);
            schdPlannerTreeView.activateNode(node.split(";")[0]);
            schdPlannerTreeView.activateNode(node);
            List<TreeViewNode> allVisibleNodes = schdPlannerTreeView.getVisibleNodes();
            for (int i = 0; i <= allVisibleNodes.size() - 1; i++) {
                tempNode = allVisibleNodes.get(i).getText();
                if (tempNode.contains(partialNode)) {
                    partialNodeFound = true;
                    schdPlannerTreeView.activateNode(node + ";" + tempNode);
                    String innerNode = empNr+ " "+firstName.toUpperCase()+",   (_"+ApplicationConstantsTrim.TRIM_PLACEHOLDER_INITIAL_AUTOMATION2+")  "+ApplicationConstantsTrim.TRIM_DUE_MONTH;
                    schdPlannerTreeView.activateNode(node + ";" + tempNode+";"+innerNode);
                    found = true;
                    reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "info", "Visibility for employeeID node '" + empNr + is + found + "' in the Tree view path : '" + node + ";" + tempNode+";"+innerNode+ "'");
                    schdPlannerTreeView.activateNode(node + ";" + tempNode+";"+innerNode);
                }
                if (partialNodeFound) {
                    break;
                }
            }
        } catch (Exception e) {
            loggerWinBasePage.error(e);
        }finally{
            if (found && validation) {
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "pass", nodeVisibility + empNr + is + found + expectedVisibility + validation + "'.");
            } else if ((!found) && (!validation)) {
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "pass", nodeVisibility+ empNr + is + found + expectedVisibility + validation + "'.");
            } else {
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "fail", nodeVisibility + empNr + is + found + expectedVisibility+ validation + "'.");
            }
            schdPlannerTreeView.activateNode(node + ";" + tempNode);
            schdPlannerTreeView.activateNode(node);
            schdPlannerTreeView.activateNode(node.split(";")[0]);
        }
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().minimize();
    }

    public int navigateMenu(String navigationString) throws GeneralLeanFtException, InterruptedException {
        int returnInt = 1;
        Window mainWin = lftObjects.tRiMTrainingResourceManagerSouthwestWindow();
        mainWin.activate();
        mainWin.maximize();

        Thread.sleep(2000);

        try {
            UiObject topMenuStrip = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().mainMenuUiObject();
            String[] arrMenuItems = navigationString.split("-->");
            topMenuStrip.highlight();
            for (String menuItem : arrMenuItems) {
                topMenuStrip.sendKeys(menuItem);
            }
            returnInt = 0;
            return returnInt;
        } catch (Exception e) {
            loggerWinBasePage.error(e);
        }
        if (returnInt <= 0) {
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "pass", "Menu navigation is successful: "+navigationString);
        } else {
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "fail", "Failed to navigate the menu.");
        }
        return returnInt;
    }

    public int searchEmployeesDetails(String empNumber, boolean verifyActive) throws  GeneralLeanFtException {
        int retVal = 1;
        String lastName;

        ReportStatus.reset();
        Window winFindEmployee = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField fldEmpSearch = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button btnShowEmpDetails = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeDetailsButton();
        Window winFindEmployeeSearch = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails();
        EditField fldEmpNumber = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails().txtEmployeeNumberEditField();
        EditField fldFirstName = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails().txtFNameEditField();
        EditField fldLastName =lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails().txtLNameEditField();

        try {
            highlight(winFindEmployee);
            setTextInEditBox(fldEmpSearch, empNumber);
            verifyNoDuplicate(empNumber);
            btnClick(btnShowEmpDetails);
            if(verifyActive) {
                verifyActive();
            }
            if (highlight(winFindEmployeeSearch)) {
                empNumberFromApp = fldEmpNumber.getText();
                fldFirstNameFromApp = fldFirstName.getText();
                lastName = fldLastName.getText();

                pgMap.put("TRIM_EMP_NO", empNumberFromApp);
                pgMap.put("TRIM_EMP_FIRSTNAME", fldFirstNameFromApp);
                retVal = 0;
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "info", "Search result after clicking on Search button. EmpNumber = "+empNumberFromApp+", FirstName = "+fldFirstNameFromApp+", LastName = "+lastName);
                winFindEmployeeSearch.close();
                searchFound = true;
            }else{
                searchFound = false;
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Failed to search employee number on Find Employee window.");
                retVal = 1;
            }
            winFindEmployee.activate();
            winFindEmployee.close();
        }catch( Exception  e){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occured in searching the employee number on Find Employee window of Trim pplication.");
            loggerWinBasePage.error(e);
        }
        return retVal;
    }

    public int verifyEmployeeSchedule(String empNumbers, String eventCode, String eventDate) throws  GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        Window findEmployeeWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField txtSearchEmpNumberEditField = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button showEmployeeScheduleButton = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeScheduleButton();
        Window instructorEmployeeScheduleWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().instructorEmployeeScheduleWindow();
        Table dgTable = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().instructorEmployeeScheduleWindow().dgTable();

        try {
            highlight(findEmployeeWindow);
            setTextInEditBox(txtSearchEmpNumberEditField, empNumbers);
            btnClick(showEmployeeScheduleButton);
            if (highlight(instructorEmployeeScheduleWindow)) {
                if (dgTable.getCustomGrid().getXtraGrid().getCell(0, eventDate).getValue().toString().equalsIgnoreCase(eventCode)) {
                    TableCell cell = dgTable.getCustomGrid().getXtraGrid().getCell(0, "5");
                    Location loc = new Location(Position.TOP_LEFT, new Point(cell.getX(), cell.getY()));
                    dgTable.mouseMove(loc);
                    Thread.sleep(5);
                    reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Training Event : "+ eventCode +" is scheduled on the date: "+ eventDate +" for Employee: "+ empNumbers);
                }else{
                    reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Training Event: "+ eventCode +" not found on the date: "+ eventDate +" for Employee: "+ empNumbers);
                }
                retVal = 0;
                instructorEmployeeScheduleWindow.close();
            }
            findEmployeeWindow.close();
        }catch( Exception  e){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while validating Employee Schedule on Find Employee Schedule window of Trim application.");
            loggerWinBasePage.error(e);
        }
        return retVal;
    }

   public void validateSearchResults(Map<String, String> runtimeData , String[] testData){
        if (empNumberFromApp.equalsIgnoreCase(runtimeData.get("CrewEmployeeNumber"))){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Employee number matched.");
        }else{
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Employee number not matched.");
        }

        if (fldFirstNameFromApp.equalsIgnoreCase(testData[4])){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Employee first name matched.");
        }else{
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Employee first name  not matched.");
        }
    }

    public void validateSearchResults(boolean status) throws GeneralLeanFtException{
        String visible = status ? "visible": "not visible";
        if ((status && searchFound) || (!status && !searchFound)){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "CREW employee details "+visible+" on Trim.");
        }else{
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "CREW employee details not "+visible+" on Trim.");
        }
        flushAllChileWindowsExceptMain();
    }



    public void verifyNoDuplicate(String empNumber)  throws GeneralLeanFtException{

        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        List<ListItem> lst = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().lstSearchListBox1().getItems();
        if (lst.size() < 2 ){ // less than 2 means no duplicate
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "No duplicate record is found on search employee window for the employee: "+empNumber);
        }else{
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Duplicate record found.");
        }
    }

    public void verifyActive() throws GeneralLeanFtException{
        CheckedState state = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow().activeCheckBox().getState();
        if (state.toString().equalsIgnoreCase("checked")){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Checkbox enabled");
        }else{
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "fail", "Checkbox disabled");
        }
    }


    public void flushAllChileWindowsExceptMain() throws  GeneralLeanFtException{
        closeWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow(),  5);
        closeWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow().employeeWindow(),  5);
    }

    public void minimizeMainWindow() throws  GeneralLeanFtException{
        minimiseWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow());
    }

    public void maximizeMainWindow() throws  GeneralLeanFtException{
        maximizeWindowIfExist(lftObjects.tRiMTrainingResourceManagerSouthwestWindow());
    }


    public void selectEquipmentAndPrimaryStatus(String empNumber, String equipments, String primaryStatus) {
        ComboBox cboEquipment = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails().cboPrimaryEquipIDComboBox();
        ComboBox cboStatus = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails().cboPrimaryCrewStatusIDComboBox();

        ReportStatus.reset();
        Window winFindEmployee = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
        EditField fldEmpSearch = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().txtSearchEmpNumberEditField();
        Button btnShowEmpDetails = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow().showEmployeeDetailsButton();
        Window winFindEmployeeSearch = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().EmployeeDetails();

        try {
            highlight(winFindEmployee);
            setTextInEditBox(fldEmpSearch, empNumber);
            btnClick(btnShowEmpDetails);

            if (highlight(winFindEmployeeSearch)) {
                selectValue(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(),cboEquipment, equipments);
                selectValue(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(),cboStatus, primaryStatus);
                winFindEmployeeSearch.close();
                searchFound = true;
            }else{
                searchFound = false;
                reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occured while searching the employee number on Find Employee window of Trim pplication.");
            }
            winFindEmployee.close();
        }catch( Exception  e){
            reportTrimHome.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occured while searching the employee number on Find Employee window of Trim pplication.");
            loggerWinBasePage.error(e);
        }
    }
}


