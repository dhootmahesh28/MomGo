package com.swacorp.crew.tests.GoMom;


import com.swacorp.crew.pages.GoMOM.GoMOM;
import com.swacorp.crew.pages.asap.SwaLifeLogin;
import com.swacorp.crew.tests.dataprovider.ASAPTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class GoMom_190552_Validate_Deleting_Incomplete_GoMom_Report_And_Delete_Button_Test extends TestManager {


    SwaLifeLogin swaLifeLogin;
    GoMOM goMOM;

    GoMom_190552_Validate_Deleting_Incomplete_GoMom_Report_And_Delete_Button_Test(){
        swaLifeLogin = new SwaLifeLogin();
        goMOM = new GoMOM();

}
    @Test(groups = {"GoMO"}, dataProvider = "FO_ASAP_REPORT", dataProviderClass = ASAPTestDataProvider.class)
    public void TC0190552_Validate_Deleting_Incomplete_GoMom_Report_And_Delete_Button_Test(String[] testData) {
        setScenarioName("TC0190552_Validate_Deleting_Incomplete_GoMom_Report_And_Delete_Button");
        try {

            swaLifeLogin.loginSWALifeGoMOM();
            goMOM.navigateToGeneralInfo();
            goMOM.addGeneralInfo("Customer Service Manager","123","Albany, NY","111","Albuquerque, NM","Amarillo, TX","11:00");
            goMOM.saveContinue();
            goMOM.verifyDataInIncompleteTable("","11:00",true);
            goMOM.deleteIncompleteRpt();
        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }









    }





