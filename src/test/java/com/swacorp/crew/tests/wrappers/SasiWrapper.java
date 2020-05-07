package com.swacorp.crew.tests.wrappers;

import com.swacorp.crew.pages.constants.EnumRosa;
import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.pages.sasi.SasiHome;
import com.swacorp.crew.pages.sasi.SasiLogin;
import com.swacorp.crew.utils.TestUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class SasiWrapper extends TestUtil {
    SasiHome sasiHome;
    SasiLogin sasiLogin;

    public SasiWrapper(){
        sasiHome = new SasiHome();
        sasiLogin = new SasiLogin();
    }

    public void loginSasi(String userName, String password){
        sasiLogin.loginSasi();
    }

    public void clickWorldViewer(){
        sasiHome.clickWorldViewer();
    }

    public void selectData() {
        sasiHome.selectData();
    }

    public void clickFirstLink(){
        sasiHome.clickFirstLink();
    }

    public void readNonFlyDetails() throws ParseException {
        sasiHome.readNonFlyDetails();
    }



}
