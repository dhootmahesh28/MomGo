package com.swacorp.crew.pages.common;

import com.aventstack.extentreports.ExtentTest;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by x17212 on 5/2/2016.
 */
public class BasePage extends  TestUtil {

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

        WebElement element;
        try {
            loggerBasePage.info("BeforeWaitForElement::" + byElement);
            waitUntilDomLoad();
            webDriverFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            element = getDriver().findElement(byElement);
        } catch (WebDriverException e) {
            loggerBasePage.info("Exception in waitForElement::" + byElement);
            throw new WebDriverException(e.getMessage());
        }
        loggerBasePage.info("AfterWaitForElement::" + byElement);
        return element;
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
            loggerBasePage.info("BeforeWaitForElement in buttonClick::" + locator);
            webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            loggerBasePage.info("Exception in buttonClick ::" + locator);
            loggerBasePage.error(e.getMessage());
        }
        WebElement elm = waitForElement(locator);
        scrollToElement(elm);
        elm.click();
        loggerBasePage.info("After buttonClick::" + locator);
    }

    public void buttonClickIfExist(By locator) {
        try {
            loggerBasePage.info("BeforeWaitForElement in buttonClick::" + locator);
            webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            loggerBasePage.info("Exception in buttonClick ::" + locator);
            loggerBasePage.error(e.getMessage());
        }
        List<WebElement> elm = getDriver().findElements(locator);
        if (elm.size() > 0) {
            scrollToElement(elm.get(0));
            elm.get(0).click();
            loggerBasePage.info("After buttonClick::" + locator);
        }
    }

    public boolean isElementPresent(By locator) {
        WebElement element;
        try {
            loggerBasePage.info("Before isElementPresent::" + locator);
            waitUntilDomLoad();
            getDriver().findElement(locator);
            loggerBasePage.info("After isElementPresent::" + locator);
            return true;

        } catch (Exception e) {
            loggerBasePage.info("Exception isElementPresent::" + locator);
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
            return false;
        }
    }

    public void enterText(By locator, String text) {
        loggerBasePage.info("Before enterText::" + locator + ", with text::" + text);
        WebElement webElementEnter = waitForElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value='" + text + "'", webElementEnter);
        loggerBasePage.info("After enterText::" + locator + ", with text::" + text);
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
        loggerBasePage.info("Before selectOption::" + locator + ", with Select Option::" + opt);
        WebElement element = waitForElement(locator);
        webDriverFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(element);
        select.selectByVisibleText(opt);
        waitUntilDomLoad();
        loggerBasePage.info("After selectOption::" + locator + ", with Select Option::" + opt);
    }

    public boolean verifyValueFromEditbox(By locator, String expectedText) {
        loggerBasePage.info("Before verifyValueFromEditbox::" + locator + MSG_WITH_TEXT + expectedText);
        WebElement webEditboxText = waitForElement(locator);
        String editBoxText = webEditboxText.getAttribute("value");
        loggerBasePage.info("After verifyValueFromEditbox::" + locator + MSG_WITH_TEXT + expectedText);
        return editBoxText.contains(expectedText);
    }

    public boolean verifyDefaultValueFromWebElement(By locator, String expectedText) {
        loggerBasePage.info("Before verifyValueFromEditbox::" + locator + MSG_WITH_TEXT + expectedText);
        WebElement webEditboxText = waitForElement(locator);
        String editBoxText = webEditboxText.getText();
        loggerBasePage.info("After verifyValueFromEditbox::" + locator + MSG_WITH_TEXT + expectedText);
        return editBoxText.contains(expectedText);
    }

    public String randomString(int len) {
        loggerBasePage.info("Before randomString with length::" + len);
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        loggerBasePage.info("After randomString with length::" + len);
        return sb.toString();
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
            }
            fluentWait.until(jQueryLoad);
        }
        try {
            ExpectedCondition<Boolean> docLoad = webDriver -> ((Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete"));
            fluentWait.until(docLoad);
        }catch (Exception e){
            e.printStackTrace();
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


    public FluentWait<WebDriver> webDriverFluentWait(int sec) {
        return new FluentWait(getDriver())
                .withTimeout(sec, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, NoSuchFrameException.class);
    }

    public static ArrayList<String> getProperty(String fileLocation, String property){
        ArrayList<String> obj=new ArrayList<String>();
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/testData/rolebasedtestdata/"+fileLocation+".properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String propertyData= (prop.getProperty(property));
        String properties[]=propertyData.split(",");
        for(String i:properties){
            obj.add(i);
        }
        return obj;
    }


    public void printConsole(String str){
        loggerBasePage.info("printConsole >> "+str);
    }
}
