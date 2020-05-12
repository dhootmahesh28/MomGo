package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;

public interface IGenericMethods {

    public boolean verifyObjectExist(TestObject o, boolean existance);
    public boolean verifyObjectExist(TestObject o);
    public boolean verifyObjectDisplayed(TestObject o, boolean existance);
    public boolean waitProperty(TestObject o, int timeOut, String timeUnit );
    public boolean waitProperty(TestObject o, String propName, String propValue, int timeOut ) throws GeneralLeanFtException, InterruptedException;
    public boolean highlight(TestObject o);
}
