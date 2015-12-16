package com.erhan.embeddeddb;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erhan.embeddeddb.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		// model.addAttribute("userCount", userService.getCountUserByCountry("Türkiye"));
		model.addAttribute("users", userService.findAll());
		return "home";
	}
	
	
	@RequestMapping(value="/searchByCountry", method=RequestMethod.POST)
	public String searchByCountry(@RequestParam("country") String country, Model model) {
		
		logger.info("seachByCountry");
		
		model.addAttribute("users", userService.findByCountry(country));
		
		return "home";
	}
	
}
