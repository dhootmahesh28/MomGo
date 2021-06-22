package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.*;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class MX_User_Edit_and_Submit_Incomplete_Report_Test extends TestManager {

    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeMXASAPReport swaLifeMXASAPReport;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;

    MX_User_Edit_and_Submit_Incomplete_Report_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeMXASAPReport = new SwaLifeMXASAPReport();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
    }

    @Test(groups = {"MX"}, dataProvider = "MX_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190738_Validate_MX_user_is_able_Edit_and_Submit_In_complete_report(String[] testData) throws Exception{
        setScenarioName("TC0190738_Validate MX_user is able Edit and Submit In complete report");
        try {
            String[] arrList = {"Event Information", "Event Type", "Supporting Information", "Event Description"};
            swaLifeLogin.loginASAPMXUser();


            swaLifeMXASAPReport.openASAPReportForm("");

            swaLifeMXASAPReport.addEmployeeInformation(testData[0], testData[1], testData[2], testData[3], testData[4]);
            swaLifeMXASAPReport.addWorkInformation(testData[5], testData[6], testData[7], testData[8], testData[9], testData[10]);
            swaLifeASAPReport.returnToASAPHomeNew();

            swaLifeHome.editIncompleteRecords("", "", "", "", "");
            swaLifeASAPReport.verifyPreviewReportEditOption(arrList);

            swaLifeASAPReport.navigateToTab(arrList[0]);

            swaLifeMXASAPReport.addEventInformation(testData[11], testData[12], testData[13], testData[14], testData[15], testData[16], testData[17], testData[18], testData[19], testData[20], testData[21]);
            swaLifeMXASAPReport.addEventType(testData[22], testData[23], testData[24]);
            swaLifeMXASAPReport.addSupportingInformation(testData[25], testData[26], testData[27], testData[28], testData[29], testData[30], testData[31]);
            swaLifeMXASAPReport.addEventDescription(testData[32], testData[33]);
            swaLifeMXASAPReport.submitFinalReport();

            swaLifeLogin.restartbrowser();
            swaLifeLogin.loginASAPMXManager();
            swaLifeManagerReport.navigateTOASAPManagerRole();
            swaLifeManagerReport.openSubmittedReports(swaLifeMXASAPReport.getStrTrackingNumber(), swaLifeMXASAPReport.getStrTableContent(), swaLifeMXASAPReport.getStrReportNumber());

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
