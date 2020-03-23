package com.swacorp.tsr.rosa;

import com.swacorp.crew.utils.TestManager;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class TC181464_Crew_19119_TC08_OQS_RK_TRiM_Validate_Solution_details_screen extends TestManager {
    RosaLogin rosa;
    RosaSolutioQueue rosaSolutioQueue;

    @Test(groups = {"181464"})
    public void TC_01_RosaLogin() throws Exception{
        setScenarioName("TC_01_ROSA Test");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        rosahome.VerifyHomePageAppear();
        rosahome.clickAndVerifyPilotTrainingOptimizer();
        rosahome.verifyConditionalRadioButtonExist();
        rosahome.verifyCoreRadioButtonExist();
        rosahome.verifyStartAndResetButtonExist();
        rosaSolutioQueue = rosahome.selectFromDropDown("OQS ETOPS QUALIFICATION TRAINING MAR");
        rosaSolutioQueue.clickViewQueuePage();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.validateCompleteStatus();



    }
}
