package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.common.Constant;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by x227377 on 08/25/2019.
 */
public class TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM extends TestManager {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM.class);
    LoginPage_am trimLoginPage = null;
    Constant commonVars = null;
    TrimHomePageAM trimHomePageAM = null;
    OQSLoginPage oqsLoginPage = null;
    Map<String, String> hmAppData = new HashMap<String, String>();

    TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM(){
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
    }

    @Test(groups = {"14477", "regression"}, dataProvider = "TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM", dataProviderClass = TestDataProvider.class)
    public void crew_14499_TC03_OQS_RK_TRiM_CA_Upgrade_FO_to_CA(String[] testData) throws GeneralLeanFtException {
        int retVal = -1;
        int oqsCreate = -1;
        setScenarioName("TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM");
        LOGGER.info("Starting TC >> TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM..");

        try {

            oqsLoginPage.loginOQS();
            HomePage oqsHomePage = new HomePage();
            String empNum = oqsHomePage.searchCrew();
            LOGGER.info("EMP no: "+empNum);

            DateUtil dateUtil = new DateUtil();
            String startDate = dateUtil.getCurrentDate();
            String classYear = dateUtil.getCurrentYear();

            try {
               oqsCreate = oqsHomePage.addCrewMember(testData[0], startDate, classYear, testData[1], testData[2], testData[3],
                        empNum, testData[4], testData[5], testData[6], testData[7], testData[8], testData[9], testData[10], testData[11], testData[12]);
                if (oqsCreate ==1){
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
                    retVal = trimHomePageAM.SearchEmployeesDetails(empNum);
                }catch(Exception e){
                    report.report("FAIL", "Error occured while searchig for the employee '"+empNum+"'"+ReportStatus.getReportMsg());
                }

                if (retVal ==1){
                    report.report("Pass", "Employee number: "+empNum+"'"+" not found in Trim as expected for position "+testData[0]);
                }else{
                    report.report("fail", "Employee number: "+empNum+"'"+" found in Trim for position "+testData[0]);
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