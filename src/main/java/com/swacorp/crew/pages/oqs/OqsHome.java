package com.swacorp.crew.pages.oqs;
/*import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.web.*;
import com.hp.lft.sdk.web.Browser;*/
//import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.ReportUtil;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.swacorp.crew.utils.DateUtil;
import org.openqa.selenium.WebElement;

public class OqsHome extends BasePage {

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
    private final By DELETE_EVENT = By.xpath("//*[(text()='Delete Event...')]");
    String xpathTrainingEvent = "//div[@id= 'addTrainingEvent']//div[contains(@class, 'yui-dt-liner') and text() = '"+"PLACEHOLDER"+"']";
    private final By Ok_BTN= By.xpath("//*[contains(text(),'Ok')]");
    private final String xpathDivTextGeneric = "//div[(text() = 'PLACEHOLDER')]";
    private  final By DATE_CAL = By.id("dateCalText");


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

        int i = 1;
        try {
            do {
                List<WebElement> allEvents = getDriver().findElements(By.xpath(locator));
                allEvents.get(0).click();
                report.reportSelenium("Pass", "Event selected for deleting: " + TrainingEvent + ", occurance: "+i);
                i++;
                waitUntilElementClickable(DELETE_EVENT);
                buttonClick(DELETE_EVENT);
                HandlePopup("");
                waitUntilDomLoad();
            } while (getDriver().findElements(By.xpath(locator)).size() > 0);
            report.reportSelenium("Pass", "Event deleted " + TrainingEvent );
        }catch(Exception e){

        }

    }



    public void AddTrainingEvent(String TrainingEvent, boolean enterpriseMode) {
        //FLIGHT TRAINING
        ObjectRepoTRiM trimObjectRepo = null;

        buttonClick(ADD_EVENT);
        String xpatheVENTcHECKBOX = "/preceding::input[@type='checkbox'][1]";
        String locator;
        String eventDate;
        eventDate = "042020";

        locator = xpathTrainingEvent.replace("PLACEHOLDER", TrainingEvent);
        String xpathDateSelectTdForAnEvent = "/preceding::td[1]/following::td[3]";
        int counter = 1;
        try {

            Thread.sleep(5000);
            if (getDriver().findElement(By.xpath(locator)).isDisplayed()) {
                Thread.sleep(2000);
                if(!enterpriseMode) {
                    getDriver().findElement(By.xpath(locator + xpatheVENTcHECKBOX)).click();
                }else{
                    Thread.sleep(5000);
                    WebElement ele = getDriver().findElement(By.xpath(locator ));
                    scrollToElement(ele);
                    ele.click();

                    Robot rob = new Robot();
                    rob.keyPress(KeyEvent.VK_SHIFT);
                    Thread.sleep(100);
                    rob.keyPress(KeyEvent.VK_TAB);
                    Thread.sleep(100);
                    rob.keyRelease(KeyEvent.VK_TAB);
                    Thread.sleep(100);
                    rob.keyRelease(KeyEvent.VK_SHIFT);
                    rob.keyPress(KeyEvent.VK_SPACE);
                    Thread.sleep(100);
                    rob.keyRelease(KeyEvent.VK_SPACE);


                }
                if(TrainingEvent.equalsIgnoreCase("MANEUVERS OBSERVATION")){
                    //do {
                        WebElement locatorDateSelectTdForAnEvent = getDriver().findElement(By.xpath(locator + xpathDateSelectTdForAnEvent));
                        locatorDateSelectTdForAnEvent.click();
                        enterText(DATE_CAL, eventDate);
                        Thread.sleep(5000);
                        /*getDriver().switchTo().frame(0);
                        Thread.sleep(10000);
                        readyStateWait(getDriver().findElement(OK_BUTTON));
                        scrollToElement(getDriver().findElement(OK_BUTTON));
                        buttonClick(OK_BUTTON);
                        Thread.sleep(2000);
                        //Keyboard.pressKey(Keyboard.Keys.ENTER);
                        getDriver().findElement(OK_BUTTON).sendKeys(Keys.ENTER);
                        try {
                            System.out.println("Clicking through JS");
                            JavascriptExecutor js = (JavascriptExecutor) getDriver();
                            js.executeScript("arguments[0].click();", getDriver().findElement(OK_BUTTON));
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        Actions actions = new Actions(getDriver());
                        actions.moveToElement(getDriver().findElement(OK_BUTTON)).click().build().perform();*/
                        try {
                            trimObjectRepo = new ObjectRepoTRiM();
                            trimObjectRepo.oQSFlightOperationsPage().compAreaFrame().webCheckBox().click();
                            Thread.sleep(5000);

                            trimObjectRepo.oQSFlightOperationsPage().compAreaFrame().webEditEditField().setValue(eventDate);
                            trimObjectRepo.oQSFlightOperationsPage().compAreaFrame().oKButton().click();

                           /* Keyboard.pressKey(Keyboard.Keys.TAB);
                            Thread.sleep(2000);
                            Keyboard.pressKey(Keyboard.Keys.ENTER);*/

                            //trimObjectRepo.oQSFlightOperationsPage().compAreaFrame().oKButton().click();
                        } catch (GeneralLeanFtException e) {
                            e.printStackTrace();
                        }


                    //Browser browser = BrowserFactory.getAllOpenBrowsers(BrowserDescription.)
/*

                    EditField webEditEditField = browser.describe(Frame.class, new FrameDescription.Builder()
                            .name("compArea").build())
                            .describe(EditField.class, new EditFieldDescription.Builder()
                                    .name("WebEdit")
                                    .tagName("INPUT")
                                    .type("text").build());



                    Button oKButton = browser.describe(Frame.class, new FrameDescription.Builder()
                            .name("compArea").build())
                            .describe(Button.class, new ButtonDescription.Builder()
                                    .buttonType("submit")
                                    .name("OK")
                                    .tagName("BUTTON").build());
*/



/*
                        WebElement element = getDriver().findElement(DATE_CAL);
                        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
                        jse.executeScript("arguments[0].value='05/2020';", element);

                        Keyboard.pressKey(Keyboard.Keys.ENTER);*/
                        System.out.println("Clicking through Action");

                        /*Actions actions = new Actions(getDriver());
                        actions.moveToElement(getDriver().findElement(OK_BUTTON)).click().build().perform();

                        try {
                            System.out.println("Clicking through JS");
                            JavascriptExecutor js = (JavascriptExecutor) getDriver();
                            js.executeScript("arguments[0].click();", OK_BUTTON);
                        }catch(Exception e){
                            e.printStackTrace();
                        }*/
                        //buttonClick(OK_BUTTON);
                        /*WebElement locatorEventDate = getDriver().findElement(By.xpath(locator + xpathDateSelectTdForAnEvent + "/div"));
                        JavascriptExecutor js = (JavascriptExecutor) getDriver();
                        js.executeScript("document.getElementByXPath("+locator + xpathDateSelectTdForAnEvent + "/div"+").value='05/2020'");
*/
                      /*  if (locatorEventDate.getText().contains(eventDate)) {
                            break;
                        }
                        counter++;*/
                    //}while(counter <5);

                }
                report.reportSelenium("Pass", "Event selected " + TrainingEvent);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        buttonClickIfExist(Ok_BTN);
        HandlePopup("ok");
    }

    public void selectTextThroughXpath(String textLocation) throws Exception{
           String locator;

        try {
            By loc = By.xpath("//*[(text()='"+textLocation+"')]");
            buttonClickIfExist(loc);
        }catch(Exception e){
            report.reportSelenium("Fail", "Unable to click on  " + "//*[(text()='"+textLocation+"')]");
        }
    }
    public void setCompatibilityMode() throws InterruptedException, AWTException {

        Thread.sleep(2000);

        Robot rob = new Robot();

        rob.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(100);
        rob.keyPress(KeyEvent.VK_T);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_T);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(100);
        rob.keyPress(KeyEvent.VK_R);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_R);
        /*for (int i = 1; i <= 9; i++) {
            rob.keyPress(KeyEvent.VK_DOWN);
            rob.keyRelease(KeyEvent.VK_DOWN);
        }*/

        rob.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(100);

    }
    public void addPosition(String position) throws Exception{
           try {
               report.reportSelenium("info", "Starting Add position... ");
               waitForElement(POSITION_ADD_BTN);
               buttonClick(POSITION_ADD_BTN);
               enterText(POSITION_START_TXT, startDate);

               enterText(MESSAGE_TXT, testData[14]);
               selectOption(POSITION_DROPDOWN, position);
               buttonClickIfExist(OK_BUTTON);

               scrollToElement(getDriver().findElement(YES_BUTTON));
               buttonClickIfExist(YES_BUTTON);
               List<WebElement> allOk = getDriver().findElements(OK_BUTTON);

               getDriver().findElements(By.xpath("//*[contains(text(),'OK')]")).get(2).click();

               report.reportSelenium("Pass", "Position edited successfully to: " + position);
          }catch(Exception e){
               report.reportSelenium("Fail", "Error occured while adding position to: " + position);
           }
       }

    public void EditPosition() throws  Exception {

        report.reportSelenium("info", "Starting Edit position... ");
        try {
        buttonClick(EDIT_POSITION_BTN);
        enterText(POSITION_START_TXT, startDate);
        enterText(QUAL_DATE_TXT, startDate);
        waitUntilElementClickable(MESSAGE_TXT);
        enterText(MESSAGE_TXT,testData[13]);
        buttonClickIfExist(OK_BUTTON);
        //((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver().findElement(OK_BUTTON));

        try{
            report.reportSelenium("Pass", "Edit position is successfully to CA. ");
            //HandlePopup("ok");
        }catch(Exception e){
            System.out.println("error while clicking.");
        }
        report.reportSelenium("Pass", "Edit position is successfully to CA. ");

    }catch(Exception e){
        report.reportSelenium("Fail", "Error occured during Edit position.");
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
            //waitByTime(1000);
            if (!empPosition.equalsIgnoreCase("FAA - All All")) {
                selectOption(TYPE_TEXT, type);
                enterText(CERTIFICATE_TEXT, certificate);
                enterText(ISSUED_TEXT, dateIssued);
                selectOption(RATING_TEXT, rating);
                report.reportSelenium("INFO", "Entered CREW details and proceeding to click on Save to CrewMember List button");

            }
            buttonClick(SAVE_TO_CREWMEMBER_LIST_BUTTON);

            if (getDriver().findElements(CREW_LIST_TABLE_FIRST_ROW).size() < 1) {
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
            }else{
                break;
            }
        }while(isElementVisible(OK_BUTTON));
    }


    public void DeSelectWnterpriseMode() throws AWTException, InterruptedException {
        setCompatibilityMode();
        enterText(SEARCH_TEXT, empNum);
        waitUntilElementClickable(SEARCH_BUTTON);
        buttonClick(SEARCH_BUTTON);
    }

   /* public void setCompatibilityMode() throws InterruptedException, AWTException {

        Thread.sleep(500);

        Robot rob = new Robot();

        rob.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(100);
        rob.keyPress(KeyEvent.VK_T);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_T);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(100);
        rob.keyPress(KeyEvent.VK_R);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_R);

        report.reportSelenium("INFO", "Starting enterprise mode.");
        rob.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(100);
        rob.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(100);

    }*/
}
