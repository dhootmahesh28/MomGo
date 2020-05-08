package com.swacorp.crew.tests.wrappers;

import com.swacorp.crew.pages.sasi.SasiHome;
import com.swacorp.crew.pages.sasi.SasiLogin;
import com.swacorp.crew.utils.TestUtil;
import java.text.ParseException;


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
