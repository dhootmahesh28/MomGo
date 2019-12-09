<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>ApplicationModel</className>
    <objects>
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
    </objects>
</applicationModel>
