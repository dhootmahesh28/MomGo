package com.swacorp.crew.tests.oqs;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.tests.wrappers.OqsWrapper;
import com.swacorp.crew.tests.wrappers.TrimWrapper;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181465 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181465.class);
    OqsWrapper oqsWrapper;
    TrimWrapper trimWrapper;
    TC181465(){
        oqsWrapper = new OqsWrapper();
        trimWrapper = new TrimWrapper();
    }


    @Test(priority=1,groups = {"181465", "regression"}, dataProvider = "TC181465", dataProviderClass = TestDataProvider.class)
    public void tc181506(String[] testData) throws GeneralLeanFtException, InterruptedException {
        setScenarioName("TC181506_OQS_RK-TRiM-FO-Add_Flight_Training_event_under_Initial_to_an_FO");
        boolean applyenterpriseMode = true;
        String domicile = testData[3];
        String firstName = testData[4];
        String schdPlannerDropdownValue = testData[16];
        String oqsTrainingEventType = testData[17];
        String event = testData[18];
        String partialNodetextTrimSchedTV = testData[19];
        String schdPlannerRandomDropdownValueToRefreshTVData = testData[20];
        String equipment = testData[21]; //737
        String primaryStatusTrimEmpDetailsWnd =  testData[22];

        LOGGER.info("Starting execution..");
        oqsWrapper.addCrewmember(testData, true, applyenterpriseMode);
        oqsWrapper.editPositionToCreateCA();
        oqsWrapper.addPosition("CAPTAIN-737 All");
        oqsWrapper.selectTrainingEventCategory(oqsTrainingEventType);
        oqsWrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV,firstName, true);
        trimWrapper.selectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);
        oqsWrapper.deleteEvent(event);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, firstName,false);
        LOGGER.info("Starting finished..");
    }
}