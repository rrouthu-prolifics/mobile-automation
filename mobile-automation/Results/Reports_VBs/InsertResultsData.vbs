Call InsertResultsData (WScript.Arguments.Item(0))
'$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
Function InsertResultsData(xmlData)
		'msgbox "Entered into inser Results Data"
		Dim oXml 
		'Dim strExecuteId                       
		'Dim iTestCaseId 
		'Dim iDataSetNo
		'Dim iStepNo
		'Dim strXML -  
		'msgboX "Sridhar Upputuri: Effecta.vbs Please click on OK to proceed with your script." & vblf & vblf & strXMLData
		'strXMLData THis should be the data parsed from XML
		'Print strXMLData
		
		Set WShell = CreateObject("WScript.Shell")

		Set WShellEnv = WShell.Environment("USER")
		WrapperInputs = WShellEnv("WrapperInputValues")
		WValues = Split(WrapperInputs,"|")
		'msgbox WrapperInputs
		strEnvelope = "intECType=2&strExecuteID=" & WValues(0) & "&strTestCaseID=" & WValues(1) & "&strDatasetNo=" & WValues(2) & "&strstepNo=" & WValues(3) &"&strResultsXML=" & xmlData & "&strExecutionEngine=S" & "&strInstanceId=" & WShellEnv("Effecta_InstanceId")
		Set oXml = CreateObject("MSXML2.XMLHTTP") 
		oXml.open "POST", WShellEnv("Effecta_ServiceURL") & "/InsertResultsData", False
		call oXml.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
		oXml.send(strEnvelope)
		
		Set owsh = CreateObject("wscript.Shell")
		'msgbox oxml.status
		If oXml.Status <> 200 Then
			'ReportWriter "Pass","Generate results in Effecta DB.","Generated Results in Effecta.",INDEX_VALUE_ZERO
		'Else
			oWsh.Popup "There was an error occured while Updating the results to Effecta." & vblf &"Details:" &vblf & "ERRCODE : " & oXml.status & vblf &"Service Used:" & sInsertResultsDataUrl,5,"Uploading Execution Result to Effecta",0+16
		End If
		Set oXml 	= nothing
		Set owsh = nothing
End Function
