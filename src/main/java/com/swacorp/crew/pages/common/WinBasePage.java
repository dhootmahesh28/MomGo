package com.swacorp.crew.pages.common;


import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.genericwrappers.editor.*;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoCSS;
import com.swacorp.crew.sharedrepository.tsr.ObjectRepoTRiM;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.swacorp.crew.utils.*;

public class WinBasePage implements IWinformEditor,IWinformComboBox,LeanftGenericMethods, IWinformButton, IWinformWindow, IJavaEditor, IJavaWindow, IJavaButton {

    ReportUtil report = new ReportUtil();
    public final static Logger loggerWinBasePage = Logger.getLogger(WinBasePage.class);
    private final int TIMEOUT_SS = 60;
    public static Window homeWindow;
    public ObjectRepoTRiM trimObjectRepo = null;
    public ObjectRepoCSS cssObjectRepo = null;

    public WinBasePage(){
        if(cssObjectRepo == null){
            loggerWinBasePage.info("Initializing 'TRiM Application Model' wrapper instances..");
            try {
                cssObjectRepo = new ObjectRepoCSS();
            } catch (GeneralLeanFtException e) {
                e.printStackTrace();
            }
        }
        if(trimObjectRepo == null){
            loggerWinBasePage.info("Initializing 'CSS Application Model' wrapper instances..");
            try {
                trimObjectRepo = new ObjectRepoTRiM();
            } catch (GeneralLeanFtException e) {
                e.printStackTrace();
            }
        }
    }

    public void flushObjects() throws  GeneralLeanFtException{
        if (trimObjectRepo.tRiMTrainingResourceManagerSouthwestWindow().exists()) {
            trimObjectRepo.tRiMTrainingResourceManagerSouthwestWindow().close();
        }
    }

    public void isWindowExist(Window window, String title) throws GeneralLeanFtException {

        if(window.exists(TIMEOUT_SS)) {
            report.reportLeanFT(homeWindow, "pass", window.getWindowTitleRegExp() + " Window Displayed");
        }
        else{
            report.reportNonWeb("error",title + " Window does not exist");
        }
    }

    public void isObjectExist(TestObject object) throws GeneralLeanFtException {

        if(!object.exists(TIMEOUT_SS)) {
            report.reportNonWeb("error",/*window.getObjectName() + */" Object does not exist");
        }
    }
    public void setDynamicData(String varName, String value) {
        Long id = Thread.currentThread().getId();
        TestUtil.dynamicDataMap.put(varName + "-" + id, value);
    }

    public String getDynamicData(String varName) {
        Long id = Thread.currentThread().getId();
        return TestUtil.dynamicDataMap.get(varName + "-" + id);
    }
}
