package com.smes.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountType;
import com.smes.service.AccountTypeService;
import com.smes.service.PaymentAccountService;
import com.smes.validator.ca.AddAccountValidator;
import com.smes.view.frm.Credential;

@Controller
@RequestMapping("/account/{customerId}")
public class AddEditAccountController {
	private final AccountTypeService accountTypeService;
	private final PaymentAccountService paymentAccountService;
	private final AddAccountValidator accountValidator;

	@Autowired
	public AddEditAccountController(AccountTypeService atService,
			PaymentAccountService paService,
			AddAccountValidator accountValidator) {
		this.accountTypeService = atService;
		this.paymentAccountService = paService;
		this.accountValidator = accountValidator;
	}
	
	@InitBinder
	public void initBinder (WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		dateFormat.setLenient(false);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.setValidator(new AddAccountValidator ());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showAccountTransaction(@PathVariable("customerId") String customerId,
			Model model, HttpSession session) {
		System.out.println("showAccountTransaction");
		Account account = new Account();
		account.setCustomerId(Integer.valueOf(customerId));
		Credential c = CredentialHandler.getCredential(session);
		Collection<AccountType> accountTypes = accountTypeService
				.getAccountTypes(c.getCompanyId());
		model.addAttribute(account);
		model.addAttribute("accountTypes", accountTypes);
		return "addAccountTransaction";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveAccount(@PathVariable("customerId") String customerId,
			@ModelAttribute("account") Account account, BindingResult result, Model model,
			HttpSession session) {
		System.out.println("saveAccount");
		Credential c = CredentialHandler.getCredential(session);
		// to set the H:s
		accountValidator.validate(account, result);
		if (result.hasErrors())
			return "addAccountTransaction";
		account.setAccountDate(ControllerUtil.setUpDate(account
				.getAccountDate()));
		account.setCompanyId(c.getCompanyId());
		AuditUtil.addAudit(account, c);
		paymentAccountService.saveAccount(account);
		model.addAttribute("customerId", account.getCustomerId());
		return "addTransactionSuccess";
	}

	@RequestMapping(value = "/editAccount/{accountId}", method = RequestMethod.GET)
	public String editAccountTransaction(@PathVariable("customerId") String customerId,
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
			@ModelAttribute("account") Account account, BindingResult result, Model model,
			HttpSession session) {
		System.out.println("save edit account transaction");
		return saveAccount(customerId, account, result, model, session);
	}
}
