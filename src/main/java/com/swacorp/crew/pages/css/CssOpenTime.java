package com.swacorp.crew.pages.css;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.internal.DynamicObjectProxy;
import com.hp.lft.sdk.java.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.pages.constants.ApplicationConstantsCss;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.utils.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.List;

public class CssOpenTime extends WinBasePage{

    ReportUtil reportCssHome = new ReportUtil();
    private final Logger loggerCssOpenTime = Logger.getLogger(CssOpenTime.class);
    ObjectRepoCSS lftObjects =null;
    Map<String, Map<String, ArrayList<String[]>>> masterHM = new LinkedHashMap<>();
    String rosaempID;
    ArrayList<String[]> training = new ArrayList<>();
    ArrayList<String[]>  triptopull = new ArrayList<>();


    public CssOpenTime()  {
        lftObjects = super.cssObjectRepo;
    }

    public void readOTTripDetails() throws  GeneralLeanFtException, CloneNotSupportedException {
        try {
            UiObject x = lftObjects.CssMainWindow().crewBoardPieceUiObject();
            x.highlight();

            //readDataFromTripDetails
            UiObjectDescription allUIObj = new UiObjectDescription.Builder()
                    .nativeClass("com.swacorp.css.screens.crewboard.CrewBoardPiece").build();
            UiObject[] allObj = lftObjects.CssMainWindow().findChildren(UiObject.class, allUIObj);

            Object startDate;
            Object endDate;
            for (int index = allObj.length - 1; index > 0; index--) {
                UiObject tripDetails = allObj[index];
                tripDetails.highlight();
                startDate = allObj[index].getNativeObject().invokeMethod("getStartDate", DynamicObjectProxy.class).invokeMethod("getCalendarDate", DynamicObjectProxy.class).invokeMethod("toString", DynamicObjectProxy.class);
                endDate = allObj[index].getNativeObject().invokeMethod("getEndDate", DynamicObjectProxy.class).invokeMethod("getCalendarDate", DynamicObjectProxy.class).invokeMethod("toString", DynamicObjectProxy.class);
                if (startDate.toString().contains("2020-03-10") && endDate.toString().contains("2020-03-15")) {
                    tripDetails.doubleClick();
                    if (searchTheCorrectTripFromOT("", "")) {
                        break;
                    }
                }
            }
            if (lftObjects.CssMainWindow().openTimeInternalFrame().exists()) {
                lftObjects.CssMainWindow().openTimeInternalFrame().close();
            }
        }catch(GeneralLeanFtException e){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Error occured while reading trip details "+e.getMessage());
            throw new GeneralLeanFtException("Error occured while reading trip details "+e.getMessage());
        }catch(CloneNotSupportedException ex){
            loggerCssOpenTime.error(ex.getMessage());
            throw new CloneNotSupportedException("Error while search the trip details on CM board."+ex.getLocalizedMessage());
        }
    }

    public boolean searchTheCorrectTripFromOT(String startDate, String endDate) throws CloneNotSupportedException, GeneralLeanFtException {
        boolean found = false;
        TableDescription tblDesc = new TableDescription.Builder()
                .nativeClass("com.swacorp.css.screens.trip.TripDetailsTable").build();

        Table[] allTables;
        allTables = lftObjects.CssMainWindow().frameTrimDetails().findChildren(Table.class, tblDesc);

        loggerCssOpenTime.info(" All Tables: " + allTables.length);

        if (startDate.isEmpty()) {
            try {
                startDate = allTables[allTables.length - 1].getRows().get(0).getCells().get(0).getValue().toString().split(",")[125];
            }  catch (Exception e) {
                throw new GeneralLeanFtException(e.getMessage(), e);
            }
        }

        if(endDate.isEmpty()) {
            try {
                endDate = allTables[allTables.length - 1].getRows().get(0).getCells().get(0).getValue().toString().split(",")[2173];
            } catch (Exception e) {
                throw new GeneralLeanFtException(e.getMessage(), e);
            }
        }

        if (endDate.length()>0){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Pass", "Trip found on OT. with start date "+startDate+ ", and endDate: "+endDate);
            found = true;
        }
        Table tbl2 = lftObjects.CssMainWindow().frameTrimDetails().crewMembersTable();
        List<TableRow> allrows = tbl2.getRows();

        if(found) {
            for (int i = 0; i < allrows.size(); i++) {
                tbl2.activateRow(0);
                allrows.get(i).getCells().get(2).click();
                tbl2.highlight();
                String assignment = allrows.get(i).getCells().get(1).getValue().toString();
                String position = allrows.get(i).getCells().get(2).getValue().toString();

                if (assignment.contains("Unassigned") && position.contains(ApplicationConstantsCss.CSS_POSITION_CA)) {
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Pass", "Position unassigned for " + position);
                    break;
                } else {
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "fail", "Position NOT unassigned for " + position);
                }
            }
        }
        lftObjects.CssMainWindow().frameTrimDetails().close();
        return found;
    }

    public void selectOTfilters(String filter) throws GeneralLeanFtException {
        readMasterHM();
        try {
            if(!(filter.equalsIgnoreCase(""))) {
                lftObjects.CssMainWindow().openTimeInternalFrame().activate();
                lftObjects.CssMainWindow().openTimeInternalFrame().fromEditor().sendKeys(ApplicationConstantsCss.CSS_OT_TRIP_START_DATE);
                lftObjects.CssMainWindow().openTimeInternalFrame().toEditor().sendKeys(ApplicationConstantsCss.CSS_OT_TRIP_END_DATE);

                do {
                    if (lftObjects.CssMainWindow().openTimeInternalFrame().whiteArrowExpandedState().exists()) {
                        lftObjects.CssMainWindow().openTimeInternalFrame().whiteArrowExpandedState().click();
                    } else {
                        break;
                    }
                } while (lftObjects.CssMainWindow().openTimeInternalFrame().whiteArrowExpandedState().exists());

                List<String> items = Arrays.asList(filter.split(","));
                for (String item : items) {
                    lftObjects.CssMainWindow().openTimeInternalFrame().BaseCheckbox().setDescription(new CheckBoxDescription.Builder().attachedText(item).build());
                    lftObjects.CssMainWindow().openTimeInternalFrame().BaseCheckbox().setState(CheckedState.CHECKED);
                }
                lftObjects.CssMainWindow().openTimeInternalFrame().getOpenTripsButton().click();

                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "pass", "OT filters selected:" + filter);
            }else{
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "info", "Filter selection data is empty.");
            }
        } catch (Exception e) {
            throw new GeneralLeanFtException(e.getMessage(), e);
        }

    }

    public void navigateToOT() throws GeneralLeanFtException {

        try {
            lftObjects.CssMainWindow().openTimeMenu().click();
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"pass", "Successfully clicked on the OT menue.");
            lftObjects.CssMainWindow().openTimeMenu().viewOpenTimeMenu().click();

            if (lftObjects.CssMainWindow().openTimeInternalFrame().fromEditor().exists()){
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"pass", "OT window has appeared.");
            }else {
                reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "fail", "OT window didn't appear.");
            }

        } catch (Exception e) {
            loggerCssOpenTime.error("Error :"+e.getMessage());
            throw new GeneralLeanFtException("Error while navigating to OT");
         }


    }

    private void readMasterHM(){
        int i=1;
        try{
            if((rosaempID == null)){
                masterHM = TestUtil.getRosaMasterHM();
                if (masterHM == null){
                    reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "masterHM is null.");
                }else {

                    for (Map.Entry<String, Map<String, ArrayList<String[]>>> lstEmpIds : masterHM.entrySet()) {
                        rosaempID = lstEmpIds.getKey();
                        Map<String, ArrayList<String[]>> keyEmpId = lstEmpIds.getValue();
                        training = keyEmpId.get("trng");
                        triptopull = keyEmpId.get("triptopull");


                        if (Integer.compare(i,ApplicationConstantsCss.NUMBER_OF_EMPLOYEE)==1) {
                            break;
                        }
                        i++;
                    }
                    if ((rosaempID == null)) {
                        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "Fail", "Error occured while reading trip details from ROSA in CSS page. rosaempID is null.");
                    } else {
                        reportCssHome.reportLeanFT(lftObjects.CssMainWindow(), "info", "ROSA employee details are read in CSS. HashMap size:"+masterHM.size());
                    }
                }
            }
        }catch(Exception e){
            reportCssHome.reportLeanFT(lftObjects.CssMainWindow(),"Fail", "Error occured while reading trip details from ROSA in CSS page."+e.getMessage());
            throw e;
        }

    }

}




