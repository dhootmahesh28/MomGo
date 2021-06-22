package com.swacorp.crew.utils;

import com.swacorp.crew.utils.config.EnvironmentFactory;

public final class EnvironmentConstants {
    private EnvironmentConstants() {
    }
    public static final String FSSR_URL = EnvironmentFactory.getConfigValue("fssrURL");
    public static final String FSSR_LOGIN_USER = EnvironmentFactory.getConfigValue("fssrLoginUser");
    public static final String FSSR_PASSWORD = EnvironmentFactory.getConfigValue("fssrPassword");
    public static final String SWA_LIFE_URL = EnvironmentFactory.getConfigValue("swalifeURL");
    public static final String SWA_LIFE_LOGIN_USER = EnvironmentFactory.getConfigValue("swaLifeUser");
    public static final String SWA_LIFE_LOGIN_MANAGER = EnvironmentFactory.getConfigValue("swaLifeManager");
    public static final String SWA_LIFE_PASSWORD = EnvironmentFactory.getConfigValue("swaLifePassword");
    public static final String ASAP_REPORT_LINK_PATH = EnvironmentFactory.getConfigValue("asapReportLinkPath");
    public static final String ASAP_MANAGER_REPORT_LINK_PATH = EnvironmentFactory.getConfigValue("asapManagerReportLinkPath");
    public static final String ASAP_MANAGER_ID = EnvironmentFactory.getConfigValue("swaLifeManagerUID");
    public static final String ASAP_MANAGER_PASSWORD = EnvironmentFactory.getConfigValue("swaLifeManagerPassword");


    public static final String SWA_LIFE_GOMOM_USER = EnvironmentFactory.getConfigValue("GoMOMUser");
    public static final String SWA_LIFE_GOMOM_ADMIN = EnvironmentFactory.getConfigValue("GoMOMAdmin");

    public static final String SWA_LIFE_URL_USER = EnvironmentFactory.getConfigValue("swalifeURL");
    public static final String SWA_LIFE_URL_ADMIN = EnvironmentFactory.getConfigValue("swalifeURL1");


    public static final String ASAP_FO_USER_URL = EnvironmentFactory.getConfigValue("asapFOUSERurl");
    public static final String ASAP_FO_Manager_URL = EnvironmentFactory.getConfigValue("asapFOManegerurl");
    public static final String ASAP_FO_USER_ID = EnvironmentFactory.getConfigValue("asapFOUserID");
    public static final String ASAP_FO_Manager_ID = EnvironmentFactory.getConfigValue("asapFOManagerID");
    public static final String ASAP_FO_Manager_SecondryID = EnvironmentFactory.getConfigValue("asapFOManagerIDSecondary");
    public static final String ASAP_FO_Manager_SecondryPWD = EnvironmentFactory.getConfigValue("asapFOManagerPWDSecondary");

    public static final String ASAP_IF_USER_URL = EnvironmentFactory.getConfigValue("asapIFUSERurl");
    public static final String ASAP_IF_Manager_URL = EnvironmentFactory.getConfigValue("asapIFManegerurl");
    public static final String ASAP_IF_USER_ID = EnvironmentFactory.getConfigValue("asapIFUserID");
    public static final String ASAP_IF_Manager_ID = EnvironmentFactory.getConfigValue("asapIFManagerID");
    public static final String ASAP_IF_Manager_SecondryID = EnvironmentFactory.getConfigValue("asapIFManagerIDSecondary");
    public static final String ASAP_IF_Manager_SecondryPWD = EnvironmentFactory.getConfigValue("asapIFManagerPWDSecondary");

    public static final String ASAP_MX_USER_URL = EnvironmentFactory.getConfigValue("asapMXUSERurl");
    public static final String ASAP_MX_Manager_URL = EnvironmentFactory.getConfigValue("asapMXManegerurl");
    public static final String ASAP_MX_USER_ID = EnvironmentFactory.getConfigValue("asapMXUserID");
    public static final String ASAP_MX_Manager_ID = EnvironmentFactory.getConfigValue("asapMXManagerID");
    public static final String ASAP_MX_Manager_SecondryID = EnvironmentFactory.getConfigValue("asapMXManagerIDSecondary");
    public static final String ASAP_MX_Manager_SecondryPWD = EnvironmentFactory.getConfigValue("asapMXManagerPWDSecondary");

    public static final String ASAP_DP_USER_URL = EnvironmentFactory.getConfigValue("asapDPUSERurl");
    public static final String ASAP_DP_Manager_URL = EnvironmentFactory.getConfigValue("asapDPManegerurl");
    public static final String ASAP_DP_USER_ID = EnvironmentFactory.getConfigValue("asapDPUserID");
    public static final String ASAP_DP_Manager_ID = EnvironmentFactory.getConfigValue("asapDPManagerID");
    public static final String ASAP_DP_Manager_SecondryID = EnvironmentFactory.getConfigValue("asapDPManagerIDSecondary");
    public static final String ASAP_DP_Manager_SecondryPWD = EnvironmentFactory.getConfigValue("asapDPManagerPWDSecondary");


}

