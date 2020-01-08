package com.swacorp.crew.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {

    @DataProvider(name = "TC181510_CREW_14499_TC07")
    public static Object[][] TC181510_CREW_14499_TC07() {
        return new Object[][]{{"SIM. INSTRUCTOR - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
    }

    @DataProvider(name = "TC181551_CREW_14499_TC08")
    public static Object[][] TC181551_CREW_14499_TC08() {
        return new Object[][]{{"FAA - All All","22", "2", "TRN","FName", "SName", "07/01/1987", "MALE", "Y", "ATP", "99988822", "05/01/2018", "BOEING 737 PIC" }};
    }

    @DataProvider(name = "TC181552_CREW_14499_TC09")
    public static Object[][] TC181552_CREW_14499_TC09() {
        return new Object[][]{{"AIRCREW PGM MGR - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
    }


}