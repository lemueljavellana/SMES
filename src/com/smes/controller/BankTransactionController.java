package com.smes.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.smes.domain.hibernate.Bank;
import com.smes.service.BankTransactionService;
import com.smes.view.frm.BankTransactionFrm;

public class BankTransactionController extends MultiActionController {
	private static final Logger logger = Logger.getLogger(BankTransactionController.class);
	private BankTransactionService bankTransactionServece;

	public BankTransactionService getBankTransactionServece() {
		return bankTransactionServece;
	}

	public void setBankTransactionServece(
			BankTransactionService bankTransactionServece) {
		this.bankTransactionServece = bankTransactionServece;
	}
	
	public ModelAndView bankTransactions (HttpServletRequest request, 
			HttpServletResponse response){
		System.out.println("entering : " + BankTransactionController.class);
		BankTransactionFrm bankTransactionFrom = new BankTransactionFrm();
		Collection<Bank> banks = bankTransactionServece.getBanks();
		int bankId = banks.iterator().next().getBankId();
		bankTransactionFrom.setBanks(banks);
		bankTransactionFrom.setBankTransactions(bankTransactionServece.getBankTransactions(bankId));
		ModelAndView mv = new ModelAndView("jsp/bank/BankTransaction.jsp");
		System.out.println(bankTransactionFrom);
		mv.addObject("bankTransactionFrom", bankTransactionFrom);
		return mv;
	}
}
