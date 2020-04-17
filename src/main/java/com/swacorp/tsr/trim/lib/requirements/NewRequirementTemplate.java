package com.swacorp.tsr.trim.lib.requirements;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;

public class NewRequirementTemplate  extends WinBasePage {

    ObjectRepoTRiM lftObjects =null;
    public NewRequirementTemplate(ObjectRepoTRiM leanftObjects){
        lftObjects = leanftObjects;
    }

    public void createNewRequirementTemplate(String[] testData) throws GeneralLeanFtException {
    System.out.println("createNewRequirementTemplate ...");
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().activate();
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().txtRequirementNameEditField().setText(testData[0]);
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().cboReqTypeIDComboBox().select(testData[1]);
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().cboDefaultCustomerComboBox().select(testData[2]);
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().txtCourseDefinitionDescriptionEditor().sendKeys(testData[3]);
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().cboEquipIDComboBox().select(testData[4]);
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().txtNormalNumberOfStudentsEditField().setText(testData[5]);
        lftObjects.tRiMTrainingResourceManagerSouthwestWindow().requirementsWindow().displaySeriesLabelCheckBox().setState(CheckedState.CHECKED);


    }
}
