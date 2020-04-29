package com.swacorp.crew.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.oqs.HomePage;
import com.swacorp.crew.pages.oqs.OQSLogin;
import com.swacorp.crew.pages.trim.TrimHomePageAM;
import com.swacorp.crew.pages.trim.LoginPageAM;
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
    LoginPageAM trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLogin oqsLoginPage ;
    HomePage oqsHomePage;
    String empNr;

    public Add_Crew_Member_In_OQS_Verify_In_Trim(){
        oqsLoginPage = new OQSLogin();
        trimLoginPage = new LoginPageAM();
        oqsHomePage = new HomePage();
    }

    public void AddCrewmember(String[] testData, boolean createFreshCrew, boolean applyEnterpriseMode) throws Exception{
        //Login to OQS
        oqsLoginPage.loginOQS(applyEnterpriseMode);

        //Add Crew Member
        empNr = oqsHomePage.addCrewMember(testData, createFreshCrew);

        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(true);
    }
    public void WrapperMethod(String[] testData, boolean visibility, boolean verifyActivechkbox, boolean createFreshCrew) throws GeneralLeanFtException {
        //Login to OQS
        oqsLoginPage.loginOQS(false);

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData, createFreshCrew);

        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(true);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        //trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(empNr, verifyActivechkbox);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );

        trimHomePageAM.MinimizeMainWindow();

    }

    public void SelectFromTrimDueEmployeeSchdPlannerDropdown(String dropdownValue) throws GeneralLeanFtException, Exception{
        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        trimHomePageAM.selectMenuFromBottomMenu("^D",dropdownValue);
    }

    public void ExpandTreeNodeAndValidate(String node, String subNode, boolean visibility) throws GeneralLeanFtException {
           trimHomePageAM.validateTreeNode(empNr, node, subNode, visibility);
    }

    public void WrapperMethodToAddDuplicateEmployeeNrOQS(String[] testData, boolean visibility, boolean verifyActivechkbox) throws GeneralLeanFtException{

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData, false); // False because we are adding duplicate emp

        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(false);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(oqsHomePage.getEmployeeNumber(), verifyActivechkbox);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );

        trimHomePageAM.MinimizeMainWindow();
    }

    public void AddNewPositionInOQSVerifyInTrim (String[] testData, boolean visibility) throws Exception{
        //Login to OQS
        oqsLoginPage.loginOQS(false);

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData, true);


        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(true);


        oqsHomePage.EditPosition();

        //Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(empNr, false);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );

        trimHomePageAM.MinimizeMainWindow();


        //oqsHomePage.addPosition();



        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.verifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(empNr, false);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );

        trimHomePageAM.MinimizeMainWindow();

    }

    public void EditPositionToCreateCA(String position) throws Exception{
        oqsHomePage.EditPosition();
    }

    public void AddPosition(String position) throws Exception{
        oqsHomePage.addPosition(position);
        oqsHomePage.selectTextThroughXpath("CAPTAIN");
    }

    public void SelectTrainingEventCategory(String event) {
        oqsHomePage.LoadTrainingEventCategory(event);
    }

    public void selectTrainingEvent(String eventCategory, String events, boolean enterpriseMode){
        oqsHomePage.LoadTrainingEventCategory(eventCategory);
        ArrayList<String> allEvents=new ArrayList<String>();

        allEvents.addAll(Arrays.asList(events.split(",")));

        for(String event: allEvents){

            oqsHomePage.AddTrainingEvent(event, enterpriseMode);
        }
        oqsHomePage.HandlePopup("OK");

    }

    public void deleteEvent(String s) {
        oqsHomePage.selectEvent(s);
    }

    /**Method description: This method is used to select the equipments from Equipments dropdown and select the primaryStatus from the primary dropdown from Employee details page in Trim.
     * method call: SelectEquipmentAndPrimaryStatus("737", "First Officer")
     * method return: void*/
    public void SelectEquipmentAndPrimaryStatus(String equipments, String primaryStatus) throws GeneralLeanFtException{

        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);
        try{
            trimHomePageAM.NavigateMenu("^E-->^F");
            trimHomePageAM.SelectEquipmentAndPrimaryStatus(empNr, equipments, primaryStatus);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
