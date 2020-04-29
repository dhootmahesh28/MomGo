package com.swacorp.crew.pages.common;


import java.util.HashMap;
import java.util.Map;

/*
Keep all the static variables here. these variables are not final.
All the global variables and flas are declared here.
*/

public  class Constant {
    public static boolean INSTIALIZED = false;

    //This map will be used by each test script to hold the run time app data.
    public static final Map<String, String> RunTimeDataFromApp =new  HashMap<String, String>();
}

