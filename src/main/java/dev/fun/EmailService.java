package dev.fun;

import  java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import  javax.naming.*;
import  javax.naming.directory.*;

/**
 * 
 * EmailService has two subclass now
 * GamilService send out email by gmail
 * GridEmailService send out email by grid email
 * @author selina
 *
 */
public abstract class EmailService {
	
	//this is for request history
	Logger logger = Logger.getLogger("requestCountLog"); 
			
	protected static AtomicInteger counter = new AtomicInteger (0);
	
	//this is encoded password for both gmail and grid email
	private final static String ENCODE_PASSWORD ="*+,M^lmbg`,+*";
	//this is the user name for both gmail and grid email
	public final static String PRE_USER_NAME = "CustomerServiceTesting";
	public final static String AT = "@";
	
	//if we get heaven request, we need more email account for send email out
	public final static int TOTAL_SEND_EMAIL_ACCOUNT_NUM = 2;
	
	public final static String MAIL_EXCHANGER = "MX";
	public final static String ENV_KEY = "java.naming.factory.initial";
	public final static String ENV_VALUE = "com.sun.jndi.dns.DnsContextFactory";
	
	//user name and password for the email account will send out email
	protected final String userName;
	protected final String password;
	
	//email information 
	protected String hostname;
	protected String fromAddress;
	protected String toAddress;
	protected String fullName;
	protected String subject;
	protected String emailText;
	
	public EmailService(String toAddress, String fullName, String subject, String emailText, String hostname) {
		
		//reset count if int overflow 
		int count = counter.getAndIncrement();
		if ( count < 0) {
			count = 0;
			counter.set(0);
		}
		
		if ( count % 1000 == 1 ) {
			logger.info("count="+count);
		}
		
		this.userName = getEmailAccount(PRE_USER_NAME, count, TOTAL_SEND_EMAIL_ACCOUNT_NUM);
		this.password = decodePassword(ENCODE_PASSWORD);
		this.toAddress = toAddress;
		this.fullName = fullName;
		this.subject = subject;
		this.emailText = emailText + "\n\n-- from " + fullName;
		this.hostname = hostname;
		this.fromAddress = this.userName + AT + this.hostname;
	}
	
	/**
	 * 
	 * @return true for send email successful, return false for email failed
	 */
	public abstract boolean sendEmail();
	
	/**
	 * 
	 * get the email account by Round-robin
	 * currently we have account below
	 * customerservicetesting0
     * customerservicetesting1
	 * @param userName
	 * @param count
	 * @param totalEmailNum
	 * @return
	 */
	protected String getEmailAccount(String userName, int count, int totalEmailNum) {
		
		return  userName + count % totalEmailNum;
	}

	/**
	 * 
	 * decode String to get password
	 * @param encodePassword
	 * @return
	 */
	protected String decodePassword(String encodePassword) {
		char[] letters = encodePassword.toCharArray();
		
		for (int i=0; i<letters.length; i++) {
			letters[i] = (char) (letters[i] + 7);
		}
		return new String(letters);
	}
	
	/**
	 * 
	 * check if the host name is active or down
	 * @return true for active; return false for service is down
	 */
	protected boolean lookupHostname() {
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(ENV_KEY, ENV_VALUE);
		
		DirContext context;
		try {
			context = new InitialDirContext( env );
			Attributes attributes = context.getAttributes( hostname, new String[] { MAIL_EXCHANGER });
			if (attributes == null ) return false;
			
			Attribute attribute = attributes.get( MAIL_EXCHANGER );
			if ( attribute == null ) return false;
			
			return attribute.size() > 0 ;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * check if the input toEmail address is correct or not
	 * @return if error return negative integer
	 */
	protected int checkError() {
		
	  // toEmail can not be empty
	  if (toAddress == null || toAddress.trim().length() == 0) {
		  return -1;
	  }
		
	  if (!isValidEmail(toAddress)) {
		  return -2;
	  }
	  return 1;
	}
	
	/**
	 * 
	 * check if the email address is valid or not
	 * @param emailAddress
	 * @return
	 */
	public static boolean isValidEmail(String emailAddress) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(emailAddress);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
	
	public String toString() {
		String output = "userName =" + userName + " password=" + password + " hostname=" + hostname +
	                    " fromAddress =" + fromAddress + " toAddress =" + toAddress + " fullName ="+ fullName + 
	                    " subject =" + subject + " emailText =" + emailText;
        return output;		
	}
	
	public int getCount() {
		return counter.get();
	}
	
	public String getHostname() {
		return hostname;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public String getSubject() {
		return subject;
	}

	public String getEmailText() {
		return emailText;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
