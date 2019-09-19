package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by x219949 on 8/14/2018.
 */
public class TestUtil {

    public static ConcurrentHashMap<Long, WebDriver> driverMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<Long, ExtentTest> extentTestMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, String> dynamicDataMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, List<String>> dynamicDataArrayMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, Boolean> dynamicBooleanDataMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, Double> dynamicDoubleDataMap = new ConcurrentHashMap();
    public static ConcurrentHashMap<String, Integer> dynamicIntegerDataMap = new ConcurrentHashMap();

}