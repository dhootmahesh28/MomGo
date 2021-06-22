package com.swacorp.crew.pages.constants;

public class AsapConstants {

    private AsapConstants(){

    }
    public static final String PENDING_ERT_TAB_NAME = "Pending ERT Review";
    public static final String ERT_MEETING_TAB_NAME = "ERT Meeting";
    public static final String POST_ERT_TAB_NAME = "Post-ERT";
    public static final String ERT_SEARCH_TAB_NAME = "Search";
    public static final String ERT_REPORTS = "Reports Received";

    public static final String PENDING_ERC_TAB_NAME = "Pending ERC Review";
    public static final String MX_DP_ERC_MEETING_TAB_NAME = "ERC Meeting";
    public static final String BTN_READY_FOR_ERC = "Ready for ERC";
    public static final String BTN_SEND_TO_ERC = "Send to ERC";
    public static final String READY_FOR_ERC_TAB_NAME = "Ready for ERC";
    public static final String ERC_MEETING_TAB_NAME = "The ERC meeting";
    public static final String POST_ERC_TAB_NAME = "Post-ERC";

    public static final String EMAIL_SUCCESS_MSG = "Your report has been sent. You should receive a copy in your email within the next few minutes.";

    public static final String ASAP_REPORT_TITLE = "Launch ASAP";
    public static final String ASAP_MANAGER_TITLE = "ASAP Manager";
    public static final String ASAP_EVENT_TYPE_TITLE = "Event Type";
    public static final String ASAP_EVENT_DESCRIPTION_TITLE = "Event Description";
    public static final String ASAP_PREVIEW_REPORT_TITLE = "Preview Report";
    public static final String ASAP_REPORT_SUBMITTED_TITLE = "ReportSubmitted";

    public static final String FO_MANAGER_PATH = "My Work-->Flight Ops-->Forms/Reports-->ASAP-->ASAP Manager";
    public static final String IF_MANAGER_PATH = "My Work-->Inflight-->Crew Member Tools-->ASAP-->ASAP Manager";
    public static final String MX_MANAGER_PATH = "My Work-->Tech Ops-->Safety & Compliance Reporting-->ASAP Manager";
    public static final String DP_MANAGER_PATH = "My Work-->NOC-->Safety-->ASAP-->ASAP Manager";

    public static final String FO_ASAP_PATH = "My Work-->Flight Ops-->Forms/Reports-->ASAP-->Launch ASAP";
    public static final String IF_ASAP_PATH = "My Work-->Inflight-->Crew Member Tools-->ASAP-->Launch ASAP";
    public static final String MX_ASAP_PATH = "My Work-->Tech Ops-->Safety & Compliance Reporting-->Launch ASAP";
    public static final String DP_ASAP_PATH = "My Work-->NOC-->Safety-->ASAP-->Launch ASAP";

    public static final String NOTES_HEADER = "Notes";
    public static final String COMPANY_CORRECTIVE_HEADER = "Company Corrective Actions";
    public static final String EVENT_DESCRIPTION_HEADER = "Event Description";
    public static final String CONTACT_INFORMATION_HEADER = "Contact Information";
    public static final String SUMMARY_EVENTS_HEADER = "Summary of Event";
    public static final String FLIGHT_INFORMATION_HEADER = "Flight Information and Conditions";

    public static final String[] ARR_IF_HEADERS = {NOTES_HEADER, COMPANY_CORRECTIVE_HEADER, CONTACT_INFORMATION_HEADER, SUMMARY_EVENTS_HEADER, EVENT_DESCRIPTION_HEADER, FLIGHT_INFORMATION_HEADER, ASAP_EVENT_TYPE_TITLE, "Factors", "Detection/Reaction"};
    public static final String[] ARR_FO_HEADERS = {NOTES_HEADER, COMPANY_CORRECTIVE_HEADER, CONTACT_INFORMATION_HEADER, SUMMARY_EVENTS_HEADER, EVENT_DESCRIPTION_HEADER, FLIGHT_INFORMATION_HEADER, ASAP_EVENT_TYPE_TITLE, "Threats", "Crew Errors"};
    public static final String[] ARR_MX_HEADERS = {NOTES_HEADER, COMPANY_CORRECTIVE_HEADER, "Employee Information", "Event Information", "Event Types", "Supporting Information", EVENT_DESCRIPTION_HEADER};
    public static final String[] ARR_DP_HEADERS = {NOTES_HEADER, COMPANY_CORRECTIVE_HEADER,  CONTACT_INFORMATION_HEADER, SUMMARY_EVENTS_HEADER, EVENT_DESCRIPTION_HEADER, FLIGHT_INFORMATION_HEADER, ASAP_EVENT_TYPE_TITLE, "Threats", "Errors"};

    public static final String USER_NAME = System.getProperty("user.name");
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String DOWNLOAD_PATH = USER_DIR + "\\build\\extent-reports\\test-data\\";

    public static final String CST_TIME_ZONE = "CST - Central Standard Time";
    public static final String EST_TIME_ZONE = "EST - Eastern Standard Time";
    public static final String DAL_DOM_STATION = "DAL";
    public static final String HOU_DOM_STATION = "HOU";
    public static final String ATL_DOM_STATION = "ATL";
    public static final String CUN_INT_STATION = "CUN";


}
