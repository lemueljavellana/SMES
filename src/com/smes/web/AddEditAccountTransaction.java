package com.smes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.Account;
import com.smes.service.AccountService;

@Controller
@RequestMapping ("/addAccount")
public class AddEditAccountTransaction {
	private final AccountService accountService;
	
	@Autowired
	public AddEditAccountTransaction (AccountService accountService){
		this.accountService = accountService;
	}
	
	@RequestMapping (method = RequestMethod.GET)
	public String showAccount (Model model){
		Account account = new Account();
		model.addAttribute(account);
		return "addAccountTransaction";
	}
}
