package com.smes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.Customer;
import com.smes.service.CustomerService;

@Controller
@RequestMapping ("/addCustomer.htm")
public class AddCustomerController {
	private final CustomerService customerService;

	@Autowired
	public AddCustomerController (CustomerService customerService){
		this.customerService = customerService;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showAddCustomerForm (ModelMap model){
		Customer customer = new Customer ();
		/*if (customerId != null)
			customerService.getCustomer(Integer.valueOf(customerId));*/
		model.addAttribute(customer);
		return "ca/AddUser";
	}
}