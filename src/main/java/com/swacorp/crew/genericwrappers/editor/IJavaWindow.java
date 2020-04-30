package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.java.Window;
import org.apache.log4j.Logger;

public interface IJavaWindow<T extends com.hp.lft.sdk.java.Window> {

     final Logger logger = Logger.getLogger(IJavaWindow.class);

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

    default void  MinimiseWindowIfExist(T obj) throws GeneralLeanFtException {
        try {
            if (obj.exists()) {
                obj.minimize();
            }
            logger.info("Window minimized successfully..");
        }catch(GeneralLeanFtException e){
            logger.error("Error while minimizing the window");
            e.printStackTrace();
        }
    }

    default void  MaximizeWindowIfExist(T obj) throws GeneralLeanFtException {
        try {
            if (obj.exists()) {
                obj.maximize();
            }
            logger.info("Window maximized successfully..");
        }catch(GeneralLeanFtException e){
            logger.error("Error while maximizing the window");
            e.printStackTrace();
        }
    }

    default void  WaitForJavaWindowTillVisible(Window obj, int timeout) throws GeneralLeanFtException {
        long t=0;
        //long x  = (Number)timeout;
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
