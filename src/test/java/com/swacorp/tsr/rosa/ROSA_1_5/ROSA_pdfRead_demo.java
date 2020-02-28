package com.swacorp.tsr.rosa.ROSA_1_5;

import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.rosa.RosaHome;
import com.swacorp.tsr.rosa.RosaLogin;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import com.swacorp.tsr.utils.pdf.RosaPTOReports;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ROSA_pdfRead_demo extends TestManager {
    RosaLogin rosa;
    RosaSolutioQueue rosaSolutioQueue;

    @Test(groups = {"pdf"})
    public void TC164530_010_FO_Validate_that_PTO_page_is_displayed_the_initial_dependent_parameters_Edge_Navigator() throws Exception{
        setScenarioName("TC001_ Verifying the pdf text");
        RosaPTOReports rosaPTOReports = new RosaPTOReports("C:\\Users\\x257093\\Downloads\\2019CQTEET1119.pdf");
        ArrayList<String> pdf =
                rosaPTOReports.readBetweenTags("To Schedule",1, "Pre-Assigned", 2, "Page+\\s\\d+\\s+of+\\s+\\d+", "\\d+\\s{1}+[A-Z]+\\s+[A-Z]+\\s+[A-Z]+\\s+[A-Z]");

        for (String ele:pdf) {
              System.out.println(ele);
        }
    }
}