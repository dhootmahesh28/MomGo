package com.swacorp.crew.tests.fssr;

import com.swacorp.crew.pages.fssr.FssrCases;
import com.swacorp.crew.pages.fssr.FssrHome;
import com.swacorp.crew.pages.fssr.FssrLogin;
import com.swacorp.crew.pages.fssr.FssrReportInput;
import com.swacorp.crew.tests.dataprovider.FSSRTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class TC12347 extends TestManager {

    FssrHome fssrHome;
    FssrLogin fssrLogin;
    FssrReportInput fssrReportInput;
    FssrCases fssrCases;

    TC12347(){
        fssrHome = new FssrHome();
        fssrLogin = new FssrLogin();
        fssrReportInput = new FssrReportInput();
        fssrCases = new FssrCases();
    }


    @Test(groups = {"12345"}, dataProvider = "TC12347", dataProviderClass = FSSRTestDataProvider.class)
    public void TC12347_Crew_Submit_Hazmat_Report_Negative(String[] testData) throws Exception{
        setScenarioName("TC012346_Crew Submit Hazmat Report");
        try {
            fssrLogin.loginFSSR();

            fssrHome.verifyHomePageAppear();
            fssrHome.closeAllTabs();
            fssrHome.navigateToMenu(testData[0]);
/*
            fssrReportInput.selectTheReportType(testData[1], testData[2]);
            fssrReportInput.enterTheDiscoveryInfo(testData[3], testData[4], testData[5], testData[6], "");
            /*fssrReportInput.enterCustomerAndFlightInfo(testData[7], testData[8], testData[9], testData[10], testData[11], testData[12], testData[13], testData[14], testData[15], testData[16]);
            fssrReportInput.enterDiscoverySource(testData[17], testData[18]);
            fssrReportInput.enterHazardousMaterialInfo(testData[19], testData[20],testData[21], testData[22]);
            fssrReportInput.verifySuccessMessage();
            fssrReportInput.addAdditionalReport(false, false);
            fssrReportInput.verifyFinalGreetMessage();

            fssrHome.navigateToMenu(testData[23]);

            fssrCases.selectGivenCasesToView(testData[24]);
            String strCaseNumber = fssrCases.getCaseNumberBasedOnDiscoveryDateTime();
            fssrCases.openSubmittedCaseDetails(strCaseNumber);*/

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }
}
