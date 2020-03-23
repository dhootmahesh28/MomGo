package com.swacorp.tsr.trim;

import com.swacorp.crew.css.Css;
import com.swacorp.crew.dataprovider.TestDataProvider;
import com.swacorp.crew.pages.trim.homepage.Requirements;
import com.swacorp.crew.utils.TestManager;
import com.swacorp.tsr.rosa.RosaSolutioQueue;
import com.swacorp.tsr.trim.lib.requirements.NewRequirementTemplate;

import org.testng.annotations.Test;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class TrimRequirementsTest extends TestManager {
    Css css;
    RosaSolutioQueue rosaSolutioQueue;

    @Test(priority=1,groups = {"1"}, dataProvider = "TrimRequirementsTest", dataProviderClass = TestDataProvider.class)
    public void CssDemo(String[] testData) throws Exception{
  /*      setScenarioName("TC164530_ rdrdrrrrrrdrdrdrd");
        Requirements requirements = new Requirements();
        requirements.CreateNewReqTemplate(testData);
*/

        //e117340/ 1Sat1111

        try (PDDocument document = PDDocument.load(new File("C:\\Users\\x257093\\Downloads\\2019CQTEET1119.pdf"))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                //System.out.println("Text:" + st);

                // split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                }

            }
        }
    }
}
