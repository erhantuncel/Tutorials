package com.mkyong.form.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkyong.form.model.User;
import com.mkyong.form.service.UserService;
import com.mkyong.form.validator.UserFormValidator;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserFormValidator userFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/user";
	}
	
	// list a page
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		logger.debug("showAllUsers()");
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}
	
	// save or update user
	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user, 
						BindingResult result, 
						Model model, 
						final RedirectAttributes redirectAttributes) {
		
		logger.debug("saveOrUpdateUser() : {}", user);
		
		if(result.hasErrors()) {
			populateDefaultModel(model);
			return "user/userform";
		} else {
			// Add messages to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			if(user.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
			}
			
			userService.saveOrUpdate(user);
			
			// POST/REDIRECT/GET
			return "redirect:/user/" + user.getId();
		}
	}
	
	// show add user form
	@RequestMapping(value="/user/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		logger.debug("showAddUserForm()");
		
		User user = new User();
		
		// set default value
		user.setName("Erhan");
		user.setEmail("test@abc.com");
		user.setAddress("abc Str");
		user.setNewsletter(true);
		user.setSex("M");
		user.setFramework(new ArrayList<String>(Arrays.asList("Spring MVC", "GWT")));
		user.setSkill(new ArrayList<String>(Arrays.asList("Spring", "Hibernate", "Groovy")));
		user.setCountry("TR");
		user.setNumber(2);
		model.addAttribute("userForm", user);
		
		populateDefaultModel(model);
		
		return "/user/userform";
	}
	
	// show update form
	@RequestMapping(value="/user/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		logger.debug("showUpdateUserForm() : {}", id);
		
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		
		populateDefaultModel(model);
		
		return "user/userform";
	}

	// delete user
	@RequestMapping(value="/user/{id}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id, 
				final RedirectAttributes redirectAttributes) {
		logger.debug("deleteUser()");
		
		userService.delete(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!"); 
		
		return "redirect:/user";
	}
	
	// show user
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {
		logger.debug("showUser()");
		
		User user = userService.findById(id);
		if(user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);
		
		return "user/show";
	}
	
	
	
	private void populateDefaultModel(Model model) {
		List<String> frameworkList = new ArrayList<String>();
		frameworkList.add("Spring MVC");
		frameworkList.add("Struts 2");
		frameworkList.add("JSF 2");
		frameworkList.add("GWT");
		frameworkList.add("Play");
		frameworkList.add("Apache Wicket");
		model.addAttribute("frameworkList", frameworkList);
		
		Map<String, String> skill = new LinkedHashMap<String, String>();
		skill.put("Hibernate", "Hibernate");
		skill.put("Spring", "Spring");
		skill.put("Struts", "Struts");
		skill.put("Groovy", "Groovy");
		skill.put("Grails", "Grails");
		model.addAttribute("javaSkillList", skill);
		
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		model.addAttribute("numberList", numbers);
		
		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("CN", "China");
		country.put("MY", "Malaysia");
		country.put("SG", "Singapore");
		country.put("TR", "Türkiye");
		country.put("US", "United States");
		model.addAttribute("countryList", country);
		
	}
}
