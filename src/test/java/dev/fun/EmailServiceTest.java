package dev.fun;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * Test EmailService with a mock class extends EmailService
 * @author selina
 */
public class EmailServiceTest {
		
	private static final String TO_ADDRESS = "someone@somewhere.com";
	private static final String FULL_NAME = "first last";
	private static final String SUBJECT = "Hello Email Service";
	private static final String EMAIL_TEXT = "bla bla bla...";
	private static final String HOST_NAME = "host.com";

	private static MockEmailService mock;
	
	@BeforeClass
    public static void beforeAll() {
		
		mock = new MockEmailService(TO_ADDRESS, FULL_NAME, SUBJECT, EMAIL_TEXT, HOST_NAME);
	}
	
	@Test
	public void testEmailService() {
			
		assertEquals(TO_ADDRESS, mock.getToAddress());
		assertEquals(FULL_NAME, mock.getFullName());
		assertEquals(SUBJECT, mock.getSubject());
		assertTrue( mock.getEmailText().contains(EMAIL_TEXT));
		assertTrue( mock.getEmailText().contains(FULL_NAME));
		assertEquals(HOST_NAME, mock.getHostname());
	}
	
	
	@Test
	public void testGetEmailAccount() {
		String userName = mock.getEmailAccount(EmailService.PRE_USER_NAME, 3, EmailService.TOTAL_SEND_EMAIL_ACCOUNT_NUM);
		assertEquals("CustomerServiceTesting1", userName);
	}
	
	@Test
	public void testDecodePassword() {
		String password = mock.decodePassword("abc");
		assertEquals("hij", password);
	}
	
	@Test
	public void testToString() {
		String toString = mock.toString();
		String expectedStr = "userName =CustomerServiceTesting0 password=123Testing321 hostname=host.com fromAddress =CustomerServiceTesting0@host.com toAddress =someone@somewhere.com fullName =first last subject =Hello Email Service emailText =bla bla bla...";
		expectedStr += "\n\n-- from " + FULL_NAME;
		assertEquals(expectedStr, toString);
	}
}

class MockEmailService extends EmailService{
	
	public MockEmailService(String toAddress, String fullName, String subject,
			String emailText, String hostname) {
		super(toAddress, fullName, subject, emailText, hostname);
	}

	public boolean sendEmail() {
		return true;
	}
	
}