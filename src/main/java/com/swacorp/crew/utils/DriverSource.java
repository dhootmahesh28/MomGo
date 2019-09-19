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

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by x219949 on 8/14/2018.
 */
public class DriverSource extends TestNgUnitTestBase {

    private final int SET_IMPLICIT_TIMEOUT_MS = 500;
    private final int SET_SCRIPT_TIMEOUT_SS = 60;
    private final int SET_PAGE_TIMEOUT_SS = 600;
    public final static Logger LOGGER = Logger.getLogger(DriverSource.class);

    @BeforeMethod(alwaysRun = true)
    public void newDriver() {
        WebDriver driver = null;
        int retryCounter = 0;
        int maxRetryCount = 5;
        String browser = System.getProperty("browser");
        do {
            try {
                if(browser.equalsIgnoreCase("chrome")){
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--start-fullscreen");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--test-type");
                    //options.addArguments("-incognito");
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
                    capabilities.setCapability("chrome.binary", System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe");
                    capabilities.setCapability("screen-resolution","1280x1024");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new ChromeDriver(capabilities);
                }else {
                  //  if (System.getProperty("os.name").contains("Windows 7"))
                        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\IEDriverServer.exe");
                  //  else
                    //    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\IEDriverServer64.exe");
                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                    internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    internetExplorerOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    internetExplorerOptions.setCapability("requireWindowFocus", true);
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
            } catch (WebDriverException e) {
                LOGGER.error("Webdriver exception : " + e.getMessage());
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
        //return null;
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