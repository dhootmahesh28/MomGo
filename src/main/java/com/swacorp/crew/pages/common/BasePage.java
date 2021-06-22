package com.swacorp.crew.pages.common;

import com.aventstack.extentreports.ExtentTest;
import com.swacorp.crew.utils.DriverSource;
import com.swacorp.crew.utils.TestUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by x17212 on 5/2/2016.
 */
public class BasePage extends TestUtil {

    public  static final Logger loggerBasePage = Logger.getLogger(BasePage.class);
    static final String MSG_WITH_TEXT = ", with Text::";

    public WebDriver getDriver() {
        Long threadId = Thread.currentThread().getId();
        return TestUtil.driverMap.get(threadId);
    }

    public ExtentTest getExtentTest() {
        Long threadId = Thread.currentThread().getId();
        return TestUtil.extentTestMap.get(threadId);
    }

    public FluentWait<WebDriver> webDriverFluentWait() {
        return new FluentWait(getDriver())
                .withTimeout(25, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, NoSuchFrameException.class);
    }

    public WebElement waitForElement(final By byElement) {
        WebElement element = null;
        try {
            loggerBasePage.info("BeforeWaitForElement::" + byElement);
            waitUntilDomLoad();
            webDriverFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            element = getDriver().findElement(byElement);
        } catch (WebDriverException e) {
            loggerBasePage.info("Exception in waitForElement::" + byElement);
            loggerBasePage.error(e);
            throw new WebDriverException(e.getMessage());
        }
        loggerBasePage.info("AfterWaitForElement::" + byElement);
        return element;
    }

    public WebElement waitForElement(WebElement webElement) {

        try {
            loggerBasePage.info("BeforeWaitForElement::" + webElement);
            webDriverFluentWait().until(ExpectedConditions.visibilityOf(webElement));
        } catch (WebDriverException e) {
            loggerBasePage.info("Exception in waitForElement::" + webElement);
            throw new WebDriverException(e.getMessage());
        }
        loggerBasePage.info("AfterWaitForElement::" + webElement);
        return webElement;
    }

    public void scrollToElement(WebElement webelement) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true);", webelement);
        loggerBasePage.info("ScrollToElement::" + webelement + "Done");
        waitUntilDomLoad();
        jse.executeScript("window.scrollBy(0,-100)", "");
        loggerBasePage.info("ScrollToElement::" + webelement + "Done");
    }

    public void waitByTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            loggerBasePage.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void buttonClick(By locator) {
        try {
            loggerBasePage.info("Before WaitForElement in buttonClick::" + locator);
            webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            loggerBasePage.info("Exception in buttonClick ::" + locator);
            loggerBasePage.error(e);
        }
        WebElement elm = waitForElement(locator);
        scrollToElement(elm);
        try {
            elm.click();
        } catch (Exception e) {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(elm).click().perform();
        }
        loggerBasePage.info("After buttonClick ::" + locator);
    }

    public void buttonClick(WebElement webElement) {
        try {
            loggerBasePage.info("BeforeWaitForElement in buttonClick::" + webElement);
            webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            loggerBasePage.info("Exception in buttonClick :" + webElement);
            loggerBasePage.error(e);
        }

        scrollToElement(webElement);
        try {
            webElement.click();
        } catch (Exception e) {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(webElement).click().perform();
        }
        loggerBasePage.info("After buttonClick::" + webElement);
    }

    public void buttonClickIfExist(By locator) {
        try {
            loggerBasePage.info("BeforeWaitForElement in buttonClick::" + locator);
            webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            loggerBasePage.info("Exception in buttonClick ::" + locator);
            loggerBasePage.error(e);
        }
        List<WebElement> elm = getDriver().findElements(locator);
        if (!elm.isEmpty()) {
            scrollToElement(elm.get(0));
            elm.get(0).click();
            loggerBasePage.info("After buttonClick::" + locator);
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            loggerBasePage.info("Before isElementPresent::" + locator);
            waitUntilDomLoad();
            getDriver().findElement(locator);
            loggerBasePage.info("After isElementPresent::" + locator);
            return true;

        } catch (Exception e) {
            loggerBasePage.info("Exception isElementPresent::" + locator);
            loggerBasePage.error(e);
            return false;
        }
    }

    public boolean isElementVisible(By locator) {
        try{
            waitUntilDomLoad();
            webDriverFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            return true;
        } catch (Exception e) {
            loggerBasePage.info("Exception isElementPresent::" + locator);
            loggerBasePage.error(e);
            return false;
        }
    }

    public void enterText(By locator, String text) {
        loggerBasePage.info("Before enterText::" + locator + ", with text::" + text);
        WebElement webElementEnter = waitForElement(locator);
        scrollToElement(webElementEnter);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value='" + text + "'", webElementEnter);
        loggerBasePage.info("After enterText::" + locator + ", with text::" + text);
    }

    public void clearAndEnterText(By locator, String text){
        clearField(locator);
        enterText(locator, text);
    }

    public void enterText(By locator, int text) {
        enterText(locator, Integer.toString(text));
    }

    public void waitUntilElementClickable(By locator) {
        loggerBasePage.info("Before waitUntilElementVisible::" + locator);
        int seconds = 180;
        waitUntilDomLoad();
        WebDriverWait expWait = new WebDriverWait(getDriver(), seconds);
        expWait.until(ExpectedConditions.elementToBeClickable(locator));
        loggerBasePage.info("After waitUntilElementVisible::" + locator);
    }

    public void selectOption(By locator, String opt) {
        try {
            loggerBasePage.info("Before selectOption::" + locator + ", with Select Option::" + opt);
            WebElement element = waitForElement(locator);
            webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
            Select select = new Select(element);
            select.selectByVisibleText(opt);
            waitUntilDomLoad();
            loggerBasePage.info("After selectOption::" + locator + ", with Select Option::" + opt);
        }catch(Exception e){
            loggerBasePage.error("Failed");
        }
    }

    public boolean verifyDefaultValueFromWebElement(By locator, String expectedText) {
        loggerBasePage.info("Before verifyValueFromEditBox::" + locator + MSG_WITH_TEXT + expectedText);
        WebElement webEditboxText = waitForElement(locator);
        String editBoxText = webEditboxText.getText();
        loggerBasePage.info("After verifyValueFromEditBox::" + locator + MSG_WITH_TEXT + expectedText);
        return editBoxText.contains(expectedText);
    }

    public String randomString(int len) {
        loggerBasePage.info("Before randomString with length::" + len);
        String ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ab.charAt(rnd.nextInt(ab.length())));
        }
        loggerBasePage.info("After randomString with length::" + len);
        return sb.toString();
    }

    public int randomNumber(int len) throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();

        // Generate random integers in range 0 to 999
        int m = (int) Math.pow(10, len);
        return rand.nextInt(m-1);
    }

    public void waitUntilDomLoad() {
        loggerBasePage.info("Inside waitUntilDomLoad");
        WebDriver driver = getDriver();
        FluentWait fluentWait = readyStateWait(driver);
        if(driver.getTitle().contains("/maintenix/")) {
            ExpectedCondition<Boolean> jQueryLoad;
            try {
                jQueryLoad = webDriver -> ((Long) ((JavascriptExecutor) driver)
                        .executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                jQueryLoad = webDriver -> (true);
                loggerBasePage.error(e);
            }
            fluentWait.until(jQueryLoad);
        }
        try {
            ExpectedCondition<Boolean> docLoad = webDriver -> ((Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete"));
            fluentWait.until(docLoad);
        }catch (Exception e){
            loggerBasePage.error(e);
        }
        loggerBasePage.info("Dom load completed");
    }

    public FluentWait<WebDriver> readyStateWait(WebDriver driver) {
        return new FluentWait(driver)
                .withTimeout(600, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
    }

    public boolean validateStringStartsWithPattern(String inputString, String regExPattern){
        Pattern pattern = Pattern.compile(regExPattern);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.lookingAt();
    }

    public FluentWait<WebElement> webDriverFluentWait(int sec) {
        return new FluentWait(getDriver())
                .withTimeout(sec, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, NoSuchFrameException.class);
    }

    public static List<String> getProperty(String fileLocation, String property) throws IOException {
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/testData/rolebasedtestdata/"+fileLocation+".properties"));
        } catch (FileNotFoundException e) {
            loggerBasePage.error(e);
        }
        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            loggerBasePage.error(e);
        }
        finally {
            fileInput.close();
        }
        String propertyData= (prop.getProperty(property));
        String[] properties=propertyData.split(",");

        return Arrays.asList(properties);
    }

    public void printConsole(String str){
        loggerBasePage.info("printConsole >> "+str);
    }

    public void clearField(By locator) {
        WebElement element = waitForElement(locator);
        try {
            element.click();
            element.clear();
        } catch (Exception e){
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element).click().perform();
            actions.moveToElement(element).sendKeys("");
        }
    }

    public String setFontColor(String strMessage, String color){
        return "<p style=\"color:" + color + "\">" + strMessage + "</p>";
    }

    public String boldIt(String strMessage){
        return "<b>" + strMessage + "</b>";
    }

    public void navigateToURL(String strURL){
        try {
            getDriver().navigate().to(strURL);
        } catch (Exception e) {
            loggerBasePage.error(e);
        }

    }

    public String getAttributeFromElement(By locator) {
        loggerBasePage.info("Before getAttributeFromElement::" + locator);
        if (getDriver().findElement(locator).isDisplayed()) {
            loggerBasePage.info("After getAttributeFromElement::" + locator);
            return waitForElement(locator).getAttribute("value");
        }
        else
            loggerBasePage.info("Returning Null Value in getAttributeFromElement::" + locator);
        return "NULL";
    }

    public String getWebElementText(By locator) {
        loggerBasePage.info("Before getWebElementText::" + locator);
        WebElement webElement = waitForElement(locator);
        loggerBasePage.info("After getWebElementText::" + locator);
        return webElement.getText();
    }

    public boolean verifyTextFromElement(By locator, String expectedText) {
        loggerBasePage.info("Before verifyTextFromElement::" + locator + ", with Text:: " + expectedText);
        WebElement webElementText = waitForElement(locator);
        String elementText = webElementText.getText();
        loggerBasePage.info("After verifyTextFromElement::" + locator + ", with Text:: " + expectedText);
        return elementText.contains(expectedText);
    }

    public String verifyIsDefaultTextEditBox(By locator) {
        loggerBasePage.info("Before verifyIsDefaultTextEditBox::"+locator);
        WebElement element = waitForElement(locator);
        String defaultText = element.getAttribute("value");
        loggerBasePage.info("After verifyIsDefaultTextEditBox::"+locator);
        return defaultText;
    }

    public Integer readFromTable(By locatorTable, String vSelect) {
        loggerBasePage.info("Before readFromTable from rows of Table::"+locatorTable+ ", contains with::"+vSelect);
        Integer reqRowValue = null;
        WebElement element = waitForElement(locatorTable);
        List<WebElement> tableRows = element.findElements(By.tagName("tr"));
        int countRows = tableRows.size();
        for (int j = 1; j < countRows; j++) {
            List<WebElement> columnsRow = tableRows.get(j).findElements(By.tagName("td"));
            String cellText = columnsRow.get(2).getText();
            if (cellText.contains((vSelect))) {
                reqRowValue = j + 1;
                break;
            }
        }
        loggerBasePage.info("After readFromTable from rows of Table::"+locatorTable+ ", contains with::"+vSelect);
        return reqRowValue;

    }

    public void clickKeyBoardButton(String strButton){
        try{
            Robot robot = new Robot();
            robot.delay(1000);
            if(strButton.equalsIgnoreCase("ENTER"))
                robot.keyPress(KeyEvent.VK_ENTER);
        }catch(Exception e){
            loggerBasePage.error(e);
        }
    }

    public String acceptAlert(){
        // Switching to Alert
        Alert alert = getDriver().switchTo().alert();

        // Capturing alert message.
        String alertMessage= getDriver().switchTo().alert().getText();

        waitByTime(1000);

        // Accepting alert
        alert.accept();

        return alertMessage;
    }


    public void selectRadioButton(By element, String value) {
        try{
            List<WebElement> list = getDriver().findElements(element);
            boolean blnSelected = false;
            for (WebElement ele: list) {
                if(ele.getAttribute("Value").toUpperCase().contains(value.toUpperCase())){
                    ele.click();
                    blnSelected = true;
                }
            }
            if (!blnSelected)
                throw new Exception(value + " Value is nor present in radio button");
        }catch(Exception e){
            loggerBasePage.error(e);
        }
    }

    public void restartDriver(){
        DriverSource driverSource = new DriverSource();
        driverSource.restartDriver();
    }


}
