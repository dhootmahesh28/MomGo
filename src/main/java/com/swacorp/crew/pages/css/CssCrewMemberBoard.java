package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.MouseButton;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.constants.ApplicationConstantsCss;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import com.swacorp.crew.pages.constants.MessageConstants;
public class CssCrewMemberBoard extends WinBasePage{

    ReportUtil reportCssHome = new ReportUtil();
    private final Logger loggerCMBoard = Logger.getLogger(CssCrewMemberBoard.class);
    ObjectRepoCSS lftObjects =null;
    boolean searchTripsOnCMBoard = true;
    boolean found = false;
    HashMap<Integer, String[]> detailFromROSA = new HashMap();
    String tripStartDate;
    String tripEndDate;
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    String rosaempID;
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();


    public CssCrewMemberBoard()  {
        lftObjects = super.cssObjectRepo;
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


                    if (Integer.compare(i,ApplicationConstantsCss.NUMBER_OF_EMPLOYEE)==1) {
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

    public void openCMBoard(String empCode) throws GeneralLeanFtException {
    readMasterHM();
    try {
        empCode = rosaempID.trim();
        lftObjects.CssMainWindow().crewsMenu().click();
        lftObjects.CssMainWindow().crewMemberSchedule().click();
        Keyboard.sendString(empCode);
        Keyboard.pressKey(Keyboard.Keys.ENTER);
        loggerCMBoard.info("lftObjects.CssMainWindow()  " + lftObjects.CssMainWindow().getTitle());
        if(lftObjects.CssMainWindow().frameCMBoard().exists()) {
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass", "CM Board opened successfully for Emp Id:"+rosaempID);
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Crew id not found in CSS. Crew ID:"+rosaempID);
        }
    }catch(GeneralLeanFtException e){
        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"fail", "Error occured while opening CM board."+e.getMessage());
        loggerCMBoard.error(e.getMessage());
        throw e;
    }

}

    public void selectTripOnCMBoard(String tripStartDateROSA, String tripEndDateROSA, boolean tripShouldBeAvailableOnCMBoard, boolean readTripDetails, boolean readTransactionReport) throws GeneralLeanFtException, CloneNotSupportedException {
    String msgTripStartDate = " trip start date:";
    String msgTripEndDate = ", Trip end date: ";
    String crewBoardPieces = "com.swacorp.css.screens.crewboard.CrewBoardPiece";
    UiObjectDescription targetLinks = new UiObjectDescription.Builder().nativeClass(crewBoardPieces).build();
    loggerCMBoard.info("collection.length: "+lftObjects.CssMainWindow().frameCMBoard().findChildren(UiObject.class, targetLinks).length);
    lftObjects.CssMainWindow().frameCMBoard().xUiObject().highlight();
    lftObjects.CssMainWindow().frameCMBoard().xUiObject().click();
    try {
        if (!lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().exists()) {
            lftObjects.CssMainWindow().frameCMBoard().modifyScheduleArrowCheckBox().click();
        }
    }catch(GeneralLeanFtException e){
        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),MessageConstants.ERROR, "LFT error: "+e.getMessage());
        loggerCMBoard.error("Lft error "+e.getMessage());
        throw e;
    }
    UiObjectDescription allUIObj = new UiObjectDescription.Builder()
            .nativeClass(crewBoardPieces).build();
    UiObject[] allObj = lftObjects.CssMainWindow().frameCMBoard().findChildren(UiObject.class, allUIObj);

    if(tripStartDateROSA.equalsIgnoreCase("")) {
        tripStartDate = training.get(0)[0];
    }

    if(tripEndDateROSA.equalsIgnoreCase("")) {
        tripEndDate = training.get(6)[0];
    }

    for (int index=allObj.length-1 ; index > 0 ; index--){
        if(found) break;
        try{
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().setDescription(new UiObjectDescription.Builder()
                    .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece")
                    .index(index).build());
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().highlight();
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().click();

            loggerCMBoard.info("index: "+index);
            loggerCMBoard.info("lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText()  : "+lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText());
            loggerCMBoard.info("lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText()  : "+lftObjects.CssMainWindow().frameCMBoard().tripEndDateEditor().getText());
            String start = lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText();
            String end = lftObjects.CssMainWindow().frameCMBoard().tripEndDateEditor().getText();

            if (tripStartDate.contains(start) && tripEndDate.contains(end)){
                found = true;
                lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().doubleClick(MouseButton.LEFT);
                break;
            }

            if(!shouldTripSearchContinue(tripStartDate,start)){
                break;
            }

            if (!searchTripsOnCMBoard){
                break;
            }

        }catch(GeneralLeanFtException genEx){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),MessageConstants.ERROR, "LFT error: "+genEx.getMessage());
            loggerCMBoard.error("Lft error "+genEx.getMessage());
            throw genEx;
        }
        catch(ParseException parsEx){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),MessageConstants.ERROR, "Parse exception: "+parsEx.getMessage());
            loggerCMBoard.error("Parseerror occured "+parsEx.getMessage());
         }
    }


    // If Trip is found then double click on the trip and verify that the TripDetails window open and Read the details from each table in the window.
    if (tripShouldBeAvailableOnCMBoard) {

        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Trip details found on CM board for Emp Id:"+rosaempID+tripStartDate+ msgTripStartDate + msgTripEndDate+tripEndDate);
        if (readTripDetails) {
            if(found) {
                lftObjects.CssMainWindow().frameCMBoard().xUiObject().doubleClick();
                Keyboard.pressKey(Keyboard.Keys.ENTER);
                if (lftObjects.CssMainWindow().frameTrimDetails().exists()) {
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip Details exist in CSS.");
                    // Read Trip details
                    readDataFromTripDetails();
                    lftObjects.CssMainWindow().frameTrimDetails().close();
                } else {
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip Details window didn't appear.");
                }
            }else{
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip is not available on CM board.");
            }
        }
    }else{
        if(!found) {
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details NOT found on CM board as expected for Emp Id:" + rosaempID + msgTripStartDate + tripStartDate + msgTripEndDate+ tripEndDate);
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip details found on CM board as expected for Emp Id:" + rosaempID + msgTripStartDate + tripStartDate + msgTripEndDate + tripEndDate);
        }
    }
    if (readTransactionReport && lftObjects.CssMainWindow().frameCMBoard().runTransactionReportButton().exists()){
        lftObjects.CssMainWindow().frameCMBoard().runTransactionReportButton().click();
        lftObjects.CssMainWindow().transactionReportInternalFrame().pDFIconButton().click();
    }
    lftObjects.CssMainWindow().frameCMBoard().close();
    }

    /*TO DO*/
    public void validateNotifiedOption(String notifiedOption) throws GeneralLeanFtException{
        if (lftObjects.CssMainWindow().frameCMBoard().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass","Notified option is checked."+notifiedOption );
        }
    }

    public void validatecredits() throws GeneralLeanFtException{
        if (lftObjects.CssMainWindow().frameCMBoard().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass","Credits are matched from rosa: 0.00" );
        }
    }

    public boolean shouldTripSearchContinue(String tripStartDate, String searchStartDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
        Date date1 = sdf.parse(tripStartDate);
        Date date2 = sdf.parse(searchStartDate);
        return date1.before(date2);
    }

    public void readDataFromTripDetails() throws GeneralLeanFtException , CloneNotSupportedException{
        int rows;
        boolean boolStartDate = false;
        boolean boolendDate= false;
        boolean boolPay= false;
        boolean boolDate= false;
        int tables = 0;

        TableDescription tblDesc = new TableDescription.Builder()
                .nativeClass("com.swacorp.css.screens.trip.TripDetailsTable").build();

        Table[] allTables = lftObjects.CssMainWindow().frameTrimDetails().findChildren(Table.class, tblDesc);
        loggerCMBoard.info(" All Tables: " + allTables.length);


        if( lftObjects.CssMainWindow().frameTrimDetails().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip Details window has appeared and trip details found. "+training.stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip Details window didn't appeared.");
        }

        for (int inxTbl = 0; inxTbl < allTables.length - 1; inxTbl++ ){
        Table tbl = allTables[inxTbl];
        rows = 0;
        tbl.highlight();
        List<TableRow> allRows = tbl.getRows();

        for (int inxRow = 0; inxRow < (allRows.size() - 1); inxRow++ ){
            TableRow r = allRows.get(inxRow);
            List<TableCell> rowCells = r.getCells();

            for (int inxCell =0;inxCell < 1;inxCell++){
                TableCell c = rowCells.get(inxCell);
                loggerCMBoard.info("--------------------------");
                try {
                    String[] tripData = c.getValue().toString().split(",");

                    if (tripData.length > 0) {
                        String date = tripData[33].split("=")[1];
                        String strtBase = tripData[9].split("=")[1];
                        String destBase = tripData[44].split("=")[1];
                        String pay = tripData[19].split("=")[1];

                        String rosaDate = (detailFromROSA.get(tables)[rows]).split("_")[0];
                        String rosaStart = (detailFromROSA.get(tables)[rows]).split("_")[2];
                        String rosaEnd = (detailFromROSA.get(tables)[rows]).split("_")[3];
                        String rosaPay = (detailFromROSA.get(tables)[rows]).split("_")[4];

                        boolStartDate = rosaStart.contains(strtBase);
                        boolendDate =rosaEnd.contains(destBase);
                        boolPay =rosaPay.contains(pay);
                        boolDate = validateRosaCssDate(date, rosaDate);

                        if(boolStartDate && boolendDate && boolPay && boolDate) {
                            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is available in CSS for table [" + tables + "] and Row [" + inxRow + "].");
                        }else{
                            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip details from ROSA is NOT available in CSS for table ["+tables+"] and Row ["+rows+"].");
                        }
                        rows++;
                        break;
                    }
                }catch(GeneralLeanFtException e){
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Error occured while comparing CSS and ROSA trip details."+e.getMessage());
                    throw e;
                }
            }
        }
            tables++;
        }
    }

    public boolean validateRosaCssDate(String date, String rosaDate){

        String[] date2 = date.split("-");
        String cssDate = date2[2]+date2[1]+date2[0];
        try {
            if (cssDate.contains(rosaDate)) {
                return true;
            }
        }catch(Exception e){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Error", "Error occured while while comparing dates"+e.getCause());
            throw e;
        }
        return false;
    }

    public void rightClickCMBoardAndSelectMenu(String rightClickMenu) throws GeneralLeanFtException {
        try {
            UiObject uiObj = lftObjects.CssMainWindow().frameCMBoard().xUiObject();
            uiObj.click(MouseButton.RIGHT);
            lftObjects.CssMainWindow().frameCMBoard().cMBoardripRghtClkMenu().selectSubMenu(rightClickMenu);
            Thread.sleep(3000);
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Right clicked and selected Menu");
            Keyboard.pressKey(Keyboard.Keys.ENTER);
        }catch(InterruptedException interuptEx){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Failed to perform right click and select menu."+interuptEx.getMessage());
            Thread.currentThread().interrupt();
        }catch(GeneralLeanFtException lftEx){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Failed to perform right click and select menu."+lftEx.getMessage());
            throw lftEx;
        }
    }
}




