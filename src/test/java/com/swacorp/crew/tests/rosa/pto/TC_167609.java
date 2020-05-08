package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.pages.constants.ApplicationConstantsCss;
import com.swacorp.crew.tests.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.tests.wrappers.CssWrapper;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.tests.wrappers.RosaWrapper;
import org.testng.annotations.Test;

public class TC_167609 extends TestManager {
    RosaWrapper rosaWrapper;
    CssWrapper cssWrapper;

    TC_167609(){
        rosaWrapper = new RosaWrapper();
        cssWrapper = new CssWrapper();
    }

    @Test(groups = {"167609","rosa_e2e","demo"}, priority=3, dataProvider = "TC167609", dataProviderClass = RosaTestDataProvider.class )
    public void TC167609_FO_Validate_Transaction_Report_that_Function_Code_TRNGCQT_displayed(String[] testData) throws Exception{
        setScenarioName("TC167609_FO Validate in Transaction Report that Function Code TRNGCQT is displayed");
        rosaWrapper.E2EFlow_RosaPTOMODIFIED();
        cssWrapper.loginCss();
        cssWrapper.NavigateToTransactionReport();
        cssWrapper.validateTransactioTeportDialog(ApplicationConstantsCss.CSS_TRAN_REPORT_FUNCTION_LOGINCRW, ApplicationConstantsCss.CSS_TRAN_REPORT_REASON_LGN);
        cssWrapper.validateTransactioReportFile();
        cssWrapper.buildLogString(2, ApplicationConstantsCss.CSS_TRAN_REPORT_FUNCTION_LOGINCRW, ApplicationConstantsCss.CSS_TRAN_REPORT_REASON_LGN);

        /*css.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        css.validatePdfContentAfterReadingTransactionReport(testData[3]);
        css.validatePdfContentAfterReadingTransactionReport(testData[4]);
        css.validatePdfContentAfterReadingTransactionReportHasEmployeeId(testData[4]);
        css.resetReportTab(testData[2]);*/
    }
}


