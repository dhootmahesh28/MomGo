package com.swacorp.crew.pages.common;

import com.swacorp.crew.pages.constants.MessageConstants;
import com.swacorp.crew.utils.FileUtil;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommonUtils extends BasePage{
    ReportUtil reportManager = new ReportUtil();
    FileUtil fileUtil = new FileUtil();
    private final Logger loggerCommUtils = Logger.getLogger(CommonUtils.class);
    private static final String REPLACE_TXT = "PLACEHOLDER";
    private static final String TEMP_XPATH = "text()='" + REPLACE_TXT + "'";
    private static final String MAIN_MENU = "//div[text()='" + REPLACE_TXT + "' and @name='megamenuItem']";
    private static final String SUB_MENU = "//*[" + TEMP_XPATH + " and contains(@class,'navLink')]";

    public void verifyValueFromEditBox(By locator, String expectedText, String strFieldName) {
        try {
            loggerBasePage.info("Before verifyValueFromEditBox::" + locator + MSG_WITH_TEXT + expectedText);
            WebElement webEditBoxText = waitForElement(locator);
            String editBoxText = webEditBoxText.getAttribute("value");
            if (expectedText == null)
                expectedText = "";
            loggerBasePage.info("After verifyValueFromEditBox::" + locator + MSG_WITH_TEXT + expectedText);
            if (editBoxText.contains(expectedText) || editBoxText.equalsIgnoreCase(expectedText)) {
                reportManager.report(MessageConstants.PASSED, "Expected Value : " + expectedText + " is same as actual value present int the " + strFieldName + " edit box.");
            } else {
                reportManager.report(MessageConstants.FAILED, "Expected Value : " + expectedText + " is not same as actual value : " + editBoxText + " present int the " + strFieldName + " edit box.");
            }
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while verifying value from edit box. Error : " + e.toString());
        }
    }

    public boolean verifyExistenceOfStaticField(String[] objects){
        Boolean blnPassed = true;
        try{
            for (String strObjName : objects)
            {
                By xpathText = By.xpath("//*[contains(text(),'" + strObjName + "')]");
                if(! isElementPresent(xpathText)){
                    reportManager.report(MessageConstants.FAILED, strObjName.replace(":", "") + " : Not Present on the screen.");
                    blnPassed = false;
                }
            }
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while verifying Existence of Static field. Error : " + e.toString());
        }
        return blnPassed;
    }

    public void verifyExistenceOfObjects(List<By> byObj){
        for(By obj : byObj){
            if(isElementPresent(obj)){
                WebElement ele = waitForElement(obj);
                reportManager.report(MessageConstants.PASSED, obj.toString() + " is present. Attached text is : "+ ele.getText());
            }else{
                reportManager.reportSelenium(MessageConstants.FAILED, obj.toString() + " is  not present.");
            }
        }
    }

    public void verifyDefaultTextInDropDown(By locator, String defaultValue, String strFieldName) {
        loggerBasePage.info("Before verifyDefaultTextInDropDown::"+locator);
        WebElement element = waitForElement(locator);
        Select dropDown = new Select(element);
        String selectedText = dropDown.getFirstSelectedOption().getText();
        loggerBasePage.info("After verifyDefaultTextInDropDown::"+locator);
        if(selectedText.equalsIgnoreCase(defaultValue)){
            reportManager.report(MessageConstants.PASSED, "Default value of Lightening condition is : " + defaultValue);
        }else{
            reportManager.reportSelenium(MessageConstants.FAILED, "Failed to verify the Default value of " + strFieldName + ". Expected value is : " + defaultValue);
        }
    }

    public void verifyTitleOfThePage(String strTitle, String strContext){
        try{
            waitByTime(2000);
            if (getDriver().getTitle().contains(strTitle)){
                reportManager.reportSelenium(MessageConstants.PASSED, "Successfully Navigated to " + strContext);
            } else {
                reportManager.report(MessageConstants.FAILED, "Failed to navigate to " + strContext + ". Title of the page is : " + getDriver().getTitle());
            }
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while verifying Title of the page. Error : " + e.toString());
        }
    }

    public void checkPresenceOfFile(String filePath, boolean blnPresence){
        try{
            waitByTime(5000);
            boolean filePresence = fileUtil.isFileExist(filePath);
            if(filePresence && blnPresence){
                reportManager.report(MessageConstants.PASSED, "File is present on the given path : " + filePath);
            }else if(!filePresence && !blnPresence){
                reportManager.report(MessageConstants.PASSED, "Specified File is not present on the given path : " + filePath);
            }else{
                reportManager.report(MessageConstants.FAILED, "Failed to verify the file presence on the path : " + filePath + ". Expected Presence: " + blnPresence + ", Actual : " + filePresence);
            }
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while verifying presence of file. Error : " + e.toString());
        }
    }

    public void switchToNewTab(String winHandleBefore){
        try{
            waitByTime(1000);
            if(winHandleBefore.equalsIgnoreCase(""))
                winHandleBefore = getDriver().getWindowHandle();

            // Perform the click operation that opens new window
            // Switch to new window opened
            int count = getDriver().getWindowHandles().size();
            if(count == 1){
                reportManager.reportSelenium(MessageConstants.FAILED, "Only one Window/Tab is opened. Failed to navigate to another window.");
            }
            for(String winHandle : getDriver().getWindowHandles()){
                if(!winHandleBefore.equals(winHandle)) {
                    getDriver().switchTo().window(winHandle);
                    break;
                }
            }
            reportManager.reportSelenium(MessageConstants.PASSED, "Successfully navigated to new Tab/Window. Title of the Page is : " + getDriver().getTitle());
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while switching to new Tab. Error : " + e.toString());
        }
    }

    public void navigateToMenu(String strMenu) {

        String[] arrMenus =  strMenu.split("-->");
        String strMenuToNavigate;
        try {
            for(int i=0; i < arrMenus.length; i++){
                if(i == 0) {
                    strMenuToNavigate = MAIN_MENU.replace(REPLACE_TXT, arrMenus[i]);
                }else{
                    strMenuToNavigate = SUB_MENU.replace(REPLACE_TXT, arrMenus[i]);
                }

                if(i == arrMenus.length-1){
                    reportManager.reportSelenium(MessageConstants.PASSED, "Navigating to " + strMenu);
                }

                List<WebElement> list = getDriver().findElements(By.xpath(strMenuToNavigate));
                if (list.size() > 1) {
                    strMenuToNavigate = SUB_MENU.replace(REPLACE_TXT, arrMenus[i - 1]) + "//following::*[" + TEMP_XPATH.replace(REPLACE_TXT, arrMenus[i]) + " and contains(@class,'navLink')]";
                }
                if (isElementPresent(By.xpath(strMenuToNavigate))) {
                    buttonClick(By.xpath(strMenuToNavigate));
                } else {
                    String[] arrSplitMenu = arrMenus[i].split(" ");
                    String strPath = "starts-with(text(), '" + arrSplitMenu[0] + "') and contains(text(), '" + arrSplitMenu[arrSplitMenu.length - 1] + "')";
                    strMenuToNavigate = SUB_MENU.replace(TEMP_XPATH, strPath);
                    if (isElementPresent(By.xpath(strMenuToNavigate))) {
                        buttonClick(By.xpath(strMenuToNavigate));
                    } else {
                        reportManager.reportSelenium(MessageConstants.FAILED, "Navigation to " + strMenu + " failed.");
                    }
                }

            }
        } catch (Exception e) {
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while navigating to menu. Error : " + e.toString());
        }
    }

    public void closeTabsAndSwitchToInnerFrame(String strParentWindow){
       try{
            for(String winHandle : getDriver().getWindowHandles()){
                if(!winHandle.equalsIgnoreCase(strParentWindow)) {
                    getDriver().close();
                    reportManager.report(MessageConstants.INFO, "Extra Tab is closed.");
                }
            }

            getDriver().switchTo().window(strParentWindow);
            switchToIFrame(1);
       }catch(Exception e){
           loggerCommUtils.error(e);
           reportManager.reportSelenium(MessageConstants.ERROR, "Error while closing and switching to inner frame. Error : " + e.toString());
       }
    }

    public void switchToIFrame(int intFrame){
        try{
            List<WebElement> iFrameElements = getDriver().findElements(By.tagName("iframe"));
            if(iFrameElements.size() > 1) {
                getDriver().switchTo().frame(iFrameElements.get(intFrame));
                waitByTime(1000);
            }
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while switching to inner frame. Error : " + e.toString());
        }
    }


    public void switchToFrame(int intFrame){
        try{
            List<WebElement> FrameElements = getDriver().findElements(By.tagName("frame"));
            if(FrameElements.size() >= 1) {
                getDriver().switchTo().frame(FrameElements.get(intFrame));

                waitByTime(1000);
            }
        }catch(Exception e){
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while switching to  frame. Error : " + e.toString());
        }
    }


    public void selectFromList(By xpathInputLocator, String strValue){
        try {
            WebElement element = waitForElement(xpathInputLocator);
            scrollToElement(element);
            element.click();
            waitByTime(1000);
            By xpathToSelect = By.xpath("//*[text() = '" + strValue +"']");
            if (isElementPresent(xpathToSelect)) {
                buttonClick(xpathToSelect);
                printConsole("selectFromList passed");
            }else {
                element.sendKeys(strValue);
                waitByTime(2000);
                buttonClick(xpathToSelect);
                printConsole("selectFromList passed");
            }
            if(! element.getAttribute("value").equalsIgnoreCase(strValue))
                reportManager.report(MessageConstants.FAILED, "Failed to select : " + strValue);
            else
                reportManager.report(MessageConstants.PASSED, "Successfully selected : " + strValue + " from the list.");
        } catch (Exception e) {
            loggerCommUtils.error(e);
            reportManager.reportSelenium(MessageConstants.ERROR, "Error while selecting from list. Error : " + e.toString());
        }
    }

    public String getcurrentdateUsingDatePicker() {
        //getDriver().findElement(locator).click();
        final LocalDate date = LocalDate.now();
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd");
        String getCurrentDate = date.format(dateFormat);
        getDriver().findElement(By.xpath("//*[text()='" + getCurrentDate + "']")).click();
        return getCurrentDate;
    }

    public int getSizeOfElement(By locator){
        List<WebElement> webelement = getDriver().findElements(locator);
        int a =webelement.size();
        return a;

    }

}
