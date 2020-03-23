Option Explicit
Dim WshShell: Set WshShell = WScript.CreateObject("WScript.Shell")

WScript.Sleep 5000
WshShell.SendKeys "{DOWN}"
WScript.Sleep 5000
WshShell.SendKeys "{DOWN}"
WScript.Sleep 5000
WshShell.SendKeys "{ENTER}"