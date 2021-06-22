package com.swacorp.crew.pages.asap;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.AsapConstants;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.DataTable;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class SwaLifeASAPReport extends BasePage {

    private final Logger loggerSWALifeASAPRpt = Logger.getLogger(SwaLifeASAPReport.class);
    ReportUtil report = new ReportUtil();
    CommonUtils commonUtils = new CommonUtils();
    SwaLifeHome swaLifeHome = new SwaLifeHome();
    private static final String REPLACE_TXT = "PLACEHOLDER";
    private static final String USER_ROLE = System.getProperty("role");
    private static final String ASAP_REPORT_TITLE = AsapConstants.ASAP_REPORT_TITLE;

    private final By xpathEmpStatus = By.id("ddlFlightStatus");
    private final By xpathEmpDomicile = By.id("ddlPilotBase");

    private final By xpathPhoneNum = By.id("PrimaryPhoneTxt");
    private final By xpathEmailAdd = By.id("EmailTxt");

    private final By xpathFlightNumber = By.id("FlightNoTxt");
    private final By xpathFlightOrigin = By.id("TextBoxOrigin");
    private final By xpathFlightDest = By.id("TextBoxDestination");
    private final By xpathAirportAlternate = By.id("TextBoxAlternate");
    private final By xpathAircraftType = By.id("DropDownList2");
    private final By xpathTailNum = By.id("TailNoTxt");
    private final By xpathFlightDate = By.id("FlightDateTxt");

    //FO
    private final By xpathTimeEvent = By.id("uxTxtTimeOfEvent");
    private final By xpathTimeZone = By.id("DropDownTimeZone");
    private final By xpathLightiningCond = By.id("Dropdownlist1");
    private static final String MET_RADIO_BTN = "//*[contains(@id, 'rbMetConditions" + REPLACE_TXT + "')]";
    private static final String ARRIVE_DELAY_RADIO_BTN = "//*[contains(@id, 'rbArriving" + REPLACE_TXT + "')]";
    private static final String DEPART_DELAY_RADIO_BTN = "//*[contains(@id, 'rbDeparting" + REPLACE_TXT + "')]";
    //IF
    private final By idOutsideLighting = By.id("Dropdownlist2");

    private final By xpathPosOfSeat = By.id("uxDdlSeat");
    private final By xpathFltHrsIn90Days = By.id("TextboxHoursFlown");
    private final By xpathFltHrsInSeat = By.id("TextboxHoursSeat");
    private final By xpathFltHrsInType = By.id("TextboxHoursType");
    private final By xpathPilotDuty = By.id("uxDdlDuties");
    private final By xpathFamiliarityWithPilot = By.id("uxDdlFamiliarity");

    private final By xpathPhaseOfFlight = By.id("uxDdlPhase");
    private final By xpathTaxiWay = By.id("dtbTaxiway");
    private final By xpathLocation = By.id("dtbApproximateLocation");

    private final By xpathSummaryOfEvent = By.id("TextBox1");
    private final By idEventSummaryIF = By.id("TextboxSummary");
    private static final String EVENT_TYPE = "//label[text()='" + REPLACE_TXT + "']";

    private final By xpathEventDesc = By.id("TextBox1");
    private final By xpathPreventiveMeasure = By.id("TextBox2");

    private final By xpathNextPage = By.id("NextButton");
    private final By xpathSubmitPage = By.id("SubmitButton");
    private final By xpathHideHelp = By.xpath("//*[text()='Hide help']");
    private final By xpathShowHelp = By.xpath("//*[text()='Show help']");

    private final By idEmailBtn = By.id("ButtonEmail");
    private final By idEmailSuccessMsg = By.id("LabelEmailSuccess");
    private final By idRetunToAsapHome = By.id("ButtonDone");
    private final By idButtonPrint = By.id("ButtonPrint");


    private static final String[] arrEventPageStaticText = {"Time event occurred:", "Lighting condition", "Meteorological conditions:", "Was this flight late?", "Arriving?", "Departing?",
            "Phase of flight at start of event:", "Detailed location explanation:"};
    private static final String[] arrEventPageFltInfoStaticText = {"Position or seat assignment during flight:", "Total hours flown in the last 90 days:", "Primary flight duties during the time of the event:", "What is your familiarity with the other pilot?"};
    private static final String[] arrEventTypesToVerify = {"Coordination/communication problems", "Datalink communication event", "Airworthiness - paperwork/manuals",
            "Operations in non compliance - FARs, policy/procedures", "Performance/weight and balance/loading", "Fuel event", "Incursion/excursion",
            "Airport facilities (taxiway/ramp/ground)", "Aircraft damage or encounter", "Weather", "Aircraft malfunction/MEL", "Illness/injury",
            "Fire, smoke or fumes", "Security concern or ground/ramp safety event", "Evacuation event", "International Procedures", "Safety concern"};
    private static final String[] arrThreatTypesToVerify ={"Adverse weather", "Poor airport conditions", "Traffic or radio congestion",
            "Complications due to airline operations (e.g. scheduling, time pressures, minimum restictions)", "Aircraft malfunctions", "Incomplete, incorrect or extra paperwork",
            "Manifest", "ATC Threats", "Cabin events", "Dispatch", "Ground Handling", "Load Planning", "Maintenance event/error", "Crew threats (e.g. fatigue, illness, inexperience)", "Wake Turbulence"};
    private static final String[] arrFlightCrewErrorToVerify = {"In-flight aircraft handling errors (altitude deviation or navigation error)", "On ground aircraft handling errors",
            "Automation error", "Documentation errors", "Procedural Error (checklist, callout, briefing error, etc.)", "Flight crew coordination errors",
            "Pilot to Pilot communication error", "Pilot to ATC communication error", "Pilot to Operations communication error", "International Ops"};

    private final By xpathTrackingNumber = By.xpath("//*[contains(text(), 'Tracking Number')]");

    private static final String DEFAULT_LIGHTENING_VALUE = "[Select lighting]";
    private final By xpathDataTable = By.xpath("//table[@rules='rows']");
    private final By xpathReturnToHome = By.xpath("//a[text()='Return to ASAP Home' or text()='Return to ASAP home']");
    private final By xpathBtnOK = By.xpath("//button[text()='OK']");
    private static final String XPATH_TABS = "//*[@id='TabControl1_" + REPLACE_TXT + "']";

    public String getStrTableContent() {
        return strTableContent;
    }
    public void setStrTableContent(String strTableContent) {
        this.strTableContent = strTableContent;
    }
    private String strTableContent;

    public String getStrReportType() {
        if(strReportType == null)
            strReportType = "";
        return strReportType;
    }
    public void setStrReportType(String strReportType) {
        this.strReportType = strReportType;
    }
    private String strReportType;

    public String getStrTrackingNumber() {
        return strTrackingNumber;
    }
    public void setStrTrackingNumber(String strTrackingNumber) {
        this.strTrackingNumber = strTrackingNumber;
    }
    private String strTrackingNumber;

    /**
     * returnToASAPHome - Returns to ASAP report.
     */
    public void returnToASAPHome(){
        try {
            buttonClick(xpathReturnToHome);
            String strAlert;
            String strTitle = "ASAP Event Report";
            if (USER_ROLE.equalsIgnoreCase("FO")) {
                report.reportSelenium(MessageConstants.INFO, "Navigating back to Home page by saving the incomplete report.");
                buttonClickIfExist(xpathBtnOK);
            } else if (USER_ROLE.equalsIgnoreCase("IF")) {
                strAlert = acceptAlert();
                report.report(MessageConstants.INFO, "Alert Received : " + strAlert);
            }


            commonUtils.verifyTitleOfThePage(strTitle, "ASAP Event report Page");
        }catch(Exception e){
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to return to ASAP Home. Error : " + e.toString());
        }
    }

    public void returnToASAPHomeNew(){
        try {


            String strTitle2 = "ASAP Event Report";
            if (USER_ROLE.equalsIgnoreCase("MX")) {
                String mXUserURL = EnvironmentConstants.ASAP_MX_USER_URL;
                getDriver().get(mXUserURL);


            } else if (USER_ROLE.equalsIgnoreCase("DP")) {

                String dpUserURL = EnvironmentConstants.ASAP_DP_USER_URL;
                getDriver().get(dpUserURL);}

                commonUtils.verifyTitleOfThePage(strTitle2, "ASAP Event Report");
                report.reportSelenium(MessageConstants.INFO, "Navigated back to Page - Launch ASAP Report.");


        }catch(Exception e){
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to return to ASAP Home. Error : " + e.toString());
        }
    }

    /**
     * addEmployeeInformation - Adds employee information.
     * @param strEmpStatus
     * @param strEmpDomicile
     */
    public void addEmployeeInformation(String strEmpStatus, String strEmpDomicile) {
        try {
            verifyShowAndHideHelpSection();

            selectOption(xpathEmpStatus, strEmpStatus);
            if(USER_ROLE.equalsIgnoreCase("FO"))
                selectOption(xpathEmpDomicile, strEmpDomicile);

            report.reportSelenium(MessageConstants.PASSED, "Employee Info Entered successfully.");
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding employee information. Error : " + e.toString());
        }
    }

    /**
     * addContactInformation - Adds contact information
     * @param strEmpPhNum
     * @param strEmpEmail
     */
    public void addContactInformation(String strEmpPhNum, String strEmpEmail) {
        try {
            enterText(xpathPhoneNum, strEmpPhNum);
            enterText(xpathEmailAdd, strEmpEmail);

            report.reportSelenium(MessageConstants.PASSED, "Contact Info Entered successfully.");
            if(getStrReportType().equalsIgnoreCase("NO_FLIGHT"))
                buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding employee information. Error : " + e.toString());
        }
    }

    /**
     * addFlightInfo - Adds flight info
     * @param strFltNum
     * @param strFltOrigin
     * @param strFltDest
     * @param strAlternateAirport
     * @param strAirCraftType
     * @param strTailNum
     * @param strFltDate
     */
    public void addFlightInfo(String strFltNum, String strFltOrigin, String strFltDest, String strAlternateAirport, String strAirCraftType, String strTailNum, String strFltDate) {
        try {
            if(getStrReportType().equalsIgnoreCase("EXISTING_FLIGHT")){
                commonUtils.verifyValueFromEditBox(xpathFlightNumber, strFltNum, "Flight Number");
                commonUtils.verifyValueFromEditBox(xpathFlightOrigin, strFltOrigin, "Flight Depart Station");
                commonUtils.verifyValueFromEditBox(xpathFlightDest, strFltDest, "Flight Arrive station");
                commonUtils.verifyValueFromEditBox(xpathFlightDate, strFltDate, "Flight date");
                commonUtils.verifyValueFromEditBox(xpathTailNum, strTailNum, "Tail Number");
            }else {
                enterText(xpathFlightNumber, strFltNum);
                enterText(xpathFlightOrigin, strFltOrigin);
                enterText(xpathFlightDest, strFltDest);
                enterText(xpathTailNum, strTailNum);
                enterText(xpathFlightDate, strFltDate);
            }
            if(strTailNum == "")
                enterText(xpathTailNum, String.valueOf(randomNumber(3)));
            selectOption(xpathAircraftType, strAirCraftType);
            enterText(xpathAirportAlternate, strAlternateAirport);

            report.reportSelenium(MessageConstants.PASSED, "Flight Info Entered successfully.");
            buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding flight info. Error : " + e.toString());
        }
    }

    /**
     * addEventInfo - Adds event info
     * @param strTime
     * @param strTimeZone
     * @param strLightCondn
     * @param strMetCond
     * @param strIsDelayInArrive
     * @param strIsDelayInDepart
     */
    public void addEventInfo(String strTime, String strTimeZone, String strLightCondn, String strMetCond, String strIsDelayInArrive, String strIsDelayInDepart) {

        try {
            verifyShowAndHideHelpSection();
            if(USER_ROLE.equalsIgnoreCase("FO")) {
                if (commonUtils.verifyExistenceOfStaticField(arrEventPageStaticText)) {
                    report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Event Page.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Events Page.");
                }
                if (!getStrReportType().equalsIgnoreCase("NO_FLIGHT")) {
                    if (commonUtils.verifyExistenceOfStaticField(arrEventPageFltInfoStaticText)) {
                        report.reportSelenium(MessageConstants.PASSED, "All Labels of FltInfo are present in Event Page.");
                    } else {
                        report.reportSelenium(MessageConstants.FAILED, "All Labels of FltInfo are not present in Events Page.");
                    }
                }
            } else if(USER_ROLE.equalsIgnoreCase("IF")) {
                selectOption(idOutsideLighting, "Night");
            }

            if(USER_ROLE.equalsIgnoreCase("FO") || USER_ROLE.equalsIgnoreCase("DP")){
                selectOption(xpathTimeZone, strTimeZone);
                commonUtils.verifyDefaultTextInDropDown(xpathLightiningCond, DEFAULT_LIGHTENING_VALUE, "Lightening Condition");
                buttonClick(By.xpath(MET_RADIO_BTN.replace(REPLACE_TXT, strMetCond)));
            }
            enterText(xpathTimeEvent, strTime);
            selectOption(xpathLightiningCond, strLightCondn);
            buttonClick(By.xpath(ARRIVE_DELAY_RADIO_BTN.replace(REPLACE_TXT, strIsDelayInArrive)));
            if(!USER_ROLE.equalsIgnoreCase("DP"))
                buttonClick(By.xpath(DEPART_DELAY_RADIO_BTN.replace(REPLACE_TXT, strIsDelayInDepart)));

            report.reportSelenium(MessageConstants.PASSED, "Event Info Entered successfully.");
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding event info. Error : " + e.toString());
        }
    }

    /**
     * addFlightExp - Adds flight info
     * @param strPosOfSeat
     * @param strHrsIn90Days
     * @param strHrsInSeat
     * @param strHrsInType
     * @param strPilotDuty
     * @param strFamiliarityWithPilot
     */
    public void addFlightExp(String strPosOfSeat, String strHrsIn90Days, String strHrsInSeat, String strHrsInType, String strPilotDuty, String strFamiliarityWithPilot) {
        try {
            commonUtils.verifyValueFromEditBox(xpathFltHrsIn90Days, "0", "Flt Hrs In 90 Days");
            commonUtils.verifyValueFromEditBox(xpathFltHrsInSeat, "0", "Flt Hrs In Seat");
            commonUtils.verifyValueFromEditBox(xpathFltHrsInType, "0", "Flt hrs in Type");
            selectOption(xpathPosOfSeat, strPosOfSeat);
            enterText(xpathFltHrsIn90Days, strHrsIn90Days);
            enterText(xpathFltHrsInSeat, strHrsInSeat);
            enterText(xpathFltHrsInType, strHrsInType);
            selectOption(xpathPilotDuty, strPilotDuty);
            selectOption(xpathFamiliarityWithPilot, strFamiliarityWithPilot);

            report.reportSelenium(MessageConstants.PASSED, "Flight Experience Entered successfully.");
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding Flight Exp. Error : " + e.toString());
        }
    }

    /**
     * addEventLocation - Adds event location
     * @param strPhaseOfFlight
     * @param strTaxiWay
     * @param strLocationInfo
     */
    public void addEventLocation(String strPhaseOfFlight, String strTaxiWay, String strLocationInfo) {
        try {
            String strTitle = AsapConstants.ASAP_EVENT_TYPE_TITLE;
            selectOption(xpathPhaseOfFlight, strPhaseOfFlight);

            if(USER_ROLE.equalsIgnoreCase("FO") || USER_ROLE.equalsIgnoreCase("DP")) {
                enterText(xpathTaxiWay, strTaxiWay);
                enterText(xpathLocation, strLocationInfo);
            }

            report.reportSelenium(MessageConstants.PASSED, "Event Location Info Entered successfully.");
            buttonClick(xpathNextPage);
           // if(USER_ROLE.equalsIgnoreCase("DP"))
           // {strTitle = ASAP_REPORT_TITLE;}

            commonUtils.verifyTitleOfThePage(strTitle, "Event Type ");
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding Event Location. Error : " + e.toString());
        }
    }

    /**
     * addEventType - Adds event type
     * @param strEventSummary
     * @param strEvent
     * @param strSubEvents
     */
    public void addEventType(String strEventSummary, String strEvent, String strSubEvents) {
        try {
            verifyShowAndHideHelpSection();
            By element = xpathSummaryOfEvent;
            if(USER_ROLE.equalsIgnoreCase("FO")) {
                if (commonUtils.verifyExistenceOfStaticField(arrEventTypesToVerify)) {
                    report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Event Page.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Events Page.");
                }
            }else if(USER_ROLE.equalsIgnoreCase("IF")) {
                element = idEventSummaryIF;
            }
            enterText(element, strEventSummary);

            By xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strEvent));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strSubEvents));
            buttonClick(xpath);

            report.reportSelenium(MessageConstants.PASSED, "Event Type Entered successfully.");
            buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding Event Type. Error : " + e.toString());
        }
    }

    /**
     * addThreatsInfo - Adds threats info
     * @param strThreatCategory
     * @param strThreatSubCategory
     */
    public void addThreatsInfo(String strThreatCategory, String strThreatSubCategory) {
        try {
            verifyShowAndHideHelpSection();
            if(!USER_ROLE.equalsIgnoreCase("DP")) {
                if (commonUtils.verifyExistenceOfStaticField(arrThreatTypesToVerify)) {
                    report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Threat Info Page.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Threat Info Page.");
                }
            }
            By xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strThreatCategory));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strThreatSubCategory));
            buttonClick(xpath);

            report.reportSelenium(MessageConstants.PASSED, "Threat Info Entered successfully.");
            buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding Threats Info. Error : " + e.toString());
        }
    }

    /**
     * addFlightCrewErrors - Add flight crew error
     * @param strFlightCrewError
     * @param strFlightCrewSubError
     */
    public void addFlightCrewErrors(String strFlightCrewError, String strFlightCrewSubError) {
        try {
            verifyShowAndHideHelpSection();
            String strSuccessMsg = "Dispatch Errors Entered successfully.";
            if(!USER_ROLE.equalsIgnoreCase("DP")) {
                if (commonUtils.verifyExistenceOfStaticField(arrFlightCrewErrorToVerify)) {
                    report.report(MessageConstants.PASSED, "All Labels are present in Flight Crew Error Page.");
                } else {
                    report.report(MessageConstants.FAILED, "All Labels are not present in Flight Crew Error Page.");
                }
                strSuccessMsg = "Flight Crew Errors Entered successfully.";
            }
            By xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strFlightCrewError));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strFlightCrewSubError));
            buttonClick(xpath);

            report.reportSelenium(MessageConstants.PASSED, strSuccessMsg);
            buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding Errors. Error : " + e.toString());
        }
    }

    /**
     * addEventDescription - Add event description
     * @param strEventDesc
     * @param strPreventMeasure
     */
    public void addEventDescription(String strEventDesc, String strPreventMeasure) {
        try {
            String strTitle = AsapConstants.ASAP_EVENT_DESCRIPTION_TITLE;
            if(USER_ROLE.equalsIgnoreCase("DP"))
                strTitle = AsapConstants.ASAP_EVENT_DESCRIPTION_TITLE;
            commonUtils.verifyTitleOfThePage(strTitle, "Event Description ");
            enterText(xpathEventDesc, strEventDesc);
            enterText(xpathPreventiveMeasure, strPreventMeasure);

            report.reportSelenium(MessageConstants.PASSED, "Event Type Entered successfully.");
            buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while adding Event Description. Error : " + e.toString());
        }
    }

    /**
     * submitFinalReport - Submits final report
     */
    public void submitFinalReport(){
        try{
            DataTable dataTable = new DataTable(waitForElement(xpathDataTable));
            String strPreviewReportTitle = AsapConstants.ASAP_PREVIEW_REPORT_TITLE;
            String strSubmittedReportTitle = AsapConstants.ASAP_REPORT_SUBMITTED_TITLE;
            if(USER_ROLE.equalsIgnoreCase("DP")){
                strPreviewReportTitle = AsapConstants.ASAP_PREVIEW_REPORT_TITLE;
                strSubmittedReportTitle = AsapConstants.ASAP_REPORT_SUBMITTED_TITLE;
            }
            commonUtils.verifyTitleOfThePage(strPreviewReportTitle, "Preview Report ");
            String strTblContent = dataTable.getFullContentOfTable();
            setStrTableContent(strTblContent);
            buttonClick(xpathSubmitPage);
            waitByTime(1000);
            commonUtils.verifyTitleOfThePage(strSubmittedReportTitle, "ReportSubmitted");

            strTrackingNumber = getDriver().findElement(xpathTrackingNumber).getText();
            setStrTrackingNumber(strTrackingNumber.replace("Tracking Number:", "").trim());
            report.report(MessageConstants.INFO, "Successfully Fetched the tracking number : " + getStrTrackingNumber());

            verifyPrintAndEmailButton(true);
        }catch(Exception e){
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while Submitting final report. Error : " + e.toString());
        }
    }

    /**
     * verifyShowAndHideHelpSection - Verifies show and hide help section
     */
    public void verifyShowAndHideHelpSection(){
        try {
            if (isElementVisible(xpathHideHelp)) {
                report.report(MessageConstants.PASSED, "Help section is displayed and Hide Help button is present.");
                buttonClick(xpathHideHelp);
                if (isElementVisible(xpathShowHelp)) {
                    report.report(MessageConstants.PASSED, "Help section is collapsed and Show Help button is present.");
                } else {
                    report.report(MessageConstants.FAILED, "Help section is collapsed and Show Help button is present.");
                }
            } else {
                report.report(MessageConstants.FAILED, "Hide Help section is present.");
            }
        }catch(Exception e){
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while Submitting final report. Error : " + e.toString());
        }
    }

    /**
     * verifyPreviewReportEditOption - Verifies the preview report
     * @param arrLists
     */
    public void verifyPreviewReportEditOption(String[] arrLists){
        try {
            DataTable dataTable = new DataTable(waitForElement(xpathDataTable));
            String strTitle = "review Report";
            if (USER_ROLE.equalsIgnoreCase("MX") || USER_ROLE.equalsIgnoreCase("DP"))
                strTitle = "review Report";
            commonUtils.verifyTitleOfThePage(strTitle, "review Report");
            String strTblContent = dataTable.getFullContentOfTable();
            setStrTableContent(strTblContent);
            strTblContent = strTblContent.replace(" ", "");
            for (String strList : arrLists) {
                String strStringToVerify = strList + " - This page is missing some required information.";
                if (strTblContent.contains(strStringToVerify.replace(" ", ""))) {
                    report.report(MessageConstants.PASSED, "Successfully verified the text : " + strStringToVerify);
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Failed to verify the text : " + strStringToVerify);
                }
            }
        }catch(Exception e){
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while verifying preview of the report. Error : " + e.toString());
        }
    }

    /**
     * navigateToTabNavigates to the tab
     * @param strTab
     */
    public void navigateToTab(String strTab){
        try{
            String strTitle = strTab;
            buttonClick(By.xpath(XPATH_TABS.replace(REPLACE_TXT, strTab.replace(" ", ""))));
            if(USER_ROLE.equalsIgnoreCase("MX") || USER_ROLE.equalsIgnoreCase("DP"))
                strTitle = "Event Information";
            commonUtils.verifyTitleOfThePage(strTitle, strTab + " Tab");
        }catch(Exception e){
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while Navigating to tab " + strTab + ". Error : " + e.toString());
        }
    }

    /**
     * addFactors - adds factors
     * @param strFactor
     * @param strFarPolicies
     */
    public void addFactors(String strFactor, String strFarPolicies) {
        try {
            commonUtils.verifyTitleOfThePage("Factors", "Factors Page");

            By xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strFactor));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strFarPolicies));
            buttonClick(xpath);

            report.reportSelenium(MessageConstants.PASSED, "Factors Info Entered successfully.");
            buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while Adding factors. Error : " + e.toString());
        }
    }

    /**
     * addDetectionAndReaction - Adds detection and reaction
     * @param strDetectedProcess
     * @param strDetectedTime
     * @param strReaction
     */
    public void addDetectionAndReaction(String strDetectedProcess, String strDetectedTime, String strReaction) {
        try {
            commonUtils.verifyTitleOfThePage("Flight DetectionReaction Errors", "Flight Detection and Reaction Errors Page");

            By xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strDetectedProcess));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strDetectedTime));
            buttonClick(xpath);
            xpath = By.xpath(EVENT_TYPE.replace(REPLACE_TXT, strReaction));
            buttonClick(xpath);

            report.reportSelenium(MessageConstants.PASSED, "Threat Info Entered successfully.");
            buttonClick(xpathNextPage);

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while verifying preview of the report. Error : " + e.toString());
        }
    }

    /**
     * verifyPrintAndEmailButton - verifies the functionality of print and email button
     * @param blnReturnToHome
     */
    public void verifyPrintAndEmailButton(boolean blnReturnToHome) {
        try {

            buttonClick(idEmailBtn);
            buttonClick(idButtonPrint);
            if(waitForElement(idEmailSuccessMsg).getText().equalsIgnoreCase(AsapConstants.EMAIL_SUCCESS_MSG))

                report.reportSelenium(MessageConstants.PASSED, "Successfully verified the success message received after clicking on 'Send mail' button. Verified text is : " + AsapConstants.EMAIL_SUCCESS_MSG);

            else
                report.reportSelenium(MessageConstants.FAILED, "Failed to verify the success message received after clicking on 'Send mail' button. Expected text is : " + AsapConstants.EMAIL_SUCCESS_MSG);

            if(blnReturnToHome) {
                buttonClick(idRetunToAsapHome);
                waitByTime(1000);
                report.reportSelenium(MessageConstants.INFO, "Successfully navigated to home page by clicking on 'Return to the ASAP Report'");
            }

        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while verifying print and email button. Error : " + e.toString());
        }
    }

}
