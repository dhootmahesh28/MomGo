package com.swacorp.crew.pages.asap;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.DriverSource;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SwaLifeLogin extends BasePage {

    private final Logger loggerSWALifeLogin = Logger.getLogger(SwaLifeLogin.class);
    ReportUtil report = new ReportUtil();
    CommonUtils commonUtils = new CommonUtils();
    DriverSource driverSource = new DriverSource();



    private final By xpathContactManagerbtn = By.xpath("//*[@id='Header1_LinkEmail']");
    private final By xpathAsapReporting = By.xpath("//img[contains(@src,'asap_reporting')]");
    private final By idUserIdInput = By.id("useridField");
    private final By idPasswordInput = By.name("password");
    private final By idLoginBtn = By.name("Submit");
    private final By xpathnextbutton = By.xpath("//*[text()='Next']");

   private final By xpathnewMoMBTN = By.xpath("//*[text()='New MOM Report']");
    private final By xpathMomAdmin = By.xpath("//*[text()='MOM Administration']");

    private static final String MSG_PASS_NAVIGATE_LAUNCH_ASAP = "Navigated to Page - Launch ASAP Report.";
    private static final String MSG_FAIL_NAVIGATE_LAUNCH_ASAP="Failed to Navigated to Page - Launch ASAP Report.";
    private static final String TITLE_LAUNCH_ASAP="Launch ASAP";
   private static final String TITLE_EVENT_ASAP="ASAP Event Report";
    private static final String FAIL_SWALIFIE_LOGIN="Failed to login to SWALife application. Error : ";
    private static final String APP_STATE_BEFORE_CLICKING_LOGIN="App state before clicking on Login Button";
    private static final String TITLE_LEGACY_SWALIFE= "Legacy SWALife Login Page";


    public void loginSWALife(String strRole) {
        try {
            String swaLifeURL = EnvironmentConstants.SWA_LIFE_URL;
            String swaLifeUser;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;


            if(strRole.equalsIgnoreCase("MANAGER") ){
                swaLifeUser = EnvironmentConstants.SWA_LIFE_LOGIN_MANAGER;

            }
            else if (strRole.equalsIgnoreCase("GoMOMUser")){
                swaLifeUser = EnvironmentConstants.SWA_LIFE_GOMOM_USER;
                swaLifeURL = EnvironmentConstants.SWA_LIFE_URL_USER;

            }
            else if (strRole.equalsIgnoreCase("GoMOMAdmin")){
                swaLifeUser = EnvironmentConstants.SWA_LIFE_GOMOM_ADMIN;
                swaLifeURL = EnvironmentConstants.SWA_LIFE_URL_ADMIN;

            }
            else{
                swaLifeUser = EnvironmentConstants.SWA_LIFE_LOGIN_USER;

            }

            driverSource.newDriver();
            getDriver().get(swaLifeURL);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);

            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife application is successful. URL: " + swaLifeURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife application URL: " + swaLifeURL);
            }

            enterText(idUserIdInput, swaLifeUser);
            enterText(idPasswordInput, swaLifePassword);

            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);



        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginSWALifeGoMOM() {
        try {

               String gomomUserURL = EnvironmentConstants.SWA_LIFE_URL_USER;

               String userID = EnvironmentConstants.SWA_LIFE_GOMOM_USER;
               String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

               String strTitle = TITLE_LEGACY_SWALIFE;
               String strTitle1 = "Mom Report Frame";
               String strTitle2="MOM Incomplete Reports List";


                getDriver().get(gomomUserURL);
                commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);

                waitUntilElementClickable(idLoginBtn);
                waitForElement(idUserIdInput);


                if (isElementVisible(idLoginBtn)) {
                    report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife GoMOM user application is successful. URL: " + gomomUserURL);
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife GoMOM user application URL: " + gomomUserURL);
                }

                enterText(idUserIdInput, userID);
                enterText(idPasswordInput, swaLifePassword);
                report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
                buttonClick(idLoginBtn);

                  if(isElementVisible(xpathnewMoMBTN)) {
                    commonUtils.verifyTitleOfThePage(strTitle2, "MOM Incomplete Reports List");
                    report.reportSelenium(MessageConstants.INFO, "Login to SWALife GoMOM user application is successful. URL: " + gomomUserURL);
                }

                else if (!isElementVisible(xpathnewMoMBTN)) {
                      commonUtils.switchToFrame(1);
                      if(isElementVisible(xpathnextbutton)) {
                      commonUtils.verifyTitleOfThePage(strTitle1, "Mom Report Frame");
                      report.reportSelenium(MessageConstants.INFO, "Login to SWALife GoMOM user application is successful. URL: " + gomomUserURL);}

                  }

                 else {
                      report.reportSelenium(MessageConstants.FAILED, "Failed to Login to SWALife GoMOM user application URL: " + gomomUserURL);


                }



        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginGoMOMaDMIN() {
        try {
            String gomomAdminURL = EnvironmentConstants.SWA_LIFE_URL_ADMIN;

            String adminID =EnvironmentConstants.SWA_LIFE_GOMOM_ADMIN ;

            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;

            String strTitle3="MOM Search Reports Page";



                getDriver().get(gomomAdminURL);
                commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
                waitUntilElementClickable(idLoginBtn);
                waitForElement(idUserIdInput);
                if (isElementVisible(idLoginBtn)) {
                    report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife GoMOM Admin application is successful. URL: " + gomomAdminURL);
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife Admin user application URL: " + gomomAdminURL);
                }
                enterText(idUserIdInput, adminID);
                enterText(idPasswordInput, swaLifePassword);
                report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
                buttonClick(idLoginBtn);

                if(isElementVisible(xpathMomAdmin)) {
                    commonUtils.verifyTitleOfThePage(strTitle3, "MOM Search Reports Page");
                    report.reportSelenium(MessageConstants.INFO, "Login to SWALife GoMOM Admin application is successful. URL: " + gomomAdminURL);
                }else{
                    report.reportSelenium(MessageConstants.INFO, "Login to SWALife GoMOM Admin application is NOT successful. URL: " + gomomAdminURL);
                }




        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void restartbrowser(){
        try{

            getDriver().manage().deleteAllCookies();
            restartDriver();



        }catch(Exception e){
            loggerSWALifeLogin.error(e);
            report.reportSelenium("ERROR", "Failed to Logout from SwaLife. Error : ");
        }
    }

    public void loginASAPFoUser() {
        try {

            String fOUserURL = EnvironmentConstants.ASAP_FO_USER_URL;
            String fOUserID = EnvironmentConstants.ASAP_FO_USER_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;
            String strTitle1 = TITLE_LAUNCH_ASAP;
            String strTitle2 = TITLE_EVENT_ASAP;

            getDriver().get(fOUserURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP FO USER application is successful. URL: " + fOUserURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP FO USER application URL: " + fOUserURL);
            }
            enterText(idUserIdInput, fOUserID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);

            if (isElementVisible(xpathAsapReporting)) {
                commonUtils.verifyTitleOfThePage(strTitle1, TITLE_LAUNCH_ASAP);
                report.reportSelenium(MessageConstants.PASSED, "Login to SWALife ASAP FO USER application is successful.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Login to SWALife ASAP FO USER application is NOT successful.");
            }
            String winHandleNew = getDriver().getWindowHandle();
            buttonClick(xpathAsapReporting);
            commonUtils.switchToNewTab(winHandleNew);

            if (isElementVisible(xpathContactManagerbtn)) {
                commonUtils.verifyTitleOfThePage(strTitle2, TITLE_EVENT_ASAP);
                report.reportSelenium(MessageConstants.INFO, MSG_PASS_NAVIGATE_LAUNCH_ASAP);
            } else {
                report.reportSelenium(MessageConstants.FAILED, MSG_FAIL_NAVIGATE_LAUNCH_ASAP);
            }



        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPIFUser() {
        try {

            String iFUserURL = EnvironmentConstants.ASAP_IF_USER_URL;
            String iFUserID = EnvironmentConstants.ASAP_IF_USER_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;
            String strTitle1 = TITLE_LAUNCH_ASAP;
            String strTitle2 = TITLE_EVENT_ASAP;

            getDriver().get(iFUserURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP IF USER application is successful. URL: " + iFUserURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP IF USER application URL: " + iFUserURL);
            }
            enterText(idUserIdInput, iFUserID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);

            if (isElementVisible(xpathAsapReporting)) {
                commonUtils.verifyTitleOfThePage(strTitle1, TITLE_LAUNCH_ASAP);
                report.reportSelenium(MessageConstants.PASSED, "Login to SWALife ASAP IF USER application is successful.");
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Login to SWALife ASAP IF USER application is NOT successful.");
            }
            String winHandleNew = getDriver().getWindowHandle();
            buttonClick(xpathAsapReporting);
            commonUtils.switchToNewTab(winHandleNew);

            if (isElementVisible(xpathContactManagerbtn)) {
                commonUtils.verifyTitleOfThePage(strTitle2, TITLE_EVENT_ASAP);
                report.reportSelenium(MessageConstants.INFO, MSG_PASS_NAVIGATE_LAUNCH_ASAP);
            } else {
                report.reportSelenium(MessageConstants.FAILED, MSG_FAIL_NAVIGATE_LAUNCH_ASAP);
            }



        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPMXUser() {
        try {

            String mXUserURL = EnvironmentConstants.ASAP_MX_USER_URL;
            String mXUserID = EnvironmentConstants.ASAP_MX_USER_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;

            String strTitle2 = TITLE_EVENT_ASAP;

            getDriver().get(mXUserURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP MX USER application is successful. URL: " + mXUserURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP MX USER application URL: " + mXUserURL);
            }
            enterText(idUserIdInput, mXUserID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);



            if (isElementVisible(xpathContactManagerbtn)) {
                commonUtils.verifyTitleOfThePage(strTitle2, TITLE_EVENT_ASAP);
                report.reportSelenium(MessageConstants.INFO, MSG_PASS_NAVIGATE_LAUNCH_ASAP);
            } else {
                report.reportSelenium(MessageConstants.FAILED, MSG_FAIL_NAVIGATE_LAUNCH_ASAP);
            }



        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPDPUser() {
        try {

            String mXUserURL = EnvironmentConstants.ASAP_DP_USER_URL;
            String mXUserID = EnvironmentConstants.ASAP_DP_USER_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;

            String strTitle2 = TITLE_EVENT_ASAP;

            getDriver().get(mXUserURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP DP USER application is successful. URL: " + mXUserURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP DP USER application URL: " + mXUserURL);
            }
            enterText(idUserIdInput, mXUserID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);



            if (isElementVisible(xpathContactManagerbtn)) {
                commonUtils.verifyTitleOfThePage(strTitle2, TITLE_EVENT_ASAP);
                report.reportSelenium(MessageConstants.INFO, MSG_PASS_NAVIGATE_LAUNCH_ASAP);
            } else {
                report.reportSelenium(MessageConstants.FAILED, MSG_FAIL_NAVIGATE_LAUNCH_ASAP);
            }



        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPFOManager() {
        try {

            String fOManagerURL = EnvironmentConstants.ASAP_FO_Manager_URL;
            String fOManagerID = EnvironmentConstants.ASAP_FO_Manager_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;



            getDriver().get(fOManagerURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP FO Manager application is successful. URL: " + fOManagerURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP FO Manager application URL: " + fOManagerURL);
            }
            enterText(idUserIdInput, fOManagerID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);





        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPIFManager() {
        try {

            String iFManagerURL = EnvironmentConstants.ASAP_IF_Manager_URL;
            String iFManagerID = EnvironmentConstants.ASAP_IF_Manager_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;



            getDriver().get(iFManagerURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP IF Manager application is successful. URL: " + iFManagerURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP IF Manager application URL: " + iFManagerURL);
            }
            enterText(idUserIdInput, iFManagerID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);





        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPMXManager() {
        try {

            String mXManagerURL = EnvironmentConstants.ASAP_MX_Manager_URL;
            String mXManagerID = EnvironmentConstants.ASAP_MX_Manager_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;



            getDriver().get(mXManagerURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP MX Manager application is successful. URL: " + mXManagerURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP MX Manager application URL: " + mXManagerURL);
            }
            enterText(idUserIdInput, mXManagerID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);





        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

    public void loginASAPDPManager() {
        try {

            String dPManagerURL = EnvironmentConstants.ASAP_DP_Manager_URL;
            String dPManagerID = EnvironmentConstants.ASAP_DP_Manager_ID;
            String swaLifePassword = EnvironmentConstants.SWA_LIFE_PASSWORD;

            String strTitle = TITLE_LEGACY_SWALIFE;



            getDriver().get(dPManagerURL);
            commonUtils.verifyTitleOfThePage(strTitle, TITLE_LEGACY_SWALIFE);
            waitUntilElementClickable(idLoginBtn);
            waitForElement(idUserIdInput);
            if (isElementVisible(idLoginBtn)) {
                report.reportSelenium(MessageConstants.INFO, "Navigation to SWALife ASAP DP Manager application is successful. URL: " + dPManagerURL);
            } else {
                report.reportSelenium(MessageConstants.FAILED, "Failed to navigate to SWALife ASAP DP Manager application URL: " + dPManagerURL);
            }
            enterText(idUserIdInput, dPManagerID);
            enterText(idPasswordInput, swaLifePassword);
            report.reportSelenium(MessageConstants.INFO, APP_STATE_BEFORE_CLICKING_LOGIN);
            buttonClick(idLoginBtn);





        } catch (Exception e) {
            loggerSWALifeLogin.error(e);
            report.reportSelenium(MessageConstants.ERROR, FAIL_SWALIFIE_LOGIN + e.toString());
        }
    }

}



