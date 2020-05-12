package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.constants.MessageConstants;
import org.apache.log4j.Logger;


public interface IJavaEditor<T extends com.hp.lft.sdk.java.Editor> {

    default void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        Logger log = Logger.getLogger(IJavaEditor.class);
        try {
            obj.setText(data);

            log.info(MessageConstants.DATA_IS_SUCCESSFULLY_SET_INTO +obj.getObjectName()+ MessageConstants.OBJECT_TYPE +obj.getClass());
        }catch(Exception e){
            log.error(MessageConstants.ERROR_WHILE_SETTING_DATA +obj.getObjectName()+ MessageConstants.OBJECT_TYPE +obj.getClass(), e);
        }
    }

    default void waitEditorTillVisible(T obj, int timeout) throws GeneralLeanFtException {
        Logger log = Logger.getLogger(IJavaEditor.class);
        long t=0;
        try {
            do {
                Thread.sleep(1);
                t++;
            }while((!obj.exists()) && t < timeout);
        }catch(Exception e){
            log.error("waitEditorTillVisible", e);
        }

    }

}
