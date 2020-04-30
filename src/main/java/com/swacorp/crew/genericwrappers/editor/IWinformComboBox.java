package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;


public interface IWinformComboBox<T extends com.hp.lft.sdk.winforms.ComboBox> {

    ReportUtil report = new ReportUtil();
    Logger logger = Logger.getLogger(IEditor.class);
    String objectType = "Type of object: ";
    String SuccessfullyClicked = "btnClick successful: ";
    String ClickUnsuccessful = "btnClick unsuccessful ";

    default void selectValue(Window mainWin, T obj, String value){
        try{
            obj.select(value);
            report.reportLeanFT(mainWin, "pass", obj.getDisplayName()+" dropdown has selected the value: '"+value+"'");
        } catch (GeneralLeanFtException e) {
            report.reportLeanFT(mainWin, "fail", obj.getDisplayName()+" dropdown could not select the value: '"+value+"'");
            e.printStackTrace();
        }

    }

}
