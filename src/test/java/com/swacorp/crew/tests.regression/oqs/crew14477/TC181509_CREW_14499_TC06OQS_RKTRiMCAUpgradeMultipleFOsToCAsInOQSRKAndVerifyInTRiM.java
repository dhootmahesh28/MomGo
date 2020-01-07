package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportStatus;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by x257093 on 03-Jan-2020.
 */
public class TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM.class);
    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;

    TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM(){
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
    }

    @Test(groups = {"14477", "regression"}, dataProvider = "TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM", dataProviderClass = TestDataProvider.class)
    public void TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM");

        //Login to OQS
        oqsLoginPage.loginOQS();

        //Verify that the login to OQS is successful
        oqsLoginPage.VerifyLoginSuccessful(true);
        HomePage oqsHomePage = new HomePage();

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
        trimHomePageAM.ValidateSearchResults(false );

    }

    @Test(groups = {"14477", "regression"}, dataProvider = "TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_A_FAA_To_OQS_RK_And_Verify_In_TRiM", dataProviderClass = TestDataProvider.class)
    public void TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_A_FAA_To_OQS_RK_And_Verify_In_TRiM(String[] testData) throws GeneralLeanFtException {
        setScenarioName("TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_A_FAA_To_OQS_RK_And_Verify_In_TRiM");


        //Login to OQS
        oqsLoginPage.loginOQS();

        //Verify that the login to OQS is successful
        oqsLoginPage.VerifyLoginSuccessful(true);
        HomePage oqsHomePage = new HomePage();

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
        trimHomePageAM.ValidateSearchResults(false );
    }
}