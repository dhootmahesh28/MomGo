package com.swacorp.crew.tests.regression.oqs;

import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.common.Constant;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by x227377 on 08/25/2019.
 */
public class Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA extends TestManager {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA.class);
    LoginPage_am trimLoginPage = null;
    Constant commonVars = null;
    Map<String, String> hmAppData = null;
    TrimHomePageAM trimHomePageAM = null;
    //TRiMHomePage trimHomePage = null;


    Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(){
        trimLoginPage = new LoginPage_am();
      //  trimHomePage = new TRiMHomePage();
        hmAppData =new HashMap<String, String>();
        trimHomePageAM = new TrimHomePageAM();
    }

    @Test(groups = {"11987654447700", "regression"}, dataProvider = "crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA", dataProviderClass = TestDataProvider.class)
    public void crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(String[] testData) {
            //CREW-14499-TC07-OQS RK-TRiM-Sim Instructor-Add a Sim Instructor to OQS RK and verify in TRiM.
        setScenarioName("Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA");
        //ReportUtil report = new ReportUtil();
        LOGGER.info("Starting TC >> Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA..");

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

            System.out.println("sysout > EmpId >> "+empNum);
            hmAppData.put("EmpId", empNum);
            String emp = hmAppData.get("EmpId");
            System.out.println("sysout > EmpId >> "+emp);
            LOGGER.info("Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA.."+emp);


            //Login to Trim
            trimLoginPage.loginTRiM("admin", "admin123");
           // trimHomePage.NavigateMenu("ss");

            trimHomePageAM.NavigateMenu("^E-->^F");
            int retVal = trimHomePageAM.SearchEmployeesDetails(empNum);

            if((trimHomePageAM.pgMap.get("TRIM_EMP_NO").equalsIgnoreCase(empNum)) && (trimHomePageAM.pgMap.get("TRIM_EMP_FIRSTNAME").equalsIgnoreCase(testData[4]))) {
                report.report("pass", "OQS data is reflected on Trim");
            }else{
                report.report("fail", "OQS data is not reflected on Trim");
        }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}