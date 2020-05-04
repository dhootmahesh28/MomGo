package com.swacorp.crew.tests.rosa.pto;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.css.CssHome;
import com.swacorp.crew.tests.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.tests.wrappers.CssWrapper;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.tests.wrappers.RosaWrapper;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_166214 extends TestManager {
    RosaWrapper rosaWrapper;
    CssWrapper cssWrapper;

    TC_166214(){
        rosaWrapper = new RosaWrapper();
        cssWrapper = new CssWrapper();
    }
    //  IN PROGRESS
    //*TODO: Validation is pending from the OT Trip details.*//
    @Test(groups = {"166214","rosa_e2e","demo"}, priority=2, dataProvider = "TC167614", dataProviderClass = RosaTestDataProvider.class)
    public void TC171389_171391_FO_Validate_in_OT_that_domestic_trip_was_pulled_after_ingest_process(String[] testData) throws InterruptedException, GeneralLeanFtException, IOException {
        setScenarioName("TC171389_171391_FO_Validate_in_OT_that_domestic_trip_was_pulled_after_ingest_process");
        /*E2EFlow_RosaPTO();
        Thread.sleep(1000);
        Css css  = rosaSolutioQueue.NavigateToCSSForValidation();*/
        //css = ptowrapper.E2EFlow_RosaPTO()

        rosaWrapper.E2EFlow_RosaPTOMODIFIED();
        cssWrapper.loginCss();
        cssWrapper.NavigateToOT();
        cssWrapper.selectOTfilters("DEN,Captain, Domestic");
        cssWrapper.readOTTripDetails();
    }
}


