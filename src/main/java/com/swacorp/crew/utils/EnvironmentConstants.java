package com.swacorp.crew.utils;

import com.swacorp.crew.utils.config.EnvironmentFactory;

public final class EnvironmentConstants {

    public static final String OQSURL = EnvironmentFactory.getConfigValue("oqsUrl");
    public static final String OQSLOGINUSER = EnvironmentFactory.getConfigValue("oqsLoginUser");
    public static final String TRiMAPPPATH = EnvironmentFactory.getConfigValue("trimAppPath");
    public static final String TRiMLOGINUSER = EnvironmentFactory.getConfigValue("trimLoginUser");
    public static final String TRiMLOGINPASSWORD = EnvironmentFactory.getConfigValue("trimLoginPassword");
}
