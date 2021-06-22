package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.SwaLifeASAPReport;
import com.swacorp.crew.pages.asap.SwaLifeHome;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.pages.asap.SwaLifeManagerReport;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class IF_Manager_Flow_ERT_Decision_Rejected_Test extends TestManager {

    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;

    IF_Manager_Flow_ERT_Decision_Rejected_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
    }

    @Test(groups = {"IF"}, dataProvider = "IF_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190734_ASAP_Report_IF_Manager_Flow_ERT_Decision_Rejected(String[] testData) throws Exception{
        setScenarioName("TC0190734_ASAP_Report_IF_Manager_Flow_ERT_Decision_Rejected");
        try {
            swaLifeLogin.loginASAPIFUser();

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
            swaLifeASAPReport.addEventLocation(testData[23], testData[24], testData[25]);
            swaLifeASAPReport.addEventType(testData[26], testData[27], testData[28]);
            swaLifeASAPReport.addFactors("High Workload", "Inadequate Policies/Procedures");

            swaLifeASAPReport.addDetectionAndReaction("Passenger", "During Flight", "Evacuation");

            swaLifeASAPReport.addEventDescription(testData[33], testData[34]);
            swaLifeASAPReport.submitFinalReport();

            swaLifeLogin.restartbrowser();
            swaLifeLogin.loginASAPIFManager();
            swaLifeManagerReport.navigateTOASAPManagerRole();
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "READY_FOR_ERC", "");
            swaLifeManagerReport.navigateToERTTab("READY_FOR_ERC");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "SEND_TO_ERC", "");
            swaLifeManagerReport.navigateToERTTab("ERC_MEETING");
            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "POST_ERC", "Report rejected");
            swaLifeManagerReport.navigateToERTTab("POST_ERC");
            swaLifeManagerReport.selectTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber());
            swaLifeManagerReport.searchAndCheckTheStatusOfTheReport("", "Not Accepted");

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
