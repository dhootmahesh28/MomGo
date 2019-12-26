package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.MouseButton;
import org.apache.log4j.Logger;

public interface IButton<T extends com.hp.lft.sdk.winforms.Button> {

    Logger logger = Logger.getLogger(IEditor.class);
    String objectType = "Type of object: ";
    String SuccessfullyClicked = "btnClick successful: ";
    String ClickUnsuccessful = "btnClick unsuccessful ";

    default void btnClick(T obj) throws GeneralLeanFtException {
        try {
            obj.click(MouseButton.LEFT);

            logger.info(SuccessfullyClicked +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(ClickUnsuccessful +obj.getObjectName()+ objectType +obj.getClass(), e);
        }
    }

    default void VerifyButtonEnabled(T obj) throws GeneralLeanFtException {
        try {
            obj.click();

            logger.info(SuccessfullyClicked +obj.getObjectName()+ objectType +obj.getClass());
        }catch(Exception e){
            logger.error(ClickUnsuccessful +obj.getObjectName()+ objectType +obj.getClass(), e);
        }
    }

}
