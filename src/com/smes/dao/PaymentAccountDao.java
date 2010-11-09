package com.smes.dao;

import java.util.Collection;

import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.AccountTransaction;

public interface PaymentAccountDao extends Dao <AccountTransaction> {
	/**
	 * Get the payment and account transactions. This is order by date
	 * @param companyId The company id.
	 * @param pageSetting The page settings
	 * @return The list of transactions
	 */
	Collection<AccountTransaction> getAccountsTransactions (int companyId, PageSetting pageSetting);
	
	/**
	 * Get the starting total of give the page number
	 * @param pageNumber The page number
	 * @return The starting total.
	 */
	double getStartingTotal (int pageNumber);
}