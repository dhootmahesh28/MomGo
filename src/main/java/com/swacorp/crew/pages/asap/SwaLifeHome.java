package com.swacorp.crew.pages.asap;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.AsapConstants;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.DataTable;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class SwaLifeHome extends BasePage {

    private final Logger loggerSWALifeLogin = Logger.getLogger(SwaLifeHome.class);
    ReportUtil report = new ReportUtil();
    CommonUtils commonUtils = new CommonUtils();
    DateUtil dateUtil = new DateUtil();
    private static final String USER_ROLE = System.getProperty("role");

    private String strFltNum;
    private String strDepart;
    private String strArrive;
    private String strFltDate;
    private String strTailNum;
    private String strAlternateSt;
    private String strEqType;

    private static final String REPLACE_TXT = "PLACEHOLDER";
    private static final String TEMP_XPATH = "text()='" + REPLACE_TXT + "'";
    private static final String FLT_NUMBER = "//a[text()='" + REPLACE_TXT + "'][1]";

    private final By xpathAsapReporting = By.xpath("//img[contains(@src,'asap_reporting')]");

    private final By idRptForFlightInTable = By.id("dgFlights");
    private final By idIncompleteRptTable = By.id("dgPending");
    private final By xpathRptForFlightNotInTable = By.id("linkFree");
    private final By xpathRptWithNoFlight = By.id("NoFlightInfo");
    private final By xpathAlreadyExistingReport = By.xpath("//*[text()='click here']");
    private static final String CHECKBOX_TBL = "dgPending_RowSelectorColumnSelector_" + REPLACE_TXT;
    private final By idBtnDelete = By.id("_btnDelete");





    public void setFltNum(String strFltNum){
        if(strFltNum.equalsIgnoreCase("")){
            try {
                strFltNum = String.valueOf(randomNumber(4));
            }catch(Exception e){
                loggerSWALifeLogin.error(e);
            }
        }
        this.strFltNum = strFltNum;
    }
    public void setFltDeptSt(String strDepart){   this.strDepart = strDepart;  }
    public void setFltArriveSt(String strArrive){    this.strArrive = strArrive;   }
    public void setFltDate(String strFltDate){
        if(strFltDate.equalsIgnoreCase("")){
            strFltDate = dateUtil.getCurrentDate("MM/dd/yyyy");
        }
        this.strFltDate = strFltDate;
    }
    public void setFltTailNum(String strTailNum){
        if(strTailNum == null) {
            strTailNum = "";
        }else if(strTailNum.equals("")){
            try {
                strTailNum = String.valueOf(randomNumber(5));
            }catch(Exception e){
                loggerSWALifeLogin.error(e);
            }
        }
        this.strTailNum = strTailNum;
    }
    public void setFltEqType(String strEqType){     this.strEqType = strEqType;   }
    public void setAlternateSt(String strAlternateSt){     this.strAlternateSt = strAlternateSt;   }

    public String getFltNum(){   return strFltNum;  }
    public String getFltDeptSt(){   return strDepart;  }
    public String getFltArriveSt(){    return strArrive;   }
    public String getFltDate(){     return strFltDate;    }
    public String getFltTailNum(){    return strTailNum;  }
    public String getFltEqType(){     return strEqType;   }
    public String getAlternateSt(){     return strAlternateSt;  }

    /**
     * navigateTOASAPReporting - Helps in Navigating to ASAP Reporting based on the user role.
     */
    public void navigateTOASAPReporting(){
        try {
            String strTitle = "";
            switch (USER_ROLE.toUpperCase()) {
                case "FO":
                    commonUtils.navigateToMenu(AsapConstants.FO_ASAP_PATH);
                    break;
                case "IF":
                    commonUtils.navigateToMenu(AsapConstants.IF_ASAP_PATH);
                    break;
                case "MX":
                    commonUtils.navigateToMenu(AsapConstants.MX_ASAP_PATH);
                    break;
                case "DP":
                    commonUtils.navigateToMenu(AsapConstants.DP_ASAP_PATH);
                    break;
                default:
                    report.report(MessageConstants.FAILED, "Invalid Case option : " + USER_ROLE);
                    break;
            }
            String winHandleNew = getDriver().getWindowHandle();

            if (USER_ROLE.equalsIgnoreCase("FO") || USER_ROLE.equalsIgnoreCase("IF")) {
                if (isElementVisible(xpathAsapReporting)) {
                    report.reportSelenium(MessageConstants.INFO, "Navigated to Page - Launch ASAP Report.");
                    buttonClick(xpathAsapReporting);
                    commonUtils.switchToNewTab(winHandleNew);
                    strTitle = "ASAP Event Report";
                }
            }else if(USER_ROLE.equalsIgnoreCase("MX") || USER_ROLE.equalsIgnoreCase("DP")){
                commonUtils.switchToIFrame(1);
                strTitle = "Launch ASAP";
            }
            commonUtils.verifyTitleOfThePage(strTitle, "ASAP Event report Page");
        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Navigate to Page - Launch ASAP Report. Error : " + e.toString());
        }
    }

    /**
     * logOutSwaLife - Logs us out of SwaLife by navigating to profile-->logout
     */
    public void logOutSwaLife(){
        try{
            if(USER_ROLE.equalsIgnoreCase("FO") || USER_ROLE.equalsIgnoreCase("IF")) {
                if (!getDriver().getTitle().contains("SWALife")) {
                    String winHandleNew = getDriver().getWindowHandle();
                    for (String winHandle : getDriver().getWindowHandles()) {
                        if (!winHandleNew.equals(winHandle)) {
                            winHandleNew = winHandle;
                            break;
                        }
                    }
                    getDriver().close();
                    getDriver().switchTo().window(winHandleNew);
                }
//
              } else{
                getDriver().switchTo().parentFrame();
                getDriver().quit();
            }

        }catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium("ERROR", "Failed to Logout from SwaLife. Error : " + e.toString());
        }
    }

    /**
     * openASAPReportForm - Open ASAP Report form based on the report type.
     */
    public void openASAPReportForm(String strReportType){
        try{
            DataTable dataTable = new DataTable(waitForElement(idRptForFlightInTable));
            if(strReportType.equalsIgnoreCase("EXISTING_FLIGHT")){
                List<WebElement> lstRows = dataTable.getRowsList();
                if(!lstRows.isEmpty()) {
                    String[] arrFltDetails = Arrays.copyOf(lstRows.get(1).getText().split(" "), 6);
                    setFltNum(arrFltDetails[0]);
                    setFltDeptSt(arrFltDetails[1]);
                    setFltArriveSt(arrFltDetails[2]);
                    setFltDate(arrFltDetails[3]);
                    setFltTailNum(arrFltDetails[4]);
                    setFltEqType(arrFltDetails[5]);
                }else{
                    report.reportSelenium(MessageConstants.FAILED, "No existing rows present. Exiting the TestCase.");
                }
                buttonClick(By.xpath(FLT_NUMBER.replace(REPLACE_TXT, getFltNum())));
                buttonClickIfExist(xpathAlreadyExistingReport);
            }else if(strReportType.equalsIgnoreCase("NO_EXISTING_FLIGHT")){
                buttonClick(xpathRptForFlightNotInTable);
            }else if(strReportType.equalsIgnoreCase("NO_FLIGHT")){
                buttonClick(xpathRptWithNoFlight);
            }
            commonUtils.verifyTitleOfThePage("Flight Information", "ASAP Event Reports");

        } catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to open ASAP Report Form based on type : " + strReportType + ". Error : " + e.toString());
        }
    }

    /**
     * verifyDataInIncompleteTable - Verifies the given data in incomplete report
     * @param fltNum
     * @param deptSt
     * @param arrStation
     * @param fltDate
     * @param fltTail
     * @param blnIsPresent
     * @return
     */
    public int verifyDataInIncompleteTable(String fltNum, String deptSt, String arrStation, String fltDate, String fltTail, boolean blnIsPresent){
        int rowNum = -1;
        try{
            String strTextToVerify = fltNum + " " + deptSt + " " + arrStation + " " + fltDate + " " + fltTail;
            if(isElementPresent(idIncompleteRptTable) && blnIsPresent) {
                DataTable dtIncompleteTable = new DataTable(waitForElement(idIncompleteRptTable));
                boolean blnFound = false;
                List<WebElement> allRows = dtIncompleteTable.getRowsList();

                for (int i = 1; i < allRows.size(); i++) {
                    String rowContent = allRows.get(i).getText();
                    //If the content of the cell in the corresponding column equal the column name, return the row
                    if (rowContent.replace(" ", "").contains(strTextToVerify.replace(" ", ""))) {
                        blnFound = true;
                        rowNum = i;
                        break;
                    }
                }
                if (blnFound)
                    report.reportSelenium(MessageConstants.PASSED, "The incomplete report details is present in the table. Verified Value : " + strTextToVerify);
                else
                    report.reportSelenium(MessageConstants.FAILED, "Failed to verify record in Incomplete report. Expected Data presence value : " + blnIsPresent + ", Actual : " + (!blnIsPresent));
            }
            else{
                if (!blnIsPresent) {
                    report.reportSelenium(MessageConstants.PASSED, "The incomplete report details table and Data: " + strTextToVerify + " is not present.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Incomplete report details tables is not present. Expected value : " + strTextToVerify);
                }
            }
        }catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to verify Data in Incomplete Report. Error : " + e.toString());
        }
        return rowNum;
    }

    /**
     * deleteRowFromIncompleteTable - Deletes the row from incomplete report
     * @param fltNum
     * @param deptSt
     * @param arrStation
     * @param fltDate
     * @param fltTail
     */
    public void deleteRowFromIncompleteTable(String fltNum, String deptSt, String arrStation, String fltDate, String fltTail){
        try{
            int rowNum = verifyDataInIncompleteTable(fltNum, deptSt, arrStation, fltDate, fltTail, true);
            buttonClick(By.id(CHECKBOX_TBL.replace(REPLACE_TXT, String.valueOf(rowNum-1))));

            report.reportSelenium(MessageConstants.INFO, "Selected the checkboxes of the row which needs to be deleted.");
            buttonClick(idBtnDelete);
            verifyDataInIncompleteTable(fltNum, deptSt, arrStation, fltDate, fltTail, false);

        }catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Delete rows from Incomplete Report. Error : " + e.toString());
        }
    }

    /**
     * deleteRowFromIncompleteTable - Deletes the row from incomplete report
     */
    public void deleteRowFromIncompleteTable(){
        try{
            if(isElementPresent(idIncompleteRptTable)) {
                DataTable dtIncompleteTable = new DataTable(waitForElement(idIncompleteRptTable));
                List<WebElement> allRows = dtIncompleteTable.getRowsList();
                for (int i = 1; i < allRows.size(); i++) {
                    buttonClick(By.id(CHECKBOX_TBL.replace(REPLACE_TXT, String.valueOf(i - 1))));
                }
                report.reportSelenium(MessageConstants.INFO, "Selected the checkboxes of all rows, needs to be deleted.");
                buttonClick(idBtnDelete);

                if (isElementPresent(By.xpath(TEMP_XPATH.replace(REPLACE_TXT, "Incomplete Reports")))) {
                    report.reportSelenium(MessageConstants.FAILED, "Incomplete Report section should not be present after deleting all rows.");
                } else {
                    report.reportSelenium(MessageConstants.PASSED, "Incomplete Report section is not present after deleting all rows.");
                }
            }else{
                report.reportSelenium(MessageConstants.INFO, "The incomplete report details table is not present.");
            }
        }catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Delete rows from Incomplete Report. Error : " + e.toString());
        }
    }

    /**
     * editIncompleteRecords - Click on edit button in the incomplete records.
     * @param fltNum
     * @param deptSt
     * @param arrStation
     * @param fltDate
     * @param fltTail
     */
    public void editIncompleteRecords(String fltNum, String deptSt, String arrStation, String fltDate, String fltTail){
        try{
            DataTable dtIncompleteTable = new DataTable(waitForElement(idIncompleteRptTable));
            int rowNum;
            String strTitleMX = "review Report";
            String strTitle ="ASAP Event Report";
            List<WebElement> allRows = dtIncompleteTable.getRowsList();
            if(!USER_ROLE.equalsIgnoreCase("MX")) {
                rowNum = verifyDataInIncompleteTable(fltNum, deptSt, arrStation, fltDate, fltTail, true);
                if(USER_ROLE.equalsIgnoreCase("DP"));
                    strTitle = AsapConstants.ASAP_REPORT_TITLE;
            }else{
                rowNum = allRows.size() - 1;
                strTitle = AsapConstants.ASAP_REPORT_TITLE;
            }
            List<WebElement> ele = allRows.get(rowNum).findElements(dtIncompleteTable.getCellTag());
            report.reportSelenium(MessageConstants.INFO, "Application state before clicking on Edit button.");
            buttonClick(ele.get(ele.size()-1));
            commonUtils.verifyTitleOfThePage(strTitleMX, "review Report");

        }catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while editing rows in Incomplete Report. Error : " + e.toString());
        }
    }

}
