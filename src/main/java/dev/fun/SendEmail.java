package dev.fun;

/**
 * 
 * SendEmail class will send email by SendGridService object, if it is failed try GmailService object
 * @author selinaTriggit
 *
 */
public class SendEmail {
	
	private String toAddress;
	private String fullName;
	private String subject;
	private String emailText;
	
	public SendEmail(String toAddress, String fullName, String subject, String emailText) {
		
		this.toAddress = toAddress;
		this.fullName = fullName;
		this.subject = subject;
		this.emailText = emailText;	
	}
	
    /**
     * 
     * This is public API for send email 
     * send email by sendgrid if failed use gmail server
     * @return if email send successfully, return true, otherwise false;
     */
	public boolean send() {
		
		//send email by gridemail
		SendGridService gridEmail = new SendGridService(toAddress, fullName, subject, emailText);
		boolean result = gridEmail.sendEmail();
		 
		if (!result) {
			//send email by gmail
			GmailService gmail = new GmailService(toAddress, fullName, subject, emailText);
			result = gmail.sendEmail();
		}
		return result;
	}
	
	public String getToAddress() {
		return toAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public String getSubject() {
		return subject;
	}

	public String getEmailText() {
		return emailText;
	}

}
