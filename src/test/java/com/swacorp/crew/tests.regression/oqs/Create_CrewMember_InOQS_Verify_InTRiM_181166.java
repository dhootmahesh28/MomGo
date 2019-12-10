package com.swacorp.crew.tests.regression.oqs;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.pages.trim.employee.EmployeePage;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.pages.trim.login.LoginPage;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

/**
 * Created by x227377 on 09/05/2019.
 */
public class Create_CrewMember_InOQS_Verify_InTRiM_181166 extends TestManager {

    @Test(groups = {"181166", "regression"})
    public void create_CrewMember_InOQS_Verify_InTRiM_181166() {
        setScenarioName("TC181166_Create CrewMember in OQS and verify CREW info in TRiM_Copy");
        try {

            LoginPage loginPage = new LoginPage();
            loginPage.loginTRiM();
            TRiMHomePage trimWindow = new TRiMHomePage();
            trimWindow.selectMenu("^E-->^F");

            FindEmployeePage findEmployeeWindow = new FindEmployeePage();
            findEmployeeWindow.findEmployee("45678");

            EmployeePage empWindow = new EmployeePage();
            empWindow.verifyEmployee("45678");
            empWindow.closeEmployeeWindow();

            findEmployeeWindow.closeFindEmployeeWindow();
            trimWindow.closeTRiMWindow();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}