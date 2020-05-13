package com.swacorp.crew.tests.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by x219949 on 8/15/2018.
 */
public class TestDataProvider {


    @DataProvider(name = "TC181465")
    public static Object[][] TC181465() {
        return new Object[][]{{
                "FIRST OFFICER - 737 All"
                ,"23"
                , "1"
                , "TRN"
                ,"Larry"
                , "Larry" //5 LastName
                , "07/01/1988"
                , "MALE"
                , "Y"
                , "ATP"
                , "99988811" //10
                , "01/05/2020"
                , "BOEING 737 PIC"
                , "Updated message."
                , "Add message."
                , "FIRST OFFICER-717 ALL" //15
                , "737 :_Placeholder - Initial_AUTOMATION2" //16. Select in Trim sched Planner dropdown
                , "Upgrade" //17. OQS Training event Type
                , "FLIGHT TRAINING" //18. Event in OQS
                , "First Officer" //19. PartialNode in Trim schedule Planing TreeView
                , "737 :_Placeholder - CQT " //20. select this to refresh the tree
                , "737" //21 Equipment
                , "737 - First Officer"}
        };
    }

    @DataProvider(name = "TC181506")
    public static Object[][] TC181506() {
        return new Object[][]{
                {
                        "FIRST OFFICER - 737 All"
                        ,"25"
                        , "1"
                        , "TRN"
                        ,"Larry"
                        , "Larry" //5 LastName
                        , "07/01/1988"
                        , "MALE"
                        , "Y"
                        , "ATP"
                        , "99988811" //10
                        , "01/05/2020"
                        , "BOEING 737 PIC"
                        , "Updated message."
                        , "Add message."
                        , "FIRST OFFICER-717 ALL" //15
                        , "737 :_Placeholder - Initial_AUTOMATION2" //16. Select in Trim sched Planner dropdown
                        , "Recurrent" //17. OQS Training event Type
                        , "MANEUVERS OBSERVATION" //18. Event in OQS
                        , "First Officer" //19. PartialNode in Trim schedule Planing TreeView
                        , "737 :_Placeholder - CQT" //20. select this to refresh the tree  737 :_Placeholder - CQT
                        , "737" //21 Equipment
                        , "737 - First Officer"}
        };
    }
    @DataProvider(name = "TC181390")
    public static Object[][] TC181390() {
        return new Object[][]{
                {
                        "FIRST OFFICER - 737 All"
                        ,"25"
                        , "1"
                        , "TRN"
                        ,"Larry"
                        , "Larry" //5 LastName
                        , "07/01/1988"
                        , "MALE"
                        , "Y"
                        , "ATP"
                        , "99988811" //10
                        , "01/05/2020"
                        , "BOEING 737 PIC"
                        , "Updated message."
                        , "Add message."
                        , "FIRST OFFICER-717 ALL" //15
                        , "737 :_Placeholder - Initial_AUTOMATION2" //16. Select in Trim sched Planner dropdown
                        , "Recurrent" //17. OQS Training event Type
                        , "MANEUVERS OBSERVATION" //18. Event in OQS
                        , "First Officer" //19. PartialNode in Trim schedule Planing TreeView
                        , "737 :_Placeholder - CQT" //20. select this to refresh the tree
                        , "737" //21 Equipment
                        , "737 - First Officer"}
        };
    }

    @DataProvider(name = "TC181387")
    public static Object[][] TC181387() {
        return new Object[][]{{
                "FIRST OFFICER - 737 All"
                ,"23"
                , "1"
                , "TRN"
                ,"Larry"
                , "Larry" //5 LastName
                , "07/01/1988"
                , "MALE"
                , "Y"
                , "ATP"
                , "99988811" //10
                , "04/05/2020"
                , "BOEING 737 PIC"
                , "Updated message."
                , "Add message."
                , "FIRST OFFICER-717 ALL" //15
                , "737 :_Placeholder - Initial_AUTOMATION2" //16. Select in Trim sched Planner dropdown
                , "Initial" //17. OQS Training event Type
                , "FLIGHT TRAINING" //18. Event in OQS
                , "First Officer" //19. PartialNode in Trim schedule Planing TreeView
                , "737 :_Placeholder - CQT " //20. select this to refresh the tree
                , "737" //21 Equipment
                , "737 - First Officer"}
    };
    }

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
        return new Object[][]{{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
                //{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" },
                //{"FIRST OFFICER - 737 All","22", "1", "TRN","Larry", "Larry", "07/01/1988", "MALE", "Y", "ATP", "99988811", "05/01/2018", "BOEING 737 PIC" }};
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

    @DataProvider(name = "TC184133")
    public static Object[][] TC184133() {
        return new Object[][]{{
                "81144,133761,91732,85928",
                "CQT 2020 - Automation",
                "10",
                "10"
        }};
    }
}