
package com.swacorp.crew.utils;

import com.microsoft.edge.seleniumtools.EdgeDriver;
import com.microsoft.edge.seleniumtools.EdgeOptions;
import com.swacorp.crew.pages.constants.AsapConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by x219949 on 8/14/2018.
 */
public class DriverSource {

    private static final int SET_IMPLICIT_TIMEOUT_MS = 500;
    private static final int SET_SCRIPT_TIMEOUT_SS = 60;
    private static final int SET_PAGE_TIMEOUT_SS = 600;
    public static final Logger LOGGER = Logger.getLogger(DriverSource.class);
    private final String userDir = System.getProperty("user.dir");

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
                    String downloadFilepath = AsapConstants.DOWNLOAD_PATH;
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 1);
                    prefs.put("profile.default_content_setting_values.automatic_downloads", "1.0");
                    prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 4 );
                    prefs.put("download.default_directory", downloadFilepath);
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--test-type");
                    options.addArguments("-incognito");
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");
                    options.addArguments("--disable-notifications");
                    options.addArguments("disable-popup-blocking");
                    options.addArguments("--allow-file-access-from-files");

                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
                    capabilities.setCapability("chrome.binary", userDir + "\\src\\drivers\\chromedriver.exe");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new ChromeDriver(capabilities);
                } else if(browser.equalsIgnoreCase("edge")){
                    System.setProperty("webdriver.edge.driver",  userDir+ "\\src\\main\\resources\\drivers\\msedgedriver.exe");

                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory", AsapConstants.DOWNLOAD_PATH);

                    EdgeOptions op = new EdgeOptions();
                    op.addArguments("-inprivate");
                    op.setPageLoadStrategy(PageLoadStrategy.fromString("eager"));
                    op.setExperimentalOption("prefs", prefs);
                    op.setAcceptInsecureCerts(true);

                    driver = new EdgeDriver(op);
                    driver.manage().window().maximize();

                } else {
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

}
