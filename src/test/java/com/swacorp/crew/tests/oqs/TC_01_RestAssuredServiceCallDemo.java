package com.swacorp.crew.tests.regression.oqs.crew14477;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.utils.RestassuredUtil;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Created by x257093 on 03-Jan-2020.
 */

// TC181510_181551_181552_
public class TC_01_RestAssuredServiceCallDemo extends TestManager {
    private final Logger LOGGER = Logger.getLogger(TC_01_RestAssuredServiceCallDemo.class);

    @Test(priority = 6, groups = {"111"})
    public void TC01_RestAssuredServiceCallDemo_01() throws GeneralLeanFtException, Exception {
        ReportUtil report = new ReportUtil();
        setScenarioName("TC01_RestAssuredServiceCallDemo_01");
        RestassuredUtil restUtl = new RestassuredUtil();

        String baseUrl = "http://pilottrainingscheduling-qa1.swab2bqa.com";
        HashMap<String, String> heards = new HashMap<String, String>();
        heards.put("Content-Type","application/json");
        heards.put("key","AvRunVGAUSGQ58CAG38S24tFZaLLFc8FFGzBNqsVk5eAcamVCPtuKutnKrd2Mr2rLAFFEbnVA8GTn9PPt4ha6Afq5Xta6s3M8tFXeNUMrpAEz3dJ6g2UYCLnQe362VS3");
        heards.put("action","listSingle");
        heards.put("empNumber","48109");

        restUtl.ExecuteREST("GET", "http://pilottrainingscheduling-qa1.swab2bqa.com/API/tdfEmployees.php", heards );
        //System.out.println("Response body is "+restUtl.getResponseBody());
        assertThat(restUtl.getResponseBody(), containsString("\"EmployeeID\":\"15055\""));



        baseUrl = "http://pilottrainingscheduling-qa1.swab2bqa.com";
        heards = new HashMap<String, String>();
        heards.put("Content-Type","application/json");
        heards.put("key","AvRunVGAUSGQ58CAG38S24tFZaLLFc8FFGzBNqsVk5eAcamVCPtuKutnKrd2Mr2rLAFFEbnVA8GTn9PPt4ha6Afq5Xta6s3M8tFXeNUMrpAEz3dJ6g2UYCLnQe362VS3");
        heards.put("action","listSingle");
        heards.put("empNumber","88147");

        restUtl.ExecuteREST("GET", "http://pilottrainingscheduling-qa1.swab2bqa.com/API/tdfEmployees.php", heards );
        //System.out.println("Response body is "+restUtl.getResponseBody());
        assertThat(restUtl.getResponseBody(), containsString("\"EmployeeID\":\"15056\""));
        report.report("Passed", "String found");

    }
}