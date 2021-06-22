package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.*;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class MX_Manager_Flow_ERT_Decision_No_furthur_Action_Test extends TestManager {

    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeMXASAPReport swaLifeMXASAPReport;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;

    MX_Manager_Flow_ERT_Decision_No_furthur_Action_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeMXASAPReport = new SwaLifeMXASAPReport();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
    }

    @Test(groups = {"MXL"}, dataProvider = "MX_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190743_ASAP_Report_MX_Manager_Flow_ERT_Decision_Not_yet_reviewed_and_No_further_Action(String[] testData) throws Exception{
        setScenarioName("TC0190743_ASAP Report-MX Manager-Flow ERT Decision-Not yet reviewed and No further Action");
        try {

            swaLifeLogin.loginASAPMXUser();


            swaLifeMXASAPReport.openASAPReportForm("");

            swaLifeMXASAPReport.addEmployeeInformation(testData[0], testData[1], testData[2], testData[3], testData[4]);
            swaLifeMXASAPReport.addWorkInformation(testData[5], testData[6], testData[7], testData[8], testData[9], testData[10]);

            swaLifeMXASAPReport.addEventInformation(testData[11], testData[12], testData[13], testData[14], testData[15], testData[16], testData[17], testData[18], testData[19], testData[20], testData[21]);
            swaLifeMXASAPReport.addEventType(testData[22], testData[23], testData[24]);
            swaLifeMXASAPReport.addSupportingInformation(testData[25], testData[26], testData[27], testData[28], testData[29], testData[30], testData[31]);
            swaLifeMXASAPReport.addEventDescription(testData[32], testData[33]);
            swaLifeMXASAPReport.submitFinalReport();

            swaLifeLogin.restartbrowser();
            swaLifeLogin.loginASAPMXManager();
            swaLifeManagerReport.navigateTOASAPManagerRole();
            swaLifeManagerReport.openSubmittedReports(swaLifeMXASAPReport.getStrTrackingNumber(), swaLifeMXASAPReport.getStrTableContent(), swaLifeMXASAPReport.getStrReportNumber());
            swaLifeMXASAPReport.setStrTrackingNumber(swaLifeManagerReport.getStrTrackingNumber());
            swaLifeManagerReport.verifyButtonsOnManagerReport(swaLifeMXASAPReport);
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeMXASAPReport.getStrTrackingNumber(), "PENDING_ERC", "");
            swaLifeManagerReport.navigateToERTTab("PENDING_ERC");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeMXASAPReport.getStrTrackingNumber(), "ERC_MEETING", "");
            swaLifeManagerReport.navigateToERTTab("ERC_MEETING");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeMXASAPReport.getStrTrackingNumber(), "POST_ERC", "Not yet reviewed");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeMXASAPReport.getStrTrackingNumber(), "POST_ERC", "No Further Action");
            swaLifeManagerReport.navigateToERTTab("POST_ERC");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeMXASAPReport.getStrTrackingNumber(), "CLOSE", "");
            swaLifeManagerReport.searchAndCheckTheStatusOfTheReport("", "Closed");

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
