package com.swacorp.crew.tests.asap;

import com.swacorp.crew.pages.asap.*;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class DP_User_Able_Delete_Incomplete_Report_Test extends TestManager {
    SwaLifeHome swaLifeHome;
    SwaLifeLogin swaLifeLogin;
    SwaLifeDPASAPReport swaLifeDPASAPReport;
    SwaLifeASAPReport swaLifeASAPReport;
    SwaLifeManagerReport swaLifeManagerReport;
    SwaLifeMXASAPReport swaLifeMXASAPReport;

    DP_User_Able_Delete_Incomplete_Report_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        swaLifeDPASAPReport = new SwaLifeDPASAPReport();
        swaLifeASAPReport = new SwaLifeASAPReport();
        swaLifeManagerReport = new SwaLifeManagerReport();
        swaLifeMXASAPReport = new SwaLifeMXASAPReport();
    }


    @Test(groups = {"16"}, dataProvider = "DP_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190668_DP_User_Able_Delete_Incomplete_Report_Test(String[] testData) throws Exception{
        setScenarioName("TC0190668_DP_User_Able_Delete_Incomplete_Report");
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




            swaLifeDPASAPReport.selectFlightSpecificType(swaLifeDPASAPReport.getStrIsFlightSpecific());


            swaLifeDPASAPReport.addContactInfo(testData[0],testData[1],testData[2]);
            swaLifeASAPReport.addFlightInfo(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(), swaLifeHome.getAlternateSt(), swaLifeHome.getFltEqType(), swaLifeHome.getFltTailNum(), swaLifeHome.getFltDate());

            swaLifeASAPReport.returnToASAPHomeNew();


            swaLifeHome.verifyDataInIncompleteTable(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(), swaLifeHome.getFltDate(),swaLifeHome.getFltTailNum(), true);
            swaLifeHome.deleteRowFromIncompleteTable(swaLifeHome.getFltNum(), swaLifeHome.getFltDeptSt(), swaLifeHome.getFltArriveSt(), swaLifeHome.getFltDate(),swaLifeHome.getFltTailNum());


            swaLifeHome.deleteRowFromIncompleteTable();


        } catch(Exception ex){
            LOGGER.error(ex);
        }
    }

}
