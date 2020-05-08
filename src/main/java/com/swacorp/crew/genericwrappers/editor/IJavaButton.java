package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;

public interface IJavaButton<T extends com.hp.lft.sdk.java.Button> {

    static final Logger log = Logger.getLogger(IJavaButton.class);

    default void waitForButtonToVisible(T obj, int timeout) throws GeneralLeanFtException {
        long t=0;
        try {
            do {
                Thread.sleep(1);
                t++;
            }while((!obj.exists()) && t < timeout);
        }catch(Exception e){
            log.error("Error in waitForButtonToVisible: "+e);
        }

    }

}
