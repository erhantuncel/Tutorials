package com.journaldev.spring.di.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journaldev.spring.di.services.MessageService;

@Component
public class MyApplication {
	
//  field-based dependency injection
//	@Autowired
	private MessageService service;
	
//	// constructor-based dependency injection
//	@Autowired
//	public MyApplication(MessageService svc) {
//		this.setService(svc);
//	}

	// Method-based dependency injection
	@Autowired
	public void setService(MessageService service) {
		this.service = service;
	}
	
	public boolean processMessage(String msg, String rec) {
		// some magic like validation, logging etc.
		return this.service.sendMessage(msg, rec);
	}
}