package com.smes.dao.hibernate;

import com.smes.dao.AccountTypeDao;
import com.smes.domain.hibernate.AccountType;

public class AccountTypeDaoImpl extends CustomerAccount<AccountType> implements AccountTypeDao{

	@Override
	protected Class<AccountType> getDomainClass() {
		return AccountType.class;
	}
}
