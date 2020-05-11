package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.java.Window;
import org.apache.log4j.Logger;

public interface IJavaWindow<T extends com.hp.lft.sdk.java.Window> {


    default void closeWindowIfExist(T obj, int timeout) throws GeneralLeanFtException {
        Logger logger = Logger.getLogger(IJavaWindow.class);
        try {
            if (obj.exists(timeout)) {
                obj.close();
            }
            logger.info("Window closed successfully..");
        }catch(GeneralLeanFtException e){
            logger.error("Error while closing the window"+e);
        }
    }

    default void minimiseWindowIfExist(T obj) throws GeneralLeanFtException {
        Logger logger = Logger.getLogger(IJavaWindow.class);
        try {
            if (obj.exists()) {
                obj.minimize();
            }
            logger.info("Window minimized successfully..");
        }catch(GeneralLeanFtException e){
            logger.error("Error while minimizing the window"+e);
        }
    }

    default void maximizeWindowIfExist(T obj) throws GeneralLeanFtException {
        Logger logger = Logger.getLogger(IJavaWindow.class);
        try {
            if (obj.exists()) {
                obj.maximize();
            }
            logger.info("Window maximized successfully..");
        }catch(GeneralLeanFtException e){
            logger.error("Error while maximizing the window"+e);
        }
    }

    default void waitForJavaWindowTillVisible(Window obj, int timeout) throws GeneralLeanFtException {
        Logger logger = Logger.getLogger(IJavaWindow.class);
        long t=0;
        try {
            do {
                Thread.sleep(1);
                t++;
            }while((!obj.exists()) && t < timeout);
        }catch(Exception e){
            logger.error("waitForJavaWindowTillVisible error"+e);
        }

    }

}
