package com.smes.web;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.Customer;
import com.smes.service.CustomerService;
import com.smes.view.frm.Credential;
import com.smes.web.dto.CustomerDto;

@Controller
@RequestMapping ("/customerList")
public class CustomerListContoller {

	private final CustomerService customerService;
	
	@Autowired
	public CustomerListContoller (CustomerService customerService){
		this.customerService = customerService;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showCustomerFrom (Model model, HttpSession currentSession) {
		System.out.println("show customer");
		CredentialHandler.validateCredential(currentSession);
		Credential c =
			CredentialHandler.getCredential(currentSession);
		Collection<Customer> customers = 
			customerService.getAllCustomers(c.getCompanyId());
		CustomerDto customerDto =
			CustomerDto.getInstaceof(customers);
		model.addAttribute(customerDto);
		return "customerList";
	}
	
	@RequestMapping (value="/{customerId}", method = RequestMethod.GET)
	public String deleteCustomer (@PathVariable ("customerId") String customerId, 
			Model model){
		System.out.println("Delete customer");
		customerService.deleteCustomer(Integer.valueOf(customerId));
		Collection<Customer> customers = 
			customerService.getAllCustomers(1);
		CustomerDto customerDto =
			CustomerDto.getInstaceof(customers);
		model.addAttribute(customerDto);
		return "customerListSuccess"; 
	}

	@RequestMapping (method=RequestMethod.POST)
	public String searchCustomer (@ModelAttribute ("customerDto")
			CustomerDto customerDto, Model model){
		Collection<Customer> customers = null;
		customers = customerService.getCustomers(customerDto.getCustomerName(), 1);
		customerDto.setCustomers(customers);
		model.addAttribute(customerDto);
		return "customerList";
	}
}
