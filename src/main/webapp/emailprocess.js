/**
 * 
 * This javascript will send input to servlet and handle respond from server
 */
var xmlHttp = createXMLHttpRequestInstance();

//Creating a new XMLHttpRequest object
function createXMLHttpRequestInstance(){
	
	var xmlHttp;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
	}
	return xmlHttp;
}

function processEmail() {

	if (xmlHttp) {
		try {
			// Create a asynchronous POST request
			xmlHttp.open("POST", "emailsend", true)

			// set this property for handle server response
			xmlHttp.onreadystatechange = receiveMessageFromServer;
			
			var emailInfo = new Object();
			emailInfo.toEmail = document.getElementById("to-email").value;
			emailInfo.fullName = document.getElementById("full-name").value;
			emailInfo.emailSubject = document.getElementById("email-subject").value;
			emailInfo.emailMessage = document.getElementById("email-message").value;
			
			errorCode = checkEmailInfoError(emailInfo);
			if ( errorCode < 0) {
				error_message = getErrorMessage(errorCode);
				displayResponseMessage(error_message) 
			} else {
				xmlHttp.send(JSON.stringify(emailInfo))
			}
			
		} catch(e) {
			alert(e.toString());
		}	
	}
	
	//this is very important. without it. the xmlHttp.status will get 0
	return false;
}

function checkEmailInfoError(emailInfo) {
	
	emailPattern ="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
	if (emailInfo == null) {
		return -1;
	} else if (emailInfo.toEmail == null || emailInfo.toEmail.trim().length == 0)  {
		return -2;
	} else if ( emailInfo.toEmail.match(emailPattern)== null) {
		return -3
	} else if (emailInfo.fullName == null || emailInfo.fullName.trim().length == 0)  {
		return -4;
	} else if (emailInfo.emailSubject == null || emailInfo.emailSubject.trim().length == 0)  {
		return -5;
	} else if (emailInfo.emailMessage == null || emailInfo.emailMessage.trim().length == 0)  {
		return -6;
	} 
	return 1;
}

function getErrorMessage(errorCode) {
	
	if (errorCode > 0 ) return;
	
	errorMessage = "EmailInfo Can Not be Null";
	if (errorCode == -2) {
		errorMessage = "To Email can not be empty";
	} else if (errorCode == -3) {
		errorMessage = "To Email format is not right";
	} else if (errorCode == -4) {
		errorMessage = "Full Name can not be empty";
	} else if (errorCode == -5) {
		errorMessage = "Email Subject can not be empty";
	} else if (errorCode == -6) {
		errorMessage = "Email Message can not be empty";
	} 
	
	return errorMessage;
}

function displayResponseMessage(responseMessage) {
	document.getElementById("response-button").style.display = "block";
	document.getElementById('response-message').innerHTML = responseMessage;
	document.getElementById('response-message').value = responseMessage;
	document.getElementById('response-message').type = "text";
}

//handle server response from server
function receiveMessageFromServer() {
	//Execution blocked till server send the response
 if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			displayResponseMessage(xmlHttp.responseText);
		} else {
			alert('Something is wrong !! Status = '+ xmlHttp.status);
		}
	}
}

function hideButton() {
	document.getElementById("response-button").style.display = "none";
}
