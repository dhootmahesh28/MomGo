package com.swacorp.crew.tests.regression.oqs;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.common.Constant;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import com.swacorp.crew.utils.ReportStatus;
/**
 * Created by x227377 on 08/25/2019.
 */
public class TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA extends TestManager {
    
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA.class);
    LoginPage_am trimLoginPage = null;
    Constant commonVars = null;
    TrimHomePageAM trimHomePageAM = null;
    OQSLoginPage oqsLoginPage = null;
    Map<String, String> hmAppData = new HashMap<String, String>();

    TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(){
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
    }

    @Test(groups = {"414477", "regression"}, dataProvider = "TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA", dataProviderClass = TestDataProvider.class)
    public void crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(String[] testData) throws GeneralLeanFtException {

        setScenarioName("TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA");
        LOGGER.info("Starting TC >> TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA..");

        try {

            oqsLoginPage.loginOQS();
            HomePage oqsHomePage = new HomePage();
            String empNum = oqsHomePage.searchCrew();
            LOGGER.info("EMP no: "+empNum);

            DateUtil dateUtil = new DateUtil();
            String startDate = dateUtil.getCurrentDate();
            String classYear = dateUtil.getCurrentYear();

            try {
               int x = oqsHomePage.addCrewMember(testData[0], startDate, classYear, testData[1], testData[2], testData[3],
                        empNum, testData[4], testData[5], testData[6], testData[7], testData[8], testData[9], testData[10], testData[11], testData[12]);
                if (x ==1){
                    throw new Exception();
                }
                hmAppData.put("EmpId", empNum);
                String emp = hmAppData.get("EmpId");
                LOGGER.info("Crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA.."+emp);


                //String empNum = "55669";
                //Login to Trim
                trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);


                trimHomePageAM.NavigateMenu("^E-->^F");
                try {
                    int retVal = trimHomePageAM.SearchEmployeesDetails(empNum);
                    System.out.println("retVal"+retVal);
                    if((trimHomePageAM.pgMap.get("TRIM_EMP_NO").equalsIgnoreCase(empNum)) && (trimHomePageAM.pgMap.get("TRIM_EMP_FIRSTNAME").equalsIgnoreCase(testData[4]))) {
                        report.report("pass", "OQS data is reflected on Trim the employee '"+empNum+"'");
                    }else{
                        report.report("fail", "OQS data is not reflected on Trim");
                    }
                }catch(Exception e){
                    report.report("FAIL", "Error occured while searchig for the employee '"+empNum+"'"+ReportStatus.getReportMsg());
                }
            }
            catch(Exception e) {
                report.report("FAIL", "Error occured during adding crewmember.");
                throw e;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }finally{
            trimHomePageAM.CloseApplication();
            ReportStatus.reset();
        }
    }
}