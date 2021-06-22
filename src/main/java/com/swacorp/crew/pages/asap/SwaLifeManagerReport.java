package com.swacorp.crew.pages.asap;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.AsapConstants;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.DataTable;

import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class SwaLifeManagerReport extends BasePage {

    private final Logger loggerSwaLifeManagerReport = Logger.getLogger(SwaLifeManagerReport.class);
    ReportUtil report = new ReportUtil();
    CommonUtils commonUtils = new CommonUtils();
    private static final String USER_ROLE = System.getProperty("role");

    private static final String REPLACE_TXT = "PLACEHOLDER";
    private static final String REPLACE_TXT2 = "REPLACE_VALUE";
    private final By idUserIdInput = By.id("Username");
    private final By idPasswordInput = By.id("Password");
    private final By idLoginBtn = By.name("SubmitLogin");
    private static final String EVENT_ID = "//*[contains(text(),'" + REPLACE_TXT + "')]";
    private final By xpathReportID = By.xpath("//*[contains(text(),'Report ID')]");
    private static final String REF_NUM = "//*[contains(text(),'Reference Number: " + REPLACE_TXT + "')]";
    private static By xpathTable = By.xpath("//*[@id='divEditor']/table[2]");

    private static By xpathBtnMoveToPendingERT = By.xpath("//*[(@value='Move Event to Pending ERT Review') or (@value='Move Event to Pending ERC Review')]");
    private static By xpathBtnMoveToERTMeeting = By.xpath("//*[@value='Move Event to ERT Meeting' or @value='Move Event to ERC Meeting']");
    private static By idEventID = By.id("DisplayID");

    private static final String XPATH_EVENT_TEXT = "text() = 'Event ";
    private static final String XPATH_ERT_PENDING = "//*[(" + XPATH_EVENT_TEXT + REPLACE_TXT + " moved to Pending ERT Reveiw.') or (" + XPATH_EVENT_TEXT + REPLACE_TXT + " moved to Pending ERC Reveiw.')]";
    private static final String XPATH_ERT_REVIEW = "//*[" + XPATH_EVENT_TEXT + REPLACE_TXT + " queued for ERC review']";
    private static final String XPATH_POST_ERT = "//*[" + XPATH_EVENT_TEXT + REPLACE_TXT + " moved to Post-ERT tab']";
    private static final String XPATH_CLOSE_ERT = "//*[text() = 'Event ID " + REPLACE_TXT + " closed.']";
    private static final String XPATH_TAB_NAME = "//a[contains(text(), '" + REPLACE_TXT + "') and contains(@id, 'Tab')]";
    private static final String MX_FO_QUEUE_TO_ASRS = "//*[text() = 'Pilot report " + REPLACE_TXT + " added to queue for ASRS.']";
    private static By idPDFImage = By.id("ToolBar_ToolBox_IdPdfImg");
    private static By idNoNamePDFImage = By.id("ToolBar_ToolBox_NoNamePdfImg");
    private static By idNoIDPDFImage = By.id("ToolBar_ToolBox_NoIdPdfImg");
    private static By idHistoryImage = By.id("ToolBar_ToolBox_HistoryImg");
    private static By xpathOriginalPilotRptImage = By.xpath("//*[@id='ToolBar_ToolBox_OrigPilotReptImg' or @id='ToolBar_ToolBox_OrigAttendantRptImg']");
    private static By xpathAddInfoFromPilot = By.xpath("//*[@value='Request Additional Info' or @value='Additional Info from Pilot' or @value='Additional Info from Employee']");
    private static By xpathConfirmEmail = By.xpath("//*[@value='Confirmation E-Mail']");
    private static By xpathQueueForASRS = By.xpath("//*[@value='Queue for ASRS']");
    private static By idEditButton = By.id("editbutton");

    private static final String XPATH_READY_FOR_ERC = "//*[" + XPATH_EVENT_TEXT + REPLACE_TXT + " moved to Ready For ERC.']";
    private static final String XPATH_SENT_TO_ERC = "//*[contains(text(), 'Event " + REPLACE_TXT + " status set to Sent to ERC for review.')]";
    private static final String XPATH_ERC_MEETING = "//*[contains(text(), 'Event " + REPLACE_TXT + " status set to ERC Meeting.')]";
    private static final String XPATH_NOT_YET_REVIEWED = "//*[contains(text(), \"No update occurred.  Please choose an ERC Decision other than 'Not yet Reviewed'\")]";
    private static final String XPATH_POST_ERC = "//*[" + XPATH_EVENT_TEXT + REPLACE_TXT + " moved to Post-ERC tab']";
    private static final By xpathChangeStatus = By.xpath("//*[@title='Change Event Status']");
    private static final By xpathSendToERC = By.xpath("//label[(text()='Sent to ERC for review') or (text()='ERC Meeting')]");
    private static final By idSubmit = By.id("btnSubmit");
    private static final String IF_QUEUE_TO_ASRS = "//*[contains(text(), 'Flight Attendant report " + REPLACE_TXT + " added to queue for ASRS.')]";
    private static final By xpathERTComplete = By.xpath("//*[@value='ERC Complete' or @value='ERT Complete']");

    private static final String XPATH_EDIT_BTN = "//*[text()='" + REPLACE_TXT + "']//ancestor::th//following-sibling::th/input[contains(@value,'" + REPLACE_TXT2 + "')]";
    private static final String XPATH_TRACKING_NUM = "//a[contains(@href, 'r=" + REPLACE_TXT + "')]";

    private static By xpathSelectERCDec = By.xpath("//*[contains(@id, 'ERCDecisionList')]");
    private static By xpathUpdateFlight = By.xpath("//*[@value='Update Flight ID']");
    private static By xpathCloseReport = By.xpath("//*[@value='Close']");

    private static final String OBJ_XPATH = "//*[@value='" + REPLACE_TXT + "']";

    private static By idEventIDInput = By.id("EventID");
    private static By idExecuteSearch = By.id("ExecuteSearch");
    private static By xpathReportStatus = By.xpath("//*[contains(@class, 'ResultStatus')]");
    private static By idNumberOfReportsFound = By.id("ResultSummary");

    private static By idSummaryLabel = By.id("SummaryLbl");
    private static By idDescLabel = By.id("DescriptionLbl");
    private static By idPrevMeasureLabel = By.id("PrevMeasuresLbl");

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    private String eventID;

    public String getStrTrackingNumber() {
        return strTrackingNumber;
    }

    public void setStrTrackingNumber(String strTrackingNumber) {
        this.strTrackingNumber = strTrackingNumber;
    }

    private String strTrackingNumber;

    /**
     * navigateTOASAPManagerRole - Navigates to ASAP Manager role
     */
    public void navigateTOASAPManagerRole(){
        try {
            /**switch (USER_ROLE.toUpperCase()) {
                case "FO":
                    commonUtils.navigateToMenu(AsapConstants.FO_MANAGER_PATH);
                    break;
                case "IF":
                    commonUtils.navigateToMenu(AsapConstants.IF_MANAGER_PATH);
                    break;
                case "MX":
                    commonUtils.navigateToMenu(AsapConstants.MX_MANAGER_PATH);
                    break;
                case "DP":
                    commonUtils.navigateToMenu(AsapConstants.DP_MANAGER_PATH);
                    break;
                default:
                    report.report(MessageConstants.FAILED, "Invalid Case option : " + USER_ROLE);
                    break;
            }

           // commonUtils.verifyTitleOfThePage(AsapConstants.ASAP_MANAGER_TITLE, "ASAP Manager");**////
            commonUtils.switchToIFrame(1);

            if(isElementPresent(idUserIdInput) && isElementPresent(idPasswordInput)){

                String strUserID = "ut";
                String strPassword = "demo";
                enterText(idUserIdInput, strUserID);
                enterText(idPasswordInput, strPassword);
                buttonClick(idLoginBtn);
            }

            commonUtils.verifyTitleOfThePage(AsapConstants.ASAP_MANAGER_TITLE, "ASAP Manager");
            printConsole("navigateTOASAPManagerRole passed");

        } catch (Exception e) {
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Navigate to ASAP Manager. Error : " + e.toString());
        }

    }

    /**
     * openSubmittedReports - Open Submitted report based on tracking number
     * @param strTrackingNumber
     * @param strTblContentFromUser
     * @param strReportNumber
     */
    public void openSubmittedReports(String strTrackingNumber, String strTblContentFromUser, String strReportNumber) {

        try {
            selectTheSubmittedReport(strTrackingNumber);
            if (USER_ROLE.equalsIgnoreCase("MX")) {
                String strActTrackingNumber = waitForElement(By.xpath(XPATH_TRACKING_NUM.replace(REPLACE_TXT, strReportNumber))).getText();
                strTrackingNumber = strActTrackingNumber;
            }
            setStrTrackingNumber(strTrackingNumber);
            WebElement eleTrackingID = waitForElement(By.xpath(EVENT_ID.replace(REPLACE_TXT, strTrackingNumber)));
            buttonClick(eleTrackingID);

            if (!USER_ROLE.equalsIgnoreCase("MX")) {
                if (isElementPresent(By.xpath(REF_NUM.replace(REPLACE_TXT, strTrackingNumber)))) {
                    report.reportSelenium(MessageConstants.PASSED, "ASAP report with Tracking number " + strTrackingNumber + " opened successfully.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Failed to open ASAP report with Tracking number " + strTrackingNumber);
                }
            } else {
                if (waitForElement(xpathReportID).getText().contains(strReportNumber)) {
                    report.reportSelenium(MessageConstants.PASSED, "ASAP report with Report number " + strReportNumber + " opened successfully.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Failed to open ASAP report with Report number " + strReportNumber);
                }
            }

            if ((strTblContentFromUser != null) && (!strTblContentFromUser.equalsIgnoreCase(""))) {
                DataTable dataTable = new DataTable(waitForElement(xpathTable));
                String tblContent = dataTable.getFullContentOfTable();
                if (tblContent.contains(strTblContentFromUser)) {
                    report.reportSelenium(MessageConstants.PASSED, "Data content of Report with Tracking number " + strTrackingNumber + " is same as Users submitted report.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Data content of Report with Tracking number " + strTrackingNumber + " is not same as Users submitted report");
                }
            }
        }catch(Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while opening Submitted report. Error : " + e.toString());
        }
    }

    /**
     * selectTheSubmittedReport - Selects the submitted report based on tracking number
     * @param strTrackingNumber
     * @return
     */
    public String selectTheSubmittedReport(String strTrackingNumber){
        try {
            String strEventNumber = strTrackingNumber.substring(1, strTrackingNumber.length() - 6);
            if (isElementPresent(By.xpath(EVENT_ID.replace(REPLACE_TXT, strEventNumber)))) {
                report.reportSelenium(MessageConstants.PASSED, "ASAP report with Event number " + strEventNumber + " opened.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to open ASAP report with Event number " + strEventNumber);
            }
            WebElement eleEventID = waitForElement(By.xpath(EVENT_ID.replace(REPLACE_TXT, strEventNumber)));
            buttonClick(eleEventID);

            String strEventID = waitForElement(idEventID).getText();
            strEventID = strEventID.split("Event ID:")[1].split(", Reference Number")[0].trim();
            report.reportSelenium(MessageConstants.INFO, "ASAP Report with Event ID: " + strEventID + " opened Successfully.");
            setEventID(strEventID);
        }catch(Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while Selecting Submitted report. Error : " + e.toString());
        }
        return getEventID();
    }

    /**
     * changeTheStatusOfTheSubmittedReport - It chnages the status of the submitted reports.
     * @param strTrackingNumber
     * @param strStatus
     * @param strSubStatus
     */
    public void changeTheStatusOfTheSubmittedReport(String strTrackingNumber, String strStatus, String strSubStatus){
        try{
            String strEventID = selectTheSubmittedReport(strTrackingNumber);
            By element = null;

            switch (strStatus.toUpperCase()){
                case "PENDING_ERT":
                case "PENDING_ERC":
                    buttonClick(xpathBtnMoveToPendingERT);
                    element = By.xpath(XPATH_ERT_PENDING.replace(REPLACE_TXT, strEventID));
                    break;
                case "ERT_MEETING":
                    buttonClick(xpathBtnMoveToERTMeeting);
                    element = By.xpath(XPATH_ERT_REVIEW.replace(REPLACE_TXT, strEventID));
                    break;
                case "POST_ERT":
                    selectOption(xpathSelectERCDec, strSubStatus);
                    buttonClick(xpathERTComplete);
                    if(strSubStatus.equalsIgnoreCase("Not yet reviewed"))
                        element = By.xpath(XPATH_NOT_YET_REVIEWED);
                    else
                        element = By.xpath(XPATH_POST_ERT.replace(REPLACE_TXT, strEventID));
                    break;
                case "POST_ERC":
                    selectOption(xpathSelectERCDec, strSubStatus);
                    buttonClick(xpathERTComplete);
                    if(strSubStatus.equalsIgnoreCase("Not yet reviewed"))
                        element = By.xpath(XPATH_NOT_YET_REVIEWED);
                    else
                        element = By.xpath(XPATH_POST_ERC.replace(REPLACE_TXT, strEventID));
                    break;
                case "CLOSE":
                    buttonClick(xpathCloseReport);
                    element = By.xpath(XPATH_CLOSE_ERT.replace(REPLACE_TXT, strEventID));
                    break;
                case "READY_FOR_ERC":
                    buttonClick(By.xpath(OBJ_XPATH.replace(REPLACE_TXT, AsapConstants.BTN_READY_FOR_ERC)));
                    element = By.xpath(XPATH_READY_FOR_ERC.replace(REPLACE_TXT, strEventID));
                    break;
                case "SEND_TO_ERC":
                case "ERC_MEETING":
                    String winHandleOld = getDriver().getWindowHandle();
                    buttonClick(xpathChangeStatus);
                    commonUtils.switchToNewTab(winHandleOld);
                    buttonClick(xpathSendToERC);
                    report.reportSelenium(MessageConstants.INFO, "Status of " + strEventID + " is selected successfully.");
                    buttonClick(idSubmit);
                    getDriver().switchTo().window(winHandleOld);
                    commonUtils.switchToIFrame(1);
                    if(USER_ROLE.equalsIgnoreCase("MX") || USER_ROLE.equalsIgnoreCase("DP")) {
                        element = By.xpath((XPATH_ERC_MEETING.replace(REPLACE_TXT, strEventID)));
                    } else {
                        element = By.xpath(XPATH_SENT_TO_ERC.replace(REPLACE_TXT, strEventID));
                    }
                    break;
                default:
                    report.reportSelenium(MessageConstants.ERROR, "Invalid status " + strStatus);
                    break;
            }
            WebElement element1 = waitForElement(element);
            if(isElementPresent(element))
                report.reportSelenium(MessageConstants.PASSED, "Status change - " + strStatus + " of " + strEventID + " is shown successfully : " + element1.getText());
            else
                report.reportSelenium(MessageConstants.FAILED, "Status change - " + strStatus + " of " + strEventID + " Failed.");
        }catch(Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while changing the status of report. Error : " + e.toString());
        }
    }

    /**
     * navigateToERTTab - navigates to the given tab.
     * @param strTabName
     */
    public void navigateToERTTab(String strTabName) {
        try{
            switch(strTabName.toUpperCase()) {
                case ("PENDING_ERT"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.PENDING_ERT_TAB_NAME)));
                    break;
                case ("ERT_MEETING"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.ERT_MEETING_TAB_NAME)));
                    break;
                case ("POST_ERT"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.POST_ERT_TAB_NAME)));
                    break;
                case ("SEARCH"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.ERT_SEARCH_TAB_NAME)));
                    break;
                case ("READY_FOR_ERC"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.READY_FOR_ERC_TAB_NAME)));
                    break;
                case ("ERC_MEETING"):
                    if (USER_ROLE.equalsIgnoreCase("MX") || USER_ROLE.equalsIgnoreCase("DP"))
                        buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.MX_DP_ERC_MEETING_TAB_NAME)));
                    else
                        buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.ERC_MEETING_TAB_NAME)));
                    break;
                case ("POST_ERC"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.POST_ERC_TAB_NAME)));
                    break;
                case ("PENDING_ERC"):
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.PENDING_ERC_TAB_NAME)));
                    break;
                default :
                    buttonClick(By.xpath(XPATH_TAB_NAME.replace(REPLACE_TXT, AsapConstants.ERT_REPORTS)));
                    break;
            }
            report.reportSelenium(MessageConstants.INFO, "Successfully navigated to Tab: " + strTabName);
        }catch(Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while navigating to tab : " + strTabName + ". Error : " + e.toString());
        }
    }

    /**
     * searchAndCheckTheStatusOfTheReport - Navigates to search tab, and check the status of the report.
     * @param strEventNum
     * @param strStatus
     */
    public void searchAndCheckTheStatusOfTheReport(String strEventNum, String strStatus){
        try{
            if(strEventNum.equalsIgnoreCase(""))
                strEventNum = getEventID();
            navigateToERTTab("SEARCH");
            enterText(idEventIDInput, strEventNum);
            buttonClick(idExecuteSearch);

            if (waitForElement(idNumberOfReportsFound).getText().contains("Your search returned 1 event.")){
                report.report(MessageConstants.PASSED, "Report with Event ID: " + strEventNum + " is found.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Report with Event ID: " + strEventNum + " is not found.");
            }

            WebElement ele = waitForElement(xpathReportStatus);
            if(ele.getText().contains(strStatus)){
                report.reportSelenium(MessageConstants.PASSED, "Successfully Verified, ASAP report with Event ID " + strEventNum + " is " + strStatus);
            }else{
                report.reportSelenium(MessageConstants.FAILED, "Failed to verify ASAP report status with Event ID " + strEventNum + ". Expected : " + strStatus + ", Actual: " + ele.getText());
            }
        }catch(Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while checking the status of the report for eventID : " + strEventNum + ". Error : " + e.toString());
        }
    }

    /**
     * checkFunctionalityOfAAllButtons - Checks the functionality of the buttons.
     * @param strTrackingNumber
     * @param strReportID
     */
    public void checkFunctionalityOfAAllButtons(String strTrackingNumber, String strReportID){
        try{
            boolean blnFO = false;
            By element = By.xpath(MX_FO_QUEUE_TO_ASRS.replace(REPLACE_TXT, strReportID));
            if(USER_ROLE.equalsIgnoreCase("FO"))
                blnFO = true;
            else if(USER_ROLE.equalsIgnoreCase("IF"))
                element = By.xpath(IF_QUEUE_TO_ASRS.replace(REPLACE_TXT, strReportID));

            buttonClick(idPDFImage);
            String strPDFName = "EventReport-" + strTrackingNumber + ".pdf";
            String strFilePath = AsapConstants.DOWNLOAD_PATH + strPDFName;
            commonUtils.checkPresenceOfFile(strFilePath, true);

            if(blnFO) {
                buttonClick(idNoNamePDFImage);
                String strPDFNameNR = strPDFName.replace(strTrackingNumber, strReportID + "-NR");
                strFilePath = AsapConstants.DOWNLOAD_PATH + strPDFNameNR;
                commonUtils.checkPresenceOfFile(strFilePath, true);
            }

            buttonClick(idNoIDPDFImage);
            String strPDFNameNoID = strPDFName.replace(strTrackingNumber, strReportID);
            strFilePath = AsapConstants.DOWNLOAD_PATH + strPDFNameNoID;
            commonUtils.checkPresenceOfFile(strFilePath, true);

            String strOldWindow = getDriver().getWindowHandle();
            buttonClick(idHistoryImage);
            commonUtils.switchToNewTab(strOldWindow);
            commonUtils.closeTabsAndSwitchToInnerFrame(strOldWindow);

            if(!USER_ROLE.equalsIgnoreCase("MX") && !USER_ROLE.equalsIgnoreCase("DP")) {
                buttonClick(xpathOriginalPilotRptImage);
                commonUtils.switchToNewTab(strOldWindow);
                List<By> list = new ArrayList<>();
                list.add(idSummaryLabel);
                list.add(idDescLabel);
                list.add(idPrevMeasureLabel);
                commonUtils.verifyExistenceOfObjects(list);

                commonUtils.closeTabsAndSwitchToInnerFrame(strOldWindow);
            }
            buttonClick(xpathAddInfoFromPilot);
            commonUtils.switchToNewTab(strOldWindow);
            commonUtils.closeTabsAndSwitchToInnerFrame(strOldWindow);

            buttonClick(xpathConfirmEmail);
            commonUtils.switchToNewTab(strOldWindow);
            commonUtils.closeTabsAndSwitchToInnerFrame(strOldWindow);

            buttonClick(xpathQueueForASRS);
            waitForElement(element);
            report.reportSelenium(MessageConstants.PASSED, strReportID + " is moved to ASRS successfully.");
        } catch (Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while checking the checking the functionality of button. Error : " + e.toString());
        }
    }

    /**
     * verifyButtonsOnManagerReport - Verifies the functionality of the buttons
     * @param swaLifeASAPReport
     * @param swaLifeHome
     */
    public void verifyButtonsOnManagerReport(SwaLifeASAPReport swaLifeASAPReport, SwaLifeHome swaLifeHome){
        try{
            String strReportID = waitForElement(idEventID).getText().split("Report ID:")[1].split(", Reference")[0].trim();
            String strTrackNumber = swaLifeASAPReport.getStrTrackingNumber();

            String[] arrHeaderList = {};
            if(USER_ROLE.equalsIgnoreCase("FO")) {
                arrHeaderList = AsapConstants.ARR_FO_HEADERS.clone();
            } else if(USER_ROLE.equalsIgnoreCase("IF")) {
                arrHeaderList = AsapConstants.ARR_IF_HEADERS.clone();
            } else if(USER_ROLE.equalsIgnoreCase("DP")) {
                arrHeaderList = AsapConstants.ARR_DP_HEADERS.clone();
            }

            checkFunctionalityOfAAllButtons(strTrackNumber, strReportID);
            updateFltInfo(swaLifeASAPReport, swaLifeHome);
            checkEditFields(swaLifeASAPReport.getStrTrackingNumber(), arrHeaderList);
        } catch (Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while checking the checking the functionality of button. Error : " + e.toString());
        }
    }

    /**
     * verifyButtonsOnManagerReport - Verifies the functionality of the buttons
     * @param swaLifeMXASAPReport
     */
    public void verifyButtonsOnManagerReport(SwaLifeMXASAPReport swaLifeMXASAPReport){
        try{
            String strReportID = waitForElement(idEventID).getText().split("Report ID:")[1].split(", Reference")[0].trim();
            String strTrackNumber = swaLifeMXASAPReport.getStrTrackingNumber();

            String[] arrHeaderList = {};
            if(USER_ROLE.equalsIgnoreCase("MX")) {
                arrHeaderList = AsapConstants.ARR_MX_HEADERS.clone();
            }

            checkFunctionalityOfAAllButtons(strTrackNumber, strReportID);
            checkEditFields(strTrackNumber, arrHeaderList);
        } catch (Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while checking the checking the functionality of buttons. Error : " + e.toString());
        }
    }

    /**
     * updateFltInfo - Updates the flight info.
     * @param swaLifeASAPReport
     * @param swaLifeHome
     */
    public void updateFltInfo(SwaLifeASAPReport swaLifeASAPReport, SwaLifeHome swaLifeHome) {

        try{
            String strTrackNumber = swaLifeASAPReport.getStrTrackingNumber();
            String strFltNum = String.format("%1$" + 5 + "s", swaLifeHome.getFltNum()).replace(' ', '0');
            String strLabelToReplace = strFltNum + "-" + "K" + swaLifeHome.getFltDeptSt();
            if(USER_ROLE.equalsIgnoreCase("FO") || USER_ROLE.equalsIgnoreCase("DP")){
                strLabelToReplace = strLabelToReplace + "-" + String.format("%1$" + 5 + "s", swaLifeHome.getFltTailNum()).replace(' ', '0');
            }
            buttonClick(idEditButton);
            String strNewFltNum = Integer.toString(randomNumber(4));
            enterText(By.xpath(OBJ_XPATH.replace(REPLACE_TXT, swaLifeHome.getFltNum())), strNewFltNum);
            swaLifeHome.setFltNum(strNewFltNum);

            String strNewDepartSt = AsapConstants.ATL_DOM_STATION;
            enterText(By.xpath(OBJ_XPATH.replace(REPLACE_TXT, swaLifeHome.getFltDeptSt())), strNewDepartSt);
            swaLifeHome.setFltDeptSt(strNewDepartSt);

            String strNewTailNum = Integer.toString(randomNumber(4));
            enterText(By.xpath(OBJ_XPATH.replace(REPLACE_TXT, swaLifeHome.getFltTailNum())), strNewTailNum);
            swaLifeHome.setFltTailNum(strNewTailNum);

            strNewFltNum = String.format("%1$" + 5 + "s", swaLifeHome.getFltNum()).replace(' ', '0');
            String strReplaceText = strNewFltNum + "-" + "K" + swaLifeHome.getFltDeptSt();
            if(USER_ROLE.equalsIgnoreCase("FO") || USER_ROLE.equalsIgnoreCase("DP")){
                strReplaceText = strReplaceText + "-" + String.format("%1$" + 5 + "s", swaLifeHome.getFltTailNum()).replace(' ', '0');
            }

            buttonClick(xpathUpdateFlight);
            scrollToElement(waitForElement(idEventID));
            String strNewTrackingNumber = strTrackNumber.replace(strLabelToReplace, strReplaceText);
            swaLifeASAPReport.setStrTrackingNumber(strNewTrackingNumber);

            report.reportSelenium(MessageConstants.PASSED, strTrackNumber + " details has been updated successfully and new Tracking number is generated " + strNewTrackingNumber);

            openSubmittedReports(strNewTrackingNumber, "", "");
        } catch (Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while updating the flight info. Error : " + e.toString());
        }
    }

    /**
     * checkEditFields - It checks the edit fields.
     * @param strTrackingNumber
     * @param arrHeaders
     */
    public void checkEditFields(String strTrackingNumber, String[] arrHeaders) {
        try{
            selectTheSubmittedReport(strTrackingNumber);

            for (String header: arrHeaders) {
                String strXPath = XPATH_EDIT_BTN.replace(REPLACE_TXT, header);
                String strOldWindow = getDriver().getWindowHandle();
                WebElement element = waitForElement(By.xpath(strXPath.replace(REPLACE_TXT2, "Edit")));
                buttonClick(element);
                if(header.equalsIgnoreCase(AsapConstants.NOTES_HEADER) || header.equalsIgnoreCase(AsapConstants.COMPANY_CORRECTIVE_HEADER)){
                    commonUtils.switchToNewTab(strOldWindow);
                    commonUtils.closeTabsAndSwitchToInnerFrame(strOldWindow);
                }else{
                    if(isElementPresent(By.xpath(strXPath.replace(REPLACE_TXT2, "Save"))) && isElementPresent(By.xpath(strXPath.replace(REPLACE_TXT2, "Cancel")))){
                        scrollToElement(waitForElement(By.xpath(strXPath.replace(REPLACE_TXT2, "Save"))));
                        report.reportSelenium(MessageConstants.PASSED, "User is able to edit the " + header + " field. 'Save' and 'Cancel' button is present");
                    }else{
                        report.reportSelenium(MessageConstants.FAILED, "User is not able to edit the " + header + " field. 'Save' and 'Cancel' button is not present");
                    }
                }
            }
        } catch (Exception e){
            loggerSwaLifeManagerReport.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while verifying edit fields. Error : " + e.toString());
        }
    }







}
