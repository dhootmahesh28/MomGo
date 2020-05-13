package com.swacorp.crew.tests.oqs;

import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.tests.wrappers.OqsWrapper;
import com.swacorp.crew.tests.wrappers.TrimWrapper;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.tests.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

public class TC181390 extends TestManager {
    private final Logger loggTc = Logger.getLogger(TC181390.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;

    OqsWrapper oqsWrapper;
    TrimWrapper trimWrapper;
    TC181390(){
        oqsWrapper = new OqsWrapper();
        trimWrapper = new TrimWrapper();
    }

    @Test(priority=1,groups = {"181390", "regression"}, dataProvider = "TC181390", dataProviderClass = TestDataProvider.class)
    public void TC181387(String[] testData) throws Exception {
        setScenarioName("TC181390_OQS_RK-TRiM-FO-Add_Flight_Training_event_under_Initial_to_an_FO");

        boolean applyenterpriseMode = false;
        String domicile = testData[3];
        String firstName = testData[4];
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
        trimWrapper.expandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, firstName,true);
        oqsWrapper.selectTrainingEventCategory(oqsTrainingEventType);
        oqsWrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);
        trimWrapper.selectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV,firstName, true);
        oqsWrapper.deleteEvent(event);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV,firstName, false);
    }
}