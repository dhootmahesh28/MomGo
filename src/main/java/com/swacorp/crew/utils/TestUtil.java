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

    public static final ConcurrentHashMap<Long, WebDriver> driverMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<Long, ExtentTest> extentTestMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, String> dynamicDataMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, List<String>> dynamicDataArrayMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, Boolean> dynamicBooleanDataMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, Double> dynamicDoubleDataMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, Integer> dynamicIntegerDataMap = new ConcurrentHashMap();
    public static final ConcurrentHashMap<String, String> short2LongMonthMap = new ConcurrentHashMap();
    static {
        short2LongMonthMap.put("Jan", "Janurary");
        short2LongMonthMap.put("Feb", "February");
        short2LongMonthMap.put("Mar", "March");
        short2LongMonthMap.put("Apr", "April");
        short2LongMonthMap.put("May", "May");
        short2LongMonthMap.put("Jun", "June");
        short2LongMonthMap.put("Jul", "July");
        short2LongMonthMap.put("Aug", "August");
        short2LongMonthMap.put("Sep", "September");
        short2LongMonthMap.put("Oct", "October");
        short2LongMonthMap.put("Nov", "November");
        short2LongMonthMap.put("Dec", "December");
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
