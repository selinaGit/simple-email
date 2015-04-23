package dev.fun;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SendEmailTest {
	
	@Test
	public void testSendEmail() {
		
		String toAddress = "ServiceCustomTesting@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		
		SendEmail test = new SendEmail(toAddress, fullName, subject, emailText);
	
		assertEquals(toAddress, test.getToAddress());
		assertEquals(fullName, test.getFullName());
		assertEquals(subject, test.getSubject());
		assertEquals(emailText, test.getEmailText());
	}

}
