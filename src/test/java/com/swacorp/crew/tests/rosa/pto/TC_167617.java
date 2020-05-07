package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.tests.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.tests.wrappers.CssWrapper;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.tests.wrappers.RosaWrapper;
import org.testng.annotations.Test;

public class TC_167617 extends TestManager {
    RosaWrapper rosaWrapper;
    CssWrapper cssWrapper;

    TC_167617(){
        rosaWrapper = new RosaWrapper();
        cssWrapper = new CssWrapper();
    }

    @Test(groups = {"167617","rosa_e2e"}, priority=3, dataProvider = "TC167617", dataProviderClass = RosaTestDataProvider.class)
    public void TC167609_TransactionReprtValidation(String[] testData) throws Exception{
        setScenarioName("TC167617_FO Validate that transaction report for Training Trip is displayed with the transactions with the same transgroupseqnumber and new function and reason code");
        rosaWrapper.E2EFlow_RosaPTOMODIFIED();
        cssWrapper.loginCss();
        cssWrapper.openCMBoard("");
        cssWrapper.selectTripOnCMBoard("","","", false, false, false);
        cssWrapper.rightClickCMBoardAndSelectMenu(testData[1]);
        cssWrapper.validateTransactioReportFile();

        cssWrapper.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        //css.validatePdfContentAfterReadingTransactionReport("TRNGCQT,ING");
        cssWrapper.ValidatePdfContentAfterReadingTransactionReport(testData[3]);
        cssWrapper.ValidatePdfContentAfterReadingTransactionReport(testData[4]);
        }

/*
        //  IN PROGRESS
    @Test(groups = {"167612","rosa_e2e"}, priority=3, dataProvider = "TC167617", dataProviderClass = RosaTestDataProvider.class)
    public void TC167612_TransactionReprtValidation(String[] testData) throws Exception{
        setScenarioName("TC167617_FO_Validate_that_transaction_report_for_Training_Trip_is_displayed_with_the_transactions_with_the_same_transgroups");
        E2EFlow_RosaPTO();
        Thread.sleep(1000);
        Css css  = rosaSolutioQueue.NavigateToCSSForValidation();
        css.loginCss();
        css.openCMBoard("");
        css.selectTripOnCMBoard("","","", false, false, false);
        css.rightClickCMBoardAndSelectMenu("Transactions");
        *//*css.validateTransactioReportFile();
        //Css css = new Css();
        css.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        css.validatePdfContentAfterReadingTransactionReport("TRNGCQT,ING");*//*

    }


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
        css.navigateToOT();
        css.selectOTfilters("DEN, DAL, Domestic, 2017");
        css.readOTTripDetails();
        *//*css.rightClickCMBoardAndSelectMenu("Transactions");
        css.validateTransactioReportFile();

        //Css css = new Css();
        css.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        css.validatePdfContentAfterReadingTransactionReport("TRNGCQT,ING");*//*
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


