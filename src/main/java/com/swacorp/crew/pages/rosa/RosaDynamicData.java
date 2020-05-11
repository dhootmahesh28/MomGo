package com.swacorp.crew.pages.rosa;

import com.swacorp.crew.pages.common.BasePage;
import com.swacorp.crew.pages.constants.ApplicationConstantsCss;
import com.swacorp.crew.utils.ReportUtil;
import com.swacorp.crew.utils.TestUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class RosaDynamicData extends BasePage {

    private static final Logger loggerRosaDynData = Logger.getLogger(RosaDynamicData.class);
    ReportUtil report = new ReportUtil();
    String currentSystemDate;
    private static Map<String, Map<String, ArrayList<String[]>>> masterHM;
    private static ArrayList<String[]> training = new ArrayList<>();
    private static ArrayList<String[]>  triptopull = new ArrayList<>();
    private static String rosaempID;


    public static ArrayList<String[]> getTraining() {
        return training;
    }

    public static ArrayList<String[]> getTriptopull() {
        return triptopull;
    }

    public static String getRosaempID() {
        return rosaempID;
    }


    public static boolean readMasterHM(){
        int i=1;
        boolean retVal = false;
        try{
            if((rosaempID == null)){
                masterHM = TestUtil.getRosaMasterHM();
                if (masterHM == null){
                    return false;
                }else {

                    for (Map.Entry<String, Map<String, ArrayList<String[]>>> lstEmpIds : masterHM.entrySet()) {
                        rosaempID = lstEmpIds.getKey();
                        Map<String, ArrayList<String[]>> keyEmpId = lstEmpIds.getValue();
                        training = keyEmpId.get("trng");
                        triptopull = keyEmpId.get("triptopull");

                        if (Integer.compare(i, ApplicationConstantsCss.NUMBER_OF_EMPLOYEE)==1) {
                            break;
                        }
                        i++;
                    }
                    if ((rosaempID == null)) {
                        return false;
                    } else {
                        retVal = true;
                    }
                }
            }
        }catch(Exception e){
            loggerRosaDynData.error(e);
            throw e;
        }
    return retVal;
    }

}
