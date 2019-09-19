package com.swacorp.crew.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {

    @DataProvider(name = "number")
    public static Object[][] serialNumber() {
        return new Object[][]{{"1"}, {"2"},{"3"}};
    }
}
