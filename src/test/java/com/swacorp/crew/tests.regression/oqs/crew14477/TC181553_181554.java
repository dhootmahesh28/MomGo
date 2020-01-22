package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.tests.regression.oqs.crew14477.Wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */
public class TC181553_181554 extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181553_181554.class);
    Add_Crew_Member_In_OQS_Verify_In_Trim wrapper;
    OQSLoginPage oqsLoginPage;
    LoginPage_am trimLoginPage;
    HomePage oqsHomePage;
    TrimHomePageAM trimHomePageAM;



    public TC181553_181554(){
        wrapper =new  Add_Crew_Member_In_OQS_Verify_In_Trim();
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
        oqsHomePage = new HomePage();
    }

    @Test(priority=8, groups = {"181553", "14477", "000", "regression"}, dataProvider = "TC181553", dataProviderClass = TestDataProvider.class)
    public void TC181553_OQS_RK_TRiM_FO_End_an_FO_position_and_add_new_FO_position_to_the_CM_to_OQS_RK_and_verify_in_TRiM (String[] testData) throws Exception {
        setScenarioName("TC181553_TC10 OQS RK TRiM FO End an FO position and add new FO position to the CM to OQS RK and verify in TRiM");
        wrapper.AddNewPositionInOQSVerifyInTrim(testData, true);
        trimHomePageAM.MinimizeMainWindow();
    }

    @Test(priority=9, groups = {"181554", "14477", "000", "regression"}, dataProvider = "TC181554", dataProviderClass = TestDataProvider.class)
    public void TC181554_CREW_14499_TC10_OQS_RK_TRiM_FO_End_an_FO_position_and_add_new_FO_position_to_the_CM_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws Exception {
        setScenarioName("TC181554_TC11 OQS RK TRiM CA End a CA position and add new FO position to the CM to OQS RK and verify in TRiM");
        wrapper.AddNewPositionInOQSVerifyInTrim(testData, true);

        oqsHomePage.EditPosition();

        //Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(oqsHomePage.getEmployeeNumber(), true);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(false );

        trimHomePageAM.MinimizeMainWindow();


        oqsHomePage.addPosition();

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);


        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(oqsHomePage.getEmployeeNumber(), true);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(false );

        trimHomePageAM.MinimizeMainWindow();
    }
}