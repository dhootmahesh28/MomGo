package com.swacorp.crew.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {

    @DataProvider(name = "TC03_Crew_14499_OQS_RK_TRiM_CA_Upgrade_FO_to_CA")
    public static Object[][] serialNumber() {

        return new Object[][]{
                {"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }
        };
    }
}
