package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.constants.MessageConstants;
import org.apache.log4j.Logger;
import com.hp.lft.sdk.winforms.EditField;

public interface IWinformEditor<T extends EditField> {


    default void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        Logger log = Logger.getLogger(IWinformEditor.class);
        try {
            obj.setText(data);

            log.info(MessageConstants.DATA_IS_SUCCESSFULLY_SET_INTO +obj.getObjectName()+ MessageConstants.OBJECT_TYPE +obj.getClass());
        }catch(Exception e){
            log.error(MessageConstants.ERROR_WHILE_SETTING_DATA +obj.getObjectName()+ MessageConstants.OBJECT_TYPE  +obj.getClass(), e);
            log.error(e.getMessage());
        }
    }

    default void  waitEditorTillVisible(T obj,long timeout) throws GeneralLeanFtException {
        Logger log = Logger.getLogger(IWinformEditor.class);
        long t=0;
        try {

            do {
                Thread.sleep(1);
                t++;
            }while((!obj.isVisible()) && t < timeout);
        }catch(Exception e){
            log.error(e);
        }
    }
}
