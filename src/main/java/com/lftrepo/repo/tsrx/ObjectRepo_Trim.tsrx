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
    </objects>
</applicationModel>
