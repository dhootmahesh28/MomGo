package com.swacorp.crew.pages.rosa.rosa_1_5;

import com.swacorp.crew.pages.css.CssHome;
import com.swacorp.crew.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.pages.rosa.wrappers.WrapperPTOWorkflow;
import org.testng.annotations.Test;

public class TC_166214 extends TestManager {
    RosaLogin rosa;
    RosaSolutionQueue rosaSolutioQueue;
    CssHome css;
    WrapperPTOWorkflow ptowrapper = new WrapperPTOWorkflow(rosa, rosaSolutioQueue, css);

    //  IN PROGRESS
    //*TODO: Validation is pending from the OT Trip details.*//
    @Test(groups = {"166214","rosa_e2e","demo"}, priority=2, dataProvider = "TC167614", dataProviderClass = RosaTestDataProvider.class)
    public void TC171389_171391_FO_Validate_in_OT_that_domestic_trip_was_pulled_after_ingest_process(String[] testData) throws Exception{
        setScenarioName("TC171389_171391_FO_Validate_in_OT_that_domestic_trip_was_pulled_after_ingest_process");
        /*E2EFlow_RosaPTO();
        Thread.sleep(1000);
        Css css  = rosaSolutioQueue.NavigateToCSSForValidation();*/
        //css = ptowrapper.E2EFlow_RosaPTO()

        css = ptowrapper.E2EFlow_RosaPTOMODIFIED();
        css.loginCss();
        css.NavigateToOT();
        css.selectOTfilters("DEN,Captain, Domestic");
        css.readOTTripDetails();
    }

    @Test(groups = {"css","rosa_e2e","demo"}, priority=2, dataProvider = "TC167614", dataProviderClass = RosaTestDataProvider.class)
    public void TC17138911_171391_FO_Validate_in_OT_that_domestic_trip_was_pulled_after_ingest_process(String[] testData) throws Exception{
        setScenarioName("TC171389_171391_FO_Validate_in_OT_that_domestic_trip_was_pulled_after_ingest_process");
        CssHome css = new CssHome();
        //css.temp();
    }


}


