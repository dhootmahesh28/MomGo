package com.swacorp.crew.tests.rosa.pto;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.tests.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.tests.wrappers.CssWrapper;
import com.swacorp.crew.tests.wrappers.RosaWrapper;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaLogin;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_166198 extends TestManager {

    RosaWrapper rosaWrapper;
    CssWrapper cssWrapper;

    TC_166198(){
        rosaWrapper = new RosaWrapper();
        cssWrapper = new CssWrapper();
    }
    @Test(groups = {"166198","rosa_e2e","demo"}, priority=1, dataProvider = "TC166198", dataProviderClass = RosaTestDataProvider.class )
    public void tc166198_FO_Validate_in_CM_board_that_domestic_trip_pulled_is_replaced_by_TR_bar(String[] testData) throws Exception {
        setScenarioName("TC166198_ FO Validate that PTO page is displayed the initial dependent parameters - Edge Navigator");
        rosaWrapper.E2EFlow_RosaPTOMODIFIED();
        cssWrapper.loginCss();
        cssWrapper.openCMBoard("");
        cssWrapper.selectTripOnCMBoard("","","", true, true, false);
        cssWrapper.ValidateNotifiedOption(testData[1]);
        cssWrapper.ValidateCredits();
    }
}


