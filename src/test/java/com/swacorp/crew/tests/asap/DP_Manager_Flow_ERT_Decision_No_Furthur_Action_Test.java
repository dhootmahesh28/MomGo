package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.*;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class DP_Manager_Flow_ERT_Decision_No_Furthur_Action_Test extends TestManager {
    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeDPASAPReport swaLifeDPASAPReport;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;
    SwaLifeMXASAPReport swaLifeMXASAPReport;

    DP_Manager_Flow_ERT_Decision_No_Furthur_Action_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeDPASAPReport = new SwaLifeDPASAPReport();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
        swaLifeMXASAPReport = new SwaLifeMXASAPReport();
    }


    @Test(groups = {"16"}, dataProvider = "DP_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)

    public void TC0190672_DP_Manager_Flow_ERT_Decision_No_Furthur_Action_Test(String[] testData) throws Exception{
        setScenarioName("TC0190672_DP_Manager_Flow_ERT_Decision_No_Furthur_Action_Test");
        try {
            swaLifeLogin.loginASAPDPUser();

            swaLifeDPASAPReport.setStrIsFlightSpecific("Yes");

            swaLifeHome.setFltNum(testData[3]);
            swaLifeHome.setFltTailNum(testData[8]);
            swaLifeHome.setFltDate(testData[9]);
            swaLifeHome.setFltDeptSt(testData[4]);
            swaLifeHome.setFltArriveSt(testData[5]);
            swaLifeHome.setAlternateSt(testData[6]);
            swaLifeHome.setFltEqType(testData[7]);


            swaLifeMXASAPReport.verifyMangerAndHelpButton();

            swaLifeDPASAPReport.selectFlightSpecificType(swaLifeDPASAPReport.getStrIsFlightSpecific());
            swaLifeDPASAPReport.addContactInfo(testData[0],testData[1],testData[2]);
            swaLifeASAPReport.addFlightInfo(swaLifeHome.getFltNum(),swaLifeHome.getFltDeptSt(),swaLifeHome.getFltArriveSt(), swaLifeHome.getAlternateSt(), swaLifeHome.getFltEqType(), swaLifeHome.getFltTailNum(), swaLifeHome.getFltDate());
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




            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "PENDING_ERC", "");
            swaLifeManagerReport.navigateToERTTab("PENDING_ERC");

            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "ERC_MEETING", "");
            swaLifeManagerReport.navigateToERTTab("ERC_MEETING");

            swaLifeManagerReport.changeTheStatusOfTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber(), "POST_ERC", "No Further Action");
            swaLifeManagerReport.navigateToERTTab("POST_ERC");
            swaLifeManagerReport.selectTheSubmittedReport(swaLifeASAPReport.getStrTrackingNumber());
            swaLifeManagerReport.searchAndCheckTheStatusOfTheReport("", "No Further Action");







        } catch(Exception ex){
            LOGGER.error(ex);
        }












    }}
