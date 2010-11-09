package com.smes.validator.ca;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.smes.domain.hibernate.CustomerAccountPreference;

public class CAPValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return CustomerAccountPreference.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		CustomerAccountPreference pref = (CustomerAccountPreference) target;
		
		if (Double.isNaN(pref.getInterest())){
			errors.rejectValue("interest", null, null, "Invalid interest");
		}
	}
}
