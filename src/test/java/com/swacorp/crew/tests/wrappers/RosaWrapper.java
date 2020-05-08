package com.swacorp.crew.tests.wrappers;

import com.swacorp.crew.pages.constants.ApplicationConstantsRosa;
import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.utils.TestUtil;

public class RosaWrapper extends TestUtil {
    RosaLogin rosaLogin;
    RosaSolutionQueue rosaSolutioQueue;

    public RosaWrapper(){
        rosaLogin = new RosaLogin();
        rosaSolutioQueue = new RosaSolutionQueue();
     }

    public void E2EFlow_RosaPTOMODIFIED() throws Exception {
        rosaLogin = new RosaLogin();
        RosaHome rosahome = rosaLogin.loginRosa();
        rosahome.VerifyHomePageAppear();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.verifyConditionalRadioButtonExist();
        rosahome.verifyCoreRadioButtonExist();
        rosahome.verifyStartAndResetButtonExist();
        rosaSolutioQueue = rosahome.createPTOSolutionRequest(ApplicationConstantsRosa.PTO_CATEGORY_RECURRENT, ApplicationConstantsRosa.PTO_CYCLE_12_MONTH,ApplicationConstantsRosa.PTO_AIRCRAFT_ALL, ApplicationConstantsRosa.ROSA_PTO_EVENT,ApplicationConstantsRosa.ROSA_PTO_TIME,ApplicationConstantsRosa.PTO_BIDLINE_HARDLINE,"");
        rosaSolutioQueue.checkDuplicateRequestExist();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.selectFilterOption(ApplicationConstantsRosa.ROSA_PTO_QUEUE_FILTER_COMMITTED);
        rosaSolutioQueue.readSolutionQueueDetails();
    }


    public void E2EFlow_RosaPTO() throws Exception{
        //setScenarioName("TC_01_ROSA Test");
        rosaLogin = new RosaLogin();
        RosaHome rosahome = rosaLogin.loginRosa();
        rosahome.VerifyHomePageAppear();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.verifyConditionalRadioButtonExist();
        rosahome.verifyCoreRadioButtonExist();
        rosahome.verifyStartAndResetButtonExist();
        rosaSolutioQueue = rosahome.createPTOSolutionRequest(ApplicationConstantsRosa.PTO_CATEGORY_RECURRENT, ApplicationConstantsRosa.PTO_CYCLE_12_MONTH,ApplicationConstantsRosa.PTO_AIRCRAFT_ALL, ApplicationConstantsRosa.ROSA_PTO_EVENT,ApplicationConstantsRosa.ROSA_PTO_TIME,ApplicationConstantsRosa.PTO_BIDLINE_HARDLINE,"");
        rosaSolutioQueue.checkDuplicateRequestExist();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.statusPollingOfPTORequest(ApplicationConstantsRosa.PTO_CATEGORY_RECURRENT, ApplicationConstantsRosa.PTO_CYCLE_12_MONTH,ApplicationConstantsRosa.PTO_AIRCRAFT_ALL,ApplicationConstantsRosa.ROSA_PTO_EVENT,ApplicationConstantsRosa.ROSA_PTO_TIME,ApplicationConstantsRosa.PTO_BIDLINE_HARDLINE,"","04Apr2020 10:03");
        //css  = rosaSolutioQueue.NavigateToCSSWithMasterDS();
        //return css;
    }
}
