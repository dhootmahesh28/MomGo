package com.swacorp.crew.pages.asap;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.DataTable;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class SwaLifeMXASAPReport extends BasePage {

    private final Logger loggerSwaLifeMXASAPReport = Logger.getLogger(SwaLifeMXASAPReport.class);
    ReportUtil report = new ReportUtil();
    SwaLifeASAPReport swaLifeASAPReport = new SwaLifeASAPReport();
    CommonUtils commonUtils = new CommonUtils();
    DateUtil dateUtil = new DateUtil();
    private static final String REPLACE_TXT = "PLACEHOLDER";

    private final By nameAirWorthiness = By.name("grpAirworthiness");
    private final By xpathCreateReport = By.xpath("//*[@value='Create New Report']");

    private final By xpathContactManager = By.xpath("//a[text() = 'Contact ASAP Manager']");
    private final By xpathHelp = By.xpath("//li[@class='header-menu']//following::a[text() = 'Help']");

    private final By idEmpAddress = By.id("AddressTxt");
    private final By idCellPhoneText = By.id("CellPhoneTxt");
    private final By idHomePhoneNum = By.id("HomePhoneTxt");
    private final By idWorkPhoneNum = By.id("WorkPhoneTxt");
    private final By idEmailAdd = By.id("EmailTxt");

    private final By idBaseStation = By.id("ddlBaseStations");
    private final By idFlightShift = By.id("ddlShift");
    private final By nameJobClassification = By.name("RblContract");
    private final By idContractPosition = By.id("ddlContractPosition");
    private final By idNonContractPosition = By.id("ddlNonContractPosition");
    private final By idDaysOff = By.id("tbDaysOff");

    private final By nameAirWorthy = By.name("RblAW");
    private final By idDateOfEvent = By.id("tbDateOfEvent");
    private final By idTimeOfEvent = By.id("uxTxtTimeOfEvent");
    private final By idOverTimeShift = By.name("RblOT");
    private final By idHoursOnDuty = By.id("tbHoursOnDuty");
    private final By idEventShift = By.id("ddlEventShift");
    private final By idBidLocationTimeOfEvent = By.id("ddlMechanics");
    private final By idStationAtTimeOfEvent = By.id("ddlStations");
    private final By nameAirCraftInvolve = By.name("RblAC");
    private final By idEventInvolvementType = By.id("ddlEventInvolvementType");
    private final By nameWasProblemCorrected = By.name("RblCorrected");

    private final By xpathSummaryOfEvent = By.id("TextBox1");
    private static final String EVENT_TYPE = "//label[text()='" + REPLACE_TXT + "']";

    private final By idDocReference = By.id("ddlDocumentTypes1");
    private final By idDocDetails = By.id("dtbDocDetails1");

    private final By idQualification = By.id("tbQualifications");
    private final By idATACode = By.id("ddlATACodes");
    private final By idEventDiscovered = By.id("ddlHowDiscovered");
    private final By nameFactFinding = By.name("grpFF");
    private final By nameDutiesOtherThanNormal = By.name("grpDD");
    private final By xpathEventDesc = By.id("TextBox1");
    private final By xpathPreventiveMeasure = By.id("TextBox2");

    private final By xpathReportNumber = By.xpath("//*[contains(text(), 'Report Number')]");
    private final By xpathNextPage = By.id("NextButton");
    private final By xpathSubmitPage = By.id("SubmitButton");
    private final By xpathDataTable = By.xpath("//table[@rules='rows']");

    public String getStrTableContent() {
        return strTableContent;
    }
    public void setStrTableContent(String strTableContent) {
        this.strTableContent = strTableContent;
    }
    private String strTableContent;

    public String getStrReportType() {
        return strReportType;
    }
    public void setStrReportType(String strReportType) {
        this.strReportType = strReportType;
    }
    private String strReportType;

    public String getStrReportNumber() {
        return strReportNumber;
    }
    public void setStrReportNumber(String strReportNumber) {
        this.strReportNumber = strReportNumber;
    }

    public String getStrTrackingNumber() {
        return strTrackingNumber;
    }

    public void setStrTrackingNumber(String strTrackingNumber) {
        this.strTrackingNumber = strTrackingNumber;
    }

    private String strTrackingNumber;
    private String strReportNumber;

    /**
     * verifyMangerAndHelpButton - verifies manager and help button
     */
    public void verifyMangerAndHelpButton(){
        try {
            String winHandleOld = getDriver().getWindowHandle();
            buttonClick(xpathContactManager);
            commonUtils.switchToNewTab(winHandleOld);
            commonUtils.closeTabsAndSwitchToInnerFrame(winHandleOld);
            buttonClick(xpathHelp);
            commonUtils.switchToNewTab(winHandleOld);
            commonUtils.closeTabsAndSwitchToInnerFrame(winHandleOld);

        }catch(Exception e){
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred. Error : " + e.toString());
        }
    }

    /**
     * openASAPReportForm - Open ASAP report Form
     * @param isAirWorthiness
     */
    public void openASAPReportForm(String isAirWorthiness){
        try {
            if(!isAirWorthiness.equalsIgnoreCase(""))
                selectRadioButton(nameAirWorthiness, isAirWorthiness);
            if(isAirWorthiness.equalsIgnoreCase("YES")){
                String strAlertMessage = acceptAlert();
                report.reportSelenium(MessageConstants.PASSED, "Alert message " + strAlertMessage + " is displayed.");
            }
            report.reportSelenium(MessageConstants.INFO, "State of App before opening report.");
            buttonClick(xpathCreateReport);
        }catch(Exception e){
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while opening ASAP Report. Error : " + e.toString());
        }
    }



    //"West Tesax, Dallas", "1234567890, "2345678901, "3456789012", "anup.pandit@wnco.com"
    /**
     * addEmployeeInformation - Adds employee information
     * @param strEmpAddress
     * @param strCellPhNum
     * @param strHomePhNum
     * @param strWorkPhNum
     * @param strEmail
     */
    public void addEmployeeInformation(String strEmpAddress, String strCellPhNum, String strHomePhNum, String strWorkPhNum, String strEmail) {
        try {
            swaLifeASAPReport.verifyShowAndHideHelpSection();

            enterText(idEmpAddress, strEmpAddress);
            enterText(idCellPhoneText, strCellPhNum);
            enterText(idHomePhoneNum, strHomePhNum);
            enterText(idWorkPhoneNum, strWorkPhNum);
            enterText(idEmailAdd, strEmail);

            report.reportSelenium(MessageConstants.PASSED, "Employee Information Entered successfully.");
        } catch (Exception e) {
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while adding Employee Info. Error : " + e.toString());
        }
    }

    //"DAL", "Day", "Contract", "AMT", "Manager", "Sunday"
    /**
     * addWorkInformation - adds work information
     * @param strBase
     * @param strFlightShift
     * @param strJobClassification
     * @param strContractPos
     * @param strNonContractPos
     * @param strDaysOff
     */
    public void addWorkInformation(String strBase, String strFlightShift, String strJobClassification, String strContractPos, String strNonContractPos, String strDaysOff) {
        try {

            selectOption(idBaseStation, strBase);
            selectOption(idFlightShift, strFlightShift);
            selectRadioButton(nameJobClassification, strJobClassification);
            selectOption(idContractPosition, strContractPos );
            selectOption(idNonContractPosition, strNonContractPos);
            enterText(idDaysOff, strDaysOff);

            report.reportSelenium(MessageConstants.PASSED, "Work Information Entered successfully.");
            buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while adding Work Info. Error : " + e.toString());
        }
    }

    //"No", "", "1200", "No", "6", "Day", "Line Service", "DAL", "No", "Safety Concern", "No"
    /**
     * addEventInformation - Add Event Info
     * @param strAirWorthiness
     * @param strDateOfEvent
     * @param strTimeOfEvent
     * @param strOverTimeShift
     * @param strHoursOnDuty
     * @param strEventShift
     * @param strBidLocation
     * @param strAltSt
     * @param strAirCraftInvolvement
     * @param strEventEnvolvementType
     * @param strWasProblemCorrected
     */
    public void addEventInformation(String strAirWorthiness, String strDateOfEvent, String strTimeOfEvent, String strOverTimeShift, String strHoursOnDuty, String strEventShift, String strBidLocation, String strAltSt, String strAirCraftInvolvement, String strEventEnvolvementType, String strWasProblemCorrected) {
        try {
            swaLifeASAPReport.verifyShowAndHideHelpSection();

            selectRadioButton(nameAirWorthy, strAirWorthiness);
            enterText(idDateOfEvent, strDateOfEvent);
            enterText(idTimeOfEvent, strTimeOfEvent);
            selectRadioButton(idOverTimeShift, strOverTimeShift);
            enterText(idHoursOnDuty, strHoursOnDuty);
            selectOption(idEventShift, strEventShift);
            selectOption(idBidLocationTimeOfEvent, strBidLocation);
            selectOption(idStationAtTimeOfEvent, strAltSt);
            selectRadioButton(nameAirCraftInvolve, strAirCraftInvolvement);
            selectOption(idEventInvolvementType, strEventEnvolvementType);
            selectRadioButton(nameWasProblemCorrected, strWasProblemCorrected);

            report.reportSelenium(MessageConstants.PASSED, "Work Information Entered successfully.");
            buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while adding Event Info. Error : " + e.toString());
        }
    }

    //"AIRCRAFT DAMAGE", "Tool Damage"
    /**
     * addEventType - Adds event Type
     * @param strEventSummary
     * @param strEvent
     * @param strSubEvents
     */
    public void addEventType(String strEventSummary, String strEvent, String strSubEvents) {
        try {
            swaLifeASAPReport.verifyShowAndHideHelpSection();
            By element = null;
            element = xpathSummaryOfEvent;

            enterText(element, strEventSummary);

            By xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strEvent));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strSubEvents));
            buttonClick(xpath);

            report.reportSelenium(MessageConstants.PASSED, "Event Type Entered successfully.");
            buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while adding Event Type. Error : " + e.toString());
        }
    }

    //"ACO", "Document Details", "No", "No", "List of Qualification", "N/A", "Gate Return"
    /**
     *
     * @param strDocReference
     * @param strDocDetails
     * @param strFactFinding
     * @param strDutyOtherThanNormal
     * @param strQualification
     * @param strATACode
     * @param strEventDiscovered
     */
    public void addSupportingInformation(String strDocReference, String strDocDetails, String strFactFinding, String strDutyOtherThanNormal, String strQualification, String strATACode, String strEventDiscovered) {
        try {
            swaLifeASAPReport.verifyShowAndHideHelpSection();

            selectOption(idDocReference, strDocReference);
            enterText(idDocDetails, strDocDetails);
            selectRadioButton(nameFactFinding, strFactFinding);
            selectRadioButton(nameDutiesOtherThanNormal, strDutyOtherThanNormal);
            enterText(idQualification, strQualification);
            selectOption(idATACode, strATACode);
            selectOption(idEventDiscovered, strEventDiscovered);

            report.reportSelenium(MessageConstants.PASSED, "Supporting Information Entered successfully.");
            buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while adding Supporting Info. Error : " + e.toString());
        }
    }

    /**
     * addEventDescription - Adds event description
     * @param strEventDesc
     * @param strPreventMeasure
     */
    public void addEventDescription(String strEventDesc, String strPreventMeasure) {
        try {
            enterText(xpathEventDesc, strEventDesc);
            enterText(xpathPreventiveMeasure, strPreventMeasure);

            report.reportSelenium(MessageConstants.PASSED, "Event Type Entered successfully.");
            buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while adding Event Desc. Error : " + e.toString());
        }
    }

    /**
     * submitFinalReport - Submits final report
     */
    public void submitFinalReport(){
        try{
            DataTable dataTable = new DataTable(waitForElement(xpathDataTable));
            String strTblContent = dataTable.getFullContentOfTable();
            setStrTableContent(strTblContent);
            buttonClick(xpathSubmitPage);
            waitByTime(1000);

            String strDate = dateUtil.getCurrentDate("yyyyMMdd");
            String strTrackingNum = "P" + strDate + "-XXXXX-XXXX-XXXXX-";
            setStrTrackingNumber(strTrackingNum);
            strReportNumber = getDriver().findElement(xpathReportNumber).getText().replace("Report Number:", "").trim();
            setStrReportNumber(strReportNumber);
            report.reportSelenium("INFO", "Successfully Fetched the Report number : " + getStrReportNumber());

            swaLifeASAPReport.verifyPrintAndEmailButton(true);

        }catch(Exception e){
            loggerSwaLifeMXASAPReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error occurred while Submitting final report. Error : " + e.toString());
        }
    }

}
