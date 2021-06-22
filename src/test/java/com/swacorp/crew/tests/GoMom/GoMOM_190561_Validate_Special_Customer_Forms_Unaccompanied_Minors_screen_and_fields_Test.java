package com.swacorp.crew.tests.GoMom;


import com.swacorp.crew.pages.GoMOM.GoMOM;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class GoMOM_190561_Validate_Special_Customer_Forms_Unaccompanied_Minors_screen_and_fields_Test extends TestManager {


    SwaLifeLogin swaLifeLogin;
    GoMOM goMOM;

    GoMOM_190561_Validate_Special_Customer_Forms_Unaccompanied_Minors_screen_and_fields_Test(){
        swaLifeLogin = new SwaLifeLogin();
        goMOM = new GoMOM();

    }
    @Test(groups = {"GoMO"}, dataProvider = "FO_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190561_GoMOM_Validate_Special_Customer_Forms_Unaccompanied_Minors_screen_and_fields_Test(String[] testData) {
        setScenarioName("TC0190561_GoMOM_190561_Validate_Special_Customer_Forms_Unaccompanied_Minors_screen_and_fields_Test");
        try {

            swaLifeLogin.loginSWALife("GoMOMUser");

            goMOM.navigateToGeneralInfo();
            goMOM.addGeneralInfo("Customer Service Manager","123","Albany, NY","111","Albuquerque, NM","Amarillo, TX","11:00");

            goMOM.navigateAndaddingUnaccompaniedMinorsNavigateContactInfo();//rename the function
            goMOM.contactInfoUMTestError();
            goMOM.testError();

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }









}





