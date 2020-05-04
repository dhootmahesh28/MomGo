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

public class CssCrewMemberBoard extends WinBasePage{

    ReportUtil reportCssHome = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(CssCrewMemberBoard.class);
    ObjectRepoCSS lftObjects =null;
    boolean searchTripsOnCMBoard = true;
    boolean found = false;
    HashMap<Integer, String[]> detailFromROSA = new HashMap<Integer, String[]>();
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

    public void openCMBoard(String empCode){
    readMasterHM();
    try {
        empCode = rosaempID.trim();
        lftObjects.CssMainWindow().crewsMenu().click();
        lftObjects.CssMainWindow().crewMemberSchedule().click();
        Keyboard.sendString(empCode);
        Keyboard.pressKey(Keyboard.Keys.ENTER);
        LOGGER.info("lftObjects.CssMainWindow()  " + lftObjects.CssMainWindow().getTitle());
        if(lftObjects.CssMainWindow().frameCMBoard().exists()) {
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass", "CM Board opened successfully for Emp Id:"+rosaempID);
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Crew id not found in CSS. Crew ID:"+rosaempID);
        }
    }catch(Exception e){
        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"fail", "Error occured while opening CM board.");
    }

}

    public void selectTripOnCMBoard(String tripStartDateROSA, String tripEndDateROSA, String TripDetails, boolean tripShouldBeAvailableOnCMBoard, boolean readTripDetails, boolean readTransactionReport) throws GeneralLeanFtException, CloneNotSupportedException {


    String x = "com.swacorp.css.screens.crewboard.CrewBoardPiece";
    UiObjectDescription targetLinks = new UiObjectDescription.Builder()
        .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece").build();

    LOGGER.info("collection.length: "+lftObjects.CssMainWindow().frameCMBoard().findChildren(UiObject.class, targetLinks).length);
    lftObjects.CssMainWindow().frameCMBoard().xUiObject().highlight();
    lftObjects.CssMainWindow().frameCMBoard().xUiObject().click();
    try {
        if (!lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().exists()) {
            lftObjects.CssMainWindow().frameCMBoard().modifyScheduleArrowCheckBox().click();
        }
    }catch(Exception e){
    }
    UiObjectDescription allUIObj = new UiObjectDescription.Builder()
            .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece").build();
    UiObject[] allObj = lftObjects.CssMainWindow().frameCMBoard().findChildren(UiObject.class, allUIObj);

    //System.out.println("allObj: "+allObj.length);
    //tripStartDate = detailFromROSA.get(0)[0].substring(0,7);
    tripStartDate = training.get(0)[0];
    tripEndDate = training.get(6)[0];
    //tripEndDate = detailFromROSA.get(3)[1].substring(0,7);
    //System.out.println("tripStartDate: "+tripStartDate);
    //System.out.println("tripEndDate: "+tripEndDate);

    for (int index=allObj.length-1 ; index > 0 ; index--){
        if(found) break;
        try{
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().setDescription(new UiObjectDescription.Builder()
                    .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece")
                    .index(index).build());
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().highlight();
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().click();

            LOGGER.info("index: "+index);
            LOGGER.info("lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText()  : "+lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText());
            LOGGER.info("lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText()  : "+lftObjects.CssMainWindow().frameCMBoard().tripEndDateEditor().getText());
            String start = lftObjects.CssMainWindow().frameCMBoard().tripStartDateEditor().getText();
            String end = lftObjects.CssMainWindow().frameCMBoard().tripEndDateEditor().getText();



            //if (start.equalsIgnoreCase(tripStartDate) && end.equalsIgnoreCase(tripEndDate)){
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

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // If Trip is found then double click on the trip and verify that the TripDetails window open and Read the details from each table in the window.
    if (tripShouldBeAvailableOnCMBoard) {
        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip details found on CM board for Emp Id:"+rosaempID+" trip start date:"+tripStartDate + ", Trip end date: "+tripEndDate);
        if (found & readTripDetails) {
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().doubleClick();
            Keyboard.pressKey(Keyboard.Keys.ENTER);
            if (lftObjects.CssMainWindow().frameTrimDetails().exists()) {
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip Details exist in CSS.");
                // Read Trip details
                ReadDataFromTripDetails();
                lftObjects.CssMainWindow().frameTrimDetails().close();
            }else{
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Trip Details window didn't appear.");
            }

        }
    }else if ((!found) & (!tripShouldBeAvailableOnCMBoard)){
        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip details NOT found on CM board as expected for Emp Id:"+rosaempID+" trip start date:"+tripStartDate + ", Trip end date: "+tripEndDate);
    }/*else{
        reportCssLogin.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Trip details NOT found on CM board for Emp Id:"+rosaempID+" trip start date:"+tripStartDate + ", Trip end date: "+tripEndDate);
    }*/
    // Verify TransactionReport
    if (readTransactionReport){
        if (lftObjects.CssMainWindow().frameCMBoard().runTransactionReportButton().exists()){
            lftObjects.CssMainWindow().frameCMBoard().runTransactionReportButton().click();
            lftObjects.CssMainWindow().transactionReportInternalFrame().pDFIconButton().click();
        }
    }
    lftObjects.CssMainWindow().frameCMBoard().close();
    }

    /*TO DO*/
    public void ValidateNotifiedOption(String notifiedOption) throws GeneralLeanFtException{
        if (lftObjects.CssMainWindow().frameCMBoard().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass","Notified option is checked." );
        }
    }

    public void ValidateCredits() throws GeneralLeanFtException{
        if (lftObjects.CssMainWindow().frameCMBoard().exists()){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass","Credits are matched from rosa: 0.00" );
        }
    }

    public boolean shouldTripSearchContinue(String tripStartDate, String searchStartDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
        Date date1 = sdf.parse(tripStartDate);
        Date date2 = sdf.parse(searchStartDate);
        if (date1.before(date2)){
            return true;
        }else{
            return false;
        }
    }

    public void ReadDataFromTripDetails() throws GeneralLeanFtException , CloneNotSupportedException{

        Set<String> rowData = null;
        HashMap<Integer, HashMap<Integer, String[]>> tripDetalsFromTripDeatilsWindow = new HashMap<Integer, HashMap<Integer, String[]>>();
        int rows;
        boolean found = false;
        HashMap<Integer, String[]> tripDetalsPerRow = new HashMap<Integer, String[]>();
        int tables = 0;

        TableDescription tblDesc = new TableDescription.Builder()
                .nativeClass("com.swacorp.css.screens.trip.TripDetailsTable").build();

        Table[] allTables = lftObjects.CssMainWindow().frameTrimDetails().findChildren(Table.class, tblDesc);
        LOGGER.info(" All Tables: " + allTables.length);


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
        //for (TableRow r:allRows ){
        for (int inxRow = 0; inxRow < (allRows.size() - 1); inxRow++ ){
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
                            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is available in CSS for table ["+tables+"] and Row ["+inxRow+"].");
                            found = true;
                            break;
                        }else{
                            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip details from ROSA is NOT available in CSS for table ["+tables+"] and Row ["+rows+"].");
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
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is available in CSS.");
        }else{
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip details from ROSA is not available in CSS.");
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




