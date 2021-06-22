package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.SwaLifeASAPReport;
import com.swacorp.crew.pages.asap.SwaLifeHome;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.pages.asap.SwaLifeManagerReport;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class FO_Manager_Flow_ERT_Decision_Hold_Test extends TestManager {

    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;

    FO_Manager_Flow_ERT_Decision_Hold_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
    }

    @Test(groups = {"FOL"}, dataProvider = "FO_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190709_ASAP_Report_FO_Manager_Flow_ERT_Decision_Hold(String[] testData) throws Exception{
        setScenarioName("TC0190709_ASAP Report-FO Manager-Flow ERT Decision-Hold");
        try {
            swaLifeLogin.loginASAPFoUser();

            swaLifeASAPReport.setStrReportType("NO_EXISTING_FLIGHT");
            swaLifeHome.setFltNum(testData[4]);
            swaLifeHome.setFltDeptSt(testData[5]);
            swaLifeHome.setFltArriveSt(testData[6]);
            swaLifeHome.setAlternateSt(testData[7]);
            swaLifeHome.setFltEqType(testData[8]);
            swaLifeHome.setFltTailNum(testData[9]);
            swaLifeHome.setFltDate(testData[10]);


            swaLifeHome.openASAPReportForm(swaLifeASAPReport.getStrReportType());

            swaLifeASAPReport.addEmployeeInformation(testData[0], testData[1]);
            swaLifeASAPReport.addContactInformation(testData[2], testData[3]);
            swaLifeASAPReport.addFlightInfo(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(), swaLifeHome.getAlternateSt(), swaLifeHome.getFltEqType(), swaLifeHome.getFltTailNum(), swaLifeHome.getFltDate());

            swaLifeASAPReport.addEventInfo(testData[11], testData[12], testData[13], testData[14], testData[15], testData[16]);
            swaLifeASAPReport.addFlightExp(testData[17], testData[18], testData[19], testData[20], testData[21], testData[22]);
            swaLifeASAPReport.addEventLocation(testData[23], testData[24], testData[25]);
            swaLifeASAPReport.addEventType(testData[26], testData[27], testData[28]);
            swaLifeASAPReport.addThreatsInfo(testData[29], testData[30]);
            swaLifeASAPReport.addFlightCrewErrors(testData[31], testData[32]);
            swaLifeASAPReport.addEventDescription(testData[33], testData[34]);
            swaLifeASAPReport.submitFinalReport();

            swaLifeLogin.restartbrowser();
            swaLifeLogin.loginASAPFOManager();

            swaLifeManagerReport.navigateTOASAPManagerRole();
            swaLifeManagerReport.openSubmittedReports(swaLifeASAPReport.getStrTrackingNumber(), swaLifeASAPReport.getStrTableContent(), "");
            swaLifeManagerReport.verifyButtonsOnManagerReport(swaLifeASAPReport, swaLifeHome);
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "PENDING_ERT", "");
            swaLifeManagerReport.navigateToERTTab("PENDING_ERT");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "ERT_MEETING", "");
            swaLifeManagerReport.navigateToERTTab("ERT_MEETING");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "POST_ERT", "Not yet reviewed");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "POST_ERT", "No Further Action");
            swaLifeManagerReport.navigateToERTTab("POST_ERT");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "CLOSE", "");
            swaLifeManagerReport.searchAndCheckTheStatusOfTheReport("", "Closed");

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
