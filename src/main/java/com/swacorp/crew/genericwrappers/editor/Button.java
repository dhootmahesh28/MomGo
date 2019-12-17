package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import org.apache.log4j.Logger;
import com.hp.lft.sdk.winforms.EditField;

public class Button<T extends com.hp.lft.sdk.winforms.Button> {

    private final Logger logger = Logger.getLogger(Editor.class);
    private String objectType = "Type of object: ";
    private String SuccessfullyClicked = "Click successful: ";
    private String ClickUnsuccessful = "Click unsuccessful ";

    public void  setTextInEditBox(T obj) throws GeneralLeanFtException {
        try {
            obj.click();

            logger.info(SuccessfullyClicked +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(ClickUnsuccessful +obj.getObjectName()+ objectType +obj.getClass(), e);
        }
    }

}
