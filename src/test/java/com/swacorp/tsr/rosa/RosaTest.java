package com.swacorp.tsr.rosa;

import com.swacorp.crew.utils.TestManager;
import org.testng.annotations.Test;

public class RosaTest extends TestManager {
    RosaLogin rosa;

    @Test(groups = {"1222"})
    public void TC_01_RosaLogin() throws Exception{
        setScenarioName("TC_01_ROSA Test");
        rosa = new RosaLogin();
        RosaHome rosahome = rosa.loginRosa();
        rosahome.VerifyHomePageAppear();
    }
}
