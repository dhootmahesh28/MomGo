package com.swacorp.crew.pages.fssr;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.DataTable;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FssrCases extends BasePage {

    private final Logger loggerFssrCases = Logger.getLogger(FssrCases.class);
    ReportUtil report = new ReportUtil();
    FssrReportInput fssrReportInput = new FssrReportInput();

    private final By xpathCasesTable = By.xpath("//table[contains(thead,'Case Number')]");
    private final By xpathReportSelection = By.xpath("//*[contains(@class, 'selectedListView')]");
    private static final String REPLACE_TXT = "MENU";
    private static final String XPATH_MENU_TO_SELECT = "//ul[@role='listbox']//descendant::span[contains(text(),'" + REPLACE_TXT + "')][1]";
    private static final String CASE_NUMBER = "//a[@title = '" + REPLACE_TXT + "'][@data-refid='recordId']";
    private static final String CASE_TAB = "//*[@title = '" + REPLACE_TXT + "'][contains(@class, 'tabHeader')][1]";


    public void selectGivenCasesToView(String strCaseType){
        try {
            WebElement element = waitForElement(xpathReportSelection);
            if(! element.getAttribute("innerHTML").equalsIgnoreCase(strCaseType)){
                By xpathOfMenu = By.xpath(XPATH_MENU_TO_SELECT.replace(REPLACE_TXT, strCaseType));
                buttonClick(xpathReportSelection);
                waitByTime(2000);
                buttonClick(xpathOfMenu);
            }
            waitByTime(2000);
            if (isElementVisible(xpathCasesTable) && element.getAttribute("innerHTML").equalsIgnoreCase(strCaseType)) {
                report.reportSelenium("Pass", strCaseType + " is present in the list.");
                printConsole("selectGivenCasesToView passed");
            } else {
                report.reportSelenium("Fail", strCaseType + " is not present in the list.");
                printConsole("selectGivenCasesToView failed");
            }
        } catch (Exception e) {
            loggerFssrCases.error(e);
        }
    }

    public String getCaseNumberBasedOnDiscoveryDateTime() {
        String strCaseNumber = "";
        try {
            DataTable dtTable = new DataTable(waitForElement(xpathCasesTable));
            String strDateTime = fssrReportInput.getStrDateTime();
            int itemColumnIndex = dtTable.findColumnIndex("Discovery Date/Time");
            int intRowNum = dtTable.getRowNumBasedOnValue(strDateTime, itemColumnIndex - 1);

            List<WebElement> rows = dtTable.getRowsList();
            WebElement rowCells = rows.get(intRowNum).findElement(dtTable.getThTag());///th
            strCaseNumber = rowCells.getText();
            strCaseNumber = dtTable.trimInitialSpaces(strCaseNumber);

            report.reportSelenium("Pass", "Successfully fetched the Case Number - '" + strCaseNumber + "' based on Discovery Date/Time - " + strDateTime);
            printConsole("getCaseNumberBasedOnDiscoveryDateTime passed");

        } catch (Exception e) {
            loggerFssrCases.error(e);
        }
        return strCaseNumber;
    }


    public String[] getCaseDetails(String strCaseNumber) {
        String[] arrData = {};
        try {
            DataTable dtTable = new DataTable(waitForElement(xpathCasesTable));
            String strDateTime = fssrReportInput.getStrDateTime();
            int itemColumnIndex = dtTable.findColumnIndex("Case Number");

            int intRowNum = dtTable.getRowNumBasedOnValue(strDateTime, itemColumnIndex - 1);
            if(intRowNum < 0){
                report.reportSelenium("FAIL", "Case Number - '" + strCaseNumber + "' is not present in the table.");
            }

            List<WebElement> rows = dtTable.getRowsList();
            String strData = rows.get(intRowNum).getText();

            report.reportSelenium("Pass", "Successfully fetched the details for Case Number - '" + strCaseNumber + "' : "  + strData.replace("\n", "  "));
            printConsole("getCaseDetails passed");
            arrData = strData.split("\n");
        } catch (Exception e) {
            loggerFssrCases.error(e);
        }
        return arrData;
    }


    public void checkStatusOfCaseNumber(String strCaseNumber, boolean blnDraft){
        try {
            String[] arrData = getCaseDetails(strCaseNumber);
            if((blnDraft && arrData[4].equalsIgnoreCase("DRAFT")) || (!blnDraft && arrData[4].equalsIgnoreCase("Submitted"))){
                report.reportSelenium("Pass", "Successfully verified the status for Case Number - '" + strCaseNumber + "' is " + arrData[4]);
            }else{
                report.reportSelenium("FAIL", "Failed to verify the status for Case Number - '" + strCaseNumber + "', Status of submitted report is " + arrData[4]);
            }

            printConsole("checkStatusOfCaseNumber passed");

        } catch (Exception e) {
            loggerFssrCases.error(e);
        }
    }

    public void openSubmittedCaseDetails(String strCaseNumber){
        try {
            By xpathCaseNumber = By.xpath(CASE_NUMBER.replace(REPLACE_TXT, strCaseNumber));
            buttonClick(xpathCaseNumber);

            waitByTime(4000);
            By xpathCaseTab = By.xpath(CASE_TAB.replace(REPLACE_TXT, strCaseNumber));
            waitForElement(xpathCaseTab);

            if(isElementPresent(xpathCaseTab)) {
                report.reportSelenium("Pass", strCaseNumber + " is open in new Tab.");
                printConsole("openSubmittedCaseDetails passed");
            } else {
                report.reportSelenium("Fail", "Failed to open " + strCaseNumber + " in new Tab.");
                printConsole("openSubmittedCaseDetails Failed");
            }

        } catch (Exception e) {
            loggerFssrCases.error(e);
        }
    }



}
