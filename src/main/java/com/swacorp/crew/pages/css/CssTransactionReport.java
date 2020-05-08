package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.constants.ApplicationConstantsCss;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.List;

public class CssTransactionReport extends WinBasePage{

    ReportUtil reportCssHome = new ReportUtil();
    private final Logger loggerTranReport = Logger.getLogger(CssTransactionReport.class);
    ObjectRepoCSS lftObjects =null;
    List<String> pdfContentAfterReadingTransactionReport;
    String tripStartDate;
    String tripEndDate;
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    String rosaempID;
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();

    public CssTransactionReport()  {
        lftObjects = super.cssObjectRepo;
        loggerTranReport.info("CssTransactionReport invoked..");
    }

    private void readMasterHM(){
        int i=1;
        try{
            if((rosaempID == null)){
                masterHM = TestUtil.getRosaMasterHM();
                if (masterHM == null){
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "masterHM is null.");
                }else {

                    for (Map.Entry<String, Map<String, ArrayList<String[]>>> lstEmpIds : masterHM.entrySet()) {
                        rosaempID = lstEmpIds.getKey();
                        Map<String, ArrayList<String[]>> keyEmpId = lstEmpIds.getValue();
                        training = keyEmpId.get("trng");
                        triptopull = keyEmpId.get("triptopull");


                        if (Integer.compare(i, ApplicationConstantsCss.NUMBER_OF_EMPLOYEE)==1) {
                            break;
                        }
                        i++;
                    }
                    if ((rosaempID == null)) {
                        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Error occured while reading trip details from ROSA in CSS page. rosaempID is null.");
                    } else {
                        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "info", "ROSA employee details are read in CSS. HashMap size:"+masterHM.size());
                    }
                }
            }
        }catch(Exception e){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Error occured while reading trip details from ROSA in CSS page."+e.getMessage());
            throw e;
        }

    }

    public void validateTransactioReportFile() {
        try {
            if (lftObjects.CssMainWindow().transactionReportInternalFrame().exists()) {
                lftObjects.CssMainWindow().transactionReportInternalFrame().activate();
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction Report reflected.");
            }
        }catch(Exception e){
            loggerTranReport.error(e);
        }
    }

    public void buildLogString(int blockSize, String value, String anEnumCssTransactionReport) throws GeneralLeanFtException{
        String space = " ";
        String[] user = {"",""};
        String[] function = {"LOGINCRW", "LOGINCRW"};
        String[] reason = {"LGN","LGN"};
        String logBody = "Log In from 107.2.235.122;Macintosh";
        String crewId = "Crew Member Id:";

        loggerTranReport.info("value: "+value);
        loggerTranReport.info("anEnumCssTransactionReport: "+anEnumCssTransactionReport);

        String[] logMessage ={logBody, logBody};

        String[] line2Data = {crewId, crewId};

        String reportContent = "";
        reportContent = lftObjects.CssMainWindow().transactionReportInternalFrame().report().getVisibleText();

        for (int i=0; i <= blockSize-1; i++){
            String line1 = user[i]+space+function[i]+space+reason[i]+space+logMessage[i];
            String line2 = line2Data[i]+" "+rosaempID;

            if (reportContent.contains(line1) && reportContent.contains(line2)){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction reportCssLogin contains: "+line1+ '\n' + line2 );
            }
        }

        lftObjects.CssMainWindow().transactionReportInternalFrame().close();
    }

    private void checkIfUnchecked(CheckBox chk) throws GeneralLeanFtException {
        if(chk.getState().toString().equalsIgnoreCase("UNCHECKED")){
            chk.click();
        }else{
            chk.click();
            chk.click();
        }
    }
    public void validateTransactioTeportDialog(String functionDropdownList, String reason) throws GeneralLeanFtException {
        boolean reasonFound = false;

        CheckBox crewMwmbwr = lftObjects.CssMainWindow().transactionReportDialog().crewMemberCheckBox();
        checkIfUnchecked(crewMwmbwr);

        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString(rosaempID);
        Keyboard.pressKey(Keyboard.Keys.ENTER);
        try {
            CheckedState x = lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().getState();
            if(x.getValue().toString().equalsIgnoreCase("0")){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "State of Function checkbox is disabled.");
            }
        } catch (GeneralLeanFtException e) {
            loggerTranReport.error(e);
        }

        try {
            lftObjects.CssMainWindow().transactionReportDialog().reasonCheckBox().click();
            Table reasonTable = lftObjects.CssMainWindow().transactionReportDialog().ReasonTable();
            List<TableRow> rows = reasonTable.getRows();
            for (int i=0; i < rows.size(); i++) {
                List<TableCell> cells = rows.get(i).getCells();
                for(int c = 0; c <  cells.size() ; c++){
                    if (cells.get(c).getValue().toString().contains(reason)){
                        cells.get(0).click();
                        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Reason code '"+reason+"' is found in the dropdown.");
                        reasonFound = true;
                        break;
                    }
                }
                if (reasonFound){
                    break;
                }
            }
            if(!reasonFound){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Reason code not found in the drpdown: "+reason);
            }
        } catch (GeneralLeanFtException e) {
            loggerTranReport.error(e);
        }

        validateFunctionList(functionDropdownList);

        tripStartDate = training.get(0)[0];
        tripEndDate = training.get(6)[0];

        lftObjects.CssMainWindow().transactionReportDialog().lastCheckBox().click();
        lftObjects.CssMainWindow().transactionReportDialog().lastCheckBox().click();

        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString(tripStartDate);
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString(tripEndDate);

        lftObjects.CssMainWindow().transactionReportDialog().runReportButton().click();
    }

    public void validateFunctionList(String functionName) throws  GeneralLeanFtException{
        lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().click();
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.pressKey(Keyboard.Keys.DOWN);
        com.hp.lft.sdk.java.List edt = lftObjects.CssMainWindow().transactionReportDialog().describe(com.hp.lft.sdk.java.List.class, new ListDescription.Builder()
                .attachedText("(CSS Login ID)")
                .index(2)
                .nativeClass("javax.swing.JComboBox").build());

        edt.highlight();
        List<ListItem> dropdownValTemp;
        String prevItem = "";

        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);

        boolean dropValueFound = false;
        do
        {
            dropdownValTemp = edt.getSelectedItems();
            String eachDropdownValue = dropdownValTemp.get(0).getText().trim();
            if(eachDropdownValue.equalsIgnoreCase(functionName)){
                dropValueFound = true;
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Function dropdown list contains the given item: "+functionName);
                Keyboard.pressKey(Keyboard.Keys.ENTER);
            }
            if (eachDropdownValue.equalsIgnoreCase(prevItem)){
                break;
            }
            Keyboard.pressKey(Keyboard.Keys.DOWN);
            prevItem = eachDropdownValue;
        }
        while (!dropValueFound );
        if (!dropValueFound){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Function dropdown list does not contains the given item: "+functionName);
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"info", "Function dropdown list is selected with function: "+functionName);
        }
    }

    public void navigateToTransactionReport() throws GeneralLeanFtException {
        readMasterHM();
        try {
            lftObjects.CssMainWindow().reportsMenu().click();
            lftObjects.CssMainWindow().reportsMenu().transactionReportMenu().click();
            Thread.sleep(4000);

        }catch(Exception e){
           loggerTranReport.error(e);
        }

        if(lftObjects.CssMainWindow().transactionReportDialog().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction reportCssLogin window exist.");
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Transaction reportCssLogin window doesn't exist.");
        }
    }

    public void readTransactionReport(String path) {
        try {

            File newpath = new  FileUtil().getTheNewestFile(path, "pdf");
            PDFReports pdf = new PDFReports(newpath.getPath());
            List<String> pdfContent = pdf.readBetweenTags("Transaction Report", "END OF REPORT");
            pdfContentAfterReadingTransactionReport = pdfContent;
            if (!pdfContentAfterReadingTransactionReport.isEmpty()){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass","Transaction reportCssLogin in pdf format is read." );
            }

        }catch(Exception  e){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "fail","Failed to read Transaction reportCssLogin in pdf format." );
            loggerTranReport.error(e);
        }
    }


    public void validatePdfContentAfterReadingTransactionReportHasEmployeeId(String searchTxt) throws  GeneralLeanFtException{
        searchTxt = searchTxt+rosaempID;
        validatePdfContentAfterReadingTransactionReport(searchTxt);
    }


    public void validatePdfContentAfterReadingTransactionReport(String searchTxt) throws  GeneralLeanFtException{
        int count=0;
        String[] arrsearchTxt = searchTxt.split(",");

        for (String str:pdfContentAfterReadingTransactionReport){
            for (String eachSarchTxt: arrsearchTxt){
                if (str.contains(eachSarchTxt)){
                    count++;
                }
            }
        }

        int trngSize = training.size()+1;
        int searchOccurance = count/(arrsearchTxt.length);

        if (Integer.compare(trngSize,searchOccurance)==1)

            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "The given search text is found in reportCssLogin.");
        else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "fail","The given search text is NOT found in reportCssLogin. " );
        }
        if (lftObjects.CssMainWindow().transactionReportInternalFrame().exists()) {
            lftObjects.CssMainWindow().transactionReportInternalFrame().close();
        }
    }

}




