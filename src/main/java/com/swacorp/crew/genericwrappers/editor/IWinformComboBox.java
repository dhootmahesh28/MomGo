package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;


public interface IWinformComboBox<T extends com.hp.lft.sdk.winforms.ComboBox> {

    default void selectValue(Window mainWin, T obj, String value){
        ReportUtil report = new ReportUtil();
        Logger logger = Logger.getLogger(IWinformComboBox.class);
        try{
            obj.select(value);
            report.reportLeanFT(mainWin, "pass", obj.getDisplayName()+" dropdown has selected the value: '"+value+"'");
        } catch (GeneralLeanFtException e) {
            logger.error(e);
        }
    }
}
