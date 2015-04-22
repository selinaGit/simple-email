package dev.fun;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * class EmailServletTest test class EmailServlet
 * @author selina
 *
 */
public class EmailServletTest {
	
	@Test
	public void testGetSendEmailObject() throws ParseException {
		
		String toAddress = "ServiceCustomTesting@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		
		EmailServlet test = new EmailServlet();
		String jsonStr = "{\"toEmail\":\"ServiceCustomTesting@gmail.com\", \"fullName\":\"My Name\", "
				+ "\"emailSubject\":\"send email to gmail\", \"emailMessage\": \"This is a email from java code\"}";
		SendEmail sendEmail = test.getSendEmailObject(jsonStr);
		
		assertEquals(emailText, sendEmail.getEmailText());
		assertEquals(fullName, sendEmail.getFullName());
		assertEquals(subject, sendEmail.getSubject());
		assertEquals(toAddress, sendEmail.getToAddress());
	}

}
