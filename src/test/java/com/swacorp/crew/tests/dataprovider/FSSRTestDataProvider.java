package com.swacorp.crew.tests.dataprovider;

import com.swacorp.crew.pages.constants.FssrConstants;
import org.testng.annotations.DataProvider;

public class FSSRTestDataProvider {

    @DataProvider(name = "TC12345")
    public static Object[][] TC12345() {
        return new Object[][]{
                {
                        FssrConstants.SOAR_MENU,
                        FssrConstants.GROUND_OPERATION, FssrConstants.HAZMAT_REPORT,
                        "", "", FssrConstants.CHECKED_BAGGAGE, "DAL", "PNR1234", "BG1234", "",
                        "Walter", "White", "Domestic", "Ghosh Para", "Dallas", "Texas", "743128", "USA", "7278186537",
                        "", "FLL", "PHX", "Hazardous Material Info",
                        FssrConstants.EMPLOYEE, FssrConstants.HAZMAT_BATTERIES, "Batteries", "EXIDE", "BIG", "2",
                        FssrConstants.CASE_MENU,
                        FssrConstants.HAZMAT_CASE_FILTER
                }
        };
    }

    @DataProvider(name = "TC12346")
    public static Object[][] TC12346() {
        return new Object[][]{
                {
                        FssrConstants.HOME_MENU,
                        FssrConstants.GROUND_OPERATION, FssrConstants.HAZMAT_REPORT,
                        "", "", FssrConstants.CHECKED_BAGGAGE, "DAL",
                        "PNR1234", "Adair Shane", "Domestic", "Dallas- USA", "1234567890", "123", "DAL", "HOU", FssrConstants.FLT_CODE, "12345",
                        FssrConstants.EMPLOYEE, "Hazardous Material",
                        "Alcohol", "Alcohol", "Kingfisher", "BIG",
                        FssrConstants.CASE_MENU,
                        FssrConstants.HAZMAT_CASE_FILTER
                }
        };
    }

    @DataProvider(name = "TC12347")
    public static Object[][] TC12347() {
        return new Object[][]{
                {
                        FssrConstants.SOAR_MENU,
                        FssrConstants.GROUND_OPERATION, FssrConstants.HAZMAT_REPORT,
                        "", "", FssrConstants.CHECKED_BAGGAGE, "ABC"
                }
        };
    }
}