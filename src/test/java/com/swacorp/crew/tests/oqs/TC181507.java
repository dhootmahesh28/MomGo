package com.swacorp.crew.tests.oqs;

import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.test.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181507 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181507.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
/*    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;s
*/
    TC181507(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
    }

    @Test(priority=7, groups = {"181507", "14477", "regression"}, dataProvider = "TC181391", dataProviderClass = TestDataProvider.class)
    public void TC181507_OQS_RK_TRiM_Attempt_to_add_an_existing_CA_to_OQS_RK_and_verify_in_TRiM(String[] data) throws Exception {
        setScenarioName("TC181507_TC04 OQS RK TRiM Attempt to add an existing CA to OQS RK and verify in TRiM");
        wrapper.wrapperMethod(data, false, false, false);

        wrapper.wrapperMethodToAddDuplicateEmployeeNrOQS(data, false, false);
    }
}