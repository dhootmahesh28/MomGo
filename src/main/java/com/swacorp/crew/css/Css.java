package com.swacorp.crew.css;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.java.Table;
import com.hp.lft.sdk.java.TableCell;
import com.hp.lft.sdk.java.TableRow;
import com.hp.lft.sdk.java.Window;
// import com.hp.lft.sdk.winforms.Button;
//import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.tsr.enums.EnumWaitConstants;
import com.swacorp.tsr.utils.pdf.PDFReports;
import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import com.swacorp.tsr.enums.EnumRosa;

public class Css extends WinBasePage{
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(Css.class);
    MainObjectRepoTrim lftObjects =null;
    public HashMap<String, String> pgMap = new HashMap<>();
    private String empNumberFromApp = "";
    private String fldFirstNameFromApp = "";
    private boolean searchFound;
    private String cssMAinWindowTitleInitial;
    private String cssMainWindowTitle;
    private String cmBoardTitle;
    Map<Integer,Set<String>> mpAllTableData = new HashMap<>();
    ArrayList<String> rosatraining;
    ArrayList<String> rosatripToPull;
    String rosaempID;
    boolean searchTripsOnCMBoard = true;
    boolean found = false;
    HashMap<Integer, String[]> detailFromROSA = new HashMap<Integer, String[]>();
    HashMap<Integer, String[]> tripDetalsFromTripDeatilsWindow = new HashMap<Integer, String[]>();
    public Css(ArrayList<String> training, ArrayList<String> tripToPull, String empID) {
        this();
        rosatraining = training;
        rosatripToPull = tripToPull;
        rosaempID = empID;
    }

    public Css()  {
        lftObjects = super.lftObjectRepo;
    }

    public Css(String empID, HashMap<Integer, String[]> hm) {
        this();
        rosaempID = empID;
        detailFromROSA = hm;
    }

public void NavigateToTransactionReport() throws GeneralLeanFtException {
    try {
        lftObjects.CssMainWindow().reportsMenu().click();
        lftObjects.CssMainWindow().reportsMenu().transactionReportMenu().click();
        report.report("pass","Transaction report is navigated successfully.");

    }catch(Exception e){
        report.report("fail","Transaction report window didnt open.");
    }
}

    private int VerifyWindowExist(Window o) throws  GeneralLeanFtException{
        int retVal = 1;
        if (o.exists()){
            retVal =0;
            return retVal;
        }
        return retVal;
    }

    public void loginCss() throws GeneralLeanFtException, InterruptedException, IOException {

        if (!isCssAlradyLogedIn()) {
            new ProcessBuilder("cmd", "/c", EnvironmentConstants.CSSPATH).start();

            WaitEditorTillVisible(lftObjects.loginDialog().userNameEditor(), EnumWaitConstants.WaitDuration.TEN.status());

            lftObjects.loginDialog().userNameEditor().setText(EnvironmentConstants.CSSUSERID);

            lftObjects.loginDialog().passwordEditor().setText(EnvironmentConstants.CSSPASSWORD);
            lftObjects.loginDialog().loginButton().click();

            WaitForJavaWindowTillVisible(lftObjects.CssMainWindow(), EnumWaitConstants.WaitDuration.TEN.status());

            if (lftObjects.CssMainWindow().exists()){
                report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Login to CSS is successful.");
            }else{
                report.report("FAIL", "CSS login is failed");
            }
        }else{
            if (lftObjects.CssMainWindow().exists()){
                cssMAinWindowTitleInitial = lftObjects.CssMainWindow().getTitle();
                report.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "CSS is already logged in.");
            }else{
                report.report("FAIL", "CSS is open but failed to identify the main window.");
            }
        }
        }

        private boolean isCssAlradyLogedIn() throws GeneralLeanFtException {

            lftObjects.CssMainWindow().describe(com.hp.lft.sdk.java.Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder().title(EnvironmentConstants.CSSWINDOWTITLE).build());
            try {
                if (lftObjects.CssMainWindow().exists()) {
                    lftObjects.CssMainWindow().highlight();
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

public void openCMBoard(String empCode) throws GeneralLeanFtException, CloneNotSupportedException {
    try {
        empCode = rosaempID.trim();
        lftObjects.CssMainWindow().crewsMenu().click();
        lftObjects.CssMainWindow().crewMemberSchedule().click();
        Keyboard.sendString(empCode);
        Keyboard.pressKey(Keyboard.Keys.ENTER);
        LOGGER.info("lftObjects.CssMainWindow()  " + lftObjects.CssMainWindow().getTitle());
        if(lftObjects.CssMainWindow().frameCMBoard().exists()) {
            report.reportLeanFT(lftObjects.CssMainWindow(), "pass", "CM Board opened successfully.");
        }else{
            report.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Crew id not found in CSS");
        }
    }catch(Exception e){
        report.reportLeanFT(lftObjects.CssMainWindow(),"fail", "Error occured while opening CM board.");
    }

}

public void selectTripOnCMBoard(String tripStartDate, String tripEndDate, String TripDetails, boolean tripShouldBeAvailableOnCMBoard, boolean readTripDetails, boolean readTransactionReport) throws GeneralLeanFtException, CloneNotSupportedException {


    String x = "com.swacorp.css.screens.crewboard.CrewBoardPiece";
    com.hp.lft.sdk.java.UiObjectDescription targetLinks = new com.hp.lft.sdk.java.UiObjectDescription.Builder()
        .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece").build();

    LOGGER.info("collection.length: "+lftObjects.CssMainWindow().frameCMBoard().findChildren(com.hp.lft.sdk.java.UiObject.class, targetLinks).length);
    lftObjects.CssMainWindow().frameCMBoard().xUiObject().highlight();
    lftObjects.CssMainWindow().frameCMBoard().xUiObject().click();
    try {
        if (!lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().exists()) {
            lftObjects.CssMainWindow().frameCMBoard().modifyScheduleArrowCheckBox().click();
        }
    }catch(Exception e){
    }
    com.hp.lft.sdk.java.UiObjectDescription allUIObj = new com.hp.lft.sdk.java.UiObjectDescription.Builder()
            .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece").build();
    com.hp.lft.sdk.java.UiObject[] allObj = lftObjects.CssMainWindow().frameCMBoard().findChildren(com.hp.lft.sdk.java.UiObject.class, allUIObj);

    //System.out.println("allObj: "+allObj.length);
    tripStartDate = detailFromROSA.get(0)[0].substring(0,7);
    tripEndDate = detailFromROSA.get(3)[1].substring(0,7);
    //System.out.println("tripStartDate: "+tripStartDate);
    //System.out.println("tripEndDate: "+tripEndDate);

    for (int index=allObj.length-1 ; index > 0 ; index--){
        if(found) break;
        try{
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().setDescription(new com.hp.lft.sdk.java.UiObjectDescription.Builder()
                    .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece")
                    .index(index).build());
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().highlight();
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().click();

            LOGGER.info("index: "+index);
            LOGGER.info("lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText()  : "+lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText());
            LOGGER.info("lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText()  : "+lftObjects.CssMainWindow().frameCMBoard().tripEndDateEditor().getText());
            String start = lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText();
            String end = lftObjects.CssMainWindow().frameCMBoard().tripEndDateEditor().getText();

            if (start.contains("Jan") | end.contains("Jan")){
                break;
            }
            if (start.equalsIgnoreCase(tripStartDate) && end.equalsIgnoreCase(tripEndDate)){
            //if (ValidateTripStartAndEndDates(start,end)){
                found = true;
                lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().doubleClick(MouseButton.LEFT);
                break;
            }

            if (!searchTripsOnCMBoard){
                break;
            }

        }catch(Exception e){
           // e.printStackTrace();
        }
    }


    // If Trip is found then double click on the trip and verify that the TripDetails window open and Read the details from each table in the window.
    if (found & tripShouldBeAvailableOnCMBoard) {
        if (found & readTripDetails) {
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().doubleClick();
            Keyboard.pressKey(Keyboard.Keys.ENTER);
            if (lftObjects.CssMainWindow().frameTrimDetails().exists()) {
                report.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip Details exist in CSS.");
                // Read Trip details
                ReadDataFromTripDetails();
                lftObjects.CssMainWindow().frameTrimDetails().close();
            }
        }
    }
    // Verify TransactionReport
    if (readTransactionReport){
        if (lftObjects.CssMainWindow().frameCMBoard().runTransactionReportButton().exists()){
            lftObjects.CssMainWindow().frameCMBoard().runTransactionReportButton().click();
            lftObjects.CssMainWindow().transactionReportInternalFrame().pDFIconButton().click();

        }
    }
    }

    public void readTransactionReport(String path) throws  Exception {
        if (found) {
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            String filepath = "";

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    filepath = file.getName();
                    break;
                }
            }

            PDFReports pdf = new PDFReports(filepath);
            ArrayList<String> pdfContent = pdf.readBetweenTags("Transaction Report", "END OF REPORT");
            LOGGER.info("pdfContent: " + pdfContent);
            report.report("Pass","TReport is successfully read.." );
        }else{
            report.report("Fail","Trips not found on CM board." );
        }
    }

    public void ReadDataFromTripDetails() throws GeneralLeanFtException , CloneNotSupportedException{

        Set<String> rowData = null;
        HashMap<Integer, HashMap<Integer, String[]>> tripDetalsFromTripDeatilsWindow = new HashMap<Integer, HashMap<Integer, String[]>>();
        int rows;
        boolean found = false;
        HashMap<Integer, String[]> tripDetalsPerRow = new HashMap<Integer, String[]>();
        int tables = 0;

        com.hp.lft.sdk.java.TableDescription tblDesc = new com.hp.lft.sdk.java.TableDescription.Builder()
                .nativeClass("com.swacorp.css.screens.trip.TripDetailsTable").build();

        Table[] allTables = lftObjects.CssMainWindow().frameTrimDetails().findChildren(Table.class, tblDesc);
        LOGGER.info(" All Tables: " + allTables.length);


        if( lftObjects.CssMainWindow().frameTrimDetails().exists()){
            report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip Details window has appeared.");
        }else{
            report.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip Details window didn't appeared.");
        }

        for (int inxTbl = 0; inxTbl < allTables.length - 2; inxTbl++ ){
        Table tbl = allTables[inxTbl];
        rows = 0;
        tbl.highlight();
        List<TableRow> allRows = tbl.getRows();
        //for (TableRow r:allRows ){
        for (int inxRow = 0; inxRow < (allRows.size() - 2); inxRow++ ){
            TableRow r = allRows.get(inxRow);
            List<TableCell> rowCells = r.getCells();
            //for (TableCell c: rowCells){
            for (int inxCell =0;inxCell < 1;inxCell++){
                TableCell c = rowCells.get(inxCell);
                LOGGER.info("--------------------------");
                try {
                    String[] tripData = c.getValue().toString().split(",");

                    if (tripData.length > 0) {
                        String date = tripData[33].split("=")[1];
                        String strtBase = tripData[9].split("=")[1];
                        String destBase = tripData[44].split("=")[1];
                        String pay = tripData[19].split("=")[1];

                        String rosaDate = (detailFromROSA.get(tables)[rows]).split("_")[0];
                        String rosaEvent = (detailFromROSA.get(tables)[rows]).split("_")[1];
                        String rosaStart = (detailFromROSA.get(tables)[rows]).split("_")[2];
                        String rosaEnd = (detailFromROSA.get(tables)[rows]).split("_")[3];
                        String rosaPay = (detailFromROSA.get(tables)[rows]).split("_")[4];

                        if (rosaStart.contains(strtBase) & rosaEnd.contains(destBase) & rosaPay.contains(pay) & validateRosaCssDate(date, rosaDate)){
                            report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is available in CSS for table ["+tables+"] and Row ["+inxRow+"].");
                            found = true;
                            break;
                        }else{
                            report.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip details from ROSA is NOT available in CSS for table ["+tables+"] and Row ["+rows+"].");
                        }
                        rows++;
                        break;
                    }
                }catch(Exception e){
                }
            }
        }
        tables++;
        if (found){
            report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is available in CSS.");
        }else{
            report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is not available in CSS.");
        }
    }
    }

    public boolean validateRosaCssDate(String date, String rosaDate){

        String[] date2 = date.split("-");
        String CssDate = date2[2]+date2[1]+date2[0];
        try {
            //String formattedRosaDate = rosaDate.substring(5, 8) + "-" + rosaDate.substring(2, 3) + "-" + rosaDate.substring(0, 1);

            if (CssDate.contains(rosaDate)) {
                return true;
            }
        }catch(Exception e){
        }
        return false;
    }
}




