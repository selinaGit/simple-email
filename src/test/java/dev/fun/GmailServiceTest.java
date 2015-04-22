package dev.fun;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.BeforeClass;
import org.junit.Test;

public class GmailServiceTest {
		
	private static final String TO_ADDRESS = "someone@somewhere.com";
	private static final String FULL_NAME = "first last";
	private static final String SUBJECT = "Hello Email Service";
	private static final String EMAIL_TEXT = "bla bla bla...";
	
	private static GmailService test;
	
	@BeforeClass
    public static void beforeAll() {
		
		test = new GmailService(TO_ADDRESS, FULL_NAME, SUBJECT, EMAIL_TEXT);		
	}

	@Test
	public void testgetpMailServer() {
		Properties properties = test.setMailProperties();
		assertEquals(GmailService.TRUE , properties.getProperty(GmailService.AUTH_KEY));
		assertEquals(GmailService.TRUE, properties.getProperty(GmailService.STARTTLS_ENABLE_KEY));
		assertEquals(GmailService.HOST_VALUE ,properties.getProperty(GmailService.HOST_KEY));
		assertEquals(GmailService.PORT_VALUE ,properties.getProperty(GmailService.PORT_KEY));
	}
	
	@Test 
	public void testgetMimeMessage() throws MessagingException {
		MimeMessage message = test.getMimeMessage();
		Address[] fromAddresses = message.getFrom();
		String fromAddress = fromAddresses[0].toString();
		assertTrue(fromAddress.equalsIgnoreCase("customerservicetesting0@gmail.com") || 
				fromAddress.equalsIgnoreCase("customerservicetesting1@gmail.com"));
	
		assertNotEquals(null, message.getSession());
	
		assertTrue(message.getSubject().equalsIgnoreCase(SUBJECT));
	}
	
}
