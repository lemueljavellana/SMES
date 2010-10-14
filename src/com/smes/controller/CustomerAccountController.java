package com.smes.controller;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.smes.service.CustomerService;


public class CustomerAccountController extends MultiActionController{
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
