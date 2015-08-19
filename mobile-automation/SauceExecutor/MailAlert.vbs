set shell=createobject("wscript.shell")
sAutomationPath=shell.Environment("USER").Item("Automation_Path")
'sProjectName=shell.Environment("USER").Item("Project_Name")
'instanceName=shell.Environment("USER").Item("InstanceName")
currentUser=shell.Environment("USER").Item("CurrentUser")
'ModuleName=shell.Environment("USER").Item("ModuleName")    
temp= shell.ExpandEnvironmentStrings("%Temp%")
TypeOfExecution = Wscript.Arguments(0)
xmlParameters = Wscript.Arguments(1)
ModuleName = Wscript.Arguments(2)
MailTo=Wscript.Arguments(3)

arr = split(xmlParameters,",")
sProjectName=arr(0)
instanceName=arr(1)


If Hour(now) >= 12 Then
    ampm="PM"
Else
   ampm="AM"
End If


strDate = Day(Date())& MonthName(month(date()))&Year(Date())&"_" & Hour(now)&"_"&Minute(Now)&ampm
destinationPath=sAutomationPath&sProjectName&"\ANT\apache-ant-1.9.3\bin\"&ModuleName&"_"&strDate&".pdf"
'msgbox destinationPath
Set FS = CreateObject("Scripting.FileSystemObject")
FS.CopyFile temp&"\output\index.pdf", destinationPath

EmailSubject = "AutomatedMailAlert" &"_"& ModuleName&"_"&strDate
EmailBody = "VMWare Selenium Scripts Execution Report"

Const EmailFrom = "rrouthu@prolifics.com"
EmailFromName = "VMWare Automation Execution Report_" & instanceName &"_"& ModuleName&"_"&currentUser

'Const EmailTo ="Malleshwar.Cheruku@prolifics.com,paramesh.bandari@prolifics.com,nareshkumar.akkala@prolifics.com"
'Const EmailTo ="Surinder.Singh@prolifics.com,Malleshwar.Cheruku@prolifics.com,vpamidi@teslamotors.com,Shaik.Liyakath@prolifics.com,paramesh.bandari@prolifics.com"
'Const EmailTo ="Malleshwar.Cheruku@prolifics.com,vpamidi@teslamotors.com,Shaik.Liyakath@prolifics.com,paramesh.bandari@prolifics.com,nareshkumar.akkala@prolifics.com,ajaybob.talpur@prolifics.com"
'Const EmailTo ="Shaik.Liyakath@prolifics.com,naga.konakalla@prolifics.com,vpamidi@teslamotors.com,saritha.renukunta@prolifics.com,Malleshwar.Cheruku@prolifics.com,suresh.sakamuri@prolifics.com,nakkala@teslamotors.com,janaiah.gera@prolifics.com,smallela@teslamotors.com​"
'Const EmailTo ="nkonakalla@teslamotors.com,bpurohit@teslamotors.com,WD-BuildEngineer@teslamotors.com,SGopal@teslamotors.com,garulappan@teslamotors.com"
EmailTo=MailTo

Const SMTPServer = "smtp.gmail.com"
Const SMTPLogon = "raveelucky@gmail.com"
Const SMTPPassword = "sairammm"
Const SMTPSSL = true
Const SMTPPort = 465


Const cdoSendUsingPickup = 1 	'Send message using local SMTP service pickup directory.
Const cdoSendUsingPort = 2 	'Send the message using SMTP over TCP/IP networking.

Const cdoAnonymous = 0 	' No authentication
Const cdoBasic = 1 	' BASIC clear text authentication
Const cdoNTLM = 2 	' NTLM, Microsoft proprietary authentication

' First, create the message

Set objMessage = CreateObject("CDO.Message")
objMessage.Subject = EmailSubject
objMessage.From = """" & EmailFromName & """ <" & EmailFrom & ">"
objMessage.To = EmailTo


objMessage.AddAttachment destinationPath
Set objFSO = CreateObject("Scripting.FileSystemObject") 
objFSO.DeleteFile(destinationPath)

'objMessage.TextBody = EmailBody

'objMessage.CreateMHTMLBody "file:///"&sAutomationPath&sProjectName&"\ANT\apache-ant-1.9.3\bin\test\runtime-totals\output\mail.html"
objMessage.CreateMHTMLBody "file:///"&temp&"\output\mail.html"
' Second, configure the server

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/sendusing") = 2

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpserver") = SMTPServer

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpauthenticate") = cdoBasic

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/sendusername") = SMTPLogon

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/sendpassword") = SMTPPassword

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpserverport") = SMTPPort

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpusessl") = SMTPSSL

objMessage.Configuration.Fields.Item _
("http://schemas.microsoft.com/cdo/configuration/smtpconnectiontimeout") = 60

objMessage.Configuration.Fields.Update

' Now send the message!

objMessage.Send