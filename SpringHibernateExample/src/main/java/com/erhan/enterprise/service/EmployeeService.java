package com.erhan.enterprise.service;

import com.erhan.enterprise.model.Employee;

public interface EmployeeService {
	void persistEmployee(Employee employee);
	Employee findEmployee(String id);
	void updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
}