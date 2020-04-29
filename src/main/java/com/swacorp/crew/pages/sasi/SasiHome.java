package com.swacorp.crew.pages.sasi;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.utils.DateUtil;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SasiHome extends BasePage {

    private Map<String, Map<String, ArrayList<String[]>>> masterHM;
    public SasiHome(Map<String, Map<String, ArrayList<String[]>>> rosaMasterHM){
        masterHM = rosaMasterHM;
    }

    private final Logger LOGGER = Logger.getLogger(SasiHome.class);
    ReportUtil report = new ReportUtil();

    /*private final By LOGIN_PAGE_TXT = By.xpath("//h2[text()='Request Optimizer Solution Application']");
    private final By PILOT_TRAINING_BTN = By.xpath("//button[@id='button-1']");
    private final By PILOT_TRAINING_OPTIMIZER_TXT = By.xpath("//h2[text()='Pilot Training Optimizer']");
    private final By CONDITIONAL_RADIO_BTN = By.xpath("//*[text()='Conditional']");
    private final By CONDITIONAL_CORE_RADIO_BTN = By.xpath("//*[text()='Core']");
    private final By START_BTN = By.xpath("//*[@id='startButton']");
    private final By RESET_BTN = By.xpath("//*[text()='Reset']");
    private final By EVENT_DROP = By.xpath("/html/body/app-root/div[2]/div/div/app-pilot-training-request/div[2]/div[1]/div[2]/cl-dropdown/div/div/div[2]");
    private final By BID_LINE_DR0P = By.xpath("//*[@class='cl-icon--dark-midnight cl-icon cl-icon-chevron-down']");
    private final By HARD_LINE_DROP_DOWN_VALUE = By.xpath("//*[text()=' Hard Line ']");
    private final By CONDITIONAL_RADIO = By.xpath("//input[@class='cl-radio__input']");
    private final By START_SUBMIT_BTN = By.xpath("//*[text()='Start']");
    private final By PTO_QUEUE_BTN = By.xpath("(//*[text()='Queue'])[1]");*/

    private final By WORLD_VIEWER_PARADISE = By.xpath("//*[text()='World Viewer - Paradise']");
    private final By SELECT_DRP = By.xpath("//select[@id='qt']");
    private final By EMPLOYEE = By.xpath("//input[@name='attributeEmployeeID']");
    private final By submit = By.xpath("//*[@id='submitRow']/td/input");
    private final By firstAnchorLinq = By.xpath("//a[@title = 'click for detailed crew view'][1]");

    String rosaempID;

    public void readMasterHM(){
        HashMap<Integer, String[]> hm = new HashMap<>();
        for (Map.Entry<String, Map<String, ArrayList<String[]>>> entry : masterHM.entrySet()){
            rosaempID = entry.getKey();
            Map<String, ArrayList<String[]>> y = entry.getValue();
            break;
        }
    }

    public boolean nonFlyValidate(String[] data) throws ParseException{//}, String header){
        String countNonFly = "//*[text()='NonflyActivities']//following-sibling::ul";
        String Airport      = "//*[text()='NonflyActivities']//following-sibling::ul[PLACEHOLDER]//tbody//*[text()='airportID']//following-sibling::td[2]/a[1]";
        String endDate      = "//*[text()='NonflyActivities']//following-sibling::ul[PLACEHOLDER]//tbody//*[text()='endDateTime']//following-sibling::td[2]"   ;
        String startDate    = "//*[text()='NonflyActivities']//following-sibling::ul[PLACEHOLDER]//tbody//*[text()='startDateTime']//following-sibling::td[2]" ;
        String positionID   = "//*[text()='NonflyActivities']//following-sibling::ul[PLACEHOLDER]//tbody//*[text()='positionID']//following-sibling::td[2]/a[1]";

        String tempLocator = new String();
        List<WebElement> allNonflySections = getDriver().findElements(By.xpath(countNonFly));
        String tempLocatorAirport;// = new String();
        String tempLocatorStartDate;// = new String();
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
        DateUtil du = new DateUtil();
        ArrayList<String[]> trn = new ArrayList<>();
        boolean proceedToNextRow = true;

       /* String SasiDate = du.changeToSasiDateFormat(rowData[0]);
        nonFlyValidate(SasiDate, "startdate");
        nonFlyValidate(rowData[1].substring(1,3), "base");*/

        for (Map.Entry<String, Map<String, ArrayList<String[]>>> entry : masterHM.entrySet()){
            String k = entry.getKey();
            Map<String, ArrayList<String[]>> y = entry.getValue();

            for (Map.Entry<String, ArrayList<String[]>> entry2 : y.entrySet()){
                //String k2 = entry2.getValue("ss");
                Map<String, ArrayList<String[]>> y2 = entry.getValue();
                trn = y2.get("trng");
                break;
            }
            break;
        }

        for (int j=0; j<= trn.size()-1; j++){
            String[] rowData = trn.get(j);
            //String SasiDate = du.changeToSasiDateFormat(rowData[0]);
            proceedToNextRow = nonFlyValidate(rowData);

            if (!proceedToNextRow){
                report.reportSelenium("Fail", "Nonfly details from Rosa '"+String.join(",", rowData)+"' Not found in SASI.");
                break;
            }else{
                report.reportSelenium("pass", "Nonfly details from Rosa '"+String.join(",", rowData)+"' found in SASI.");
            }

            //nonFlyValidate(rowData[1].substring(1,3), "base");
            //nonFlyValidate(rowData[2], "nonflycode");
       }

        /*String xpathPortion = "//*[text() = '"+"PLACEHOLDER"+"'][1]";
        String allTd   = "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr";

        String airport =   "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[1]/td[3]/a[1]";
        String startDate = "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[4]/td[3]";
        String endDate =   "//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[13]/td[3]";

        System.out.println(getDriver().findElement(By.xpath(airport)).getText());
        System.out.println(getDriver().findElement(By.xpath(startDate)).getText());
        System.out.println(getDriver().findElement(By.xpath(endDate)).getText());
        System.out.println(getDriver().findElement(By.xpath("//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[12]/td[3]")).getText());
        System.out.println(getDriver().findElement(By.xpath("//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[11]/td[3]")).getText());
        System.out.println(getDriver().findElement(By.xpath("//*[text()='NonflyActivity']/following-sibling::table/tbody/tr[10]/td[3]")).getText());
        scrollToElement(getDriver().findElement(By.xpath(airport)));
        String expectedBase = "DEN";
        String expectedStartDate = "2020-04-02";
        if (getDriver().findElement(By.xpath(airport)).getText().contains(expectedBase)){
            report.reportSelenium("Pass", "The expected airport-id fould in SASI: "+expectedBase);
        }

        scrollToElement(getDriver().findElement(By.xpath(endDate)));
        if (getDriver().findElement(By.xpath(startDate)).getText().contains(expectedStartDate)){
            report.reportSelenium("Pass", "The expected start date is found in SASI: "+expectedStartDate);
        }*/

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

    public void selectData() throws Exception{
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

        buttonClick(WORLD_VIEWER_PARADISE);
        if (isElementVisible(WORLD_VIEWER_PARADISE)){
            report.reportSelenium("Pass", "Clicked on world viewer paradise link.");
        }else{
            report.reportSelenium("Fail", "Failed to click on world viewer paradise link.");
        }
    }




}
