package com.swacorp.crew.pages.constants;

public class GoMomConstants {


    private GoMomConstants(){ }

      public static String SubmittedReportNumber ="";

    public static final String GoMOM_User_Path = "My Work-->Operational-->MOM Information-->Launch Online MOM Report";
    public static final String GoMOM_Admin_Path = "My Work-->Operational-->MOM Information-->MOM Admin";

    private static final String[] ARR_ADDITIONAL_CONTACT_INFO_FIELDS ={"Additional Contact Information", "*Street Address:", "Apt #:",
            "*City:", "*State:", "Telephone Number:", "Zip:"};

    private static final String[] arrContactInformationUM ={"Contact Information for Unaccompanied Minors", "*Last Name:", "*First Name:",
            "Street Address:","Apt #:","City:", "State:", "Zip:", "Telephone Number:","Additional Contact Information","Brief Description of conversation with Contact Person:"};


    private static final String[] arrGeneralInfoFields ={ "Aircraft#","*Event City","*Flight#","*Event Date (mm/dd/yyyy) ","*Original City Pair",
            "*To","*Event Time" };

    private static final String[] arrMOMQuestions ={ "Please Click the following that apply:","Was an emergency declared?  ","Did the majority of the Customers express concern about the flight?  ",
            "Did the Customers experience a highly unusual amount of inconvenience?  ","Did the Customers display  obvious physical or emotional discomfort?  "
            ,"Do you feel a follow up message to the Customer is necessary?  ","Were you able to provide our Customers with an explanation about what occurred?  ",
            "Is this report for a disability-related incident AND have you completed the WN-314/WN-315?  ","Was this flight an Ops Recovery (SWORD) Flight?  ",
            "Did you issue SLVs for the affected Customers?  ","Did you reroute Customers?  ","If SLVs were not provided, did you e-mail a specific list of affected Customers to the PCS Team-DG?  "};

    private static final String[] arrEventtypes ={ "Please Click the following that apply:","Air Turnback  ",
            "Compound Problems  ","Lengthy Delay  ","Misboard/Thru Override Customer  ","Rejected Takeoff  ","Bomb Threat  ",
            "Diversion  ","Medical Emergency  ","Unruly Customer  ","Originator Delay  ","Unaccompanied Minor  ","Young Traveler (ages 12-17)  ",
            "Other  ","If Other Please Explain:"};

    private static final String[] arrDisrutionTypes ={"Please Click the following that apply:","Abnormal aircraft vibration/noise  "
            ,"Cabin Pressurization Problem  ","Customers told to get in the \"brace\" position  ","Delay was under four hours  ","Flaps/Slats Malfunction  ",
            "Landing Crew Gear Problem  ","Severe Turbulence  ","Smoke and/or Smell In Cabin  ","Systems Failure  ","The Customers were truly shaken up  ",
            "ATC Delay (on taxiway longer than two hours)  ","Crew Duty Time Expired(delay longer than two hours)  ","Delay was over four hours  ","Engine shut down  "
            , "Majority of flight was completed before turnback  ","Lengthy/Multiple MX delays (more than four hours)  ","Severe Weather  ","Sudden Braking  ","The Captain declared an emergency onboard  ",
            "The Captain declared an emergency onboard  ","There was obvious physical or emotional discomfort  "};

    private static final String[] arrAdditionEventInfo={"Event Summary (beginning to end):","What type of local Customer Accommodations (if any) were provided?","How did your Team respond to this event?"};

    private static final String[] arrPrintPreview ={"Ground Ops MOM Irregularity Report","General Information",
            "MOM Questions","Event Types","Disruption Types","Additional Event Information","Unaccompanied Minors Onboard",
            "Medical Emergency","Unruly Customer(s)"};

}
