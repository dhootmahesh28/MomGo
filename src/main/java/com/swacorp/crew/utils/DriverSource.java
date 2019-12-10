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

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
/**
 * Created by x219949 on 8/14/2018.
 */
//public class DriverSource extends TestNgUnitTestBase {
public class DriverSource{
    private final int SET_IMPLICIT_TIMEOUT_MS = 500;
    private final int SET_SCRIPT_TIMEOUT_SS = 60;
    private final int SET_PAGE_TIMEOUT_SS = 600;
    public final static Logger LOGGER = Logger.getLogger(DriverSource.class);
    public static WindowsDriver trimSession;
    DriverSource driversource =new DriverSource();

    @BeforeMethod(alwaysRun = true)
    public void newDriver() throws MalformedURLException, Exception {
        LOGGER.info("Before method invoked: newDriver");
        WebDriver driver = null;

        DesiredCapabilities capabilities = null;
        int retryCounter = 0;
        int maxRetryCount = 5;
        String browser = System.getProperty("browser");
        Long threadId = Thread.currentThread().getId();

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\North Orca\\TRiM\\TRiM.exe");
        trimSession= new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);

       // trimSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
/*        trimSession.findElementByClassName("WindowsForms10.EDIT.app.0.13965fa_r10_ad1").sendKeys("admin123");

        Thread.sleep(5000);*/

        /*
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
                    capabilities = new DesiredCapabilities();
                    capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
                    capabilities.setCapability("chrome.binary", System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe");
                    capabilities.setCapability("screen-resolution","1280x1024");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new ChromeDriver(capabilities);
                }else if(browser.equalsIgnoreCase("DesktopApp")){
                    //WindowsDriver trimSession;
                    capabilities = new DesiredCapabilities();
                    capabilities.setCapability("app", "C:\\Program Files (x86)\\North Orca\\TRiM\\TRiM.exe");
                    trimSession= new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
                    trimSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    trimSession.findElementByName("Password:").sendKeys("admin123");
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
                if (driver  != null) {
                    driver.manage().timeouts().implicitlyWait(SET_IMPLICIT_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                    driver.manage().timeouts().setScriptTimeout(SET_SCRIPT_TIMEOUT_SS, TimeUnit.SECONDS);
                    driver.manage().timeouts().pageLoadTimeout(SET_PAGE_TIMEOUT_SS, TimeUnit.SECONDS);
                    driver.manage().deleteAllCookies();
                    LOGGER.info("Ready to navigate.....");
                    driver.get("https://www.google.com");
                    TestUtil.driverMap.put(threadId, driver);
                } else {
//                    TestUtil.driverMap.put(threadId, trimSession);
                    //TestUtil.desktopDriverMap.put(threadId, trimSession);
                    //tSession = trimSession;
                }
                //break;
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
    */

        //WindowsDriver trimSession;

    }

    public void quitDriver_WD() throws Exception {
        WebDriver driver = getDriver();
        if (driver != null) {
           driver.close();
           driver.quit();
        }
    }

    public void quitDriver() throws Exception{
        WindowsDriver driver = getDriver();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void restartDriver() throws MalformedURLException, Exception {
        WindowsDriver driver = getDriver();
        driver.close();
        driver.quit();
        newDriver();
    }

/*    public WebDriver getDriver() {
        Long key = Thread.currentThread().getId();
        return TestUtil.driverMap.get(key);
        //return null;
    }*/

    public WindowsDriver getDriver() throws Exception {
        if (trimSession == null){
            driversource.restartDriver();
        }
        return trimSession;
        //return null;
    }

    /*
    @Override
    protected String getClassName() {
        return "Driver source";
    }

    @Override
    protected String getTestName() {
        return "LEAN FT";
    }

    */
}
