package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.*;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class DP_User_able_to_Edit_and_Submit_Incomplete_report_Test extends TestManager {
    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeDPASAPReport swaLifeDPASAPReport;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;
    SwaLifeMXASAPReport swaLifeMXASAPReport;

    DP_User_able_to_Edit_and_Submit_Incomplete_report_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeDPASAPReport = new SwaLifeDPASAPReport();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
        swaLifeMXASAPReport = new SwaLifeMXASAPReport();
    }


    @Test(groups = {"16"}, dataProvider = "DP_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190667_DP_User_able_to_Edit_and_Submit_Incomplete_report_Test(String[] testData) throws Exception{
        setScenarioName("TC0190667_Validate DP_user is able Edit and Submit In complete report");
        try {

            String[] arrList = {"Event Information", "Event Type", "Event Description"};

            swaLifeLogin.loginASAPDPUser();

            swaLifeDPASAPReport.setStrIsFlightSpecific("yes");

            swaLifeHome.setFltNum(testData[3]);
            swaLifeHome.setFltTailNum(testData[8]);
            swaLifeHome.setFltDate(testData[9]);
            swaLifeHome.setFltDeptSt(testData[4]);
            swaLifeHome.setFltArriveSt(testData[5]);
            swaLifeHome.setAlternateSt(testData[6]);
            swaLifeHome.setFltEqType(testData[7]);


            swaLifeDPASAPReport.selectFlightSpecificType(swaLifeDPASAPReport.getStrIsFlightSpecific());


            swaLifeDPASAPReport.addContactInfo(testData[0],testData[1],testData[2]);
            swaLifeASAPReport.addFlightInfo(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(), swaLifeHome.getAlternateSt(), swaLifeHome.getFltEqType(), swaLifeHome.getFltTailNum(), swaLifeHome.getFltDate());

            swaLifeASAPReport.returnToASAPHomeNew();

            swaLifeHome.editIncompleteRecords(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(),  swaLifeHome.getFltDate(),swaLifeHome.getFltTailNum());
           swaLifeASAPReport.verifyPreviewReportEditOption(arrList);

            swaLifeASAPReport.navigateToTab(arrList[0]);

            swaLifeASAPReport.addEventInfo(testData[10],testData[11],testData[12],testData[13],testData[14], "");
            swaLifeDPASAPReport.addExperience(testData[15],testData[16],testData[17]);
            swaLifeASAPReport.addEventLocation(testData[18],testData[19],testData[20]);
            swaLifeASAPReport.addEventType(testData[21],testData[22],testData[23]);
            swaLifeASAPReport.addThreatsInfo(testData[24],testData[25]);
            swaLifeASAPReport.addFlightCrewErrors(testData[26],testData[27]);
            swaLifeASAPReport.addEventDescription(testData[28],testData[29]);
            swaLifeASAPReport.submitFinalReport();

            swaLifeLogin.restartbrowser();
            swaLifeLogin.loginASAPDPManager();
            swaLifeManagerReport.navigateTOASAPManagerRole();
            swaLifeManagerReport.openSubmittedReports(swaLifeASAPReport.getStrTrackingNumber(), swaLifeASAPReport.getStrTableContent(), "");

        } catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
