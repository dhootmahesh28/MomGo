package com.swacorp.crew.tests.regression.oqs;

import com.swacorp.crew.genericwrappers.editor.IEditor;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class CREW_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA_in_OQS_RK_and_Verify_In_TRiM extends TestManager {
    final Logger Logg = Logger.getLogger(CREW_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA_in_OQS_RK_and_Verify_In_TRiM.class);

    @Test(groups = {"144770", "smoke"})
    public void crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA_in_OQS_RK_and_Verify_In_TRiM(){
        Logg.info("Test execution started..");
        try {
            OQSLoginPage oqsLoginPage = new OQSLoginPage();
            oqsLoginPage.loginOQS();
            HomePage oqsHomePage = new HomePage();
            String empNum = oqsHomePage.searchCrew();

            DateUtil dateUtil = new DateUtil();
            String startDate = dateUtil.getCurrentDate();
            String classYear = dateUtil.getCurrentYear();

            oqsHomePage.addCrewMember("FIRST OFFICER - 737 All", startDate, classYear, "22", "1", "TRN",
                    empNum, "Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC");
        }catch(Exception e){
            e.printStackTrace();
        }
        Logg.info("Test execution finished..");

    }
}
