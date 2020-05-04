package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by x219949 on 8/14/2018.
 */
public class TestUtil {

    public static ConcurrentHashMap<Long, WebDriver> driverMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<Long, WindowsDriver> desktopDriverMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<Long, ExtentTest> extentTestMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, String> dynamicDataMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, List<String>> dynamicDataArrayMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, Boolean> dynamicBooleanDataMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, Double> dynamicDoubleDataMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, Integer> dynamicIntegerDataMap = new ConcurrentHashMap();
    public static Map<String, Map<String, ArrayList<String[]>>> rosaMasterHM = new LinkedHashMap<String, Map<String, ArrayList<String[]>>>();

    public static Map<String, Map<String, ArrayList<String[]>>> getRosaMasterHM() {
        return rosaMasterHM;
    }

    public static void setRosaMasterHM(Map<String, Map<String, ArrayList<String[]>>> rosaMasterHM) {
        TestUtil.rosaMasterHM = rosaMasterHM;
    }


    public void setDynamicData(String varName, String value) {
        Long id = Thread.currentThread().getId();
        TestUtil.dynamicDataMap.put(varName + "-" + id, value);
    }

    public String getDynamicData(String varName) {
        Long id = Thread.currentThread().getId();
        return TestUtil.dynamicDataMap.get(varName + "-" + id);
    }

}
