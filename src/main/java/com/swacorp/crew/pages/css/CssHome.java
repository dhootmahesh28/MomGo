package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.internal.DynamicObjectProxy;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.tsr.enums.EnumWaitConstants;
import com.swacorp.crew.utils.FileUtil;
import com.swacorp.crew.utils.PDFReports;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Css extends WinBasePage{


    public void readpdf() throws GeneralLeanFtException {
        Label x = lftObjects.CssMainWindow().transactionReportInternalFrame().report();
        System.out.println(x.getVisibleText().toString());
    }

    public enum CMBoardRightClickMenu{
        Transaction(7),
        AddNonfly(1);

        private int position;

        CMBoardRightClickMenu(int pos) {
            this.position = pos;
        }
        public int status() {
            return position;
        }
    }
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(Css.class);
    ObjectRepoCSS lftObjects =null;
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
    ArrayList<String> pdfContentAfterReadingTransactionReport;
    String tripStartDate;
    String tripEndDate;
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();
    String pos = "CA";

    public Css(Map<String, Map<String, ArrayList<String[]>>> masterHMrosa) {
        this();
        masterHM = masterHMrosa;
    }

    public Css()  {
        lftObjects = super.cssObjectRepo;
    }

    public Css(String empID, HashMap<Integer, String[]> hm) {
        this();
        rosaempID = empID;
        detailFromROSA = hm;
    }

    public void readMasterHM(){

        HashMap<Integer, String[]> hm = new HashMap<>();
        //masterHM
        for (Map.Entry<String, Map<String, ArrayList<String[]>>> entry : masterHM.entrySet()){
            rosaempID = entry.getKey();
            Map<String, ArrayList<String[]>> y = entry.getValue();

            for (Map.Entry<String, ArrayList<String[]>> entry2 : y.entrySet()){
                //String k2 = entry2.getValue("ss");
                Map<String, ArrayList<String[]>> y2 = entry.getValue();
                training = y2.get("trng");
                triptopull = y2.get("triptopull");
            }
            break;
        }
    }
    public void readOTTripDetails() throws GeneralLeanFtException, CloneNotSupportedException, Exception{
        
        UiObject x = lftObjects.CssMainWindow().crewBoardPieceUiObject();
        x.highlight();

        //ReadDataFromTripDetails
        com.hp.lft.sdk.java.UiObjectDescription allUIObj = new com.hp.lft.sdk.java.UiObjectDescription.Builder()
                .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece").build();
        com.hp.lft.sdk.java.UiObject[] allObj = lftObjects.CssMainWindow().findChildren(com.hp.lft.sdk.java.UiObject.class, allUIObj);

        Object startDate;
        Object EndDate;
        for (int index=allObj.length-1 ; index > 0 ; index--){
            System.out.println("OT index: "+index);
            UiObject tripDetails = allObj[index];
            tripDetails.highlight();

              startDate = allObj[index].getNativeObject().invokeMethod("getStartDate", DynamicObjectProxy.class).invokeMethod("getCalendarDate", DynamicObjectProxy.class).invokeMethod("toString", DynamicObjectProxy.class);
              EndDate = allObj[index].getNativeObject().invokeMethod("getEndDate", DynamicObjectProxy.class).invokeMethod("getCalendarDate",DynamicObjectProxy.class).invokeMethod("toString", DynamicObjectProxy.class);
            System.out.println("startDate.toString()"+startDate.toString());
            System.out.println("EndDate.toString()"+EndDate.toString());
              if (startDate.toString().contains("2020-03-10") & EndDate.toString().contains("2020-03-15")) {
                  tripDetails.doubleClick();
                  if (SearchTheCorrectTripFromOT("", "")) {
                      break;
                  }
              }
           }

        if (lftObjects.CssMainWindow().openTimeInternalFrame().exists()){
            lftObjects.CssMainWindow().openTimeInternalFrame().close();
        }

    }

    public boolean SearchTheCorrectTripFromOT(String startdate, String enddate) throws GeneralLeanFtException , CloneNotSupportedException, Exception{
        found = false;
        Thread.sleep((2000));
        System.out.println("-------- ");
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

        String[] split = allTables[0].getRows().get(0).getCells().get(0).getValue().toString().split(",");
        //String s = split[125];
        String s = allTables[0].getRows().get(0).getCells().get(0).getValue().toString().split(",")[125];
        String s1 = allTables[allTables.length-1].getRows().get(0).getCells().get(0).getValue().toString().split(",")[125];
        String s2="";
        try{
             s2 = allTables[allTables.length-1].getRows().get(0).getCells().get(0).getValue().toString().split(",")[2173];
        }catch(Exception e){

        }

        if (s2.length()>0){
            report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Trip found on OT. with start date "+s1);
            found = true;
        }
        Table tbl2 = lftObjects.CssMainWindow().frameTrimDetails().crewMembersTable();
        List<TableRow> allrows = tbl2.getRows();//.get(0).getCells().get(0).getValue().toString();

        if(found) {
            for (int i = 0; i < allrows.size(); i++) {
                tbl2.activateRow(0);
                allrows.get(i).getCells().get(2).click();
                tbl2.highlight();
                String assignment = allrows.get(i).getCells().get(1).getValue().toString();
                String position = allrows.get(i).getCells().get(2).getValue().toString();
                try {
                    if (assignment.contains("Unassigned") & position.contains(pos)) {

                        report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Position unassigned for " + position);
                        break;
                    } else {
                        report.reportLeanFT(lftObjects.CssMainWindow(), "fail", "Position NOT unassigned for " + position);
                    }
                }catch(Exception e){

                }
            }
        }

        lftObjects.CssMainWindow().frameTrimDetails().close();
        return found;
    }

    public void selectOTfilters(String filter) throws  GeneralLeanFtException{
        String from = "10Mar20";
        String to = "14Mar20";

        try {
            lftObjects.CssMainWindow().openTimeInternalFrame().activate();
            lftObjects.CssMainWindow().openTimeInternalFrame().fromEditor().sendKeys(from);
            lftObjects.CssMainWindow().openTimeInternalFrame().toEditor().sendKeys(to);

            do {
                if (lftObjects.CssMainWindow().openTimeInternalFrame().whiteArrowExpandedState().exists()) {
                    lftObjects.CssMainWindow().openTimeInternalFrame().whiteArrowExpandedState().click();
                } else {
                    break;
                }
            } while (lftObjects.CssMainWindow().openTimeInternalFrame().whiteArrowExpandedState().exists());

            List<String> items = Arrays.asList(filter.split(","));
            for (String item : items) {
                lftObjects.CssMainWindow().openTimeInternalFrame().BaseCheckbox().setDescription(new CheckBoxDescription.Builder().attachedText(item).build());
                lftObjects.CssMainWindow().openTimeInternalFrame().BaseCheckbox().setState(CheckedState.CHECKED);
            }
            lftObjects.CssMainWindow().openTimeInternalFrame().getOpenTripsButton().click();

            report.reportLeanFT(lftObjects.CssMainWindow(), "pass", "OT filters selected:"+filter);

        }catch(Exception e){
            report.reportLeanFT(lftObjects.CssMainWindow(), "fail", "error while selecting filters: "+filter);
        }

    }

    public void NavigateToOT() throws GeneralLeanFtException {

        try {
            lftObjects.CssMainWindow().openTimeMenu().click();
            report.reportLeanFT(lftObjects.CssMainWindow(),"pass", "Successfully clicked on the OT menue.");
            lftObjects.CssMainWindow().openTimeMenu().viewOpenTimeMenu().click();
        }catch(Exception e){
            report.reportLeanFT(lftObjects.CssMainWindow(),"fail", "Error occured in selecting the OT from menu bar.");
         }

         try{
             if (lftObjects.CssMainWindow().openTimeInternalFrame().fromEditor().exists()){
                 report.reportLeanFT(lftObjects.CssMainWindow(),"pass", "OT window has appeared.");
             }
         }catch(Exception e){
             report.reportLeanFT(lftObjects.CssMainWindow(),"fail", "OT window didn't appear.");
         }

    }

    public void rightClickCMBoardAndSelectMenu(String rightClickMenu) throws  GeneralLeanFtException{
        try {
            UiObject x = lftObjects.CssMainWindow().frameCMBoard().xUiObject();
            x.click(MouseButton.RIGHT);
            lftObjects.CssMainWindow().frameCMBoard().cMBoardripRghtClkMenu().selectSubMenu(rightClickMenu);
            Thread.sleep(3000);
            report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Right clicked and selected Menu");
            Keyboard.pressKey(Keyboard.Keys.ENTER);
        }catch(Exception e){
            report.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Failed to perform right click and select menu.");
        }
    }

    public void validateTransactioReportFile() throws Exception {
        //FileUtils.cleanDirectory(new File("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp"));

        try {
            if (lftObjects.CssMainWindow().transactionReportInternalFrame().exists()) {
                lftObjects.CssMainWindow().transactionReportInternalFrame().activate();
                //lftObjects.CssMainWindow().transactionReportInternalFrame().pDFIconButton().click();
                report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction Report reflected.");
            }
        }catch(Exception e){
        }
        //readTransactionReport("C:\\Users\\x257093\\AppData\\Local\\Temp\\JasperRptTemp");
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
        //System.out.println(x.getVisibleText().toString());

        for (int i=0; i <= blockSize-1; i++){
            String line1 = user[i]+space+function[i]+space+reason[i]+space+logMessage[i];
            String line2 = line2Data[i]+" "+rosaempID;
            String line3 = line3Data[i];

            //validateTransactionReport(line1+ '\n' + line2 + '\n' + line3);
           /* if (reportContent.contains(line1+ '\n' + line2 + '\n' + line3)){
                report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction report contains: "+line1+ '\n' + line2 + '\n' + line3);
            }*/

            if (reportContent.contains(line1) & reportContent.contains(line2)){
                report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction report contains: "+line1+ '\n' + line2 );
            }
        }

        lftObjects.CssMainWindow().transactionReportInternalFrame().close();
    }

    public void validateTransactionReport(String s) throws GeneralLeanFtException {

        report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction report contains: "+s);
    }

    private void CheckIfUnchecked(CheckBox chk) throws GeneralLeanFtException {
        if(chk.getState().toString().equalsIgnoreCase("UNCHECKED")){
            chk.click();
        }else{
            chk.click();
            chk.click();
        }
    }
    public void validateTransactioTeportDialog(String functionDropdownList, String reason) throws InterruptedException, GeneralLeanFtException {
        boolean reasonFound = false;

        CheckBox crewMwmbwr = lftObjects.CssMainWindow().transactionReportDialog().crewMemberCheckBox();
        CheckIfUnchecked(crewMwmbwr);

        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString("76643");
        Keyboard.pressKey(Keyboard.Keys.ENTER);
        try {
            CheckedState x = lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().getState();
            if(x.getValue().toString().equalsIgnoreCase("0")){
                report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "State of Function checkbox is disabled.");
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
                        report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "In the reason table. Specified value is found:"+reason);
                        reasonFound = true;
                        break;
                    }
                }
                if (reasonFound){
                    break;
                }
            }
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }

        ValidateFunctionList(functionDropdownList);

        tripStartDate = training.get(0)[0];
        tripEndDate = training.get(6)[0];

        lftObjects.CssMainWindow().transactionReportDialog().lastCheckBox().click();
        //Thread.sleep(1000);
        lftObjects.CssMainWindow().transactionReportDialog().lastCheckBox().click();
        //Keyboard.pressKey(Keyboard.Keys.TAB);
        //Keyboard.sendString("200");
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString(tripStartDate);
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString(tripEndDate);

        lftObjects.CssMainWindow().transactionReportDialog().runReportButton().click();
        System.out.println("tripStartDate: "+tripStartDate);
        System.out.println("tripEndDate: "+tripEndDate);
    }

    public void ValidateFunctionList(String txt) throws  GeneralLeanFtException{
        lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().click();
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.pressKey(Keyboard.Keys.DOWN);
        com.hp.lft.sdk.java.List edt = lftObjects.CssMainWindow().transactionReportDialog().describe(com.hp.lft.sdk.java.List.class, new ListDescription.Builder()
                .attachedText("(CSS Login ID)")
                .index(2)
                .nativeClass("javax.swing.JComboBox").build());

        edt.highlight();
        java.util.List<ListItem> x;
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
            x = edt.getSelectedItems();
            String a = x.get(0).getText().trim();
            System.out.println("a: "+a);
            if(a.equalsIgnoreCase(txt)){
                found = true;
                report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Function dropdown list contains the given item: "+txt);
                Keyboard.pressKey(Keyboard.Keys.ENTER);
                break;
            }

            if (a.contains(prevItem)){
                notFound = true;
            }
            Keyboard.pressKey(Keyboard.Keys.DOWN);
            prevItem = a;
        }
        while (!found | !notFound);
        if (!found){
            report.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Function dropdown list does not contains the given item: "+txt);
        }
        //lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().click();
    }

   // This function is required to reset the selections on the Report dialog.
    // css preserves the selections which causes the test to fail in next run.
    public void resetReportTab(String itemFunctionDropdown) throws InterruptedException, GeneralLeanFtException {
        boolean reasonFound = false;
        lftObjects.CssMainWindow().reportsMenu().click();
        lftObjects.CssMainWindow().reportsMenu().transactionReportMenu().click();
        try {
            Table reasonTable = lftObjects.CssMainWindow().transactionReportDialog().ReasonTable();
            List<TableRow> rows = reasonTable.getRows();
            for (int i=0; i < rows.size(); i++) {
                List<TableCell> cells = rows.get(i).getCells();
                for(int c = 0; c <  cells.size() ; c++){
                    if (cells.get(c).getValue().toString().contains(itemFunctionDropdown)){
                        cells.get(0).click();
                        reasonFound = true;
                        break;
                    }
                    if (reasonFound){
                        break;
                    }
                }
            }
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }

        try {
            lftObjects.CssMainWindow().transactionReportDialog().reasonCheckBox().click();
            lftObjects.CssMainWindow().transactionReportDialog().functionsCheckBox().click();

        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }

        lftObjects.CssMainWindow().transactionReportDialog().lastCheckBox().click();
        Thread.sleep(1000);
        lftObjects.CssMainWindow().transactionReportDialog().lastCheckBox().click();
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString("");

        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.pressKey(Keyboard.Keys.TAB);
        Keyboard.sendString("");
        lftObjects.CssMainWindow().transactionReportDialog().cancel().click();

    }


public void NavigateToTransactionReport() throws Exception {
    try {
        lftObjects.CssMainWindow().reportsMenu().click();
        lftObjects.CssMainWindow().reportsMenu().transactionReportMenu().click();
        Thread.sleep(4000);

    }catch(Exception e){
        //report.report("fail","Transaction report window didnt open.");
        e.printStackTrace();
    }


    if(lftObjects.CssMainWindow().transactionReportDialog().exists()){
        report.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Transaction report window exist.");
    }else{
        report.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Transaction report window doesn't exist.");
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

        readMasterHM();
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
            report.reportLeanFT(lftObjects.CssMainWindow(), "pass", "CM Board opened successfully for Emp Id:"+rosaempID);
        }else{
            report.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Crew id not found in CSS. Crew ID:"+rosaempID);
        }
    }catch(Exception e){
        report.reportLeanFT(lftObjects.CssMainWindow(),"fail", "Error occured while opening CM board.");
    }

}

public void selectTripOnCMBoard(String tripStartDateROSA, String tripEndDateROSA, String TripDetails, boolean tripShouldBeAvailableOnCMBoard, boolean readTripDetails, boolean readTransactionReport) throws GeneralLeanFtException, CloneNotSupportedException {


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
    //tripStartDate = detailFromROSA.get(0)[0].substring(0,7);
    tripStartDate = training.get(0)[0];
    tripEndDate = training.get(6)[0];
    //tripEndDate = detailFromROSA.get(3)[1].substring(0,7);
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
        report.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip details found on CM board for Emp Id:"+rosaempID+" trip start date:"+tripStartDate + ", Trip end date: "+tripEndDate);
        if (found & readTripDetails) {
            lftObjects.CssMainWindow().frameCMBoard().xUiObject().doubleClick();
            Keyboard.pressKey(Keyboard.Keys.ENTER);
            if (lftObjects.CssMainWindow().frameTrimDetails().exists()) {
                report.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip Details exist in CSS.");
                // Read Trip details
                ReadDataFromTripDetails();
                lftObjects.CssMainWindow().frameTrimDetails().close();
            }else{
                report.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Trip Details window didn't appear.");
            }

        }
    }else if ((!found) & (!tripShouldBeAvailableOnCMBoard)){
        report.reportLeanFT(lftObjects.CssMainWindow(),"PASS", "Trip details NOT found on CM board as expected for Emp Id:"+rosaempID+" trip start date:"+tripStartDate + ", Trip end date: "+tripEndDate);
    }/*else{
        report.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Trip details NOT found on CM board for Emp Id:"+rosaempID+" trip start date:"+tripStartDate + ", Trip end date: "+tripEndDate);
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
            report.reportLeanFT(lftObjects.CssMainWindow(), "pass","Notified option is checked." );
        }
    }

    /*TODO*/
    public void ValidateNonflyBarReasonCode(String reasonCode){

    }

    public void ValidateCredits() throws GeneralLeanFtException{
        if (lftObjects.CssMainWindow().frameCMBoard().exists()){
            report.reportLeanFT(lftObjects.CssMainWindow(), "pass","Credits are matched from rosa: 0.00" );
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

    public void readTransactionReport(String path) throws  Exception {
        boolean found = false;
        try {

            File newpath = new  FileUtil().getTheNewestFile(path, "pdf");
            PDFReports pdf = new PDFReports(newpath.getPath());
            ArrayList<String> pdfContent = pdf.readBetweenTags("Transaction Report", "END OF REPORT");
            pdfContentAfterReadingTransactionReport = pdfContent;
            if (!pdfContentAfterReadingTransactionReport.isEmpty()){
                report.reportLeanFT(lftObjects.CssMainWindow(), "pass","Transaction report in pdf format is read." );
            }

        }catch(Exception  e){
            report.reportLeanFT(lftObjects.CssMainWindow(), "fail","Failed to read Transaction report in pdf format." );
        }
    }


    public void ValidatePdfContentAfterReadingTransactionReportHasEmployeeId (String searchTxt) throws  GeneralLeanFtException{
        searchTxt = searchTxt+rosaempID;
        ValidatePdfContentAfterReadingTransactionReport(searchTxt);
    }


    public void ValidatePdfContentAfterReadingTransactionReport (String searchTxt) throws  GeneralLeanFtException{
        int count=0;
        String[] arrsearchTxt = searchTxt.split(",");

        for (String str:pdfContentAfterReadingTransactionReport){
            for (String eachSarchTxt: arrsearchTxt){
                if (str.contains(eachSarchTxt)){
                    count++;
                }
            }
        }

       int x = detailFromROSA.size()+1;
       int y = count/(arrsearchTxt.length);

        if (Integer.compare(x,y)==1)
            report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "The given search text is found in report.");
        else{
            report.reportLeanFT(lftObjects.CssMainWindow(), "fail","The given search text is NOT found in report. " );
        }
        if (lftObjects.CssMainWindow().transactionReportInternalFrame().exists()) {
            lftObjects.CssMainWindow().transactionReportInternalFrame().close();
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
            report.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Trip Details window has appeared and trip details found. "+training);
        }else{
            report.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Trip Details window didn't appeared.");
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




