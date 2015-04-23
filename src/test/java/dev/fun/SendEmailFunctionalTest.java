package dev.fun;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SendEmailFunctionalTest {
	
	@Test
	public void testSendTrue1() {
		String toAddress = "ServiceCustomTesting@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		
		SendEmail test = new SendEmail(toAddress, fullName, subject, emailText);
		assertEquals(true, test.send());
	}
	
	@Test
	public void testSendTrue2() {
		String toAddress = "ServiceCustomTesting@gmail.com";
		String fullName = "";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		
		SendEmail test = new SendEmail(toAddress, fullName, subject, emailText);
		assertEquals(true, test.send());
	}
	
	@Test
	public void testSendTrue3() {
		String toAddress = "ServiceCustomTesting@gmail.com";
		String fullName = "My Name";
		String subject = "";
		String emailText = "This is a email from java code";
		
		SendEmail test = new SendEmail(toAddress, fullName, subject, emailText);
		assertEquals(true, test.send());
	}
	
	@Test
	public void testSendTrue4() {
		String toAddress = "ServiceCustomTesting@gmail.com";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "";
		
		SendEmail test = new SendEmail(toAddress, fullName, subject, emailText);
		assertEquals(true, test.send());
	}


	@Test
	public void testSendFailed1() {
		String toAddress = "";
		String fullName = "My Name";
		String subject = "send email to gmail";
		String emailText = "This is a email from java code";
		
		SendEmail test = new SendEmail(toAddress, fullName, subject, emailText);
		assertEquals(false, test.send());
	}

}
