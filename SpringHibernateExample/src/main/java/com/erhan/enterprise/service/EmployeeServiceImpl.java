package com.erhan.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.enterprise.dao.EmployeeDAO;
import com.erhan.enterprise.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Transactional
	public void persistEmployee(Employee employee) {
		employeeDAO.persistEmployee(employee);
	}

	@Transactional
	public Employee findEmployee(String id) {
		return employeeDAO.findEmployeeById(id);
	}

	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);
	}

	@Transactional
	public void deleteEmployee(Employee employee) {
		employeeDAO.deleteEmployee(employee);
	}

}
