package com.swacorp.tsr.rosa.rosa_1_5;

import com.swacorp.crew.css.Css;
import com.swacorp.crew.dataprovider.RosaTestDataProvider;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.enums.EnumCssTransactionReport;
import com.swacorp.tsr.rosa.RosaLogin;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import com.swacorp.tsr.rosa.wrappers.WrapperPTOWorkflow;
import org.testng.annotations.Test;

public class TC_167609 extends TestManager {
    RosaLogin rosa;
    RosaSolutioQueue rosaSolutioQueue;
    Css css;
    WrapperPTOWorkflow ptowrapper = new WrapperPTOWorkflow(rosa, rosaSolutioQueue, css);

    @Test(groups = {"167609","rosa_e2e","demo"}, priority=3, dataProvider = "TC167609", dataProviderClass = RosaTestDataProvider.class )
    public void TC167609_FO_Validate_Transaction_Report_that_Function_Code_TRNGCQT_displayed(String[] testData) throws Exception{
        setScenarioName("TC167609_FO Validate in Transaction Report that Function Code TRNGCQT is displayed");
        css = ptowrapper.E2EFlow_RosaPTOMODIFIED();
        Thread.sleep(1000);
        css.loginCss();
        css.NavigateToTransactionReport();
        css.validateTransactioTeportDialog(EnumCssTransactionReport.Function.LOGINCRW.getValue(), EnumCssTransactionReport.Reason.LGN.getValue());
        css.validateTransactioReportFile();
        css.buildLogString(2, EnumCssTransactionReport.Function.LOGINCRW.getValue(), EnumCssTransactionReport.Reason.LGN.getValue());

        /*css.readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
        css.ValidatePdfContentAfterReadingTransactionReport(testData[3]);
        css.ValidatePdfContentAfterReadingTransactionReport(testData[4]);
        css.ValidatePdfContentAfterReadingTransactionReportHasEmployeeId(testData[4]);
        css.resetReportTab(testData[2]);*/
    }
}


