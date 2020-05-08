package com.swacorp.crew.tests.rosa.pto;

import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TC181464_Crew_19119_TC08_OQS_RK_TRiM_Validate_Solution_details_screen extends TestManager {
    RosaLogin rosa;
    RosaSolutionQueue rosaSolutioQueue;
    DateUtil du = new DateUtil();

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
        // TRiM BCAT 2020"
        //"TRiM Initial 2020 - Kari"
        rosaSolutioQueue = rosahome.createPTOSolutionRequest("Recurrent", "AQP 12 Month","737 All","OQS 2020 CQT","May 2020","Hard Line","");
        rosaSolutioQueue.checkDuplicateRequestExist();
        rosaSolutioQueue.veryfySolutionQueueExists();
        rosaSolutioQueue.statusPollingOfPTORequest("Recurrent", "OQS 2020 CQT","737 All","OQS 2020 CQT","May 2020","Hard Line","","04Apr2020 10:03");

    }

    @Test(groups = {"12"})
    public void Test() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
        String strExpectedDate = s.format(new Date(cal.getTimeInMillis()));
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd /C date " + "02-01-2020");
        //Runtime.getRuntime().exec("cmd /C date " + "01-02-20");
        /*DateUtil du = new DateUtil();

        System.out.print(du.getTimeDiff("05Apr2020 15:15","05Apr2020 14:52",CommonFormats.ROSA_FORMAT));
        System.out.println("du.getCurrentDate(): "+du.getCurrentDate(CommonFormats.ROSA_FORMAT));

        du.isBefore("05Apr2020 05:15","05Apr2020 18:52",CommonFormats.ROSA_FORMAT);*/
    }
}
