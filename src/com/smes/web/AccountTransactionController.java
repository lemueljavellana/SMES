package com.smes.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.AccountTransaction;
import com.smes.service.PaymentAccountService;

@Controller
@RequestMapping ("/accountTransaction")
public class AccountTransactionController {
	private final PaymentAccountService paymentAccountService;
	
	@Autowired
	public AccountTransactionController (PaymentAccountService paymentAccountService){
		this.paymentAccountService = paymentAccountService;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showTransactions (Model model){
		System.out.println("show transactions");
		Collection<AccountTransaction> ats = paymentAccountService.getTransactions(1, 0);
		for (AccountTransaction at : ats){
			System.out.println(at);
		}
		return "";
	}
}
