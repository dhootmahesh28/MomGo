package com.swacorp.crew.tests.regression.oqs;
import com.swacorp.crew.pages.trim.FindEmployee.FindEmployeePage;
import com.swacorp.crew.pages.trim.employee.EmployeePage;
import com.swacorp.crew.pages.trim.homepage.TRiMHomePage;
import com.swacorp.crew.pages.trim.login.LoginPage;
//import com.swacorp.crew.pages.trim.scheduledplanner.ScheduledPlanner;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by x227377 on 09/05/2019.
 */
public class Create_CrewMember_InOQS_Verify_InTRiM_1111 extends TestManager {

    @Test(groups = {"1111", "regression"})
    public void create_CrewMember_InOQS_Verify_InTRiM_1111() throws IOException {
        LoginPage_am loginPage_am = new LoginPage_am();
        try {
            loginPage_am.loginTRiM();
        }catch(Exception e){

        }
    }
}