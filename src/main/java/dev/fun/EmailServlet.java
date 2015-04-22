package dev.fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * 
 * class EmailServlet build a servlet for send out email.
 */
@WebServlet(description = "simple Jetty Servlet", urlPatterns = { "/emailsend" })
public class EmailServlet extends HttpServlet{
	
private static final long serialVersionUID = -458078963074027179L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonInput="";
		if ( br!= null) {
			jsonInput = br.readLine();
		}
		
		SendEmail sendEmail;
		String output = "Email Sending Failed! Try Later On";
		try {
			sendEmail = getSendEmailObject(jsonInput);
			output = "Email Sending Failed! Try Later On";
			if (sendEmail!=null) {
				boolean result = sendEmail.send();
				if (result) {    
					output = "Email Sending Successfully ";	
				}
			} else {
				output = "Some Input is wrong";
			}
		} catch (ParseException e) {
			output = "input Json format is not right";
			e.printStackTrace();
		}
			
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(output);
		writer.close();
	}
	
	/**
	 * 
	 * get SendEmail object from json input string
	 * @param jsonInput  the json input from website with email, full name, subject and message information
	 * @return
	 * @throws ParseException 
	 */
	protected SendEmail getSendEmailObject(String jsonInput) throws ParseException {
		
		
			JSONObject json = (JSONObject)new JSONParser().parse(jsonInput);
			String toEmail = json.get("toEmail").toString();
			String fullName = json.get("fullName").toString();
			String subject = json.get("emailSubject").toString();
			String message = json.get("emailMessage").toString();
			
			// toEmail, fullName, subject, message are required, can not be empty
			if ( json== null || json.size() == 0) {
				return null;
			} else if (toEmail == null || fullName == null || subject == null || message == null) {
				return null;
			} else if (toEmail.trim().length() == 0 || fullName.trim().length() == 0 ||
					subject.trim().length() == 0  || message.trim().length() == 0 ) {
				return null;
			}
			
			return new SendEmail(toEmail, fullName, subject, message);	
	}

}
