package com.swacorp.crew.pages.common;


import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.RegExpProperty;
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
    public static final Logger loggerWinBasePage = Logger.getLogger(WinBasePage.class);
    private static final int TIMEOUT_SS = 60;
    public ObjectRepoTRiM trimObjectRepo = null;
    public ObjectRepoCSS cssObjectRepo = null;

    public WinBasePage(){
        if(cssObjectRepo == null){
            loggerWinBasePage.info("Initializing 'TRiM Application Model' wrapper instances..");
            try {
                cssObjectRepo = new ObjectRepoCSS();
            } catch (GeneralLeanFtException e) {
                loggerWinBasePage.error(e);
            }
        }
        if (trimObjectRepo == null) {
            loggerWinBasePage.info("Initializing 'CSS Application Model' wrapper instances..");
            try {
                trimObjectRepo = new ObjectRepoTRiM();
            } catch (GeneralLeanFtException e) {
                loggerWinBasePage.error(e);
            }
        }
    }

    public void flushObjects() throws  GeneralLeanFtException{
        if (trimObjectRepo.tRiMTrainingResourceManagerSouthwestWindow().exists()) {
            trimObjectRepo.tRiMTrainingResourceManagerSouthwestWindow().close();
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

    public void activateBrowser(String title) throws GeneralLeanFtException {
        Window rosaWindow = Desktop.describe(Window.class, new WindowDescription.Builder()
                .childWindow(false)
                .ownedWindow(false)
                .text(new RegExpProperty(title + ".*")).build());
        rosaWindow.activate();
    }
}
