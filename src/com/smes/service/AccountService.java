package com.smes.service;

import com.smes.dao.AccountDao;
import com.smes.domain.Page;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;

public class AccountService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}
	
	public Account getAccount (int accountId){
		return accountDao.get(accountId);
	}
	
	public void saveOrUpdate (Account account){
		accountDao.saveOrUpdate(account);
	}
	
	public Page<AccountTransaction> getUnpaidTransaction (int customerId, int currentPage) {
		PageSetting pageSetting = new PageSetting(currentPage);
		return accountDao.getUnpaidTransactions(customerId, pageSetting);
	}
}
