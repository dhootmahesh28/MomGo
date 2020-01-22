package com.swacorp.crew.tests.regression.oqs.crew14477.Wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.oqs.homepage.HomePage;
import com.swacorp.crew.pages.oqs.login.OQSLoginPage;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.crew.pages.trim.login.LoginPage_am;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class Add_Crew_Member_In_OQS_Verify_In_Trim extends TestManager {
    private final Logger LOGGER = Logger.getLogger(Add_Crew_Member_In_OQS_Verify_In_Trim.class);
    LoginPage_am trimLoginPage ;
    TrimHomePageAM trimHomePageAM;
    OQSLoginPage oqsLoginPage ;
    HomePage oqsHomePage;

    public Add_Crew_Member_In_OQS_Verify_In_Trim(){
        oqsLoginPage = new OQSLoginPage();
        trimLoginPage = new LoginPage_am();
        oqsHomePage = new HomePage();
    }

    public void WrapperMethod(String[] testData, boolean visibility, boolean verifyActivechkbox, boolean createFreshCrew) throws GeneralLeanFtException{
        //Login to OQS
        oqsLoginPage.loginOQS();

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData, createFreshCrew);

        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(true);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.VerifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(empNr, verifyActivechkbox);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );

        trimHomePageAM.MinimizeMainWindow();

    }

    public void WrapperMethodToAddDuplicateEmployeeNrOQS(String[] testData, boolean visibility, boolean verifyActivechkbox) throws GeneralLeanFtException{

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData, false); // False because we are adding duplicate emp

        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(false);

        ////Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.VerifyLoginSuccessful(true);

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
        oqsLoginPage.loginOQS();

        //Add Crew Member
        String empNr = oqsHomePage.addCrewMember(testData, true);


        //Verify that Crew member is successfully added.
        oqsHomePage.VerifyCrewAddedSuccessfully(true);


        oqsHomePage.EditPosition();

        //Login to Trim
        trimHomePageAM = trimLoginPage.loginTRiM(EnvironmentConstants.TRiMLOGINUSER, EnvironmentConstants.TRiMLOGINPASSWORD);

        //Verify login is successful.
        trimLoginPage.VerifyLoginSuccessful(true);

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
        trimLoginPage.VerifyLoginSuccessful(true);

        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");

        //Search the employee details in Trim.
        trimHomePageAM.SearchEmployeesDetails(empNr, false);

        //Verify that the details are not visible in Trim
        trimHomePageAM.ValidateSearchResults(visibility );

        trimHomePageAM.MinimizeMainWindow();

    }

    public void EditPositionToCReateCA () throws Exception{
        //Login to OQS

        oqsHomePage.EditPosition();
    }

    public void VerifyDueEmployee() throws GeneralLeanFtException {

        trimHomePageAM.flushAllChileWindowsExceptMain();
        //Navigate Main Menu on Trim
        trimHomePageAM.NavigateMenu("^E-->^F");
    }

    public void addDuplicateCrewMember(String[] data, boolean status){
        boolean res = oqsHomePage.addDuplicateCrewMember(data, status);

    }
}
