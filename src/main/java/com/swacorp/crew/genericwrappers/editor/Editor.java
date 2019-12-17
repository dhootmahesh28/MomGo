package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;


public class Editor<T extends com.hp.lft.sdk.winforms.EditField & com.hp.lft.sdk.java.Editor > {

    private final Logger logger = Logger.getLogger(Editor.class);
    private String objectType = "Type of object: ";
    private String dataSuccessfullySet = "Data is successfully set into: ";
    private String errorWhileSettingData = "Error occured while setting data ";

    public void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);

            logger.info(dataSuccessfullySet +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(errorWhileSettingData +obj.getObjectName()+ objectType +obj.getClass(), e);
            e.printStackTrace();
        }
    }

}
