package com.smes.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.Page;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;
import com.smes.domain.hibernate.AccountType;
import com.smes.domain.hibernate.Customer;
import com.smes.domain.hibernate.Payment;
import com.smes.service.AccountService;
import com.smes.service.AccountTypeService;
import com.smes.service.CustomerService;
import com.smes.service.PaymentAccountService;
import com.smes.validator.ca.AddAccountValidator;
import com.smes.view.frm.Credential;
import com.smes.web.dto.AccountTransactionMgr;

@Controller
@RequestMapping("/accountTransaction/{customerId}")
public class AccountTransactionController {
	private final PaymentAccountService paymentAccountService;
	private final CustomerService customerService;
	private final AccountTypeService accountTypeService;
	private final AddAccountValidator accountValidator;
	private final AccountService accountService;
	
	@Autowired
	public AccountTransactionController(PaymentAccountService paymentAccountService,
			CustomerService customerService,
			AccountTypeService accountTypeService,
			AccountService accountService,
			AddAccountValidator validator) {
		this.paymentAccountService = paymentAccountService;
		this.customerService = customerService;
		this.accountTypeService = accountTypeService;
		this.accountValidator = validator;
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
	public String showTransactions(@PathVariable("customerId") String customerId, Model model, HttpSession currentSession) {
		System.out.println("show transactions");
		Credential c =
			CredentialHandler.getCredential(currentSession);
		Customer customer = customerService.getCustomer(Integer
				.valueOf(customerId));

		Page<AccountTransaction> page = accountService.getUnpaidTransaction(customer.getCustomerId(), 0);
		AccountTransactionMgr mgr = new AccountTransactionMgr();
		mgr.setPage(page);
		mgr.setCustomer(customer);
		model.addAttribute(mgr);
		return "customersAccount";
	}

	@RequestMapping(value = "/editAccount/{accountId}", method = RequestMethod.GET)
	public String editAccountTransaction(
			@PathVariable("customerId") String customerId,
			@PathVariable("accountId") String accountId, Model model, HttpSession session) {
		System.out.println("edit account transaction");
		Credential c =
			CredentialHandler.getCredential(session);
		Account account = paymentAccountService.getAccount(Integer
				.valueOf(accountId));
		account.setCustomerId(Integer.valueOf(customerId));
		model.addAttribute(account);
		setAccountType(c, model);
		return "addAccountTransaction";
	}

	@RequestMapping(value = "/editAccount/{accountId}", method = RequestMethod.POST)
	public String saveEditAccountTransaction(
			@PathVariable("customerId") String customerId,
			@PathVariable("accountId") String accountId,
			@ModelAttribute("account") Account account, BindingResult result, Model model,
			HttpSession session) {
		System.out.println("save edit account transaction");
		return saveAccount(customerId, account, result, model, session);
	}

	private void setAccountType (Credential c, Model model) {
		Collection<AccountType> accountTypes =
			accountTypeService.getAccountTypes(c.getCompanyId());
		model.addAttribute("accountTypes", accountTypes);
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String showAccountTransaction( @PathVariable("customerId") String customerId,
			Model model, HttpSession session) {
		System.out.println("showAccountTransaction");
		Account account = new Account();
		account.setCustomerId(Integer.valueOf(customerId));
		Credential c =
			CredentialHandler.getCredential(session);
		model.addAttribute(account);
		setAccountType(c, model);
		return "addAccountTransaction";
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String saveAccount(@PathVariable("customerId") String customerId,
			@ModelAttribute("account") Account account,  BindingResult result, Model model,
			HttpSession session) {
		System.out.println("saveAccount");
		Credential c =
			CredentialHandler.getCredential(session);
		// to set the H:s
		accountValidator.validate(account, result);
		if (result.hasErrors()) {
			setAccountType(c, model);
			return "addAccountTransaction";
		}
		account.setAccountDate(includeTime(account.getAccountDate()));
		account.setCompanyId(c.getCompanyId());
		AuditUtil.addAudit(account, c);
		paymentAccountService.saveAccount(account);
		model.addAttribute("customerId", account.getCustomerId());
		return "addTransactionSuccess";
	}

	@RequestMapping (value = "/deleteAccount/{tag}", method = RequestMethod.GET)
	public String deleteAccount (@PathVariable("customerId") String customerId,
			@PathVariable ("tag") String tag, Model model){
		String[] toBeDeleted = tag.split(",");
		List<Integer> ids = new ArrayList<Integer>();
		for (String strId : toBeDeleted){
			ids.add(Integer.valueOf(strId));
		}
		accountService.deleteAccounts(ids);
		return "addTransactionSuccess";
	}
	
	@RequestMapping(value = "/addPayment", method = RequestMethod.GET)
	public String showPaymentForm(@PathVariable("customerId") String customerId, Model model) {
		System.out.println("show payment form");
		Payment payment = new Payment();
		payment.setCustomerId(Integer.valueOf(customerId));
		return showPaymentForm(payment, model);
	}

	private String showPaymentForm (Payment payment, Model model) {
		model.addAttribute(payment);
		return "paymentForm";
	}

	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public String savePaymentForm(@PathVariable("customerId") String customerId,
			@ModelAttribute("payment") Payment payment, Model model, HttpSession session) {
		Credential c =
			CredentialHandler.getCredential(session);
		payment.setCompanyId(c.getCompanyId());
		payment.setPaymentDate(includeTime(payment.getPaymentDate()));
		AuditUtil.addAudit(payment, c);
		paymentAccountService.savePayment(payment);
		model.addAttribute(payment);
		model.addAttribute("customerId", payment.getCustomerId());
		return "addTransactionSuccess";
	}

	@RequestMapping (value = "/editPayment/{paymentId}", method = RequestMethod.GET)
	public String editPayment (@PathVariable ("customerId") String customerId, 
			@PathVariable ("paymentId") String paymentId, Model model){
		Payment payment = paymentAccountService.getPayment(Integer.valueOf(paymentId));
		return showPaymentForm(payment, model);
	}

	@RequestMapping (value = "/editPayment/{paymentId}", method = RequestMethod.POST)
	public String saveEditedPayment (@PathVariable ("customerId") String customerId, 
			@PathVariable ("paymentId") String paymentId, 
			@ModelAttribute ("payment") Payment payment, Model model, HttpSession session){
		return savePaymentForm(customerId, payment, model, session);
	}

	@RequestMapping (value = "/deleteTransaction/{tag}", method = RequestMethod.GET)
	public String deleteTransaction (@PathVariable("customerId") String customerId,
			@PathVariable ("tag") String tag, Model model) {
		String[] toBeDeleted = tag.split(":");
		for (String str : toBeDeleted){
			String[] type2Id = str.split(",");
			String type = type2Id[0];
			int id = Integer.valueOf(type2Id[1]);
			if (type.equalsIgnoreCase("A")) {
				paymentAccountService.deleteAccount(id);
			} else if (type.equalsIgnoreCase("P")) {
				paymentAccountService.deletePayment(id);
			} else {
				throw new IllegalArgumentException("invalid type " + type);
			}
		}
		model.addAttribute("customerId", customerId);
		return "addTransactionSuccess";
	}

	private Date includeTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Calendar c1 = Calendar.getInstance();
		c.set(Calendar.HOUR, c1.get(Calendar.HOUR));
		c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, c1.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c1.get(Calendar.MILLISECOND));
		return c.getTime();
	}
}
