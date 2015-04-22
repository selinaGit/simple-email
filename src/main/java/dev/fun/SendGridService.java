package dev.fun;

import com.sendgrid.*;
import com.sendgrid.SendGrid.*;

/**
 * 
 * class SendGridService send email by SendGrid object
 * @author selinaTriggit
 *
 */
public class SendGridService extends EmailService {

	public final static String HOST_NAME = "sendgrid.com";
	private Email email;
	
	public SendGridService(String toAddress, String fullName, String subject,
			String emailText) {

		super(toAddress, fullName, subject, emailText, HOST_NAME);
		this.email = new Email();
		setEmail();
	}

	/**
	 * This is API to send email by SendGrid
	 */
	public boolean sendEmail() {

		boolean result = false;
		boolean isActive = lookupHostname();
		if (!isActive) return result;
		
		if (checkError()<0) {
			return false;
		}
		
		SendGrid sendgrid = new SendGrid(userName, password);
		
		try {
			Response response = sendgrid.send(this.email);
			result = response.getStatus();
		} catch (SendGridException e) {
			System.err.println(e);
		}
		return result;
	}

	protected void setEmail() {
		this.email.addTo(toAddress);
		this.email.setFrom(fromAddress);
		this.email.setSubject(subject);
		this.email.setText(emailText);
	}
	
	public Email getEmail() {
		return this.email;
	}
	
}
