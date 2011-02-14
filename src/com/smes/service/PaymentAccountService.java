package com.smes.service;

import java.util.ArrayList;
import java.util.Collection;

import com.smes.dao.AccountDao;
import com.smes.dao.PaymentAccountDao;
import com.smes.dao.PaymentDao;
import com.smes.domain.Page;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction_toBeDeleted;
import com.smes.domain.hibernate.Payment;
import com.smes.web.dto.AccountTransactionDto;

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
	
	public Page<AccountTransactionDto> getTransactions (int companyId, int customerId, int currentPage) {
		PageSetting pageSetting = new PageSetting(currentPage);
		Page<AccountTransaction_toBeDeleted> page = paymentAccountDao.getAccountsTransactions(customerId, pageSetting);
		Collection<AccountTransactionDto> atds = new ArrayList<AccountTransactionDto>(page.getData().size());
		for (AccountTransaction_toBeDeleted at : page.getData())
			atds.add(AccountTransactionDto.getInstance(at));
		return new Page<AccountTransactionDto> (page.getPageSetting(), atds, page.getTotalRecords());
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
