package com.journaldev.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyMainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(MyConfiguration.class);
		MyService service = ctx.getBean(MyService.class);
		
		System.out.println("*********************************************");
		service.setVar1("Variable 1");
		service.log("service");
		
		MyService newService = ctx.getBean(MyService.class);
		newService.log("newService");
		
		System.out.println();
		System.out.println("service hashcode = " + service.hashCode());
		System.out.println("newService hashcode = " + newService.hashCode());
		System.out.println("*********************************************");
		
		ctx.close();
	}
}
