package dev.fun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sendgrid.SendGrid.Email;

public class SendGridServiceTest {
	
	@Test
	public void testSendGridService() {
		
		String toAddress = "CustomerServiceTesting0@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		SendGridService test = new SendGridService(toAddress, fullName, subject, emailText);
		
		Email email = test.getEmail();
		String fromAddr = email.getFrom();
		assertTrue (fromAddr.equalsIgnoreCase("CustomerServiceTesting0@sendgrid.com") ||
		fromAddr.equalsIgnoreCase("CustomerServiceTesting1@sendgrid.com")) ;
		
		String[] tos = email.getTos();
		assertEquals(1, tos.length);
		
		String to = tos[0];	
		assertEquals("CustomerServiceTesting0@gmail.com", to);
		
		assertEquals(subject, email.getSubject());
		assertTrue(email.getText().contains(emailText));
		assertTrue(email.getText().contains(fullName));
	}

}
