package com.wap.uiautomation.crew;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import com.swacorp.crew.pages.trim.scheduledplanner.ScheduledPlanner;
import com.swacorp.crew.utils.EnvironmentConstants;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.windows.WindowsDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;


public class WinAppDriverDemo extends TestManager {
    private final Logger LOGGER = Logger.getLogger(WinAppDriverDemo.class);
    ReportUtil report = new ReportUtil();
    public String oqsUrl;
    public WindowsDriver trimSession;

//    public WinAppDriverDemo()throws MalformedURLException, InterruptedException{
//
//       trimSession.findElementByName("Password:").sendKeys("admin123");
//    }

    public WinAppDriverDemo() throws Exception {
        if (trimSession == null){
            trimSession = getDriver();
        }
    }

    public void demoTest() throws MalformedURLException, InterruptedException{

        // Login to Application
        LoginToTrim(trimSession);
        //MouseHover(trimSession);
        SelectEmployee(trimSession);
        SelectClassroom(trimSession);
        NavigateTemplete(trimSession);
    }


    private void MouseHover(WindowsDriver trimSession) throws InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("collapse.....");
        Thread.sleep(5000);
        WebElement ele = trimSession.findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"));
        trimSession.getMouse().mouseMove(null, 1,5);
    }



    private  void NavigateTemplete(WindowsDriver trimSession) throws InterruptedException {
        // TODO Auto-generated method stub
        Actions actions = new Actions(trimSession);
        trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1")).findElement(By.name("Classroom")).click();
        trimSession.getKeyboard().sendKeys(Keys.TAB);
        trimSession.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
        trimSession.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
        trimSession.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
//		trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1")).findElement(By.name("Coopers")).click();
        trimSession.findElement(By.name("SideNav"))
                .findElement(By.name("Sched Planner"))
                .findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"))
                .findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"))
                .findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"))
                .findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1"))
                .findElement(By.name("737"))
                .click();

        WebElement x = trimSession.findElement(By.name("SideNav"))
                .findElement(By.name("Sched Planner"))
                .findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"))
                .findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"))
                .findElement(By.className("WindowsForms10.Window.8.app.0.13965fa_r10_ad1"))
                .findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1"))
                .findElement(By.name("737"));

        System.out.println("Expand 737 tree >>>>>");
        Thread.sleep(4000);
        actions.doubleClick(x).perform();
        trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1")).findElement(By.name("737")).findElement(By.name("737")).click();

        trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1")).findElement(By.name("737")).findElement(By.name("737")).findElement(By.name("Coopers")).click();


        //Actions actions = new Actions(trimSession);

        WebElement s = trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner"))
                .findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1"))
                .findElement(By.name("737")).findElement(By.name("737")).findElement(By.name("Coopers"));

        List<WebElement> t = trimSession.findElement(By.className("WindowsForms10.MDICLIENT.app.0.13965fa_r10_ad1"))
                .findElement(By.name("03-Classroom - Nov 2019"))
                .findElement(By.name("DataGrid")).findElements(By.name("4"));

        actions.dragAndDrop(s, t.get(1)).perform();

        Thread.sleep(20000);
        actions.doubleClick(t.get(1));

        //t.get(1)
        Actions actions1 = new Actions(trimSession);
        actions1.contextClick(t.get(1)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

    }

    private  void SelectClassroom(WindowsDriver trimSession) {
        trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1")).findElement(By.name("Classroom")).click();
        trimSession.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElement(By.className("WindowsForms10.SysTreeView32.app.0.13965fa_r10_ad1")).findElement(By.name("Classroom")).findElement(By.name("03-Classroom")).click();
        trimSession.getKeyboard().sendKeys(Keys.RETURN);

    }

    private  void SelectEmployee(WindowsDriver trimSession) {
        WebDriver mainWindow;
        Set<String> handles = trimSession.getWindowHandles();
        System.out.println("handles.size()"+handles.size()+","+handles.toString());
        for (String s:handles) {
            //System.out.println(s);

            System.out.println(trimSession.getTitle());
            mainWindow = trimSession.switchTo().window(s);
            mainWindow.findElement(By.name("topNav")).findElement(By.name("&Main Menu")).findElement(By.name("Employees")).click();

            //mainWindow.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).click();//findElement(By.name("")).findElement(By.name("")).findElement(By.name("Classroom")).findElement(By.name("04-Classroom")).click();
            //List<WebElement> pans = mainWindow.findElement(By.name("SideNav")).findElement(By.name("Sched Planner")).findElements(By.name(""));

            mainWindow.findElement(By.name("topNav")).findElement(By.name("&Main Menu")).findElement(By.name("Employees")).click();
            WebElement ContextMenu = mainWindow.findElement(By.name("topNav")).findElement(By.name("&Main Menu")).findElement(By.name("Employees"));
            Actions actions = new Actions(trimSession);
            actions.click(ContextMenu).build().perform();
        }




    }

    private  void LoginToTrim(WindowsDriver trimSession) throws InterruptedException, MalformedURLException {

        trimSession.findElementByName("Password:").sendKeys("admin123");
        System.out.println(trimSession.getTitle()+","+trimSession.getWindowHandle());
        trimSession.findElementByName("Login").click();
        Thread.sleep(5000);
        System.out.println("LoginToTrim successful..");
        //trimSession.close();
    }

    private  void SelectClassroom() throws InterruptedException, MalformedURLException {

//		  DesiredCapabilities capabilities = new DesiredCapabilities();
//		  capabilities.setCapability("app", "mainWindow");
//		  WindowsDriver trimSession =  new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);


//		driver.switchTo().window("mainWindow");
//		driver.findElementByName("topNav ").click();
//		driver.findElementByName("&Main Menu").click();
//		driver.findElementByName("Employees").click();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("AutomationId", "frmMain");
        WindowsDriver trimSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        trimSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        System.out.println(trimSession.getTitle()+","+trimSession.getWindowHandle());
    }
}
