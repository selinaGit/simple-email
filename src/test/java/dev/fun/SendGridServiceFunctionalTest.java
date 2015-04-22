package dev.fun;

import static org.junit.Assert.*;

import org.junit.Test;

public class SendGridServiceFunctionalTest {
	
	@Test
	public void testSendEmailTrue1() {
		String toAddress = "CustomerServiceTesting0@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		SendGridService test = new SendGridService(toAddress, fullName, subject, emailText);

		assertEquals(true, test.sendEmail());
	}
	
	@Test
	public void testSendEmailTrue2() {
		String toAddress = "CustomerServiceTesting1@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		SendGridService test = new SendGridService(toAddress, fullName, subject, emailText);
	
		assertEquals(true, test.sendEmail());
	}
	
	@Test
	public void testSendEmailFalse1() {
		String toAddress = "";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		SendGridService test = new SendGridService(toAddress, fullName, subject, emailText);
		
		assertEquals(false, test.sendEmail());
	}
}
