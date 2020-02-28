package com.swacorp.tsr.rosa.ROSA_1_5;

import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.rosa.RosaHome;
import com.swacorp.tsr.rosa.RosaLogin;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import org.testng.annotations.Test;
import com.swacorp.crew.css.Css;

public class CssTest extends TestManager {
    Css css;
    RosaSolutioQueue rosaSolutioQueue;

    @Test(groups = {"9"})
    public void CssDemo() throws Exception{
        setScenarioName("TC164530_ CssDemo");
        css = new Css();
        css.loginCss();

        //e117340/ 1Sat1111
    }
}
