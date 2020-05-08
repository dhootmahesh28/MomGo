package com.swacorp.tsr.trim;

import com.swacorp.crew.pages.trim.TrimAddEmployeeRequirement;
import com.swacorp.crew.pages.trim.TrimAutoPopulate;
import com.swacorp.crew.pages.trim.TrimHome;
import com.swacorp.crew.pages.trim.TrimLogin;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import org.testng.annotations.Test;

public class TC184133_Crew_20413_TC03_OQSRK_TRiM_Assign_MO_Due_CM_Through_Plumbing extends TestManager {


    @Test(groups = {"184133"})
    public void TC184133_Crew_20413_TC03_OQSRK_TRiM_Assign_MO_Due_CM_Through_Plumbing() throws Exception{
        setScenarioName("TC184133_CREW-20413-TC03-OQS RK-TRiM-Assign a CM who is due for MO as a trainee to every time slot available through plumbing.");
        try {

            TrimLogin loginpage = new TrimLogin();
            loginpage.loginTRiM("","");

            TrimHome trimHomePageAM = new TrimHome();
            trimHomePageAM.navigateMenu("^p");

            String schedPeriod = "Apr2020";
            String empNumbers = "81144,133761,91732,85928";
            String requirementName = "CQT 2020 - Automation";
            String equipment = "737";

            trimHomePageAM.navigateMenu("^m-->^A");
            TrimAddEmployeeRequirement trimAddEmpRequirement  = new TrimAddEmployeeRequirement();
            trimAddEmpRequirement.addEmployeeRequirement(empNumbers, equipment +" "+ requirementName);
            trimHomePageAM.navigateMenu("^T-->^P");
            TrimAutoPopulate autoPopulate  = new TrimAutoPopulate();
            autoPopulate.autoPopulate("737", requirementName, "10", "10");

            RosaLogin rosa = new RosaLogin();
            RosaHome rosahome = rosa.loginRosa();
            rosahome.verifyHomePageAppear();
            rosahome.clickAndVerifyPilotTrainingOptimizer();


            //String monthYear = rosahome.getMonthYear();
            //if monthYear

            //trimHomePageAM.navigateMenu("^E-->^F");
            //trimHomePageAM.verifyEmployeeSchedule("81144", "MO", "5");*/
            //DateUtil dateUtil = new DateUtil();
            //dateUtil.changeLocalDate(-1);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
