package com.swacorp.crew.tests.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.constants.ApplicationConstantsTrim;
import com.swacorp.crew.pages.oqs.OqsHome;
import com.swacorp.crew.pages.oqs.OqsLogin;
import com.swacorp.crew.pages.trim.TrimHome;
import com.swacorp.crew.pages.trim.TrimLogin;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by x257093 on 03-Jan-2020.
 */

public class Add_Crew_Member_In_OQS_Verify_In_Trim extends TestManager {
    private final Logger LOGGER = Logger.getLogger(Add_Crew_Member_In_OQS_Verify_In_Trim.class);
    TrimLogin trimLoginPage ;
    TrimHome trimHomePageAM;
    OqsLogin oqsLoginPage ;
    OqsHome oqsHomePage;
    String empNr;

    public Add_Crew_Member_In_OQS_Verify_In_Trim(){
        oqsLoginPage = new OqsLogin();
        trimLoginPage = new TrimLogin();
        oqsHomePage = new OqsHome();
    }

    public void addCrewmember(String[] testData, boolean createFreshCrew, boolean applyEnterpriseMode){
        //Login to OQS
        oqsLoginPage.loginOQS(applyEnterpriseMode);
        getDriver().switchTo().frame("compArea");
        LOGGER.info("Login done...");
        //Add Crew Member
        empNr = oqsHomePage.addCrewMember(testData, createFreshCrew);

        //Verify that Crew member is successfully added.
        oqsHomePage.verifyCrewAddedSuccessfully(true);
    }
    public void wrapperMethod(String[] testData, boolean visibility, boolean verifyActivechkbox, boolean createFreshCrew) throws GeneralLeanFtException {
        //Login to OQS
        oqsLoginPage.loginOQS(false);

        //Add Crew Member
        empNr = oqsHomePage.addCrewMember(testData, createFreshCrew);

        //Verify that Crew member is successfully added.
        oqsHomePage.verifyCrewAddedSuccessfully(true);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Navigate Main Menu on Trim
        try {
            trimHomePageAM.navigateMenu(ApplicationConstantsTrim.SELECT_EMPLOYEE_MENU);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Search the employee details in Trim.
        trimHomePageAM.searchEmployeesDetails(empNr, verifyActivechkbox);

        //Verify that the details are not visible in Trim
        trimHomePageAM.validateSearchResults(visibility );

        trimHomePageAM.minimizeMainWindow();

    }

    public void selectFromTrimDueEmployeeSchdPlannerDropdown(String dropdownValue) throws GeneralLeanFtException{
        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        trimHomePageAM.selectMenuFromBottomMenu("^D",dropdownValue);
    }

    public void expandTreeNodeAndValidate(String node, String subNode, boolean visibility) throws GeneralLeanFtException {
          // trimHomePageAM.validateTreeNode(empNr, node, subNode, visibility);
    }

    public void wrapperMethodToAddDuplicateEmployeeNrOQS(String[] testData, boolean visibility, boolean verifyActivechkbox) throws GeneralLeanFtException{

        //Add Crew Member
        empNr = oqsHomePage.addCrewMember(testData, false); // False because we are adding duplicate emp

        //Verify that Crew member is successfully added.
        oqsHomePage.verifyCrewAddedSuccessfully(false);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        try {
            trimHomePageAM.navigateMenu(ApplicationConstantsTrim.SELECT_EMPLOYEE_MENU);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Search the employee details in Trim.
        trimHomePageAM.searchEmployeesDetails(oqsHomePage.getEmployeeNumber(), verifyActivechkbox);

        //Verify that the details are not visible in Trim
        trimHomePageAM.validateSearchResults(visibility );

        trimHomePageAM.minimizeMainWindow();
    }

    public void addNewPositionInOQSVerifyInTrim(String[] testData, boolean visibility) throws GeneralLeanFtException {
        //Login to OQS
        oqsLoginPage.loginOQS(false);

        //Add Crew Member
        empNr = oqsHomePage.addCrewMember(testData, true);


        //Verify that Crew member is successfully added.
        oqsHomePage.verifyCrewAddedSuccessfully(true);


        oqsHomePage.editPosition();

        //Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        try {
            trimHomePageAM.navigateMenu(ApplicationConstantsTrim.SELECT_EMPLOYEE_MENU);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Search the employee details in Trim.
        trimHomePageAM.searchEmployeesDetails(empNr, false);

        //Verify that the details are not visible in Trim
        trimHomePageAM.validateSearchResults(visibility );

        trimHomePageAM.minimizeMainWindow();

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        try {
            trimHomePageAM.navigateMenu("^E-->^F");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Search the employee details in Trim.
        trimHomePageAM.searchEmployeesDetails(empNr, false);

        //Verify that the details are not visible in Trim
        trimHomePageAM.validateSearchResults(visibility );

        trimHomePageAM.minimizeMainWindow();

    }

    public void editPositionToCreateCA(){
        oqsHomePage.editPosition();
    }

    public void addPosition(String position) {
        oqsHomePage.addPosition(position);
        oqsHomePage.selectTextThroughXpath("CAPTAIN");
    }

    public void selectTrainingEventCategory(String event) {
        oqsHomePage.loadTrainingEventCategory(event);
    }

    public void selectTrainingEvent(String eventCategory, String events, boolean enterpriseMode){
        oqsHomePage.loadTrainingEventCategory(eventCategory);
        ArrayList<String> allEvents=new ArrayList();

        allEvents.addAll(Arrays.asList(events.split(",")));

        for(String event: allEvents){

            oqsHomePage.addTrainingEvent(event, enterpriseMode);
        }
        oqsHomePage.handlePopup("OK");
    }

    public void deleteEvent(String s) {
        oqsHomePage.selectEvent(s);
    }

    /**Method description: This method is used to select the equipments from Equipments dropdown and select the primaryStatus from the primary dropdown from Employee details page in Trim.
     * method call: selectEquipmentAndPrimaryStatus("737", "First Officer")
     * method return: void*/
    public void selectEquipmentAndPrimaryStatus(String equipments, String primaryStatus) throws GeneralLeanFtException{

        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        try{
            trimHomePageAM.navigateMenu(ApplicationConstantsTrim.SELECT_EMPLOYEE_MENU);
            trimHomePageAM.selectEquipmentAndPrimaryStatus(empNr, equipments, primaryStatus);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
