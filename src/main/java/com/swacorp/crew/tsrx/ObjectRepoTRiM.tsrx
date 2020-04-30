<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>ObjectRepoTRiM</className>
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
                        <object type="Button" technology="WinForms">
                            <name>Show Employee Schedule</name>
                            <codeName>showEmployeeScheduleButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnShowEmployeeSchedule</property>
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
                <object type="Window" technology="WinForms">
                    <name>Add Employee Requirement</name>
                    <codeName>addEmployeeRequirementWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmAddEmployeeRequirement</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Add Employee Requirement</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboEmployeeID</name>
                            <codeName>cboEmployeeIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboEmployeeID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Editor" technology="WinForms">
                            <name>txtEmployeeNumbers</name>
                            <codeName>txtEmployeeNumbersEditor</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtEmployeeNumbers</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboRequirementID</name>
                            <codeName>cboRequirementIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboRequirementID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="WinForms">
                            <name>Add Requirement to Due Pilot List</name>
                            <codeName>addRequirementToDuePilotListButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnAdd</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="UiObject" technology="WinForms">
                    <name>SwfObject</name>
                    <codeName>swfObjectUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="FullType" type="string" isRegExp="false">System.Windows.Forms.MdiClient</property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                </object>
                <object type="Window" technology="WinForms">
                    <name>Auto Populate</name>
                    <codeName>autoPopulateWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmAutoPopulate</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Auto Populate</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Table" technology="WinForms">
                            <name>templateDataGrid</name>
                            <codeName>templateDataGridTable</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">templateDataGrid</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="TabControl" technology="WinForms">
                            <name>tabPopulate</name>
                            <codeName>tabPopulateTabControl</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">tabPopulate</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="WinForms">
                            <name>Start</name>
                            <codeName>startButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnStart</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboEquip</name>
                            <codeName>cboEquipComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboEquip</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Window" technology="WinForms">
                    <name>Requirement Template</name>
                    <codeName>requirementTemplateWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmRequirements</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false"> Automation</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="WinForms">
                            <name>Make Duplicate</name>
                            <codeName>makeDuplicateButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnMakeDuplicate</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="TreeView" technology="WinForms">
                    <name>tvSchedPlanner</name>
                    <codeName>tvTreeView</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">tv</property>
                        </properties>
                    </identification>
                </object>
                <object type="Dialog" technology="Standard Windows">
                    <name>Finished</name>
                    <codeName>finishedDialog</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">false</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">true</property>
                            <property name="NativeClass" type="string" isRegExp="false">#32770</property>
                            <property name="Text" type="string" isRegExp="false">Finished</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Standard Windows">
                            <name>OK</name>
                            <codeName>oKButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">Button</property>
                                    <property name="Text" type="string" isRegExp="false">OK</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Static" technology="Standard Windows">
                            <name>RequirementCreatedMsg</name>
                            <codeName>requirementSCreatedNoErrorsStatic</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">Static</property>
                                    <property name="WindowId" type="int" isRegExp="false">65535</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Window" technology="WinForms">
                    <name>Instructor/Employee Schedule</name>
                    <codeName>instructorEmployeeScheduleWindow</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmResourceSched</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Table" technology="WinForms">
                            <name>dg</name>
                            <codeName>dgTable</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">dg</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Window" technology="WinForms">
                    <name>EmployeeDetailsWindow</name>
                    <codeName>EmployeeDetails</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmEmployees</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboPrimaryEquipID</name>
                            <codeName>cboPrimaryEquipIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboPrimaryEquipID</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="ComboBox" technology="WinForms">
                            <name>cboPrimaryCrewStatusID</name>
                            <codeName>cboPrimaryCrewStatusIDComboBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">cboPrimaryCrewStatusID</property>
                                </properties>
                            </identification>
                        </object>
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
                            <name>txtFName</name>
                            <codeName>txtFNameEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtFName</property>
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
                        <object type="Button" technology="WinForms">
                            <name>Save and Close</name>
                            <codeName>saveAndCloseButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">btnSaveClose</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="EditField" technology="WinForms">
                            <name>txtLName</name>
                            <codeName>txtLNameEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">txtLName</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="WinForms">
                            <name>Inactive</name>
                            <codeName>inactiveCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ObjectName" type="string" isRegExp="false">chkHide</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="ComboBox" technology="WinForms">
                    <name>cmbSchedulePlanner</name>
                    <codeName>cbSubFilterComboBox</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">cbSubFilter</property>
                        </properties>
                    </identification>
                </object>
                <object type="Window" technology="WinForms">
                    <name>WEST, WAS (4545)</name>
                    <codeName>wESTWAS4545Window</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">true</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                            <property name="ObjectName" type="string" isRegExp="false">frmEmployees</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">WEST, WAS (4545)</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Window" technology="WinForms">
            <name>Progress</name>
            <codeName>progressWindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="IsChildWindow" type="boolean" isRegExp="false">false</property>
                    <property name="IsOwnedWindow" type="boolean" isRegExp="false">false</property>
                    <property name="ObjectName" type="string" isRegExp="false">frmPopulateProgressChecker</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">Progress</property>
                </properties>
            </identification>
            <children>
                <object type="Label" technology="WinForms">
                    <name>Populate Complete.</name>
                    <codeName>populateCompleteLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">infoLabel</property>
                            <property name="Text" type="string" isRegExp="false">Populate Complete.</property>
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
                <object type="Frame" technology="Web">
                    <name>compArea</name>
                    <codeName>compAreaFrame</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="Name" type="string" isRegExp="false">compArea</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="EditField" technology="Web">
                            <name>WebEdit</name>
                            <codeName>webEditEditField</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="Name" type="string" isRegExp="false">WebEdit</property>
                                    <property name="TagName" type="string" isRegExp="false">INPUT</property>
                                    <property name="Type" type="string" isRegExp="false">text</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="Button" technology="Web">
                            <name>OK</name>
                            <codeName>oKButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="ButtonType" type="string" isRegExp="false">submit</property>
                                    <property name="Name" type="string" isRegExp="false">OK</property>
                                    <property name="TagName" type="string" isRegExp="false">BUTTON</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Web">
                            <name>WebCheckBox</name>
                            <codeName>webCheckBox</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="AccessibilityName" type="string" isRegExp="false"></property>
                                    <property name="Name" type="string" isRegExp="false"></property>
                                    <property name="Role" type="string" isRegExp="false"></property>
                                    <property name="TagName" type="string" isRegExp="false">INPUT</property>
                                    <property name="Type" type="string" isRegExp="false">checkbox</property>
                                </properties>
                                <index>7</index>
                            </identification>
                        </object>
                    </children>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
