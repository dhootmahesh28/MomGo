package com.swacorp.crew.pages.common;

public class LftMethodCall<T> {
    T obj;
    public LftMethodCall(T o){
        obj = o;
    }

    public void callMethod(){
        System.out.println(obj);
    }
}
