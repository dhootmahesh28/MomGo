package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;
import com.swacorp.crew.pages.common.MessageConstants;

public interface IJavaButton<T extends com.hp.lft.sdk.java.Button> {

     final Logger log = Logger.getLogger(IJavaButton.class);

    default void  WaitForButtonToVisible(T obj, int timeout) throws GeneralLeanFtException {
        long t=0;
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
