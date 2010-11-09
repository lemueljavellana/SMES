package com.smes.service;

import java.util.Collection;

import com.smes.dao.PaymentAccountDao;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.AccountTransaction;

public class PaymentAccountService {
	private PaymentAccountDao paymentAccountDao;

	public void setPaymentAccountDao(PaymentAccountDao paymentAccountDao) {
		this.paymentAccountDao = paymentAccountDao;
	}
	
	public PaymentAccountDao getPaymentAccountDao() {
		return paymentAccountDao;
	}
	
	public Collection<AccountTransaction> getTransactions (int companyId, int currentPage) {
		PageSetting pageSetting = new PageSetting(currentPage);
		return paymentAccountDao.getAccountsTransactions(companyId, pageSetting);
	}
}
