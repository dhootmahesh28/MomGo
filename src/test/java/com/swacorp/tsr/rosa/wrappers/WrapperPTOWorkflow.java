package com.swacorp.tsr.rosa.wrappers;

import com.swacorp.crew.css.Css;
import com.swacorp.crew.pages.trim.homepage.TrimHomePageAM;
import com.swacorp.tsr.enums.EnumRosa;
import com.swacorp.tsr.rosa.RosaHome;
import com.swacorp.tsr.rosa.RosaLogin;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import com.swacorp.tsr.sasi.SasiHome;
import com.swacorp.tsr.sasi.SasiLogin;

import java.util.ArrayList;
import java.util.Map;

public class WrapperPTOWorkflow {
    RosaLogin rosa;
    RosaSolutioQueue rosaSolutioQueue;
    Css css;
    Map<String, Map<String, ArrayList<String[]>>> masterHM;


    public Map<String, Map<String, ArrayList<String[]>>> getMasterHM(){
        return masterHM;
    }

    public void setMasterHM(){
        masterHM = rosaSolutioQueue.getHM();
    }
    public WrapperPTOWorkflow(RosaLogin ros,RosaSolutioQueue solq, Css cssMainPage ){
        rosa = ros;
        rosaSolutioQueue = solq;
        css = cssMainPage;
    }

    public Css E2EFlow_RosaPTOMODIFIED() throws Exception{
        //setScenarioName("TC_01_ROSA Test");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        rosahome.VerifyHomePageAppear();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.verifyConditionalRadioButtonExist();
        rosahome.verifyCoreRadioButtonExist();
        rosahome.verifyStartAndResetButtonExist();
        // TRiM BCAT 2020"
        //"TRiM Initial 2020 - Kari"
        //rosaSolutioQueue = rosahome.createPTOSolutionRequest("Recurrent", "AQP 12 Month","737 All","OQS 2020 CQT","May 2020","Hard Line","");
        rosaSolutioQueue = rosahome.createPTOSolutionRequest(EnumRosa.Category.RECURRENT.getValue(), EnumRosa.Cycle.TWELVEMONTHS.getValue(),EnumRosa.Aircraft.ALL737.getValue(),"OQS 2020 CQT","May 2020",EnumRosa.Bidline.HARDLINE.getValue(),"");
        rosaSolutioQueue.checkDuplicateRequestExist();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.selectFilterOption("Committed");
        rosaSolutioQueue.readSolutionQueueDetails();
        rosaSolutioQueue.readHM();
        //rosaSolutioQueue.StatusPollingOfPTORequest(EnumRosa.Category.RECURRENT.getValue(), EnumRosa.Cycle.TWELVEMONTHS.getValue(),EnumRosa.Aircraft.ALL737.getValue(),"OQS 2020 CQT","May 2020",EnumRosa.Bidline.HARDLINE.getValue(),"","04Apr2020 10:03");
        //rosaSolutioQueue.readHM();
        css  = rosaSolutioQueue.NavigateToCSSWithMasterDS();
        return css;
    }
    public SasiLogin navigateToSasi() throws Exception{
        return rosaSolutioQueue.NavigateToSasiWithMasterDS();
    }

    public Css E2EFlow_RosaPTO() throws Exception{
        //setScenarioName("TC_01_ROSA Test");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
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
        css  = rosaSolutioQueue.NavigateToCSSWithMasterDS();
        return css;
    }
}
