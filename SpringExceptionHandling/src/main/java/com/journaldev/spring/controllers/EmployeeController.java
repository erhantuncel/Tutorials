package com.journaldev.spring.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.exception.EmployeeNotFoundException;
import com.journaldev.spring.model.Employee;

@Controller
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String getEmployee(@PathVariable int id, Model model) throws Exception {
		if(id==1) {
			throw new EmployeeNotFoundException(id);
		} else if(id==2) {
			throw new SQLException("SQLException id = " + id);
		} else if(id==3) {
			throw new IOException("IOException id = " + id);
		} else if(id==10) {
			Employee emp = new Employee();
			emp.setName("Erhan");
			emp.setId(id);
			model.addAttribute("employee", emp);
			return "home";
		} else {
			throw new Exception("Generic Exception id = " + id);
		}
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {
		logger.error("Requested URL = " + request.getRequestURL());
		logger.error("Exception Raised = " + ex);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());
		
		modelAndView.setViewName("error");
		return modelAndView;
	}
}