package com.swacorp.crew.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {

    @DataProvider(name = "TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM")
    public static Object[][] TC181509_CREW_14499_TC06OQS_RKTRiMCAUpgradeMultipleFOsToCAsInOQSRKAndVerifyInTRiM() {

        return new Object[][]{
                {"SIM. INSTRUCTOR - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }
        };
    }

    @DataProvider(name = "TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_A_FAA_To_OQS_RK_And_Verify_In_TRiM")
    public static Object[][] TC181551_CREW_14499_TC08_OQS_RK_TRiM_FAA_Add_A_FAA_To_OQS_RK_And_Verify_In_TRiM() {

        return new Object[][]{
                {"FAA - All All","22", "2", "TRN","FName", "SName", "07/01/1987", "MALE", "Y", "ATP", "99988822", "05/01/2018", "BOEING 737 PIC" }
              // {"SIM. INSTRUCTOR - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" },
               //{"AIRCREW PGM MGR - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }
        };
    }
}