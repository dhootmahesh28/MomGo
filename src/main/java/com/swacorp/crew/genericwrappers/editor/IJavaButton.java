package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.apache.log4j.Logger;


public interface IJavaButton<T extends com.hp.lft.sdk.java.Button> {

     final Logger log = Logger.getLogger(IJavaButton.class);
     String objectType = "Type of object: ";
     String dataSuccessfullySet = "Data is successfully set into: ";
     String errorWhileSettingData = "Error occured while setting data ";

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
