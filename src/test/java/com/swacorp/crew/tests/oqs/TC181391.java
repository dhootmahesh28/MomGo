package com.swacorp.crew.tests.oqs;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.tests.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181391 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181391.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
/*    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;s
*/
    TC181391(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
    }

    @Test(priority=6, groups = {"181391", "14477", "regression"}, dataProvider = "TC181391", dataProviderClass = TestDataProvider.class)
    public void TC181391_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException, Exception {
        setScenarioName("TC181391_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM");
        wrapper.wrapperMethod(testData, false, false, false);
        wrapper.editPositionToCreateCA();
        wrapper.wrapperMethodToAddDuplicateEmployeeNrOQS(testData, false, false);

    }
}