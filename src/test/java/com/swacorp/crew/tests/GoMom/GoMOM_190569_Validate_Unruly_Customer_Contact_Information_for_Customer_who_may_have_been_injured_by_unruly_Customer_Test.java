package com.swacorp.crew.tests.GoMom;


import com.swacorp.crew.pages.GoMOM.GoMOM;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class GoMOM_190569_Validate_Unruly_Customer_Contact_Information_for_Customer_who_may_have_been_injured_by_unruly_Customer_Test extends TestManager {


    SwaLifeLogin swaLifeLogin;
    GoMOM goMOM;

    GoMOM_190569_Validate_Unruly_Customer_Contact_Information_for_Customer_who_may_have_been_injured_by_unruly_Customer_Test(){
        swaLifeLogin = new SwaLifeLogin();
        goMOM = new GoMOM();

    }
    @Test(groups = {"GoMO"}, dataProvider = "FO_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190569_Validate_Unruly_Customer_Contact_Information_for_Customer_who_may_have_been_injured_by_unruly_Customer_Test(String[] testData) {
        setScenarioName("TC0190569_Validate_Unruly_Customer_Contact_Information_for_Customer_who_may_have_been_injured_by_unruly_Customer");
        try {

            swaLifeLogin.loginSWALife("GoMOMUser");

            goMOM.navigateToGeneralInfo();
            goMOM.addGeneralInfo("Customer Service Manager","123","Albany, NY","111","Albuquerque, NM","Amarillo, TX","11:00");
            goMOM.navigateUnrulyAdditionalContact();
            goMOM.testError();
            goMOM.contactInfoCustomerInjuredBYunruly();
            goMOM.contactInfoCustomerHelpedrestrainTheunruly();

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }









}





