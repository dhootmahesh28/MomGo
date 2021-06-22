package com.swacorp.crew.tests.fssr;

import com.swacorp.crew.pages.fssr.FssrCases;
import com.swacorp.crew.pages.fssr.FssrHome;
import com.swacorp.crew.pages.fssr.FssrLogin;
import com.swacorp.crew.pages.fssr.FssrReportInput;
import com.swacorp.crew.tests.dataprovider.FSSRTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class TC001 extends TestManager {

    FssrHome fssrHome;
    FssrLogin fssrLogin;
    FssrReportInput fssrReportInput;
    FssrCases fssrCases;

    TC001(){
        fssrHome = new FssrHome();
        fssrLogin = new FssrLogin();
        fssrReportInput = new FssrReportInput();
        fssrCases = new FssrCases();
    }

    @Test(groups = {"FSSR001"}, dataProvider = "TC12345", dataProviderClass = FSSRTestDataProvider.class)
    public void TC12345_Crew_Submit_Hazmat_Report_SOAR_Menu(String[] testData) throws Exception{
        setScenarioName("TC012345_Crew Submit Hazmat Report from SOAR Menu");
        try {
            fssrLogin.loginFSSR();

            fssrHome.verifyHomePageAppear();
            fssrHome.closeAllTabs();
            fssrHome.navigateToMenu(testData[0]);

            fssrReportInput.setStrFlightNum(testData[19]);

            fssrReportInput.selectTheReportType(testData[1], testData[2]);
            fssrReportInput.enterTheDiscoveryInfo(testData[3], testData[4], testData[5], testData[6], testData[7], testData[8], testData[9]);
            fssrReportInput.enterCustomerInfo(testData[10], testData[11], testData[12], testData[13], testData[14], testData[15], testData[16], testData[17], testData[18]);
            fssrReportInput.enterFlightInfo(fssrReportInput.getStrFlightNum(), testData[20],testData[21], testData[22]);
            fssrReportInput.enterHazardousMaterialInfo(testData[23], testData[24],testData[25], testData[26], testData[27], Integer.parseInt(testData[28]));
            fssrReportInput.saveOrSubmitReport(true, false);
            fssrReportInput.verifyFinalGreetMessage();

            fssrHome.navigateToMenu(testData[23]);

            fssrCases.checkStatusOfCaseNumber(fssrReportInput.getStrCaseNumber(), false);
            /*fssrCases.selectGivenCasesToView(testData[24]);
            String strCaseNumber = fssrCases.getCaseNumberBasedOnDiscoveryDateTime();
            fssrCases.openSubmittedCaseDetails(strCaseNumber);*/
        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
