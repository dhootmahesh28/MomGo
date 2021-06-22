package com.swacorp.crew.tests.GoMom;


import com.swacorp.crew.pages.GoMOM.GoMOM;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class GoMOM_190562_Validate_Special_Customer_Forms_Medical_Emergency_screen_and_fields_Test extends TestManager {


    SwaLifeLogin swaLifeLogin;
    GoMOM goMOM;

    GoMOM_190562_Validate_Special_Customer_Forms_Medical_Emergency_screen_and_fields_Test(){
        swaLifeLogin = new SwaLifeLogin();
        goMOM = new GoMOM();

    }
    @Test(groups = {"GoMO"}, dataProvider = "FO_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190562_Validate_Special_Customer_Forms_Medical_Emergency_screen_and_fields_Test(String[] testData) {
        setScenarioName("TC0190562_Validate_Special_Customer_Forms_Medical_Emergency_screen_and_fields_Test");
        try {

            swaLifeLogin.loginSWALife("GoMOMUser");

            goMOM.navigateToGeneralInfo();
            goMOM.addGeneralInfo("Customer Service Manager","123","Albany, NY","111","Albuquerque, NM","Amarillo, TX","11:00");


            goMOM.navigateAndaddingMENavigateContactInfo();

            goMOM.testError();

            goMOM.familyContactInfoME();

            goMOM.contactInfoAssitedME();

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }



}














