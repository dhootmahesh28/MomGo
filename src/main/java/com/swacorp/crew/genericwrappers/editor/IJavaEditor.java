package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;


public interface IJavaEditor<T extends com.hp.lft.sdk.java.Editor> {

     final Logger log = Logger.getLogger(IJavaEditor.class);
     String objectType = "Type of object: ";
     String dataSuccessfullySet = "Data is successfully set into: ";
     String errorWhileSettingData = "Error occured while setting data ";

    default void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);

            log.info(dataSuccessfullySet +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            log.error(errorWhileSettingData +obj.getObjectName()+ objectType +obj.getClass(), e);
            e.printStackTrace();
        }
    }

    default void  WaitEditorTillVisible(T obj, int timeout) throws GeneralLeanFtException {
        long t=0;
        try {
            do {
                Thread.sleep(1);
                t++;
            }while((!obj.exists()) && t < timeout);
        }catch(Exception e){
              e.printStackTrace();
        }

    }

}
