package com.journaldev.spring.di.services;

public class TwitterService implements MessageService {

	public boolean sendMessage(String msg, String rec) {
		System.out.println("Twitter message sent to " + rec + " with Message : " + msg);
		return true;
	}
}