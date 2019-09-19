package com.swacorp.crew.tests.regression.oqs;

import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.pages.trim.employee.EmployeePage;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.pages.trim.login.LoginPage;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

/**
 * Created by x227377 on 08/25/2019.
 */
public class Create_CrewMember_InOQS_Verify_InTRiM_181164 extends TestManager {

    @Test(groups = {"181164", "regression"})

    public void create_CrewMember_InOQS_Verify_InTRiM_181164() {
        setScenarioName("TC181164_Create CrewMember in OQS and verify CREW info in TRiM");
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

            LoginPage loginPage = new LoginPage();
            loginPage.loginTRiM();
            TRiMHomePage trimWindow = new TRiMHomePage();

            // Menu selection [Employees;Find Employee] using hot keys
            trimWindow.selectMenu("^E-->^F");

            FindEmployeePage findEmployeeWindow = new FindEmployeePage();
            findEmployeeWindow.findEmployee(empNum);

            EmployeePage empWindow = new EmployeePage();
            empWindow.verifyEmployee(empNum);
            empWindow.closeEmployeeWindow();

            findEmployeeWindow.closeFindEmployeeWindow();
            trimWindow.closeTRiMWindow();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}