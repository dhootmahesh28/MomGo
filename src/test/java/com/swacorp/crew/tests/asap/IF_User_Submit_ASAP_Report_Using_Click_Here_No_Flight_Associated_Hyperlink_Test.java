package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.SwaLifeASAPReport;
import com.swacorp.crew.pages.asap.SwaLifeHome;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.pages.asap.SwaLifeManagerReport;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class IF_User_Submit_ASAP_Report_Using_Click_Here_No_Flight_Associated_Hyperlink_Test extends TestManager {

    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;

    IF_User_Submit_ASAP_Report_Using_Click_Here_No_Flight_Associated_Hyperlink_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
    }

    @Test(groups = {"IF"}, dataProvider = "IF_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190718_Validate_IF_user_is_able_to_create_and_submit_report_by_clicking_on_Click_Here_hyperlink_againsts_If_you_would_like_to_create_a_report_not_associated_with_a_flight(String[] testData) throws Exception{
        setScenarioName("TC0190718_Validate IF user is able to create and submit report by clicking on Click Here hyperlink againsts If you would like to create a report not associated with a flight");
        try {
            swaLifeLogin.loginASAPIFUser();

            swaLifeASAPReport.setStrReportType("NO_FLIGHT");
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
