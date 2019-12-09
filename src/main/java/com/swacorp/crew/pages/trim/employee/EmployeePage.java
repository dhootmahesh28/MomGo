package com.swacorp.crew.pages.trim.employee;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.common.LeanFTBasePage;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;

public class EmployeePage extends LeanFTBasePage {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(EmployeePage.class);
    public static final String EMPLOYEE_WINDOW = "Employee";
    private final String EMPLOYEE_NUMBER = "txtEmployeeNumber";

    public void verifyEmployee(String empNum) throws GeneralLeanFtException {
        if(getText(EMPLOYEE_NUMBER).compareToIgnoreCase(empNum)==0)
            report.report("pass", "Emplyee: " + empNum + " verification is successful");
        else
            report.report("fail", "Emplyee: " + empNum + " verification is NOT successful");
    }

    public void closeEmployeeWindow() throws GeneralLeanFtException {
        closeWindow(EMPLOYEE_WINDOW);
    }

    @Override
    protected void loadObjectRepo() {

    }
}
