package com.smes.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.Page;
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
		return setPage("0", model, currentSession);
	}

	@RequestMapping (value="/page/{pageNumber}", method = RequestMethod.GET)
	public String setPage (@PathVariable String pageNumber, Model model, HttpSession currentSession){
		Credential c =
			CredentialHandler.getCredential(currentSession);
		CredentialHandler.validateCredential(currentSession);
		Page<Customer> page = customerService.getCustomers(c.getCompanyId(), Integer.valueOf(pageNumber)-1);
		CustomerDto customerDto = new CustomerDto();
		customerDto.setPage(page);
		model.addAttribute(customerDto);
		return "customerList";
	}

	@RequestMapping (value="/page/{pageNumber}/{searchCriteria}", method = RequestMethod.GET)
	public String setPageWithCriteria (@PathVariable String pageNumber, @PathVariable String searchCriteria,
			Model model, HttpSession currentSession){
		return searchCustomer(searchCriteria, Integer.valueOf(pageNumber),
				model, currentSession);
	}
	@RequestMapping (value = "/search/{searchCriteria}", method=RequestMethod.GET)
	public String searchCustomer (@PathVariable ("searchCriteria") String searchCriteira,
			Model model, HttpSession currentSession){
		return searchCustomer(searchCriteira, 0, model, currentSession);
	}
	
	@RequestMapping (value = "/search", method=RequestMethod.GET)
	public String searchCustomerEmpty (Model model, HttpSession currentSession){
		return setPage("0", model, currentSession);
	}
	
	private String searchCustomer (String criteria, int pageNumber,
			Model model, HttpSession currentSession) {
		Credential c =
			CredentialHandler.getCredential(currentSession);
		CustomerDto customerDto = new CustomerDto ();
		Page<Customer> page = customerService.getCustomers(criteria, c.getCompanyId(),
				pageNumber);
		customerDto.setPage(page);
		model.addAttribute(customerDto);
		return "customerList";
	}
}
