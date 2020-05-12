package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.tests.wrappers.RosaWrapper;
import com.swacorp.crew.tests.wrappers.SasiWrapper;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.sasi.SasiHome;
import com.swacorp.crew.pages.sasi.SasiLogin;
import org.testng.annotations.Test;

public class TC_171389_171391 extends TestManager {
    RosaWrapper rosaWrapper;
    SasiWrapper sasiWrapper;

    TC_171389_171391(){
        rosaWrapper = new RosaWrapper();
        sasiWrapper = new SasiWrapper();
    }

    //*In Progress*//*
    @Test(groups = {"171389", "171391","sasi","rosa_e2e","demo"}, priority=4)
    public void TC171389_TC171391_Validate_nonfly_activity_is_updated_on_SASI_for_each_training_trip_ingested() throws Exception{
        setScenarioName("TC171389_171391_FO_Validate_that_transaction_report_for_Training_Trip_is_displayed_with_the_transactions_with_the_same_transgroups");
        rosaWrapper.E2EFlow_RosaPTOMODIFIED();
        // sasiLogin = new SasiLogin(ptowrapper.getRosaMasterHM());
        //sasiLogin = ptowrapper.navigateToSasi();
        sasiWrapper.loginSasi("","");
        sasiWrapper.clickWorldViewer();
        sasiWrapper.selectData();
        sasiWrapper.clickFirstLink();
        sasiWrapper.readNonFlyDetails();
    }

}


