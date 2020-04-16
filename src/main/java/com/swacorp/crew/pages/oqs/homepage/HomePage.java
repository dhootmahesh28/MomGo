package com.swacorp.crew.pages.oqs.homepage;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.ReportUtil;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.swacorp.crew.utils.DateUtil;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    ReportUtil report = new ReportUtil();

    public final By MAIN_MENU_SEARCH = By.xpath("//a[text()='Recordkeeping']");
    private final By MENU_SEARCH = By.xpath("//a[@class='yuibaritemlabel yuimenuitemlabel-hassubmenu yuimenuitemlabel' and text()='Add Crewmember']");
    private final By SUBMENU_SEARCH = By.xpath("//a[@class='yuimenuitemlabel' and text()='Add Crewmember']");
    private final By SEARCH_TEXT = By.id("crewSearchFieldId");
    private final By NO_RECORDS_FOUND_TEXT = By.xpath("//h3[contains(text(),'No matching records found')]");
    private final By SEARCH_BUTTON = By.id("crewmemberSearchButtonId");
    private final By SEARCH_CREWMEMBER_MENU = By.xpath("//a[@class='yuimenuitemlabel' and text()='Search Crewmember']");
    private final By CLOSE_BUTTON = By.id("crewmemberCloseButtonId");

    private final By POSITION_TEXT = By.id("positionDefinition");
    private final By CLASS_YEAR_TEXT = By.id("classYear");
    private final By CLASS_NUM_TEXT = By.id("classNumber");
    private final By CREW_INDEX_TEXT = By.id("numCrewmember");
    private final By START_DATE_TEXT = By.id("startDate");
    private final By BASE_TEXT = By.id("location");
    private final By EMP_NUM_TEXT = By.id("employeeNumber");
    private final By LAST_TEXT = By.id("lastName");
    private final By FIRST_TEXT = By.id("firstName");
    private final By DOB_TEXT = By.id("dateOfBirth");
    private final By GENDER_TEXT = By.id("gender");
    private final By US_CITIZEN_TEXT = By.id("usCitizen");
    private final By TYPE_TEXT = By.id("addCrewMemberCertificateType");
    private final By CERTIFICATE_TEXT = By.id("addCrewMemberCertificateNumber");
    private final By ISSUED_TEXT = By.id("addCrewMemberCertificateIssuedDate");
    private final By RATING_TEXT = By.id("addCrewMemberRatingType");
    private final By SAVE_TO_CREWMEMBER_LIST_BUTTON = By.id("addToCrewListButton");
    private final By IMPORT_BUTTON = By.id("importButton");
    private final By SAVE_PARTIAL_LIST_BUTTON = By.id("savePartialListButton");
    private final By CREW_LIST_TABLE_FIRST_ROW = By.xpath("//*[@tabindex = '0']");
    private final By IMPORT_SUCCESS_MSG = By.xpath("//*[contains(text(),'Class list sucessfully imported.')]");
    private final By OK_BUTTON = By.xpath("//*[contains(text(),'OK')]");
    private final By EMP_NUM_ID_TEXT = By.id("empNoId");
    private final By OQS_SEARCH_CLOSE_BTN = By.id("crewmemberCloseButtonId");
    private final By CREATE_CREW_CLOSE_BTN = By.id("closeButton");
    private final By EDIT_POSITION_BTN = By.id("positionEditButtonId");
   // private final By EDIT_POSITION_BTN = By.id("positionEditButtonId"); //Edit position button
    private final By EDIT_POSITION_DIALOG = By.id("positionEditButtonId"); //Dialog that opens after clicking Edit position
    private final By POSITION_END_BOX = By.id("positionEndDate"); //positionEndDate
    private final By POSITION_ADD_BTN = By.id("positionAddButtonId");
    private final By MESSAGE_TXT = By.id("commentId");
    private final By POSITION_DROPDOWN = By.xpath("(//*[contains(@id,'positionDefId')])[2]");
    private final By POSITION_START_TXT = By.id("positionStartDate");
    private final By POSITION_END_TXT = By.id("positionEndDate");
    private final By QUAL_DATE_TXT = By.id("qualDate");
    private final By YES_BUTTON = By.xpath("//*[contains(text(),'Yes')]");

    private final By AddEvent = By.xpath("//*[@id='trEvtAddButtonId']");
    private final By TRAINING_EVENT_LOADER = By.xpath("//select[@id='categoryId']");
    private final By ADD_EVENT = By.xpath("//*[contains(text(),'Add Event...')]");
    private final By DELETE_EVENT = By.xpath("//*[contains(text(),'Delete Event...)]");
    String xpathTrainingEvent = "//div[@id= 'addTrainingEvent']//div[contains(@class, 'yui-dt-liner') and text() = '"+"PLACEHOLDER"+"']";
    private final By Ok_BTN= By.xpath("//*[contains(text(),'Ok')]");
    private final String xpathDivTextGeneric = "//div[(text() = 'PLACEHOLDER')]";


    private boolean crewAddedSuccessfully;
    private boolean editPosition;
    String empNum;
    String[] testData;
    String startDate;
    String positionStartDate;
    String positionEndtDate;
    DateUtil dateUtil = new DateUtil();
    String duplicateEmployeeError = "";

    public void AddEvent(String event){
        buttonClickIfExist(AddEvent);
    }


    public void selectEvent(String TrainingEvent){

        String locator;
        //div[(text() = 'FLIGHT TRAINING')]
        locator = xpathDivTextGeneric.replace("PLACEHOLDER", TrainingEvent);
        try{
            Thread.sleep(7000);
        }catch(Exception e){

        }
        List<WebElement> allEvents = getDriver().findElements(By.xpath(locator));


        //waitUntilElementClickable(By.xpath(locator));
       /* allEvents.get(0).click();
        report.reportSelenium("Pass", "Event selected for deleting: " + TrainingEvent);
        buttonClick(DELETE_EVENT);*/

        for (int i = 0; i <= allEvents.size() - 1; i++){

            allEvents.get(i).click();
            report.reportSelenium("Pass", "Event selected for deleting: " + TrainingEvent);
            buttonClick(DELETE_EVENT);
        }

    }

    public void AddTrainingEvent(String TrainingEvent){
        //FLIGHT TRAINING

        String xpatheVENTcHECKBOX = "/preceding::input[@type='checkbox'][1]";
        String locator;

        locator = xpathTrainingEvent.replace("PLACEHOLDER", TrainingEvent);

        try {
            if (getDriver().findElement(By.xpath(locator)).isDisplayed()) {
                getDriver().findElement(By.xpath(locator + xpatheVENTcHECKBOX)).click();
                report.reportSelenium("Pass", "Event selected " + TrainingEvent);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        buttonClickIfExist(Ok_BTN);

    }
    public void addPosition() throws Exception{
        waitByTime(5000);
        waitUntilElementClickable(POSITION_ADD_BTN);
        buttonClick(POSITION_ADD_BTN);
        waitUntilElementClickable(POSITION_START_TXT);

        enterText(POSITION_START_TXT,startDate );
        enterText(POSITION_END_TXT, startDate);
        enterText(MESSAGE_TXT, testData[14]);
        selectOption(POSITION_DROPDOWN, testData[15]);
        buttonClick(OK_BUTTON);
        buttonClick(YES_BUTTON);
        buttonClick(YES_BUTTON);

        Thread.sleep(4000);
    }

    public void EditPosition() throws  Exception {

        try {
        waitUntilElementClickable(EDIT_POSITION_BTN);
        waitByTime(5000);
        waitForElement(EDIT_POSITION_BTN);
        buttonClick(EDIT_POSITION_BTN);
        waitUntilElementClickable(EDIT_POSITION_DIALOG);

        enterText(POSITION_START_TXT, startDate);
        enterText(QUAL_DATE_TXT, startDate);
        enterText(POSITION_END_BOX, startDate);
        waitUntilElementClickable(MESSAGE_TXT);
        enterText(MESSAGE_TXT,testData[13]);
        buttonClick(OK_BUTTON);
        buttonClick(OK_BUTTON);
        editPosition = true;
            //ValidateEditPositionSuccessful(editPosition);
    }catch(Exception e){
            editPosition = false;
            //e.printStackTrace();
        }
    }

    private void ValidateEditPositionSuccessful(boolean editPosition){
        if (editPosition){
            report.reportSelenium("Pass", "Position is edited successfully.");
        }else{
            report.reportSelenium("Fail", "Error occured while position is edited.");
        }
    }

    public void selectMenuHome() {
        buttonClick(MAIN_MENU_SEARCH);
        waitUntilElementClickable(MENU_SEARCH);
        buttonClick(MENU_SEARCH);
        waitUntilElementClickable(SUBMENU_SEARCH);
        buttonClick(SUBMENU_SEARCH);
    }
    public String searchCrew() {
        waitUntilElementClickable(SEARCH_TEXT);
        boolean retryStatus = false;
        int retryCounter = 0;
        int maxRetryCount = 10;
        String empNum = null;
        do {
            try {
                //empNum = randomNumber(5);
                int empID = ThreadLocalRandom.current().nextInt(1000, 99999);
                empNum = Integer.toString(empID);
                enterText(SEARCH_TEXT, empNum);
                buttonClick(SEARCH_BUTTON);
                if (isElementPresent(NO_RECORDS_FOUND_TEXT) == true) {
                    break;
                }else
                    waitByTime(10000);
                    buttonClick(MAIN_MENU_SEARCH);
                    waitUntilElementClickable(SEARCH_CREWMEMBER_MENU);
                    buttonClick(SEARCH_CREWMEMBER_MENU);
                    waitUntilElementClickable(SEARCH_TEXT);
                    retryStatus = true;
                    if (++retryCounter > maxRetryCount) {
                        break;
                    }
            } catch (Exception e) {
                e.printStackTrace();
                retryStatus = true;
                if (++retryCounter > maxRetryCount) {
                    break;
                }
            }
        } while (retryStatus);
        buttonClick(CLOSE_BUTTON);
        waitByTime(2000);
        buttonClick(MAIN_MENU_SEARCH);
        waitUntilElementClickable(MENU_SEARCH);
        scrollToElement(waitForElement(MENU_SEARCH));
        buttonClick(MENU_SEARCH);
        waitUntilElementClickable(SUBMENU_SEARCH);
        scrollToElement(waitForElement(SUBMENU_SEARCH));
        buttonClick(SUBMENU_SEARCH);
        return empNum;
    }

    public void  VerifySearchCrew(String empNum, boolean visibility) {
        boolean found;

        try {
            buttonClick(MAIN_MENU_SEARCH);
            waitUntilElementClickable(SEARCH_CREWMEMBER_MENU);
            buttonClick(SEARCH_CREWMEMBER_MENU);
            waitUntilElementClickable(SEARCH_TEXT);
            enterText(SEARCH_TEXT, empNum);
            buttonClick(SEARCH_BUTTON);
            if (isElementPresent(NO_RECORDS_FOUND_TEXT) == true) {
                found = true;
            } else
                found = false;

            if ((visibility & found ) || (!visibility & !found )){
                report.reportSelenium("Pass", "Crew Member: " + empNum + " Found.");
            }else
                report.reportSelenium("Fail", "Crew Member: " + empNum + " not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public int addCrewMember(String empPosition, String startDate, String classYear, String classNumber, String crewNumber, String baseLocation,
                              String empNum, String lastName, String firstName, String dateOfBirth, String gender, String usCitizenFlag, String type,
                              String certificate, String dateIssued, String rating) {
        try {
            getDriver().switchTo().frame("compArea");
            selectOption(POSITION_TEXT, empPosition);
            enterText(CLASS_YEAR_TEXT, classYear);
            enterText(CLASS_NUM_TEXT, classNumber);
            enterText(CREW_INDEX_TEXT, crewNumber);
            enterText(START_DATE_TEXT, startDate);
            selectOption(BASE_TEXT, baseLocation);
            enterText(EMP_NUM_TEXT, empNum);
            enterText(LAST_TEXT, lastName);
            enterText(FIRST_TEXT, firstName);
            enterText(DOB_TEXT, dateOfBirth);
            selectOption(GENDER_TEXT, gender);
            selectOption(US_CITIZEN_TEXT, usCitizenFlag);
            waitByTime(1000);
            if (!empPosition.equalsIgnoreCase("FAA - All All")) {
                selectOption(TYPE_TEXT, type);
                enterText(CERTIFICATE_TEXT, certificate);
                enterText(ISSUED_TEXT, dateIssued);
                selectOption(RATING_TEXT, rating);
                report.reportSelenium("INFO", "Entered CREW details and proceeding to click on Save to CrewMember List button");

            }
            buttonClick(SAVE_TO_CREWMEMBER_LIST_BUTTON);
            waitByTime(3000);
            //buttonClickIfExist(OK_BUTTON);
            //waitUntilElementClickable(By.xpath("//*[@class='yui-dt0-col-lastname yui-dt-col-lastname' and text()='Larry']"));
            if (getDriver().findElements(CREW_LIST_TABLE_FIRST_ROW).size() < 1) {
                //throw new Exception(); //ElementNotVisibleException("Table is not reflected");
                return 1;
            }
            buttonClick(CREW_LIST_TABLE_FIRST_ROW);
            buttonClick(SAVE_PARTIAL_LIST_BUTTON);
            buttonClick(OK_BUTTON);
            report.reportSelenium("INFO", "Crew Member: " + empNum + " added to the Crewmember Import List and proceeding to click on Import button");
            buttonClick(IMPORT_BUTTON);


            waitByTime(5000);
            waitUntilElementClickable(IMPORT_SUCCESS_MSG);
            //buttonClickIfExist(OK_BUTTON);
            report.reportSelenium("INFO", "Class list successfully imported and proceeding to click on OK button");
            buttonClick(OK_BUTTON);

            if (verifyValueFromEditbox(EMP_NUM_ID_TEXT, empNum))
                report.reportSelenium("INFO", "Crew Member: " + empNum + " created successfully");
            else
                report.reportSelenium("FAIL", "Crew Member creation with: " + empNum + " Failed");
            return 0;
        }catch(Exception e){
            return -1;
        }
    }


    public String addCrewMember(String[] data, boolean createFreshCrew){
        int retVal = -1;
        int crewAdded;
        testData = data;

        DateUtil dateUtil = new DateUtil();
/*        try {

            empNum = getDynamicData("empNum");

            if (empNum == null){
                empNum = searchCrew();
                setDynamicData("EmpNumber", empNum);
            }
        }catch(Exception e) {
            empNum = searchCrew();
            setDynamicData("EmpNumber", empNum);
        }*/

        empNum = getDynamicData("EmpNumber");
        System.out.print("empNum: "+empNum);
        if (empNum == null | createFreshCrew){
            empNum = searchCrew();
            setDynamicData("EmpNumber", empNum);
        }

        try {
            //startDate = dateUtil.getCurrentDate();
            startDate = dateUtil.getCurrentDate("MM/dd/yyyy");
            String classYear = dateUtil.getCurrentYear();
            retVal = addCrewMember(data[0], startDate, classYear, data[1], data[2], data[3], empNum, data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12]);
            System.out.print("retVal: "+retVal);
            retVal = 0;
        }catch(Exception e){
            e.printStackTrace();
        }

        //setDynamicData("EmployeeNumber",empNum);
        if (retVal == 0){

            crewAddedSuccessfully = true;
            return empNum;
        }else {
            crewAddedSuccessfully = false;
            return "ERROR";
        }
    }

    public String getEmployeeNumber(){
        System.out.print("Returning empNr. "+empNum);
        return empNum;
    }

    public boolean addDuplicateCrewMember(String[] data, boolean status){
        int retVal = -1;
        int crewAdded;
        testData = data;
        empNum = getDynamicData("EmpNumber");
        System.out.print("empNum  "+empNum);
        try {
            //startDate = dateUtil.getCurrentDate();
            String classYear = dateUtil.getCurrentYear();
            retVal = addCrewMember(data[0], startDate, classYear, data[1], data[2], data[3], empNum, data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12]);

        }catch(Exception e){
            e.printStackTrace();
        }

        if (retVal == 0){
            report.reportSelenium("FAIL", "duplicate employee number successsfully added. ");
            return false;
        }else {
            report.reportSelenium("Pass", "duplicate employee number not added to OQS. ");
            return  true;
        }

    }

    public void VerifyCrewAddedSuccessfully(boolean status){
        if (status && crewAddedSuccessfully){
            report.reportSelenium("Pass", "Crew Member: " + empNum + " created successfully");
        }else{
            report.reportSelenium("Fail", "Error occured while creating CREW member.");
        }
    }

    public int addCrewMember(String empPosition, String startDate, String classYear, String classNumber, String crewNumber, String baseLocation,
                             String empNum, String lastName, String firstName, String dateOfBirth, String gender, String usCitizenFlag, String type,
                             String certificate, String dateIssued, String rating, boolean stat) {
        // try {
        getDriver().switchTo().frame("compArea");
        selectOption(POSITION_TEXT, empPosition);
        enterText(CLASS_YEAR_TEXT, classYear);
        enterText(CLASS_NUM_TEXT, classNumber);
        enterText(CREW_INDEX_TEXT, crewNumber);
        enterText(START_DATE_TEXT, startDate);
        selectOption(BASE_TEXT, baseLocation);
        enterText(EMP_NUM_TEXT, empNum);
        enterText(LAST_TEXT, lastName);
        enterText(FIRST_TEXT, firstName);
        enterText(DOB_TEXT, dateOfBirth);
        selectOption(GENDER_TEXT, gender);
        selectOption(US_CITIZEN_TEXT, usCitizenFlag);
        waitByTime(1000);
        if (!empPosition.equalsIgnoreCase("FAA - All All")) {
            selectOption(TYPE_TEXT, type);
            enterText(CERTIFICATE_TEXT, certificate);
            enterText(ISSUED_TEXT, dateIssued);
            selectOption(RATING_TEXT, rating);
            report.reportSelenium("INFO", "Entered CREW details and proceeding to click on Save to CrewMember List button");

        }
        buttonClick(SAVE_TO_CREWMEMBER_LIST_BUTTON);
        waitByTime(3000);
        //waitUntilElementClickable(By.xpath("//*[@class='yui-dt0-col-lastname yui-dt-col-lastname' and text()='Larry']"));
        if (getDriver().findElements(CREW_LIST_TABLE_FIRST_ROW).size() < 1){
            //throw new Exception(); //ElementNotVisibleException("Table is not reflected");
            return 1;
        }
        buttonClick(CREW_LIST_TABLE_FIRST_ROW);
        buttonClick(SAVE_PARTIAL_LIST_BUTTON);
        buttonClick(OK_BUTTON);
        report.reportSelenium("INFO", "Crew Member: " + empNum + " added to the Crewmember Import List and proceeding to click on Import button");
        buttonClick(IMPORT_BUTTON);
        waitUntilElementClickable(IMPORT_SUCCESS_MSG);
        report.reportSelenium("INFO", "Class list successfully imported and proceeding to click on OK button");
        buttonClick(OK_BUTTON);



        if (verifyValueFromEditbox(EMP_NUM_ID_TEXT, empNum))
            report.reportSelenium("INFO", "Crew Member: " + empNum + " created successfully");
        else
            report.reportSelenium("FAIL", "Crew Member creation with: " + empNum + " Failed");
        // getDriver().switchTo().defaultContent();
        return 0;
    }

    public void LoadTrainingEventCategory(String event) {
        try {
            selectOption(TRAINING_EVENT_LOADER, event);
            report.reportSelenium("pass", "Event successfully loaded:" + event);
            //getDriver().findElement(TRAINING_EVENT_LOADER).getText();
            buttonClickIfExist(ADD_EVENT);
            report.reportSelenium("pass", "Clicked on Add Event button." + event);
            printConsole("getDriver().findElement(TRAINING_EVENT_LOADER).getText(): "+getDriver().findElement(TRAINING_EVENT_LOADER).getText());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void HandlePopup(String ok) {

        do {
            if(isElementVisible(OK_BUTTON)){
                report.reportSelenium("pass", "Clicking on OK button");
                buttonClick(OK_BUTTON);
            }
        }while(isElementVisible(OK_BUTTON));
    }


}
