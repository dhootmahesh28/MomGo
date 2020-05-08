package com.swacorp.crew.pages.trim;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.stdwin.Dialog;
import com.hp.lft.sdk.stdwin.Static;
import com.hp.lft.sdk.winforms.Button;
import com.hp.lft.sdk.winforms.ComboBox;
import com.hp.lft.sdk.winforms.Editor;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.ReportStatus;
import com.swacorp.crew.utils.ReportUtil;

public class TrimAddEmployeeRequirement extends WinBasePage {
    ReportUtil reportTrimEmpReq = new ReportUtil();
    ObjectRepoTRiM lftObjects = null;
    public TrimAddEmployeeRequirement() {
        lftObjects = super.trimObjectRepo;
    }
    public int addEmployeeRequirement(String empNumbers, String requirementName) throws GeneralLeanFtException {
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
            highlight(winAddEmpRequirement);
            txtEmployeeNumbersEditor.sendKeys(empNumbers);
            cboRequirementIDComboBox.select(requirementName);
            btnClick(addRequirementToDuePilotListButton);
            if (highlight(finishedDialog)) {
                if (verifyObjectExist(requirementSCreatedNoErrorsStatic, true)) {
                    if (requirementSCreatedNoErrorsStatic.getText().replace("\r\n", " ").equalsIgnoreCase(requirementsCreatedMsg)){
                        reportTrimEmpReq.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Pass", "Expected message displayed: " + requirementsCreatedMsg);
                        retVal = 0;
                    }else{
                        reportTrimEmpReq.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Failed to verify the requirements created message. Expected: "+ requirementsCreatedMsg +" Actual: "+ requirementSCreatedNoErrorsStatic.getText());
                    }
                }else{
                    reportTrimEmpReq.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail", "Expected message not displayed on Finished dialog: "+ requirementsCreatedMsg);
                }
                oKButton.click();
            }
        }catch( Exception  e){
            reportTrimEmpReq.reportLeanFT(lftObjects.tRiMTrainingResourceManagerSouthwestWindow(), "Fail","Error occurred while adding employee(s) to the requirement on Add Employee Requirement window of Trim pplication.");
            loggerWinBasePage.error(e);
        }
        return retVal;
    }
}
