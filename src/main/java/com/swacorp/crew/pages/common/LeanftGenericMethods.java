package com.swacorp.crew.pages.common;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import org.apache.log4j.Logger;
import com.swacorp.crew.genericwrappers.editor.IGenericMethods;


public interface LeanftGenericMethods extends IGenericMethods {

    public static final Logger log = Logger.getLogger(LeanftGenericMethods.class);

    @Override
    default boolean verifyObjectExist(TestObject o, boolean existance) {
        boolean objExist;
        try {
            objExist = o.exists() ? true: false;

            return (objExist && existance) ? true : false;
        }
        catch (Exception e){
            log.error("Object does not exist: "+o.getDisplayName());
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
        String tempProp;
        int counter = timeOut * 60 * 1000;
        if (!o.exists() && (counter < timeOut )){
            Thread.sleep(1000);
        }

        if (o.exists())
            return true;
        else
            return false;
    }

    @Override
    default boolean highlight(TestObject o) {
        try {
                o.highlight();
                log.info("Object highlighted, "+o.getDisplayName());
                return true;
        }
        catch (Exception e){
            log.error("Object does not exist: "+o.getDisplayName());
            //e.printStackTrace();
            return false;
        }

    }

}
