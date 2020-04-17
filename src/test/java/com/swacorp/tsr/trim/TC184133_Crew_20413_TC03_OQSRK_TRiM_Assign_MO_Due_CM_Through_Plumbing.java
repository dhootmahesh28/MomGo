package com.swacorp.tsr.trim;

import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.rosa.RosaHome;
import com.swacorp.tsr.rosa.RosaLogin;
import org.testng.annotations.Test;
import com.swacorp.crew.utils.DateUtil;

public class TC184133_Crew_20413_TC03_OQSRK_TRiM_Assign_MO_Due_CM_Through_Plumbing extends TestManager {


    @Test(groups = {"184133"})
    public void TC184133_Crew_20413_TC03_OQSRK_TRiM_Assign_MO_Due_CM_Through_Plumbing() throws Exception{
        setScenarioName("TC184133_CREW-20413-TC03-OQS RK-TRiM-Assign a CM who is due for MO as a trainee to every time slot available through plumbing.");
        try {

            LoginPage_am loginpage = new LoginPage_am();
            loginpage.loginTRiM("","");

            TrimHomePageAM trimHomePageAM = new TrimHomePageAM();
            trimHomePageAM.NavigateMenu("^p");

            String schedPeriod = "Apr2020";
            String empNumbers = "81144,133761,91732,85928";
            String requirementName = "CQT 2020 - Automation";
            String equipment = "737";

            trimHomePageAM.NavigateMenu("^m-->^A");
            trimHomePageAM.AddEmployeeRequirement(empNumbers, equipment +" "+ requirementName);
            trimHomePageAM.NavigateMenu("^T-->^P");
            trimHomePageAM.AutoPopulate("737", requirementName);

            RosaLogin rosa = new RosaLogin();
            RosaHome rosahome = rosa.loginRosa();
            rosahome.VerifyHomePageAppear();
            rosahome.clickAndVerifyPilotTrainingOptimizer();


            //String monthYear = rosahome.getMonthYear();
            //if monthYear

            //trimHomePageAM.NavigateMenu("^E-->^F");
            //trimHomePageAM.VerifyEmployeeSchedule("81144", "MO", "5");*/
            //DateUtil dateUtil = new DateUtil();
            //dateUtil.changeLocalDate(-1);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
