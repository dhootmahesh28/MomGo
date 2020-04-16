package com.swacorp.tsr.rosa;

import com.swacorp.crew.css.Css;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonFormats;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.tsr.sasi.SasiHome;
import com.swacorp.tsr.sasi.SasiLogin;
import com.swacorp.tsr.utils.pdf.PDFReports;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.*;

import com.swacorp.tsr.utils.pdf.FileUtil;
import org.openqa.selenium.interactions.Actions;

public class RosaSolutioQueue extends BasePage {

    private final Logger LOGGER = Logger.getLogger(RosaSolutioQueue.class);
    ReportUtil report = new ReportUtil();
    HashMap<Integer, String[]> hm = new HashMap<>();
    //LinkedHashMap<String, LinkedHashMap<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<String, Map<String, ArrayList<String[]>>>();
    String timeOfCreatingRequest;

    public RosaSolutioQueue() {
    }

    public RosaSolutioQueue(String currentDate){
        timeOfCreatingRequest = currentDate;
    }

    private final By SOLUTION_QUEUE_TITLE = By.xpath("//h2[@class='pilot-queue__title']");
    private final By VIEW_QUEUE_PAGE = By.xpath("//div[text()='View Queue page']");
    private final By VIEW_LINK = By.xpath("(//a[text()='View'])[2]");
    private final By SOLUTION_DETAILS_FIRSTLINK = By.xpath("/html/body/app-root/div[2]/div/div/app-solution-details/div[2]/cl-tabs/div[2]/cl-tab[1]/div/pto-solution-details-table/cl-data-table/cl-data-table-panel[1]/cl-data-table-panel-header/div/cl-data-table-row/cl-data-table-column[2]\n");
    private final By EMPLOYEE_ID = By.xpath("/html/body/app-root/div[2]/div/div/app-solution-details/div[2]/cl-tabs/div[2]/cl-tab[1]/div/pto-solution-details-table/cl-data-table/cl-data-table-panel/cl-data-table-panel-header/div/cl-data-table-row/cl-data-table-column[2]/div");
    private final By DATA_DIV = By.xpath("(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[1]/div[1]/div[@class='grid__row']/div[1]/div");
    private final By FILTER_BUTTON = By.xpath("//*[text()=' Filter ']");
    private final By FILTER_STATUS = By.xpath("//*[text()=' Status ']");
    private final String STATUS_COMMITTED = "//*[text()='PLACEHOLDER']";
    private final By APPLY_BTN = By.xpath("//*[text()=' Apply ']");
    private final By PDF_DOWNLOAD_BUTTON = By.xpath("(//div[@class ='dot-menu' ])[1]");
    private final By DOWNLOAD_BUTTON = By.xpath("//button[text() ='Download' ]");
    private final By ALL_DETAILS = By.xpath("//*[@class='solution-details-table__row'][1]//div[@class = 'cl-data-table-column-item']");

    ArrayList<String> training = null;
    ArrayList<String> TripToPull = null;
    ArrayList<String> empID= null;
    String employeeID;

    String[] intermediateStatus = {"Queued", "Ingest Reviewed"};
    String[] failedStatus = {"Request Failure", "Ingest Failure"};
    String[] passedStatus = {"Committed"};


    public void StatusPollingOfPTORequest(String Category, String Cycle, String Aircraft, String Event, String Month, String Bidline, String CoreConditional, String RequestCreationTime) throws InterruptedException, ParseException {
        String xpathAllRowsFromSolutionQueue = "(//*[@style = 'justify-content: space-between;'])"; //11 rows including the header.
        String xpathAlltheElementsInRow = "(//*[@style = 'justify-content: space-between;'])[" + "PLACEHOLDER" + "]/*/div";
        String xpathSingleElementsInRow;
        List<WebElement> allRowsFromSolutionQueue = getDriver().findElements(By.xpath(xpathAllRowsFromSolutionQueue));
        int rosaOneMinutCounter = 0;

        for (int i = 2; i < allRowsFromSolutionQueue.size() - 1; i++) {
            xpathSingleElementsInRow = xpathAlltheElementsInRow.replace("PLACEHOLDER", "" + i);
            List<WebElement> rowElems = getDriver().findElements(By.xpath(xpathSingleElementsInRow));
            String queuEvent = (rowElems.get(0)).getText();
            String queuBidPeriod = (rowElems.get(1)).getText();
            String queuBidLine = (rowElems.get(2)).getText();
            String queuTime = (rowElems.get(3)).getText();  //getDriver().findElement(By.xpath(xpathSingleElementsInRow.replace("PLACEHOLDER")))
            boolean isTimeDiffWithinpermitiableRange = isTimeWithinAcceptableRange(queuTime, timeOfCreatingRequest);

           if (queuEvent.equalsIgnoreCase(Event) & queuBidLine.equalsIgnoreCase(Bidline) & queuBidPeriod.equalsIgnoreCase(Month)) {
                String xpathstatus = xpathSingleElementsInRow + "//p";
                String status = getDriver().findElement(By.xpath(xpathstatus)).getText();

                //If the tatus is intermediate then wait for some time
                if (Arrays.asList(intermediateStatus).contains(status)) {
                    Thread.sleep(5000);
                    rosaOneMinutCounter++;

                    if (rosaOneMinutCounter < 1) {
                        StatusPollingOfPTORequest(Category, Cycle, Aircraft, Event, Month, Bidline, CoreConditional, timeOfCreatingRequest);
                        report.reportSelenium("info", "The request is in intermediate status after " + rosaOneMinutCounter + " minutes of its creation.");
                    } else {
                        report.reportSelenium("fail", "The request is still in intermediate status even after " + rosaOneMinutCounter + " minutes.");
                        break;
                    }

                } else if (Arrays.asList(failedStatus).contains(status)) {
                    report.reportSelenium("fail", "The request has reached to a failed status: " + status);
                    break;
                } else if (Arrays.asList(passedStatus).contains(status)) {
                    //if (true) {

                        try {

                            readSolutionQueueDetails();
                        } catch (Exception e) {
                            report.reportSelenium("Fail", "Trip to pull or training data does not exist in the solution.");
                            e.printStackTrace();
                        }
            break;
                    }
                    // Reading of all employee done. Hence break the loopm and return
                    //break;
                }
            }
        }

    public Map<String, Map<String, ArrayList<String[]>>> getHM(){
        //masterHM
        return masterHM;
    }

    public void readHM(){
        //masterHM
        for (Map.Entry<String, Map<String, ArrayList<String[]>>> entry : masterHM.entrySet()){
            String k = entry.getKey();
            Map<String, ArrayList<String[]>> y = entry.getValue();

            for (Map.Entry<String, ArrayList<String[]>> entry2 : y.entrySet()){
                //String k2 = entry2.getValue("ss");
                Map<String, ArrayList<String[]>> y2 = entry.getValue();
                ArrayList<String[]> aa = y2.get("trng");
                ArrayList<String[]>  bb = y2.get("triptopull");
            }
            break;
        }
    }

    /*If the time fidderence between the system date and the one that is pickes up from t
    he solution queue is more than 1 minut.
    then this is nt the right row. hence return false.ParseException
    if difference is within 1 min range then its the right row.*/
    public boolean isTimeWithinAcceptableRange(String qTime, String sysTime) throws ParseException{
       long diffSecs, diffMins;
       try {
           diffSecs = new DateUtil().getTimeDiff(qTime, sysTime, CommonFormats.ROSAFormat);
           diffMins = diffSecs / 60;
           if (diffMins > 1) {
               return false;
           } else {
               return true;
           }
       }catch(Exception e){
            e.printStackTrace();
           return false;
           }
    }

    public void readSolutionQueueDetails() throws Exception {
        String training = "(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[PLACEHOLDER]/div[1]/div[@class='grid__row']/div[1]/div";
        String triptopull = "(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[PLACEHOLDER1]/div[PLACEHOLDER2]//div[@class = 'grid__column-8/12']/div";
        int x = 1;
        int rowIndx = 0;
        waitForElement(VIEW_LINK);
        ArrayList<String> list = new ArrayList<>();
        Thread.sleep(5000);
        if (isElementPresent(VIEW_LINK)) {
            buttonClickIfExist(VIEW_LINK);
        }
        Thread.sleep(6000);
        List<WebElement> listCrewIdRows = getDriver().findElements(ALL_DETAILS);

        do {
            WebElement ele = listCrewIdRows.get(x);
            list.add(rowIndx, ele.getText());
            rowIndx++;
            x = x+7;
        } while (x < listCrewIdRows.size());

        try{
            for (int i = 0; i < list.size() ; i++){
            LinkedHashMap<String, ArrayList<String[]>> trngHM2= new LinkedHashMap<>();

            ArrayList<String[]> list1 = new ArrayList<>();
            ArrayList<String[]> list2 = new ArrayList<>();

            String xpathId = "//*[text()='"+list.get(i)+"']";
            getDriver().findElement(By.xpath(xpathId)).click();
            report.reportSelenium("info","Reading row number: "+i);
            Thread.sleep(3000);
            String fullxpathTraining = training.replace("PLACEHOLDER",""+(2*i+1));
            String fullxpathTripToPullTemp = triptopull.replace("PLACEHOLDER1",""+(2*i+2));
            String fullxpathTripToPull1 = fullxpathTripToPullTemp.replace("PLACEHOLDER2",""+2);
            String fullxpathTripToPull2 = fullxpathTripToPullTemp.replace("PLACEHOLDER2",""+3);
            List<WebElement> trng = getDriver().findElements(By.xpath(fullxpathTraining));

            int rowNumber = 0;
            for (int t=0; t<trng.size();t++ ){
                WebElement eleTrng = trng.get(t);

                List<WebElement> elements = eleTrng.findElements(By.xpath("./*"));
                for (int j = 0; j < elements.size() - 1; j++) {
                    WebElement e = elements.get(j);
                    list1.add(rowNumber,e.getText().toString().split("\n"));
                    rowNumber++;
                }
            }

            List<WebElement> ttp = getDriver().findElements(By.xpath(fullxpathTripToPull1));
            int rowTripToPull = 0;
            for (int t=0; t<ttp.size();t++ ){
                WebElement eleTrng = ttp.get(t);
                List<WebElement> elements = eleTrng.findElements(By.xpath("./*"));
                for (int j = 0; j < elements.size() - 1; j++) {
                    WebElement e = elements.get(j);
                    list2.add(rowTripToPull,e.getText().toString().split("\n"));
                    rowTripToPull++;
                }
            }

            List<WebElement> ttp2 = getDriver().findElements(By.xpath(fullxpathTripToPull2));
            if (ttp2.size() > 0){
                for (int t=0; t<ttp2.size();t++ ){
                    WebElement eleTrng = ttp2.get(t);

                    List<WebElement> elements = eleTrng.findElements(By.xpath("./*"));
                    for (int j = 0; j < elements.size() - 1; j++) {
                        WebElement e = elements.get(j);
                        list2.add(rowTripToPull,e.getText().toString().split("\n"));
                        rowTripToPull++;
                    }
                }
            }

            trngHM2.put("trng", list1);
            trngHM2.put("triptopull", list2);
            masterHM.put(list.get(i),trngHM2);
            getDriver().findElement(By.xpath(xpathId)).click();
            Thread.sleep(4000);
        }
        }catch(Exception e){
            report.reportSelenium("Fail", "Error occured while reading the row.");
            e.printStackTrace();
        }
    }

    public Css  NavigateToCSSWithMasterDS(){
        try {
            if (masterHM.size() > 0) {
                report.reportSelenium("pass", "ROSA part of the flow is successful, now navigating to CSS. Total number of employeeIDs read from rosa is: "+masterHM.size());
                return new Css(masterHM);
            }else{
                report.reportSelenium("fail", "ROSA part of the flow is completed, but Training or Trip to pull data could not be read");
                return new Css(masterHM);
            }
        }catch(Exception e){
            report.report("fail", "Navigation to CSS window failed.");
            return null;
        }
    }

    public SasiLogin NavigateToSasiWithMasterDS(){
        try {
            if (masterHM.size() > 0) {
                report.reportSelenium("pass", "ROSA part of the flow is successful, now navigating to SASI. Total number of employeeIDs read from rosa is: "+masterHM.size());
                return new SasiLogin(masterHM);
            }else{
                report.reportSelenium("fail", "ROSA part of the flow is completed, but Training or Trip to pull data could not be read");
                return new SasiLogin(masterHM);
            }
        }catch(Exception e){
            report.report("fail", "Navigation to CSS window failed.");
            return null;
        }
    }

    public Css  NavigateToCSSForValidation(){
        try {
            report.reportSelenium("pass", "ROSA part of the flow is successful, now navigating to CSS.");
            return new Css(employeeID, hm);
        }catch(Exception e){
            report.report("fail", "Navigation to CSS window failed.");
            return null;
        }
    }

    public void clickDownloadPdf(int index) throws Exception{
        waitUntilElementClickable(PDF_DOWNLOAD_BUTTON);
        buttonClick(PDF_DOWNLOAD_BUTTON);
        WebElement webElement =  getDriver().findElement((PDF_DOWNLOAD_BUTTON));
        Actions builder = new Actions( getDriver());
        builder.moveToElement(webElement).click(webElement);
        builder.perform();

        buttonClickIfExist(PDF_DOWNLOAD_BUTTON);
        buttonClickIfExist(DOWNLOAD_BUTTON);

        Thread.sleep(5000);

        FileUtil pdf = new FileUtil();
        String pdfFilePAth = pdf.getTheNewestFile("C:/Users/x257093/Downloads","pdf").toString();
        printConsole("Latest pdf file downloaded: "+pdfFilePAth);
        PDFReports dfreports = new PDFReports(pdfFilePAth);
        training = dfreports.readBetweenTags("TRAINING","TRIP TO PULL");
        printConsole("training :"+training);

        TripToPull = dfreports.readBetweenTags("TRIP TO PUL", "TRAINING");
        printConsole("TripToPull :"+TripToPull);
        empID = dfreports.readBetweenTags("Solutions", "TRAINING");
        printConsole("empID :"+empID);
        employeeID = parseEmpID(empID);
    }

    private String parseEmpID(ArrayList<String> empID){
        String emp = empID.get(0).toString();
        String employeeID = emp.substring(10,16);
        printConsole("employeeID: "+employeeID);

    return employeeID;
    }

    public void latestFile() throws Exception{
    FileUtil pdf = new FileUtil();
    printConsole(pdf.getTheNewestFile("C:/Users/x257093/Downloads","pdf").toString());

}


    public void selectFilterOption(String Filter) throws InterruptedException {

        try {
            waitUntilElementClickable(FILTER_BUTTON);
            waitForElement(FILTER_BUTTON);
            buttonClick(FILTER_BUTTON);
            buttonClickIfExist(FILTER_STATUS);
            //buttonClickIfExist(STATUS_COMMITTED);
            buttonClickIfExist(By.xpath(STATUS_COMMITTED.replace("PLACEHOLDER",Filter )));
            buttonClickIfExist(APPLY_BTN);
            report.reportSelenium("pass", "Filter button clicked successful .");
        }catch(Exception e){
            report.reportSelenium("fail", "Navigation failed.");
        }

    }

    public void clickOnFirstViewlink(int position){
        buttonClickIfExist(VIEW_LINK);
    }

    public void veryfySolutionQueueExists(){
        waitForElement(SOLUTION_QUEUE_TITLE);
        if (isElementVisible(SOLUTION_QUEUE_TITLE)){
            report.reportSelenium("Pass", "Solution Queue is visible.");
            printConsole("veryfySolutionQueueExists passed");

        }else {
            report.reportSelenium("fail", "Solution Queue is NOT visible.");
            printConsole("veryfySolutionQueueExists failed");
        }
    }

    public void checkDuplicateRequestExist(){
        try {
            waitForElement(VIEW_QUEUE_PAGE);
            if(isElementPresent(VIEW_QUEUE_PAGE)) {
                report.reportSelenium("info", "Duplicate solution exists in the queue.");
                buttonClickIfExist(VIEW_QUEUE_PAGE);
            }else{
                report.reportSelenium("info", "Solution is unique, duplicate solution doesn't exist.");
            }
        }catch(Exception e){
            report.reportSelenium("info", "Duplicate solution exist in the queue.");
        }
    }

    public void validateCompleteStatus(){

        buttonClick(SOLUTION_DETAILS_FIRSTLINK);

        employeeID = getDriver().findElement(EMPLOYEE_ID).getText();
        printConsole("empid: "+employeeID);
        List<WebElement> divList = getDriver().findElements(DATA_DIV);
        report.reportSelenium("Pass", "Data is successfully read after expanding the solution details.");
        for (int i = 0; i < divList.size(); i++) {
            WebElement ele = divList.get(i);
            String[] arrStr = new String[10];
            
            List<WebElement> elements = ele.findElements(By.xpath("./*"));
            for (int j = 0; j < elements.size() - 1; j++) {
                WebElement e = elements.get(j);
                e.click();
                arrStr[j] = e.getText().toString().replace("\n", "_");
                hm.put(i,arrStr);
            }
        }
    }
}
