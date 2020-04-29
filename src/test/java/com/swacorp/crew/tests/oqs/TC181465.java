package com.swacorp.crew.tests.oqs;

import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.test.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181465 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181465.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
/*    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;
*/
    TC181465(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
    }


    @Test(priority=1,groups = {"181465", "regression"}, dataProvider = "TC181465", dataProviderClass = TestDataProvider.class)
    public void TC181506(String[] testData) throws Exception {
        setScenarioName("TC181506_OQS_RK-TRiM-FO-Add_Flight_Training_event_under_Initial_to_an_FO");
        boolean applyenterpriseMode = true;
        String domicile = testData[3];
        String schdPlannerDropdownValue = testData[16];
        String oqsTrainingEventType = testData[17];
        String event = testData[18];
        String partialNodetextTrimSchedTV = testData[19];
        String schdPlannerRandomDropdownValueToRefreshTVData = testData[20];
        String equipment = testData[21]; //737
        String primaryStatusTrimEmpDetailsWnd =  testData[22];

        wrapper.AddCrewmember(testData, true, applyenterpriseMode);

        wrapper.EditPositionToCreateCA("CAPTAIN-737 All");
        wrapper.AddPosition("CAPTAIN-737 All");

        wrapper.SelectTrainingEventCategory(oqsTrainingEventType);

        //  NOT NEEDED wrapper.selectTrainingEvent(oqsTrainingEventType, event);
        wrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        wrapper.ExpandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, true);

        wrapper.SelectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);
        //wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        //wrapper.ExpandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, true);

        wrapper.deleteEvent(event);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);

        wrapper.SelectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        wrapper.ExpandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, false);
    }
}