package com.swacorp.crew.pages.trim.FindEmployee;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.pages.common.LeanFTBasePage;
import com.swacorp.crew.pages.trim.employee.EmployeePage;
import com.swacorp.crew.utils.ReportUtil;
import org.apache.log4j.Logger;

public class FindEmployeePage extends LeanFTBasePage {
    ReportUtil report = new ReportUtil();
    private final Logger LOGGER = Logger.getLogger(FindEmployeePage.class);
    public static final String FIND_EMPLOYEE_WINDOW = "Find Employee";
    private final String EMPLOYEE_NUMBER_TEXT = "txtSearchEmpNumber";
    private final String SHOW_EMPLOYEE_DETAILS_BUTTON = "btnShowEmployeeDetails";
    private final String EMPLOYEE_WINDOW = EmployeePage.EMPLOYEE_WINDOW;

    public void findEmployee(String empNum) throws GeneralLeanFtException {
        enterText(EMPLOYEE_NUMBER_TEXT, empNum);
        buttonClick(SHOW_EMPLOYEE_DETAILS_BUTTON);
        setWindow(EMPLOYEE_WINDOW);
    }

    public void closeFindEmployeeWindow() throws GeneralLeanFtException {
        closeWindow(FIND_EMPLOYEE_WINDOW);
    }

}
