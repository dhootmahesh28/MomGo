package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import com.hp.lft.sdk.winforms.*;
import com.hp.lft.sdk.winforms.EditField;
import org.apache.log4j.Logger;

//public class Editor<T extends TestObject>  {
public class Editor  {
    private final Logger LOGGER = Logger.getLogger(Editor.class);
    public <T extends com.hp.lft.sdk.winforms.EditField> void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);
            LOGGER.info("Data is successfully set into: "+obj.getObjectName()+"Type of object: "+obj.getClass());
        }catch(Exception e){
            LOGGER.error("Data is successfully set into: "+obj.getObjectName()+"Type of object: "+obj.getClass(), e);
        }
    }

    public <T extends com.hp.lft.sdk.java.Editor> void  setTextInEditBox(T obj, String data) throws GeneralLeanFtException {
        try {
            obj.setText(data);
            LOGGER.info("Data is successfully set into: "+obj.getObjectName()+"Type of object: "+obj.getClass());
        }catch(Exception e){
            LOGGER.error("Data is successfully set into: "+obj.getObjectName()+"Type of object: "+obj.getClass(), e);
        }
    }

}
