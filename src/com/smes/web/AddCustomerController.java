package com.smes.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.BaseDomain;
import com.smes.domain.hibernate.Customer;
import com.smes.service.CustomerService;
import com.smes.validator.ca.AddCustomerValidator;

@Controller
@RequestMapping ("/addCustomer")
public class AddCustomerController {
	private final CustomerService customerService;
	private final AddCustomerValidator addCustomerValidator;
	@Autowired
	public AddCustomerController (CustomerService customerService, AddCustomerValidator addCustomerValidator){
		this.customerService = customerService;
		this.addCustomerValidator = addCustomerValidator;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showAddCustomerForm (ModelMap model){
		Customer customer = new Customer ();
		/*if (customerId != null)
			customerService.getCustomer(Integer.valueOf(customerId));*/
		model.addAttribute(customer);
		return "ca/AddCustomer";
	}

	@RequestMapping (method = RequestMethod.POST)
	public String onSubmit (@ModelAttribute ("customer") Customer customer, BindingResult result){
		customer.setCompanyId(1);
		addAudit(customer);
		addCustomerValidator.validate(customer, result);
		if (result.hasErrors())
			return "ca/AddCustomer";
		customerService.saveCustomer(customer);
		return "";
	}
	
	private void addAudit (BaseDomain domain){
		domain.setCreatedBy(1);
		domain.setCreatedDate(new Date());
		domain.setModifiedBy(1);
		domain.setModifiedDate(new Date ());
	}
}