package com.smes.dao;

import java.util.Collection;

import com.smes.domain.hibernate.Bank;

/**
 * Data access object that handles the database transaction of bank.
 * @author Lemuel Javellana
 */
public interface BankDao {
	
	/**
	 * Get the bank.
	 * @param bankId bank id.
	 * @return The bank
	 */
	Bank getBank (int bankId);
	
	/**
	 * Get the bank using bank name
	 * @param bankName The bank name
	 * @return The bank.
	 */
	Bank getBank (String bankName);
	
	/**
	 * Get all banks. Order by bank name
	 * @return All of the banks.
	 */
	Collection<Bank> getAllBanks ();
	
	/**
	 * Save the bank. If the bank has bank id this signify for update
	 * otherwise insert.
	 * @param bank bank to be saved
	 */
	void saveBank (Bank bank);
	
	/**
	 * Delete bank.
	 * @param bank Bank to be deleted.
	 */
	void deleteBank (Bank bank);
	
	/**
	 * Delete bank.
	 * @param bankId bank id that will be deleted.
	 */
	void deleteBank (int bankId);
}
