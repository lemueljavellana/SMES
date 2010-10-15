package com.smes.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.Customer;
import com.smes.service.CustomerService;
import com.smes.web.dto.CustomerListDto;

@Controller
@RequestMapping ("/customerlist")
public class CustomerListContoller {

	private CustomerService customerService;
	
	@Autowired
	public CustomerListContoller (CustomerService customerService){
		this.customerService = customerService;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showCustomerFrom (ModelMap model) {
		System.out.println("show customer");
		Collection<Customer> customers = 
			customerService.getAllCustomers(1);
		CustomerListDto customerListDto =
			CustomerListDto.getInstaceof(customers);
		model.addAttribute(customerListDto);
		return "ca/CustomerList";
	}
}
