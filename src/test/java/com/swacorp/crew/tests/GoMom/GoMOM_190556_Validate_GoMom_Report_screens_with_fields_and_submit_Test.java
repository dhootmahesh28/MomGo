package com.swacorp.crew.tests.GoMom;


import com.swacorp.crew.pages.GoMOM.GoMOM;
import com.swacorp.crew.pages.asap.SwaLifeHome;
import com.swacorp.crew.pages.asap.SwaLifeLogin;

import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class GoMOM_190556_Validate_GoMom_Report_screens_with_fields_and_submit_Test extends TestManager {


    SwaLifeLogin swaLifeLogin;
    SwaLifeHome swaLifeHome;
    GoMOM goMOM;

    GoMOM_190556_Validate_GoMom_Report_screens_with_fields_and_submit_Test(){
        swaLifeLogin = new SwaLifeLogin();
        swaLifeHome = new SwaLifeHome();
        goMOM = new GoMOM();

    }
    @Test(groups = {"190566"}, dataProvider = "FO_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190556_GoMOM_Validate_GoMom_Report_screens_with_fields_and_submit_Test(String[] testData) {
        setScenarioName("TC0190556_GoMOM_Validate_GoMom_Report_screens_with_fields_and_submit_Test");
        try {

            swaLifeLogin.loginSWALifeGoMOM();
            goMOM.navigateToGeneralInfo();
            goMOM.generalInfoErrorsValidation("Customer Service Manager","123","Albany, NY","111","Albuquerque, NM","Amarillo, TX","11:00");
            goMOM.momQuestionsScreen();
            goMOM.eventTypescreen();
            goMOM.disrutiontypescreen();
            goMOM.additionalinfoEventscreen();
            goMOM.specialcustFormscreen();
            goMOM.previewandSubmit();
            swaLifeLogin.restartbrowser();

            swaLifeLogin.loginGoMOMaDMIN();

            goMOM.searchGoMOMReport();
            goMOM.addStation("TST","TEST","TSS");
            goMOM.updateStation("TST","TEST","TESS");


        }
        catch(Exception ex){
            LOGGER.error(ex);

        }
    }









}





