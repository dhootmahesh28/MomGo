package com.swacorp.crew.pages.common;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
//import com.hp.lft.sdk.winforms.*;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.genericwrappers.editor.IEditor;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.wrappers.GenericMethods;
import org.apache.log4j.Logger;
import org.junit.Assert;
import com.swacorp.crew.genericwrappers.editor.IButton;

public abstract class WinBasePage implements IEditor,GenericMethods, IButton{

    ReportUtil report = new ReportUtil();
    public final static Logger LOGGER = Logger.getLogger(WinBasePage.class);
    private final int TIMEOUT_SS = 60;
    public static Window homeWindow;
    public static Window windowObject;
    public MainObjectRepoTrim or = null;

    public void setParent(Window object) {
        this.homeWindow = object;
        this.windowObject = object;
    }

    {
        if(or == null){
            LOGGER.info("Initializing wrapper instances..");
            try {
                or = new MainObjectRepoTrim();
            } catch (GeneralLeanFtException e) {
                e.printStackTrace();
            }
        }
    }

    protected void FlushObjects() throws  GeneralLeanFtException{
        or.tRiMTrainingResourceManagerSouthwestWindow().close();
    }

    public void setWindow(Window object) {
        this.windowObject = object;
    }

    public void setParentWindow(String windowTitle) throws GeneralLeanFtException {
       try {
           LOGGER.info("Before seParentWindow::" + windowTitle);
           Window object = Desktop.describe(Window.class, new WindowDescription.Builder()
               .windowTitleRegExp(windowTitle).build());
           setParent(object);
           isWindowExist(object, windowTitle);
           LOGGER.info("After seParentWindow::" + windowTitle);
       } catch (Exception e) {
           LOGGER.info("Exception in seParentWindow ::" + windowTitle);
           e.printStackTrace();
           Assert.fail("Exception occurred while setting parent window with title: "+windowTitle+" & exception is "+e.getMessage());
       }
    }
    public void setWindow(String windowTitle) throws GeneralLeanFtException {
        try {
            LOGGER.info("Before setWindow::" + windowTitle);
            Window object = homeWindow.describe(Window.class, new WindowDescription.Builder()
                    .windowTitleRegExp(windowTitle).build());
            setWindow(object);
            isWindowExist(object, windowTitle);
            LOGGER.info("After setWindow::" + windowTitle);
        } catch (Exception e) {
            LOGGER.info("Exception in setWindow ::" + windowTitle);
            e.printStackTrace();
            Assert.fail("Exception occurred while setting window with title: "+windowTitle+" & exception is "+e.getMessage());
        }
    }

    public void toolBarSendKeys(String objectName, String key) {
        try {
            LOGGER.info("Before click in toolBarClick::" + objectName);
            ToolBar toolBar = windowObject.describe(ToolBar.class, new ToolBarDescription.Builder()
                    .objectName(objectName).build());
            isObjectExist(toolBar);
            toolBar.sendKeys(key);
            Thread.sleep(2000);
        } catch (Exception e) {
            LOGGER.info("Exception in toolBarClick ::" + objectName);
            e.printStackTrace();
            Assert.fail("Exception occurred while performing sendKeys on toolbar "+objectName+" & exception is "+e.getMessage());
        }
    }
    public void buttonClick(String objectName) {
        /*try {
            LOGGER.info("Before buttonClick in buttonClick::" + objectName);
            IButton button = windowObject.describe(Button.class, new ButtonDescription.Builder()
                    .objectName(objectName).build());
            isObjectExist(button);
            report.reportLeanFT(homeWindow, "INFO", " Window state before button click");
            button.click();
            LOGGER.info("After buttonClick in buttonClick::" + objectName);
        } catch (Exception e) {
            LOGGER.info("Exception in buttonClick ::" + objectName);
            e.printStackTrace();
            Assert.fail("Exception occurred while clicking on btn "+objectName+" & exception is "+e.getMessage());
        }*/
    }

    public void enterText(String objectName, String text) {
        try {
            LOGGER.info("Before enterText::" + objectName + ", with text::" + text);
            EditField editField = windowObject.describe(EditField.class, new ButtonDescription.Builder()
                    .objectName(objectName).build());
            isObjectExist(editField);
            editField.setText(text);
            LOGGER.info("After enterText::" + objectName + ", with text::" + text);
        } catch (Exception e) {
            LOGGER.info("Exception in enterText ::" + objectName);
            e.printStackTrace();
            Assert.fail("Exception occurred while entering text in edit box "+objectName+" & exception is "+e.getMessage());
        }
    }

    public void enterSecureText(String objectName, String text) {
        try {
            LOGGER.info("Before enterSecureText::" + objectName + ", with text::" + text);
            EditField editField = windowObject.describe(EditField.class, new ButtonDescription.Builder()
                    .objectName(objectName).build());
            isObjectExist(editField);
            editField.setSecure(text);
            LOGGER.info("After enterSecureText::" + objectName + ", with text::" + text);
        } catch (Exception e) {
            LOGGER.info("Exception in enterSecureText ::" + objectName);
            e.printStackTrace();
            Assert.fail("Exception occurred while entering secured text in edit box "+objectName+" & exception is "+e.getMessage());
        }
    }

    public String getText(String objectName) throws GeneralLeanFtException {
        EditField editField = null;
        try {
            LOGGER.info("Before getText::" + objectName);
            editField = windowObject.describe(EditField.class, new ButtonDescription.Builder()
                    .objectName(objectName).build());
            isObjectExist(editField);
            LOGGER.info("After getText::" + objectName);
        } catch (Exception e) {
            LOGGER.info("Exception in getText ::" + objectName);
            e.printStackTrace();
            Assert.fail("Exception occurred while capturing text from edit box "+objectName+" & exception is "+e.getMessage());
        }
        return editField.getText();
    }
    public void windowClick() throws GeneralLeanFtException {
        try {
            LOGGER.info("Before click in clickWindow");
            windowObject.click();
            LOGGER.info("After click in clickWindow");
        } catch (Exception e) {
            LOGGER.info("Exception in clickWindow");
            e.printStackTrace();
            Assert.fail("Exception occurred while clicking on home window "+ windowObject.getWindowTitleRegExp() +" & exception is "+e.getMessage());
        }
    }

    public void closeWindow(String windowTitle) throws GeneralLeanFtException {
        Window window = homeWindow.describe(Window.class, new WindowDescription.Builder().windowTitleRegExp(windowTitle).build());
        window.close();
    }

    public void closeHomeWindow() throws GeneralLeanFtException {
        homeWindow.close();
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


        if(object.exists(TIMEOUT_SS)==false) {
            report.reportNonWeb("error",/*window.getObjectName() + */" Object does not exist");
        }
    }


/*    public boolean Highlight(TestObject o) {
        try {
            o.highlight();
            System.out.println("Object highlighted: "+o.getDisplayName());
            LOGGER.info("Object highlighted, "+o.getDisplayName());

            return true;

        }
        catch (Exception e){
            LOGGER.error("Object does not exist: "+o.getDisplayName());

        }
        return false;
    }*/
}
