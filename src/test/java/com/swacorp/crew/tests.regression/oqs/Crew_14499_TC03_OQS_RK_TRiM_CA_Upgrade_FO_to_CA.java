package com.swacorp.crew.tests.regression.oqs;

import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.pages.trim.employee.EmployeePage;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.pages.trim.login.LoginPage;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x227377 on 08/25/2019.
 */
public class Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA extends TestManager {
    private final Logger LOGGER = Logger.getLogger(Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA.class);
    LoginPage_am trimLoginPage = null;

    Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(){
        trimLoginPage = new LoginPage_am();
    }

    @Test(groups = {"14477", "regression"}, dataProvider = "crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA", dataProviderClass = TestDataProvider.class)
    public void crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(String[] testData) {
            //CREW-14499-TC07-OQS RK-TRiM-Sim Instructor-Add a Sim Instructor to OQS RK and verify in TRiM.

        LOGGER.info("Starting TC >> Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA..");
        setScenarioName("Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA");
        String userName;
        String password;
        try {

            OQSLoginPage oqsLoginPage = new OQSLoginPage();
            oqsLoginPage.loginOQS();
            HomePage oqsHomePage = new HomePage();
            String empNum = oqsHomePage.searchCrew();
            LOGGER.info("EMP no: "+empNum);

            DateUtil dateUtil = new DateUtil();
            String startDate = dateUtil.getCurrentDate();
            String classYear = dateUtil.getCurrentYear();

            oqsHomePage.addCrewMember(testData[0], startDate, classYear, testData[1], testData[2], testData[3],
                    empNum, testData[4], testData[5], testData[6], testData[7], testData[8], testData[9], testData[10], testData[11], testData[12]);

            // Login to Trim
           /*  userName = dataProperties.getProperty("trimUserName");
            password = dataProperties.getProperty("trimUserPassword");
            trimLoginPage.loginTRiM(userName, password);
            */
            //Navigate to Find Employee



        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}