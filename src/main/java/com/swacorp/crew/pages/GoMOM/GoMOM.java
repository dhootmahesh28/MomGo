package com.swacorp.crew.pages.GoMOM;

import com.google.common.collect.Iterables;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.GoMomConstants;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.DataTable;

import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class GoMOM extends BasePage {


    private final Logger loggerGoMOM = Logger.getLogger(GoMOM.class);
    ReportUtil report = new ReportUtil();
    CommonUtils commonUtils = new CommonUtils();

    private static final By xpathError = By.xpath("//*[@class = 'eipErrors']");
    private static final By xpathMEreview = By.xpath("//*[@id='numberOfMes']/a");
    private static final By xpathDone = By.xpath("//a[text()='Done']");
    private static final By xpathCancel = By.xpath("//*[text()='Cancel']");
    private static final By xpathDelete = By.xpath("/html/body/form/div/div[2]/div[2]/a[2]");//Wrong
    private static final By xpathNewMomReportBtn = By.xpath("/html/body/form/div/div[2]/div[1]/a[1]");//Wrong
    private static final By idMOMReportHeader = By.id("momReportLegend");
    private static final By xpathSubmitterJobCode = By.xpath("//*[@id='submitterJobCode']");
    private static final By iDAircraftNumber = By.id("aircraftNumber");
    private static final By iDEventCity = By.id("eventCity");
    private static final By iDFlightNumber = By.id("flightNumber");
    private static final By iDOrigCity = By.id("origCity");
    private static final By iDDestCity = By.id("destCity");
    private static final By iDEventTime = By.id("eventTime");
    private static final By xpathNextBtn = By.xpath("//a[text() = 'Next']");
    private static final By xpathSaveAndCont = By.xpath("//a[text()='Save and Continue Later']");//
    private static final By xpathIncompleteTable = By.xpath("//*[@ class='swaTable']");
    private static final By xpathDeleteBtn = By.xpath("//a[ text()='Delete']");// momSpecialForms
    private static final By idSpecialForm = By.id("momSpecialForms");
    private static final By idMeLink = By.id("meLink");
    private static final By idAddME = By.xpath("//*[text()='Add Another ME']");
    private static final By xpathEvent = By.xpath("//*[@class='legend'] ");
    private static final By xpathUcReview = By.xpath("//*[@id=\"numberOfUcs\"]/a");
    private static final By xpathUMReview = By.xpath("//*[@id=\"numberOfUms\"]/a");
    private static final By xpathAddUC = By.xpath("//*[text()='Add Another UC']");
    private static final By xpathAddUM = By.xpath("//*[text()='Add Another UM']");
    private static final By idLastName = By.id("lastName");//
    private static final By idContactLastName = By.id("contactLastName");
    private static final By idContactFirstName = By.id("contactFirstName");
    private static final By idFirstName = By.id("firstName");
    private static final By xpathGender = By.xpath("//*[@value='M']");
    private static final By idEvent = By.xpath("//*[@id='event0']");
    private static final By xpathUnrulyLink = By.xpath("//*[text()='Unruly Customer(s)']");
    private static final By xpathUnaccompainedLink = By.xpath("//*[@id='umLinkGroup']");
    private static final By xpathAddBtn = By.xpath("//*[text()='Add']");
    private static final By xpathEditBtn = By.xpath("//*[text()='Edit']");
    private static final By xpathAdditionalInfoBtn = By.xpath("//*[text()='Add Additional Information']");
    private static final By xpathNewUMContact = By.xpath("//*[@id='editUmsButton']");
    private static final By idStreetAddress = By.id("streetAddress");
    private static final By idStreetAddressLine2 = By.id("streetAddressLine2");
    private static final By idCity = By.id("city");
    private static final By idZipCode = By.id("zipcode");
    private static final By idPhone = By.id("phone");
    private static final By xpathSubmit = By.xpath("//*[text()='Submit']");
    private static final By xpathSubmitMsg = By.xpath("//*[contains(text(),'The MOM Report Id')]");
    private static final By xpathReviewAndSub = By.xpath("/html/body/form/div/div[3]/div[3]/div/a[2]");//Wrong
    private static final By xpathOkButton = By.xpath("//a[contains(text(),'Ok')]");
    private static final By xpathClickHere = By.xpath("//*[@id='MOMUcsCustomerContact']/div[2]/div[27]/a");//Wrong
    private static final By xpathClickHereFamily = By.xpath("//*[@id='MOMMesCustomerContact']/div[21]/a");//Wrong
    private static final By xpathClickHereRestrain = By.xpath("//*[@id='MOMUcsCustomerContact']/div[2]/div[23]/a");//Wrong
    private static final By xpathClickHereAssisted = By.xpath("//*[@id='MOMMesCustomerContact']/div[24]/a");//Wrong


    private static final String[] arrAdditionalContactInfoFields = {"Additional Contact Information", "*Street Address:", "Apt #:",
            "*City:", "*State:", "Telephone Number:", "Zip:"};

    private static final String[] arrContactInformationUM = {"Contact Information for Unaccompanied Minors", "*Last Name:", "*First Name:",
            "Street Address:", "Apt #:", "City:", "State:", "Zip:", "Telephone Number:", "Additional Contact Information", "Brief Description of conversation with Contact Person:"};


    private static final String[] arrGeneralInfoFields = {"Aircraft#", "*Event City", "*Flight#", "*Event Date (mm/dd/yyyy) ", "*Original City Pair",
            "*To", "*Event Time"};

    private static final String[] arrMOMQuestions = {"Please Click the following that apply", "Was an emergency declared?  ", "Did the majority of the Customers express concern about the flight?  ",
            "Did the Customers experience a highly unusual amount of inconvenience?  ", "Did the Customers display  obvious physical or emotional discomfort?  "
            , "Do you feel a follow up message to the Customer is necessary?  ", "Were you able to provide our Customers with an explanation about what occurred?  ",
            "Is this report for a disability-related incident AND have you completed the WN-314/WN-315?  ", "Was this flight an Ops Recovery (SWORD) Flight?  ",
            "Did you issue SLVs for the affected Customers?  ", "Did you reroute Customers?  ", "If SLVs were not provided, did you e-mail a specific list of affected Customers to the PCS Team-DG?  "};

    private static final String[] arrEventtypes = {"Please Click the following that apply:", "Air Turnback  ",
            "Compound Problems  ", "Lengthy Delay  ", "Misboard/Thru Override Customer  ", "Rejected Takeoff  ", "Bomb Threat  ",
            "Diversion  ", "Medical Emergency  ", "Unruly Customer  ", "Originator Delay  ", "Unaccompanied Minor  ", "Young Traveler (ages 12-17)  ",
            "Other  ", "If Other Please Explain:"};

    private static final String[] arrDisrutionTypes = {"Please Click the following that apply:", "Abnormal aircraft vibration/noise  "
            , "Cabin Pressurization Problem  ", "Customers told to get in the \"brace\" position  ", "Delay was under four hours  ", "Flaps/Slats Malfunction  ",
            "Landing Crew Gear Problem  ", "Severe Turbulence  ", "Smoke and/or Smell In Cabin  ", "Systems Failure  ", "The Customers were truly shaken up  ",
            "ATC Delay (on taxiway longer than two hours)  ", "Crew Duty Time Expired(delay longer than two hours)  ", "Delay was over four hours  ", "Engine shut down  "
            , "Majority of flight was completed before turnback  ", "Lengthy/Multiple MX delays (more than four hours)  ", "Severe Weather  ", "Sudden Braking  ", "The Captain declared an emergency onboard  ",
            "The Captain declared an emergency onboard  ", "There was obvious physical or emotional discomfort  "};

    private static final String[] arrAdditionEventInfo = {"Event Summary (beginning to end):", "What type of local Customer Accommodations (if any) were provided?", "How did your Team respond to this event?"};

    private static final String[] arrPrintPreview = {"Ground Ops MOM Irregularity Report", "General Information",
            "MOM Questions", "Event Types", "Disruption Types", "Additional Event Information", "Unaccompanied Minors Onboard",
            "Medical Emergency", "Unruly Customer(s)"};

    //*[@name='additionalEventText0']

    private static final By xpathadditionalEvenMSG0 = By.xpath("//*[@name='additionalEventText0']");
    private static final By xpathadditionalEvenMSG1 = By.xpath("//*[@name='additionalEventText1']");
    private static final By xpathadditionalEvenMSG2 = By.xpath("//*[@name='additionalEventText2']");
    private static final By xpathadditionalEvenMSG3 = By.xpath("//*[@name='additionalEventText3']");

    private static final By xpathValueDisType = By.xpath("//*[@value='AAV']");
    private static final By xpathDateRangeRadioBtn = By.xpath("//*[@id='byDateRadio']");
    private static final By xpathDateFrom = By.xpath("(//*[@id='momAdmin']/div/div[*]/a/img)[1]");//Wrong
    private static final By xpathDateTo = By.xpath("(//*[@id='momAdmin']/div/div[*]/a/img)[2]");//Wrong
    private static final By xpathStation = By.xpath("//*[@id=\"stationDesc\"]");
    private static final By xpathStationCode = By.xpath("//*[@id=\"stationCode\"]");
    private static final By xpathAddNewStationBtn = By.xpath("//a[contains(text(),'Add New Station')]");
    private static final By xpathEditStationBtn = By.xpath("//a[contains(text(),'Edit')]");
    private static final By xpathDeleteStationBtn = By.xpath("//a[contains(text(),'Delete')]");
    private static final By xpathOkBtn = By.xpath("//a[contains(@href,'ReportsAction')]");
    private static final By xpathSaveBtn = By.xpath("//a[contains(text(),'Save')]");
    private static final By xpathCancelBtn = By.xpath("//a[contains(text(),'Cancel')]");
    private static final By xpathSearchBtn = By.xpath("//*[@id='momAdmin']/div/div[6]/div/a");//Wrong
    private static final By xpathSearchAgainBtn = By.xpath("//*[contains(text(),'Search Again')]");
    private static final By xpathSearchByRptId = By.name("reportId");
    private static final By xpathNewSearchBtn = By.xpath("//*[contains(text(),'New Search')]");
    private static final By xpathBackBtn = By.xpath("//*[contains(text(),'Back')]");
    private static final By xpathMessageWhenRptIDPresent = By.xpath("//*[contains(text(),'Report Id')]");
    private static final By xpathMessageWhenSearchWithBlankReportIdField = By.xpath("//*[contains(text(),'Please choose to search')]");
    private static final By xpathMessageWhenStationInfoSaved = By.xpath("//*[contains(text(),'The MOM Station having station code as')]");
    private static final By xpathSearchWithCharInReportIdField = By.xpath("//*[contains(text(),'a single numeric')]");
    private static final By xpathMessageWhenNoReportFound = By.xpath("//*[contains(text(),'There were no Complete Mom Reports found')]");
    private static final By xpathMessageWhenStationCodeIsBlank = By.xpath("//*[contains(text(),'A Station Code is required')]");
    private static final By xpathMessageWhenStationNameIsBlank = By.xpath("//*[contains(text(),'A Station Description is required')]");
    private static final By xpathMessageWhenStationCodeAlreadyExists = By.xpath("//*[contains(text(),'This station Code already exists')]");
    private static final By xpathCheckBox = By.xpath("//input[@name='deleteReports']");
    private static final String NAVIGATE_TO_SPECIAL_FORM = "Navigated to Special Customer Forms";
    private static final String MSG_CONTACT_INFO_DELETED = " Contact Info Deleted successfully ";
    private static final String MSG_CONTACT_INFO_NOT_DELETED = "Contact Info NOT  Deleted successfully ";
    private static final String MSG_SEARCH_OPTIONS = "'Please choose to search by 'Report ID' or by 'From Date' and 'To Date' and enter valid values. This is required data' Message displayed successfully";
    private static final String MSG_ERROR_NOT_DISPLAYED = "Error Message is not Displayed";
    private static final String MSG_ADD_STATION_PASS = "Navigated to 'Add New Station' window successfully";
    private static final String MSG_ADD_STATION_FAIL = "Failed to Navigate 'Add New Station' window";
    private static final String DATA_LAST_NAME = "testLastname";
    private static final String DATA_FIRST_NAME = "testfirstname";
    private static final String NAVIGATION_ADDITIONAL_CONTACT_INFO = "Navigated to Additional Contact Information";
    private static final String MSG_ERROR_FIRST_NAME = "A First Name is required. Please enter a First Name.";
    private static final String MSG_ERROR_FIRST_NAME1 = "Required-FirstNameError is NOT displayed";
    private static final String MSG_ERROR_LAST_NAME1 = "Required-LastNameError is NOT displayed";
    private static final String MSG_ERROR_LAST_NAME = "A Last Name is required. Please enter a Last Name.";
    private static final String MSG_ERROR_IN_SCREEN= "Error in screens";
    private static final String INCORRECT_INFO_MSG="Incorrect Info message displayed";
    private static final String INFO_MSG="Info message displayed";

    public void navigateToGeneralInfo() {
        try {

            if (isElementPresent(xpathNewMomReportBtn)) {
                buttonClick(xpathNewMomReportBtn);
            }
            isElementPresent(idMOMReportHeader);
            report.reportSelenium(MessageConstants.PASSED, "Navigated to General Info screen");
        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Navigate to screen - General Information. Error : " + e.toString());
        }
    }


    public void generalInfoErrorsValidation(String strSubTitle, String strAircraft, String strEventCity, String strFlight, String strCityfrom, String strCityto, String strEventTime) {
        try {
            commonUtils.switchToFrame(1);
            if (commonUtils.verifyExistenceOfStaticField(arrGeneralInfoFields)) {
                report.reportSelenium(MessageConstants.PASSED, "All Labels are present in GeneralInfo Page.");
            }



            buttonClick(xpathNextBtn);

            String strAlert1 = "Event Time is not in a valid format (HH:MM)";
            String alert1 = getDriver().switchTo().alert().getText();

            if (alert1.equalsIgnoreCase(strAlert1)) {
                report.report(MessageConstants.PASSED, INFO_MSG);
            } else {
                report.report(MessageConstants.FAILED, INCORRECT_INFO_MSG);
            }
            acceptAlert();



            enterText(iDAircraftNumber, strAircraft);
            selectOption(iDEventCity, strEventCity);
            enterText(iDFlightNumber, strFlight);
            selectOption(iDOrigCity, strCityfrom);
            selectOption(iDDestCity, strCityto);
            enterText(iDEventTime, strEventTime);
            report.reportSelenium(MessageConstants.INFO, "Entered all fields values except -Sumitter's Title");
            buttonClick(xpathNextBtn);
            generalInfoErrorMessage(true,false,false,false,false);

            selectOption(xpathSubmitterJobCode, strSubTitle);
            clearField(iDFlightNumber);

            report.reportSelenium(MessageConstants.INFO, "Entered all fields values except -Flight Number");
            buttonClick(xpathNextBtn);
            generalInfoErrorMessage(false,true,false,false,false);

            enterText(iDFlightNumber, strFlight);
            selectOption(iDEventCity,"");

            report.reportSelenium(MessageConstants.INFO, "Entered all fields values except -Event City");
            buttonClick(xpathNextBtn);
            generalInfoErrorMessage(false,false,true,false,false);


            selectOption(iDEventCity, strEventCity);

            selectOption(iDOrigCity, "");
            report.reportSelenium(MessageConstants.INFO, "Entered all fields values except -Origin City");
            buttonClick(xpathNextBtn);
            generalInfoErrorMessage(false,false,false,true,false);



            selectOption(iDOrigCity, strCityfrom);
            selectOption(iDDestCity, "");
            report.reportSelenium(MessageConstants.INFO, "Entered all fields values except -Destination City");
            buttonClick(xpathNextBtn);
            generalInfoErrorMessage(false,false,false,false,true);

            selectOption(iDDestCity, strCityto);

            buttonClick(xpathNextBtn);


        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error in General INfo screen");
        }
    }

    public void saveContinue(){
        try {
            buttonClick(xpathSaveAndCont);

        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Save and Continue button not working");
        }

    }

    public void addGeneralInfo(String strSubTitle, String strAircraft, String strEventCity, String strFlight, String strCityfrom, String strCityto, String strEventTime) {
        try {

            commonUtils.switchToFrame(1);

            selectOption(xpathSubmitterJobCode, strSubTitle);

            enterText(iDAircraftNumber, strAircraft);
            selectOption(iDEventCity, strEventCity);
            enterText(iDFlightNumber, strFlight);
            selectOption(iDOrigCity, strCityfrom);
            selectOption(iDDestCity, strCityto);
            enterText(iDEventTime, strEventTime);
            report.reportSelenium(MessageConstants.PASSED, "Added  General Info ");


        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to add info to screen - General Information. Error : " + e.toString());
        }

    }

    public void momQuestionsScreen() {
        try {

            commonUtils.switchToFrame(1);
            if (commonUtils.verifyExistenceOfStaticField(arrMOMQuestions)) {
                report.reportSelenium(MessageConstants.PASSED, "All Labels are present in MOM Questions Page.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in MOM Questions Page.");
            }

            String stralert = "You have selected No for all the questions, would you like to submit an informal report to the MOM group?";
            buttonClick(xpathNextBtn);
            String alert = getDriver().switchTo().alert().getText();

            if (alert.equalsIgnoreCase(stralert)) {
                report.report(MessageConstants.PASSED, INFO_MSG);

            } else {
                report.reportSelenium(MessageConstants.FAILED, INCORRECT_INFO_MSG);
            }

            acceptAlert();

        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, MSG_ERROR_IN_SCREEN);
        }

    }

    public void eventTypescreen(){
        try {
            commonUtils.switchToFrame(1);
            if (isElementPresent(xpathEvent)) {
                report.reportSelenium(MessageConstants.PASSED, "Navigated to Event Types screen.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "NOT Navigated to Event Types screen.");
            }

            if (commonUtils.verifyExistenceOfStaticField(arrEventtypes)) {
                report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Event Types Page.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Event TypesPage.");
            }

            String strAlert1 = "You must select one of the event types";
            buttonClick(xpathNextBtn);
            String alert1 = getDriver().switchTo().alert().getText();

            if (alert1.equalsIgnoreCase(strAlert1)) {
                report.report(MessageConstants.PASSED, INFO_MSG);
            } else {
                report.report(MessageConstants.FAILED, "Incorrect Info message displayed ");
            }
            acceptAlert();


        }catch (Exception e) {
        loggerGoMOM.error(e);
        report.reportSelenium(MessageConstants.ERROR, MSG_ERROR_IN_SCREEN);
    }

}

    public void disrutiontypescreen(){
        try {
            buttonClick(idEvent);
            buttonClick(xpathNextBtn);

            commonUtils.switchToFrame(1);
            if (isElementPresent(xpathEvent)) {
                report.reportSelenium(MessageConstants.PASSED, "Navigated to Disruption Types screen.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "NOT Navigated to Disruption Types screen.");
            }

            if (commonUtils.verifyExistenceOfStaticField(arrDisrutionTypes)) {
                report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Disruption Types Page.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Disruption Types Page.");
            }
buttonClick(xpathValueDisType);
            buttonClick(xpathNextBtn);



        }catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, MSG_ERROR_IN_SCREEN);
        }

    }

    public void additionalinfoEventscreen(){
        try {
            commonUtils.switchToFrame(1);
            if (isElementPresent(xpathEvent)) {
                report.reportSelenium(MessageConstants.PASSED, "Navigated to Additional Event Information screen.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "NOT Navigated to Additional Event Information screen.");
            }

            if (commonUtils.verifyExistenceOfStaticField(arrAdditionEventInfo)) {
                report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Additional Event Information Page.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Additional Event Information Page.");
            }
  enterText(xpathadditionalEvenMSG0,"GoMOM");
            enterText(xpathadditionalEvenMSG1,"Under");
            enterText(xpathadditionalEvenMSG2,"Automation");
            enterText(xpathadditionalEvenMSG3,"Influence");




            buttonClick(xpathNextBtn);
        }catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, MSG_ERROR_IN_SCREEN);
        }

    }

    public void specialcustFormscreen(){
        try {
            if (isElementPresent(xpathEvent)) {
                report.reportSelenium(MessageConstants.PASSED, "Navigated to Special Customer Forms screen.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "NOT Navigated to Special Customer Forms screen.");
            }
            buttonClick(xpathNextBtn);

            commonUtils.switchToFrame(1);
            if (isElementPresent(xpathEvent)) {
                report.reportSelenium(MessageConstants.PASSED, "Navigated to Ready to Send?");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "NOT Navigated to Ready to Send?");
            }

            buttonClick(xpathReviewAndSub);


        }catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, MSG_ERROR_IN_SCREEN);
        }

    }

    public void previewandSubmit(){
        try {
            commonUtils.switchToFrame(1);
            if (commonUtils.verifyExistenceOfStaticField(arrPrintPreview)) {
                report.reportSelenium(MessageConstants.PASSED, "Navigated to Print preview screen and all labels are present");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Error in labels in print preview screen");
            }

            buttonClick(xpathSubmit);

            if (isElementPresent(xpathSubmitMsg)) {
                report.reportSelenium(MessageConstants.PASSED, "GoMOM Report is submitted successfully and confirmation message is displayed");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "GoMOM Report is not submitted successfully");
            }

            String text = getDriver().findElement(xpathSubmitMsg).getText();
            GoMomConstants.SubmittedReportNumber = text.replaceAll("[^0-9]", "");
            buttonClick(xpathOkButton);


        }catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, MSG_ERROR_IN_SCREEN);
        }

    }




    public int verifyDataInIncompleteTable(String reportID, String dateAndtime, boolean blnIsPresent) {
        int rowNum = -1;
        try {
            String strTextToVerify = reportID + " " + dateAndtime;
            if (isElementPresent(xpathIncompleteTable) && blnIsPresent) {
                DataTable dtIncompleteTable = new DataTable(waitForElement(xpathIncompleteTable));
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
            } else {
                if (!blnIsPresent) {
                    report.reportSelenium(MessageConstants.PASSED, "The incomplete report details table and Data: " + strTextToVerify + " is not present.");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Incomplete report details tables is not present. Expected value : " + strTextToVerify);
                }
            }
        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to verify Data in Incomplete Report. Error : " + e.toString());
        }
        return rowNum;
    }


    public void navigateMEAdditionalContact() {

        buttonClick(xpathNextBtn);
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(0);
        buttonClick(idSpecialForm);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATE_TO_SPECIAL_FORM);


        buttonClick(idMeLink);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Review Medical Emergencies");
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(1);

        buttonClick(idAddME);

        String lastname = DATA_LAST_NAME;
        String firstname = DATA_FIRST_NAME;
        enterText(idLastName, lastname);
        enterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        report.reportSelenium(MessageConstants.PASSED, "ADDED Customer's Contact Information");
        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATION_ADDITIONAL_CONTACT_INFO);
    }

    public void navigateUnrulyAdditionalContact() {

        buttonClick(xpathNextBtn);
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(0);
        buttonClick(idSpecialForm);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATE_TO_SPECIAL_FORM);


        buttonClick(xpathUnrulyLink);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Review Unruly Customers");
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(1);

        buttonClick(xpathAddUC);


        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);

        String strtest1 = "test";
        enterText(idLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);

        buttonClick(xpathEditBtn);


        String lastname = DATA_LAST_NAME;
        String firstname = DATA_FIRST_NAME;

        clearAndEnterText(idLastName, lastname);
        clearAndEnterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        report.reportSelenium(MessageConstants.PASSED, "ADDED unruly Customer's Information");

        buttonClick(xpathAddUC);


        enterText(idLastName, lastname);
        enterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        buttonClick(xpathDelete);
        getDriver().switchTo().alert().accept();

        buttonClick(xpathDone);
        buttonClick(xpathUcReview);
        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATION_ADDITIONAL_CONTACT_INFO);
    }

    public void navigateAndaddingUnaccompaniedMinorsNavigateContactInfo() {

        buttonClick(xpathNextBtn);
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(0);
        buttonClick(idSpecialForm);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATE_TO_SPECIAL_FORM);


        buttonClick(xpathUnaccompainedLink);
        report.reportSelenium(MessageConstants.PASSED, "Review Unaccompanied Minors");
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(1);

        buttonClick(xpathAddUM);


        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);

        String strtest1 = "test";
        enterText(idLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);

        buttonClick(xpathEditBtn);


        String lastname = DATA_LAST_NAME;
        String firstname = DATA_FIRST_NAME;

        clearAndEnterText(idLastName, lastname);
        clearAndEnterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        report.reportSelenium(MessageConstants.PASSED, "ADDED  Unaccompanied Minors  Information");

        buttonClick(xpathAddUM);


        enterText(idLastName, lastname);
        enterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        buttonClick(xpathDelete);
        getDriver().switchTo().alert().accept();

        buttonClick(xpathDone);
        buttonClick(xpathUMReview);
        buttonClick(xpathEditBtn);
        buttonClick(xpathNewUMContact);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Contact Person Information(Required)");
    }

    public void navigateAndaddingMENavigateContactInfo() {

        buttonClick(xpathNextBtn);
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(0);
        buttonClick(idSpecialForm);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATE_TO_SPECIAL_FORM);


        buttonClick(idMeLink);
        report.reportSelenium(MessageConstants.PASSED, "Review Medical Emergencies");
        getDriver().switchTo().parentFrame();
        commonUtils.switchToFrame(1);

        buttonClick(idAddME);


        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);

        String strtest1 = "test";
        enterText(idLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);

        buttonClick(xpathEditBtn);


        String lastname = DATA_LAST_NAME;
        String firstname = DATA_FIRST_NAME;

        clearAndEnterText(idLastName, lastname);
        clearAndEnterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        report.reportSelenium(MessageConstants.PASSED, "ADDED  Medical Emergencies Customer's Contact Information ");

        buttonClick(idAddME);


        enterText(idLastName, lastname);
        enterText(idFirstName, firstname);
        buttonClick(xpathGender);

        buttonClick(xpathAddBtn);
        buttonClick(xpathDelete);
        getDriver().switchTo().alert().accept();

        buttonClick(xpathDone);
        buttonClick(xpathMEreview);
        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);
        report.reportSelenium(MessageConstants.PASSED, NAVIGATION_ADDITIONAL_CONTACT_INFO);
    }

    public void generalInfoErrorMessage(boolean blnSubmitterTitle,boolean blnFlghtnumber,boolean blnEventCity,boolean blnOriginalcity,boolean blnDesCity) {
        WebElement element = waitForElement(xpathError);
        String strErrors = element.getText();

        if (blnSubmitterTitle && strErrors.contains("A Submitter's Title is required. Please select a Submitter's Title.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-Submitters Title Error is displayed");
        } else if (!blnSubmitterTitle && !strErrors.contains("A Submitter's Title is required. Please select a Submitter's Title"))

        report.reportSelenium(MessageConstants.INFO, "Submitters Title Error is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-Submitters Title Error is NOT displayed");
        }

        if (blnFlghtnumber && strErrors.contains("A Flight Number must be entered. This is a required field.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-Flight Error is displayed");
        } else if (!blnFlghtnumber && !strErrors.contains("A Flight Number must be entered. This is a required field."))

            report.reportSelenium(MessageConstants.INFO, "Flight Error is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-Flight Error is NOT displayed");
        }
        if (blnEventCity && strErrors.contains("An Event City is required. Please select an Event City.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-Event City Error is displayed");
        } else if (!blnEventCity && !strErrors.contains("An Event City is required. Please select an Event City."))

            report.reportSelenium(MessageConstants.INFO, "Event City Error is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-Event City Error is NOT displayed");
        }
        if (blnOriginalcity && strErrors.contains("An Original City is required. Please select an Original City.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-Original City Error is displayed");
        } else if (!blnOriginalcity && !strErrors.contains("An Original City is required. Please select an Original City."))

            report.reportSelenium(MessageConstants.INFO, "Original City Error is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-Original City Error is NOT displayed");
        }

        if (blnDesCity && strErrors.contains("A Destination City is required. Please select a Destination City.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-Destination City Error is displayed");
        } else if (!blnDesCity && !strErrors.contains("A Destination City is required. Please select a Destination City."))

            report.reportSelenium(MessageConstants.INFO, "Destination City Error is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-Destination City Error is NOT displayed");
        }


    }

    public void verifyErrorMessage(boolean blnStreetAddress, boolean blnCity, boolean blnphone) {
        WebElement element = waitForElement(xpathError);
        String strErrors = element.getText();
        if (blnStreetAddress && strErrors.contains("The street address is a required field. Please provide the street address.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-StreetAddressError is displayed");
        } else if (!blnStreetAddress && !strErrors.contains("The street address is a required field. Please provide the street address."))

            report.reportSelenium(MessageConstants.PASSED, "Required-StreetAddressError is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-StreetAddressError is NOT displayed");
        }
        if (blnCity && strErrors.contains("The address city is a required field. Please provide the city name.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-CityAddressError is displayed");
        } else if (!blnCity && !strErrors.contains("The address city is a required field. Please provide the city name."))

            report.reportSelenium(MessageConstants.PASSED, "Required-CityAddressError is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-CityAddressError is NOT displayed");
        }
        if (blnphone && strErrors.contains("A telephone number 'OR' complete address (Street Address, City, and State) is required.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-PhoneError is displayed");
        } else if (!blnphone && !strErrors.contains("A telephone number 'OR' complete address (Street Address, City, and State) is required."))

            report.reportSelenium(MessageConstants.PASSED, "Required-PhoneError is  displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-PhoneError is Not displayed");
        }


    }

    public void verifyContactError(boolean blnFirstName, boolean blnLastName) {
        WebElement element = waitForElement(xpathError);
        String strErrors = element.getText();
        if (blnFirstName && strErrors.contains(MSG_ERROR_FIRST_NAME)) {

            report.reportSelenium(MessageConstants.PASSED, "Required -FirstName Error is displayed");
        } else if (!blnFirstName && !strErrors.contains(MSG_ERROR_FIRST_NAME))

            report.reportSelenium(MessageConstants.PASSED, MSG_ERROR_FIRST_NAME1);
        else {
            report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_FIRST_NAME1);
        }
        if (blnLastName && strErrors.contains(MSG_ERROR_LAST_NAME)) {

            report.reportSelenium(MessageConstants.PASSED, "Required-LastNameError is displayed");
        } else if (!blnLastName && !strErrors.contains(MSG_ERROR_LAST_NAME))

            report.reportSelenium(MessageConstants.PASSED, MSG_ERROR_LAST_NAME1);
        else {
            report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_LAST_NAME1);
        }

    }

    public void verifyContactErrorMessage(boolean blnFirstName, boolean blnLastName, boolean blnGender) {
        WebElement element = waitForElement(xpathError);
        String strErrors = element.getText();
        if (blnFirstName && strErrors.contains(MSG_ERROR_FIRST_NAME)) {

            report.reportSelenium(MessageConstants.PASSED, "Required -FirstName Error is displayed");
        } else if (!blnFirstName && !strErrors.contains(MSG_ERROR_FIRST_NAME))

            report.reportSelenium(MessageConstants.PASSED, MSG_ERROR_FIRST_NAME1);
        else {
            report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_FIRST_NAME1);
        }
        if (blnLastName && strErrors.contains(MSG_ERROR_LAST_NAME)) {

            report.reportSelenium(MessageConstants.PASSED, "Required-LastNameError is displayed");
        } else if (!blnLastName && !strErrors.contains(MSG_ERROR_LAST_NAME))

            report.reportSelenium(MessageConstants.PASSED, MSG_ERROR_LAST_NAME1);
        else {
            report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_LAST_NAME1);
        }
        if (blnGender && strErrors.contains("A Gender is required. Please chose either 'M' or 'F'.")) {

            report.reportSelenium(MessageConstants.PASSED, "Required-GenderError is displayed");
        } else if (!blnGender && !strErrors.contains("A Gender is required. Please chose either 'M' or 'F'."))

            report.reportSelenium(MessageConstants.PASSED, "Required-GenderError is NOT displayed");
        else {
            report.reportSelenium(MessageConstants.FAILED, "Required-GenderError is  displayed");
        }


    }

    public void testError() {
        if (commonUtils.verifyExistenceOfStaticField(arrAdditionalContactInfoFields)) {
            report.reportSelenium(MessageConstants.PASSED, "All Labels are present in AdditionalContactInfo Page.");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in AdditionalContactInfo Page.");
        }

        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add Additional Contact info without Address Or Phone details ");
        verifyErrorMessage(true, true, true);

        String strtest = "test";
        enterText(idStreetAddress, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add Additional Contact info without complete Address-Street Address  ");
        verifyErrorMessage(false, true, true);

        enterText(idStreetAddressLine2, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add Additional Contact info without complete Address-City ");
        verifyErrorMessage(false, true, true);

        enterText(idZipCode, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add Additional Contact info without complete Address  ");
        verifyErrorMessage(false, true, true);

        clearField(idStreetAddress);
        clearField(idStreetAddressLine2);

        enterText(idCity, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add Additional Contact info without complete Address  ");
        verifyErrorMessage(true, false, true);
        enterText(idStreetAddress, strtest);
        enterText(idStreetAddressLine2, strtest);
        buttonClick(xpathAddBtn);

        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "Additional Contact Added successfully by providing only Address details");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "Additional Contact NOT Added successfully by providing only Address details");
        }
        buttonClick(xpathEditBtn);
        clearField(idStreetAddress);
        clearField(idStreetAddressLine2);
        clearField(idCity);
        clearField(idZipCode);
        enterText(idPhone, strtest);
        buttonClick(xpathAddBtn);

        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "Additional Contact Added successfully by providing only Phone details");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "Additional Contact NOT Added successfully by providing only Phone details");
        }

        buttonClick(xpathDeleteBtn);

        getDriver().switchTo().alert().accept();

        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.FAILED, "Additional Contact Info NOT  Deleted successfully ");
        } else {
            report.reportSelenium(MessageConstants.PASSED, "Additional Contact Info Deleted successfully ");
        }


    }

    public void contactInfoCustomerInjuredBYunruly() {
        buttonClick(xpathClickHere);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Contact Information for Customer(s) who may have been injured or affected by unruly individual(s).");
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);


        String strtest1 = "test";
        enterText(idContactLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idContactFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "ADDED successfully-Contact Information for Customer(s) who may have been injured by unruly Customer");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "Not Added successfully-Contact Information for Customer(s) who may have been injured by unruly Customer");
        }

        buttonClick(xpathDeleteBtn);

        getDriver().switchTo().alert().accept();

        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.FAILED, MSG_CONTACT_INFO_NOT_DELETED);
        } else {
            report.reportSelenium(MessageConstants.PASSED, MSG_CONTACT_INFO_DELETED);
        }


    }

    public void familyContactInfoME() {
        buttonClick(xpathClickHereFamily);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Family Contact Information");
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);


        String strtest1 = "test";
        enterText(idContactLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idContactFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "ADDED successfully-Family Contact Information");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "Not Added successfully-Family Contact Information");
        }
        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);

        testError();

        buttonClick(xpathCancel);

        buttonClick(xpathDeleteBtn);

        getDriver().switchTo().alert().accept();


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.FAILED, "Family Contact Information NOT  Deleted successfully ");
        } else {
            report.reportSelenium(MessageConstants.PASSED, " Family Contact Information Info Deleted successfully ");
        }


    }

    public void contactInfoCustomerHelpedrestrainTheunruly() {
        buttonClick(xpathClickHereRestrain);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Contact Information for Customer(s) who helped restrain the unruly Customer..");
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);


        String strtest1 = "test";
        enterText(idContactLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idContactFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "ADDED successfully-Contact Information for Customer(s) who helped restrain the unruly Customer");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "Not Added successfully-Contact Information for Customer(s) who helped restrain the unruly Customer");
        }

        buttonClick(xpathDeleteBtn);

        getDriver().switchTo().alert().accept();

        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.FAILED, MSG_CONTACT_INFO_NOT_DELETED);
        } else {
            report.reportSelenium(MessageConstants.PASSED, MSG_CONTACT_INFO_DELETED);
        }


    }

    public void contactInfoAssitedME() {
        buttonClick(xpathClickHereAssisted);
        report.reportSelenium(MessageConstants.PASSED, "Navigated to Contact information for Customer(s) who assisted in the event.");
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, true, true);


        String strtest1 = "test";
        enterText(idContactLastName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(true, false, true);

        enterText(idContactFirstName, strtest1);
        buttonClick(xpathAddBtn);
        verifyContactErrorMessage(false, false, true);

        buttonClick(xpathGender);
        buttonClick(xpathAddBtn);


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "ADDED successfully-Contact information for Customer(s) who assisted in the event");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "Not Added successfully-Contact information for Customer(s) who assisted in the event");
        }
        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);

        testError();

        buttonClick(xpathCancel);


        buttonClick(xpathDeleteBtn);

        getDriver().switchTo().alert().accept();

        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.FAILED, MSG_CONTACT_INFO_NOT_DELETED);
        } else {
            report.reportSelenium(MessageConstants.PASSED, MSG_CONTACT_INFO_DELETED);
        }


    }

    public void contactInfoUMTestError() {
        if (commonUtils.verifyExistenceOfStaticField(arrContactInformationUM)) {
            report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Contact Information for Unaccompanied Minors Page.");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Contact Information for Unaccompanied Minors Page.");
        }

        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add  Contact info without First and Last names ");

        verifyContactError(true, true);
        String strtest = "test";
        enterText(idContactFirstName, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add  Contact info without  Last name ");
        verifyContactError(false, true);

        clearField(idContactFirstName);
        enterText(idContactLastName, strtest);
        buttonClick(xpathAddBtn);
        report.reportSelenium(MessageConstants.INFO, "Trying to add  Contact info without  first name ");
        verifyContactError(true, false);

        enterText(idContactFirstName, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "On trying to add  Contact info with first name and last name-No Error displayed   ");


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "Contact Info  Added successfully ");
        } else {
            report.reportSelenium(MessageConstants.FAILED, " Contact Info NOT Added successfully ");
        }

        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);

        if (isElementPresent(idStreetAddress)) {
            report.reportSelenium(MessageConstants.PASSED, "Navigated to Additional Contact Information page ");
        } else {
            report.reportSelenium(MessageConstants.FAILED, " Failed to Navigate to Additional Contact Information page ");
        }


    }

    public void contactInfoMETestError() {
        if (commonUtils.verifyExistenceOfStaticField(arrContactInformationUM)) {
            report.reportSelenium(MessageConstants.PASSED, "All Labels are present in Contact Information for Unaccompanied Minors Page.");
        } else {
            report.reportSelenium(MessageConstants.FAILED, "All Labels are not present in Contact Information for Unaccompanied Minors Page.");
        }

        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add  Contact info without First and Last names ");

        verifyContactError(true, true);
        String strtest = "test";
        enterText(idContactFirstName, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "Trying to add  Contact info without  Last name ");
        verifyContactError(false, true);

        clearField(idContactFirstName);
        enterText(idContactLastName, strtest);
        buttonClick(xpathAddBtn);
        report.reportSelenium(MessageConstants.INFO, "Trying to add  Contact info without  first name ");
        verifyContactError(true, false);

        enterText(idContactFirstName, strtest);
        buttonClick(xpathAddBtn);

        report.reportSelenium(MessageConstants.INFO, "On trying to add  Contact info with first name and last name-No Error displayed   ");


        if (isElementPresent(xpathEditBtn)) {
            report.reportSelenium(MessageConstants.PASSED, "Contact Info  Added successfully ");
        } else {
            report.reportSelenium(MessageConstants.FAILED, " Contact Info NOT Added successfully ");
        }

        buttonClick(xpathEditBtn);
        buttonClick(xpathAdditionalInfoBtn);

        if (isElementPresent(idStreetAddress)) {
            report.reportSelenium(MessageConstants.PASSED, "Navigated to Additional Contact Information page ");
        } else {
            report.reportSelenium(MessageConstants.FAILED, " Failed to Navigate to Additional Contact Information page ");
        }

    }


    public void searchGoMOMReport() { try {
            report.reportSelenium(MessageConstants.INFO, "Logged in to Admin role Successfully");

            commonUtils.clearAndEnterText(xpathSearchByRptId, GoMomConstants.SubmittedReportNumber);
            report.reportSelenium(MessageConstants.INFO, "Entered Report ID in search field successfully");
            buttonClick(xpathSearchBtn);
            if (isElementVisible(xpathMessageWhenRptIDPresent)) {
                report.reportSelenium(MessageConstants.PASSED, "Report ID " + GoMomConstants.SubmittedReportNumber + " Is Present");
                buttonClick(xpathBackBtn);
            }else {
                report.reportSelenium(MessageConstants.FAILED, "There were no Complete Mom Reports found for this Report Id. Please try again");
                buttonClick(xpathSearchAgainBtn);
            }

            report.reportSelenium(MessageConstants.INFO, "Navigated to Home Page Successfully");
            buttonClick(xpathSearchBtn);
            if (isElementVisible(xpathMessageWhenSearchWithBlankReportIdField))
                report.reportSelenium(MessageConstants.PASSED, MSG_SEARCH_OPTIONS);
            else
                report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_NOT_DISPLAYED);
            getDriver().navigate().back();
            getDriver().navigate().refresh();

            waitForElement(xpathSearchBtn);
            commonUtils.clearAndEnterText(xpathSearchByRptId, "a");
            report.reportSelenium(MessageConstants.INFO, "Text entered successfully in Report Id field");
            buttonClick(xpathSearchBtn);
            if (isElementVisible(xpathSearchWithCharInReportIdField))
                report.reportSelenium(MessageConstants.PASSED, "'The Report Id entered was not a single numeric value. Please enter again using a single numeric value.' Message displayed successfully");
            else
                report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_NOT_DISPLAYED);
            getDriver().navigate().back();
            getDriver().navigate().refresh();

            waitForElement(xpathSearchBtn);
            buttonClick(xpathDateRangeRadioBtn);
            String parentHandle = getDriver().getWindowHandle();
            buttonClick(xpathDateFrom);
            commonUtils.switchToNewTab(parentHandle);
            commonUtils.getcurrentdateUsingDatePicker();
            commonUtils.closeTabsAndSwitchToInnerFrame(parentHandle);

            buttonClick(xpathDateTo);
            commonUtils.switchToNewTab(parentHandle);
            commonUtils.getcurrentdateUsingDatePicker();
            commonUtils.closeTabsAndSwitchToInnerFrame(parentHandle);
            report.reportSelenium(MessageConstants.INFO, "Date Range selected Successfully");

            buttonClick(xpathSearchBtn);
            if (isElementVisible(xpathMessageWhenNoReportFound))
                report.reportSelenium(MessageConstants.INFO, "'There were no Complete Mom Reports found in the date range that you entered. Please try again.' displayed successfully");
            else
                report.reportSelenium(MessageConstants.INFO, "Searched Reports Successfully");

            buttonClick(xpathNewSearchBtn);
            buttonClick(xpathDateFrom);
            commonUtils.switchToNewTab(parentHandle);
            commonUtils.getcurrentdateUsingDatePicker();
            commonUtils.closeTabsAndSwitchToInnerFrame(parentHandle);
            report.reportSelenium(MessageConstants.INFO, "DateFrom is selected Successfully");
            buttonClick(xpathSearchBtn);
            if (isElementVisible(xpathMessageWhenSearchWithBlankReportIdField))
                report.reportSelenium(MessageConstants.PASSED, MSG_SEARCH_OPTIONS);
            else
                report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_NOT_DISPLAYED);
            getDriver().navigate().back();
            getDriver().navigate().refresh();

            buttonClick(xpathDateTo);
            commonUtils.switchToNewTab(parentHandle);
            commonUtils.getcurrentdateUsingDatePicker();
            commonUtils.closeTabsAndSwitchToInnerFrame(parentHandle);
            report.reportSelenium(MessageConstants.INFO, "DateTo is selected Successfully");
            buttonClick(xpathSearchBtn);
            if (isElementVisible(xpathMessageWhenSearchWithBlankReportIdField))
                report.reportSelenium(MessageConstants.PASSED, MSG_SEARCH_OPTIONS);
            else
                report.reportSelenium(MessageConstants.FAILED, MSG_ERROR_NOT_DISPLAYED);
            getDriver().navigate().back();
            getDriver().navigate().refresh();

        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Search Report. Error : " + e.toString());
        }
    }

    public void addStation(String stncode, String stn, String stncodenew) {
        try {
            report.reportSelenium(MessageConstants.INFO, "Click on 'Add New Station'");
            buttonClick(xpathAddNewStationBtn);
            if (isElementPresent(xpathStationCode))
                report.reportSelenium(MessageConstants.PASSED, MSG_ADD_STATION_PASS);
            else
                report.reportSelenium(MessageConstants.FAILED, MSG_ADD_STATION_FAIL);
            commonUtils.clearAndEnterText(xpathStationCode, stncode);
            commonUtils.clearAndEnterText(xpathStation, stn);
            report.reportSelenium(MessageConstants.INFO, "Station Code and Station Values entered Successfully");
            buttonClick(xpathSaveBtn);
            if (isElementPresent(xpathMessageWhenStationInfoSaved)) {
                report.reportSelenium(MessageConstants.PASSED, "Station code " + stncode + " and Station Name " + stn + " added successfully");
                buttonClick(xpathOkBtn);
            } else
                report.reportSelenium(MessageConstants.FAILED, "Failed to add Station code " + stncode + " and Station Name " + stn);


            if (isElementPresent(xpathStationCode)) {
                buttonClick(xpathAddNewStationBtn);
                report.reportSelenium(MessageConstants.PASSED, MSG_ADD_STATION_PASS);
            } else
                report.reportSelenium(MessageConstants.FAILED, MSG_ADD_STATION_FAIL);
            commonUtils.clearAndEnterText(xpathStation, stn);
            buttonClick(xpathSaveBtn);
            if (isElementPresent(xpathMessageWhenStationCodeIsBlank))
                report.reportSelenium(MessageConstants.PASSED, "'A Station Code is required. Please provide the station code' Message displayed Successfully");
            else
                report.reportSelenium(MessageConstants.FAILED, "Failed to display error Message when Station code is empty");
            buttonClick(xpathCancelBtn);


            if (isElementPresent(xpathStationCode)) {
                buttonClick(xpathAddNewStationBtn);
                report.reportSelenium(MessageConstants.PASSED, MSG_ADD_STATION_PASS);
            } else
                report.reportSelenium(MessageConstants.FAILED, MSG_ADD_STATION_FAIL);
            commonUtils.clearAndEnterText(xpathStationCode, stncodenew);
            buttonClick(xpathSaveBtn);
            if (isElementPresent(xpathMessageWhenStationNameIsBlank))
                report.reportSelenium(MessageConstants.PASSED, "'A Station Description is required. Please provide the station Description' Message displayed Successfully");
            else
                report.reportSelenium(MessageConstants.FAILED, "Failed to display error Message when Station Name is empty");
            buttonClick(xpathCancelBtn);

        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Add Station. Error : " + e.toString());
        }
    }


    public void updateStation(String stncode, String stn,String stnnew){
        try{
            if (isElementPresent(xpathStationCode)) {
                buttonClick(xpathAddNewStationBtn);
                report.reportSelenium(MessageConstants.PASSED, MSG_ADD_STATION_PASS);
            } else
                report.reportSelenium(MessageConstants.FAILED, MSG_ADD_STATION_FAIL);
            commonUtils.clearAndEnterText(xpathStationCode, stncode);
            buttonClick(xpathSaveBtn);
            if (isElementPresent(xpathMessageWhenStationCodeAlreadyExists))
                report.reportSelenium(MessageConstants.PASSED, "'This station Code already exists please provide a different station code.' Message displayed Successfully");
            else
                report.reportSelenium(MessageConstants.FAILED, "Failed to display error Message when Station code already exists");
            buttonClick(xpathCancelBtn);

          commonUtils.selectOption(xpathStation, stn);
            report.reportSelenium(MessageConstants.INFO, "Station Name " + stn + " is selected Successfully");
            buttonClick(xpathEditStationBtn);
            commonUtils.clearAndEnterText(xpathStation, stnnew);
            report.reportSelenium(MessageConstants.INFO, "Station name has been changed successfully");
            buttonClick(xpathSaveBtn);
            if (isElementPresent(xpathMessageWhenStationInfoSaved)) {
                report.reportSelenium(MessageConstants.PASSED, "'The MOM Station having station code as " + stncode + " has been updated successfully with new station name as " + stnnew + " 'Message displayed successfully");
                buttonClick(xpathOkBtn);
            } else
                report.reportSelenium(MessageConstants.FAILED, "Failed to update Station Name");

            commonUtils.selectOption(xpathStation, stnnew);
            report.reportSelenium(MessageConstants.INFO, "Station Name " + stnnew + " is selected Successfully");
            buttonClick(xpathDeleteStationBtn);
            acceptAlert();
            if (isElementPresent(xpathMessageWhenStationInfoSaved)) {
                report.reportSelenium(MessageConstants.PASSED, "'The MOM Station having station code as " + stncode + " and station name as " + stnnew + " 'has been deleted successfully");
                buttonClick(xpathOkBtn);
                report.reportSelenium(MessageConstants.INFO, "Navigated back to Home Page successfully");
            } else
                report.reportSelenium(MessageConstants.FAILED, "Failed to delete Station ");

        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to Update Station. Error : " + e.toString());
        }
    }


    public void deleteIncompleteRpt() {
        try {
            List<WebElement> webelement = getDriver().findElements(xpathCheckBox);
            int a = commonUtils.getSizeOfElement(xpathCheckBox);

            for (int i = 0; i < a; i++) {
                WebElement lastelement = Iterables.get(webelement, i);
                buttonClick(lastelement);
            }
            report.reportSelenium(MessageConstants.INFO, "Check Box is clicked successfully");
            buttonClick(xpathDeleteBtn);
            waitByTime(2000);

            Alert alt = getDriver().switchTo().alert();
            if (alt.getText().contains("Is it ok to delete")) {
                alt.dismiss();
                report.reportSelenium(MessageConstants.INFO, "Clicked on 'Cancel' popup successfully");
            }
            buttonClick(xpathDeleteBtn);
            waitByTime(1000);
            alt.accept();
            report.reportSelenium(MessageConstants.INFO, "Accepted Pop up and Incomplete Report is Deleted Successfully");
        } catch (Exception e) {
            loggerGoMOM.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Failed to delete incomplete Report. Error : " + e.toString());
        }
    }

}
