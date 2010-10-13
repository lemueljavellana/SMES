package com.smes.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smes.domain.hibernate.Bank;
import com.smes.service.BankService;
import com.smes.view.frm.BankFrm;

@Controller
public class BankController2 {
	private static final Logger logger = Logger.getLogger(BankController2.class);
	private final BankService bankService;
	
	@Autowired
	public BankController2 (BankService bankService){
		this.bankService = bankService;
	}

	@RequestMapping ("/bankList.htm")
	public ModelMap loadBankList (){
		logger.debug("enterig loadBankList");
		BankFrm bankFrm = new BankFrm();
		bankFrm.setBanks(new ArrayList<Bank>(bankService.getBanks()));
		System.out.println(bankFrm);
		return new ModelMap("bankfrm", bankFrm);
	}
	
}
