package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;


public interface IEditor<T extends com.hp.lft.sdk.winforms.EditField> {

     final Logger log = Logger.getLogger(IEditor.class);
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

}
