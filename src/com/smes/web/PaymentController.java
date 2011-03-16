package com.smes.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.smes.web.dto.FormatUtil;

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
		CredentialHandler.getCredential(currentSession);
		Customer customer = customerService.getCustomer(Integer
				.valueOf(customerId));
		//Set as 10 max item per page.
		Page<AccountTransaction> page =
			accountService.getUnpaidTransaction(customer.getCustomerId(), 0, 10);
		model.addAttribute("accounts", page);
		model.addAttribute("customerId", customer.getCustomerId());
		return "paymentTab";
	}
	
	@RequestMapping (value="/excludeAccounts/{ids}", method = RequestMethod.GET)
	public String excludeFromAR (@PathVariable("customerId") String customerId,
			@PathVariable ("ids") String ids, Model model,
			HttpSession currentSession){
		CredentialHandler.getCredential(currentSession);
		Customer customer = customerService.getCustomer(Integer.valueOf(customerId));
		List<Integer> exclude = parseIds(ids); 
			//Set as 10 max item per page.
		Page<AccountTransaction> page =	
			accountService.getUnpaidTransaction(customer.getCustomerId(), 0, 10, exclude);
		model.addAttribute("accounts", page);
		model.addAttribute("customerId", customer.getCustomerId());
		return "unpaidAccountsTable";
	}
	
	@RequestMapping (value="/toBePaidAccounts/{ids}", method = RequestMethod.GET)
	public String toBePaidAccounts (@PathVariable("customerId") String customerId,
			@PathVariable ("ids") String ids, Model model, HttpSession currentSession){
		CredentialHandler.getCredential(currentSession);
		Customer customer = customerService.getCustomer(Integer.valueOf(customerId));
		List<Integer> toBePaidAccounts = parseIds(ids);
		Page<AccountTransaction> page = accountService.getUnpaidTransactionWithIds(customer.getCustomerId(),
				toBePaidAccounts);
		model.addAttribute("accounts", page);
		model.addAttribute("customerId", customer.getCustomerId());
		//set the total amount and interest earned
		double totalAmount = 0;
		double totalInterestEarned = 0;
		for (AccountTransaction at : page.getData()){
			totalAmount += at.getTotalAmount();
			totalInterestEarned += at.getEarnedInterest();
		}
		model.addAttribute("totalAmount", FormatUtil.format(totalAmount));
		model.addAttribute("totalInterestEarned", FormatUtil.format(totalInterestEarned));
		model.addAttribute("total", FormatUtil.format(totalInterestEarned + totalAmount));
		return "toBePaidAccounts";
	}
	
	private List<Integer> parseIds(String ids) {
		List<Integer> exclude = new ArrayList<Integer>(); 
		for (String strId : ids.split(","))
			exclude.add(Integer.valueOf(strId));
		return exclude;
	}
}
