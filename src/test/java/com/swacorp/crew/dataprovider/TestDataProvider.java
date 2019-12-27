package com.swacorp.crew.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {

    @DataProvider(name = "TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM")
    public static Object[][] TC07_CREW_14499_OQS_RK_TRiM_Sim_Instructor_Add_Sim_Instructor_to_OQS_RK_and_verify_in_TRiM() {

        return new Object[][]{
                {"SIM. INSTRUCTOR - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }
        };
    }

    @DataProvider(name = "TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM")
    public static Object[][] TC08_CREW_14499_OQS_RK_TRiM_FAA_Add_FAA_to_OQS_RK_And_verify_In_TRiM() {

        return new Object[][]{
                {"FAA - All All","22", "2", "TRN","FName", "SName", "07/01/1987", "MALE", "Y", "ATP", "99988822", "05/01/2018", "BOEING 737 PIC" }
        };
    }
}
//FAA - All All  --not visible
//FIRST OFFICER - 737 All
// "SIM. INSTRUCTOR - 737 All" visible