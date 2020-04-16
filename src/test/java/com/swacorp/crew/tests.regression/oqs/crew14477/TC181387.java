package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181387 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181387.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
/*    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;
*/
    TC181387(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
    }


    @Test(priority=1,groups = {"181387", "regression"}, dataProvider = "TC181387", dataProviderClass = TestDataProvider.class)
    public void TC181510_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws Exception {
        setScenarioName("TC181510_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM");
        wrapper.AddCrewmember(testData, true);
        wrapper.SelectTrainingEventCategory("Initial");
        wrapper.selectTrainingEvent("FLIGHT TRAINING");
        wrapper.deleteEvent("FLIGHT TRAINING");
        System.out.println("done");




        /*wrapper.SelectTrainingEventCategory("Additional");
        wrapper.SelectTrainingEventCategory("Upgrade");*/
        //wrapper.AddEvent("Initial");
        //wrapper.LoginToTrim();


    }
}