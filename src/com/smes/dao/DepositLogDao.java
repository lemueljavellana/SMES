package com.smes.dao;

import java.util.Collection;
import java.util.Date;

import com.smes.domain.hibernate.Bank;
import com.smes.domain.hibernate.DepositLog;

/**
 * Data access object for depositLog. Handles all of database process for depositLog
 * database.
 * @author Lemuel Javellana
 */
public interface DepositLogDao {
	/**
	 * Get the deposit transactions given a range of date.
	 * @param frm from or beginning date.
	 * @param to to or end date.
	 * @return The list of deposit in the range of date.
	 */
	Collection<DepositLog> getDeposits (Date frm, Date to);
	/**
	 * Get the deposit transactions.
	 * @param bank The bank name is associated to the deposit transaction.
	 * @return The list of deposit transactions that belongs to the bank.
	 */
	Collection<DepositLog> getDeposits (Bank bank);
	/**
	 * Get the deposit transactions.
	 * @param bankId the bank unique id.
	 * @return The list of deposit transaction that belongs to the banks.
	 */
	Collection<DepositLog> getDeposits (int bankId);
	/**
	 * Get the deposit transaction.
	 * @param depositLogId the deposit transaction.
	 * @return The deposit transaction.
	 */
	DepositLog getDeposit (int depositLogId);
	/**
	 * Save the deposit transaction. Add new entry if the deposit
	 * transacation's id is not set otherwise update.
	 * @param depositLog The deposit transaction to be saved.
	 */
	void saveDeposit (DepositLog depositLog);
	
	/**
	 * Delete the deposit transaction
	 * @param depositLogId the depositLog id.
	 */
	void deleteDeposit (int depositLogId);
	
	/**
	 * Persist the object.
	 * @param depositLog The object to be persisted.
	 */
	void persistObject (DepositLog depositLog);
	
}
