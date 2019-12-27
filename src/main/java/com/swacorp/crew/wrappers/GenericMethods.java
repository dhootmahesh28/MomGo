package com.swacorp.crew.wrappers;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;
import org.apache.log4j.Logger;
import com.swacorp.crew.interfaces.IGenericMethods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.swacorp.crew.pages.common.CommonFormats.MONTH_DAY_YEAR;

public interface  GenericMethods extends IGenericMethods {

    public final static Logger LOGGER = Logger.getLogger(GenericMethods.class);

    @Override
    default boolean VerifyObjectExist(TestObject o, boolean existance) {
        boolean objExist;
        try {
            /*if (o.exists()) {
                LOGGER.info("Object exists on application, "+o.getDisplayName());
                objExist = true;
            }else{
                objExist = false;
            }*/

            objExist = o.exists() ? true: false;

            return (objExist && existance) ? true : false;

        }
        catch (Exception e){
            LOGGER.error("Object does not exist: "+o.getDisplayName());
        }
        return false;
    }

    @Override
    default boolean VerifyObjectExist(TestObject o) {
        return false;
    }

    @Override
    default boolean VerifyObjectDisplayed(TestObject o, boolean existance) {
        return false;
    }

    @Override
    default boolean WaitProperty(TestObject o, int timeout, String timeUnit) {
        return false;
    }

    @Override
    default boolean WaitProperty(TestObject o, String propName, String propValue, int timeOut) throws GeneralLeanFtException, InterruptedException {
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
    default boolean Highlight(TestObject o) {
        try {
                o.highlight();
                LOGGER.info("Object highlighted, "+o.getDisplayName());
                return true;
        }
        catch (Exception e){
            LOGGER.error("Object does not exist: "+o.getDisplayName());
            //e.printStackTrace();
            return false;
        }

    }

}
