package com.smes.validator.ca;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.smes.domain.hibernate.Customer;
import com.smes.service.CustomerService;

public class AddCustomerValidator implements Validator{
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService){
		this.customerService = customerService;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer)target;
		
		rejectIfEmpty (errors, customer.getFirstName(), "firstName", "First Name required");
		rejectIfEmpty (errors, customer.getLastName(), "lastName", "Last Name required");
		rejectIfEmpty (errors, customer.getContactNumber(), "contactNumber", "ContactNumber required");
		if (!customerService.uniqueCustomer(customer.getFirstName(), customer.getLastName())){
			errors.rejectValue("lastName", null, null, "first name and last name already exist");
		}
	}
	
	private void rejectIfEmpty (Errors errors, String actualfield, String field, String errorMsg){
		if (actualfield == null || actualfield.isEmpty())
			errors.rejectValue(field, null, null, errorMsg);
	}
}
