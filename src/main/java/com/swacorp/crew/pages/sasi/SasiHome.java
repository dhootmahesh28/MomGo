package com.swacorp.crew.pages.sasi;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SasiHome extends BasePage {

    ReportUtil report = new ReportUtil();
    private Map<String, Map<String, ArrayList<String[]>>> masterHM;
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();

    private final By WORLD_VIEWER_PARADISE = By.xpath("//*[text()='World Viewer - Paradise']");
    private final By SELECT_DRP = By.xpath("//select[@id='qt']");
    private final By EMPLOYEE = By.xpath("//input[@name='attributeEmployeeID']");
    private final By submit = By.xpath("//*[@id='submitRow']/td/input");
    private final By firstAnchorLinq = By.xpath("//a[@title = 'click for detailed crew view'][1]");
    String rosaempID;

    public SasiHome(Map<String, Map<String, ArrayList<String[]>>> rosaMasterHM){
        masterHM = rosaMasterHM;
    }
    public SasiHome(){
    }

    private void readMasterHM(){
        try{
            if((rosaempID == null)){
                masterHM = TestUtil.getRosaMasterHM();
                if (masterHM == null){
                    report.reportSelenium("Fail", "masterHM is null.");
                }else {

                    for (Map.Entry<String, Map<String, ArrayList<String[]>>> lstEmpIds : masterHM.entrySet()) {
                        rosaempID = lstEmpIds.getKey();
                        Map<String, ArrayList<String[]>> detailEachEmpId = lstEmpIds.getValue();

                        for (Map.Entry<String, ArrayList<String[]>> entry2 : detailEachEmpId.entrySet()) {
                            Map<String, ArrayList<String[]>> keyEmpId = lstEmpIds.getValue();
                            training = keyEmpId.get("trng");
                            triptopull = keyEmpId.get("triptopull");
                        }
                        break;
                    }
                    if ((rosaempID == null)) {
                        report.reportSelenium("Fail", "Error occured while reading trip details from ROSA in CSS page. rosaempID is null.");
                    } else {
                        report.reportSelenium("info", "ROSA employee details are read in CSS. HashMap size:"+masterHM.size());
                    }
                }
            }
        }catch(Exception e){
            report.reportSelenium("Fail", "Error occured while reading trip details from ROSA in CSS page."+e.getMessage());
        }
    }


    public boolean nonFlyValidate(String[] data) throws ParseException{
        String countNonFly = "//*[text()='NonflyActivities']//following-sibling::ul";
        String Airport      = "//*[text()='NonflyActivities']//following-sibling::ul[PLACEHOLDER]//tbody//*[text()='airportID']//following-sibling::td[2]/a[1]";
        String startDate    = "//*[text()='NonflyActivities']//following-sibling::ul[PLACEHOLDER]//tbody//*[text()='startDateTime']//following-sibling::td[2]" ;

        List<WebElement> allNonflySections = getDriver().findElements(By.xpath(countNonFly));
        String tempLocatorAirport;
        String tempLocatorStartDate;
        boolean dateNotFound = false;
        DateUtil du = new DateUtil();

        for (int i=1; i <= allNonflySections.size();i++){
            tempLocatorAirport = Airport.replace("PLACEHOLDER", ""+i);
            tempLocatorStartDate = startDate.replace("PLACEHOLDER", ""+i);


            List<WebElement> eles = getDriver().findElements(By.xpath(tempLocatorStartDate));

            if (eles.size() > 0){
                WebElement ele = eles.get(0);
                //If date matches go for rest of the validation
                String startDateSASI = du.changeToSasiDateFormat(data[0]);
                if (ele.getText().contains(startDateSASI)){
                    report.reportSelenium("pass", "Nonfly activity in SASI does not contain start date from ROSA "+startDateSASI);
                    //read the base
                    WebElement baseEle = getDriver().findElement(By.xpath(tempLocatorAirport));
                    scrollToElement(baseEle);
                    if (baseEle.getText().contains(data[2].substring(0,2))){
                        report.reportSelenium("pass", "Base is found in SASI as per ROSA "+data[2]);
                    }else{
                        report.reportSelenium("fail", "Base is NOT found in SASI as per ROSA "+data[2]);
                    }

                }else if(i == allNonflySections.size()){
                    scrollToElement(ele);
                    report.reportSelenium("Fail", "Nonfly activity in SASI does not contain start date from ROSA "+startDateSASI);
                    dateNotFound = false;
                }else{
                    scrollToElement(ele);
                    report.reportSelenium("info", "Nonfly activity section '"+i+"' in SASI does not contain start date from ROSA "+startDateSASI);
                }

            }
        }

        return dateNotFound;
    }

    public void readNonFlyDetails() throws ParseException {
        boolean proceedToNextRow = true;
        for (int j=0; j<= training.size()-1; j++){
            String[] rowData = training.get(j);
            proceedToNextRow = nonFlyValidate(rowData);

            if (!proceedToNextRow){
                report.reportSelenium("Fail", "Nonfly details from Rosa '"+String.join(",", rowData)+"' Not found in SASI.");
                break;
            }else{
                report.reportSelenium("pass", "Nonfly details from Rosa '"+String.join(",", rowData)+"' found in SASI.");
            }
       }
    }

public void clickFirstLink(){
        try {

            report.reportSelenium("Pass", "Search Result has appeared and clicking on the first link. ");
            buttonClick(firstAnchorLinq);
            report.reportSelenium("Pass", "Navigated to the SASi details page to find nonfly information.");

        }catch(Exception e){
            report.reportSelenium("Fail", "Error while clickng on the search result. ");
        }
}

    public void selectData(){
        try {
            Thread.sleep(5000);
            getDriver().switchTo().frame(0);
            selectOption(SELECT_DRP, "Crew ");
            readMasterHM();
            enterText(EMPLOYEE, rosaempID);
            report.reportSelenium("Pass", "EmployeeID is entered: "+rosaempID);
            buttonClick(submit);
        }catch(Exception e){
            report.reportSelenium("Fail", "Failed while performing the employee search ");
            e.printStackTrace();
        }
    }

    public void clickWorldViewer(){
        readMasterHM();
        buttonClick(WORLD_VIEWER_PARADISE);
        if (isElementVisible(WORLD_VIEWER_PARADISE)){
            report.reportSelenium("Pass", "Clicked on world viewer paradise link.");
        }else{
            report.reportSelenium("Fail", "Failed to click on world viewer paradise link.");
        }
    }

}
