package com.swacorp.crew.pages.trim;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.ReportStatus;
import com.swacorp.crew.utils.ReportUtil;

import static com.swacorp.crew.pages.constants.ApplicationConstantsTrim.*;

public class TrimAutoPopulate extends WinBasePage {
    ReportUtil reportTrimAutoPopulate = new ReportUtil();
    ObjectRepoTRiM lftObjects = null;
    public TrimAutoPopulate() {
        lftObjects = super.trimObjectRepo;
    }
    public int autoPopulate(String equipment, String requirementName, String countCAs, String countFOs) throws GeneralLeanFtException {
        int retVal = 1;
        ReportStatus.reset();
        Window autoPopulateWindow = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow();
        ComboBox cboEquipComboBox = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().cboEquipComboBox();
        TabControl tabPopulateTabControl = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().tabPopulateTabControl();
        Button startButton = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().startButton();
        Window progressWindow = lftObjects.progressWindow();
        Label populateCompleteLabel = lftObjects.progressWindow().populateCompleteLabel();

        try {
            highlight(autoPopulateWindow);
            autoPopulateWindow.maximize();
            cboEquipComboBox.select(equipment);
            selectTemplate(requirementName);
            enterTextInCell(TBL_FIRST_ROW_INDEX, TRIM_TEMPLATE_COLUMN_CA, countCAs);
            enterTextInCell(TBL_FIRST_ROW_INDEX, TRIM_TEMPLATE_COLUMN_FO, countFOs);
            tabPopulateTabControl.select(TRIM_TAB_LAUNCH);
            startButton.click();
            if (populateCompleteLabel.exists(150)){
                reportTrimAutoPopulate.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Auto populate is complete.");
                progressWindow.close();
                retVal = 0;
            }else{
                reportTrimAutoPopulate.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Failed to complete the Auto Populate.");
            }
            autoPopulateWindow.close();
        }catch( Exception  e){
            loggerWinBasePage.error(e);
            reportTrimAutoPopulate.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while performing Auto Populate on Auto Populate window of Trim pplication.");
        }
        return retVal;
    }

    public void selectTemplate(String template){
        Table templateDataGridTable = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().templateDataGridTable();
        ComboBox swfComboBox = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().templateDataGridTable().swfComboBox();
        try {
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(0, "Template");
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(0, "Template");
            swfComboBox.select(template);
        }catch(Exception  e){
            loggerWinBasePage.error(e);
            reportTrimAutoPopulate.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while selecting the Template.");
        }
    }

    public void enterTextInCell(Integer row, String colName, String text){
        Table templateDataGridTable = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().templateDataGridTable();
        EditField swfEditEditField = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().autoPopulateWindow().templateDataGridTable().swfEditEditField();
        try {
            templateDataGridTable.getCustomGrid().getXtraGrid().selectCell(row, colName);
            templateDataGridTable.getCustomGrid().getXtraGrid().activateCell(row, colName);
            swfEditEditField.setText(text);
        }catch(Exception  e){
            loggerWinBasePage.error(e);
            reportTrimAutoPopulate.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while entering the text in table cell.");
        }
    }
}
