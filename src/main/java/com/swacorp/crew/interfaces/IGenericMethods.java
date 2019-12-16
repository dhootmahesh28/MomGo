package com.swacorp.crew.interfaces;

import com.hp.lft.sdk.TestObject;

public interface IGenericMethods {

    public boolean VerifyObjectExist(TestObject o, boolean existance);

    public boolean VerifyObjectExist(TestObject o);

    public boolean VerifyObjectDisplayed(TestObject o, boolean existance);

    public boolean WaitProperty(TestObject o,Long timeout, String timeUnit );

}
