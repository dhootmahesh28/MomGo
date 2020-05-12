package com.swacorp.crew.tests.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.css.*;
import com.swacorp.crew.utils.TestUtil;

import java.io.IOException;

public class CssWrapper extends TestUtil {
    CssLogin cssLogin;
    //CssHome cssHome;
    CssCrewMemberBoard cssCrewMemberBoard;
    CssOpenTime cssOpenTime;
    CssTransactionReport cssTransactionReport;

    public CssWrapper(){
        cssLogin = new CssLogin();
        //cssHome = new CssHome();
        cssCrewMemberBoard = new CssCrewMemberBoard();
        cssOpenTime = new CssOpenTime();
        cssTransactionReport = new CssTransactionReport();
    }

    public void loginCss() throws GeneralLeanFtException, IOException {
        cssLogin.loginCss();
    }

    public void openCMBoard(String empCode) throws GeneralLeanFtException{
        cssCrewMemberBoard.openCMBoard(empCode);
    }

    public void selectTripOnCMBoard(String tripStartDateROSA, String tripEndDateROSA, String TripDetails, boolean tripShouldBeAvailableOnCMBoard, boolean readTripDetails, boolean readTransactionReport) throws GeneralLeanFtException, CloneNotSupportedException {
        cssCrewMemberBoard.selectTripOnCMBoard(tripStartDateROSA,tripEndDateROSA, tripShouldBeAvailableOnCMBoard, readTripDetails, readTransactionReport);
    }

    public void ValidateNotifiedOption(String notifiedOption) throws GeneralLeanFtException{
        cssCrewMemberBoard.validateNotifiedOption(notifiedOption);
    }

    public void ValidateCredits() throws GeneralLeanFtException {
        cssCrewMemberBoard.validatecredits();
    }

    public void NavigateToOT() throws GeneralLeanFtException{
        cssOpenTime.navigateToOT();
    }

    public void selectOTfilters(String filter) throws GeneralLeanFtException {
        cssOpenTime.selectOTfilters(filter);
    }

    public void readOTTripDetails() throws GeneralLeanFtException, CloneNotSupportedException  {
        cssOpenTime.readOTTripDetails();
    }

    public void NavigateToTransactionReport() throws GeneralLeanFtException{
        cssTransactionReport.navigateToTransactionReport();
    }

    public void  validateTransactioTeportDialog(String functionDropdownList, String reason) throws GeneralLeanFtException {
        cssTransactionReport.validateTransactioTeportDialog(functionDropdownList,reason);
    }

    public void validateTransactioReportFile() {
        cssTransactionReport.validateTransactioReportFile();
    }

    public void buildLogString(int blockSize, String value, String anEnumCssTransactionReport) throws  GeneralLeanFtException{
        cssTransactionReport.buildLogString(blockSize,value,anEnumCssTransactionReport);
    }

    public void rightClickCMBoardAndSelectMenu(String rightClickMenu)throws GeneralLeanFtException{
        cssCrewMemberBoard.rightClickCMBoardAndSelectMenu(rightClickMenu);
    }
    public void readTransactionReport(String path) {
        cssTransactionReport.readTransactionReport(path);
    }

    public void ValidatePdfContentAfterReadingTransactionReport (String searchTxt) throws  GeneralLeanFtException{
        cssTransactionReport.validatePdfContentAfterReadingTransactionReport(searchTxt);
    }
}


