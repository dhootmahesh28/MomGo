package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.pages.css.CssHome;
import com.swacorp.crew.tests.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.test.wrappers.WrapperPTOWorkflow;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaLogin;
import org.testng.annotations.Test;

public class TC_166198 extends TestManager {
    RosaLogin rosa;
    RosaSolutionQueue rosaSolutioQueue;
    CssHome css;
    WrapperPTOWorkflow ptowrapper = new WrapperPTOWorkflow(rosa, rosaSolutioQueue, css);
    @Test(groups = {"166198","rosa_e2e","demo"}, priority=1, dataProvider = "TC166198", dataProviderClass = RosaTestDataProvider.class )
    public void TC166198_FO_Validate_in_CM_board_that_domestic_trip_pulled_is_replaced_by_TR_bar(String[] testData) throws Exception{
        setScenarioName("TC166198_ FO Validate that PTO page is displayed the initial dependent parameters - Edge Navigator");
        css = ptowrapper.E2EFlow_RosaPTOMODIFIED();
        Thread.sleep(1000);
        //Css css  = rosaSolutioQueue.NavigateToCSSForValidation();
        css.loginCss();
        css.openCMBoard("");
        css.selectTripOnCMBoard("","","", true, true, false);
        css.ValidateNotifiedOption(testData[1]);
        //css.ValidateNonflyBarReasonCode(testData[2]);
        css.ValidateCredits();
    }

    @Test(groups = {"oqsLft","rosa_e2e","demo"}, priority=1, dataProvider = "TC166198", dataProviderClass = RosaTestDataProvider.class )
    public void TC166198_111(String[] testData) throws Exception{
        setScenarioName("TC166198_ FO Validate that PTO page is displayed the initial dependent parameters - Edge Navigator");

    }
}


