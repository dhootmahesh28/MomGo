package com.swacorp.tsr.rosa;

import com.swacorp.crew.css.Css;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.tsr.utils.pdf.PDFReports;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

import com.swacorp.tsr.utils.pdf.FileUtil;
import org.openqa.selenium.interactions.Actions;

public class RosaSolutioQueue extends BasePage {

    private final Logger LOGGER = Logger.getLogger(RosaSolutioQueue.class);
    ReportUtil report = new ReportUtil();
    HashMap<Integer, String[]> hm = new HashMap<Integer, String[]>();

    private final By SOLUTION_QUEUE_TITLE = By.xpath("//h2[@class='pilot-queue__title']");
    private final By VIEW_QUEUE_PAGE = By.xpath("//div[text()='View Queue page']");
    private final By STATUS_HEADER = By.xpath("//div[text()='STATUS']");
    private final By VIEW_LINK = By.xpath("(//a[text()='View'])[1]");
    private final By SOLUTION_DETAILS_FIRSTLINK = By.xpath("/html/body/app-root/div[2]/div/div/app-solution-details/div[2]/cl-tabs/div[2]/cl-tab[1]/div/pto-solution-details-table/cl-data-table/cl-data-table-panel[1]/cl-data-table-panel-header/div/cl-data-table-row/cl-data-table-column[2]\n");
    private final By EMPLOYEE_ID = By.xpath("/html/body/app-root/div[2]/div/div/app-solution-details/div[2]/cl-tabs/div[2]/cl-tab[1]/div/pto-solution-details-table/cl-data-table/cl-data-table-panel/cl-data-table-panel-header/div/cl-data-table-row/cl-data-table-column[2]/div");
    //private final By DATA_DIV = By.xpath("(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[1]/div[1]/div[@class='grid__row']/div[1]/div/div[1]/div[@class='grid__column-12/12']/div[1]");
    private final By DATA_DIV = By.xpath("(//div[@class='trip-details__content-block']/div[@class='trip-details__content-block'])[1]/div[1]/div[@class='grid__row']/div[1]/div");
    private final By FILTER_BUTTON = By.xpath("//*[text()=' Filter ']");
    private final By FILTER_STATUS = By.xpath("//*[text()=' Status ']");
    private final By STATUS_COMMITTED = By.xpath("//*[text()='Committed']");
    private final By APPLY_BTN = By.xpath("//*[text()=' Apply ']");
    private final By SOLUTION_ROWS = By.xpath("//*[@class='cl-data-table-panel']");
    private final By FIRST_EMP_NUMBER_SOL_DETAILS = By.xpath("//div[@class='cl-data-table-column-item']");
    private final By PDF_DOWNLOAD_BUTTON = By.xpath("(//div[@class ='dot-menu' ])[1]");
    private final By DOWNLOAD_BUTTON = By.xpath("//button[text() ='Download' ]");

    ArrayList<String> training = null;
    ArrayList<String> TripToPull = null;
    ArrayList<String> empID= null;
    String employeeID;
;

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
            buttonClickIfExist(STATUS_COMMITTED);
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

    public void clickViewQueuePage(){
        buttonClick(VIEW_QUEUE_PAGE);
    }

    public void validateCompleteStatus(){

        waitByTime(5000);
        buttonClick(VIEW_LINK);
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
