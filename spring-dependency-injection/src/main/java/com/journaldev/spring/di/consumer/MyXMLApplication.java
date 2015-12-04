package com.journaldev.spring.di.consumer;

import com.journaldev.spring.di.services.MessageService;

public class MyXMLApplication {
	
	private MessageService service;

//	// constructor-based dependency injection
//	public MyXMLApplication(MessageService svc) {
//		this.setService(svc);
//	}
	
	
	// setter-based dependency injection
	public void setService(MessageService service) {
		this.service = service;
	}
	
	public boolean processMessage(String msg, String rec) {
		// some magic like validation, logging etc.
		return this.service.sendMessage(msg, rec);
	}
}
