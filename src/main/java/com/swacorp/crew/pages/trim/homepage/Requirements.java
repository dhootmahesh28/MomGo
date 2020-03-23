package com.swacorp.crew.pages.trim.homepage;


import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.winforms.*;
import com.swacorp.crew.pages.common.WinBasePage;
import com.swacorp.crew.sharedrepository.tsr.MainObjectRepoTrim;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;
import com.swacorp.crew.utils.ReportStatus;
import com.swacorp.tsr.trim.lib.requirements.NewRequirementTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requirements extends WinBasePage{
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(TrimHomePageAM.class);
    MainObjectRepoTrim lftObjects =null;
    public HashMap<String, String> pgMap = new HashMap<>();
    private String empNumberFromApp = "";
    private String fldFirstNameFromApp = "";
    private boolean searchFound;

    public String getDynamicDataFromPage(String key){
        return pgMap.get(key);
    }


    //Window winFindEmployee = lftObjects.tRiMTrainingResourceManagerSouthwestWindow().findEmployeeWindow();
    public Requirements()  {
        lftObjects = super.lftObjectRepo;
    }

    private int VerifyWindowExist(Window o) throws  GeneralLeanFtException{
        int retVal = 1;
        if (o.exists()){
            retVal =0;
            return retVal;
        }
        return retVal;
    }


    public void CreateNewReqTemplate(String[] testData) throws GeneralLeanFtException {

        new NewRequirementTemplate(lftObjects).createNewRequirementTemplate(testData);

    }


}

