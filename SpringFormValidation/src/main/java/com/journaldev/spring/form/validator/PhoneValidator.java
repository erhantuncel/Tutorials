package com.journaldev.spring.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone arg0) {
	}

	@Override
	public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
		if(phoneNo == null) {
			return false;
		}
		
		// validate phone numbers of format "1234567890"
		if(phoneNo.matches("\\d{10}")) {
			return true;
		// validate phone numbers with -, . or spaces
		} else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
			return true;
		// validate phone numeber with extension length from 3 to 5
		} else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
			return true;
		// validating phone number where area code is in braces
		} else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
			return true;
		// return false if no matches the input
		} else {
			return false;
		}
	}
}