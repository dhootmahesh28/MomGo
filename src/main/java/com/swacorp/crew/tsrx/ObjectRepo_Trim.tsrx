<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>ObjectRepo_Trim</className>
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
    </objects>
</applicationModel>
