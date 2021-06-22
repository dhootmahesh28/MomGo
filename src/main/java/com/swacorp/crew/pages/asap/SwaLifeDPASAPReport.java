package com.swacorp.crew.pages.asap;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.common.CommonUtils;
import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SwaLifeDPASAPReport extends BasePage {

    private final Logger loggerSWALifeASAPRpt = Logger.getLogger(SwaLifeDPASAPReport.class);
    ReportUtil report = new ReportUtil();
    CommonUtils commonUtils = new CommonUtils();
    SwaLifeASAPReport swaLifeASAPReport = new SwaLifeASAPReport();

    private String strIsFlightSpecific;


    private static final By idFlightSpecificYes = By.id("rbFlightRelatedYes");
    private static final By idFlightSpecificNo = By.id("rbFlightRelatedNo");//
    private static final By idCreateFlightReport = By.id("buttonCreateFlightReport");
    private static final By idCreateNotFlightReport = By.id("buttonCreateNonFlightReport");
    private static final By idFlightInfotable = By.id("FlightInfo");
    private static final By idPrimaryPhone = By.id("PrimaryPhoneTxt");
    private static final By idEmailAdd = By.id("EmailTxt");
    private static final By idAddress = By.id("AddressTxt");

    private static final By xpathNextPage = By.id("NextButton");

    private static final By xpathPosition = By.id("uxDdlSeat");
    private static final By xpathYearsAsDispatcher = By.id("TextboxYearsDP");
    private static final By xpathHoursOnDutyDuringTimeOfevent = By.id("TextboxHoursDuty");

    public String getStrIsFlightSpecific() {
        return strIsFlightSpecific;
    }

    public void setStrIsFlightSpecific(String strIsFlightSpecific) {
        this.strIsFlightSpecific = strIsFlightSpecific;
    }

    /**
     * selectFlightSpecificType - This method will select what kind of report we want to submit
     * @param strIsFlightSpecific
     */
    public void selectFlightSpecificType(String strIsFlightSpecific) {
        try {

            if (strIsFlightSpecific.equalsIgnoreCase("YES")) {
                buttonClick(idFlightSpecificYes);
                if (isElementVisible(idCreateFlightReport)) {
                    report.reportSelenium(MessageConstants.PASSED, "Create Flight Related report button is visible on clicking Yes Radio Button");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Create Flight Related report button is NOT visible on clicking Yes Radio Button");
                }
                buttonClick(idCreateFlightReport);
                if (isElementVisible(idFlightInfotable)) {
                    report.reportSelenium(MessageConstants.PASSED, "Creating flight specific ASAP report AND Flight info header is  displayed");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Creating flight specific ASAP report AND Flight info header is NOT displayed");
                }
            } else if (strIsFlightSpecific.equalsIgnoreCase("NO")) {
                buttonClick(idFlightSpecificNo);
                if (isElementVisible(idCreateNotFlightReport)) {
                    report.reportSelenium(MessageConstants.PASSED, "Create NON Flight Related report button is visible on clicking No Radio Button");
                } else {
                    report.reportSelenium(MessageConstants.FAILED, "Create NON Flight Related report button is NOT visible on clicking No Radio Button");
                }
                buttonClick(idCreateNotFlightReport);
                if (isElementVisible(idFlightInfotable)) {
                    report.reportSelenium(MessageConstants.FAILED, "Creating Non flight specific ASAP report AND Flight info header is  displayed");
                } else {
                    report.reportSelenium(MessageConstants.PASSED, "Creating NON flight specific ASAP report AND Flight info header is not displayed");
                }
            }
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
            report.reportSelenium(MessageConstants.ERROR, "Error while selecting flight specific. Error : " + e.toString());
        }
    }

    /**
     * addContactInfo - Adds contact info of the member
     * @param strPhoneNum
     * @param strEmailAdd
     * @param strAddress
     */
    public void addContactInfo(String strPhoneNum, String strEmailAdd, String strAddress) {
        try {
            swaLifeASAPReport.verifyShowAndHideHelpSection();

            clearAndEnterText(idPrimaryPhone, strPhoneNum);
            clearAndEnterText(idEmailAdd, strEmailAdd);
            clearAndEnterText(idAddress, strAddress);
            scrollToElement(waitForElement(idPrimaryPhone));

            report.reportSelenium(MessageConstants.PASSED, "Contact Info Entered successfully.");
            if(getStrIsFlightSpecific().equalsIgnoreCase("No"))
                buttonClick(xpathNextPage);
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
        }
    }

    /**
     * addExperience - Add Employee experience
     * @param strPosition
     * @param strYearsAsDispatcher
     * @param strHoursOnDutyDuringTimeOfEvent
     */
    public void addExperience(String strPosition, String strYearsAsDispatcher, String strHoursOnDutyDuringTimeOfEvent) {
        try {
            clearAndEnterText(xpathYearsAsDispatcher, strYearsAsDispatcher);
            clearAndEnterText(xpathHoursOnDutyDuringTimeOfevent, strHoursOnDutyDuringTimeOfEvent);
            selectOption(xpathPosition, strPosition);

            report.reportSelenium(MessageConstants.PASSED, "Event info and Experience Entered successfully.");
        } catch (Exception e) {
            loggerSWALifeASAPRpt.error(e);
        }

    }


}
