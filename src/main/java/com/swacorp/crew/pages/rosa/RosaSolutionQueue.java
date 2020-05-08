package com.swacorp.crew.pages.rosa;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.constants.ApplicationConstantsRosa;
import com.swacorp.crew.pages.constants.CommonFormats;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;

public class RosaSolutionQueue extends BasePage {

    private static final Logger loggerRosaPto = Logger.getLogger(RosaSolutionQueue.class);
    ReportUtil report = new ReportUtil();
    HashMap<Integer, String[]> hm = new HashMap<>();
    public static final Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap();
    String timeOfCreatingRequest;

    public RosaSolutionQueue() {
    }

    public RosaSolutionQueue(String currentDate){
        timeOfCreatingRequest = currentDate;
    }

    private static final By SOLUTION_QUEUE_TITLE = By.xpath("//h2[@class='pilot-queue__title']");
    private static final By VIEW_QUEUE_PAGE = By.xpath("//div[text()='View Queue page']");
    private static final By VIEW_LINK = By.xpath("(//a[text()='View'])[2]");
    private static final By FIRST_VIEW_LINK = By.xpath("(//a[text()='View'])[1]");
    private static final By SOLUTION_DETAILS_FIRSTLINK = By.xpath("/html/body/app-root/div[2]/div/div/app-solution-details/div[2]/cl-tabs/div[2]/cl-tab[1]/div/pto-solution-details-table/cl-data-table/cl-data-table-panel[1]/cl-data-table-panel-header/div/cl-data-table-row/cl-data-table-column[2]\n");
    private static final By EMPLOYEE_ID = By.xpath("/html/body/app-root/div[2]/div/div/app-solution-details/div[2]/cl-tabs/div[2]/cl-tab[1]/div/pto-solution-details-table/cl-data-table/cl-data-table-panel/cl-data-table-panel-header/div/cl-data-table-row/cl-data-table-column[2]/div");
    private static final By DATA_DIV = By.xpath("(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[1]/div[1]/div[@class='grid__row']/div[1]/div");
    private static final By FILTER_BUTTON = By.xpath("//*[text()=' Filter ']");
    private static final By FILTER_EVENT = By.xpath("//*[text()=' Event ']");
    private static final By FILTER_BID_PERIOD = By.xpath("//*[text()=' Bid Period ']");
    private static final By FILTER_BID_LINE = By.xpath("//*[text()=' Bid Line ']");
    private static final By FILTER_STATUS = By.xpath("//*[text()=' Status ']");
    private static final String STATUS_COMMITTED = "//*[text()='PLACEHOLDER']";
    private static final By COMMIT_BUTTON = By.xpath("//*[text()='Commit']");
    private static final String FILTER_OPTIONS = "//*[text()='PLACEHOLDER']";
    private static final By YES_COMMIT_BUTTON = By.xpath("//*[text()=' Yes, Commit ']");
    private static final By APPLY_BTN = By.xpath("//*[text()=' Apply ']");
    private static final By PDF_DOWNLOAD_BUTTON = By.xpath("(//div[@class ='dot-menu' ])[1]");
    private static final By DOWNLOAD_BUTTON = By.xpath("//button[text() ='Download' ]");
    private static final By ALL_DETAILS = By.xpath("//*[@class='solution-details-table__row'][1]//div[@class = 'cl-data-table-column-item']");

    ArrayList<String> training = null;
    ArrayList<String> tripToPull = null;
    ArrayList<String> empID= null;
    String employeeID;

    String[] intermediateStatus = {"Pending Review"};
    String[] failedStatus = {"Request Failure"};
    String[] passedStatus = {"Committed", "Ingest Failure"};

    public void processPTORequest(String event, String month, String bidLine) throws InterruptedException, ParseException {
        waitForSolutionStatus(event, month, bidLine, 15, intermediateStatus, failedStatus);
        buttonClick(FIRST_VIEW_LINK);
        buttonClick(COMMIT_BUTTON);
        buttonClick(YES_COMMIT_BUTTON);
        waitForSolutionStatus(event, month, bidLine, 15, passedStatus, failedStatus);
        try {
            readSolutionQueueDetails();
        } catch (Exception e) {
            report.reportSelenium("Fail", "Trip to pull or training data does not exist in the solution.");
            loggerRosaPto.error(e);
        }
    }

    public void waitForSolutionStatus(String event, String month, String bidLine, Integer maxWaitInMinutes, String[] passedStatus, String[] failedStatus) throws InterruptedException, ParseException {
        long startTime = System.currentTimeMillis();
        long endTime = startTime +(1000 * 60 * maxWaitInMinutes);
        String xpathSingleElementsInRow = "(//*[@style = 'justify-content: space-between;'])[2]/*/div"; //First row is table header
        Boolean statusFound = false;
        String status = null;
        while(System.currentTimeMillis() < endTime) {
            applyFilter(event, month, bidLine);
            List<WebElement> rowElms = getDriver().findElements(By.xpath(xpathSingleElementsInRow));
            String queueEvent = (rowElms.get(0)).getText();
            String queueBidPeriod = (rowElms.get(1)).getText();
            String queueBidLine = (rowElms.get(2)).getText();
            if (queueEvent.equalsIgnoreCase(event) && queueBidPeriod.equalsIgnoreCase(month) && queueBidLine.equalsIgnoreCase(bidLine)) {
                status = getDriver().findElement(By.xpath(xpathSingleElementsInRow + "//p")).getText();
                if (Arrays.asList(passedStatus).contains(status)) {
                    statusFound = true;
                    break;
                } else if (Arrays.asList(failedStatus).contains(status)) {
                    report.reportSelenium("Fail", "The request has reached to a failed status: "+ status);
                    break;
                } else {
                    getDriver().navigate().refresh();
                    Thread.sleep(20000);
                }
            }
        }
        if (statusFound) {
            long timeElapsed = System.currentTimeMillis() - startTime;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed);
            if (minutes <= 0) {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
                report.reportSelenium("pass", "The request status changed to : "+ status +" in "+ seconds +" seconds.");
            }
        }else{
            report.reportSelenium("error", "Status change failed, script waited for "+ maxWaitInMinutes +" minutes. Expected status : '"+ String.join("/",passedStatus) +"' but Actual status is : "+ status);
        }
    }

    public void applyFilter(String event, String bidPeriod, String bidLine) throws InterruptedException {
        try {
            buttonClick(FILTER_BUTTON);
            buttonClick(FILTER_EVENT);
            buttonClick(By.xpath(FILTER_OPTIONS.replace(MessageConstants.PLACEHOLDER, event)));
            report.reportSelenium("pass", "Filter option applied with Event as "+ event);
            buttonClick(FILTER_BID_PERIOD);
            buttonClick(By.xpath(FILTER_OPTIONS.replace(MessageConstants.PLACEHOLDER, bidPeriod)));
            report.reportSelenium("pass", "Filter option applied with Bid Period as "+ bidPeriod);
            buttonClick(FILTER_BID_LINE);
            buttonClick(By.xpath(FILTER_OPTIONS.replace(MessageConstants.PLACEHOLDER, bidLine)));
            report.reportSelenium("pass", "Filter option applied with Bid Line as "+ bidLine);
            buttonClick(APPLY_BTN);
            report.reportSelenium("pass", "Filter options successfully applied");
        }catch(Exception e){
            report.reportSelenium("fail", "Failed to apply filter options.");
            loggerRosaPto.error(e);
        }
    }

    public void statusPollingOfPTORequest(String category, String cycle, String aircraft, String event, String month, String bidline, String coreConditional, String requestCreationTime) throws InterruptedException, ParseException {
        String xpathAllRowsFromSolutionQueue = "(//*[@style = 'justify-content: space-between;'])"; //11 rows including the header.
        String xpathAlltheElementsInRow = "(//*[@style = 'justify-content: space-between;'])[" + MessageConstants.PLACEHOLDER + "]/*/div";
        String xpathSingleElementsInRow;
        List<WebElement> allRowsFromSolutionQueue = getDriver().findElements(By.xpath(xpathAllRowsFromSolutionQueue));
        int rosaOneMinutCounter = 0;

        loggerRosaPto.info("category:"+category);
        loggerRosaPto.info("cycle:"+cycle);
        loggerRosaPto.info("aircraft:"+aircraft);
        loggerRosaPto.info("event:"+event);
        loggerRosaPto.info("month:"+month);
        loggerRosaPto.info("bidline:"+bidline);
        loggerRosaPto.info("coreConditional:"+coreConditional);
        loggerRosaPto.info("requestCreationTime:"+requestCreationTime);

        for (int i = 2; i < allRowsFromSolutionQueue.size() - 1; i++) {
            xpathSingleElementsInRow = xpathAlltheElementsInRow.replace(MessageConstants.PLACEHOLDER, "" + i);
            List<WebElement> rowElems = getDriver().findElements(By.xpath(xpathSingleElementsInRow));
            String queuEvent = (rowElems.get(0)).getText();
            String queuBidPeriod = (rowElems.get(1)).getText();
            String queuBidLine = (rowElems.get(2)).getText();

           if (queuEvent.equalsIgnoreCase(event) && queuBidLine.equalsIgnoreCase(bidline) && queuBidPeriod.equalsIgnoreCase(month)) {
                String xpathstatus = xpathSingleElementsInRow + "//p";
                String status = getDriver().findElement(By.xpath(xpathstatus)).getText();

                //If the tatus is intermediate then wait for some time
                if (Arrays.asList(intermediateStatus).contains(status)) {
                    Thread.sleep(5000);
                    rosaOneMinutCounter++;

                    if (rosaOneMinutCounter < 1) {
                        //F5
                        statusPollingOfPTORequest(category, cycle, aircraft, event, month, bidline, coreConditional, timeOfCreatingRequest);
                        report.reportSelenium("info", "The request is in intermediate status after " + rosaOneMinutCounter + " minutes of its creation.");
                    } else {
                        report.reportSelenium("fail", "The request is still in intermediate status even after " + rosaOneMinutCounter + " minutes.");
                        break;
                    }

                } else if (Arrays.asList(failedStatus).contains(status)) {
                    report.reportSelenium("fail", "The request has reached to a failed status: " + status);
                    break;
                } else if (Arrays.asList(passedStatus).contains(status)) {
                        try {

                            readSolutionQueueDetails();
                        } catch (Exception e) {
                            report.reportSelenium("Fail", "Trip to pull or training data does not exist in the solution.");
                            loggerRosaPto.error(e);
                        }
            break;
                    }
                }
            }
        }

    public Map<String, Map<String, ArrayList<String[]>>> getHM(){
        //masterHM
        return masterHM;
    }

     /*If the time fidderence between the system date and the one that is pickes up from t
    he solution queue is more than 1 minut.
    then this is nt the right row. hence return false.ParseException
    if difference is within 1 min range then its the right row.*/
    public boolean isTimeWithinAcceptableRange(String qTime, String sysTime) throws ParseException{
       long diffSecs, diffMins;
       try {
           diffSecs = new DateUtil().getTimeDiff(qTime, sysTime, CommonFormats.ROSA_FORMAT);
           diffMins = diffSecs / 60;
           return (diffMins > 1);
       }catch(Exception e){
           loggerRosaPto.error(e);
           return false;
           }
    }

    public void readSolutionQueueDetails()  {
        try{
        String locatorTraining = "(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[PLACEHOLDER]/div[1]/div[@class='grid__row']/div[1]/div";
        String triptopull = "(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[PLACEHOLDER1]/div[PLACEHOLDER2]//div[@class = 'grid__column-8/12']/div";
        int x = 1;
        int rowIndx = 0;
        waitForElement(FIRST_VIEW_LINK);
        ArrayList<String> list = new ArrayList<>();
        if (isElementPresent(FIRST_VIEW_LINK)) {
            buttonClickIfExist(FIRST_VIEW_LINK);
        }
        Thread.sleep(6000);
        List<WebElement> listCrewIdRows = getDriver().findElements(ALL_DETAILS);

        do {
            WebElement ele = listCrewIdRows.get(x);
            webDriverFluentWait();
            list.add(rowIndx, ele.getText());
            rowIndx++;
            x = x+7;
        } while (x < listCrewIdRows.size());


            for (int i = 0; i < list.size() ; i++){
            LinkedHashMap<String, ArrayList<String[]>> trngHM2= new LinkedHashMap<>();

            ArrayList<String[]> list1 = new ArrayList<>();
            ArrayList<String[]> list2 = new ArrayList<>();

            String xpathId = "//*[text()='"+list.get(i)+"']";
            getDriver().findElement(By.xpath(xpathId)).click();
            report.reportSelenium("info","Reading row number: "+i);
            Thread.sleep(1000);
            String fullxpathTraining = locatorTraining.replace(MessageConstants.PLACEHOLDER,""+(2*i+1));
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
                    list1.add(rowNumber,e.getText().split("\n"));
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
                    list2.add(rowTripToPull,e.getText().split("\n"));
                    rowTripToPull++;
                }
            }

            List<WebElement> ttp2 = getDriver().findElements(By.xpath(fullxpathTripToPull2));
            if (!ttp2.isEmpty()){
                for (int t=0; t<ttp2.size();t++ ){
                    WebElement eleTrng = ttp2.get(t);

                    List<WebElement> elements = eleTrng.findElements(By.xpath("./*"));
                    for (int j = 0; j < elements.size() - 1; j++) {
                        WebElement e = elements.get(j);
                        list2.add(rowTripToPull,e.getText().split("\n"));
                        rowTripToPull++;
                    }
                }
            }

            trngHM2.put("trng", list1);
            trngHM2.put("triptopull", list2);
            masterHM.put(list.get(i),trngHM2);
            TestUtil.setRosaMasterHM(masterHM);
            getDriver().findElement(By.xpath(xpathId)).click();
            Thread.sleep(4000);
        }
        }catch(Exception e){
            report.reportSelenium("Fail", "Error occured while reading the row.");
            loggerRosaPto.error(e);
        }
    }

    public void clickDownloadPdf() {
        try {
            waitUntilElementClickable(PDF_DOWNLOAD_BUTTON);
            buttonClick(PDF_DOWNLOAD_BUTTON);
            WebElement webElement = getDriver().findElement((PDF_DOWNLOAD_BUTTON));
            Actions builder = new Actions(getDriver());
            builder.moveToElement(webElement).click(webElement);
            builder.perform();

            buttonClickIfExist(PDF_DOWNLOAD_BUTTON);
            buttonClickIfExist(DOWNLOAD_BUTTON);

            FileUtil pdf = new FileUtil();
            String pdfFilePAth = pdf.getTheNewestFile("C:/Users/x257093/Downloads", "pdf").toString();
            printConsole("Latest pdf file downloaded: " + pdfFilePAth);
            printConsole("training :" + training);

            printConsole("tripToPull :" + tripToPull);
            printConsole("empID :" + empID);
            employeeID = parseEmpID(empID);
        }catch(Exception e){
            loggerRosaPto.error(e);
        }
    }

    private String parseEmpID(ArrayList<String> empID){
        String emp = empID.get(0);
        printConsole("employeeID: "+employeeID);
        return emp.substring(10,16);
    }

    public void latestFile() {
    FileUtil pdf = new FileUtil();
    printConsole(pdf.getTheNewestFile("C:/Users/x257093/Downloads","pdf").toString());
    }


    public void selectFilterOption(String filter) throws InterruptedException {

        try {
            waitUntilElementClickable(FILTER_BUTTON);
            waitForElement(FILTER_BUTTON);
            buttonClick(FILTER_BUTTON);
            buttonClickIfExist(FILTER_STATUS);
            buttonClickIfExist(By.xpath(STATUS_COMMITTED.replace(MessageConstants.PLACEHOLDER,filter )));
            buttonClickIfExist(APPLY_BTN);
            report.reportSelenium("pass", "Filter button clicked successful .");
        }catch(Exception e){
            report.reportSelenium("fail", "Navigation failed.");
            loggerRosaPto.error(e);
        }

    }

    public void clickOnFirstViewlink(){
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
            loggerRosaPto.error(e);
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
                arrStr[j] = e.getText().replace("\n", "_");
                hm.put(i,arrStr);
            }
        }
    }
}
