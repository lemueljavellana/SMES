package com.smes.dao;

import com.smes.domain.Page;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;

public interface AccountDao extends Dao<Account>{
	/**
	 * Get the list of account transactions.
	 * @param customerId the customer Id.
	 * @param pageSetting The page settings.
	 * @return The page which handles the data.
	 */
	public Page<AccountTransaction> getUnpaidTransactions (int customerId,
			PageSetting pageSetting);
}
