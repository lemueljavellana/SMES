package com.smes.dao.hibernate;

import com.smes.dao.AccountDao;
import com.smes.domain.hibernate.Account;

public class AccountDaoImpl extends BaseDao<Account>
	implements AccountDao{

	@Override
	protected Class<Account> getDomainClass() {
		return Account.class;
	}

}
