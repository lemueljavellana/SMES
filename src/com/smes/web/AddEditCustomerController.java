package com.smes.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.BaseDomain;
import com.smes.domain.hibernate.Customer;
import com.smes.service.CustomerService;
import com.smes.validator.ca.AddCustomerValidator;

@Controller
@RequestMapping ("/addCustomer")
public class AddEditCustomerController {
	private final CustomerService customerService;
	private final AddCustomerValidator addCustomerValidator;

	@Autowired
	public AddEditCustomerController (CustomerService customerService, AddCustomerValidator addCustomerValidator){
		this.customerService = customerService;
		this.addCustomerValidator = addCustomerValidator;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showAddCustomerForm (ModelMap model){
		model.addAttribute(new Customer ());
		return "addCustomer";
	}
	
	@RequestMapping (value="/{customerId}", method = RequestMethod.GET)
	public String showEditCustomerFrom (@PathVariable ("customerId") String customerId,
			Model model){
		System.out.println("edit");
		Customer customer = customerService.getCustomer(Integer.valueOf(customerId));
		model.addAttribute(customer);
		return "addCustomer";
	}

	@RequestMapping (method = RequestMethod.POST)
	public String onSubmit (@ModelAttribute ("customer") Customer customer, BindingResult result){
		customer.setCompanyId(1);
		addAudit(customer);
		addCustomerValidator.validate(customer, result);
		if (result.hasErrors())
			return "addCustomer";
		customerService.saveCustomer(customer);
		return "customerListSuccess";
	}

	private void addAudit (BaseDomain domain){
		domain.setCreatedBy(1);
		domain.setCreatedDate(new Date());
		domain.setModifiedBy(1);
		domain.setModifiedDate(new Date ());
	}
}