package com.journaldev.spring.main;

import java.util.Date;

public class MyService {
	
	private String var1;
	
	public void log(String msg) {
		System.out.println(new Date() + "::" + msg + " --- var1 = " + var1);
	}

	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}
}
