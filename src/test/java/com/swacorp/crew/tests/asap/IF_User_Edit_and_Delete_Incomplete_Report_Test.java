package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.SwaLifeASAPReport;
import com.swacorp.crew.pages.asap.SwaLifeHome;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.pages.asap.SwaLifeManagerReport;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class IF_User_Edit_and_Delete_Incomplete_Report_Test extends TestManager {

    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;

    IF_User_Edit_and_Delete_Incomplete_Report_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
    }

    @Test(groups = {"IF"}, dataProvider = "IF_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190714_Validate_IF_user_is_able_Edit_and_delete_In_complete_report(String[] testData) throws Exception{
        setScenarioName("TC0190714_Validate IF_user is able Edit and delete In complete report");
        try {

            String[] arrList = {"Event Information", "Event Type", "Event Description"};
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
            swaLifeASAPReport.returnToASAPHome();

            swaLifeHome.editIncompleteRecords(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(), swaLifeHome.getFltDate(), swaLifeHome.getFltTailNum());
            swaLifeASAPReport.verifyPreviewReportEditOption(arrList);

            swaLifeASAPReport.navigateToTab(arrList[0]);

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
            swaLifeManagerReport.openSubmittedReports(swaLifeASAPReport.getStrTrackingNumber(), swaLifeASAPReport.getStrTableContent(), "");


        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
