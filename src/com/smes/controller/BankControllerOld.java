package com.smes.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.smes.domain.hibernate.Bank;
import com.smes.service.BankService;
import com.smes.view.frm.BankFrm;

public class BankControllerOld extends MultiActionController {
	
	private BankService bankService;
	
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	public ModelAndView onLoadBankList (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("onloadbankList");
		BankFrm bankFrm = new BankFrm();
		bankFrm.setBanks(new ArrayList<Bank>(bankService.getBanks()));
		ModelAndView mv = new ModelAndView("jsp/bank/BankMain.jsp");
		mv.addObject("bankfrm", bankFrm);
		System.out.println(bankFrm);
		return mv;
	}
}
