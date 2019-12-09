package com.swacorp.crew.tests.regression.oqs;
//import com.swacorp.crew.pages.trim.scheduledplanner.ScheduledPlanner;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;
import com.wap.uiautomation.crew.WinAppDriverDemo;

/**
 * Created by x227377 on 09/05/2019.
 */
public class WapDemo_TrimWorkflow_1000 extends TestManager {

    @Test(groups = {"1000", "regression"})
    public void create_CrewMember_InOQS_Verify_InTRiM_1000() {
        System.out.print("create_CrewMember_InOQS_Verify_InTRiM_1000  starting...");
        setScenarioName("Demo workflow automation by WinAppDriver");
        try {
            WinAppDriverDemo wadtest = new WinAppDriverDemo();
            wadtest.demoTest();
            System.out.print("Test 100 pased");

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}