package com.swacorp.crew.tests.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.oqs.OqsHome;
import com.swacorp.crew.pages.oqs.OqsLogin;
import com.swacorp.crew.utils.TestUtil;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by x257093 on 03-Jan-2020.
 */

public class OqsWrapper extends TestUtil {
    private final Logger logger = Logger.getLogger(OqsWrapper.class);
    OqsLogin oqsLoginPage ;
    OqsHome oqsHomePage;
    String empNr;

    public OqsWrapper(){
        oqsLoginPage = new OqsLogin();
        oqsHomePage = new OqsHome();
    }

    public void addCrewmember(String[] testData, boolean createFreshCrew, boolean applyEnterpriseMode){
        //Login to OQS
        oqsLoginPage.loginOQS(applyEnterpriseMode);
        logger.info("Login done...");
        //Add Crew Member
        empNr = oqsHomePage.addCrewMember(testData, createFreshCrew);

        //Verify that Crew member is successfully added.
        oqsHomePage.verifyCrewAddedSuccessfully(true);
    }

    public void wrapperMethodToAddDuplicateEmployeeNrOQSOQS(String[] testData) throws GeneralLeanFtException{
        empNr = oqsHomePage.addCrewMember(testData, false); // False because we are adding duplicate emp
        oqsHomePage.verifyCrewAddedSuccessfully(false);
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

}
