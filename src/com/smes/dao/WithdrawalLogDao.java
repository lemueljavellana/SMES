package com.smes.dao;

import java.util.Collection;
import java.util.Date;


import com.smes.domain.hibernate.Bank;
import com.smes.domain.hibernate.WithdrawalLog;

/**
 * Data access object for withdrawal log.
 * @author Lemuel Javellana
 */
public interface WithdrawalLogDao {
	/**
	 * Get withdrawal transactions. Order by date.
	 * @param frm date from.
	 * @param to date to.
	 * @return the list of withdrawal transactions.
	 */
	Collection<WithdrawalLog> getWithdrawals (Date frm, Date to);
	/**
	 * Get withdrawal transactions. Order by date.
	 * @param bank The bank.
	 * @return The list of withdrawal transactions that belongs to the bank.
	 */
	Collection<WithdrawalLog> getWithdrawals (Bank bank);
	/**
	 * Get withdrawal transactions. Order by date.
	 * @param bankId Banks unique id.
	 * @return The list of withdrawal that belongs to the bank.
	 */
	Collection<WithdrawalLog> getWithdrawals (int bankId);
	
	/**
	 * Save the withdrawal log. Add new entry if the withdrawalLog will
	 * have no {@link WithdrawalLog#getWithdrawalLogId()} otherwise update.
	 * @param withdrawalLog
	 */
	void saveWithdrawal (WithdrawalLog withdrawalLog);
	
	/**
	 * Get the withdrawal transaction.
	 * @param withdrawalLogId the withdrawal log id.
	 * @return The withdrawal transaction, null if no withdrawal transaction found.
	 */
	WithdrawalLog getWithdrawal (int withdrawalLogId);
	
	/**
	 * Delete the withdrawal transaction.
	 * @param withdrawalLogID the withdrawalLog id.
	 */
	void deleteWithdrawal (int withdrawalLogID);
	
	/**
	 * Persist the object.
	 * @param withdrawalLog The object to be persisted.
	 */
	void persistObject (WithdrawalLog withdrawalLog);
}
