package com.swacorp.crew.pages.common;

public enum Wait {
    NO_WAIT(0),
    WAIT_ONE_SECOND(1) ,
    WAIT_FIVE_SECOND(5),
    WAIT_ONE_MINUTE(60),
    REGISTER_SCREEN(3);

    private int waitUnit;

    Wait(int waitUnit){
        this.waitUnit = waitUnit;
    }

    public int getID(){
        return waitUnit;
    }
}
