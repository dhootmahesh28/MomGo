package com.swacorp.crew.pages.common;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import org.apache.log4j.Logger;
import com.swacorp.crew.genericwrappers.editor.IGenericMethods;


public interface LeanftGenericMethods extends IGenericMethods {



    @Override
    default boolean verifyObjectExist(TestObject o, boolean existance) {
        Logger log = Logger.getLogger(LeanftGenericMethods.class);
        try {
            return (o.exists() && existance);
        }
        catch (Exception e){
            log.error("Object does not exist: "+e);
        }
        return false;
    }

    @Override
    default boolean verifyObjectExist(TestObject o) {
        return false;
    }

    @Override
    default boolean verifyObjectDisplayed(TestObject o, boolean existance) {
        return false;
    }

    @Override
    default boolean waitProperty(TestObject o, int timeout, String timeUnit) {
        return false;
    }

    @Override
    default boolean waitProperty(TestObject o, String propName, String propValue, int timeOut) throws GeneralLeanFtException, InterruptedException {
        int counter = timeOut * 60 * 1000;
        if (!o.exists() && (counter < timeOut )){
            Thread.sleep(1000);
        }
        return o.exists();
    }

    @Override
    default boolean highlight(TestObject o) {
        Logger log = Logger.getLogger(LeanftGenericMethods.class);
        try {
                o.highlight();
                log.info("Object highlighted, "+o.getDisplayName());
                return true;
        }
        catch (Exception e){
            log.error("Object does not exist: "+e);
            return false;
        }

    }

}
