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
    public  Map<String, String> RunTimeDataFromApp =new  HashMap<String, String>();
}



/*
Command to create a java file of a tsrx file
C:\Program Files (x86)\HPE\Unified Functional Testing\Tools\AppModelCodeGenerator\Java>java -jar appmodel-code-generator.jar C:\Users\x257093\Desktop\Work\gitRepo\qmo-crew-automation\src\main\java\com\swacorp\crew\tsrx\MainObjectRepoTrim.tsrx -package com.swacorp.crew.sharedrepository.tsr -inPlace
*/

