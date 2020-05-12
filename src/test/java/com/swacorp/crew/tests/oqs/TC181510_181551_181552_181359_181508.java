package com.swacorp.crew.tests.oqs;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.swacorp.crew.tests.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181510_181551_181552_181359_181508 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181510_181551_181552_181359_181508.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
/*    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;
*/
    TC181510_181551_181552_181359_181508(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
    }


    @Test(priority=1,groups = {"181510", "14477", "regression"}, dataProvider = "TC181510_CREW_14499_TC07", dataProviderClass = TestDataProvider.class)
    public void TC181510_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181510_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM");
        wrapper.wrapperMethod(testData, false, false, true);
    }

    @Test(priority=2, groups = {"181551", "14477", "regression"}, dataProvider = "TC181551_CREW_14499_TC08", dataProviderClass = TestDataProvider.class)
    public void TC181551_OQS_RK_TRiM_FAA_Add_a_FAA_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_a_FAA_to_OQS_RK_and_verify_in_TRiM");
        wrapper.wrapperMethod(testData, false, false, true);
    }

    @Test(priority=3, groups = {"181552", "14477", "regression"}, dataProvider = "TC181552_CREW_14499_TC09", dataProviderClass = TestDataProvider.class)
    public void TC181552_OQS_RK_TRiM_APM_Add_a_APM_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181552_OQS_RK_TRiM_APM_Add_a_APM_to_OQS_RK_and_verify_in_TRiM");
        wrapper.wrapperMethod(testData, false, false, true);
    }

    @Test(priority=4, groups = {"181359", "14477", "regression"}, dataProvider = "TC181359", dataProviderClass = TestDataProvider.class)
    public void TC181359_OQS_RK_TRiM_FO_Add_a_FO_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181359_OQS_RK_TRiM_FO_Add_a_FO_to_OQS_RK_and_verify_in_TRiM");
        wrapper.wrapperMethod(testData, true, false, true);
    }

    @Test(priority=5, groups = {"181508", "14477", "regression"}, dataProvider = "TC181508", dataProviderClass = TestDataProvider.class)
    public void TC181508_OQS_RK_TRiM_FO_Add_multiple_FOs_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181508_OQS_RK_TRiM_FO_Add_multiple_FOs_to_OQS_RK_and_verify_in_TRiM");
        wrapper.wrapperMethod(testData, true, false, true);
    }
}