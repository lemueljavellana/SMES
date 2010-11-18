package com.smes.service;

import java.util.Collection;

import com.smes.dao.AccountTypeDao;
import com.smes.domain.hibernate.AccountType;

public class AccountTypeService {
	private AccountTypeDao accountTypeDao;
	
	public void setAccountTypeDao(AccountTypeDao accountTypeDao) {
		this.accountTypeDao = accountTypeDao;
	}
	
	public Collection<AccountType> getAccountTypes (int companyId){
		return accountTypeDao.getAllByCompanyId(companyId);
	}
}
