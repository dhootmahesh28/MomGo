package com.swacorp.crew.tests.dataprovider;

import org.testng.annotations.DataProvider;

public class RosaTestDataProvider {

    @DataProvider(name = "TC166198")
    public static Object[][] TC166198() {
        return new Object[][]{{"Committed","ON", "OQS 2020 CQT"}};
    }

    @DataProvider(name = "TC167609")
    public static Object[][] TC167609() {
        return new Object[][]{{"Committed", "TRNGCQT", "ING", "Auto-TR TRNGCQT ING Assign Crew Member To Position FO", "Auto-TR TRNGCQT ING Assign Crew Member To Position", "Crew Member Id: "}};
    }

    @DataProvider(name = "TC167617")
    public static Object[][] TC167617() {
        return new Object[][]{{"Committed", "Transactions", "ING", "Auto-TR TRNGCQT ING Assign Crew Member To Position FO", "Auto-TR TRNGCQT ING Assign Crew Member To Position", "Crew Member Id: "}};
    }

    @DataProvider(name = "TC167612")
    public static Object[][] TC167612() {
        return new Object[][]{{"Committed", "Transactions", "ING", "Auto-TR TRNGCQT ING Assign Crew Member To Position FO", "Auto-TR TRNGCQT ING Assign Crew Member To Position", "Crew Member Id: "}};
    }

    @DataProvider(name = "TC167614")
    public static Object[][] TC167614() {
        return new Object[][]{{"Committed", "Transactions", "ING", "Auto-TR TRNGCQT ING Assign Crew Member To Position FO", "Auto-TR TRNGCQT ING Assign Crew Member To Position", "Crew Member Id: "}};
    }
}
