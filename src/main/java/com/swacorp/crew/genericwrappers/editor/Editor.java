package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import com.hp.lft.sdk.winforms.*;
import com.hp.lft.sdk.winforms.EditField;
import org.apache.log4j.Logger;

//public class Editor<T extends TestObject>  {
public class Editor  {
    private final Logger LOGGER = Logger.getLogger(Editor.class);
    private String ObjectType = "Type of object: ";
    private String DataSuccessfullySet = "Data is successfully set into: ";
    private String ErrorWhileSettingData = "Error occured while setting data ";
    public <T extends com.hp.lft.sdk.winforms.EditField> void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);
            LOGGER.info(DataSuccessfullySet+obj.getObjectName()+ObjectType+obj.getClass());
        }catch(Exception e){
            LOGGER.error(ErrorWhileSettingData+obj.getObjectName()+ObjectType+obj.getClass(), e);
        }
    }

    public <T extends com.hp.lft.sdk.java.Editor> void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);
            LOGGER.info(DataSuccessfullySet+obj.getObjectName()+ObjectType+obj.getClass());
        }catch(Exception e){
            LOGGER.error(ErrorWhileSettingData+obj.getObjectName()+ObjectType+obj.getClass(), e);
        }
    }

}
