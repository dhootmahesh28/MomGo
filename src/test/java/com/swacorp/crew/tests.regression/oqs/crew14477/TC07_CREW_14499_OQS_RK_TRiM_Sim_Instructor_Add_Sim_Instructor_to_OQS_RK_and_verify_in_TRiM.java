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
public class TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM extends TestManager {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM.class);
    LoginPage_am trimLoginPage = null;
    Constant commonVars = null;
    TrimHomePageAM trimHomePageAM = null;
    OQSLoginPage oqsLoginPage = null;
    Map<String, String> hmAppData = new HashMap<String, String>();

    TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM(){
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
    }

    @Test(groups = {"14477", "regression"}, dataProvider = "TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM", dataProviderClass = TestDataProvider.class)
    public void TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM(String[] testData) throws GeneralLeanFtException {
        int retVal = -1;
        int oqsCreate = -1;
        setScenarioName("TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM");
        LOGGER.info("Starting TC >> TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM..");

        try {

            oqsLoginPage.loginOQS();
            HomePage oqsHomePage = new HomePage();
            String empNum = oqsHomePage.searchCrew();
            LOGGER.info("EMP no: "+empNum);

            DateUtil dateUtil = new DateUtil();
            String startDate = dateUtil.getCurrentDate();
            String classYear = dateUtil.getCurrentYear();

            try {
                oqsCreate = oqsHomePage.addCrewMember(testData[0], startDate, classYear, testData[1], testData[2], testData[3],empNum, testData[4], testData[5], testData[6], testData[7], testData[8], testData[9], testData[10], testData[11], testData[12]);
                if ( oqsCreate ==1){
                    throw new Exception();
                }

                //Login to Trim
                trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);


                trimHomePageAM.NavigateMenu("^E-->^F");
                try {
                    retVal = trimHomePageAM.SearchEmployeesDetails(empNum);

                    if((retVal==0)&&(trimHomePageAM.pgMap.get("TRIM_EMP_NO").equalsIgnoreCase(empNum)) && (trimHomePageAM.pgMap.get("TRIM_EMP_FIRSTNAME").equalsIgnoreCase(testData[4]))) {
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