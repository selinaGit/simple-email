package dev.fun;

import static org.junit.Assert.*;

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
		String to = tos[0];
		assertEquals(1, to.length());
		assertEquals("toCustomerServiceTesting0@gmail.com", to);
		
		assertEquals(subject, email.getSubject());
		assertEquals(emailText, email.getText());
	}

}
