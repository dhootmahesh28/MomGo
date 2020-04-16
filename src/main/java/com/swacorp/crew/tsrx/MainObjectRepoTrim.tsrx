<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>MainObjectRepoTrim</className>
    <objects>
        <object type="Window" technology="WinForms">
            <name>Login to Southwest</name>
            <codeName>loginToSouthwestWindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="IsChildWindow" type="boolean" isRegExp="false">false</property>
                    <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                    <property name="ObjectName" type="string" isRegExp="false">frmLogin</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">Login to Southwest</property>
                </properties>
            </identification>
            <children>
                <object type="EditField" technology="WinForms">
                    <name>txtUserID</name>
                    <codeName>txtUserIDEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">txtUserID</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="WinForms">
                    <name>txtPassword</name>
                    <codeName>txtPasswordEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">txtPassword</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="WinForms">
                    <name>Login</name>
                    <codeName>btnLoginButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">btnOK</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="CheckBox" technology="WinForms">
            <name>Use Active Directory</name>
            <codeName>useActiveDirectoryCheckBox</codeName>
            <description></description>
            <identification/>
        </object>
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
        <object type="Window" technology="WinForms">
            <name>TRiM - Training Resource Manager:  Southwest</name>
            <codeName>tRiMTrainingResourceManagerSouthwestWindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="IsChildWindow" type="boolean" isRegExp="false">false</property>
                    <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                    <property name="ObjectName" type="string" isRegExp="false">frmMain</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">TRiM</property>
                </properties>
            </identification>
            <children>
                <object type="UiObject" technology="WinForms">
                    <name>Main Menu</name>
                    <codeName>mainMenuUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">barMainMenu</property>
                        </properties>
                    </identification>
                </object>
                <object type="Window" technology="WinForms">
                    <name>Find Employee</name>
                    <codeName>findEmployeeWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmFindEmployee</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Find Employee</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="EditField" technology="WinForms">
                            <name>txtSearchEmpNumber</name>
                            <codeName>txtSearchEmpNumberEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtSearchEmpNumber</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="WinForms">
                            <name>Show Employee Details</name>
                            <codeName>showEmployeeDetailsButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnShowEmployeeDetails</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ListBox" technology="WinForms">
                            <name>lstSearch</name>
                            <codeName>lstSearchListBox1</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="FullNamePath" type="string" isRegExp="false">lstSearch;GroupBox1;frmFindEmployee;;frmMain</property>
                                    <property name="ObjectName" type="string" isRegExp="false">lstSearch</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Window" technology="WinForms">
                    <name>Employee</name>
                    <codeName>employeeWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmQualEmployee</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Employee</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="EditField" technology="WinForms">
                            <name>txtEmployeeNumber</name>
                            <codeName>txtEmployeeNumberEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtEmployeeNumber</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtFirstName</name>
                            <codeName>txtFirstNameEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtFirstName</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Calendar" technology="WinForms">
                            <name>dtpDateOfBirth</name>
                            <codeName>dtpDateOfBirthCalendar</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">dtpDateOfBirth</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Active</name>
                            <codeName>activeCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkActive</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Window" technology="WinForms">
                    <name>Requirements</name>
                    <codeName>requirementsWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmRequirements</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Requirements</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="EditField" technology="WinForms">
                            <name>txtRequirementName</name>
                            <codeName>txtRequirementNameEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtRequirementName</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboReqTypeID</name>
                            <codeName>cboReqTypeIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboReqTypeID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboDefaultCustomer</name>
                            <codeName>cboDefaultCustomerComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboDefaultCustomer</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Disabled</name>
                            <codeName>disabledCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkDisabled</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Favorite</name>
                            <codeName>favoriteCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cbxFavorite</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="WinForms">
                            <name>txtCourseDefinitionDescription</name>
                            <codeName>txtCourseDefinitionDescriptionEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtCourseDefinitionDescription</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboEquipID</name>
                            <codeName>cboEquipIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboEquipID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboAlsoSatisfiesReqID</name>
                            <codeName>cboAlsoSatisfiesReqIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboAlsoSatisfiesReqID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="WinForms">
                            <name>txtRequirementNotes</name>
                            <codeName>txtRequirementNotesEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtRequirementNotes</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Suppress Notes</name>
                            <codeName>suppressNotesCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkSuppressRequirementNotes</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>This Requirement Template is a Course</name>
                            <codeName>thisRequirementTemplateIsACourseCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkIsCourse</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Show Error when scheduled after due date.</name>
                            <codeName>showErrorWhenScheduledAfterDueDateCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkShowErrorWhenSchedAfterDueDate</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Allow Self Registration</name>
                            <codeName>allowSelfRegistrationCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkAllowSelfRegistration</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtMaxSpanDays</name>
                            <codeName>txtMaxSpanDaysEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtMaxSpanDays</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtDefaultBackgroundColor</name>
                            <codeName>txtDefaultBackgroundColorEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtDefaultBackgroundColor</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Display Series Label</name>
                            <codeName>displaySeriesLabelCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkSeriesLabel</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtNormalNumberOfStudents</name>
                            <codeName>txtNormalNumberOfStudentsEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtNormalNumberOfStudents</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Do Not Auto Schedule</name>
                            <codeName>doNotAutoScheduleCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkDoNotAutoSched</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Auto Sched in Existing Slots Only</name>
                            <codeName>autoSchedInExistingSlotsOnlyCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkAutoSchedInExistingSlotsOnly</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Do not split accross multiple Resources</name>
                            <codeName>doNotSplitAccrossMultipleResourcesCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkisForceKeepSameResource</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Pull ahead if required</name>
                            <codeName>pullAheadIfRequiredCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkAutoSchedPullFromNextMonthIfRequired</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtAutoSchedPriority</name>
                            <codeName>txtAutoSchedPriorityEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtAutoSchedPriority</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtAutoSchedPullFromNextMonthDays</name>
                            <codeName>txtAutoSchedPullFromNextMonthDaysEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtAutoSchedPullFromNextMonthDays</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="WinForms">
                            <name>txtRequirementSabreCode</name>
                            <codeName>txtRequirementSabreCodeEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtRequirementSabreCode</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtPBSCode</name>
                            <codeName>txtPBSCodeEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtPBSCode</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="WinForms">
                            <name>Save</name>
                            <codeName>saveButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnSave</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="TreeView" technology="WinForms">
                    <name>tv</name>
                    <codeName>tvTreeView</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">tv</property>
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
    </objects>
</applicationModel>
