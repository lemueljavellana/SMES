package com.smes.dao;

import java.util.Collection;
import java.util.Date;

import com.smes.domain.hibernate.Account;

public interface AccountDao<T> extends Dao<T>{
	
	Collection<Account> getAccounts (int customerId, Date from, Date to);
	
	Collection<Account> getAccounts (int customerId);
}
