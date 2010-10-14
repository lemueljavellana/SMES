package com.smes.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/customerlist.htm")
public class CustomerListContoller {
	@RequestMapping (method = RequestMethod.GET)
	public String showCustomerFrom (ModelMap model) {
		System.out.println("show customer");
		return "ca/CustomerList";
	}
}
