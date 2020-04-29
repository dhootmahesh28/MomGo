package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.test.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TC181387 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181387.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;

    TC181387(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
    }

    @Test(priority=1,groups = {"181387", "regression"}, dataProvider = "TC181387", dataProviderClass = TestDataProvider.class)
    public void TC181387(String[] testData) throws Exception {
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

        LOGGER.info("Calling AddCrewmember..");
        wrapper.AddCrewmember(testData, true, applyenterpriseMode);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        wrapper.ExpandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, true);

        wrapper.SelectTrainingEventCategory(oqsTrainingEventType);

        wrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);

        wrapper.SelectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        wrapper.ExpandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, true);

        wrapper.deleteEvent(event);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        wrapper.ExpandTreeNodeAndValidate(equipment+";"+domicile, partialNodetextTrimSchedTV, false);
    }
}