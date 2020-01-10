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
        <object type="Window" technology="WPF">
            <name>HPE MyFlight Sample Application</name>
            <codeName>hPEMyFlightSampleApplicationWindow</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="FullType" type="string" isRegExp="false">window</property>
                    <property name="ObjectName" type="string" isRegExp="false">HPE MyFlight Sample Application</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">HPE MyFlight Sample Application</property>
                </properties>
            </identification>
            <children>
                <object type="EditField" technology="WPF">
                    <name>agentName</name>
                    <codeName>agentNameEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">agentName</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="WPF">
                    <name>password</name>
                    <codeName>passwordEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">password</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="WPF">
                    <name>OK</name>
                    <codeName>oKButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">okButton</property>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Dialog" technology="Java">
            <name>Login</name>
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
            </children>
        </object>
    </objects>
</applicationModel>
