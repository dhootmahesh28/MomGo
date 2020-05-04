package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.MouseButton;
import com.hp.lft.sdk.internal.DynamicObjectProxy;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.constants.EnumWaitConstants;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CssTransactionReport extends WinBasePage{

    ReportUtil reportCssHome = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(CssTransactionReport.class);
    ObjectRepoCSS lftObjects =null;
    ArrayList<String> pdfContentAfterReadingTransactionReport;
    String tripStartDate;
    String tripEndDate;
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    String rosaempID;
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();

    public CssTransactionReport()  {
        lftObjects = super.cssObjectRepo;
        LOGGER.info("CssTransactionReport invoked..");
    }

    private void readMasterHM(){
        try{
            if((rosaempID == null)){
                masterHM = TestUtil.getRosaMasterHM();
                if (masterHM == null){
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "masterHM is null.");
                }else {

                    for (Map.Entry<String, Map<String, ArrayList<String[]>>> lstEmpIds : masterHM.entrySet()) {
                        rosaempID = lstEmpIds.getKey();
                        Map<String, ArrayList<String[]>> detailEachEmpId = lstEmpIds.getValue();

                        for (Map.Entry<String, ArrayList<String[]>> entry2 : detailEachEmpId.entrySet()) {
                            Map<String, ArrayList<String[]>> keyEmpId = lstEmpIds.getValue();
                            training = keyEmpId.get("trng");
                            triptopull = keyEmpId.get("triptopull");
                        }
                        break;
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
        }

    }

    public void validateTransactioReportFile() {
        try {
            if (lftObjects.CssMainWindow().transactionReportInternalFrame().exists()) {
                lftObjects.CssMainWindow().transactionReportInternalFrame().activate();
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction Report reflected.");
            }
        }catch(Exception e){
        }
    }

    public void buildLogString(int blockSize, String value, String anEnumCssTransactionReport) throws GeneralLeanFtException{
        String space = " ";
        String[] user = {"",""};
        String[] function = {"LOGINCRW", "LOGINCRW"};
        String[] reason = {"LGN","LGN"};
        String[] logMessage ={"Log In from 107.2.235.122;Macintosh", "Log In from 107.2.235.122;Macintosh"};

        String[] line2Data = {"Crew Member Id:", "Crew Member Id:"};

        String[] line3Data = {"Log In from 107.2.235.122;Macintosh","Log In from 107.2.235.122;Macintosh"};

        ArrayList<String> blocks = new ArrayList<>();

        String reportContent = "";
        reportContent = lftObjects.CssMainWindow().transactionReportInternalFrame().report().getVisibleText().toString();

        for (int i=0; i <= blockSize-1; i++){
            String line1 = user[i]+space+function[i]+space+reason[i]+space+logMessage[i];
            String line2 = line2Data[i]+" "+rosaempID;
            String line3 = line3Data[i];

            if (reportContent.contains(line1) & reportContent.contains(line2)){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction reportCssLogin contains: "+line1+ '\n' + line2 );
            }
        }

        lftObjects.CssMainWindow().transactionReportInternalFrame().close();
    }

    private void CheckIfUnchecked(CheckBox chk) throws GeneralLeanFtException {
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
        CheckIfUnchecked(crewMwmbwr);

        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString(rosaempID);
        Keyboard.pressKey(Keyboard.Keys.ENTER);
        try {
            CheckedState x = lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().getState();
            if(x.getValue().toString().equalsIgnoreCase("0")){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "State of Function checkbox is disabled.");
            }
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        ValidateFunctionList(functionDropdownList);

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
        System.out.println("tripStartDate: "+tripStartDate);
        System.out.println("tripEndDate: "+tripEndDate);
    }

    public void ValidateFunctionList(String functionName) throws  GeneralLeanFtException{
        lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().click();
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.pressKey(Keyboard.Keys.DOWN);
        com.hp.lft.sdk.java.List edt = lftObjects.CssMainWindow().transactionReportDialog().describe(com.hp.lft.sdk.java.List.class, new ListDescription.Builder()
                .attachedText("(CSS Login ID)")
                .index(2)
                .nativeClass("javax.swing.JComboBox").build());

        edt.highlight();
        List<ListItem> dropdownValTemp;
        boolean found = false;
        boolean notFound = false;
        String prevItem = "";

        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);
        Keyboard.pressKey(Keyboard.Keys.PAGE_UP);

        do
        {
            dropdownValTemp = edt.getSelectedItems();
            String eachDropdownValue = dropdownValTemp.get(0).getText().trim();
            if(eachDropdownValue.equalsIgnoreCase(functionName)){
                found = true;
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Function dropdown list contains the given item: "+functionName);
                Keyboard.pressKey(Keyboard.Keys.ENTER);
                break;
            }

            System.out.println("eachDropdownValue: "+eachDropdownValue);
            System.out.println("prevItem: "+prevItem);

            if (eachDropdownValue.equalsIgnoreCase(prevItem)){
                break;
            }
            Keyboard.pressKey(Keyboard.Keys.DOWN);
            prevItem = eachDropdownValue;
        }
        while (!found );
        if (!found){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Function dropdown list does not contains the given item: "+functionName);
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"info", "Function dropdown list is selected with function: "+functionName);
        }
    }

    public void NavigateToTransactionReport() throws GeneralLeanFtException {
        readMasterHM();
        try {
            lftObjects.CssMainWindow().reportsMenu().click();
            lftObjects.CssMainWindow().reportsMenu().transactionReportMenu().click();
            Thread.sleep(4000);

        }catch(Exception e){
            //reportCssLogin.reportCssLogin("fail","Transaction reportCssLogin window didnt open.");
            e.printStackTrace();
        }

        if(lftObjects.CssMainWindow().transactionReportDialog().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction reportCssLogin window exist.");
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Transaction reportCssLogin window doesn't exist.");
        }
    }

    public void readTransactionReport(String path) {
        boolean found = false;
        try {

            File newpath = new  FileUtil().getTheNewestFile(path, "pdf");
            PDFReports pdf = new PDFReports(newpath.getPath());
            ArrayList<String> pdfContent = pdf.readBetweenTags("Transaction Report", "END OF REPORT");
            pdfContentAfterReadingTransactionReport = pdfContent;
            if (!pdfContentAfterReadingTransactionReport.isEmpty()){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass","Transaction reportCssLogin in pdf format is read." );
            }

        }catch(Exception  e){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "fail","Failed to read Transaction reportCssLogin in pdf format." );
        }
    }
}




