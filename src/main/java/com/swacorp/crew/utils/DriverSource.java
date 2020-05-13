package com.swacorp.crew.utils;

import com.hp.lft.unittesting.TestNgUnitTestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.swacorp.crew.genericwrappers.editor.IWinformEditor;
import com.swacorp.crew.genericwrappers.editor.IWinformButton;
import com.swacorp.crew.pages.common.LeanftGenericMethods;
/**
 * Created by x219949 on 8/14/2018.
 */
public class DriverSource extends TestNgUnitTestBase implements LeanftGenericMethods {

    private static final int SET_IMPLICIT_TIMEOUT_MS = 500;
    private static final int SET_SCRIPT_TIMEOUT_SS = 60;
    private static final int SET_PAGE_TIMEOUT_SS = 600;
    public static final Logger LOGGER = Logger.getLogger(DriverSource.class);
    private final String userDir = System.getProperty("user.dir");

    protected IWinformEditor edt = null;
    protected IWinformButton btn = null;

    @BeforeMethod(alwaysRun = true)
    public void initTest(){
        newDriver();
    }

    public void newDriver() {
        WebDriver driver = null;
        int retryCounter = 0;
        int maxRetryCount = 5;
        String browser = System.getProperty("browser");
        do {
            try {
                if(browser.equalsIgnoreCase("chrome")){
                    System.setProperty("webdriver.chrome.driver",  userDir+ "\\src\\main\\resources\\drivers\\chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--test-type");
                    options.addArguments("-incognito");
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");

                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
                    capabilities.setCapability("chrome.binary", userDir + "\\src\\drivers\\chromedriver.exe");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new ChromeDriver(capabilities);
                }else {
                    System.setProperty("webdriver.ie.driver", userDir + "\\src\\main\\resources\\drivers\\IEDriverServer64.exe");
                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                    internetExplorerOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
                    internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    internetExplorerOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    internetExplorerOptions.setCapability("nativeEvents", false);
                    driver = new InternetExplorerDriver(internetExplorerOptions);
                    driver.manage().window().maximize();
                }
                driver.manage().timeouts().implicitlyWait(SET_IMPLICIT_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                driver.manage().timeouts().setScriptTimeout(SET_SCRIPT_TIMEOUT_SS, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(SET_PAGE_TIMEOUT_SS, TimeUnit.SECONDS);
                driver.manage().deleteAllCookies();
                Long threadId = Thread.currentThread().getId();
                TestUtil.driverMap.put(threadId, driver);
                break;
            } catch(WebDriverException e) {
                LOGGER.error("WebDriver Exception"+ e);
                if (driver != null)
                    driver.quit();
                if (++retryCounter > maxRetryCount) {
                    Assert.assertTrue(false, "Exception while creating driver instance : " + e.getMessage());
                    break;
                }
            }
        } while (true);
    }

    public void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void restartDriver() {
        WebDriver driver = getDriver();
        driver.close();
        driver.quit();
        newDriver();
    }

    public WebDriver getDriver() {
        Long key = Thread.currentThread().getId();
        return TestUtil.driverMap.get(key);
    }

    @Override
    protected String getClassName() {
        return "Driver source";
    }

    @Override
    protected String getTestName() {
        return "LEAN FT";
    }
}
