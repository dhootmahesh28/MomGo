package com.swacorp.crew.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {


//    addCrewMember( empPosition,  classNumber,  crewNumber,  baseLocation, empNum,  lastName,  firstName,  dateOfBirth,
                    // gender,  usCitizenFlag,  type, certificate,  dateIssued,  rating)
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

    @DataProvider(name = "TC181553")
    public static Object[][] TC181553() {
        return new Object[][]{{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "01/05/2020", "BOEING 737 PIC", "Updated message.", "Add message.", "FIRST OFFICER-717 ALL" }};
    }

    @DataProvider(name = "TC181554")
    public static Object[][] TC181554() {//FIRST OFFICER-717 ALL
        return new Object[][]{{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "01/05/2020", "BOEING 737 PIC", "Updated message.", "Add message.", "CAPTAIN-737 All" }};
    }

    @DataProvider(name = "TC181359")
    public static Object[][] TC181359() {
        return new Object[][]{{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
    }

    @DataProvider(name = "TC181391")
    public static Object[][] TC181391() {
        return new Object[][]{{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
    }

    @DataProvider(name = "TC181508")
    public static Object[][] TC181508() {
        return new Object[][]{{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" },
                {"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" },
                {"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
    }

    @DataProvider(name = "TrimRequirementsTest")
    public static Object[][] TrimRequirementsTest() {
        return new Object[][]{{
                "Automation",//0
                "Recurrent",
                "Southwest",
                "description automation",
                "737",
                "24",
                "07/01/1988", //5
                "MALE",
                "Y",
                "ATP",
                "99988811",
                "05/01/2018",//10
                "BOEING 737 PIC"
        }};
    }

}