package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;
import com.hp.lft.sdk.java.Button;

public interface IJavaButton<T extends Button> {

     default void waitForButtonToVisible(T obj, int timeout) throws GeneralLeanFtException {
         Logger log = Logger.getLogger(IJavaEditor.class);
         long t=0;
         try {
             do {
                 Thread.sleep(1);
                 t++;
             }while((!obj.exists(1)) && t < timeout);
         }catch(Exception e){

             log.error("Error in waitForButtonToVisible: "+e);
         }
    }

}
