package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;


public interface IJavaEditor<T extends com.hp.lft.sdk.java.Editor> {

     final Logger log = Logger.getLogger(IJavaEditor.class);
     String OBJECT_TYPE = "Type of object: ";
     String DATA_SUCCESSFULLY_SET = "Data is successfully set into: ";
     String ERROR_OCCURED_WHILE_SETTING_DATA = "Error occured while setting data ";

    default void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);

            log.info(DATA_SUCCESSFULLY_SET +obj.getObjectName()+ OBJECT_TYPE +obj.getClass());
        }catch(Exception e){
            log.error(ERROR_OCCURED_WHILE_SETTING_DATA +obj.getObjectName()+ OBJECT_TYPE +obj.getClass(), e);
        }
    }

    default void waitEditorTillVisible(T obj, int timeout) throws GeneralLeanFtException {
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
