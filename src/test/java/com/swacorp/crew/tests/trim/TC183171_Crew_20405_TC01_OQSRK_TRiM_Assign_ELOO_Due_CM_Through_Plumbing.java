package com.swacorp.crew.tests.trim;

import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.trim.TrimAddEmployeeRequirement;
import com.swacorp.crew.pages.trim.TrimAutoPopulate;
import com.swacorp.crew.pages.trim.TrimHome;
import com.swacorp.crew.pages.trim.TrimLogin;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class TC183171_Crew_20405_TC01_OQSRK_TRiM_Assign_ELOO_Due_CM_Through_Plumbing extends TestManager {


    @Test(groups = {"184133"}, dataProvider = "TC184133", dataProviderClass = TestDataProvider.class)
    public void TC183171_Crew_20405_TC01_OQSRK_TRiM_Assign_ELOO_Due_CM_Through_Plumbing(String[] testData) throws Exception{
        setScenarioName("CREW-20405-TC01-OQS RK-TRiM-Assign a CM who is due for ELOO as a trainee to a time slot through plumbing.");
        try {

        }
        catch(Exception ex){
            LOGGER.error(ex);
        }
    }
}
