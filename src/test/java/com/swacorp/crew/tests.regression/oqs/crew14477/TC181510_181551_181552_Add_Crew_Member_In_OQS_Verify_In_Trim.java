package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */
public class TC181510_181551_181552_Add_Crew_Member_In_OQS_Verify_In_Trim extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181510_181551_181552_Add_Crew_Member_In_OQS_Verify_In_Trim.class);
    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;

    TC181510_181551_181552_Add_Crew_Member_In_OQS_Verify_In_Trim(){
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
        oqsHomePage = new HomePage();
    }

    @Test(groups = {"181510", "14477", "regression"}, dataProvider = "TC181510_CREW_14499_TC07", dataProviderClass = TestDataProvider.class)
    public void TC181510_CREW_14499_TC07_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181510_CREW_14499_TC07_OQS_RK_TRiM_Sim_Instructor_Add_a_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM");
        WrapperMethod(testData, false);
    }

    @Test(groups = {"181551", "14477", "regression"}, dataProvider = "TC181551_CREW_14499_TC08", dataProviderClass = TestDataProvider.class)
    public void TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_a_FAA_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_a_FAA_to_OQS_RK_and_verify_in_TRiM");
        WrapperMethod(testData, false);
    }

    @Test(groups = {"181552", "14477", "regression"}, dataProvider = "TC181552_CREW_14499_TC09", dataProviderClass = TestDataProvider.class)
    public void TC181552_CREW_14499_TC09_OQS_RK_TRiM_APM_Add_a_APM_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181552_CREW_14499_TC09_OQS_RK_TRiM_APM_Add_a_APM_to_OQS_RK_and_verify_in_TRiM");
        WrapperMethod(testData, false);
    }

    private void WrapperMethod(String[] testData, boolean visibility) throws GeneralLeanFtException{
        //Login to OQS
        oqsLoginPage.loginOQS();

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData);

        //Store employee number in global hashmap
        runtimeData.RunTimeDataFromApp.put("CrewEmployeeNumber" , empNr);

        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(true);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.VerifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(runtimeData.RunTimeDataFromApp.get("CrewEmployeeNumber"));

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );
    }
}