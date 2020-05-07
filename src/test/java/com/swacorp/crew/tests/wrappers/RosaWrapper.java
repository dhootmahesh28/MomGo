package com.swacorp.crew.tests.wrappers;

import com.swacorp.crew.pages.constants.ApplicationConstantsRosa;
import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.pages.constants.EnumRosa;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.utils.TestUtil;

import java.util.ArrayList;
import java.util.Map;

public class RosaWrapper extends TestUtil {
    RosaLogin rosaLogin;
    RosaSolutionQueue rosaSolutioQueue;

    public RosaWrapper(){
        rosaLogin = new RosaLogin();
        rosaSolutioQueue = new RosaSolutionQueue();
     }

    public void E2EFlow_RosaPTOMODIFIED() throws InterruptedException {
        rosaLogin = new RosaLogin();
        RosaHome rosahome = rosaLogin.loginRosa();
        rosahome.VerifyHomePageAppear();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.verifyConditionalRadioButtonExist();
        rosahome.verifyCoreRadioButtonExist();
        rosahome.verifyStartAndResetButtonExist();
        rosaSolutioQueue = rosahome.createPTOSolutionRequest(EnumRosa.Category.RECURRENT.getValue(), EnumRosa.Cycle.TWELVEMONTHS.getValue(),EnumRosa.Aircraft.ALL737.getValue(), ApplicationConstantsRosa.ROSA_PTO_EVENT,ApplicationConstantsRosa.ROSA_PTO_TIME,EnumRosa.Bidline.HARDLINE.getValue(),"");
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
        rosaSolutioQueue = rosahome.createPTOSolutionRequest(EnumRosa.Category.RECURRENT.getValue(), EnumRosa.Cycle.TWELVEMONTHS.getValue(),EnumRosa.Aircraft.ALL737.getValue(),"OQS 2020 CQT","May 2020",EnumRosa.Bidline.HARDLINE.getValue(),"");
        rosaSolutioQueue.checkDuplicateRequestExist();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.StatusPollingOfPTORequest(EnumRosa.Category.RECURRENT.getValue(), EnumRosa.Cycle.TWELVEMONTHS.getValue(),EnumRosa.Aircraft.ALL737.getValue(),"OQS 2020 CQT","May 2020",EnumRosa.Bidline.HARDLINE.getValue(),"","04Apr2020 10:03");
        rosaSolutioQueue.readHM();
        //css  = rosaSolutioQueue.NavigateToCSSWithMasterDS();
        //return css;
    }
}
