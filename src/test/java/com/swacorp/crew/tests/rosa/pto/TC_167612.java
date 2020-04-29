package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.pages.css.CssHome;
import com.swacorp.crew.tests.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.test.wrappers.WrapperPTOWorkflow;
import org.testng.annotations.Test;

public class TC_167612 extends TestManager {
    RosaLogin rosa;
    RosaSolutionQueue rosaSolutioQueue;
    CssHome css;
    WrapperPTOWorkflow ptowrapper = new WrapperPTOWorkflow(rosa, rosaSolutioQueue, css);

        //  IN PROGRESS
    @Test(groups = {"167612","rosa_e2e"}, priority=3, dataProvider = "TC167617", dataProviderClass = RosaTestDataProvider.class)
    public void TC167612_TransactionReprtValidation(String[] testData) throws Exception{
        setScenarioName("TC167617_FO_Validate_that_transaction_report_for_Training_Trip_is_displayed_with_the_transactions_with_the_same_transgroups");
        /*E2EFlow_RosaPTO();
        Thread.sleep(1000);
        Css css  = rosaSolutioQueue.NavigateToCSSForValidation();*/
        css = ptowrapper.E2EFlow_RosaPTOMODIFIED();
        css.loginCss();
        css.openCMBoard("");
        css.selectTripOnCMBoard("","","", false, false, false);
        css.rightClickCMBoardAndSelectMenu("Transactions");
        //*css.validateTransactioReportFile();
        //Css css = new Css();
        css.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        css.ValidatePdfContentAfterReadingTransactionReport("TRNGCQT,ING");
    }

/*
    //  IN PROGRESS
    *//*TODO: Validation is pending from the OT Trip details.*//*
    @Test(groups = {"166214","rosa_e2e"}, priority=3, dataProvider = "TC167614", dataProviderClass = RosaTestDataProvider.class)
    public void TC171389_171391(String[] testData) throws Exception{
        setScenarioName("TC166214");
        E2EFlow_RosaPTO();
        Thread.sleep(1000);
        Css css  = rosaSolutioQueue.NavigateToCSSForValidation();
        css.loginCss();
        css.openCMBoard("");
        css.selectTripOnCMBoard("","","", false, false, false);
        css.NavigateToOT();
        css.selectOTfilters("DEN, DAL, Domestic, 2017");
        css.readOTTripDetails();
        *//*css.rightClickCMBoardAndSelectMenu("Transactions");
        css.validateTransactioReportFile();

        //Css css = new Css();
        css.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        css.ValidatePdfContentAfterReadingTransactionReport("TRNGCQT,ING");*//*
    }

    *//*In Progress*//*
    @Test(groups = {"171389", "171391","sasi","rosa_e2e"}, priority=3)
    public void TC167609_Temp_Test() throws Exception{
        setScenarioName("TC171389_171391_FO_Validate_that_transaction_report_for_Training_Trip_is_displayed_with_the_transactions_with_the_same_transgroups");
        E2EFlow_RosaPTO();
        SasiLogin sasiLogin = new SasiLogin();
        sasiLogin.loginSasi();
    }


    @BeforeTest
    public void E2EFlow_RosaPTO() throws Exception{
        setScenarioName("TC_01_ROSA Test");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        rosahome.VerifyHomePageAppear();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.verifyConditionalRadioButtonExist();
        rosahome.verifyCoreRadioButtonExist();
        rosahome.verifyStartAndResetButtonExist();
        // TRiM BCAT 2020"
        //"TRiM Initial 2020 - Kari"
        rosaSolutioQueue = rosahome.createPTOSolutionRequest("Recurrent", "AQP 12 Month","737 All","OQS 2020 CQT","May 2020","Hard Line","");
        rosaSolutioQueue.checkDuplicateRequestExist();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.StatusPollingOfPTORequest("Recurrent", "OQS 2020 CQT","737 All","OQS 2020 CQT","May 2020","Hard Line","","04Apr2020 10:03");
        css  = rosaSolutioQueue.NavigateToCSSForValidation();
    }*/

}


