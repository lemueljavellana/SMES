package com.smes.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;
import com.smes.domain.hibernate.AccountType;
import com.smes.domain.hibernate.Customer;
import com.smes.domain.hibernate.Payment;
import com.smes.service.AccountTypeService;
import com.smes.service.CustomerService;
import com.smes.service.PaymentAccountService;
import com.smes.web.dto.AccountTransactionDto;
import com.smes.web.dto.AccountTransactionMgr;

@Controller
@RequestMapping("/accountTransaction/{customerId}")
public class AccountTransactionController {
	private final PaymentAccountService paymentAccountService;
	private final CustomerService customerService;
	private final AccountTypeService accountTypeService;

	@Autowired
	public AccountTransactionController(
			PaymentAccountService paymentAccountService,
			CustomerService customerService, AccountTypeService accountTypeService) {
		this.paymentAccountService = paymentAccountService;
		this.customerService = customerService;
		this.accountTypeService = accountTypeService;
	}

	@InitBinder
	public void initBindier(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showTransactions(
			@PathVariable("customerId") String customerId, Model model) {
		System.out.println("show transactions");
		Customer customer = customerService.getCustomer(Integer
				.valueOf(customerId));
		Collection<AccountTransaction> ats = paymentAccountService
				.getTransactions(1, customer.getCustomerId(), 0);
		Collection<AccountTransactionDto> acDto = new ArrayList<AccountTransactionDto>();
		for (AccountTransaction at : ats)
			acDto.add(AccountTransactionDto.getInstance(at));
		AccountTransactionMgr mgr = new AccountTransactionMgr();
		mgr.setAccountTrasactionDtos(acDto);
		mgr.setCustomer(customer);
		model.addAttribute(mgr);
		return "customersAccount";
	}

	@RequestMapping(value = "/editAccount/{accountId}", method = RequestMethod.GET)
	public String editAccountTransaction(
			@PathVariable("customerId") String customerId,
			@PathVariable("accountId") String accountId, Model model) {
		System.out.println("edit account transaction");
		Account account = paymentAccountService.getAccount(Integer
				.valueOf(accountId));
		account.setCustomerId(Integer.valueOf(customerId));
		model.addAttribute(account);
		return "addAccountTransaction";
	}

	@RequestMapping(value = "/editAccount/{accountId}", method = RequestMethod.POST)
	public String saveEditAccountTransaction(
			@PathVariable("customerId") String customerId,
			@PathVariable("accountId") String accountId,
			@ModelAttribute("account") Account account, Model model) {
		System.out.println("save edit account transaction");
		return saveAccount(customerId, account, model);
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String showAccountTransaction( @PathVariable("customerId") String customerId,
			Model model) {
		System.out.println("showAccountTransaction");
		Account account = new Account();
		account.setCustomerId(Integer.valueOf(customerId));
		Collection<AccountType> accountTypes = accountTypeService.getAccountTypes(1);
		model.addAttribute(account);
		return "addAccountTransaction";
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String saveAccount(@PathVariable("customerId") String customerId,
			@ModelAttribute("account") Account account, Model model) {
		System.out.println("saveAccount");
		// to set the H:s
		account.setAccountDate(includeTime(account.getAccountDate()));
		account.setCompanyId(1);
		AuditUtil.addAudit(account);
		paymentAccountService.saveAccount(account);
		model.addAttribute("customerId", account.getCustomerId());
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
			@ModelAttribute("payment") Payment payment, Model model) {
		payment.setCompanyId(1);
		payment.setPaymentDate(includeTime(payment.getPaymentDate()));
		AuditUtil.addAudit(payment);
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
			@ModelAttribute ("payment") Payment payment, Model model){
		return savePaymentForm(customerId, payment, model);
	}

	@RequestMapping (value = "/deleteTransaction/{tag}", method = RequestMethod.GET)
	public String deleteTransaction (@PathVariable("customerId") String customerId,
			@PathVariable ("tag") String tag, Model model){
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
