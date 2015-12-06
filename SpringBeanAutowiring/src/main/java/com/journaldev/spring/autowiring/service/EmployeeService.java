package com.journaldev.spring.autowiring.service;

import com.journaldev.spring.autowiring.model.Employee;

public class EmployeeService {
	
	private Employee employee;
	
	// Constructor is used for autowire by constructor.
	public EmployeeService(Employee emp) {
		System.out.println("Autowiring by constructor used");
		this.employee = emp;
	}
	
	// Default constructor to avoid BeanInstantiationException for autowire byName or byType
	public EmployeeService() {
		System.out.println("Default constructor is used");
	}
	
	// Used for autowire byName or byType
	public void setEmployee(Employee emp) {
		this.employee = emp;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}
}

