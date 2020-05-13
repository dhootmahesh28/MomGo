package com.swacorp.crew.tests.oqs;

import com.hp.lft.sdk.winforms.EditField;
import com.swacorp.crew.pages.css.CssLogin;
import com.swacorp.crew.pages.trim.TrimLogin;
import com.swacorp.crew.tests.dataprovider.TestDataProvider;
import com.swacorp.crew.tests.wrappers.OqsWrapper;
import com.swacorp.crew.tests.wrappers.TrimWrapper;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.crew.tests.wrappers.Add_Crew_Member_In_OQS_Verify_In_Trim;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC181506 extends TestManager implements java.io.Serializable {
    private final Logger LOGGER = Logger.getLogger(TC181506.class);

    OqsWrapper oqsWrapper;
    TrimWrapper trimWrapper;
    TC181506(){
        oqsWrapper = new OqsWrapper();
        trimWrapper = new TrimWrapper();
    }


    @Test(priority=1,groups = {"181506", "regression"}, dataProvider = "TC181506", dataProviderClass = TestDataProvider.class)
    public void TC181506(String[] testData) {
        setScenarioName("TC181506_OQS_RK-TRiM-FO-Add_Flight_Training_event_under_Initial_to_an_FO");
        boolean applyenterpriseMode = true;
        String domicile = testData[3];
        String firstName = testData[4];
        String schdPlannerDropdownValue = testData[16];
        String oqsTrainingEventType = testData[17];
        String event = testData[18];
        String partialNodetextTrimSchedTV = testData[19];
        String schdPlannerRandomDropdownValueToRefreshTVData = testData[20];
        String equipment = testData[21]; //737
        String primaryStatusTrimEmpDetailsWnd =  testData[22];

        /*LOGGER.info("Starting execution..");
        oqsWrapper.addCrewmember(testData, true, applyenterpriseMode);
        oqsWrapper.editPositionToCreateCA();
        oqsWrapper.addPosition("CAPTAIN-737 All");
        oqsWrapper.selectTrainingEventCategory(oqsTrainingEventType);
        oqsWrapper.selectTrainingEvent(oqsTrainingEventType, event, applyenterpriseMode);
      *//*  trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV,firstName, true);
        trimWrapper.selectEquipmentAndPrimaryStatus(equipment,primaryStatusTrimEmpDetailsWnd);*//*
        oqsWrapper.deleteEvent(event);
        *//*trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerRandomDropdownValueToRefreshTVData);
        trimWrapper.selectFromTrimDueEmployeeSchdPlannerDropdown(schdPlannerDropdownValue);
        trimWrapper.expandTreeNodeAndValidate("737;"+domicile, partialNodetextTrimSchedTV, firstName,false);
        LOGGER.info("Starting finished..");*//*

        */
    }
}