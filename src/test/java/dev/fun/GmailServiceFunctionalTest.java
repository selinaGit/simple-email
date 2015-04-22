package dev.fun;

import static org.junit.Assert.*;

import org.junit.Test;

public class GmailServiceFunctionalTest {

	@Test
	public void testSuccess1() {
		String toAddress = "CustomerServiceTesting0@gmail.com";
		String fullName = "Customer Service";
		String subject = "send email to self";
		String emailText = "This is a email from java code";

		GmailService test = new GmailService(toAddress, fullName, subject, emailText);
		
		assertEquals(true, test.sendEmail());
	}
	
	@Test
	public void testSuccess2() {
		String toAddress = "CustomerServiceTesting1@gmail.com";
		String fullName = "Customer Service";
		String subject = "send email to self";
		String emailText = "This is a email from java code";

		GmailService test = new GmailService(toAddress, fullName, subject, emailText);
		
		assertEquals(true, test.sendEmail());
	}
	
	@Test
    public void testFailed() {
    	String toAddress = "";
		String fullName = "Customer Service";
		String subject = "send email to self";
		String emailText = "This is a email from java code";

		GmailService test = new GmailService(toAddress, fullName, subject, emailText);
		
		assertEquals(false, test.sendEmail());
	}
}
