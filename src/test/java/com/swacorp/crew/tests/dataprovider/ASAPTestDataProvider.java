package com.swacorp.crew.tests.dataprovider;


import com.swacorp.crew.pages.constants.AsapConstants;
import org.testng.annotations.DataProvider;

public class ASAPTestDataProvider {

    @DataProvider(name = "FO_ASAP_REPORT")
    public static Object[][] FO_ASAP_REPORT() {
        return new Object[][]{
                {
                        "Line Pilot", AsapConstants.DAL_DOM_STATION,
                        "7278186537", "anup.pandit@wnco.com",
                        "", AsapConstants.DAL_DOM_STATION, AsapConstants.HOU_DOM_STATION, AsapConstants.ATL_DOM_STATION, "Seven-Hundred", "", "",
                        "0600", AsapConstants.CST_TIME_ZONE, "Daylight", "VMC", "No", "No",
                        "CA", "100", "30", "20", "Pilot Flying", "First leg ever flown",
                        "Taxi out", "TEX1234", "21.616579,86.055270",
                        "This is a event type", "Weather", "Hail",
                        "Adverse weather", "Thunderstorm",
                        "Documentation errors", "Management of HAZMAT information",
                        "This is a Event created for testing", "These are the preventive measures taken"
                }
        };
    }

    @DataProvider(name = "IF_ASAP_REPORT")
    public static Object[][] IF_ASAP_REPORT() {
        return new Object[][]{
                {
                        "\"A\" Flight Attendant", "",
                        "7278186537", "anup.pandit@wnco.com",
                        "", AsapConstants.DAL_DOM_STATION, AsapConstants.HOU_DOM_STATION, AsapConstants.ATL_DOM_STATION, "Seven-Hundred", "", "",
                        "0600", "", "NIGHT", "", "No", "No",
                        "CA", "100", "30", "20", "Pilot Flying", "First leg ever flown",
                        "Taxi out", "", "",
                        "This is a event type", "Cabin Equipment", "Emergency Equipment",
                        "Cabin Event", "Decompression",
                        "Documentation errors", "Management of HAZMAT information",
                        "This is a Event created for testing", "These are the preventive measures taken"
                }
        };
    }

    @DataProvider(name = "MX_ASAP_REPORT")
    public static Object[][] MX_ASAP_REPORT() {
        return new Object[][]{
                {
                        "West Texas, Dallas", "7234567890", "2345678901", "3456789012", "anup.pandit@wnco.com",
                        AsapConstants.DAL_DOM_STATION, "Day", "Contract", "AMT", "Manager", "Sunday",
                        "No", "03/11/2021", "1200", "No", "6", "Day", "Line Service", AsapConstants.DAL_DOM_STATION, "No", "Safety Concern", "No",
                        "This is a event type", "AIRCRAFT DAMAGE", "Vehicle Damage",
                        "ACO", "Document Details", "No", "No", "List of Qualification", "N/A", "Gate Return",
                        "This is a Event created for testing", "These are the preventive measures taken"
                }
        };
    }


    @DataProvider(name = "DP_ASAP_REPORT")
    public static Object[][] DP_ASAP_REPORT() {
        return new Object[][]{
                {

                        "214.555.1211", "Test@QMO.com", "QMO Address",
                        "", AsapConstants.DAL_DOM_STATION, AsapConstants.CUN_INT_STATION, AsapConstants.ATL_DOM_STATION, "800", "", "",
                        "10:00", AsapConstants.EST_TIME_ZONE, "Dusk", "VMC", "No",
                        "SOD", "10", "10",
                        "Taxi out", "QMO", "QmoAutomation",
                        "sum", "Flight Planning/Release", "Missing Amendment",
                        "Adverse weather", "Icing",
                        "Flight Planning", "Weather",
                        "Test", "QmoTest"
                }
        };
    }
}