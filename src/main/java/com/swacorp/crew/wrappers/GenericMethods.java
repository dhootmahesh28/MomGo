package com.swacorp.crew.wrappers;

import com.hp.lft.sdk.TestObject;
import org.apache.log4j.Logger;
import com.swacorp.crew.interfaces.IGenericMethods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.swacorp.crew.pages.common.CommonFormats.MONTH_DAY_YEAR;

public class GenericMethods implements IGenericMethods {

    public final static Logger LOGGER = Logger.getLogger(GenericMethods.class);
    public boolean verifyObjectExist(TestObject o){
        try {
            if (o.exists()) {
                System.out.println("Object found: "+o.getDisplayName());
                LOGGER.info("Object exists on application, "+o.getDisplayName());
                return true;
            }
        }
        catch (Exception e){
            LOGGER.error("Object does not exist: "+o.getDisplayName());

        }
        return false;
    }

    @Override
    public boolean VerifyObjectExist(TestObject o, boolean existance) {
        return false;
    }

    @Override
    public boolean VerifyObjectExist(TestObject o) {
        return false;
    }

    @Override
    public boolean VerifyObjectDisplayed(TestObject o, boolean existance) {
        return false;
    }

    @Override
    public boolean WaitProperty(TestObject o, Long timeout, String timeUnit) {
        return false;
    }
}
