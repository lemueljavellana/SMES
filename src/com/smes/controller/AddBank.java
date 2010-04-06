package com.smes.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.smes.dao.hibernate.BankDaoImpl;
import com.smes.domain.hibernate.Bank;
import com.smes.service.BankService;


public class AddBank extends SimpleFormController {

	private BankService bankService;
	
	public BankService getBankService() {
		return bankService;
	}
	
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Bank bank = (Bank)command;
		addAudit(bank);
		bankService.saveBank(bank);
		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String strId = request.getParameter("bankId");
		System.out.println(strId);
		if (strId == null)
			return super.formBackingObject(request);
		int bankId = Integer.valueOf(strId);
		//int bankId = ServletRequestUtils.getIntParameter(request, "bankId");
		Bank bank = bankService.getBank(bankId);
		if (bank != null)
			return bank;
		/*int bankId = ServletRequestUtils.getRequiredIntParameter(request, "bankId");
		;*/
		return super.formBackingObject(request);
	}
	
	private void addAudit (Bank bank){
		bank.setCreatedBy(1);
		bank.setCreatedDate(new Date());
		bank.setModifiedBy(1);
		bank.setModifiedDate(new Date ());
	}
}
