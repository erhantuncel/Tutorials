package com.erhan.enterprise;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.erhan.enterprise.model.Employee;
import com.erhan.enterprise.service.EmployeeService;

public class App {

	public static void main(String[] args) {
		System.out.println("Load context");
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		Employee em = new Employee();
		em.setId("123");
		em.setName("John");
		em.setAge(35);
		EmployeeService emService = (EmployeeService) context.getBean("employeeService");
		emService.persistEmployee(em);
		System.out.println("Updated Age : " + emService.findEmployee("123").getAge());
		em.setAge(32);
		emService.updateEmployee(em);
		System.out.println("Updated Age : " + emService.findEmployee("123").getAge());
		emService.deleteEmployee(em);
		context.close();
	}
}