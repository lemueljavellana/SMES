package com.smes.dao;

import java.util.List;

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
	Page<AccountTransaction> getUnpaidTransactions (int customerId,
			PageSetting pageSetting);
	
	/**
	 * Get the list of account receivable 
	 * @param customerId The customer id
	 * @param exclude exclude from the list
	 * @param pageSetting the page settings.
	 * @return
	 */
	Page<AccountTransaction> getUnpaidTransactions (int customerId, List<Integer> exclude,
			PageSetting pageSetting);
}
