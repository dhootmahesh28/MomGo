package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.TestObject;

public interface IGenericMethods {

    public boolean VerifyObjectExist(TestObject o, boolean existance);
    public boolean VerifyObjectExist(TestObject o);
    public boolean VerifyObjectDisplayed(TestObject o, boolean existance);
    public boolean WaitProperty(TestObject o,int timeOut, String timeUnit );
    public boolean WaitProperty(TestObject o,String propName, String propValue, int timeOut ) throws GeneralLeanFtException, InterruptedException;
    public boolean Highlight(TestObject o);
}
