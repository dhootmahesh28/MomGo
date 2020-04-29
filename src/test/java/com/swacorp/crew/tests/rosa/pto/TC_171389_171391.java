package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.pages.css.CssHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.test.wrappers.WrapperPTOWorkflow;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.sasi.SasiHome;
import com.swacorp.crew.pages.sasi.SasiLogin;
import org.testng.annotations.Test;

public class TC_171389_171391 extends TestManager {
    RosaLogin rosa;
    RosaSolutionQueue rosaSolutioQueue;
    CssHome css;
    SasiHome sasiHome;
    SasiLogin sasiLogin;
    WrapperPTOWorkflow ptowrapper = new WrapperPTOWorkflow(rosa, rosaSolutioQueue, css);

    //*In Progress*//*
    @Test(groups = {"171389", "171391","sasi","rosa_e2e","demo"}, priority=4)
    public void TC171389_TC171391_Validate_nonfly_activity_is_updated_on_SASI_for_each_training_trip_ingested() throws Exception{
        setScenarioName("TC171389_171391_FO_Validate_that_transaction_report_for_Training_Trip_is_displayed_with_the_transactions_with_the_same_transgroups");
        css = ptowrapper.E2EFlow_RosaPTOMODIFIED();
        Thread.sleep(1000);

        // sasiLogin = new SasiLogin(ptowrapper.getMasterHM());
        sasiLogin = ptowrapper.navigateToSasi();
        sasiHome = sasiLogin.loginSasi();
        sasiHome.clickWorldViewer();
        sasiHome.selectData();
        sasiHome.clickFirstLink();
        sasiHome.readNonFlyDetails();
    }


    @Test(groups = {"19"}, priority=4)
    public void TC17323231389_TC171391_Validate_nonfly_activity_is_updated_on_SASI_for_each_training_trip_ingested() throws Exception{
        setScenarioName("TC171389_171391_FO_Validate_that_transaction_report_for_Training_Trip_is_displayed_with_the_transactions_with_the_same_transgroups");
        DateUtil du = new DateUtil();
        //System.out.println(du.dateFormat("yyyy-mm-dd","08Mar2020", "ddmmmyyyy"));
    }
}


