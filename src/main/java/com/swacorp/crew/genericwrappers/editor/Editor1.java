package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import com.hp.lft.sdk.winforms.EditField;
import org.apache.log4j.Logger;

public class Editor1 {
    private final Logger logger = Logger.getLogger(Editor1.class);
    private String objectType = "Type of object: ";
    private String dataSuccessfullySet = "Data is successfully set into: ";
    private String errorWhileSettingData = "Error occured while setting data ";
    public <T extends EditField> void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);
            logger.info(dataSuccessfullySet +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(errorWhileSettingData +obj.getObjectName()+ objectType +obj.getClass(), e);
        }
    }

    public <T extends com.hp.lft.sdk.java.Editor> void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);
            logger.info(dataSuccessfullySet +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(errorWhileSettingData +obj.getObjectName()+ objectType +obj.getClass(), e);
        }
    }


    public void  setTextInEditBox(TestObject o, String data) throws GeneralLeanFtException {
        EditField obj = (EditField) o;
        try {
            obj.setText(data);
            logger.info(dataSuccessfullySet +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(errorWhileSettingData +obj.getObjectName()+ objectType +obj.getClass(), e);
        }
    }
}
