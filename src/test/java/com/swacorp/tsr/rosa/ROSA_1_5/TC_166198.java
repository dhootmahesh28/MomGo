package com.swacorp.tsr.rosa.ROSA_1_5;

import com.swacorp.crew.css.Css;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.rosa.RosaHome;
import com.swacorp.tsr.rosa.RosaLogin;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import org.testng.annotations.Test;

public class TC_166198 extends TestManager {
    RosaLogin rosa;
    RosaSolutioQueue rosaSolutioQueue;

    @Test(groups = {"166198"})
    public void TC164530_010_FO_Validate_that_PTO_page_is_displayed_the_initial_dependent_parameters_Edge_Navigator() throws Exception{
        setScenarioName("TC166198_ FO Validate that PTO page is displayed the initial dependent parameters - Edge Navigator");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        RosaSolutioQueue solQueus = rosahome.gotoPTOQueus();
        solQueus.selectFilterOption("Completed");
        solQueus.validateCompleteStatus();

        Thread.sleep(1000);
        Css css  = solQueus.NavigateToCSSForValidation();

        css.loginCss();
        css.openCMBoard("");
       css.selectTripOnCMBoard("","","", true, true, false);
    }


    public void RosaLoginNavigateSolQue() throws Exception {
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        RosaSolutioQueue solQueus = rosahome.gotoPTOQueus();
        solQueus.selectFilterOption("Completed");
        //solQueus.validateCompleteStatus();
        /*rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.VerifyElementAppear("CONDITIONAL_RADIO_BTN");*/
        solQueus.clickDownloadPdf(1);
        Thread.sleep(1000);
        Css css  = solQueus.NavigateToCSSForValidation();
        }
}


