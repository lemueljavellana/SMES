package com.smes.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		return getUnpaidTransaction(customerId, null, pageSetting);
	}
	
	public Page<AccountTransaction> getUnpaidTransaction (int customerId,
			int currentPage, int maxPerPage) {
		PageSetting pageSetting = new PageSetting(currentPage, maxPerPage);
		return getUnpaidTransaction(customerId, null, pageSetting);
	}
	
	public Page<AccountTransaction> getUnpaidTransaction (int customerId, int currentPage,
			int maxPage, List<Integer> exclude){
		PageSetting pageSetting = new PageSetting(currentPage, maxPage);
		return getUnpaidTransaction(customerId, exclude, pageSetting);
	}
	
	private Page<AccountTransaction> getUnpaidTransaction (int customerId, List<Integer> exclude, PageSetting pageSetting){
		return accountDao.getUnpaidTransactions(customerId, exclude, pageSetting);
	}
	
	public void deleteAccounts (List<Integer> accountIds){
		for (Integer id : accountIds){
			accountDao.delete(id);
		}
	}
}
