package com.swacorp.crew.tests.oqs;

import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.tests.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.pages.constants.EnumWaitConstants;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181390 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181390.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
/*    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;
*/
    TC181390(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
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

        wrapper.addCrewmember(testData, true, applyenterpriseMode);

        //wrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        //wrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, false);
//
        wrapper.selectTrainingEventCategory(oqsTrainingEventType);

        wrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);

        //wrapper.selectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);
        //wrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        //wrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, true);

        wrapper.deleteEvent(event);
        Thread.sleep(EnumWaitConstants.WaitDuration.TEN.status());

        wrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);

        wrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);

        //wrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, firstName,false);
    }
}