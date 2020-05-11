package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;
import com.swacorp.crew.pages.constants.MessageConstants;

public interface IWinformButton<T extends com.hp.lft.sdk.winforms.Button> {

    default void btnClick(T obj) throws GeneralLeanFtException {
        Logger log = Logger.getLogger(IWinformEditor.class);
        try {
            obj.click();
            log.info(MessageConstants.MSG_CLICKED_SUCCESSFULLY +obj.getDisplayName()+ MessageConstants.OBJECT_TYPE +obj.getClass());
        }catch(Exception e){
            log.error(e);
        }
    }
}
