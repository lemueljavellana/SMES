package com.smes.controller;

import java.util.Collection;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smes.domain.hibernate.Bank;
import com.smes.service.BankTransactionService;
import com.smes.view.frm.BankTransactionFrm;

//@Controller
public class BankTransactionController{
	private static final Logger logger = Logger.getLogger(BankTransactionController.class);
	private BankTransactionService bankTransactionServece;

	//@Autowired
/*	public BankTransactionController (BankTransactionService bankTransactionServece){
		this.bankTransactionServece = bankTransactionServece;
	}*/

	public void setBankTransactionServece(
			BankTransactionService bankTransactionServece) {
		this.bankTransactionServece = bankTransactionServece;
	}

	//@RequestMapping ("/bankTransaction.htm")
	public ModelMap bankTransactions (){
		logger.log(Level.DEBUG, "Entering bankTransactions");
		BankTransactionFrm bankTransactionFrom = new BankTransactionFrm();
		Collection<Bank> banks = bankTransactionServece.getBanks();
		int bankId = banks.iterator().next().getBankId();
		bankTransactionFrom.setBanks(banks);
		bankTransactionFrom.setBankTransactions(bankTransactionServece.getBankTransactions(bankId));
		return new ModelMap("bankTransactionFrom", bankTransactionFrom);
	}
}
