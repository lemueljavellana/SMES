package com.smes.service;

import com.smes.dao.AccountDao;
import com.smes.domain.hibernate.Account;

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
}
