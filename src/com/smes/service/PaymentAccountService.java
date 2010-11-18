package com.smes.service;

import java.util.Collection;

import com.smes.dao.AccountDao;
import com.smes.dao.PaymentAccountDao;
import com.smes.dao.PaymentDao;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;
import com.smes.domain.hibernate.Payment;

public class PaymentAccountService {
	private PaymentAccountDao paymentAccountDao;
	private AccountDao accountDao;
	private PaymentDao paymentDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setPaymentAccountDao(PaymentAccountDao paymentAccountDao) {
		this.paymentAccountDao = paymentAccountDao;
	}
	
	public PaymentAccountDao getPaymentAccountDao() {
		return paymentAccountDao;
	}
	
	public Collection<AccountTransaction> getTransactions (int companyId, int customerId, int currentPage) {
		PageSetting pageSetting = new PageSetting(currentPage);
		return paymentAccountDao.getAccountsTransactions(customerId, pageSetting);
	}
	
	public void saveAccount (Account account){
		accountDao.saveOrUpdate(account);
	}
	
	public Account getAccount (int accountId){
		return accountDao.get(accountId);
	}
	
	public void savePayment (Payment payment) {
		paymentDao.saveOrUpdate(payment);
	}
	
	public Payment getPayment (int paymentId) {
		return paymentDao.get(paymentId);
	}
	
	public void deleteAccount (int accountId) {
		accountDao.delete(accountId);
	}
	
	public void deletePayment (int paymentId){
		paymentDao.delete(paymentId);
	}
}
