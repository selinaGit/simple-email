package dev.fun;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * class GmailService: check the host status and send the email if the host status is active
 * @author selinaTriggit
 *
 */
public class GmailService extends EmailService {
			
	public final static String HOST_NAME = "gmail.com";
	public final static String AUTH_KEY = "mail.smtp.auth";
	public final static String STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";
	public final static String HOST_KEY = "mail.smtp.host";
	public final static String HOST_VALUE = "smtp.gmail.com";
	public final static String PORT_KEY = "mail.smtp.port";
	public final static String PORT_VALUE = "587";
	public final static String TRUE = "true";


	public GmailService(String toAddress, String fullName, String subject,
			String emailText) {

		super(toAddress, fullName, subject, emailText, HOST_NAME);
	}

	/**
	 * 
	 * This is API we will send email by Gmail Service
	 */
	public boolean sendEmail() {

		boolean isActive = lookupHostname();
		if (!isActive) return false;
		
		if (checkError()<0) {
			return false;
		}
		
		// Create a default MimeMessage object.
		MimeMessage message  = getMimeMessage();
		try {
			// Send message
			Transport.send(message);
			
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	protected Properties setMailProperties() {
		// Setup mail server
		Properties properties = System.getProperties();
		properties.put(AUTH_KEY, TRUE);
		properties.put(STARTTLS_ENABLE_KEY, TRUE);
		properties.put(HOST_KEY, HOST_VALUE);
		properties.put(PORT_KEY, PORT_VALUE);
		
		return properties;
	}
	
	protected MimeMessage getMimeMessage() {
		
		// Get system properties
		Properties properties = setMailProperties();
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		// Get the default Session object.
		Session session = Session.getInstance(properties, auth);
		MimeMessage message = setUpMimeMessage(session);
		
		return message;
	}

	protected MimeMessage setUpMimeMessage(Session session)  {
		
		MimeMessage message = new MimeMessage(session);
		// Set From: header field of the header.
		try {
			message.setFrom(new InternetAddress(fromAddress));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(emailText);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return message;
	}

}
