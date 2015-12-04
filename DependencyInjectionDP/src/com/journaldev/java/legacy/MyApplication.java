package com.journaldev.java.legacy;

public class MyApplication {
	
	// Hard-coded dependency
	private EmailService email = new EmailService();
	
	public void processMessages(String msg, String rec) {
		// do some message validation, manipulation logic etc.
		this.email.sendEmail(msg, rec);
	}
}