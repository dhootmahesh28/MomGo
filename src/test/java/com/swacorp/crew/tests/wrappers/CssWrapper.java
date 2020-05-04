package com.swacorp.crew.tests.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.constants.EnumRosa;
import com.swacorp.crew.pages.css.*;
import com.swacorp.crew.pages.rosa.RosaHome;
import com.swacorp.crew.pages.rosa.RosaLogin;
import com.swacorp.crew.pages.rosa.RosaSolutionQueue;
import com.swacorp.crew.pages.sasi.SasiLogin;
import com.swacorp.crew.utils.TestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CssWrapper extends TestUtil {
    CssLogin cssLogin;
    CssHome cssHome;
    CssCrewMemberBoard cssCrewMemberBoard;
    CssOpenTime cssOpenTime;
    CssTransactionReport cssTransactionReport;

    public CssWrapper(){
        cssLogin = new CssLogin();
        cssHome = new CssHome();
        cssCrewMemberBoard = new CssCrewMemberBoard();
        cssOpenTime = new CssOpenTime();
        cssTransactionReport = new CssTransactionReport();
    }

    public void loginCss() throws GeneralLeanFtException, IOException {
        cssLogin.loginCss();
    }

    public void openCMBoard(String empCode) {
        cssCrewMemberBoard.openCMBoard(empCode);
    }

    public void selectTripOnCMBoard(String tripStartDateROSA, String tripEndDateROSA, String TripDetails, boolean tripShouldBeAvailableOnCMBoard, boolean readTripDetails, boolean readTransactionReport) throws GeneralLeanFtException, CloneNotSupportedException {
        cssCrewMemberBoard.selectTripOnCMBoard(tripStartDateROSA,tripEndDateROSA,TripDetails,tripShouldBeAvailableOnCMBoard, readTripDetails, readTransactionReport);
    }

    public void ValidateNotifiedOption(String notifiedOption) throws GeneralLeanFtException{
        cssCrewMemberBoard.ValidateNotifiedOption(notifiedOption);
    }

    public void ValidateCredits() throws GeneralLeanFtException {
        cssCrewMemberBoard.ValidateCredits();
    }

    public void NavigateToOT() throws GeneralLeanFtException{
        cssOpenTime.NavigateToOT();
    }

    public void selectOTfilters(String filter) throws GeneralLeanFtException {
        cssOpenTime.selectOTfilters(filter);
    }

    public void readOTTripDetails()  {
        cssOpenTime.readOTTripDetails();
    }

    public void NavigateToTransactionReport() throws GeneralLeanFtException{
        cssTransactionReport.NavigateToTransactionReport();
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

    public void rightClickCMBoardAndSelectMenu(String rightClickMenu){
        cssHome.rightClickCMBoardAndSelectMenu(rightClickMenu);
    }
    public void readTransactionReport(String path) {
        cssHome.readTransactionReport(path);
    }

    public void ValidatePdfContentAfterReadingTransactionReport (String searchTxt) throws  GeneralLeanFtException{
        cssHome.ValidatePdfContentAfterReadingTransactionReport(searchTxt);
    }
}


