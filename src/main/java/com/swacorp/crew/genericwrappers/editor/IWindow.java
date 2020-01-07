package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;

public interface IWindow<T extends com.hp.lft.sdk.winforms.Window> {

     final Logger logger = Logger.getLogger(IWindow.class);

    default void  CloseWindowIfExist(T obj, int timeout) throws GeneralLeanFtException {
        try {
            if (obj.exists(timeout)) {
                obj.close();
            }
            logger.info("Window closed successfully..");
        }catch(GeneralLeanFtException e){
            logger.error("Error while closing the window");
            e.printStackTrace();
        }
    }

}
