package com.smes.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.Page;
import com.smes.domain.hibernate.AccountTransaction;
import com.smes.domain.hibernate.Customer;
import com.smes.service.AccountService;
import com.smes.service.CustomerService;
import com.smes.service.PaymentAccountService;
import com.smes.validator.ca.AddAccountValidator;
import com.smes.view.frm.Credential;

@Controller
@RequestMapping ("/{customerId}/payment")
public class PaymentController {
	private final PaymentAccountService paymentService;
	private final CustomerService customerService;
	private final AccountService accountService;
	
	@Autowired
	public PaymentController (PaymentAccountService paymentService,
			CustomerService customerService, AccountService accountService,
			AddAccountValidator validator) {
		this.paymentService = paymentService;
		this.customerService = customerService; 
		this.accountService = accountService;
	}
	
	@InitBinder
	public void initBindier(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPaymentTab (@PathVariable("customerId") String customerId, Model model,
			HttpSession currentSession) {
		Credential c =
			CredentialHandler.getCredential(currentSession);
		Customer customer = customerService.getCustomer(Integer
				.valueOf(customerId));
		//Set as 10 max item per page.
		Page<AccountTransaction> page =
			accountService.getUnpaidTransaction(customer.getCustomerId(), 0, 10);
		model.addAttribute("accounts", page);
		return "paymentTab";
	}
}
