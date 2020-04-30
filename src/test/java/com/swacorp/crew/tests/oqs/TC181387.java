package com.swacorp.crew.tests.oqs;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.test.wrappers.OqsWrapper;
import com.swacorp.crew.test.wrappers.TrimWrapper;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.test.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TC181387 extends TestManager {
    private final Logger loggTc = Logger.getLogger(TC181387.class);
    OqsWrapper oqsWrapper;
    TrimWrapper trimWrapper;
    TC181387(){
        oqsWrapper = new OqsWrapper();
        trimWrapper = new TrimWrapper();
    }

    @Test(priority=1,groups = {"181387", "regression"}, dataProvider = "TC181387", dataProviderClass = TestDataProvider.class)
    public void tc181387(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181387_OQS_RK-TRiM-FO-Add_Flight_Training_event_under_Initial_to_an_FO");
        boolean applyenterpriseMode = false;
        String domicile = testData[3];
        String schdPlannerDropdownValue = testData[16];
        String oqsTrainingEventType = testData[17];
        String event = testData[18];
        String partialNodetextTrimSchedTV = testData[19];
        String schdPlannerRandomDropdownValueToRefreshTVData = testData[20];
        String equipment = testData[21]; //737
        String primaryStatusTrimEmpDetailsWnd =  testData[22];

        loggTc.info("Starting the execution..");
        oqsWrapper.addCrewmember(testData, true, applyenterpriseMode);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, true);
        oqsWrapper.selectTrainingEventCategory(oqsTrainingEventType);
        oqsWrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);
        trimWrapper.selectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, true);
        oqsWrapper.deleteEvent(event);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, false);
    }
}