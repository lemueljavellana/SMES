package com.smes.dao;


import com.smes.domain.Page;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.AccountTransaction;

public interface PaymentAccountDao extends Dao <AccountTransaction> {
	/**
	 * Get the payment and account transactions. This is order by date
	 * @param companyId The company id.
	 * @param pageSetting The page settings
	 * @return The list of transactions
	 */
	Page<AccountTransaction> getAccountsTransactions (int customerId, PageSetting pageSetting);
	
	/**
	 * Get the starting total of give the page number
	 * @param pageNumber The page number
	 * @return The starting total.
	 */
	double getStartingTotal (int pageNumber);
}
