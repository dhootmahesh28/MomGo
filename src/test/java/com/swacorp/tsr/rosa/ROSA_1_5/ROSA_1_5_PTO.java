package com.swacorp.tsr.rosa.ROSA_1_5;

import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.rosa.RosaHome;
import com.swacorp.tsr.rosa.RosaLogin;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import org.testng.annotations.Test;

public class ROSA_1_5_PTO extends TestManager {
    RosaLogin rosa;
    RosaSolutioQueue rosaSolutioQueue;

    @Test(groups = {"164530"})
    public void TC164530_010_FO_Validate_that_PTO_page_is_displayed_the_initial_dependent_parameters_Edge_Navigator() throws Exception{
        setScenarioName("TC164530_ FO Validate that PTO page is displayed the initial dependent parameters - Edge Navigator");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.VerifyElementAppear("CONDITIONAL_RADIO_BTN");
    }
}
