package com.swacorp.tsr.trim;

import com.swacorp.crew.pages.trim.TrimAddEmployeeRequirement;
import com.swacorp.crew.pages.trim.TrimAutoPopulate;
import com.swacorp.crew.pages.trim.TrimHome;
import com.swacorp.crew.pages.trim.TrimLogin;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import org.testng.annotations.Test;

public class TC184133_Crew_20413_TC03_OQSRK_TRiM_Assign_MO_Due_CM_Through_Plumbing extends TestManager {


    @Test(groups = {"184133"}, dataProvider = "TC184133", dataProviderClass = TestDataProvider.class)
    public void TC184133_Crew_20413_TC03_OQSRK_TRiM_Assign_MO_Due_CM_Through_Plumbing(String[] testData) throws Exception{
        setScenarioName("TC184133_CREW-20413-TC03-OQS RK-TRiM-Assign a CM who is due for MO as a trainee to every time slot available through plumbing.");
        try {

            String schedPeriod = testData[0];
            String empNumbers = testData[1];
            String requirementName = testData[2];
            String equipment = testData[3];
            String countCAs = testData[4];
            String countFOs = testData[5];

            TrimLogin loginpage = new TrimLogin();
            loginpage.loginTRiM("","");

            TrimHome trimHomePageAM = new TrimHome();
            trimHomePageAM.navigateMenu("^p");

            trimHomePageAM.navigateMenu("^m-->^A");
            TrimAddEmployeeRequirement trimAddEmpRequirement  = new TrimAddEmployeeRequirement();
            trimAddEmpRequirement.addEmployeeRequirement(empNumbers, equipment +" "+ requirementName);
            trimHomePageAM.navigateMenu("^T-->^P");
            TrimAutoPopulate autoPopulate  = new TrimAutoPopulate();
            autoPopulate.autoPopulate(equipment, requirementName, countCAs, countFOs);

            RosaLogin rosa = new RosaLogin();
            RosaHome rosahome = rosa.loginRosa();
            rosahome.verifyHomePageAppear();
            rosahome.clickAndVerifyPilotTrainingOptimizer();

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }
}
