package com.swacorp.crew.utils;

import com.swacorp.crew.utils.config.EnvironmentFactory;

public final class EnvironmentConstants {

    public static final String OQSURL = EnvironmentFactory.getConfigValue("oqsUrl");
    public static final String OQSLOGINUSER = EnvironmentFactory.getConfigValue("oqsLoginUser");
    public static final String TRiMAPPPATH = EnvironmentFactory.getConfigValue("trimAppPath");
    public static final String TRiMLOGINUSER = EnvironmentFactory.getConfigValue("trimLoginUser");
    public static final String TRiMLOGINPASSWORD = EnvironmentFactory.getConfigValue("trimLoginPassword");
    public static final String ROSAURL = EnvironmentFactory.getConfigValue("rosaUrl");
    public static final String ROSAUSERID = EnvironmentFactory.getConfigValue("rosaUserid");
    public static final String ROSAPASSWORD = EnvironmentFactory.getConfigValue("rosaPassword");

    public static final String CSSPATH = EnvironmentFactory.getConfigValue("cssPath");
    public static final String CSSWINDOWTITLE = EnvironmentFactory.getConfigValue("cssWindowTitle");
    public static final String CSSUSERID = EnvironmentFactory.getConfigValue("cssUserid");
    public static final String CSSPASSWORD = EnvironmentFactory.getConfigValue("cssPassword");

    public static final String SASIURL = EnvironmentFactory.getConfigValue("sasiUrl");

}
