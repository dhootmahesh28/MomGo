<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>ObjectRepoCSS</className>
    <objects>
        <object type="Dialog" technology="Java">
            <name>cssLogin</name>
            <codeName>loginDialog</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Title" type="string" isRegExp="false">Login</property>
                </properties>
            </identification>
            <children>
                <object type="Editor" technology="Java">
                    <name>User Name:</name>
                    <codeName>userNameEditor</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AttachedText" type="string" isRegExp="false">User Name:</property>
                        </properties>
                    </identification>
                </object>
                <object type="Editor" technology="Java">
                    <name>Password:</name>
                    <codeName>passwordEditor</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AttachedText" type="string" isRegExp="false">Password:</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Java">
                    <name>Login</name>
                    <codeName>loginButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Label" type="string" isRegExp="false">Login</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Window" technology="Java">
            <name>1_CSS Client Home Page</name>
            <codeName>pLTCSSClient36SatSuperUserDefaultDesktopWindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Title" type="string" isRegExp="false">(PLT)CSS Client 36 sat - Super User - Default Desktop</property>
                    <property name="Label" type="string" isRegExp="false">(PLT)CSS Client 36 sat - Super User - Default Desktop</property>
                </properties>
            </identification>
            <children>
                <object type="Dialog" technology="Java">
                    <name>Open Crew Member Schedule</name>
                    <codeName>openCrewMemberScheduleDialog</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Open Crew Member Schedule</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Java">
                            <name>Continue</name>
                            <codeName>continueButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Continue</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>Employee Name or ID</name>
                            <codeName>employeeNameOrIDEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Employee Name or ID</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="ToolBar" technology="Java">
                    <name>CSSToolBar</name>
                    <codeName>cSSToolBar</codeName>
                    <description></description>
                    <identification/>
                </object>
                <object type="Button" technology="Java">
                    <name>Crew_Member_Schedule</name>
                    <codeName>crewMemberScheduleButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AttachedText" type="string" isRegExp="false">Crew_Member_Schedule</property>
                            <property name="Label" type="string" isRegExp="false">Crew_Member_Schedule</property>
                            <property name="ObjectName" type="string" isRegExp="false">Crew Board</property>
                            <property name="Path" type="string" isRegExp="false">CrewBoardButton;CSSToolBar;JPanel;JLayeredPane;JRootPane;WnApplicationContainer;</property>
                            <property name="TagName" type="string" isRegExp="false">Crew_Member_Schedule</property>
                        </properties>
                    </identification>
                </object>
                <object type="Dialog" technology="Java">
                    <name>Transaction Report Dialog</name>
                    <codeName>transactionReportDialog</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Transaction Report Dialog</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="CheckBox" technology="Java">
                            <name>Crew Member:</name>
                            <codeName>crewMemberCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Crew Member:</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>Reason:</name>
                            <codeName>reasonCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Reason:</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="UiObject" technology="Java">
                    <name>WnDesktopMDI$1</name>
                    <codeName>wnDesktopMDI1UiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="NativeClass" type="string" isRegExp="false">com.swacorp.aaf.ui.uac.container.internal.WnDesktopMDI$1</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Button" technology="Java">
            <name>CMButton</name>
            <codeName>cMButton</codeName>
            <identification>
                <properties>
                    <property name="Label" type="string" isRegExp="false">Crew Board Window</property>
                </properties>
            </identification>
        </object>
        <object type="Window" technology="Java">
            <name>CssMainWindow</name>
            <codeName>CssMainWindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Path" type="string" isRegExp="false">WnApplicationContainer;</property>
                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.aaf.ui.uac.container.WnApplicationContainer</property>
                </properties>
                <index>0</index>
            </identification>
            <children>
                <object type="Button" technology="Java">
                    <name>Crew_Member_Schedule</name>
                    <codeName>crewMemberScheduleButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Label" type="string" isRegExp="false">Crew_Member_Schedule</property>
                        </properties>
                        <index>0</index>
                    </identification>
                </object>
                <object type="ToolBar" technology="Java">
                    <name>CSSToolBar</name>
                    <codeName>cSSToolBar</codeName>
                    <description></description>
                    <identification/>
                </object>
                <object type="Menu" technology="Java">
                    <name>Crews</name>
                    <codeName>crewsMenu</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Label" type="string" isRegExp="false">Crews</property>
                        </properties>
                    </identification>
                </object>
                <object type="Menu" technology="Java">
                    <name>Crew Member Schedule...</name>
                    <codeName>crewMemberSchedule</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Label" type="string" isRegExp="false">Crew Member Schedule...</property>
                        </properties>
                    </identification>
                </object>
                <object type="Dialog" technology="Java">
                    <name>Open Crew Member Schedule</name>
                    <codeName>openCrewMemberScheduleDialog</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Open Crew Member Schedule</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Java">
                            <name>Continue</name>
                            <codeName>continueButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Continue</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>Employee Name or ID</name>
                            <codeName>cmbEmployeeNameOrIDEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Employee Name or ID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Java">
                            <name>Base</name>
                            <codeName>btnDropDownEmployeeSelect</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Base</property>
                                    <property name="Label" type="string" isRegExp="false"></property>
                                    <property name="NativeClass" type="string" isRegExp="false">com.incors.plaf.alloy.dw</property>
                                </properties>
                                <index>0</index>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="InternalFrame" technology="Java">
                    <name>CMBoard</name>
                    <codeName>frameCMBoard</codeName>
                    <description></description>
                    <identification>
                        <index>0</index>
                    </identification>
                    <children>
                        <object type="UiObject" technology="Java">
                            <name>X</name>
                            <codeName>xUiObject</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="true">com.swacorp.css.screens.crewboard.CrewBoardPiece</property>
                                </properties>
                                <index>3</index>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>ModifyScheduleArrow</name>
                            <codeName>modifyScheduleArrowCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">whiteArrow_up</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>TripStartDate</name>
                            <codeName>tripStartDateEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Start Date</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>TripEndDate</name>
                            <codeName>tripEndDateEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">End Date</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>TripStartTime</name>
                            <codeName>tripStartTimeEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Time</property>
                                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.common.CSSTimeField</property>
                                </properties>
                                <index>0</index>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>TripEndTime</name>
                            <codeName>tripEndTimeEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Time</property>
                                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.common.CSSTimeField</property>
                                </properties>
                                <index>1</index>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>Credit</name>
                            <codeName>creditEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Credit</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Java">
                            <name>Run_Transaction_Report</name>
                            <codeName>runTransactionReportButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Run_Transaction_Report</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Java">
                            <name>PDF_Icon</name>
                            <codeName>pDFIconButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">PDF_Icon</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Menu" technology="Java">
                            <name>CMBoardripRghtClkMenu</name>
                            <codeName>cMBoardripRghtClkMenu</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Transactions</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="InternalFrame" technology="Java">
                    <name>Crew Member Schedule - 90870 CAHOON CLAY **INTL**</name>
                    <codeName>crewMemberSchedule90870CAHOONCLAYINTLInternalFrame</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Crew Member Schedule - 90870 CAHOON CLAY **INTL**</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="CheckBox" technology="Java">
                            <name>ModifyScheduleArrow</name>
                            <codeName>modifyScheduleArrowCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">whiteArrow_up</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="InternalFrame" technology="Java">
                    <name>Crew Member Schedule - 90870 CAHOON CLAY **INTL**1</name>
                    <codeName>crewMemberSchedule90870CAHOONCLAYINTLInternalFrame1</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Crew Member Schedule - 90870 CAHOON CLAY **INTL**</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="CheckBox" technology="Java">
                            <name>ModifyScheduleArrow</name>
                            <codeName>modifyScheduleArrowCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">whiteArrow_up</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="InternalFrame" technology="Java">
                    <name>Trip Details</name>
                    <codeName>frameTrimDetails</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Path" type="string" isRegExp="false">WnCapturedMDIWindow;WnDesktopMDI$1;WnDesktopMDI;WnDesktopArea;JPanel;JLayeredPane;JRootPane;WnApplicationContainer;</property>
                        </properties>
                        <index>0</index>
                    </identification>
                    <children>
                        <object type="Table" technology="Java">
                            <name>firstTable</name>
                            <codeName>firstTable</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.trip.TripDetailsTable</property>
                                </properties>
                                <index>0</index>
                            </identification>
                        </object>
                        <object type="Table" technology="Java">
                            <name>SecondTable</name>
                            <codeName>secondTable</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.trip.TripDetailsTable</property>
                                </properties>
                                <index>1</index>
                            </identification>
                        </object>
                        <object type="Table" technology="Java">
                            <name>ThirdTable</name>
                            <codeName>thirdTable</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.trip.TripDetailsTable</property>
                                </properties>
                                <index>2</index>
                            </identification>
                        </object>
                        <object type="InternalFrame" technology="Java">
                            <name>Crew Member Schedule - 90870 CAHOON CLAY **INTL**</name>
                            <codeName>crewMemberSchedule90870CAHOONCLAYINTLInternalFrame</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Title" type="string" isRegExp="false">Crew Member Schedule - 90870 CAHOON CLAY **INTL**</property>
                                </properties>
                            </identification>
                            <children>
                                <object type="CheckBox" technology="Java">
                                    <name>ModifyScheduleArrow</name>
                                    <codeName>modifyScheduleArrowCheckBox</codeName>
                                    <description></description>
                                    <identification>
                                        <properties>
                                            <property name="AttachedText" type="string" isRegExp="false">whiteArrow_up</property>
                                        </properties>
                                    </identification>
                                </object>
                            </children>
                        </object>
                        <object type="Table" technology="Java">
                            <name>CrewMembers</name>
                            <codeName>crewMembersTable</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">javax.swing.JTable</property>
                                    <property name="TagName" type="string" isRegExp="false">Crew Members</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="InternalFrame" technology="Java">
                    <name>Transaction Report</name>
                    <codeName>transactionReportInternalFrame</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Transaction Report</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Java">
                            <name>PDF_Icon</name>
                            <codeName>pDFIconButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">PDF_Icon</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Label" technology="Java">
                            <name>report</name>
                            <codeName>report</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false"></property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Menu" technology="Java">
                    <name>Reports</name>
                    <codeName>reportsMenu</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Label" type="string" isRegExp="false">Reports</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Menu" technology="Java">
                            <name>Transaction Report</name>
                            <codeName>transactionReportMenu</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Transaction Report...</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Dialog" technology="Java">
                    <name>Transaction Report Dialog</name>
                    <codeName>transactionReportDialog</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Transaction Report Dialog</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="CheckBox" technology="Java">
                            <name>Crew Member:</name>
                            <codeName>crewMemberCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Crew Member:</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>Reason:</name>
                            <codeName>reasonCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Reason:</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>Functions:</name>
                            <codeName>functionsCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Functions:</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>FunctionDropdownList</name>
                            <codeName>FunctionDropdownList</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">(CSS Login ID)</property>
                                    <property name="NativeClass" type="string" isRegExp="false">javax.swing.plaf.basic.BasicComboBoxEditor$BorderlessTextField</property>
                                </properties>
                                <index>2</index>
                            </identification>
                        </object>
                        <object type="Table" technology="Java">
                            <name>ReasonTable</name>
                            <codeName>ReasonTable</codeName>
                            <description></description>
                            <identification/>
                        </object>
                        <object type="Button" technology="Java">
                            <name>SelectFunctionButton</name>
                            <codeName>SelectFunctionButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Ending Date</property>
                                    <property name="Label" type="string" isRegExp="false"></property>
                                    <property name="NativeClass" type="string" isRegExp="false">com.incors.plaf.alloy.dw</property>
                                </properties>
                                <index>0</index>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>BeginningDate</name>
                            <codeName>beginningDateEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Beginning Date</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>EndingDate</name>
                            <codeName>endingDateEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Ending Date</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Java">
                            <name>btnRunReport</name>
                            <codeName>runReportButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Run Report</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Java">
                            <name>btnCancel</name>
                            <codeName>cancel</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Cancel</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>Last</name>
                            <codeName>lastCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Last:</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Menu" technology="Java">
                    <name>OpenTime</name>
                    <codeName>openTimeMenu</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Label" type="string" isRegExp="false">Open Time</property>
                            <property name="NativeClass" type="string" isRegExp="false">javax.swing.JMenu</property>
                            <property name="Path" type="string" isRegExp="false">JMenu;JMenuBar;JLayeredPane;JRootPane;WnApplicationContainer;</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Java">
                            <name>getOpenTripsButton</name>
                            <codeName>getOpenTripsButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Get Open Trips</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Menu" technology="Java">
                            <name>ViewOpenTime...</name>
                            <codeName>viewOpenTimeMenu</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">View Open Time...</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="InternalFrame" technology="Java">
                    <name>OpenTimeInternalFrame</name>
                    <codeName>openTimeInternalFrame</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="true">Open Time.*</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="CheckBox" technology="Java">
                            <name>whiteArrowExpandedState</name>
                            <codeName>whiteArrowExpandedState</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">whiteArrow_up</property>
                                    <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.common.UpDownButton</property>
                                    <property name="State" type="enum" isRegExp="false">Unchecked</property>
                                </properties>
                                <index>0</index>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>BWI</name>
                            <codeName>bWICheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">BWI</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>From</name>
                            <codeName>fromEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">From</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="Java">
                            <name>To</name>
                            <codeName>toEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">To</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>BaseCheckbox</name>
                            <codeName>BaseCheckbox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">ATL</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>captainCheckBox</name>
                            <codeName>captainCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Captain</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Java">
                            <name>getOpenTripsButton</name>
                            <codeName>getOpenTripsButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Label" type="string" isRegExp="false">Get Open Trips</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="UiObject" technology="Java">
                    <name>CrewBoardPiece</name>
                    <codeName>crewBoardPieceUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="NativeClass" type="string" isRegExp="false">com.swacorp.css.screens.crewboard.CrewBoardPiece</property>
                        </properties>
                        <index>1</index>
                    </identification>
                </object>
            </children>
        </object>
        <object type="List" technology="Java">
            <name>BasicComboPopup$1</name>
            <codeName>cmbEmployeeListDropdown</codeName>
            <description></description>
            <identification/>
        </object>
        <object type="Page" technology="Web">
            <name>OQS - Record Keeping</name>
            <codeName>oQSRecordKeepingPage</codeName>
            <description></description>
            <identification/>
            <children>
                <object type="WebElement" technology="Web">
                    <name>ROSA</name>
                    <codeName>rOSAWebElement</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="InnerHTML" type="string" isRegExp="false">ROSA</property>
                            <property name="InnerText" type="string" isRegExp="false">ROSA</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Page" technology="Web">
            <name>OQS - Flight Operations</name>
            <codeName>oQSFlightOperationsPage</codeName>
            <description></description>
            <identification/>
            <children>
                <object type="EditField" technology="Web">
                    <name>crewSearchValue</name>
                    <codeName>crewSearchValueEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Name" type="string" isRegExp="false">crewSearchValue</property>
                            <property name="TagName" type="string" isRegExp="false">INPUT</property>
                            <property name="Type" type="string" isRegExp="false">text</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Web">
                    <name>Search</name>
                    <codeName>searchButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ButtonType" type="string" isRegExp="false">button</property>
                            <property name="Name" type="string" isRegExp="false">Search</property>
                            <property name="TagName" type="string" isRegExp="false">BUTTON</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
