package com.smes.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/customerMain.htm")
public class CustomerMainController {
	
	@RequestMapping (method = RequestMethod.GET)
	public String showCustomerMain (ModelMap model){
		return "ca/CAHome";
	}
}
